/// <reference path="../typings/node/node.d.ts" />

import * as util from 'util';
import * as moment from 'moment';
import { Task as task, Task } from './task';
import { splitFirstSpace } from './helper';
import { createReadlineInterface, readLineOnClose, println } from './readlinehelper';


export class TaskList
{
    static QUIT = 'quit';
    private readline;
    private tasks: {[index: string]: Task[]} = {};
    private lastId = 0;

    constructor(reader: NodeJS.ReadableStream, writer: NodeJS.WritableStream) {

        this.readline = createReadlineInterface(reader, writer);

        this.readline.setPrompt("> ");
        this.readline.on('line', (cmd) => {
            if(cmd == TaskList.QUIT) {
                this.readline.close();
                return;
            }
            this.execute(cmd);
            this.readline.prompt();
        });

        readLineOnClose(writer, this.readline);
    }

    run() {
        this.readline.prompt();
    }

    forEachProject(func: (key: string, value: Task[]) => any) {
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
            case "today":
                this.today();
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
            case "deadline":
                this.deadline(commandRest[1]);
                break;
            case "view":
                this.view(commandRest[1]);
                break;
            case "delete":
                this.delete(commandRest[1]);
                break;
            default:
                this.error(command);
                break;
        }
    }

    private show() {
        this.forEachProject((project, taskList) => {
            println(project, this.readline);
            taskList.forEach((task) => {
                println(util.format("    [%s] %s: %s %s", (task.done ? 'x' : ' '), task.id, task.description, task.deadline), this.readline);
            });
            println('',this.readline);
        });
    }

    private view(commandLine: string) {
        var subcommandRest = splitFirstSpace(commandLine);
        var subCommand = subcommandRest[0];
        if(subCommand == 'deadline'){
            this.forEachProject((project, taskList) => {
                println(project, this.readline);
                taskList.sort(function(a: any, b: any ){return a._deadline - b._deadline}).forEach((task) => {
                    println(util.format("    [%s] %s: %s %s", (task.done ? 'x' : ' '), task.id, task.description, task.deadline), this.readline);
                });
                println('', this.readline);
            });
        }
        
    }

    private today() {
        this.forEachProject((project, taskList) => {
            println(project, this.readline);
            taskList.forEach((task) => {
                if(moment(moment(task.deadline).format('YYYY-MM-DD')).isSame(moment().format('YYYY-MM-DD')))
                    println(util.format("    [%s] %s: %s %s", (task.done ? 'x' : ' '), task.id, task.description, task.deadline), this.readline);
            });
            println('', this.readline);
        });
    }

    private deadline(commandLine: string){

        var subcommandRest = splitFirstSpace(commandLine);
        var idString = subcommandRest[0];
        var date = new Date(subcommandRest[1]);
        var found = false;
        this.forEachProject((project, taskList) => {
            taskList.forEach((task) => {
                if (task.id == idString) {
                    task.deadline = date;
                    found = true
                }
            });
        });

        if(!found)
            println(util.format("Could not find a task with an ID of %s.", idString), this.readline);
    }

    private delete(idString: string){

        let found = false
        this.forEachProject((project, taskList) => {
            let newTasklist = taskList.splice(taskList.findIndex(a => a.id === idString) , 1)
            if(newTasklist.length > 0){
                found = true
            }
        });

        if(!found)
            println(util.format("Could not find a task with an ID of %s.", idString), this.readline);
    }


    private add(commandLine: string) {
        var subcommandRest = splitFirstSpace(commandLine);
        var subcommand = subcommandRest[0];
        if (subcommand === "project") {
            this.addProject(subcommandRest[1]);
        } else if (subcommand === "task") {
            var projectTask = splitFirstSpace(subcommandRest[1]);
            let idString = splitFirstSpace(projectTask[1])
            this.addTask(projectTask[0], idString[0], idString[1]);
        }
    }

    private addProject(name: string) {
        this.tasks[name] = [];
    }

    private addTask(project: string, description: string, idString: string) {
        var projectTasks = this.tasks[project];
        
        if (projectTasks == null) {
            println(util.format("Could not find a project with the name \"%s\".", project), this.readline);
            return;
        }
        let regString =  new RegExp(/[A-z0-9]*/);
        if(regString.test(idString) == true){
            projectTasks.push(new Task(idString , description, false, null));
        }
        else{
            println(util.format("please make sure you are using number and letters without space and special char \"%s\".", idString), this.readline);
            return;
        }
           
    }

    private check(idString: string) {
        this.setDone(idString, true);
    }

    private uncheck(idString: string) {
        this.setDone(idString, false);
    }

    private setDone(idString: string, done: boolean) {
        var found = false;
        this.forEachProject((project, taskList) => {
            taskList.forEach((task) => {
                if (task.id == idString) {
                    task.done = done;
                    found = true;
                }
            });
        });
        if(!found)
            println(util.format("Could not find a task with an ID of %d.", idString), this.readline);
    }

    private help() {
        println("Commands:", this.readline);
        println("  show", this.readline);
        println("  today", this.readline);
        println("  add project <project name>", this.readline);
        println("  add task <project name> <task description> <task ID>", this.readline);
        println("  check <task ID>", this.readline);
        println("  uncheck <task ID>", this.readline);
        println("  deadline <task ID>", this.readline);
        println("  delete <task ID>", this.readline);
        println("  view deadline", this.readline);
        println("", this.readline);
    }

    private error(command: string) {
        println('I don\'t know what the command "' + command + '" is.', this.readline);
    }

}

if(require.main == module) {
    new TaskList(process.stdin, process.stdout).run()
}
