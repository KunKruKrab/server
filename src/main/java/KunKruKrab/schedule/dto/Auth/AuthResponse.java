package KunKruKrab.schedule.dto.Auth;

import KunKruKrab.schedule.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private String token;
    private String fullName;
    private String email;
    private Role role;
}
