package com.edu3tospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Edu3ToSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(Edu3ToSpringApplication.class, args);
    }

}
