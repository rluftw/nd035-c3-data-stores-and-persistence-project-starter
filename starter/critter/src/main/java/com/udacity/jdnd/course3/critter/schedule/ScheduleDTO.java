package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.user.employee.EmployeeSkill;
import com.udacity.jdnd.course3.critter.user.employee.Employee;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Represents the form that schedule request and response data takes. Does not map
 * to the database directly.
 */
@Getter @Setter @NoArgsConstructor
public class ScheduleDTO {
    private long id;
    private List<Long> employeeIds;
    private List<Long> petIds;
    private LocalDate date;
    private Set<EmployeeSkill> activities;

    public static Schedule convertDTOToEntity(ScheduleDTO scheduleDTO) {
        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(scheduleDTO, schedule);
        return schedule;
    }

    public static ScheduleDTO convertEntityToDTO(Schedule schedule) {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        BeanUtils.copyProperties(schedule, scheduleDTO);

        List<Employee> employees = schedule.getEmployees();
        if (employees != null) {
            scheduleDTO.setEmployeeIds(employees
                    .stream()
                    .map(employee -> employee.getId())
                    .collect(Collectors.toList()));
        }

        List<Pet> pets = schedule.getPets();
        if (pets != null) {
            scheduleDTO.setPetIds(pets
                    .stream()
                    .map(pet -> pet.getId())
                    .collect(Collectors.toList()));
        }

        return scheduleDTO;
    }

    public static List<ScheduleDTO> convertListEntityToListDTO(List<Schedule> schedules) {
        return schedules.stream().map(ScheduleDTO::convertEntityToDTO).collect(Collectors.toList());
    }
}
