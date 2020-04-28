package hu.elte.webtechnologiak.realestaterecalc.controllers;

import hu.elte.webtechnologiak.realestaterecalc.model.entities.Appraisal;
import hu.elte.webtechnologiak.realestaterecalc.model.entities.RealEstate;
import hu.elte.webtechnologiak.realestaterecalc.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
public class NotificationController {

	private final NotificationService notificationService;

	@Autowired
	public NotificationController( final NotificationService notificationService ) {
		this.notificationService = notificationService;
	}

	@PostMapping("/realEstate/add")
	public ResponseEntity<RealEstate> addRealEstate( @RequestBody final RealEstate realEstate ) {
		try {
			return ResponseEntity.ok(notificationService.addRealEstate(realEstate));
		} catch (final Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@PostMapping("/realEstate/get")
	public ResponseEntity<RealEstate> getRealEstate( @RequestBody final RealEstate realEstate ) {
		try {
			return ResponseEntity.ok(notificationService.getRealEstate(realEstate));
		} catch (final Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@PostMapping("/realEstate/remove")
	public ResponseEntity<RealEstate> removeRealEstate( @RequestBody final RealEstate realEstate ) {
		try {
			return ResponseEntity.ok(notificationService.removeRealEstate(realEstate));
		} catch (final Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@PostMapping("/appraisal/add")
	public ResponseEntity<Appraisal> addAppraisal( @RequestBody final Appraisal appraisal ) {
		try {
			return ResponseEntity.ok(notificationService.addAppraisal(appraisal));
		} catch (final Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@PostMapping("/appraisal/get")
	public ResponseEntity<Appraisal> getAppraisal( @RequestBody final Appraisal appraisal ) {
		try {
			return ResponseEntity.ok(notificationService.getAppraisal(appraisal));
		} catch (final Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@PostMapping("/appraisal/remove")
	public ResponseEntity<Appraisal> removeAppraisal( @RequestBody final Appraisal appraisal ) {
		try {
			return ResponseEntity.ok(notificationService.removeAppraisal(appraisal));
		} catch (final Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

}
