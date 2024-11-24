package myFactory.model.entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@MappedSuperclass
public abstract class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private String name;

    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private int age;

    @Column
    private String password;

    @OneToOne
    private WorkerRole role;

}
