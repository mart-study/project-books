package com.project.books;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ProjectBooksApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectBooksApplication.class, args);
	}

}
