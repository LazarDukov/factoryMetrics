package myFactory.model.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "technicians")
public class Technician extends Worker {

    @ManyToMany
    private List<Task> tasks;

    public Technician() {
        super();

    }


    public List<Task> getTasks() {
        return tasks;
    }

    public Technician setTasks(List<Task> tasks) {
        this.tasks = tasks;
        return this;
    }
}
