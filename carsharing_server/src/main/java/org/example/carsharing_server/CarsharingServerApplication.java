package org.example.carsharing_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableScheduling
@EnableTransactionManagement
public class CarsharingServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarsharingServerApplication.class, args);
	}
}
