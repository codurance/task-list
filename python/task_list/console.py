

class Console:
    def __init__(self, input_reader, output_writer):
        self.input_reader = input_reader
        self.output_writer = output_writer

    def print(self, string="", end="\n"):
        self.output_writer.write(string + end)

    def input(self, prompt=""):
        self.output_writer.write(prompt)
        self.output_writer.flush()
        return self.input_reader.readline().strip()

