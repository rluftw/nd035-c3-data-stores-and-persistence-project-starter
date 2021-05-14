package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.user.customer.Customer;
import com.udacity.jdnd.course3.critter.user.customer.CustomerService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@Getter @Setter @NoArgsConstructor
public class PetService {
    @Autowired
    private PetRepository petRepository;

    @Autowired
    private CustomerService customerService;

    public Pet savePet(Pet pet, Long customerId) {
        Customer customer = customerService.getCustomerById(customerId);
        pet.setCustomer(customer);
        pet = petRepository.save(pet);
        customerService.addPet(pet, customerId);
        return pet;
    }

    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }
}
