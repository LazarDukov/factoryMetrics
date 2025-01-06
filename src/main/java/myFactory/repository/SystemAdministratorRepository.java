package myFactory.repository;

import myFactory.model.entities.SystemAdministrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SystemAdministratorRepository extends JpaRepository<SystemAdministrator, UUID> {
    Optional<SystemAdministrator> findSystemAdministratorByWorkerIdentityNickname(String workerIdentity);
}
