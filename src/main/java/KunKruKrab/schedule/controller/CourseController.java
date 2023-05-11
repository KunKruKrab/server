package KunKruKrab.schedule.controller;

import KunKruKrab.schedule.dto.CourseRequest;
import KunKruKrab.schedule.dto.CourseResponse;
import KunKruKrab.schedule.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping
    public List<CourseResponse> getCourses() {
        return courseService.getCourses();
    }

    @PostMapping
    public String addCourse(@Valid @RequestBody CourseRequest course, BindingResult result) {
        if (result.hasErrors()) {
            FieldError fieldError = result.getFieldError();
            String error = String.format("course %s: %s", fieldError.getField(), fieldError.getDefaultMessage());
            return error;
        }

        courseService.create(course);
        return "Create new course successfully";
    }
}
