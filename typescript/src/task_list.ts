import * as readline from 'readline';
import * as util from 'util';
import { Task } from './task';

enum Commands {
    SHOW = 'show',
    ADD_PROJECT = 'add project',
    ADD_TASK = 'add task',
    CHECK = 'check',
    UNCHECK = 'uncheck',
    HELP = 'help',
    QUIT = "QUIT"
}

export class TaskList {
    private readonly readlineInterface;
    private readonly tasks: Record<string, Task[]> = {};
    private lastId = 0;

    constructor(private readonly reader: NodeJS.ReadableStream, private readonly writer: NodeJS.WritableStream) {
        this.readlineInterface = readline.createInterface({
            terminal: false,
            input: reader,
            output: writer,
        });

        this.setupReadline();
    }

    private setupReadline() {
        this.readlineInterface.setPrompt('> ');
        this.readlineInterface.on('line', this.handleCommand.bind(this));
        this.readlineInterface.on('close', () => this.writer.end());
    }

    private handleCommand(commandLine: string) {
        const [command, rest] = this.splitFirstSpace(commandLine);

        switch (command) {
            case Commands.QUIT:
                this.readlineInterface.close();
                break;
            case Commands.SHOW:
                this.show();
                break;
            case Commands.ADD_PROJECT:
                this.addProject(rest);
                break;
            case Commands.ADD_TASK:
                this.addTask(rest);
                break;
            case Commands.CHECK:
                this.setDone(rest, true);
                break;
            case Commands.UNCHECK:
                this.setDone(rest, false);
                break;
            case Commands.HELP:
                this.help();
                break;
            default:
                this.error(command);
                break;
        }

        this.readlineInterface.prompt();
    }

    private splitFirstSpace(s: string): [string, string] {
        const pos = s.indexOf(' ');
        if (pos === -1) {
            return [s, ''];
        }
        return [s.substr(0, pos), s.substr(pos + 1)];
    }

    private show() {
        for (const [project, taskList] of Object.entries(this.tasks)) {
            this.println(project);
            taskList.forEach((task) => {
                this.println(util.format('    [%s] %d: %s', task.done ? 'x' : ' ', task.id, task.description));
            });
            this.println('');
        }
    }

    private addProject(name: string) {
        this.tasks[name] = [];
    }

    private addTask(commandLine: string) {
        const [project, description] = this.splitFirstSpace(commandLine);
        const projectTasks = this.tasks[project];

        if (!projectTasks) {
            this.println(util.format('Could not find a project with the name "%s".', project));
            return;
        }

        projectTasks.push(new Task(this.nextId(), description, false));
    }

    private setDone(idString: string, done: boolean) {
        const id = parseInt(idString, 10);

        for (const taskList of Object.values(this.tasks)) {
            const task = taskList.find((t) => t.id === id);
            if (task) {
                task.done = done;
                return;
            }
        }

        this.println(util.format('Could not find a task with an ID of %d.', id));
    }

    private help() {
        this.println('Commands:');
        this.println('  show');
        this.println('  add project <project name>');
        this.println('  add task <project name> <task description>');
        this.println('  check <task ID>');
        this.println('  uncheck <task ID>');
        this.println('');
    }

    private error(command: string) {
        this.println(`I don't know what the command "${command}" is.`);
    }

    private println(ln: string) {
        this.readlineInterface.output.write(ln);
        this.readlineInterface.output.write('\n');
    }

    private nextId(): number {
        return ++this.lastId;
    }

    run() {
        this.readlineInterface.prompt();
    }
}

if (require.main === module) {
    new TaskList(process.stdin, process.stdout).run();
}
