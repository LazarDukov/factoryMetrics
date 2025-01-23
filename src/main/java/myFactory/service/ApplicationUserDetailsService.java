package myFactory.service;

import myFactory.model.entities.*;
import myFactory.repository.TechnicianRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {
//    private final LoginService loginService;
    private final TechnicianRepository technicianRepository;

    //    private final SystemAdministratorRepository systemAdministratorRepository;
//    private final SupervisorRepository supervisorRepository;

//    private final WarehouserRepository warehouserRepository;

    public ApplicationUserDetailsService(LoginService loginService,
                                         TechnicianRepository technicianRepository) {
//        this.loginService = loginService;

        this.technicianRepository = technicianRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String workerIdentity) throws UsernameNotFoundException {
//        Technician tech = loginService.getTechnicianByWorkerIdentity(workerIdentity);
        return technicianRepository.getByWorkerIdentityNickname(workerIdentity)
                .map(this::mapTechnician)
                .orElseThrow(()->new UsernameNotFoundException("User with identity " + workerIdentity + " not found!"));


    }

    //
//    private UserDetails mapWarehouser(Warehouser warehouser) {
//        return User.builder().username(warehouser.getWorkerIdentityNickname()).password(warehouser.getPassword())
//                .authorities(warehouser.getRole().stream()
//                        .map(this::map).toList()).build();
//    }
//
    private UserDetails mapTechnician(Technician technician) {
        return User.builder()
                .username(technician.getWorkerIdentityNickname())
                .password(technician.getPassword())
                .authorities(technician.getRole().stream()
                        .map(this::map).toList())
                .build();
    }

    //
//    private UserDetails mapSupervisor(Supervisor supervisor) {
//        return User.builder().username(supervisor.getWorkerIdentityNickname()).password(supervisor.getPassword())
//                .authorities(supervisor.getRole().stream()
//                        .map(this::map).toList()).build();
//    }
//
//    private UserDetails mapSystemAdministrator(SystemAdministrator systemAdministrator) {
//        return User.builder().username(systemAdministrator.getWorkerIdentityNickname()).password(systemAdministrator.getPassword())
//                .authorities(systemAdministrator.getRole().stream()
//                        .map(this::map).toList()).build();
//    }
//
    private GrantedAuthority map(WorkerRole workerRole) {
        return new SimpleGrantedAuthority("ROLE_" + workerRole.getRole().name());
    }
}
