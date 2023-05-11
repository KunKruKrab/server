package KunKruKrab.schedule.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CourseRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String description;
}
