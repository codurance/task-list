/// <reference path="../typings/node/node.d.ts" />

import readline = require('readline');
import util = require('util');

import task = require('./task');

function splitFirstSpace(s: string) {
    var pos = s.indexOf(' ');
    if(pos === -1) {
        return [s];
    }
    return [s.substr(0, pos), s.substr(pos+1)]
}

export class TaskList
{
    static QUIT = 'quit';
    private readline;
    private tasks: {[index: string]: task.Task[]} = {};
    private lastId = 0;

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
            this.execute(cmd);
            this.readline.prompt();
        });
        this.readline.on('close', () => {
            writer.end();
        });
    }

    println(ln: string) {
        this.readline.output.write(ln);
        this.readline.output.write('\n');
    }

    run() {
        this.readline.prompt();
    }

    forEachProject(func: (key: string, value: task.Task[]) => any) {
        for(var key in this.tasks) {
            if(this.tasks.hasOwnProperty(key))
                func(key, this.tasks[key])
        }
    }

    execute(commandLine: string) {
        var commandRest = splitFirstSpace(commandLine);
        var command = commandRest[0];
        switch (command) {
            case "show":
                this.show();
                break;
            case "add":
                this.add(commandRest[1]);
                break;
            case "check":
                this.check(commandRest[1]);
                break;
            case "uncheck":
                this.uncheck(commandRest[1]);
                break;
            case "help":
                this.help();
                break;
            default:
                this.error(command);
                break;
        }
    }

    private show() {
        this.forEachProject((project, taskList) => {
            this.println(project);
            taskList.forEach((task) => {
                this.println(util.format("    [%s] %d: %s", (task.done ? 'x' : ' '), task.id, task.description));
            });
            this.println('');
        });
    }

    private add(commandLine: string) {
        var subcommandRest = splitFirstSpace(commandLine);
        var subcommand = subcommandRest[0];
        if (subcommand === "project") {
            this.addProject(subcommandRest[1]);
        } else if (subcommand === "task") {
            var projectTask = splitFirstSpace(subcommandRest[1]);
            this.addTask(projectTask[0], projectTask[1]);
        }
    }

    private addProject(name: string) {
        this.tasks[name] = [];
    }

    private addTask(project: string, description: string) {
        var projectTasks = this.tasks[project];
        if (projectTasks == null) {
            this.println(util.format("Could not find a project with the name \"%s\".", project));
            return;
        }
        projectTasks.push(new task.Task(this.nextId(), description, false));
    }

    private check(idString: string) {
        this.setDone(idString, true);
    }

    private uncheck(idString: string) {
        this.setDone(idString, false);
    }

    private setDone(idString: string, done: boolean) {
        var id = parseInt(idString, 10);
        var found = false;
        this.forEachProject((project, taskList) => {
            taskList.forEach((task) => {
                if (task.id == id) {
                    task.done = done;
                    found = true;
                }
            });
        });
        if(!found)
            this.println(util.format("Could not find a task with an ID of %d.", id));
    }

    private help() {
        this.println("Commands:");
        this.println("  show");
        this.println("  add project <project name>");
        this.println("  add task <project name> <task description>");
        this.println("  check <task ID>");
        this.println("  uncheck <task ID>");
        this.println("");
    }

    private error(command: string) {
        this.println('I don\'t know what the command "' + command + '" is.');
    }

    private nextId(): number {
        return ++this.lastId;
    }
}

if(require.main == module) {
    new TaskList(process.stdin, process.stdout).run()
}
