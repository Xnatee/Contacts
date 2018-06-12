package DAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
	
	private static final Logger log = LoggerFactory.getLogger(Application.class);


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
	public CommandLineRunner demo(ContactRepository repository) {
		return (args) -> {
			// save a couple of customers
			/*
			repository.save(new Contact("Jack", "Bauer", "0000000000"));
			repository.save(new Contact("Chloe", "O'Brian", "0000000000"));
			repository.save(new Contact("Kim", "Bauer", "0000000000"));
			repository.save(new Contact("David", "Palmer", "0000000000"));
			repository.save(new Contact("Michelle", "Dessler", "0000000000"));
*/
			// fetch all customers
			log.info("Contacts found with findAll():");
			log.info("-------------------------------");
			for (Contact contact : repository.findAll()) {
				log.info(contact.toString());
			}
			log.info("");

			// fetch an individual customer by ID
			repository.findById(1L)
				.ifPresent(contact -> {
					log.info("Contact found with findById(1L):");
					log.info("--------------------------------");
					log.info(contact.toString());
					log.info("");
				});

			// fetch contact by last name
			log.info("Contact found with findByLastName('Bauer'):");
			log.info("--------------------------------------------");
			repository.findByLastName("Bauer").forEach(bauer -> {
				log.info(bauer.toString());
			});

			log.info("");
		};
    }
}