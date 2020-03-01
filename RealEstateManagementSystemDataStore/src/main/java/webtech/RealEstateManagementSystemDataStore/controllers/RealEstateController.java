package webtech.RealEstateManagementSystemDataStore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import webtech.RealEstateManagementSystemDataStore.model.entities.RealEstate;
import webtech.RealEstateManagementSystemDataStore.services.RealEstateService;

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
}
