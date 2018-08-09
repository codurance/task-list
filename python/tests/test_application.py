import subprocess
import unittest
from threading import Timer


class ApplicationTest(unittest.TestCase):
    PROMPT = "> "
    TIMEOUT = 2

    def setUp(self):
        self.proc = subprocess.Popen(
            ["python", "-m", "task_list"],
            stdin=subprocess.PIPE, stdout=subprocess.PIPE,
            universal_newlines=True)
        self.timer = Timer(self.TIMEOUT, self.proc.kill)
        self.timer.start()

    def tearDown(self):
        self.timer.cancel()
        self.proc.stdout.close()
        self.proc.stdin.close()
        while self.proc.returncode is None:
            self.proc.poll()

    def test_it_works(self):
        self.execute("show")
        self.execute("add project secrets")
        self.execute("add task secrets Eat more donuts.")
        self.execute("add task secrets Destroy all humans.")
        self.execute("show")

        self.read_lines(
            "secrets",
            "  [ ] 1: Eat more donuts.",
            "  [ ] 2: Destroy all humans.",
            "")

        self.execute("add project training")
        self.execute("add task training Four Elements of Simple Design")
        self.execute("add task training SOLID")
        self.execute("add task training Coupling and Cohesion")
        self.execute("add task training Primitive Obsession")
        self.execute("add task training Outside-In TDD")
        self.execute("add task training Interaction-Driven Design")

        self.execute("check 1")
        self.execute("check 3")
        self.execute("check 5")
        self.execute("check 6")
        self.execute("show")

        self.read_lines(
            "secrets",
            "  [x] 1: Eat more donuts.",
            "  [ ] 2: Destroy all humans.",
            "",
            "training",
            "  [x] 3: Four Elements of Simple Design",
            "  [ ] 4: SOLID",
            "  [x] 5: Coupling and Cohesion",
            "  [x] 6: Primitive Obsession",
            "  [ ] 7: Outside-In TDD",
            "  [ ] 8: Interaction-Driven Design",
            "")

        self.execute("quit")

    def execute(self, command):
        self.write(command + "\n")

    def write(self, command):
        self.read(self.PROMPT)
        self.proc.stdin.write(command)
        self.proc.stdin.flush()

    def read(self, expected_output):
        output = self.proc.stdout.read(len(expected_output))
        self.assertEqual(expected_output, output)

    def read_lines(self, *lines):
        for line in lines:
            self.read(line + "\n")
