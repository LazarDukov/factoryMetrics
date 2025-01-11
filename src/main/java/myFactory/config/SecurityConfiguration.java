package myFactory.config;

import myFactory.model.entities.SystemAdministrator;
import myFactory.repository.SupervisorRepository;
import myFactory.repository.SystemAdministratorRepository;
import myFactory.repository.TechnicianRepository;
import myFactory.repository.WarehouserRepository;
import myFactory.service.ApplicationUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.SecurityContextRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(auth -> auth.requestMatchers("/","/user/login","/user/register","/css/**", "/js/**", "/images/**")
                        .permitAll().anyRequest().authenticated()).
                formLogin(form -> form.loginPage("/user/login").permitAll()
                        .usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                        .passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY)
                        .defaultSuccessUrl("/", true).failureForwardUrl("/error-page"))
                        .logout(LogoutConfigurer::permitAll);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(SystemAdministratorRepository systemAdministratorRepository, SupervisorRepository supervisorRepository, TechnicianRepository technicianRepository, WarehouserRepository warehouserRepository) {
        return new ApplicationUserDetailsService(systemAdministratorRepository, supervisorRepository, technicianRepository, warehouserRepository);
    }



}

