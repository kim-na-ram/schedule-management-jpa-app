package com.bootcamp.schedulemanagementjpaapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class ScheduleManagementJpaAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScheduleManagementJpaAppApplication.class, args);
    }

}
