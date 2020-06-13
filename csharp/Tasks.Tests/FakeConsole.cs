using System.IO;

namespace Tasks
{
	public class FakeConsole : IConsole
	{
		private readonly TextReader _inputReader;
		private readonly TextWriter _inputWriter;

		private readonly TextReader _outputReader;
		private readonly TextWriter _outputWriter;

		public FakeConsole() 
		{
			Stream inputStream = new BlockingStream(new ProducerConsumerStream());
			this._inputReader = new StreamReader(inputStream);
			this._inputWriter = new StreamWriter(inputStream) { AutoFlush = true };

			Stream outputStream = new BlockingStream(new ProducerConsumerStream());
			this._outputReader = new StreamReader(outputStream);
			this._outputWriter = new StreamWriter(outputStream) { AutoFlush = true };
		}

		public string ReadLine()
		{
			return _inputReader.ReadLine();
		}

		public void Write(string format, params object[] args)
		{
			_outputWriter.Write(format, args);
		}

		public void WriteLine(string format, params object[] args)
		{
			_outputWriter.WriteLine(format, args);
		}

		public void WriteLine()
		{
			_outputWriter.WriteLine();
		}

		public void SendInput(string input)
		{
			_inputWriter.Write(input);
		}

		public string RetrieveOutput(int length)
		{
			var buffer = new char[length];
			_outputReader.ReadBlock(buffer, 0, length);
			return new string(buffer);
		}
	}
}
