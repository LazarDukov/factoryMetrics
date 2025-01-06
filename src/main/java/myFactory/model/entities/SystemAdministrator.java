package myFactory.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "system_administrators")
public class SystemAdministrator extends Worker {
    public SystemAdministrator() {
        super();
    }

}
