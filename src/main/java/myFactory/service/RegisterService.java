package myFactory.service;

import jakarta.validation.Valid;
import myFactory.model.dtos.ColleagueRegistrationDTO;
import myFactory.model.entities.Technician;
import myFactory.model.enums.WorkerRoleEnum;
import myFactory.repository.TechnicianRepository;
import myFactory.repository.WorkerRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class RegisterService {
    private WorkerRoleRepository workerRoleRepository;
    private TechnicianRepository technicianRepository;

    @Autowired
    public RegisterService(WorkerRoleRepository workerRoleRepository, TechnicianRepository technicianRepository) {
        this.workerRoleRepository = workerRoleRepository;
        this.technicianRepository = technicianRepository;
    }

    @Transactional
    public void RegisterNewColleague(@Valid ColleagueRegistrationDTO colleagueRegistrationDTO) {
        registerNewTechnician(colleagueRegistrationDTO);
    }

    private void registerNewTechnician(ColleagueRegistrationDTO colleagueRegistrationDTO) {
        Technician newTechnician = new Technician();
        newTechnician.setFirstName(colleagueRegistrationDTO.getFirstName());
        newTechnician.setLastName(colleagueRegistrationDTO.getLastName());
        newTechnician.setAge(colleagueRegistrationDTO.getAge());
        newTechnician.setRole(workerRoleRepository.findWorkerRoleByRole(WorkerRoleEnum.TECHNICIAN));
        newTechnician.setTasks(new ArrayList<>());
        technicianRepository.save(newTechnician);

    }
}
