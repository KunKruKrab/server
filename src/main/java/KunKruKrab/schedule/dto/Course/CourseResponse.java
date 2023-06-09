package KunKruKrab.schedule.dto.Course;

import lombok.Data;
import java.util.UUID;

@Data
public class CourseResponse {
    private UUID id;
    private String classCode;
    private String name;
    private String description;
    private String professor;
}