package myFactory.repository;

import myFactory.model.entities.Technician;
import myFactory.model.entities.WorkerRole;
import myFactory.model.enums.WorkerRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WorkerRoleRepository extends JpaRepository<WorkerRole, UUID> {
    WorkerRole findWorkerRoleByRole(WorkerRoleEnum role);
}
