package KunKruKrab.schedule.controller;

import KunKruKrab.schedule.dto.Course.CourseRequest;
import KunKruKrab.schedule.dto.Course.CourseResponse;
import KunKruKrab.schedule.dto.Schedule.ScheduleRequest;
import KunKruKrab.schedule.model.Course;
import KunKruKrab.schedule.model.Schedule;
import KunKruKrab.schedule.service.CourseService;
import KunKruKrab.schedule.service.ScheduleService;
import KunKruKrab.schedule.util.RandomClassCodeGenerator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public List<CourseResponse> getCourses() {
        return courseService.getCourses();
    }

    @PostMapping
    public String addCourse(@RequestBody CourseRequest courseRequest, BindingResult result) {
        if (result.hasErrors()) {
            FieldError fieldError = result.getFieldError();
            assert fieldError != null;
            return String.format("course %s: %s", fieldError.getField(), fieldError.getDefaultMessage());
        }

        Course course = new Course();
        final UUID courseID = UUID.randomUUID();
        course.setId(courseID);
        course.setName(courseRequest.getName());
        course.setDescription(courseRequest.getDescription());
        course.setClassCode(RandomClassCodeGenerator.generateCode());
        course.setCreatedAt(Instant.now());
        courseService.create(course);

        System.out.println("COURSE ID: " + courseID);

        for (ScheduleRequest schedule: courseRequest.getSchedule()) {
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
