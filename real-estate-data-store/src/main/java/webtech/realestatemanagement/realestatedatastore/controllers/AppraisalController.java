package webtech.realestatemanagement.realestatedatastore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webtech.realestatemanagement.realestatedatastore.model.entities.Appraisal;
import webtech.realestatemanagement.realestatedatastore.services.AppraisalService;
import webtech.realestatemanagement.realestatedatastore.services.AuthenticationService;
import webtech.realestatemanagement.realestatedatastore.services.exceptions.DataStoreException;

@RestController
@RequestMapping("/appraisal")
public class AppraisalController {

    private AppraisalService appraisalService;

    private AuthenticationService authenticationService;

    @Autowired
    public AppraisalController(AppraisalService appraisalService, AuthenticationService authenticationService) {
        this.appraisalService = appraisalService;
        this.authenticationService = authenticationService;
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<Appraisal>> listAll(@RequestHeader("Authorization") String token) {
        authenticationService.authenticate(token);
        return ResponseEntity.ok(appraisalService.findAll());
    }

    @GetMapping("/actives")
    public ResponseEntity<Iterable<Appraisal>> listActives(@RequestHeader("Authorization") String token) {
        authenticationService.authenticate(token);
        return ResponseEntity.ok(appraisalService.findActives());
    }

    @GetMapping("/realEstate")
    public ResponseEntity<Iterable<Appraisal>> findByRealEstateId(@RequestParam String realEstateId, @RequestHeader("Authorization") String token) {
        authenticationService.authenticate(token);
        return ResponseEntity.ok(appraisalService.findByRealEstateId(realEstateId));
    }

    @GetMapping("/id")
    public ResponseEntity<Appraisal> findById(@RequestParam long id, @RequestHeader("Authorization") String token) throws DataStoreException {
        authenticationService.authenticate(token);
        return ResponseEntity.ok(appraisalService.findById(id));
    }

    @GetMapping("/uniqueId")
    public ResponseEntity<Appraisal> findByUId(@RequestParam String uniqueId, @RequestHeader("Authorization") String token) throws DataStoreException {
        authenticationService.authenticate(token);
        return ResponseEntity.ok(appraisalService.findByUId(uniqueId));
    }

    @PostMapping("/add")
    public ResponseEntity<Appraisal> addAppraisal(@RequestBody Appraisal appraisal, @RequestHeader("Authorization") String token) throws DataStoreException {
        authenticationService.authenticate(token);
        return ResponseEntity.ok(appraisalService.addAppraisal(appraisal, token));
    }

    @PutMapping("/update")
    public ResponseEntity<Appraisal> updateAppraisal(@RequestBody Appraisal appraisal, @RequestHeader("Authorization") String token) throws DataStoreException {
        authenticationService.authenticate(token);
        return ResponseEntity.ok(appraisalService.updateAppraisal(appraisal));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Appraisal> deleteAppraisal(@RequestParam String uniqueId, @RequestHeader("Authorization") String token) throws DataStoreException {
        authenticationService.authenticate(token);
        return ResponseEntity.ok(appraisalService.deleteAppraisal(uniqueId, token));
    }
}
