package KunKruKrab.schedule.model;

import KunKruKrab.schedule.util.RandomClassCodeGenerator;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
public class Course {

    @Id
    private UUID id;
    private String classCode;
    private String name;
    private String description;
    private String professor;
    private Instant createdAt;
}