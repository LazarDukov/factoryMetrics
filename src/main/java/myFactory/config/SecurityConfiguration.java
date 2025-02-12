package myFactory.config;

import myFactory.interceptor.JwtAuthFilter;
import myFactory.service.ApplicationUserDetailsService;

import myFactory.util.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final ApplicationUserDetailsService applicationUserDetailsService;
    @Autowired
    private JwtToken jwtToken;
    @Autowired
    private UserDetailsService userDetailsService;

    public SecurityConfiguration(ApplicationUserDetailsService applicationUserDetailsService) {
        this.applicationUserDetailsService = applicationUserDetailsService;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        JwtAuthFilter jwtAuthFilter = new JwtAuthFilter(jwtToken, userDetailsService);
        return http.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(request ->
                        request.requestMatchers("/", "/user/login", "/user/register", "/user/login-error", "/css/**", "/js/**", "/images/**")
                                .permitAll().anyRequest().authenticated())
                .formLogin(form -> form
                        .loginProcessingUrl("/user/login")
                        .usernameParameter("workerIdentity")
                        .passwordParameter("password")
                        .successHandler((request, response, authentication) -> {
                            String token = jwtToken.generateToken(authentication.getName());
                            response.setContentType("application/json");
                            response.setCharacterEncoding("UTF-8");
                            response.getWriter().write("{token: " + token + "}");
                            response.sendRedirect("/");
                            System.out.println(token);
                        })
                        .failureHandler((request, response, exception) -> {
                            response.sendRedirect("/user/login?error=true");
                        })
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) // Allow session-based auth
                )
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class).build(); // Add JWT validation filter

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

