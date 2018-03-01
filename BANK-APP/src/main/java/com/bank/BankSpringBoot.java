package com.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.bank")
public class BankSpringBoot {

	public static void main(String[] args) {
		SpringApplication.run(BankSpringBoot.class, args);
	}
}
