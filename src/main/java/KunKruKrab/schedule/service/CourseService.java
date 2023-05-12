package KunKruKrab.schedule.service;

import KunKruKrab.schedule.dto.Course.CourseRequest;
import KunKruKrab.schedule.dto.Course.CourseResponse;
import KunKruKrab.schedule.model.Course;
import KunKruKrab.schedule.repository.CourseRepository;
import KunKruKrab.schedule.util.RandomClassCodeGenerator;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CourseService {
    @Autowired
    private CourseRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public List<CourseResponse> getCourses() {
        List<Course> courses = repository.findAll();

        List<CourseResponse> courseResponses = courses
                .stream()
                .map(restaurant -> modelMapper.map(restaurant,
                        CourseResponse.class))
                .collect(Collectors.toList());

        return courseResponses;
    }

    public UUID getCourseIdByClassCode(String classCode) {
        Course course = repository.findByClassCode(classCode);
        return course.getId();
    }

    public void create(CourseRequest restaurantRequest) {
        Course course = modelMapper.map(restaurantRequest, Course.class);
        course.setClassCode(RandomClassCodeGenerator.generateCode());
        course.setCreatedAt(Instant.now());
        repository.save(course);
    }
}
