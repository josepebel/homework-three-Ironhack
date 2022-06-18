package com.homework3.homework3overriders;

import com.homework3.homework3overriders.supportiveClasses.CheckCommands;
import com.homework3.homework3overriders.supportiveClasses.PrintText;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
// *** IMPORTANT***
// The line below must be UNCOMMENTED to run the APP and COMMENTED to run the tests
public class Homework3overridersApplication implements CommandLineRunner {

// *** IMPORTANT***
// The line below must be COMMENTED to run the APP and UNCOMMENTED to run the tests
//public class Homework3overridersApplication {

	@Autowired
	CheckCommands checkCommands;

	private static Logger LOG = LoggerFactory.getLogger(Homework3overridersApplication.class);


	public static void main(String[] args) {

		LOG.info("STARTING THE APPLICATION");
		SpringApplication.run(Homework3overridersApplication.class, args).close();
		LOG.info("APPLICATION FINISHED");
	}


	// *** IMPORTANT *** //
	// The code block below must be UNCOMMENTED to run the APP and must be COMMENTED to run the tests

	////////////////////////////////////////////////////////////
	@Override
	public void run(String... args) throws Exception {
		try {
			String command = null;
			Scanner scanner = new Scanner(System.in);
			do{
				PrintText.insertCommand();
				try {
					command = scanner.nextLine();
					checkCommands.checkCommand(command);
				} catch (IllegalArgumentException e){
					PrintText.wrongCommand(e.getMessage());
				} finally {
					Thread.sleep(500);
				}

			} while (!command.trim().equalsIgnoreCase("exit"));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	////////////////////////////////////////////////////////////

}
