package myFactory.config;

import myFactory.service.ApplicationUserDetailsService;
import myFactory.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable).
                authorizeHttpRequests(auth -> auth.requestMatchers("/", "/user/login", "/user/register", "/error-page", "/css/**", "/js/**", "/images/**", "/login")
                        .permitAll().anyRequest().authenticated())
                .formLogin(form -> form
                        .loginProcessingUrl("/user/login")
                        .usernameParameter("workerIdentity")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/")
                        .failureUrl("/error-page")
                        .permitAll())
                .logout(logout -> logout.invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .logoutSuccessUrl("/"));
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}

