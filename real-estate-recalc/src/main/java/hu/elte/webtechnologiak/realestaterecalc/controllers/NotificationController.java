package hu.elte.webtechnologiak.realestaterecalc.controllers;

import hu.elte.webtechnologiak.realestaterecalc.model.entities.Appraisal;
import hu.elte.webtechnologiak.realestaterecalc.model.entities.RealEstate;
import hu.elte.webtechnologiak.realestaterecalc.model.entities.dto.AppraisalDto;
import hu.elte.webtechnologiak.realestaterecalc.model.entities.dto.RealEstateDto;
import hu.elte.webtechnologiak.realestaterecalc.services.AuthenticationService;
import hu.elte.webtechnologiak.realestaterecalc.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
public class NotificationController {

	private final NotificationService notificationService;

	private final AuthenticationService authenticationService;

	@Autowired
	public NotificationController( final NotificationService notificationService, final AuthenticationService authenticationService ) {
		this.notificationService = notificationService;
		this.authenticationService = authenticationService;
	}

	@PostMapping("/realEstate/add")
	public ResponseEntity<RealEstate> addRealEstate( @RequestBody final RealEstateDto realEstate, @RequestHeader("Authorization") final String token ) {
		authenticationService.authenticate(token);

		try {
			return ResponseEntity.ok(notificationService.addRealEstate(realEstate));
		} catch (final Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@PostMapping("/realEstate/get")
	public ResponseEntity<RealEstate> getRealEstate( @RequestBody final RealEstate realEstate, @RequestHeader("Authorization") final String token ) {
		authenticationService.authenticate(token);

		try {
			return ResponseEntity.ok(notificationService.getRealEstate(realEstate));
		} catch (final Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@PostMapping("/realEstate/remove")
	public ResponseEntity<RealEstate> removeRealEstate( @RequestBody final RealEstateDto realEstate, @RequestHeader("Authorization") final String token ) {
		authenticationService.authenticate(token);

		try {
			return ResponseEntity.ok(notificationService.removeRealEstate(realEstate));
		} catch (final Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@PostMapping("/appraisal/add")
	public ResponseEntity<Appraisal> addAppraisal( @RequestBody final AppraisalDto appraisal, @RequestHeader("Authorization") final String token ) {
		authenticationService.authenticate(token);

		try {
			return ResponseEntity.ok(notificationService.addAppraisal(appraisal));
		} catch (final Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@PostMapping("/appraisal/get")
	public ResponseEntity<Appraisal> getAppraisal( @RequestBody final Appraisal appraisal, @RequestHeader("Authorization") final String token ) {
		authenticationService.authenticate(token);

		try {
			return ResponseEntity.ok(notificationService.getAppraisal(appraisal));
		} catch (final Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@PostMapping("/appraisal/remove")
	public ResponseEntity<Appraisal> removeAppraisal( @RequestBody final AppraisalDto appraisal, @RequestHeader("Authorization") final String token ) {
		authenticationService.authenticate(token);

		try {
			return ResponseEntity.ok(notificationService.removeAppraisal(appraisal));
		} catch (final Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

}
