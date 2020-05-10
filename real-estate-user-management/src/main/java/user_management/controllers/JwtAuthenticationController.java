package user_management.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import user_management.exceptions.UserTakenException;
import user_management.services.JwtUserDetailsService;


import user_management.config.JwtTokenUtil;
import user_management.models.JwtRequest;
import user_management.models.JwtResponse;
import user_management.models.UserDTO;

@RestController
@RequestMapping("/")
@CrossOrigin
public class JwtAuthenticationController {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@RequestMapping(value = "/authenticated", method = RequestMethod.GET)
	public ResponseEntity<?> isAuthenticated() {
		return ResponseEntity.ok().build();
	}

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) {
		try {
			authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

			final UserDetails userDetails = userDetailsService
					.loadUserByUsername(authenticationRequest.getUsername());

			final String token = jwtTokenUtil.generateToken(userDetails);

			return ResponseEntity.ok(new JwtResponse(token));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@RequestBody UserDTO user) {
		try {
			return ResponseEntity.ok(userDetailsService.save(user));
		} catch (UserTakenException e) {
			return ResponseEntity.badRequest().build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}