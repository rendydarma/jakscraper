package com.jakmall.jakscraper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
@ComponentScan (basePackages = "com.jakmall.jakscraper")
public class JakscraperApplication {

	public static void main(String[] args) {
		SpringApplication.run(JakscraperApplication.class, args);
	}

}
