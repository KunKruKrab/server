package KunKruKrab.schedule.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;


@Data
@Entity
public class Registration {

    @Id
    @GeneratedValue
    private UUID id;
    private UUID courseID;
    private UUID userID;
    private Instant createdAt;
}
