package com.udacity.jdnd.course3.critter.pet;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents the form that pet request and response data takes. Does not map
 * to the database directly.
 */
@Getter @Setter @NoArgsConstructor
public class PetDTO {
    private long id;
    private PetType type;
    private String name;
    private long ownerId;
    private LocalDate birthDate;
    private String notes;

    public static Pet convertDTOToEntity(PetDTO petDTO) {
        Pet pet = new Pet();
        BeanUtils.copyProperties(petDTO, pet);
        return pet;
    }

    public static PetDTO convertEntityToDTO(Pet pet) {
        PetDTO petDTO = new PetDTO();
        BeanUtils.copyProperties(pet, petDTO);
        petDTO.setOwnerId(pet.getCustomer().getId());
        return petDTO;
    }

    public static List<PetDTO> convertListEntityToListDTO(Collection<Pet> pets) {
        return pets.stream().map(PetDTO::convertEntityToDTO).collect(Collectors.toList());
    }
}
