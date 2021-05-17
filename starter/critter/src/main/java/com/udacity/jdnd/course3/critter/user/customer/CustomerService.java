package com.udacity.jdnd.course3.critter.user.customer;

import com.udacity.jdnd.course3.critter.pet.Pet;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
@Getter @Setter @NoArgsConstructor
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public void savePet(Pet pet, Long customerId) {
        Customer customer = getCustomerById(customerId);
        if (customer.getPets() == null) {
            customer.setPets(new HashSet<Pet>());
        }
        customer.getPets().add(pet);
    }

    public Customer getCustomerById(Long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Unable to find customer with id " + customerId));
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
}
