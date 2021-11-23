package com.team1.obvalidacion;

import com.team1.obvalidacion.repositories.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ObValidacionApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ObValidacionApplication.class, args);
		UserRepository userRepository = context.getBean(UserRepository.class);

		System.out.println("User's number in DB when API initialized: " + userRepository.count());

	}
}
