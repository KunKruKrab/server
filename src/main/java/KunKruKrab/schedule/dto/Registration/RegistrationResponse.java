package KunKruKrab.schedule.dto.Registration;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class RegistrationResponse {
    private UUID courseID;
    private UUID userID;
    private Instant createdAt;
}
