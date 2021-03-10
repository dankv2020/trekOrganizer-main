package org.home.trekOrganizer;

import org.home.trekOrganizer.model.Trek;
import org.home.trekOrganizer.request.JourneyRequest;
import org.home.trekOrganizer.request.ManagerRequest;
import org.home.trekOrganizer.request.TrekRequest;
import org.home.trekOrganizer.request.TrekkerRequest;
import org.home.trekOrganizer.service.JourneyService;
import org.home.trekOrganizer.service.ManagerService;
import org.home.trekOrganizer.service.TrekService;
import org.home.trekOrganizer.service.TrekkerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class TrekOrganizerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrekOrganizerApplication.class, args);
	}


	@Bean
	public CommandLineRunner populateDB(TrekkerService trekkerService, TrekService trekService, JourneyService journeyService, ManagerService managerService){
		return (args)->{
			System.out.println("test");
			TrekkerRequest trekkerRequest1 = new TrekkerRequest("John Black","johnblack@gmail.com", 7);
			TrekkerRequest trekkerRequest2 = new TrekkerRequest("Steve White","stevewhite@gmail.com", 1);
			TrekkerRequest trekkerRequest3 = new TrekkerRequest("Mickael Green","mickaelgreen@gmail.com", 5);
			System.out.println(trekkerService.createTrekker(trekkerRequest1));
			System.out.println(trekkerService.createTrekker(trekkerRequest2));
			System.out.println(trekkerService.createTrekker(trekkerRequest3));

			TrekRequest trekRequest1 = new TrekRequest("Trek1",12,"Just simple trek #1");
			TrekRequest trekRequest2 = new TrekRequest("Trek2",22,"Just simple trek #2");
			TrekRequest trekRequest3 = new TrekRequest("Trek3",33,"Just simple trek #3");

			System.out.println(trekService.createTrek(trekRequest1));
			System.out.println(trekService.createTrek(trekRequest2));
			System.out.println(trekService.createTrek(trekRequest3));

			PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String encodedPassword = passwordEncoder.encode("test");

			ManagerRequest managerRequest1 = new ManagerRequest("Manager1", "test", encodedPassword, "ROLE_USER", true);
			encodedPassword = passwordEncoder.encode("test1");
			ManagerRequest managerRequest2 = new ManagerRequest("Manager2", "test1", encodedPassword, "ROLE_ADMIN", true);
			managerService.createManager(managerRequest1);
			managerService.createManager(managerRequest2);

			System.out.println(passwordEncoder.encode("georgi"));

		};
	}


}
