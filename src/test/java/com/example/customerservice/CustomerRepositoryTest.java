package com.example.customerservice;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest(classes = CustomerServiceApplication.class)
@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerRepositoryTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private CustomerRepository repository;


    @Test
    public void saveShoudlMapCorrectly(){
        Customer customer = new Customer(null, "first", "last", "email@email.com");
        Customer saved = this.testEntityManager.persistFlushFind(customer);

        then(saved.getId()).isNotNull();
        then(saved.getFirstName()).isNotBlank();
        then(saved.getFirstName()).isEqualToIgnoringCase("first");
        then(saved.getLastName()).isEqualToIgnoringCase("last");
        then(saved.getEmail()).isEqualToIgnoringCase("email@email.com");
    }

    @Test
    public void repositoryShouldMapCorrectly(){
        Customer customer = new Customer(null, "first", "last", "email@email.com");
        Customer saved = this.repository.save(customer);

        then(saved.getId()).isNotNull();
        then(saved.getFirstName()).isNotBlank();
        then(saved.getFirstName()).isEqualToIgnoringCase("first");
        then(saved.getLastName()).isEqualToIgnoringCase("last");
        then(saved.getEmail()).isEqualToIgnoringCase("email@email.com");

    }

    @Test
    @Ignore
    public void newInstanceWithInvalidParametersShouldResultInConstraintViolations() throws Exception{
        this.expectedException.expect(ConstraintViolationException.class);

        Customer customer = new Customer(null, null, null, null);


        Customer saved = this.repository.save(customer);
        //then(saved.getFirstName()).isNotBlank();
    }

}
