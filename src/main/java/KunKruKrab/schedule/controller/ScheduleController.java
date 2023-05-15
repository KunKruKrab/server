package KunKruKrab.schedule.controller;

import KunKruKrab.schedule.dto.Schedule.ScheduleResponse;
import KunKruKrab.schedule.model.Schedule;
import KunKruKrab.schedule.model.User;
import KunKruKrab.schedule.repository.ScheduleRepository;
import KunKruKrab.schedule.service.RegistrationService;
import KunKruKrab.schedule.service.ScheduleService;
import KunKruKrab.schedule.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
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

    @Autowired
    private UserService userService;

    @GetMapping
    public List<ScheduleResponse> getSchedule(Principal principal) {
        String email = principal.getName();
        User user = userService.getUserByEmail(email);

        List<UUID> courseList = registrationService.getCourseIDByUserID(user.getId());
        List<ScheduleResponse> scheduleList = new ArrayList<>();
        for (UUID courseID : courseList) {
            ScheduleResponse schedule = scheduleService.getScheduleByCourseID(courseID);
            scheduleList.add(schedule);
        }

        return scheduleList;
    }
}
