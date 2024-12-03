package myFactory.service;

import myFactory.model.entities.WorkerRole;
import myFactory.repository.WorkerRoleRepository;
import org.springframework.stereotype.Service;

import static myFactory.model.enums.WorkerRoleEnum.TECHNICIAN;
import static myFactory.model.enums.WorkerRoleEnum.WAREHOUSE_WORKER;

@Service
public class dbInitService {
    private WorkerRoleRepository workerRoleRepository;

    public dbInitService(WorkerRoleRepository workerRoleRepository) {
        this.workerRoleRepository = workerRoleRepository;
    }


    public void initRoles() {
        if (workerRoleRepository.count() == 0) {
            WorkerRole supervisor = new WorkerRole().setRole(TECHNICIAN);
            WorkerRole warehouseWorker = new WorkerRole().setRole(WAREHOUSE_WORKER);
            WorkerRole technician = new WorkerRole().setRole(TECHNICIAN);
            workerRoleRepository.save(supervisor);
            workerRoleRepository.save(warehouseWorker);
            workerRoleRepository.save(technician);
        }
    }

}
