package hu.elte.webtechnologiak.realestatedocumenthandling.controllers;

import hu.elte.webtechnologiak.realestatedocumenthandling.model.entities.Document;
import hu.elte.webtechnologiak.realestatedocumenthandling.services.DocumentService;
import hu.elte.webtechnologiak.realestatedocumenthandling.services.exceptions.DocumentHandlingException;
import org.apache.tika.mime.MimeTypeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/document")
public class DocumentController {

    private DocumentService documentService;

    @Autowired
    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Document> findById(@PathVariable long id) throws DocumentHandlingException {
        return ResponseEntity.ok(documentService.findDocument(id));
    }

    @PostMapping("/add")
    public ResponseEntity<?> addDocument(@RequestPart("properties") String documentJson,
                                                @RequestPart("file") MultipartFile file, @RequestParam String uid) {
        try {
            return ResponseEntity.ok(documentService.createDocument(documentJson, file, uid));
        } catch (DocumentHandlingException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (IOException | MimeTypeException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("File upload error!");
        }
    }
/*
    @PatchMapping("/{id}")
    public ResponseEntity<Document> updateDocument(Document document) {
        return ResponseEntity.ok(documentService.updateDocument(document));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Document> deleteDocument(long id) {
        return ResponseEntity.ok(documentService.deleteDocument(id));
    }*/

}