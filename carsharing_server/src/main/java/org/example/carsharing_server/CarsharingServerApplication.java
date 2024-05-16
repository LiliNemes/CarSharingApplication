package org.example.carsharing_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@RestController
public class CarsharingServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarsharingServerApplication.class, args);
	}

	/*
	@GetMapping("/hello")
	public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}
	*/




}
