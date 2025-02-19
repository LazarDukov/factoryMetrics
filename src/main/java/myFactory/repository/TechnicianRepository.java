package myFactory.repository;

import myFactory.model.entities.Technician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TechnicianRepository extends JpaRepository<Technician, UUID> {
    Technician findByWorkerIdentityNickname(String workerIdentity);

    Optional<Technician> getByWorkerIdentityNickname(String workerIdentity);

}
