package KunKruKrab.schedule.repository;

import KunKruKrab.schedule.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, UUID> {
    List<Registration> findByCourseID(UUID courseID);
}
