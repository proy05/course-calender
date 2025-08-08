package com.roypr.content_calendar.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    //CommandLineRunner can be used to run some bootstrapping code like db row loading.
    //It is run after dependency injection is done as the app starts up.
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Hello from DataLoader :)");
    }
}
