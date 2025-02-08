package myFactory.config;

import myFactory.service.ApplicationUserDetailsService;
import myFactory.service.JWTService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final ApplicationUserDetailsService applicationUserDetailsService;

    private final JWTService jwtService;

    public SecurityConfiguration(ApplicationUserDetailsService applicationUserDetailsService, JWTService jwtService) {
        this.applicationUserDetailsService = applicationUserDetailsService;
        this.jwtService = jwtService;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(request ->
                        request.requestMatchers("/", "/user/login", "/user/register", "/user/login-error", "/css/**", "/js/**", "/images/**")
                                .permitAll().anyRequest().authenticated())
                .formLogin(form -> form
                        .loginProcessingUrl("/user/login")
                        .usernameParameter("workerIdentity")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/")
                        .failureHandler((request, response, exception) -> {
                            response.sendRedirect("/user/login?error=true");
                        }))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(applicationUserDetailsService);
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();

    }


}

