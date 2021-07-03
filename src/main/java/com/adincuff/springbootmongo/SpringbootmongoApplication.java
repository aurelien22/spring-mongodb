package com.adincuff.springbootmongo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class SpringbootmongoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootmongoApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(StudentRepository repository, MongoTemplate mongoTemplate) {
		return args -> {

			Address address = new Address(
					"France",
					"Fréhel",
					"22240"
			);

			String email = "aurelien.dincuff@gmail.com";

			Student student = new Student(
					"Aurélien",
					"Dincuff",
					email,
					Gender.MALE,
					address,
					List.of("Computer science"),
					BigDecimal.TEN,
					LocalDateTime.now()
			);

			repository.findStudentByEmail(email)
					.ifPresentOrElse(s -> {
						System.out.println(s + " already exists");
					}, () -> {
						System.out.println("Inserting student " + student);
						repository.insert(student);
					});

		};
	}

}
