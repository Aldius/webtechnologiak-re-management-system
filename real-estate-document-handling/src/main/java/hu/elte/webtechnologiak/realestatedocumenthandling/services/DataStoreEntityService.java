package hu.elte.webtechnologiak.realestatedocumenthandling.services;

import hu.elte.webtechnologiak.realestatedocumenthandling.model.BaseEntity;
import hu.elte.webtechnologiak.realestatedocumenthandling.model.entities.DataStoreEntity;
import hu.elte.webtechnologiak.realestatedocumenthandling.model.repositories.DataStoreEntityRepository;
import hu.elte.webtechnologiak.realestatedocumenthandling.services.exceptions.DataStoreException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@SessionScope
@Service
public class DataStoreEntityService {

    private DataStoreEntityRepository dataStoreEntityRepository;

    @Autowired
    public DataStoreEntityService(DataStoreEntityRepository dataStoreEntityRepository) {
        this.dataStoreEntityRepository = dataStoreEntityRepository;
    }

    public DataStoreEntity findActiveByUniqueId(String uniqueId) throws DataStoreException {
        return dataStoreEntityRepository.findByUniqueIdAndStatus(uniqueId, BaseEntity.ACTIVE_ENTITY_STATUS)
                .orElseThrow(() -> new DataStoreException("Entity by uid " + uniqueId + " not found!"));
    }

    public void addDataStoreEntity(DataStoreEntity dataStoreEntity) {
        dataStoreEntityRepository.save(dataStoreEntity);
    }

    public void deleteDataStoreEntity(DataStoreEntity dataStoreEntity) throws DataStoreException {
        DataStoreEntity dse = dataStoreEntityRepository.findByUniqueIdAndStatus(dataStoreEntity.getUniqueId(), BaseEntity.ACTIVE_ENTITY_STATUS)
                .orElseThrow(() -> new DataStoreException("Entity by uid " + dataStoreEntity.getUniqueId() + " not found!"));
        dse.setStatus(dataStoreEntity.getStatus());
        dse.setVersion(dataStoreEntity.getVersion());
        dataStoreEntityRepository.save(dse);
    }
}
