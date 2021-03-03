package org.home.trekOrganizer;

import org.home.trekOrganizer.model.Trek;
import org.home.trekOrganizer.request.JourneyRequest;
import org.home.trekOrganizer.request.TrekRequest;
import org.home.trekOrganizer.request.TrekkerRequest;
import org.home.trekOrganizer.service.JourneyService;
import org.home.trekOrganizer.service.TrekService;
import org.home.trekOrganizer.service.TrekkerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class TrekOrganizerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrekOrganizerApplication.class, args);
	}


	@Bean
	public CommandLineRunner demo(TrekkerService trekkerService, TrekService trekService, JourneyService journeyService){
		return (args)->{
			System.out.println("test");
			TrekkerRequest trekkerRequest1 = new TrekkerRequest("John Black","johnblack@gmail.com", 7);
			TrekkerRequest trekkerRequest2 = new TrekkerRequest("Steve White","stevewhite@gmail.com", 1);
			TrekkerRequest trekkerRequest3 = new TrekkerRequest("Mickael Green","mickaelgreen@gmail.com", 555);

			System.out.println(trekkerService.createTrekker(trekkerRequest1));
			System.out.println(trekkerService.createTrekker(trekkerRequest2));
			System.out.println(trekkerService.createTrekker(trekkerRequest3));

			/****************************************************************************/

			TrekRequest trekRequest1 = new TrekRequest("Trek1",12,"Just simple trek #1");
			TrekRequest trekRequest2 = new TrekRequest("Trek2",22,"Just simple trek #2");
			TrekRequest trekRequest3 = new TrekRequest("Trek3",33,"Just simple trek #3");

			System.out.println(trekService.createTrek(trekRequest1));
			System.out.println(trekService.createTrek(trekRequest2));
			System.out.println(trekService.createTrek(trekRequest3));

			/****************************************************************************/

			JourneyRequest journeyRequest1 = new JourneyRequest("Journey1");
			JourneyRequest journeyRequest2 = new JourneyRequest("Journey2");
			JourneyRequest journeyRequest3 = new JourneyRequest("Journey3");

			System.out.println(journeyService.createJourney(journeyRequest1));
			System.out.println(journeyService.createJourney(journeyRequest2));
			System.out.println(journeyService.createJourney(journeyRequest3));


		};
	}


}
