package KunKruKrab.schedule.service;

import KunKruKrab.schedule.dto.Registration.RegisterToCourseRequest;
import KunKruKrab.schedule.dto.Registration.RegistrationRequest;
import KunKruKrab.schedule.dto.Registration.RegistrationResponse;
import KunKruKrab.schedule.model.Registration;
import KunKruKrab.schedule.service.CourseService;
import KunKruKrab.schedule.repository.RegistrationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RegistrationService {

    @Autowired
    private RegistrationRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CourseService courseService;

    public List<Registration> getAll() {
        return repository.findAll();
    }

    public List<RegistrationResponse> getRegistrationByCourseID(UUID courseID) {
        if (courseID == null) {
            throw new IllegalArgumentException("Course ID cannot be null");
        }

        try {
            List<Registration> registrations = repository.findAll();

            registrations.removeIf(r -> !(r.getId().equals(courseID)));

            List<RegistrationResponse> dtos = registrations
                    .stream()
                    .map(registration -> modelMapper.map(registration, RegistrationResponse.class))
                    .collect(Collectors.toList());

            return dtos;
        } catch (DataAccessException ex) {
            throw new RuntimeException("Error retrieving registrations by course ID: " + ex.getMessage(), ex);
        }
    }

    public List<UUID> getCourseIDByUserID(UUID userID) {
        if (userID == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }

        try {
            List<Registration> registrations = repository.findAll();

            registrations.removeIf(r -> !(r.getUserID().equals(userID)));

            List<UUID> dtos = registrations
                    .stream()
                    .map(registration -> registration.getCourseID())
                    .collect(Collectors.toList());

            return dtos;
        } catch (DataAccessException ex) {
            throw new RuntimeException("Error retrieving registrations by course ID: " + ex.getMessage(), ex);
        }
    }

    public void registerToCourse(RegisterToCourseRequest registrationRequest) {
        UUID courseID = courseService.getCourseIdByClassCode(registrationRequest.getClassCode());
        Registration course = modelMapper.map(registrationRequest, Registration.class);
        course.setCourseID(courseID);
        course.setCreatedAt(Instant.now());
        repository.save(course);
    }
}
