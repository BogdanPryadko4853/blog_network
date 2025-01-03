package com.example.blog_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

import com.example.blog_app.config.SwaggerConfiguration;

@SpringBootApplication
@EnableAsync
@Import(SwaggerConfiguration.class)
public class BlogAppApplication {

	public static void main(String[] args) {
		System.out.println("Welcome to the reddit clone");
		SpringApplication.run(BlogAppApplication.class, args);
	}

}

