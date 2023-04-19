package com.brooklyn.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.brooklyn.security.JwtAuthenticationEntryPoint;
import com.brooklyn.security.JwtAuthenticationFillter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
	
	@Autowired
	private UserDetailsService detailsService;
	@Autowired
	private JwtAuthenticationEntryPoint authenticationEntryPoint;
	@Autowired
	private JwtAuthenticationFillter authenticationFillter;
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}
	
	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.authorizeHttpRequests((authorize)-> 
					authorize.requestMatchers(HttpMethod.GET,"/**").permitAll()
						     .requestMatchers("/auth/**").permitAll()
						.anyRequest().authenticated())
						.exceptionHandling(exception -> exception
								.authenticationEntryPoint(authenticationEntryPoint))
						.sessionManagement(session -> session
								.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		http.addFilterBefore(authenticationFillter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
	
//	@Bean
//	public UserDetailsService userDetailsService() {
//		UserDetails brooklyn = User.builder()
//				.username("brooklyn")
//				.password(passwordEncoder().encode("brooklyn"))
//				.roles("USER").build();
//		UserDetails admin = User.builder()
//				.username("admin")
//				.password(passwordEncoder().encode("admin"))
//				.roles("ADMIN").build();
//		return new InMemoryUserDetailsManager(brooklyn, admin);
//	}
	
}
