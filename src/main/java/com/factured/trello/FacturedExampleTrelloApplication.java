package com.factured.trello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.factured.trello.repository")
public class FacturedExampleTrelloApplication {

    public static void main(String[] args) {
        SpringApplication.run(FacturedExampleTrelloApplication.class, args);
    }

}
