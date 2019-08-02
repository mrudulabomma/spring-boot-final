package com.stackroute.muzixapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class TrackServiceApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(TrackServiceApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(TrackServiceApplication.class, args);
	}
}
