package com.alnura.greco.client.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        final SpringApplication application = new SpringApplication(Application.class);
        application.run(args);
    }
}



