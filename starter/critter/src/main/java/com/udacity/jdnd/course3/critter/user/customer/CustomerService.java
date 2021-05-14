package com.udacity.jdnd.course3.critter.user.customer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@Getter @Setter @NoArgsConstructor
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

}
