

class Console:
    def __init__(self, input_reader, output_writer):
        self.input_reader = input_reader
        self.output_writer = output_writer

    def print(self, string="", end="\n", flush=True):
        self.output_writer(string, end=end, flush=flush)

    def input(self, prompt=""):
        return self.input_reader(prompt)

