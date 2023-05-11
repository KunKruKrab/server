package KunKruKrab.schedule.dto.Registration;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;

@Data
public class RegistrationRequest {
    @NotBlank
    private String courseID;
    private UUID userID;
}
