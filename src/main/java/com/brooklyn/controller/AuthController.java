package com.brooklyn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.brooklyn.payload.LoginDTO;
import com.brooklyn.payload.RegisterDTO;
import com.brooklyn.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	@PostMapping(value = {"/login", "/signin"})
	public ResponseEntity<String> login(@RequestBody LoginDTO dto){
		String response = authService.login(dto);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping(value = {"/register", "/signup"})
	public ResponseEntity<String> register(@RequestBody RegisterDTO dto){
		String response = authService.register(dto);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
}
