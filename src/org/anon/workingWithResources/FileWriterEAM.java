package org.anon.workingWithResources;

import java.io.FileWriter;
import java.io.IOException;

import org.anon.exceptions.UseInstance;

public class FileWriterEAM {
	private final FileWriter writer;

	private FileWriterEAM(final String fileName) throws IOException {
		writer = new FileWriter(fileName);
	}

	private void close() throws IOException {
		System.out.println("close called automatically...");
		writer.close();
	}

	public void writeStuff(final String message) throws IOException {
		writer.write(message);
	}

	/**
	 * The use() method represents the structure of execute around method pattern.
	 * We could have used a java.function.Consumer interface instead of defining our own UseInstance; however, since the method may 
	 * throw an exception, we needed to indicate that in our interface.
	 * @param fileName
	 * @param block
	 * @throws IOException
	 */
	public static void use(final String fileName, final UseInstance<FileWriterEAM, IOException> block) throws IOException {
		final FileWriterEAM writerEAM = new FileWriterEAM(fileName);
		try {
			block.accept(writerEAM);
		} finally {
			writerEAM.close();
		}
	}
}
