package com.roypr.content_calendar.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.roypr.content_calendar.model.Content;
import com.roypr.content_calendar.repository.ContentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

//@Profile("!dev") //Don't run below bean(DataLoader) when active profile is dev, meaning dev env.
@Component
public class DataLoader implements CommandLineRunner {
    //CommandLineRunner can be used to run some bootstrapping code like db row loading.
    //It is run after dependency injection is done as the app starts up.

    //both fields provided by Spring by dependency injection
    private final ContentRepository repository;
    private final ObjectMapper objectMapper;

    public DataLoader(ContentRepository repository, ObjectMapper objectMapper) {
        this.repository = repository;
        this.objectMapper = objectMapper; //automatically initialized Spring bean present in IOC container
        // as jackson is in classpath
    }


    /**
    Loads a JSON file from resources.
    Deserializes it into a List<Content>.
    Saves all items to the repository.
    Closes the file automatically.
     */
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Hello from DataLoader :)");
        //if (repository.count()==0){ //Run bootstrap data loader only when the content table is empty
        try(InputStream inputStream = TypeReference.class.getResourceAsStream("/data/content.json")){
            repository.saveAll(objectMapper.readValue(
                    inputStream,
                    new TypeReference<List<Content>>(){} //this argument is an anonymous inner class object
                    //(with no body) implementing abstract TypeReference<T>
                    )
            );
            }
        }
    //}
}
