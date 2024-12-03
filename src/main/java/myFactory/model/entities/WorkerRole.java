package myFactory.model.entities;

import jakarta.persistence.*;
import myFactory.model.enums.WorkerRoleEnum;

import java.util.UUID;

@Entity
@Table(name = "worker_role")
public class WorkerRole {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private WorkerRoleEnum role;

    public WorkerRole() {
    }

    public WorkerRoleEnum getRole() {
        return role;
    }

    public WorkerRole setRole(WorkerRoleEnum role) {
        this.role = role;
        return null;
    }
}
