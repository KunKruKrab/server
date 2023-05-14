package KunKruKrab.schedule.dto.Schedule;

import KunKruKrab.schedule.model.Course;
import KunKruKrab.schedule.model.Schedule;
import lombok.Data;
import java.util.List;

@Data
public class ScheduleResponse {
    private Course course;
    private List<Schedule> scheduleList;
}
