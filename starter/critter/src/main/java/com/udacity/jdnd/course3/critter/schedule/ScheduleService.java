package com.udacity.jdnd.course3.critter.schedule;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Getter @Setter @NoArgsConstructor
public class ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;
}
