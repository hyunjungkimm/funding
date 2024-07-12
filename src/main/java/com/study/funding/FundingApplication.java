package com.study.funding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FundingApplication {

    public static void main(String[] args) {
        SpringApplication.run(FundingApplication.class, args);
    }

}
