package com.example.customerservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Collection;
import java.util.stream.Stream;

@SpringBootApplication
public class CustomerServiceApplication {

    @Bean
    ApplicationRunner init(CustomerRepository customerRepository){
        return args -> {
            Stream.of("a", "b", "c")
                    .forEach(n -> customerRepository.save(new Customer(null,n,n)));

            customerRepository.findAll().forEach(System.out::println);


        };

    }

    public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}
}




@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
class Customer {
	@Id
	@GeneratedValue
	private Long id;

	private String firstName, lastName;
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

