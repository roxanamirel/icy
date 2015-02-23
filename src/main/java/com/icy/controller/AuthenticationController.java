package com.icy.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.icy.entity.Account;
import com.icy.entity.Authority;
import com.icy.service.AccountService;

@Controller
@RequestMapping("/login")
@SessionAttributes({ "id" })
public class AuthenticationController extends
		AbstractUserDetailsAuthenticationProvider {

	@Autowired
	private MessageDigestPasswordEncoder messageDigestPasswordEncoder;

	@Inject
	protected AccountService accountService;


	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {

	}

	
	@Override
	protected UserDetails retrieveUser(String username,
			UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		String password = (String) authentication.getCredentials();
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		/* String encryptedPassword =
		 messageDigestPasswordEncoder.encodePassword(password, null);
		 */
		com.icy.entity.Account user = accountService.findByUsername(username);
		if (user == null)
			throw new BadCredentialsException(
					"User was not found in the db. Please register!");

		if (!user.getUsername().equals(username))
			throw new BadCredentialsException("Invalid username or password");
		if (!user.getPassword().equals(password))
			throw new BadCredentialsException("Invalid username or password");

	
			authorities.add(new GrantedAuthorityImpl(user.getAuthority().getAuthority()));
	

		return new User(username, password, true, true, true, true, authorities);

	}

}
