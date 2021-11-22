package com.team1.obvalidacion;

import com.team1.obvalidacion.repositories.RegUserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ObValidacionApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ObValidacionApplication.class, args);
		RegUserRepository regUserRepository = context.getBean(RegUserRepository.class);

		System.out.println("User's number in DB when API initialized: " + regUserRepository.count());
	}
}
