
class Task
{
    constructor(private id: number, private description: string, private done: boolean) {}

    getId() {
        return this.id;
    }

    getDescription() {
        return this.description;
    }

    isDone() {
        return this.done;
    }

    setDone(val: boolean) {
        this.done = val;
    }
}

