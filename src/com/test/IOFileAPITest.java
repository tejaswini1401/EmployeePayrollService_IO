package com.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

import com.payrollFileHandling.EmployeePayrollService;

class NIOFileAPITest {
	
	private static String HOME = System.getProperty("user.home");
	private static String PLAY_WITH_NIO = "TempPlayGround";
	
	@Test
	public void givenPathCheckedWhenConfirmed() throws IOException{
		Path homePath = Paths.get(HOME);
		assertFalse(Files.notExists(homePath));
		
		Path playPath = Paths.get(HOME,PLAY_WITH_NIO);
		if(Files.exists(playPath))
			EmployeePayrollService.deleteFiles(playPath.toFile());
		assertTrue(Files.notExists(playPath));
		
		Files.createDirectory(playPath);
		assertTrue(Files.exists(playPath));
		
		IntStream.range(1, 10).forEach(cntr -> {
			Path tempFile = Paths.get(playPath+ "/temp"+ cntr);
			assertTrue(Files.notExists(tempFile));
			try {
				Files.createFile(tempFile);
			}
			catch(IOException e) {
				e.printStackTrace();
			}
			assertTrue(Files.exists(tempFile));
		});
		
		
		Files.list(playPath).filter(Files :: isRegularFile).forEach(System.out::println);
		Files.newDirectoryStream(playPath).forEach(System.out::println);
		Files.newDirectoryStream(playPath , path -> path.toFile().isFile() && path.toString().startsWith("temp"))
			 .forEach(System.out::println);

	}

}
