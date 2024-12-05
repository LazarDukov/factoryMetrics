package myFactory.service;

import jakarta.validation.Valid;
import myFactory.model.dtos.ColleagueRegistrationDTO;
import myFactory.model.entities.Technician;
import myFactory.model.enums.WorkerRoleEnum;
import myFactory.repository.TechnicianRepository;
import myFactory.repository.WorkerRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;

@Service
public class RegisterService {
    private WorkerRoleRepository workerRoleRepository;
    private TechnicianRepository technicianRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public RegisterService(WorkerRoleRepository workerRoleRepository, TechnicianRepository technicianRepository, PasswordEncoder passwordEncoder) {
        this.workerRoleRepository = workerRoleRepository;
        this.technicianRepository = technicianRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void RegisterNewColleague(ColleagueRegistrationDTO colleagueRegistrationDTO) {
        registerNewTechnician(colleagueRegistrationDTO);
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
}
