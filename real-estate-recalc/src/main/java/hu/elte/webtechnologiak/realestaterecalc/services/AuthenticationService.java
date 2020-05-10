package hu.elte.webtechnologiak.realestaterecalc.services;

import hu.elte.webtechnologiak.realestaterecalc.services.utils.HttpUtil;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

	private final HttpUtil httpUtil;

	public AuthenticationService( final HttpUtil httpUtil ) {
		this.httpUtil = httpUtil;
	}

	public void authenticate( final String token ) {
		httpUtil.sendPostRequest("http://real-estate-management-user-mgmt/authenticated", "", token);
	}

}
