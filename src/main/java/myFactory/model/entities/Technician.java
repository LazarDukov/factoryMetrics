package myFactory.model.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "technicians")
public class Technician extends Worker {
    public Technician() {
        super();

    }
}
