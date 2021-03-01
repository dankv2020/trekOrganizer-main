package org.home.trekOrganizer;

import org.home.trekOrganizer.request.TrekkerRequest;
import org.home.trekOrganizer.service.TrekkerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TrekOrganizerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrekOrganizerApplication.class, args);
	}


	@Bean
	public CommandLineRunner demo(TrekkerService trekkerService){
		return (args)->{
			System.out.println("test");
			TrekkerRequest trekkerRequest1 = new TrekkerRequest("John Black","johnblack@gmail.com", 7);
			TrekkerRequest trekkerRequest2 = new TrekkerRequest("Steve White","stevewhite@gmail.com", 1);
			TrekkerRequest trekkerRequest3 = new TrekkerRequest("Mickael Green","mickaelgreen@gmail.com", 555);

			System.out.println(trekkerService.createTrekker(trekkerRequest1));
			System.out.println(trekkerService.createTrekker(trekkerRequest2));
			System.out.println(trekkerService.createTrekker(trekkerRequest3));

		};
	}


}
