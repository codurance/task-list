
export class Task
{
    constructor(private _id: number, private _description: string, private _done: boolean) {}

    get id() {
        return this._id;
    }

    get description() {
        return this._description;
    }

    get done() {
        return this._done;
    }

    set done(val: boolean) {
        this._done = val;
    }
}

