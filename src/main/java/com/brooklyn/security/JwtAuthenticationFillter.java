package com.brooklyn.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class JwtAuthenticationFillter extends OncePerRequestFilter{
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	@Autowired
	private UserDetailsService detailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String token = getTokenFromRequest(request);
		
		if(StringUtils.hasText(token) && jwtTokenProvider.validateToken(token)) {
			String username = jwtTokenProvider.getUsername(token);
			
			UserDetails userDetails = detailsService.loadUserByUsername(username);
			
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
			authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			
			SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		}
		filterChain.doFilter(request, response);
	}
	
	private String getTokenFromRequest(HttpServletRequest request) {
		String beaerToken = request.getHeader("Authorization");
		if(StringUtils.hasText(beaerToken) && beaerToken.startsWith("Bearer ")) {
			return beaerToken.substring(7, beaerToken.length());
		}
		return null;
	}

}
