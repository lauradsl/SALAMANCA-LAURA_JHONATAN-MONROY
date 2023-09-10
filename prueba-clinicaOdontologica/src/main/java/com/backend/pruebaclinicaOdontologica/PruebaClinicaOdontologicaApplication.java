package com.backend.pruebaclinicaOdontologica;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.SQLException;

@SpringBootApplication
public class PruebaClinicaOdontologicaApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(PruebaClinicaOdontologicaApplication.class);

	public static void main(String[] args) throws SQLException, ClassNotFoundException
	{
		SpringApplication.run(PruebaClinicaOdontologicaApplication.class, args);
		LOGGER.info("ClinicaOdontologica is now running...");
	}

	@Bean
	public ModelMapper modelMapper()
	{
		return new ModelMapper();
	}

}
