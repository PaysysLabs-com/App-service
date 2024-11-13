package com.paysys.mojaloop.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = { "com.paysyslabs.mojaloop.dao.repo" })

@EntityScan(basePackages = { "com.paysyslabs.mojaloop.dao.entity" })

@SpringBootApplication(scanBasePackages = { "com.paysys.mojaloop", "com.paysyslabs.bootstrap" })
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
