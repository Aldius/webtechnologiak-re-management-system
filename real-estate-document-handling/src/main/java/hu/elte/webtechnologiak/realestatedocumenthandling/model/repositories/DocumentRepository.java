package hu.elte.webtechnologiak.realestatedocumenthandling.model.repositories;

import hu.elte.webtechnologiak.realestatedocumenthandling.model.entities.Document;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DocumentRepository extends CrudRepository<Document, Long> {
    Iterable<Document> findAllByStatus(int status);
    //Iterable<Document> findAllByDataStoreEntityUniqueId(String uniqueId);

    Optional<Document> findById(Long id);
    Optional<Document> findByIdAndStatus(Long id, int status);
}
