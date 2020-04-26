package hu.elte.webtechnologiak.realestatedocumenthandling.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.elte.webtechnologiak.realestatedocumenthandling.model.BaseEntity;
import hu.elte.webtechnologiak.realestatedocumenthandling.model.entities.DataStoreEntity;
import hu.elte.webtechnologiak.realestatedocumenthandling.model.entities.Document;
import hu.elte.webtechnologiak.realestatedocumenthandling.model.repositories.DocumentRepository;
import hu.elte.webtechnologiak.realestatedocumenthandling.model.utils.DocumentType;
import hu.elte.webtechnologiak.realestatedocumenthandling.services.exceptions.DataStoreException;
import hu.elte.webtechnologiak.realestatedocumenthandling.services.exceptions.DocumentHandlingException;
import org.apache.tika.config.TikaConfig;
import org.apache.tika.mime.MimeType;
import org.apache.tika.mime.MimeTypeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@SessionScope
@Service
public class DocumentService {

    DocumentRepository documentRepository;
    DataStoreEntityService dataStoreEntityService;

    @Autowired
    public DocumentService(DocumentRepository documentRepository, DataStoreEntityService dataStoreEntityService) {
        this.documentRepository = documentRepository;
        this.dataStoreEntityService = dataStoreEntityService;
    }

    public Iterable<Document> findAllDocument() {
        return documentRepository.findAll();
    }

    public Iterable<Document> findAllActiveDocument() {
        return documentRepository.findAllByStatus(BaseEntity.ACTIVE_ENTITY_STATUS);
    }

    public Iterable<Document> findAllDocumentByEntity(String uniqueId) throws DataStoreException {
        DataStoreEntity dataStoreEntity = dataStoreEntityService.findActiveByUniqueId(uniqueId);
        return dataStoreEntity.getDocuments();
    }

    public Document findDocument(long id) throws DocumentHandlingException {
        return documentRepository.findByIdAndStatus(id, BaseEntity.ACTIVE_ENTITY_STATUS)
                .orElseThrow(() -> new DocumentHandlingException("Document by id " + id + " not found!"));
    }

    public Document createDocument(String documentJson, MultipartFile file, String uid)
            throws DocumentHandlingException, IOException, MimeTypeException, DataStoreException {
        Document document = new ObjectMapper().readValue(documentJson, Document.class);
        DataStoreEntity dataStoreEntity = dataStoreEntityService.findActiveByUniqueId(uid);
        checkDocumentTypeValidity(uid, document.getDocumentType());
        document.setDataStoreEntity(dataStoreEntity);
        document.setData(file.getBytes());
        TikaConfig config = TikaConfig.getDefaultConfig();
        MimeType mimeType = config.getMimeRepository().getRegisteredMimeType(file.getContentType());
        document.setDocumentFormat(mimeType.getExtension());
        documentRepository.save(document);
        return document;
    }

    public Document updateDocument(long id, Document documentJson) throws DocumentHandlingException {
        Document document = documentRepository.findByIdAndStatus(id, BaseEntity.ACTIVE_ENTITY_STATUS)
                .orElseThrow(() -> new DocumentHandlingException("Document by id " + id + " not found!"));
        if (documentJson.getDescription() != null) {
            document.setDescription(documentJson.getDescription());
        }
        if (documentJson.getDocumentType() != null) {
            checkDocumentTypeValidity(document.getDataStoreEntity().getUniqueId(), documentJson.getDocumentType());
            document.setDocumentType(documentJson.getDocumentType());
        }
        return documentRepository.save(document);
    }

    public void deleteDocument(long id) throws DocumentHandlingException {
        Document document = documentRepository.findByIdAndStatus(id, BaseEntity.ACTIVE_ENTITY_STATUS)
                .orElseThrow(() -> new DocumentHandlingException("Document by id " + id + " not found!"));
        document.setStatus(BaseEntity.INACTIVE_ENTITY_STATUS);
        documentRepository.save(document);
    }

    public ResponseEntity<byte[]> downloadDocument(long id) throws DocumentHandlingException {
        Document document = documentRepository.findByIdAndStatus(id, BaseEntity.ACTIVE_ENTITY_STATUS)
                .orElseThrow(() -> new DocumentHandlingException("Document by id " + id + " not found!"));
        HttpHeaders headers = new HttpHeaders();
        TikaConfig config = TikaConfig.getDefaultConfig();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        String filename = document.getDataStoreEntity().getUniqueId()
                + "_" + document.getDocumentType().name() + document.getDocumentFormat();
        headers.setContentDispositionFormData(filename, filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        return new ResponseEntity<>(document.getData(), headers, HttpStatus.OK);
    }

    private void checkDocumentTypeValidity(String uid, DocumentType dt) throws DocumentHandlingException {
        if (uid.startsWith("RE")) {
            if (dt == DocumentType.APPRAISAL_FORM || dt == DocumentType.ON_SITE_PICTURE || dt == DocumentType.OTHER_DOCUMENT) {
                throw new DocumentHandlingException("This document type cannot be used for Real Estates!");
            }
        } else if (uid.startsWith("AP")) {
            if (dt == DocumentType.CUSTOM_DOCUMENT || dt == DocumentType.LEGAL_BASIS || dt == DocumentType.MORTGAGE_REGISTRATION) {
                throw new DocumentHandlingException("This document type cannot be used for Appraisals!");
            }
        }
    }

}
