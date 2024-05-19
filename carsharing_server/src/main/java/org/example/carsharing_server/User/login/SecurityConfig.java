package org.example.carsharing_server.User.login;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    //private UserServiceImpl userService;




    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .csrf().disable()
                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers(HttpMethod.POST, "/users/register", "/users/login").permitAll()
                                .requestMatchers("/bookings/users/{userID}").hasAuthority("ROLE_RENTER")
                                .requestMatchers(HttpMethod.GET, "/bookings").denyAll()
                                .requestMatchers(HttpMethod.POST, "/bookings").hasAuthority("ROLE_RENTER")
                                .requestMatchers(HttpMethod.PUT, "/bookings/stop").hasAuthority("ROLE_RENTER")
                                .requestMatchers(HttpMethod.GET, "/bookings/user/past/{userID}").hasAuthority("ROLE_RENTER")
                                .requestMatchers(HttpMethod.GET, "/bookings/user/current/{userID}").hasAuthority("ROLE_RENTER")
                                .requestMatchers(HttpMethod.GET, "/bookings/user/{userID}").hasAuthority("ROLE_RENTER")
                                .requestMatchers(HttpMethod.GET, "/cars").hasAuthority("ROLE_RENTER")
                                .requestMatchers(HttpMethod.POST, "/cars").hasAuthority("ROLE_OWNER")
                                .requestMatchers(HttpMethod.PUT, "/cars").hasAuthority("ROLE_OWNER")
                                .requestMatchers(HttpMethod.GET, "/cars/available").hasAuthority("ROLE_RENTER")
                                .requestMatchers(HttpMethod.DELETE, "/cars/{licensePlate}").hasAuthority("ROLE_OWNER")
                                .requestMatchers(HttpMethod.GET, "/cars/user/{userID}").hasAuthority("ROLE_OWNER")
                                .requestMatchers(HttpMethod.POST, "/payments").hasAuthority("ROLE_RENTER")
                                .requestMatchers(HttpMethod.GET, "/payments/{userID}").hasAuthority("ROLE_RENTER")
                                .requestMatchers(HttpMethod.POST, "/reviewratings").hasAuthority("ROLE_RENTER")
                                .requestMatchers(HttpMethod.GET, "/reviewratings/user/").hasAuthority("ROLE_RENTER")
                                .requestMatchers(HttpMethod.GET, "/users").denyAll()
                                .anyRequest().authenticated()
                )
                        .logout().logoutUrl("/logout").invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .and().httpBasic()
                        .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER);
        return http.build();

    }


    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Use BCrypt for password hashing
    }
}
