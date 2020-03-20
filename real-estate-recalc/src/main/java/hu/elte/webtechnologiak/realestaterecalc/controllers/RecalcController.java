package hu.elte.webtechnologiak.realestaterecalc.controllers;

import hu.elte.webtechnologiak.realestaterecalc.services.RecalcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/recalc")
public class RecalcController {

    private RecalcService recalcService;

    @Autowired
    public RecalcController(RecalcService recalcService) {
        this.recalcService = recalcService;
    }

//
//    @GetMapping("/id")
//    public ResponseEntity<Recalc> findById(@RequestParam long id) {
//        try {
//            return ResponseEntity.ok(recalcService.findById(id));
//        } catch (RecalcException e) {
//            return ResponseEntity.badRequest().build();
//        }
//    }
//
//    @PostMapping("/add")
//    public ResponseEntity<Recalc> addRecalc(@RequestBody Recalc recalc) {
//        try {
//            return ResponseEntity.ok(recalcService.addRecalc(recalc));
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().build();
//        }
//    }
//
//    @PutMapping("/update")
//    public ResponseEntity<Recalc> updateRecalc(@RequestBody Recalc recalc) {
//        try {
//            return ResponseEntity.ok(recalcService.updateRecalc(recalc));
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().build();
//        }
//    }
//
//    @DeleteMapping("/delete")
//    public ResponseEntity<Recalc> deleteRecalc(@RequestParam long id) {
//        try {
//            return ResponseEntity.ok(recalcService.deleteRecalc(id));
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().build();
//        }
//    }
}
