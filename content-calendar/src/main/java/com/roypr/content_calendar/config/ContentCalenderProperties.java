package com.roypr.content_calendar.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

//"cc" is the prefix for the below 2 variables(welcomeMessage and about) used in application.properties file
@ConfigurationProperties(value = "cc")
public record ContentCalenderProperties(String welcomeMessage, String about) {
}
