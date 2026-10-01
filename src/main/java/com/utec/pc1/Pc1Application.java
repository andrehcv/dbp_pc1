package com.utec.pc1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class Pc1Application {

    public static void main(String[] args) {
        SpringApplication.run(Pc1Application.class, args);
    }

}
