package myFactory.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "warehousers")
public class Warehouser extends Worker {
    @ManyToMany
    private List<Part> partsOrdered;

    public Warehouser() {
        super();
    }

    public List<Part> getPartsOrdered() {
        return partsOrdered;
    }

    public Warehouser setPartsOrdered(List<Part> partsOrdered) {
        this.partsOrdered = partsOrdered;
        return this;
    }
}
