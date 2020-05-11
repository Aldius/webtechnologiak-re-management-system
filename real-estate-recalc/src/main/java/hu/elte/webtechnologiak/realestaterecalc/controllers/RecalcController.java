package hu.elte.webtechnologiak.realestaterecalc.controllers;

import hu.elte.webtechnologiak.realestaterecalc.services.AuthenticationService;
import hu.elte.webtechnologiak.realestaterecalc.services.RecalcService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recalc")
public class RecalcController {

	private final RecalcService recalcServiceService;

	private final AuthenticationService authenticationService;

	public RecalcController( final RecalcService recalcServiceService, final AuthenticationService authenticationService ) {
		this.recalcServiceService = recalcServiceService;
		this.authenticationService = authenticationService;
	}

	@PostMapping("/run/all")
	public ResponseEntity<Void> runAllAlgs( @RequestHeader("Authorization") final String token ) {
		authenticationService.authenticate(token);

		try {
			recalcServiceService.runAllAlgorithms();
			return ResponseEntity.ok().build();
		} catch (final Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@PostMapping("/run/currencies")
	public ResponseEntity<Void> runCurrencyAlgs( @RequestHeader("Authorization") final String token ) {
		authenticationService.authenticate(token);

		try {
			recalcServiceService.runCurrencyAlgorithms();
			return ResponseEntity.ok().build();
		} catch (final Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

}
