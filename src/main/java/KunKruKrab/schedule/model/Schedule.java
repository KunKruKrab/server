package KunKruKrab.schedule.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class Schedule {

//    public Schedule(UUID courseID, String startTime, String endTime, Day day) {
//        this.courseID = courseID;
//        this.startTime = startTime;
//        this.endTime = endTime;
//        this.day = day;
//    }

    @Id
    @GeneratedValue
    private UUID id;
    private UUID courseID;
    private String startTime;
    private String endTime;
    private Day day;
}
