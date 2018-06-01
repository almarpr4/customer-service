package com.example.customerservice;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

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

	@GetMapping(path = "/customers/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Customer customerByid(@PathVariable Long id){
		return this.customerRepository.findById(id).orElse(new Customer());

	}
}
