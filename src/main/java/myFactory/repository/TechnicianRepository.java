package myFactory.repository;

import myFactory.model.entities.Technician;
import myFactory.service.ApplicationUserDetailsService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TechnicianRepository extends JpaRepository<Technician, UUID> {

    //Optional<Technician> findByWorkerIdentityNickname(String workerIdentity);
    Optional<Technician> findTechnicianByWorkerIdentityNickname(String workerIdentity);
}
