package com.example.schedulemanagerdevelop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ScheduleManagerDevelopApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScheduleManagerDevelopApplication.class, args);
    }

}
