package webtech.realestatemanagement.realestatedatastore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webtech.realestatemanagement.realestatedatastore.model.entities.RealEstate;
import webtech.realestatemanagement.realestatedatastore.services.AuthenticationService;
import webtech.realestatemanagement.realestatedatastore.services.RealEstateService;
import webtech.realestatemanagement.realestatedatastore.services.exceptions.DataStoreException;

@RestController
@RequestMapping("/realEstate")
public class RealEstateController {

    private RealEstateService realEstateService;

    private AuthenticationService authenticationService;

    @Autowired
    public RealEstateController(RealEstateService realEstateService, AuthenticationService authenticationService) {
        this.realEstateService = realEstateService;
        this.authenticationService = authenticationService;
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<RealEstate>> listAll(@RequestHeader("Authorization") String token) {
        authenticationService.authenticate(token);
        return ResponseEntity.ok(realEstateService.findAll());
    }

    @GetMapping("/actives")
    public ResponseEntity<Iterable<RealEstate>> listActives(@RequestHeader("Authorization") String token) {
        authenticationService.authenticate(token);
        return ResponseEntity.ok(realEstateService.findActives());
    }

    @GetMapping("/id")
    public ResponseEntity<RealEstate> findById(@RequestParam long id, @RequestHeader("Authorization") String token) throws DataStoreException {
        authenticationService.authenticate(token);
        return ResponseEntity.ok(realEstateService.findById(id));
    }

    @GetMapping("/uniqueId")
    public ResponseEntity<RealEstate> findByUId(@RequestParam String uniqueId, @RequestHeader("Authorization") String token) throws DataStoreException {
        authenticationService.authenticate(token);
        return ResponseEntity.ok(realEstateService.findByUId(uniqueId));
    }

    @PostMapping("/add")
    public ResponseEntity<RealEstate> addRealEstate(@RequestBody RealEstate realEstate, @RequestHeader("Authorization") String token) {
        authenticationService.authenticate(token);
        return ResponseEntity.ok(realEstateService.addRealEstate(realEstate, token));
    }

    @PutMapping("/update")
    public ResponseEntity<RealEstate> updateRealEstate(@RequestBody RealEstate realEstate, @RequestHeader("Authorization") String token) throws DataStoreException {
        authenticationService.authenticate(token);
        return ResponseEntity.ok(realEstateService.updateRealEstate(realEstate));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<RealEstate> deleteRealEstate(@RequestParam String uniqueId, @RequestHeader("Authorization") String token) throws DataStoreException {
        authenticationService.authenticate(token);
        return ResponseEntity.ok(realEstateService.deleteRealEstate(uniqueId, token));
    }
}
