package co.edu.uco.taskpeak.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"co.edu.uco.taskpeak.api.controller"})
public class TaskPeakApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskPeakApplication.class, args);
	}

}
