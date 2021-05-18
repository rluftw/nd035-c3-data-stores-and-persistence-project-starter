package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetService;
import com.udacity.jdnd.course3.critter.user.customer.Customer;
import com.udacity.jdnd.course3.critter.user.customer.CustomerService;
import com.udacity.jdnd.course3.critter.user.employee.Employee;
import com.udacity.jdnd.course3.critter.user.employee.EmployeeService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Getter @Setter @NoArgsConstructor
public class ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private PetService petService;

    @Autowired
    private CustomerService customerService;

    public Schedule saveSchedule(Schedule schedule, List<Long> employeeIds, List<Long> petIds) {
        schedule.setEmployees(employeeIds
                .stream()
                .map(employeeId -> employeeService.getEmployeeById(employeeId))
                .collect(Collectors.toList())
        );

        schedule.setPets(petIds
                .stream()
                .map(petId -> petService.getPetById(petId))
                .collect(Collectors.toList())
        );

        return scheduleRepository.save(schedule);
    }

    public List<Schedule> getSchedulesForEmployee(Long employeeId) {
        Employee employee = employeeService.getEmployeeById(employeeId);
        return scheduleRepository.findSchedulesByEmployeesIn(Arrays.asList(employee));
    }

    public List<Schedule> getScheduleForPet(Long petId) {
        Pet pet = petService.getPetById(petId);
        return scheduleRepository.findSchedulesByPetsIn(Arrays.asList(pet));
    }

    public List<Schedule> getSchedulesForCustomer(Long customerId) {
        Customer customer = customerService.getCustomerById(customerId);
        return scheduleRepository.findSchedulesByPetsIn(customer.getPets());
    }

    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }
}
