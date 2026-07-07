package pe.edu.upc.cubegridlab.securities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> {}) // <- FALTA ESTO
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(authorize -> authorize
                    .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                    .requestMatchers(HttpMethod.POST, "/auth/login", "/auth/register").permitAll()
                    .requestMatchers("/users/registra").permitAll()
                    .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html").permitAll()

                    // GET access rules
                    .requestMatchers(HttpMethod.GET, "/cursos/**", "/modulos/**", "/lecciones/**").hasAnyRole("ADMIN","TEACHER","STUDENT")
                    .requestMatchers(HttpMethod.GET, "/evaluaciones/**", "/evaluaciones").hasAnyRole("ADMIN","TEACHER","STUDENT")
                    .requestMatchers(HttpMethod.GET, "/simulaciones/**").hasAnyRole("ADMIN","TEACHER","STUDENT","RESEARCHER")
                    .requestMatchers(HttpMethod.GET, "/iot-devices/**").hasAnyRole("ADMIN","RESEARCHER")
                    .requestMatchers(HttpMethod.GET, "/cubesats/**").hasAnyRole("ADMIN","RESEARCHER")
                    .requestMatchers(HttpMethod.GET, "/misiones/**").hasAnyRole("ADMIN","TEACHER")

                    // Modification (POST/PUT/DELETE) reserved for ADMIN
                    .requestMatchers(HttpMethod.POST, "/cursos/**", "/modulos/**", "/lecciones/**", "/evaluaciones/**", "/simulaciones/**", "/iot-devices/**", "/cubesats/**", "/misiones/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/cursos/**", "/modulos/**", "/lecciones/**", "/evaluaciones/**", "/simulaciones/**", "/iot-devices/**", "/cubesats/**", "/misiones/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/cursos/**", "/modulos/**", "/lecciones/**", "/evaluaciones/**", "/simulaciones/**", "/iot-devices/**", "/cubesats/**", "/misiones/**").hasRole("ADMIN")

                    .anyRequest().authenticated()
            )
            .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
