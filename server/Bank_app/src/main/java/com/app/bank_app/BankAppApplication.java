package com.app.bank_app;

import com.app.bank_app.security.auth.AuthentificationService;
import com.app.bank_app.security.auth.RegisterRequest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.LocalDate;

@SpringBootApplication
public class BankAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankAppApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(
		AuthentificationService authentificationService
	){
	return args -> {
		authentificationService.register(
				RegisterRequest.builder()
						.firstName("oussama")
						.lastName("zaoui")
						.birthday(LocalDate.of(2002,05,21))
						.email("oussama@gmail.com")
						.id_Card("UB107427")
						.phoneNumber("0615356028")
						.password("oussama")
						.build()

		);
	};

	}

}
