package org.anon;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileExamples {
	private static final String directoryPath = "C:/anil/misc/aus";
	public static void main(String [] args) throws IOException, InterruptedException {
		FileExamples fileExamples = new FileExamples();
		fileExamples.listFiles();
		fileExamples.listFiles(directoryPath);
		fileExamples.onlySubDirectories(directoryPath);
		fileExamples.selectFiles();
		fileExamples.selectFiles1();
		fileExamples.printHiddenFiles();
		fileExamples.listTheHardWay();
		fileExamples.betterWay();
		fileExamples.watchFileChange();
		//System.exit(0);
	}

	public void listFiles() throws IOException {
		Files.list(Paths.get("."))
		.forEach(System.out::println);
	}

	public void listFiles(final String dirPath) throws IOException {
		Files.list(Paths.get(dirPath))
		.forEach(System.out::println);
	}

	public void onlySubDirectories(final String dirPath) throws IOException {
		System.out.println("only sub directories");
		Files.list(Paths.get(dirPath))
		.filter(Files::isDirectory)
		.forEach(System.out::println);
	}

	public void selectFiles() {
		System.out.println("Listing only java files present in com directory");

		String[] fileList = new File("C:/anil/misc/aus/com").list(new FilenameFilter() {			
			@Override
			public boolean accept(File dir, String name) {
				// TODO Auto-generated method stub
				return name.endsWith(".java");
			}
		});
		for (String file : fileList) {
			System.out.println(file);
		}
	}

	public void selectFiles1() throws IOException {
		Files.newDirectoryStream(Paths.get("C:/anil/misc/aus/com"), path -> path.toString().endsWith(".java"))
		.forEach(System.out::println);
	}

	public void printHiddenFiles() {
		System.out.println("Print hidden files");
		File[] files = new File(directoryPath).listFiles(file -> file.isHidden());
		for (File file : files) {
			System.out.println(file);
		}		
	}

	/**
	 * List all files
	 */
	public void listTheHardWay() {
		List<File> files = new ArrayList<>();
		File[] filesInCurrentDir = new File(directoryPath).listFiles();
		for(File file : filesInCurrentDir) {
			File[] filesInSubDir = file.listFiles();
			if(filesInSubDir != null) {
				files.addAll(Arrays.asList(filesInSubDir));
			} 
			else {
				files.add(file);
			}
		}
		System.out.println("count:" + files.size());
	}
	
	
	
	/**
	 * List all files in a better way
	 */
	public void betterWay() {
		List<File> files = 
				Stream.of (new File(directoryPath).listFiles())
				.flatMap(file -> file.listFiles() == null ?
						Stream.of(file) : Stream.of(file.listFiles()))
						.collect(Collectors.toList());
		System.out.println("count:" + files.size());
	}
	
	public void watchFileChange() throws IOException, InterruptedException {
		final Path path = Paths.get(directoryPath);
		final WatchService watchService = 
				path.getFileSystem().newWatchService();
		path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);
		System.out.println("Report any file changed within next 1 minute ...");
		
		final WatchKey watchKey = watchService.poll(1, TimeUnit.MINUTES);
		if (watchKey != null) {
			watchKey.pollEvents()
			.stream()
			.forEach(event -> System.out.println(event.context()));
		}
	}



}
