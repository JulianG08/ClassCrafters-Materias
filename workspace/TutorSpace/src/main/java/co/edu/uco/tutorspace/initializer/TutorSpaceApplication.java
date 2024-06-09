package co.edu.uco.tutorspace.initializer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"co.edu.uco.tutorspace.controller"})
public class TutorSpaceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TutorSpaceApplication.class, args);
	}
}
