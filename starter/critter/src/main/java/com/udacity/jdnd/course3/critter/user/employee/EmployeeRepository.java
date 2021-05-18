package com.udacity.jdnd.course3.critter.user.employee;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findBySkillsInAndDaysAvailableIn(Set<EmployeeSkill> skills, Set<DayOfWeek> dayOfWeeks);
}
