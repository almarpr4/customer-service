package com.example.customerservice;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

interface CustomerRepository extends JpaRepository<Customer, Long> {
	Collection<Customer> findByFirstName(String fn);
}
