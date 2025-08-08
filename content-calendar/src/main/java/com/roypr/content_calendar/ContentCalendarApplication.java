package com.roypr.content_calendar;

import com.roypr.content_calendar.config.ContentCalenderProperties;
import com.roypr.content_calendar.model.Content;
import com.roypr.content_calendar.model.Status;
import com.roypr.content_calendar.model.Type;
import com.roypr.content_calendar.repository.ContentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.Arrays;

//@EnableConfigurationProperties is a Spring Boot annotation used to activate one or more
// @ConfigurationProperties classes so that Spring Boot will bind external configuration
// (from application.properties, application.yml, env vars, etc.) to them
@EnableConfigurationProperties(ContentCalenderProperties.class)
@SpringBootApplication
public class ContentCalendarApplication {

	public static void main(String[] args) {

		SpringApplication.run(ContentCalendarApplication.class, args);
	}

	//Use @bean to mark a method to create a Spring managed bean. Here, that bean is CommandLineRunner
	//and its instantiation is done using this method marked with @Bean
	//As it is a CommandlineRunner(FI), it is run/created after dependency injection is done as the app starts up
	@Bean
	CommandLineRunner commandLineRunner(ContentRepository repository){ //contentRepository param is autowired
		//Bootstrapping code: insert some data into the db
		return args -> {
			Content c  = new Content(null,
					"My first blogpost",
					"Bootstrapped row using CommandLineRunner and @Bean",
					Status.IDEA,
					Type.ARTICLE,
					LocalDateTime.now(),
					null,
					""
			);
			repository.save(c);

		}; //lambda function to implement CommandLineRunner FI
	}
}
