package myFactory.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "supervisors")
public class Supervisor extends Worker {

    @OneToMany
    private List<Task> createdTasks;

    public Supervisor() {
        super();

    }

    public List<Task> getCreatedTasks() {
        return createdTasks;
    }

    public Supervisor setCreatedTasks(List<Task> createdTasks) {
        this.createdTasks = createdTasks;
        return this;
    }
}
