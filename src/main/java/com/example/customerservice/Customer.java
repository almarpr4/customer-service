package com.example.customerservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
class Customer {
	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	private String firstName, lastName;

	@org.hibernate.validator.constraints.Email
	@NotNull
	private String email;
}
