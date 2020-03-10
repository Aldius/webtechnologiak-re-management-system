package webtech.realestatemanagement.realestatedatastore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import webtech.realestatemanagement.realestatedatastore.model.BaseEntity;
import webtech.realestatemanagement.realestatedatastore.model.entities.RealEstate;
import webtech.realestatemanagement.realestatedatastore.model.repositories.RealEstateRepository;
import webtech.realestatemanagement.realestatedatastore.services.exceptions.DataStoreException;

@Service
@SessionScope
public class RealEstateService {

    private RealEstateRepository realEstateRepository;

    @Autowired
    public RealEstateService(RealEstateRepository realEstateRepository) {
        this.realEstateRepository = realEstateRepository;
    }

    public Iterable<RealEstate> findAll() {
        return realEstateRepository.findAll();
    }

    public Iterable<RealEstate> findActives() {
        return realEstateRepository.findAllByStatus(BaseEntity.ACTIVE_ENTITY_STATUS);
    }

    public RealEstate findById(long id) throws DataStoreException {
        return realEstateRepository.findById(id)
                .orElseThrow(() -> new DataStoreException("Real estate by id " + id + " not found!"));
    }

    public RealEstate addRealEstate(RealEstate realEstate) {
        return realEstateRepository.save(realEstate);
    }

    public RealEstate updateRealEstate(RealEstate realEstate) throws DataStoreException {
        RealEstate current = findById(realEstate.getId());
        realEstate.setVersion(current.getVersion());

        return realEstateRepository.save(realEstate);
    }

    public RealEstate deleteRealEstate(long id) throws DataStoreException {
        RealEstate current = findById(id);
        current.setStatus(BaseEntity.INACTIVE_ENTITY_STATUS);
        return realEstateRepository.save(current);
    }
}
