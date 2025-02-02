package myFactory.config;

import myFactory.service.ApplicationUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
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
    @Autowired
    private UserDetailsService userDetailsService;


//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.csrf(AbstractHttpConfigurer::disable).
//                authorizeHttpRequests(auth -> auth.requestMatchers("/", "/user/login", "/user/register", "/error-page", "/css/**", "/js/**", "/images/**", "/login")
//                        .permitAll().anyRequest().authenticated())
//                .httpBasic(Customizer.withDefaults())
//                .formLogin(form -> form
//                        .loginProcessingUrl("/user/login")
//                        .usernameParameter("workerIdentity")
//                        .passwordParameter("password")
//                        .defaultSuccessUrl("/")
//                        .failureUrl("/error-page")
//                        .permitAll())
//                .sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .logout(logout -> logout.invalidateHttpSession(true)
//                        .clearAuthentication(true)
//                        .logoutSuccessUrl("/"));
//        return http.build();
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(auth -> auth.requestMatchers("/", "/user/login", "/user/register", "/user/login-error", "/css/**", "/js/**", "/images/**").permitAll())
                .formLogin(form -> form
                        .loginProcessingUrl("/user/login")
                        .usernameParameter("workerIdentity")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/", true)
                        .failureHandler((request, response, exception) -> {
                            response.sendRedirect("/user/login?error=true");
                        }))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        provider.setUserDetailsService(userDetailsService);

        return provider;
    }
}

