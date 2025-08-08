package com.roypr.content_calendar.controller;


import com.roypr.content_calendar.config.ContentCalenderProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {


    private final ContentCalenderProperties properties;

    //now spring is managing the ContentCalenderProperties (@ConfigurationProperties) bean
    // and will inject it into below constructor
    public HomeController(ContentCalenderProperties properties) {
        this.properties = properties;
    }

    @GetMapping("/")
    //return an object of ContentCalenderProperties record class with welcomeMessage and about
    public ContentCalenderProperties home(){
        return properties; //send back as json dictionary automatically
    }

}
