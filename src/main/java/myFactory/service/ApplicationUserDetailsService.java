package myFactory.service;

import jakarta.persistence.Transient;
import myFactory.model.entities.Supervisor;
import myFactory.model.entities.Technician;
import myFactory.model.entities.Warehouser;
import myFactory.model.entities.WorkerRole;
import myFactory.repository.SupervisorRepository;
import myFactory.repository.TechnicianRepository;
import myFactory.repository.WarehouserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

public class ApplicationUserDetailsService implements UserDetailsService {
    private final SupervisorRepository supervisorRepository;
    private final TechnicianRepository technicianRepository;
    private final WarehouserRepository warehouserRepository;

    public ApplicationUserDetailsService(SupervisorRepository supervisorRepository, TechnicianRepository technicianRepository, WarehouserRepository warehouserRepository) {
        this.supervisorRepository = supervisorRepository;
        this.technicianRepository = technicianRepository;
        this.warehouserRepository = warehouserRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String workerIdentity) throws UsernameNotFoundException {
        return supervisorRepository.findByWorkerIdentityNickname(workerIdentity).map(this::mapSupervisor).orElseGet(() ->
                technicianRepository.findByWorkerIdentityNickname(workerIdentity).map(this::mapTechician).orElseGet(() ->
                        warehouserRepository.findByWorkerIdentityNickname(workerIdentity).map(this::mapWarehouser).
                                orElseThrow(() -> new UsernameNotFoundException("User with username " + workerIdentity + " not found!"))));
    }

    private UserDetails mapWarehouser(Warehouser warehouser) {
        return User.builder().username(warehouser.getWorkerIdentityNickname()).password(warehouser.getPassword())
                .authorities(warehouser.getRole().stream()
                        .map(this::map).toList()).build();
    }

    private UserDetails mapTechician(Technician technician) {
        return User.builder().username(technician.getWorkerIdentityNickname()).password(technician.getPassword())
                .authorities(technician.getRole().stream()
                        .map(this::map).toList()).build();
    }

    private UserDetails mapSupervisor(Supervisor supervisor) {
        return User.builder().username(supervisor.getWorkerIdentityNickname()).password(supervisor.getPassword())
                .authorities(supervisor.getRole().stream()
                        .map(this::map).toList()).build();
    }

    private GrantedAuthority map(WorkerRole workerRole) {
        return new SimpleGrantedAuthority("ROLE_" + workerRole.getRole().name());
    }
}
