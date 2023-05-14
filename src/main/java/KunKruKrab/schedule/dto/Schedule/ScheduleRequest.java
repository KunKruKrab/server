package KunKruKrab.schedule.dto.Schedule;

import KunKruKrab.schedule.model.Day;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ScheduleRequest {
    @NotBlank
    private String startTime;
    @NotBlank
    private String endTime;
    @NotBlank
    private Day day;
}
