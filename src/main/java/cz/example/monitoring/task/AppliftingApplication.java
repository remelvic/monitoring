package cz.example.monitoring.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class AppliftingApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppliftingApplication.class, args);
	}

}
