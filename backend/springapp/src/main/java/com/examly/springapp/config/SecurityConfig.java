// SecurityConfig.java
package com.examly.springapp.config;
 
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
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
@EnableMethodSecurity
public class SecurityConfig {
    private final UserDetailsService userDetailsService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final CrosConfig corsConfig;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
 
    public SecurityConfig(UserDetailsService userDetailsService, JwtAuthenticationFilter jwtAuthenticationFilter,
            CrosConfig corsConfig, JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint) {
        this.userDetailsService = userDetailsService;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.corsConfig = corsConfig;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
    }
 
    // private static final String[] WHITELIST = {
    //         "/v3/api-docs/**",
    //         "/swagger-ui/**",
    //         "/swagger-ui.html/",
    //         "/webjars/**",
    //         "/api/login",
    //         "/api/register",
    //         "/api/users/**",
    //         "/api/loanapplication/**",
    //         "/api/loan/**",
    //         "/api/loanapplication/user/**",
    //         "/api/feedback/user/**",
    //         "/api/feedback/**",
    //         "api/user/**"
    // };
 
    // @Bean
    // public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    //     return http.cors(cors -> cors.configurationSource(corsConfig.corsConfigurationSource()))
    //             .csrf(csrf -> csrf.disable())
    //             .authorizeHttpRequests(auth -> auth
    //                     .requestMatchers(WHITELIST)
    //                     .permitAll()
    //                     .anyRequest()
    //                     .authenticated())
    //             .authenticationProvider(authenticationProvider())
    //             .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
    //             .exceptionHandling(exception -> exception.authenticationEntryPoint(jwtAuthenticationEntryPoint))
    //             .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
    //             .build();
    // }
    private static final String[] WHITELIST = {
        "/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html", "/api/login", "/api/register", "/api/**"
};
 
/**
 * Configures the security filter chain.
 *
 * @param http The HttpSecurity object to configure.
 * @return The configured SecurityFilterChain.
 * @throws Exception If an error occurs during configuration.
 */
@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                        .cors()
                        .and()
                        .csrf(c -> c.disable())
                        .authorizeHttpRequests(request -> request
                                        .requestMatchers(WHITELIST).permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/users/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/loan/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/loan/**").hasAnyRole("ADMIN","USER")
                        .requestMatchers(HttpMethod.PUT, "/api/loan/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/loan/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/loanapplication/**").hasRole("USER")
                        .requestMatchers(HttpMethod.GET,
                        "/api/loanapplication/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/loanapplication/**").hasRole("USER")
                        .requestMatchers(HttpMethod.DELETE,
                        "/api/loanapplication/**").hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "/api/feedback/**").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/api/feedback/**").hasAnyRole("USER",
                        "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/feedback/**").hasRole("USER")
                        .requestMatchers(HttpMethod.PUT, "/api/feedback/**").hasRole("USER")
                        .anyRequest().authenticated())
                        .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
                        .and()
                        .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                        .authenticationProvider(authenticationProvider())
                        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                        .build();
}
 
 
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
 
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
 
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
 
}