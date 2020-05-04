package hu.elte.webtechnologiak.realestaterecalc.controllers;

import hu.elte.webtechnologiak.realestaterecalc.services.RecalcService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recalc")
public class RecalcController {

	private final RecalcService recalcServiceService;

	public RecalcController( final RecalcService recalcServiceService ) {
		this.recalcServiceService = recalcServiceService;
	}

	@PostMapping("/run/all")
	public ResponseEntity<Void> runAllAlgs() {
		try {
			recalcServiceService.runAllAlgorithms();
			return ResponseEntity.ok().build();
		} catch (final Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@PostMapping("/run/currencies")
	public ResponseEntity<Void> runCurrencyAlgs() {
		try {
			recalcServiceService.runCurrencyAlgorithms();
			return ResponseEntity.ok().build();
		} catch (final Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

}
