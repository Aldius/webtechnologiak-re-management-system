package hu.elte.webtechnologiak.realestatedocumenthandling.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.elte.webtechnologiak.realestatedocumenthandling.model.BaseEntity;
import hu.elte.webtechnologiak.realestatedocumenthandling.model.entities.DataStoreEntity;
import hu.elte.webtechnologiak.realestatedocumenthandling.model.entities.Document;
import hu.elte.webtechnologiak.realestatedocumenthandling.model.repositories.DataStoreEntityRepository;
import hu.elte.webtechnologiak.realestatedocumenthandling.model.repositories.DocumentRepository;
import hu.elte.webtechnologiak.realestatedocumenthandling.model.utils.DocumentType;
import hu.elte.webtechnologiak.realestatedocumenthandling.services.exceptions.DocumentHandlingException;
import org.apache.tika.config.TikaConfig;
import org.apache.tika.mime.MimeType;
import org.apache.tika.mime.MimeTypeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@SessionScope
@Service
public class DocumentService {

    DocumentRepository documentRepository;
    DataStoreEntityRepository dataStoreEntityRepository;

    @Autowired
    public DocumentService(DocumentRepository documentRepository, DataStoreEntityRepository dataStoreEntityRepository) {
        this.documentRepository = documentRepository;
        this.dataStoreEntityRepository = dataStoreEntityRepository;
    }

    public Iterable<Document> findAllDocument() {
        return documentRepository.findAll();
    }

    public Iterable<Document> findAllActiveDocument() {
        return documentRepository.findAllByStatus(BaseEntity.ACTIVE_ENTITY_STATUS);
    }

    public Iterable<Document> findAllDocumentByEntity(String uniqueId) throws DocumentHandlingException {
        DataStoreEntity dataStoreEntity = dataStoreEntityRepository.findByUniqueIdAndStatus(uniqueId, BaseEntity.ACTIVE_ENTITY_STATUS)
                .orElseThrow(() -> new DocumentHandlingException("Entity by uid " + uniqueId + " not found!"));
        return dataStoreEntity.getDocuments();
    }

    public Document findDocument(long id) throws DocumentHandlingException {
        return documentRepository.findByIdAndStatus(id, BaseEntity.ACTIVE_ENTITY_STATUS)
                .orElseThrow(() -> new DocumentHandlingException("Document by id " + id + " not found!"));
    }

    public Document createDocument(String documentJson, MultipartFile file, String uid)
            throws DocumentHandlingException, IOException, MimeTypeException {
        Document document = new ObjectMapper().readValue(documentJson, Document.class);
        DataStoreEntity dataStoreEntity = dataStoreEntityRepository.findByUniqueIdAndStatus(uid, BaseEntity.ACTIVE_ENTITY_STATUS)
                .orElseThrow(() -> new DocumentHandlingException("Entity by uid " + uid + " not found!"));
        DocumentType dt = document.getDocumentType();
        if (uid.startsWith("RE")) {
            if (dt == DocumentType.APPRAISAL_FORM || dt == DocumentType.ON_SITE_PICTURE || dt == DocumentType.OTHER_DOCUMENT) {
                throw new DocumentHandlingException("This document type cannot be used for Real Estates!");
            }
        } else if (uid.startsWith("AP")) {
            if (dt == DocumentType.CUSTOM_DOCUMENT || dt == DocumentType.LEGAL_BASIS || dt == DocumentType.MORTGAGE_REGISTRATION) {
                throw new DocumentHandlingException("This document type cannot be used for Appraisals!");
            }
        }
        document.setDataStoreEntity(dataStoreEntity);
        document.setData(file.getBytes());
        TikaConfig config = TikaConfig.getDefaultConfig();
        MimeType mimeType = config.getMimeRepository().getRegisteredMimeType(file.getContentType());
        document.setDocumentFormat(mimeType.getExtension());
        documentRepository.save(document);
        return document;
    }

}
