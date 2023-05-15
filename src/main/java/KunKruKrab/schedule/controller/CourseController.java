package KunKruKrab.schedule.controller;

import KunKruKrab.schedule.dto.Course.CourseRequest;
import KunKruKrab.schedule.dto.Course.CourseResponse;
import KunKruKrab.schedule.dto.Schedule.ScheduleRequest;
import KunKruKrab.schedule.model.Course;
import KunKruKrab.schedule.model.Role;
import KunKruKrab.schedule.model.Schedule;
import KunKruKrab.schedule.model.User;
import KunKruKrab.schedule.service.CourseService;
import KunKruKrab.schedule.service.ScheduleService;
import KunKruKrab.schedule.service.UserService;
import KunKruKrab.schedule.util.RandomClassCodeGenerator;
import KunKruKrab.schedule.util.CheckTimeValid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private UserService userService;

    @GetMapping
    public List<CourseResponse> getCourses() {
        return courseService.getCourses();
    }

    @PostMapping
    public String addCourse(@RequestBody CourseRequest courseRequest, BindingResult result, Principal principal) {
        if (result.hasErrors()) {
            FieldError fieldError = result.getFieldError();
            assert fieldError != null;
            return String.format("course %s: %s", fieldError.getField(), fieldError.getDefaultMessage());
        }

        User user = userService.getUserByEmail(principal.getName());
        if (user.getRole() != Role.TEACHER) {
            return "You do not have permission to do this action";
        }

        Course course = new Course();
        final UUID courseID = UUID.randomUUID();
        course.setId(courseID);
        course.setName(courseRequest.getName());
        course.setDescription(courseRequest.getDescription());
        course.setClassCode(RandomClassCodeGenerator.generateCode());
        course.setProfessor(user.getFullName());
        course.setCreatedAt(Instant.now());
        courseService.create(course, user.getId());

        for (ScheduleRequest schedule: courseRequest.getSchedule()) {
            if (!CheckTimeValid.verify(schedule.getStartTime(), schedule.getEndTime())) {
                return "End time cannot be scheduled before start time";
            }
            Schedule s = new Schedule();
            s.setCourseID(courseID);
            s.setStartTime(schedule.getStartTime());
            s.setEndTime(schedule.getEndTime());
            s.setDay(schedule.getDay());
            scheduleService.create(s);
        }

        return "Create new course successfully";
    }
}
