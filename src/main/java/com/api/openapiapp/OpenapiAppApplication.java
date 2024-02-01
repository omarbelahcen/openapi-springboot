package com.api.openapiapp;

import com.api.openapiapp.entities.Customer;
import com.api.openapiapp.entities.Product;
import com.api.openapiapp.repository.CustomerRepository;
import com.api.openapiapp.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class OpenapiAppApplication implements CommandLineRunner {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	CustomerRepository customerRepository;

	public static void main(String[] args) {
		SpringApplication.run(OpenapiAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Product p = productRepository.save(new Product(12L, "Azerty", 44));
		log.info("### Saved user {} ###", p.getName());

		customerRepository.save(Customer.builder().name("Omar").email("omar@gmail.com").build());
		customerRepository.save(Customer.builder().name("Ali").email("ali@gmail.com").build());
		customerRepository.save(Customer.builder().name("Badre").email("badre@gmail.com").build());
	}
}
