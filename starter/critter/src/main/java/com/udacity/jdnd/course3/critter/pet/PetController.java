package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.user.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private PetService petService;

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        Pet pet = PetDTO.convertDTOToEntity(petDTO);
        pet = petService.savePet(pet, petDTO.getOwnerId());
        return PetDTO.convertEntityToDTO(pet);
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        Pet pet = petService.getPetById(petId);
        return PetDTO.convertEntityToDTO(pet);
    }

    @GetMapping
    public List<PetDTO> getPets(){
        return PetDTO.convertListEntityToListDTO(petService.getAllPets());
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        List<Pet> pets = customerService.getCustomerById(ownerId).getPets();
        return PetDTO.convertListEntityToListDTO(pets);
    }
}
