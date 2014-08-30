/// <reference path="../typings/tsd.d.ts" />

import readline = require('readline');

export class TaskList
{
    readline;

    constructor(reader, writer) {

        this.readline = readline.createInterface({
            terminal: false,
            input: reader,
            output: writer
        });

        this.readline.setPrompt("> ");
        this.readline.on('line', (answer) => {
            this.readline.output.write("Hello " + answer + "\n");
            this.readline.prompt();
        });
    }

    run() {
        this.readline.output.write("What's your name?\n");
        this.readline.prompt();
    }
}

if(require.main == module) {
    new TaskList(process.stdin, process.stdout).run()
}
