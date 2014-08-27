using System;
using System.IO;
using System.Threading;

namespace Tasks
{
	public class BlockingStream : Stream
	{
		private readonly Stream underlyingStream;

		public BlockingStream(Stream underlyingStream)
		{
			this.underlyingStream = underlyingStream;
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
			int read = 0;
			while (true)
			{
				lock (underlyingStream)
				{
					read = underlyingStream.Read(buffer, offset, count);
					if (read > 0)
					{
						return read;
					}
				}

				Thread.Yield();
			}
		}

		public override long Seek(long offset, SeekOrigin origin)
		{
			lock (underlyingStream)
			{
				return underlyingStream.Seek(offset, origin);
			}
		}

		public override void Write(byte[] buffer, int offset, int count)
		{
			lock (underlyingStream)
			{
				underlyingStream.Write(buffer, offset, count);
			}
		}

		public override void SetLength(long value)
		{
			underlyingStream.SetLength(value);
		}

		public override bool CanRead
		{
			get
			{
				return underlyingStream.CanRead;
			}
		}

		public override bool CanSeek
		{
			get
			{
				lock (underlyingStream)
				{
					return underlyingStream.CanSeek;
				}
			}
		}

		public override bool CanWrite
		{
			get
			{
				lock (underlyingStream)
				{
					return underlyingStream.CanWrite;
				}
			}
		}

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
			get
			{
				lock (underlyingStream)
				{
					return underlyingStream.Position;
				}
			}
			set
			{
				lock (underlyingStream)
				{
					underlyingStream.Position = value;
				}
			}
		}
	}
}
