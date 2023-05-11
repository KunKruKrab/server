package KunKruKrab.schedule.repository;

import java.util.UUID;
import KunKruKrab.schedule.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>{
    
    // SELECT * FROM Member WHERE username = ‘username in parameter’
    User findByUserEmail(String email);  
}
