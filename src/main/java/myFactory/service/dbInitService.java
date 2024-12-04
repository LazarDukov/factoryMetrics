package myFactory.service;

import jakarta.annotation.PostConstruct;
import myFactory.model.entities.WorkerRole;
import myFactory.repository.WorkerRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static myFactory.model.enums.WorkerRoleEnum.*;


@Service
public class dbInitService {
    WorkerRoleRepository workerRoleRepository;

    @Autowired
    public dbInitService(WorkerRoleRepository workerRoleRepository) {
        this.workerRoleRepository = workerRoleRepository;
    }

    @PostConstruct
    public void initDb() {
        if (workerRoleRepository.count() == 0) {
            initRoles();
        }
    }
    public void initRoles() {

        WorkerRole systemAdministrator = new WorkerRole().setRole(SYSTEM_ADMINISTRATOR);
        WorkerRole supervisor = new WorkerRole().setRole(SUPERVISOR);
        WorkerRole warehouseWorker = new WorkerRole().setRole(WAREHOUSE_WORKER);
        WorkerRole technician = new WorkerRole().setRole(TECHNICIAN);
        workerRoleRepository.save(systemAdministrator);
        workerRoleRepository.save(supervisor);
        workerRoleRepository.save(warehouseWorker);
        workerRoleRepository.save(technician);


    }

}
