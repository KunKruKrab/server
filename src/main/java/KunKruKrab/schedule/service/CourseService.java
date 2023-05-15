package KunKruKrab.schedule.service;

import KunKruKrab.schedule.dto.Course.CourseRequest;
import KunKruKrab.schedule.dto.Course.CourseResponse;
import KunKruKrab.schedule.model.Course;
import KunKruKrab.schedule.model.Registration;
import KunKruKrab.schedule.repository.CourseRepository;
import KunKruKrab.schedule.repository.RegistrationRepository;
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
    RegistrationRepository registrationRepository;

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

    public void create(Course course, UUID userId) {
        repository.save(course);
        Registration registration = new Registration();
        registration.setCourseID(course.getId());
        registration.setUserID(userId);
        registration.setCreatedAt(Instant.now());
        registrationRepository.save(registration);
    }
}
