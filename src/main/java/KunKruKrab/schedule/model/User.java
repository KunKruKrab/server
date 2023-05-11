package KunKruKrab.schedule.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import java.util.UUID;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue
    private UUID id;
    
    private String email;
    private String password;
    private String name;
    private String role;
}
