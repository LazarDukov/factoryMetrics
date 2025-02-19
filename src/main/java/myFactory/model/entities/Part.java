package myFactory.model.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "parts")
public class Part {
    @Id
    @Column
    private long id;
    @Column
    private String name;

    @Column
    private String manufacturer;

    @Column
    private String description;

    @ManyToMany
    private List<Warehouser> purchaser;


    public Part() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Warehouser> getPurchaser() {
        return purchaser;
    }

    public Part setPurchaser(List<Warehouser> purchaser) {
        this.purchaser = purchaser;
        return this;
    }
}
