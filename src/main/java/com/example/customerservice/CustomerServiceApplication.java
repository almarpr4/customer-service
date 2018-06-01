package com.example.customerservice;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Stream;

@SpringBootApplication
public class CustomerServiceApplication {

    @Bean
    ApplicationRunner init(CustomerRepository customerRepository){
        return args -> {
            Stream.of("a", "b", "c")
                    .forEach(n -> customerRepository.save(new Customer(null,n,n,n)));

            customerRepository.findAll().forEach(System.out::println);


        };

    }

    public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}
}


interface CustomerRepository extends JpaRepository<Customer, Long>{
	Collection<Customer> findByFirstName(String fn);
}

@RestController
class CustomerRestController {
	private final CustomerRepository customerRepository;

	CustomerRestController(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@GetMapping("/customers")
	Collection<Customer> customers(){
		return this.customerRepository.findAll();
	}
}

