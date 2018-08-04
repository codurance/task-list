

class Task:
    def __init__(self, id_, description, done):
        self.id = id_
        self.description = description
        self.done = done

    def set_done(self, done):
        self.done = done

    def is_done(self):
        return self.done
