package com.roypr.content_calendar.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("!dev") //Don't run below bean(DataLoader) when active profile is dev, meaning dev env.
@Component
public class DataLoader implements CommandLineRunner {
    //CommandLineRunner can be used to run some bootstrapping code like db row loading.
    //It is run after dependency injection is done as the app starts up.
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Hello from DataLoader :)");
    }
}
