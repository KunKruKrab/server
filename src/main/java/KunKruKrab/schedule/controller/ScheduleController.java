package KunKruKrab.schedule.controller;

import KunKruKrab.schedule.dto.Schedule.ScheduleResponse;
import KunKruKrab.schedule.model.Schedule;
import KunKruKrab.schedule.repository.ScheduleRepository;
import KunKruKrab.schedule.service.RegistrationService;
import KunKruKrab.schedule.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private RegistrationService registrationService;

    @GetMapping
    public List<ScheduleResponse> getSchedule() {
        UUID userId = UUID.fromString("7b612ab3-d388-458c-9b17-7ec2496d0000");
        List<UUID> courseList = registrationService.getCourseIDByUserID(userId);
        List<ScheduleResponse> scheduleList = new ArrayList<>();
        for (UUID courseID : courseList) {
            ScheduleResponse schedule = scheduleService.getScheduleByCourseID(courseID);
            scheduleList.add(schedule);
        }

        return scheduleList;
    }
}
