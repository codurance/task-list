using System;
using System.Collections.Generic;
using System.Linq;

namespace Tasks
{
	public sealed class TaskList
	{
		private const string Quit = "quit";

		private readonly IDictionary<string, IList<Task>> _tasks;
		private readonly IConsole _console;

        private readonly ProjectList _projectList;

		private long _lastId;

		public TaskList(IConsole console, IDictionary<string, IList<Task>> tasksRepository)
        {
            _tasks = tasksRepository;
			_console = console;
			_projectList = new ProjectList(_tasks);
		}

        private void RunOnce()
        {
            _console.Write("> ");
            var command = _console.ReadLine();
            if (command == Quit)
            {
                return;
            }
            Execute(command);
		}

		public void Run()
		{
            while (true)
            {
                RunOnce();
            }
            // ReSharper disable once FunctionNeverReturns
        }

		private void Execute(string commandLine)
		{
			var commandRest = commandLine.Split(" ".ToCharArray(), 2);
			var command = commandRest[0];
			switch (command) {
			case "show":
				Show();
				break;
			case "add":
				Add(commandRest[1]);
				break;
			case "check":
				Check(commandRest[1]);
				break;
			case "uncheck":
				Uncheck(commandRest[1]);
				break;
			case "help":
				Help();
				break;
			default:
				Error(command);
				break;
			}
		}

		private void Show()
		{
			_console.Write(_projectList.Show());
		}

		private void Add(string commandLine)
		{
			var subcommandRest = commandLine.Split(" ".ToCharArray(), 2);
			var subcommand = subcommandRest[0];
			if (subcommand == "project") {
				AddProject(subcommandRest[1]);
			} else if (subcommand == "task") {
				var projectTask = subcommandRest[1].Split(" ".ToCharArray(), 2);
				AddTask(projectTask[0], projectTask[1]);
			}
		}

		private void AddProject(string name)
		{
			_tasks[name] = new List<Task>();
		}

		private void AddTask(string project, string description)
		{
			IList<Task> projectTasks = _tasks[project];
			if (projectTasks == null) {
				Console.WriteLine("Could not find a project with the name \"{0}\".", project);
				return;
			}
			projectTasks.Add(new Task { Id = NextId(), Description = description, Done = false });
		}

		private void Check(string idString)
		{
			SetDone(idString, true);
		}

		private void Uncheck(string idString)
		{
			SetDone(idString, false);
		}

		private void SetDone(string idString, bool done)
		{
			var id = int.Parse(idString);
			var identifiedTask = _tasks
                .Select(project => project.Value.FirstOrDefault(task => task.Id == id))
                .FirstOrDefault(task => task != null);
			if (identifiedTask == null) {
				_console.WriteLine("Could not find a task with an ID of {0}.", id);
				return;
			}

			identifiedTask.Done = done;
		}

		private void Help()
		{
			_console.WriteLine("Commands:");
			_console.WriteLine("  show");
			_console.WriteLine("  add project <project name>");
			_console.WriteLine("  add task <project name> <task description>");
			_console.WriteLine("  check <task ID>");
			_console.WriteLine("  uncheck <task ID>");
			_console.WriteLine();
		}

		private void Error(string command)
		{
			_console.WriteLine("I don't know what the command \"{0}\" is.", command);
		}

		private long NextId()
		{
			return ++_lastId;
		}
	}
}
