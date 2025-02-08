package myFactory.service;

import myFactory.model.entities.*;
import myFactory.repository.SupervisorRepository;
import myFactory.repository.SystemAdministratorRepository;
import myFactory.repository.TechnicianRepository;
import myFactory.repository.WarehouserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {
    private final TechnicianRepository technicianRepository;
    private final SupervisorRepository supervisorRepository;
    private final WarehouserRepository warehouserRepository;
    private final SystemAdministratorRepository systemAdministratorRepository;


    public ApplicationUserDetailsService(TechnicianRepository technicianRepository, SupervisorRepository supervisorRepository, WarehouserRepository warehouserRepository, SystemAdministratorRepository systemAdministratorRepository) {

        this.technicianRepository = technicianRepository;
        this.supervisorRepository = supervisorRepository;
        this.warehouserRepository = warehouserRepository;
        this.systemAdministratorRepository = systemAdministratorRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String workerIdentity) throws UsernameNotFoundException {
        System.out.println("Im here in load user by username");
        UserDetails userDetails = technicianRepository.getByWorkerIdentityNickname(workerIdentity).map(this::mapTechnician)
                .orElseGet(() -> supervisorRepository.getByWorkerIdentityNickname(workerIdentity).map(this::mapSupervisor)
                        .orElseGet(() -> warehouserRepository.getByWorkerIdentityNickname(workerIdentity).map(this::mapWarehouser)
                                .orElseGet(() -> systemAdministratorRepository.getByWorkerIdentityNickname(workerIdentity).map(this::mapSystemAdministrator)
                                        .orElseThrow(() -> new UsernameNotFoundException("User with identity " + workerIdentity + " not found!")))));

        System.out.println(userDetails.getUsername());
        return userDetails;
    }

    private UserDetails mapSystemAdministrator(SystemAdministrator systemAdministrator) {
        return User.builder()
                .username(systemAdministrator.getWorkerIdentityNickname())
                .password(systemAdministrator.getPassword())
                .authorities(systemAdministrator.getRole().stream()
                        .map(this::map).toList())
                .build();
    }


    private UserDetails mapTechnician(Technician technician) {
        System.out.println("mapvam technician-a");
        return User.builder()
                .username(technician.getWorkerIdentityNickname())
                .password(technician.getPassword())
                .authorities(technician.getRole().stream()
                        .map(this::map).toList())
                .build();
    }

    private UserDetails mapSupervisor(Supervisor supervisor) {
        return User.builder()
                .username(supervisor.getWorkerIdentityNickname())
                .password(supervisor.getPassword())
                .authorities(supervisor.getRole().stream()
                        .map(this::map).toList())
                .build();
    }

    private UserDetails mapWarehouser(Warehouser warehouser) {
        return User.builder()
                .username(warehouser.getWorkerIdentityNickname())
                .password(warehouser.getPassword())
                .authorities(warehouser.getRole().stream()
                        .map(this::map).toList())
                .build();
    }

    private GrantedAuthority map(WorkerRole workerRole) {
        return new SimpleGrantedAuthority("ROLE_" + workerRole.getRole().name());
    }
}
