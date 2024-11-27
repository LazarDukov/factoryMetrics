package myFactory.model.entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @Column
    private long id;

    @Column
    private String description;

    @Column
    private Date beginDate;
    @Column
    private Date endDate;

    @Column
    private boolean isFinished = false;

    @ManyToOne
    private Supervisor creator;

    @ManyToMany
    private List<Technician> technicianList;

    public Task() {
        super();
    }

    public long getId() {
        return id;
    }

    public Task setId(long id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Task setDescription(String description) {
        this.description = description;
        return this;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public Task setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
        return this;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Task setEndDate(Date endDate) {
        this.endDate = endDate;
        return this;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public Task setFinished(boolean finished) {
        isFinished = finished;
        return this;
    }

    public Supervisor getCreator() {
        return creator;
    }

    public Task setCreator(Supervisor creator) {
        this.creator = creator;
        return this;
    }

    public List<Technician> getTechnicianList() {
        return technicianList;
    }

    public Task setTechnicianList(List<Technician> technicianList) {
        this.technicianList = technicianList;
        return this;
    }
}
