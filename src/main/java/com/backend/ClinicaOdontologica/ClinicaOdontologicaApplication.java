package com.backend.ClinicaOdontologica;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;

@RestController
@SpringBootApplication
public class ClinicaOdontologicaApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(ClinicaOdontologicaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ClinicaOdontologicaApplication.class, args);
		LOGGER.info("Clinica Odontologica is now running.. ");

	}


    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }



}
