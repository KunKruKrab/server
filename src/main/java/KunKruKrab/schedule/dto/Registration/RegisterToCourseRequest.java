package KunKruKrab.schedule.dto.Registration;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.util.UUID;

@Data
public class RegisterToCourseRequest {
    @NotBlank
    private String classCode;
    @NotBlank
    private UUID userID;
}
