package com.udacity.jdnd.course3.critter.user.customer;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.udacity.jdnd.course3.critter.pet.Pet;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Represents the form that customer request and response data takes. Does not map
 * to the database directly.
 */
@Getter @Setter @NoArgsConstructor
public class CustomerDTO {
    private long id;
    private String name;
    private String phoneNumber;
    private String notes;
    private List<Long> petIds;

    public static Customer convertDTOToEntity(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        return customer;
    }

    public static CustomerDTO convertEntityToDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDTO);
        Set<Pet> pets = customer.getPets();

        if (pets != null) {
            List<Long> petIds = new ArrayList<>();
            pets.forEach(pet -> petIds.add(pet.getId()));
            customerDTO.setPetIds(petIds);
        }
        return customerDTO;
    }

    public static List<CustomerDTO> convertEntityListToDTOList(List<Customer> customers) {
        return customers.stream().map(customer -> convertEntityToDTO(customer)).collect(Collectors.toList());
    }
}
