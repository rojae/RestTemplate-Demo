package kr.rojae.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application{

    // Main 구현 X, RestTemplateTest.java 참조
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
