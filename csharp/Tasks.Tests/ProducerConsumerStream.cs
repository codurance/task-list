using System;
using System.IO;

namespace Tasks
{
	class ProducerConsumerStream : Stream
	{
		private readonly MemoryStream underlyingStream;
		private long readPosition = 0;
		private long writePosition = 0;

		public ProducerConsumerStream()
		{
			this.underlyingStream = new MemoryStream();
		}

		public override void Flush()
		{
			lock (underlyingStream)
			{
				underlyingStream.Flush();
			}
		}

		public override int Read(byte[] buffer, int offset, int count)
		{
			lock (underlyingStream)
			{
				underlyingStream.Position = readPosition;
				int read = underlyingStream.Read(buffer, offset, count);
				readPosition = underlyingStream.Position;
				return read;
			}
		}

		public override void Write(byte[] buffer, int offset, int count)
		{
			lock (underlyingStream)
			{
				underlyingStream.Position = writePosition;
				underlyingStream.Write(buffer, offset, count);
				writePosition = underlyingStream.Position;
			}
		}

		public override bool CanRead { get { return true; } }

		public override bool CanSeek { get { return false; } }

		public override bool CanWrite { get { return true; } }

		public override long Length
		{
			get
			{
				lock (underlyingStream)
				{
					return underlyingStream.Length;
				}
			}
		}

		public override long Position
		{
			get { throw new NotSupportedException(); }
			set { throw new NotSupportedException(); }
		}

		public override long Seek(long offset, SeekOrigin origin)
		{
			throw new NotSupportedException();
		}

		public override void SetLength(long value)
		{
			throw new NotImplementedException();
		}
	}
}
