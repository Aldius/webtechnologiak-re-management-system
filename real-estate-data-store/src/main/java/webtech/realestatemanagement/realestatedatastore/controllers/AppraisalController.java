package webtech.realestatemanagement.realestatedatastore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webtech.realestatemanagement.realestatedatastore.model.entities.Appraisal;
import webtech.realestatemanagement.realestatedatastore.services.AppraisalService;
import webtech.realestatemanagement.realestatedatastore.services.exceptions.DataStoreException;

@RestController
@RequestMapping("/appraisal")
public class AppraisalController {

    private AppraisalService appraisalService;

    @Autowired
    public AppraisalController(AppraisalService appraisalService) {
        this.appraisalService = appraisalService;
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<Appraisal>> listAll() {
        return ResponseEntity.ok(appraisalService.findAll());
    }

    @GetMapping("/actives")
    public ResponseEntity<Iterable<Appraisal>> listActives() {
        return ResponseEntity.ok(appraisalService.findActives());
    }

    @GetMapping("/realEstate")
    public ResponseEntity<Iterable<Appraisal>> findByRealEstateId(@RequestParam String realEstateId) {
        return ResponseEntity.ok(appraisalService.findByRealEstateId(realEstateId));
    }

    @GetMapping("/id")
    public ResponseEntity<Appraisal> findById(@RequestParam long id) throws DataStoreException {
        return ResponseEntity.ok(appraisalService.findById(id));
    }

    @GetMapping("/uniqueId")
    public ResponseEntity<Appraisal> findByUId(@RequestParam String uniqueId) throws DataStoreException {
        return ResponseEntity.ok(appraisalService.findByUId(uniqueId));
    }

    @PostMapping("/add")
    public ResponseEntity<Appraisal> addAppraisal(@RequestBody Appraisal appraisal) throws DataStoreException {
        return ResponseEntity.ok(appraisalService.addAppraisal(appraisal));
    }

    @PutMapping("/update")
    public ResponseEntity<Appraisal> updateAppraisal(@RequestBody Appraisal appraisal) throws DataStoreException {
        return ResponseEntity.ok(appraisalService.updateAppraisal(appraisal));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Appraisal> deleteAppraisal(@RequestParam String uniqueId) throws DataStoreException {
        return ResponseEntity.ok(appraisalService.deleteAppraisal(uniqueId));
    }
}
