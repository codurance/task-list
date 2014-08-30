/// <reference path="../typings/tsd.d.ts" />

import readline = require('readline');

export class TaskList
{
    static QUIT = 'quit';
    readline;

    constructor(reader: NodeJS.ReadableStream, writer: NodeJS.WritableStream) {

        this.readline = readline.createInterface({
            terminal: false,
            input: reader,
            output: writer
        });

        this.readline.setPrompt("> ");
        this.readline.on('line', (cmd) => {
            if(cmd == TaskList.QUIT) {
                this.readline.close();
                return;
            }
            this.readline.prompt();
        });
        this.readline.on('close', () => {
            writer.end();
        });
    }

    run() {
        this.readline.prompt();
    }
}

if(require.main == module) {
    new TaskList(process.stdin, process.stdout).run()
}
