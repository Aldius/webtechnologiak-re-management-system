package webtech.realestatemanagement.realestatedatastore.services;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

	private final RestCommunicator restCommunicator;

	public AuthenticationService( final RestCommunicator restCommunicator ) {
		this.restCommunicator = restCommunicator;
	}

	public void authenticate( final String token ) {
		try {
			restCommunicator.sendPostRequest("http://real-estate-management-user-mgmt/authenticated", "", token);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
