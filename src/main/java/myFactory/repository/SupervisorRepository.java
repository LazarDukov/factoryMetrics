package myFactory.repository;

import myFactory.model.entities.Supervisor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SupervisorRepository extends JpaRepository<Supervisor, UUID> {
    Optional<Supervisor> getByWorkerIdentityNickname(String workerIdentity);
}
