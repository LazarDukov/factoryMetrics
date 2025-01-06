package myFactory.service;

import jakarta.annotation.PostConstruct;
import myFactory.model.entities.SystemAdministrator;
import myFactory.model.entities.WorkerRole;
import myFactory.model.enums.WorkerRoleEnum;
import myFactory.repository.SystemAdministratorRepository;
import myFactory.repository.WorkerRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import static myFactory.model.enums.WorkerRoleEnum.*;


@Service
public class dbInitService {
    private final WorkerRoleRepository workerRoleRepository;
    private final SystemAdministratorRepository systemAdministratorRepository;

    @Autowired
    public dbInitService(WorkerRoleRepository workerRoleRepository, SystemAdministratorRepository systemAdministratorRepository) {
        this.workerRoleRepository = workerRoleRepository;
        this.systemAdministratorRepository = systemAdministratorRepository;
    }

    @PostConstruct
    public void initDb() {
        if (workerRoleRepository.count() == 0) {
            initRoles();
        }
        if (systemAdministratorRepository.count() == 0) {
            initSystemAdministrators();
        }
    }

    private void initSystemAdministrators() {
        SystemAdministrator systemAdministrator = new SystemAdministrator();
        systemAdministrator.setWorkerIdentityNickname("Fi1Ad");
        systemAdministrator.setFirstName("First");
        systemAdministrator.setLastName("Administrator");
        systemAdministrator.setAge(0);
        systemAdministrator.setEmail("FirstAdministrator@FactoryMetrics.eu");
        systemAdministrator.setPassword("1111");
        systemAdministrator.setRole(new ArrayList<>());
        systemAdministrator.getRole().add(workerRoleRepository.findWorkerRoleByRole(SYSTEM_ADMINISTRATOR));
        systemAdministratorRepository.save(systemAdministrator);
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
