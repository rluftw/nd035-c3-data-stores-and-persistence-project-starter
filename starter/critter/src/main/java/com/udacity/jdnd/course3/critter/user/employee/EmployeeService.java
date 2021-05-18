package com.udacity.jdnd.course3.critter.user.employee;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@Getter @Setter @NoArgsConstructor
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee getEmployeeById(Long employeeId) {
        return employeeRepository
                .findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Unable to find customer with id " + employeeId));
    }

    public List<Employee> findEmployeeForService(Set<EmployeeSkill> requiredSkills, Set<DayOfWeek> dayOfWeeks) {
        List<Employee> employees = employeeRepository.findBySkillsInAndDaysAvailableIn(requiredSkills, dayOfWeeks);
        List<Employee> resultEmployees = new ArrayList<>();
        employees.stream().forEach(employee -> {
            if(!resultEmployees.contains(employee) && employee.getSkills().containsAll(requiredSkills)) {
                resultEmployees.add(employee);
            }
        });
        return resultEmployees;
    }
}
