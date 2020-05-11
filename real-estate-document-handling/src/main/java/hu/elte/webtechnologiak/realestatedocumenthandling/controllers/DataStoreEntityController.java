package hu.elte.webtechnologiak.realestatedocumenthandling.controllers;

import hu.elte.webtechnologiak.realestatedocumenthandling.model.entities.DataStoreEntity;
import hu.elte.webtechnologiak.realestatedocumenthandling.services.DataStoreEntityService;
import hu.elte.webtechnologiak.realestatedocumenthandling.services.exceptions.DataStoreException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/DataStoreEntity")
public class DataStoreEntityController {

    private DataStoreEntityService dataStoreEntityService;

    @Autowired
    public DataStoreEntityController(DataStoreEntityService dataStoreEntityService) {
        this.dataStoreEntityService = dataStoreEntityService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addDataStoreEntity(@RequestBody DataStoreEntity dataStoreEntity) {
        dataStoreEntityService.addDataStoreEntity(dataStoreEntity);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteDataStoreEntity(@RequestBody DataStoreEntity dataStoreEntity) throws DataStoreException {
        dataStoreEntityService.deleteDataStoreEntity(dataStoreEntity);
        return ResponseEntity.ok().build();
    }

}
