package com.bolsadeideas.springboot.backend.apirest.spbackendapirest.auth;

import com.bolsadeideas.springboot.backend.apirest.spbackendapirest.auth.jwt.JwtEntryPoint;
import com.bolsadeideas.springboot.backend.apirest.spbackendapirest.auth.jwt.JwtRequestFilter;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true)
@Configuration
public class SecurityConfig {

    @Autowired
    private JwtRequestFilter jwtRequestFilter;
    @Autowired
    private JwtEntryPoint jwtEntryPoint;
    @Autowired
    private CorsConfig corsConfig;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration
                                                        authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain web(HttpSecurity http) throws Exception {
        http
                .csrf(csrf->csrf.disable()) // (2)
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/error").permitAll()
                        .requestMatchers("/auth/login", "/api/clientes", "/api/clientes/page/**", "/api/uploads/img/**", "/images/**").permitAll()
                        .requestMatchers(HttpMethod.GET,"/api/clientes/{id}").hasRole("ADMIN")
////                                .requestMatchers("/api/clientes/{id}").permitAll()
////                                .requestMatchers("/api/facturas/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/clientes/upload").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE,"/api/clientes/{id}").hasRole( "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/clientes").hasRole("ADMIN")
                        .requestMatchers("/api/clientes/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .cors(cors-> corsConfig.corsConfigurationSource())
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(exception->exception.authenticationEntryPoint(jwtEntryPoint));

        http
                .formLogin(withDefaults()); // (1)
        http
                .httpBasic(withDefaults()); // (1)

        return http.build();
    }
}
