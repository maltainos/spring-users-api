package com.tccfinal.app.ws.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Jwts;

public class AuthorizationFilter extends BasicAuthenticationFilter{
	
	public AuthorizationFilter(AuthenticationManager authManager) {
		super(authManager);
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		String header = request.getHeader(SecurityConstants.HEADER_STRING);
		
		if(header == null || !header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
			filterChain.doFilter(request, response);
			return;
		}
		
		UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		filterChain.doFilter(request, response);
	}

	@SuppressWarnings("unchecked")
	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(SecurityConstants.HEADER_STRING);
		
		if(token != null) {
			token = token.replace(SecurityConstants.TOKEN_PREFIX, "");
			
			String user = Jwts.parser()
					.setSigningKey(SecurityConstants.getTokenSecret())
					.parseClaimsJws(token)
					.getBody()
					.getSubject();
			if(user != null) {
				return new UsernamePasswordAuthenticationToken(user, null, (Collection<? extends GrantedAuthority>) new ArrayList<>());
			}
			return null;
		}
		return null;
	}
}







