using System;
using System.IO;

namespace Tasks
{
	class ProducerConsumerStream : Stream
	{
		private readonly MemoryStream _underlyingStream;
		private long _readPosition;
		private long _writePosition;

		public ProducerConsumerStream()
		{
			this._underlyingStream = new MemoryStream();
		}

		public override void Flush()
		{
			lock (_underlyingStream)
			{
				_underlyingStream.Flush();
			}
		}

		public override int Read(byte[] buffer, int offset, int count)
		{
			lock (_underlyingStream)
			{
				_underlyingStream.Position = _readPosition;
				int read = _underlyingStream.Read(buffer, offset, count);
				_readPosition = _underlyingStream.Position;
				return read;
			}
		}

		public override void Write(byte[] buffer, int offset, int count)
		{
			lock (_underlyingStream)
			{
				_underlyingStream.Position = _writePosition;
				_underlyingStream.Write(buffer, offset, count);
				_writePosition = _underlyingStream.Position;
			}
		}

		public override bool CanRead => true;

        public override bool CanSeek => false;

        public override bool CanWrite => true;

        public override long Length
		{
			get
			{
				lock (_underlyingStream)
				{
					return _underlyingStream.Length;
				}
			}
		}

		public override long Position
		{
			get => throw new NotSupportedException();
            set => throw new NotSupportedException();
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
