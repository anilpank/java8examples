package org.anon.workingWithResources;

import java.io.FileWriter;
import java.io.IOException;

public class FileWriterExample {
	private final FileWriter writer;
	public FileWriterExample(final String fileName) throws IOException {
		writer = new FileWriter(fileName);
	}
	public void writeStuff(final String message) throws IOException {
		writer.write(message);
	}
	public void finalize() throws IOException {
		writer.close();
	}

	public void close() throws IOException {
		writer.close();
	}

	public static void main (String [] args) throws Exception {
		/**
		 * When running this, peekaboo.txt file was created but it’s empty. The finalizer never ran; the JVM decided it wasn’t
          necessary as there was enough memory.
		 */
		final FileWriterExample writerExample =
				new FileWriterExample("peekaboo.txt");
		writerExample.writeStuff("peek-a-boo");

		betterHandling();
		usingARM();
		FileWriterEAM.use("eam.txt", writerEAM -> writerEAM.writeStuff("sweet"));
	}

	/**
	 * Better handling but here are the problems
	 * We may not reach the call to close method if there was an exception in code leading up to it
	 * @throws IOException
	 */
	public static void betterHandling() throws IOException {
		final FileWriterExample writerExample =
				new FileWriterExample("peekaboo.txt");
		writerExample.writeStuff("peek-a-boo");
		writerExample.close();
	}


	/**
	 * This will ensure resource cleanup but is a lot of effort
	 * @throws IOException
	 */
	public static void ensuringCleanUp() throws IOException {
		final FileWriterExample writerExample =
				new FileWriterExample("peekaboo.txt");
		try {
			writerExample.writeStuff("peek-a-boo");
		} finally {
			writerExample.close();
		}
	}


	/**
	 * We created an instance of the class FileWriterARM within the safe haven of the 
	 * try-with-resources form and invoked the writeStuff() method within its block. 
	 * When we leave the scope of the try block, the close() method is automatically 
	 * called on the instance/resource managed by this try block.
	 * 
	 * For this to work, the compiler requires the managed resource class to implement the AutoCloseable interface
	 * 
	 * 
	 * The previous code using ARM is quite concise and charming, but the programmers have to remember to use it. The code won’t complain if we ignore this 
	 * elegant construct; it will simply create an instance and call methods like writeStuff() outside of any try blocks.
	 * @throws Exception 
	 * @throws IOException 
	 */
	public static void usingARM() throws IOException, Exception {
		try(final FileWriterARM writerARM = new FileWriterARM("peekaboo.txt")) {
			writerARM.writeStuff("peek-a-boo");
			System.out.println("done with the resource...");
		}
	}
}
