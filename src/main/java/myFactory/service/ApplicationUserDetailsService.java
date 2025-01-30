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
    private final TechnicianRepository technicianRepository;


    public ApplicationUserDetailsService(TechnicianRepository technicianRepository) {

        this.technicianRepository = technicianRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String workerIdentity) throws UsernameNotFoundException {
        return technicianRepository.getByWorkerIdentityNickname(workerIdentity)
                .map(this::mapTechnician)
                .orElseThrow(()->new UsernameNotFoundException("User with identity " + workerIdentity + " not found!"));


    }


    private UserDetails mapTechnician(Technician technician) {
        return User.builder()
                .username(technician.getWorkerIdentityNickname())
                .password(technician.getPassword())
                .authorities(technician.getRole().stream()
                        .map(this::map).toList())
                .build();
    }


    private GrantedAuthority map(WorkerRole workerRole) {
        return new SimpleGrantedAuthority("ROLE_" + workerRole.getRole().name());
    }
}
