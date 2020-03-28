package webtech.realestatemanagement.realestatedatastore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webtech.realestatemanagement.realestatedatastore.model.entities.RealEstate;
import webtech.realestatemanagement.realestatedatastore.services.RealEstateService;
import webtech.realestatemanagement.realestatedatastore.services.exceptions.DataStoreException;

@RestController
@RequestMapping("/realEstate")
public class RealEstateController {

    private RealEstateService realEstateService;

    @Autowired
    public RealEstateController(RealEstateService realEstateService) {
        this.realEstateService = realEstateService;
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<RealEstate>> listAll() {
        return ResponseEntity.ok(realEstateService.findAll());
    }

    @GetMapping("/actives")
    public ResponseEntity<Iterable<RealEstate>> listActives() {
        return ResponseEntity.ok(realEstateService.findActives());
    }

    @GetMapping("/id")
    public ResponseEntity<RealEstate> findById(@RequestParam long id) throws DataStoreException {
        return ResponseEntity.ok(realEstateService.findById(id));
    }

    @GetMapping("/uniqueId")
    public ResponseEntity<RealEstate> findByUId(@RequestParam String uniqueId) throws DataStoreException {
        return ResponseEntity.ok(realEstateService.findByUId(uniqueId));
    }

    @PostMapping("/add")
    public ResponseEntity<RealEstate> addRealEstate(@RequestBody RealEstate realEstate) {
        return ResponseEntity.ok(realEstateService.addRealEstate(realEstate));
    }

    @PutMapping("/update")
    public ResponseEntity<RealEstate> updateRealEstate(@RequestBody RealEstate realEstate) throws DataStoreException {
        return ResponseEntity.ok(realEstateService.updateRealEstate(realEstate));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<RealEstate> deleteRealEstate(@RequestParam String uniqueId) throws DataStoreException {
        return ResponseEntity.ok(realEstateService.deleteRealEstate(uniqueId));
    }
}
