package hu.elte.webtechnologiak.realestaterecalc.controllers;

import hu.elte.webtechnologiak.realestaterecalc.model.entities.RealEstate;
import hu.elte.webtechnologiak.realestaterecalc.services.RealEstateService;
import hu.elte.webtechnologiak.realestaterecalc.services.exceptions.DataStoreException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<RealEstate> findById(@RequestParam long id) {
        try {
            return ResponseEntity.ok(realEstateService.findById(id));
        } catch (DataStoreException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<RealEstate> addRealEstate(@RequestBody RealEstate realEstate) {
        try {
            return ResponseEntity.ok(realEstateService.addRealEstate(realEstate));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/update")
    public ResponseEntity<RealEstate> updateRealEstate(@RequestBody RealEstate realEstate) {
        try {
            return ResponseEntity.ok(realEstateService.updateRealEstate(realEstate));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<RealEstate> deleteRealEstate(@RequestParam long id) {
        try {
            return ResponseEntity.ok(realEstateService.deleteRealEstate(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
