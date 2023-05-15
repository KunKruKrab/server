package KunKruKrab.schedule.service;

import KunKruKrab.schedule.dto.Schedule.ScheduleRequest;
import KunKruKrab.schedule.dto.Schedule.ScheduleResponse;
import KunKruKrab.schedule.model.Course;
import KunKruKrab.schedule.model.Schedule;
import KunKruKrab.schedule.repository.CourseRepository;
import KunKruKrab.schedule.repository.ScheduleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ScheduleResponse getScheduleByCourseID(UUID courseID) {
        try {
            Course course = courseRepository.findById(courseID).orElse(null);
            if (course == null) {
                throw new InternalError("Invalid course id");
            }

            List<Schedule> schedules = scheduleRepository.findAll();

            System.out.println("Schedule no filter: " + schedules);

            schedules.removeIf(s -> !(s.getCourseID().equals(courseID)));

            System.out.println("Schedule: " + schedules);

            ScheduleResponse response = new ScheduleResponse();
            response.setCourse(course);
            response.setScheduleList(schedules);

            return response;
        } catch(DataAccessException ex) {
            throw new InternalError("Error retrieving schedule by course ID: " + ex.getMessage(), ex);
        }
    }

    public void create(Schedule schedule) {
        scheduleRepository.save(schedule);
    }
}
