package org.anon.exceptions;

import java.io.IOException;
import java.util.stream.Stream;
import java.io.File;

public class HandleException {
	public static void main(String[] args) throws IOException {
		Stream.of("/usr", "/tmp")
		//.map(path -> new File(path).getCanonicalPath())
		.forEach(System.out::println);
		//Error, this code will not compile, if we uncomment the above line.
		
		
		//Way 1, catch the exception and re-throw a RuntimeException.
		//Offcourse this needs to be handled at method level which calls this, otherwise this will terminate the program
		/*
		In a concurrent execution, an exception raised within the lambda expressions
		will be propagated automatically to the calling primary thread. There are two
		snags: First, this will not terminate or obstruct the execution of other lambda
		expressions running concurrently. Second, if exceptions are thrown from
		multiple concurrent executions, only one of them will be reported in the catch
		block.
		*/
		Stream.of("/usr", "/tmp")
		.map(path ->  {try {
			return new File(path).getCanonicalPath();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} })
		.forEach(System.out::println);
		
		//Way 2 Handle the exception right here
		//Here we return the exception details instead 
		Stream.of("/usr", "/tmp")
		.map(path -> {
		try {
		return new File(path).getCanonicalPath();
		} catch(IOException ex) {
		return ex.getMessage();
		}
		})
		.forEach(System.out::println);
		
		
	}
}
