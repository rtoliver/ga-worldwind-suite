package au.gov.ga.worldwind.dataset.downloader;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;

public class FileRetrievalResult extends ByteBufferRetrievalResult
{
	private File file;

	public FileRetrievalResult(File file)
	{
		super(readFile(file));
		this.file = file;
	}

	private static ByteBuffer readFile(File file)
	{
		if (!file.exists() || file.isDirectory() || !file.canRead())
			return null;
		ByteBuffer buffer = ByteBuffer.allocate((int) file.length());
		try
		{
			FileInputStream fis = new FileInputStream(file);
			fis.read(buffer.array());
			fis.close();
			return buffer;
		}
		catch (Exception e)
		{
			return null;
		}
	}

	public Long lastModified()
	{
		if (hasData())
			return file.lastModified();
		return null;
	}
}
