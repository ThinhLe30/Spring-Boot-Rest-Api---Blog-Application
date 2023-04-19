package com.brooklyn.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.brooklyn.entity.Role;
import com.brooklyn.entity.User;
import com.brooklyn.exception.BlogApiException;
import com.brooklyn.payload.LoginDTO;
import com.brooklyn.payload.RegisterDTO;
import com.brooklyn.repository.RoleRepository;
import com.brooklyn.repository.UserRepository;
import com.brooklyn.security.JwtTokenProvider;

@Service
public class AuthService {

	@Autowired
	private UserRepository repository;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private PasswordEncoder encoder;
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	public String login(LoginDTO dto) {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				dto.getUsernameOrEmail(),
				dto.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = jwtTokenProvider.generateToken(authentication);
		return token;
	}
	public String register(RegisterDTO registerDTO) {
		if(repository.existsByUsername(registerDTO.getUsername())) {
			throw new BlogApiException(HttpStatus.BAD_REQUEST, "Username is already exists");
		}
		if(repository.existsByEmail(registerDTO.getEmail())) {
			throw new BlogApiException(HttpStatus.BAD_REQUEST, "Email is already exists");
		}
		User user = new User();
		user.setName(registerDTO.getName());
		user.setUsername(registerDTO.getUsername());
		user.setPassword(encoder.encode(registerDTO.getPassword()));
		user.setEmail(registerDTO.getEmail());
		
		Set<Role> roles = new HashSet<>();
		Role userRole = roleRepository.findByName("ROLE_USER").get();
		roles.add(userRole);
		user.setRoles(roles);
		repository.save(user);
		return "User registered successfully!.";
	}
}
