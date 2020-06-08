package com.java.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The `main` class.
 */
@SpringBootApplication
public class JavaSpringBootProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaSpringBootProjectApplication.class, args);
        System.out.println("REST App is running!");
    }

}
