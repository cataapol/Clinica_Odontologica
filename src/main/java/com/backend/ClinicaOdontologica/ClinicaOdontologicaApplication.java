package com.backend.ClinicaOdontologica;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;

@RestController
@SpringBootApplication
public class ClinicaOdontologicaApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(ClinicaOdontologicaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ClinicaOdontologicaApplication.class, args);
		crearTable();
		LOGGER.info("Clinica Odontologica is now running.. ");

	}


	private static void crearTable(){
		Connection connection = null;
		try {

			Class.forName("org.h2.Driver");
			connection = DriverManager.getConnection("jdbc:h2:~/clinicaH2DataBase;INIT=RUNSCRIPT FROM 'create.sql'", "catalina", "violettatini");


		}catch (Exception e){
			e.printStackTrace();
		}finally {
			try {
				connection.close();
			}catch (Exception ex){
				ex.printStackTrace();
			}
		}
	}

}
