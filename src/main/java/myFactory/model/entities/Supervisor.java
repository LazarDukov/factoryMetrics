package myFactory.model.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "supervisors")
public class Supervisor extends Worker {
    @OneToMany
    private List<Task> tasks;

    public Supervisor() {
        super();

    }

    public List<Task> getTasks() {
        return tasks;
    }

    public Supervisor setTasks(List<Task> tasks) {
        this.tasks = tasks;
        return this;
    }
}
