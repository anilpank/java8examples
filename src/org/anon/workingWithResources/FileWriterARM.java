package org.anon.workingWithResources;

import java.io.FileWriter;
import java.io.IOException;

/**
 * The rules around AutoCloseable have gone through a few changes in Java 8. First, Stream implements AutoCloseable and as a result all input/output 
 * (I/O)-backed streams can be used with try-with-resources. The contract of AutoCloseable has been modified from a strict “the resource must be closed” to 
 * a more relaxed “the resource can be closed.
 * 
 * @author averma
 *
 */
public class FileWriterARM implements AutoCloseable {
	private final FileWriter writer;
	
	public FileWriterARM(final String fileName) throws IOException {
		writer = new FileWriter(fileName);
	}
	public void writeStuff(final String message) throws IOException {
		writer.write(message);
	}
	@Override
	public void close() throws Exception {
		System.out.println("close called automatically...");
		writer.close();		
	}
}
