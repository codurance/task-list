using System;
using System.Collections.Generic;
using System.Threading;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace Tasks.Tests
{
	[TestClass]
	public sealed class ApplicationTest
	{
		public const string Prompt = "> ";

		private FakeConsole _console;
        private CancellationTokenSource _cancellationTokenSource;
		private System.Threading.Tasks.Task _applicationTask;

		[TestInitialize]
		public void StartTheApplication()
		{
			this._console = new FakeConsole();
			var taskList = new TaskList(_console, new Dictionary<string, IList<Task>>());

			_cancellationTokenSource = new CancellationTokenSource();
            _applicationTask = System.Threading.Tasks.Task.Run(() => taskList.Run(), _cancellationTokenSource.Token);
        }

		[TestCleanup]
		public void KillTheApplication()
		{
            try
            {
                _cancellationTokenSource.Cancel();
                System.Threading.Tasks.Task.WaitAll(new[] {_applicationTask}, _cancellationTokenSource.Token);
            }
            catch (OperationCanceledException)
            {
				//ignore
            }
		}

		[TestMethod]
		public void Show_Ne_Renvoi_Rien_Initialement()
        {
			Execute("show");
			ReadLines();
        }

		[TestMethod]
		public void ItWorks()
		{
			Execute("show");

			Execute("add project secrets");
			Execute("add task secrets Eat more donuts.");
			Execute("add task secrets Destroy all humans.");

			Execute("show");
			ReadLines(
				"secrets",
				"    [ ] 1: Eat more donuts.",
				"    [ ] 2: Destroy all humans.",
				""
			);

			Execute("add project training");
			Execute("add task training Four Elements of Simple Design");
			Execute("add task training SOLID");
			Execute("add task training Coupling and Cohesion");
			Execute("add task training Primitive Obsession");
			Execute("add task training Outside-In TDD");
			Execute("add task training Interaction-Driven Design");

			Execute("check 1");
			Execute("check 3");
			Execute("check 5");
			Execute("check 6");

			Execute("show");
			ReadLines(
				"secrets",
				"    [x] 1: Eat more donuts.",
				"    [ ] 2: Destroy all humans.",
				"",
				"training",
				"    [x] 3: Four Elements of Simple Design",
				"    [ ] 4: SOLID",
				"    [x] 5: Coupling and Cohesion",
				"    [x] 6: Primitive Obsession",
				"    [ ] 7: Outside-In TDD",
				"    [ ] 8: Interaction-Driven Design",
				""
			);

			Execute("quit");
		}

		private void Execute(string command)
		{
			Read(Prompt);
			Write(command);
		}

		private void Read(string expectedOutput)
		{
			var length = expectedOutput.Length;
			var actualOutput = _console.RetrieveOutput(length);
			Assert.AreEqual(expectedOutput, actualOutput);
		}

		private void ReadLines(params string[] expectedOutput)
		{
			foreach (var line in expectedOutput)
			{
				Read(line + Environment.NewLine);
			}
		}

		private void Write(string input)
		{
			_console.SendInput(input + Environment.NewLine);
		}
	}
}
