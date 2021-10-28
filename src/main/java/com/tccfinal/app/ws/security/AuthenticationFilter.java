package com.tccfinal.app.ws.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tccfinal.app.ws.SpringApplicationContext;
import com.tccfinal.app.ws.service.resource.UserServiceResource;
import com.tccfinal.app.ws.shared.dto.UserDto;
import com.tccfinal.app.ws.ui.model.request.UserLoginRequestModel;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	
	private final AuthenticationManager authenticationManager;
	
	public AuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Authentication attemptAuthentication(
			HttpServletRequest request,
			HttpServletResponse response)throws AuthenticationException{
		
		try {
			UserLoginRequestModel creds = new ObjectMapper()
					.readValue(request.getInputStream(), UserLoginRequestModel.class);
			return authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							creds.getEmail(),
							creds.getPassword(),
							(Collection<? extends GrantedAuthority>) new ArrayList<>()
							));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void successfulAuthentication(HttpServletRequest request,
			HttpServletResponse response, 
			FilterChain filterChain,
			Authentication auth)throws IOException, ServletException {
		
		String userName = ((User) auth.getPrincipal()).getUsername();
		
		String token = Jwts.builder()
				.setSubject(userName)
				.setExpiration(new Date(System.currentTimeMillis()+SecurityConstants.EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SecurityConstants.getTokenSecret())
				.compact();
		
		UserServiceResource userService = (UserServiceResource) SpringApplicationContext.getBean("userService");
		UserDto userDto = userService.getUserByEmail(userName);
		
		response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
		response.addHeader("UserId", userDto.getUserId());
	}
	
}



























