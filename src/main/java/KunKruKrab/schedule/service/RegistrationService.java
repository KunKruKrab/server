package KunKruKrab.schedule.service;

import KunKruKrab.schedule.dto.Registration.RegistrationRequest;
import KunKruKrab.schedule.dto.Registration.RegistrationResponse;
import KunKruKrab.schedule.model.Registration;
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

    public List<Registration> getAll() {
        return repository.findAll();
    }

    public List<RegistrationResponse> getRegistrationByCourseID(UUID courseID) {
        if (courseID == null) {
            throw new IllegalArgumentException("Course ID cannot be null");
        }

        try {
            List<Registration> registrations = repository.findByCourseID(courseID);

            List<RegistrationResponse> dtos = registrations
                    .stream()
                    .map(registration -> modelMapper.map(registration, RegistrationResponse.class))
                    .collect(Collectors.toList());

            return dtos;
        } catch (DataAccessException ex) {
            throw new RuntimeException("Error retrieving registrations by course ID: " + ex.getMessage(), ex);
        }
    }

    public void registerToCourse(RegistrationRequest registrationRequest) {
        Registration course = modelMapper.map(registrationRequest, Registration.class);
//        course.setCourseID(getCourseIDByClassCode(registrationRequest.getClassCode()));
        course.setCourseID("qwerty");
        course.setCreatedAt(Instant.now());
        repository.save(course);
    }
}
