package KunKruKrab.schedule.dto.Course;

import KunKruKrab.schedule.dto.Schedule.ScheduleRequest;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.util.List;

@Data
public class CourseRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotBlank
    private List<ScheduleRequest> schedule;
}
