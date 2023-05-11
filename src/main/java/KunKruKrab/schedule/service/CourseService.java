package KunKruKrab.schedule.service;

import KunKruKrab.schedule.dto.CourseRequest;
import KunKruKrab.schedule.model.Course;
import KunKruKrab.schedule.repository.CourseRepository;
import KunKruKrab.schedule.util.RandomClassCodeGenerator;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.Instant;

@Service
public class CourseService {
    @Autowired
    private CourseRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public void create(CourseRequest restaurantRequest) {
        Course course = modelMapper.map(restaurantRequest, Course.class);
        course.setClassCode(RandomClassCodeGenerator.generateCode());
        course.setCreatedAt(Instant.now());
        repository.save(course);
    }
}
