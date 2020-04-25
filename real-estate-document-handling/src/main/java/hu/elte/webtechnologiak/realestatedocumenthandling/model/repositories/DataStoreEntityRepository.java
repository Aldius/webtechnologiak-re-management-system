package hu.elte.webtechnologiak.realestatedocumenthandling.model.repositories;

import hu.elte.webtechnologiak.realestatedocumenthandling.model.entities.DataStoreEntity;
import hu.elte.webtechnologiak.realestatedocumenthandling.model.entities.Document;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DataStoreEntityRepository extends CrudRepository<DataStoreEntity, String> {

    Optional<DataStoreEntity> findByUniqueIdAndStatus(String uid, int status);
}
