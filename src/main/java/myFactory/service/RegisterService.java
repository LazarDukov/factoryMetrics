package myFactory.service;

import myFactory.model.dtos.ColleagueRegistrationDTO;
import myFactory.model.entities.Supervisor;
import myFactory.model.entities.Technician;
import myFactory.model.entities.Warehouser;
import myFactory.model.enums.WorkerRoleEnum;
import myFactory.repository.SupervisorRepository;
import myFactory.repository.TechnicianRepository;
import myFactory.repository.WarehouserRepository;
import myFactory.repository.WorkerRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RegisterService {
    private WorkerRoleRepository workerRoleRepository;
    private TechnicianRepository technicianRepository;

    private SupervisorRepository supervisorRepository;

    private PasswordEncoder passwordEncoder;
    private WarehouserRepository warehouserRepository;

    @Autowired
    public RegisterService(WorkerRoleRepository workerRoleRepository, TechnicianRepository technicianRepository, SupervisorRepository supervisorRepository, PasswordEncoder passwordEncoder, WarehouserRepository warehouserRepository) {
        this.workerRoleRepository = workerRoleRepository;
        this.technicianRepository = technicianRepository;
        this.supervisorRepository = supervisorRepository;
        this.passwordEncoder = passwordEncoder;
        this.warehouserRepository = warehouserRepository;
    }

    public void RegisterNewColleague(ColleagueRegistrationDTO colleagueRegistrationDTO) {
        if (colleagueRegistrationDTO.getRole().equals("Technician")) {
            registerNewTechnician(colleagueRegistrationDTO);
        }
        if (colleagueRegistrationDTO.getRole().equals("Supervisor")) {
            registerNewSupervisor(colleagueRegistrationDTO);
        }
        if (colleagueRegistrationDTO.getRole().equals("Warehouser")) {
            registerNewWarehouser(colleagueRegistrationDTO);
        }
    }

    private void registerNewWarehouser(ColleagueRegistrationDTO colleagueRegistrationDTO) {
        Warehouser newWarehouser = new Warehouser();
        newWarehouser.setFirstName(colleagueRegistrationDTO.getFirstName());
        newWarehouser.setLastName(colleagueRegistrationDTO.getLastName());
        newWarehouser.setAge(colleagueRegistrationDTO.getAge());
        newWarehouser.setPassword(passwordEncoder.encode(colleagueRegistrationDTO.getPassword()));
        newWarehouser.setRole(new ArrayList<>());
        newWarehouser.getRole().add(workerRoleRepository.findWorkerRoleByRole(WorkerRoleEnum.TECHNICIAN));
        newWarehouser.setPartsOrdered(new ArrayList<>());
        warehouserRepository.save(newWarehouser);
    }

    private void registerNewTechnician(ColleagueRegistrationDTO colleagueRegistrationDTO) {
        Technician newTechnician = new Technician();
        newTechnician.setFirstName(colleagueRegistrationDTO.getFirstName());
        newTechnician.setLastName(colleagueRegistrationDTO.getLastName());
        newTechnician.setAge(colleagueRegistrationDTO.getAge());
        newTechnician.setPassword(passwordEncoder.encode(colleagueRegistrationDTO.getPassword()));
        newTechnician.setRole(new ArrayList<>());
        newTechnician.getRole().add(workerRoleRepository.findWorkerRoleByRole(WorkerRoleEnum.TECHNICIAN));
        newTechnician.setTasks(new ArrayList<>());
        technicianRepository.save(newTechnician);

    }

    private void registerNewSupervisor(ColleagueRegistrationDTO colleagueRegistrationDTO) {
        Supervisor newSupervisor = new Supervisor();
        newSupervisor.setFirstName(colleagueRegistrationDTO.getFirstName());
        newSupervisor.setLastName(colleagueRegistrationDTO.getLastName());
        newSupervisor.setAge(newSupervisor.getAge());
        newSupervisor.setPassword(passwordEncoder.encode(colleagueRegistrationDTO.getPassword()));
        newSupervisor.setRole(new ArrayList<>());
        newSupervisor.getRole().add(workerRoleRepository.findWorkerRoleByRole(WorkerRoleEnum.WAREHOUSE_WORKER));
        newSupervisor.setTasks(new ArrayList<>());
        supervisorRepository.save(newSupervisor);
    }

}
