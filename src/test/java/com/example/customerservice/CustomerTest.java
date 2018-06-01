package com.example.customerservice;

import org.assertj.core.api.BDDAssertions;
import org.junit.Before;
import org.junit.Test;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

public class CustomerTest {

    private Validator validator;

    @Before
    public void setUp(){
        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
        localValidatorFactoryBean.afterPropertiesSet();
        validator = localValidatorFactoryBean.getValidator();

    }

    @Test
    public void newInstanceWithValidValuesShouldReturnARecord(){
        Customer customer = new Customer(1L, "first", "last", "email@aamil.com");

        BDDAssertions.then(customer.getId()).isEqualTo(1L);
        BDDAssertions.then(customer.getFirstName()).isEqualTo("first");
        BDDAssertions.then(customer.getLastName()).isEqualTo("last");
        BDDAssertions.then(customer.getEmail()).isEqualTo("email@aamil.com");
        BDDAssertions.then(customer.getId()).isNotNull();

    }

    @Test
    public void newInstanceWithInvalidConstraintsShouldProduceContraintViolations(){
        Customer customer = new Customer(null, null, null, null);
        Set<ConstraintViolation<Customer>> constraintViolations = validator.validate(customer);

        BDDAssertions.then(constraintViolations.size()).isEqualTo(3);




    }
}
