package hu.elte.webtechnologiak.realestaterecalc.services;

import hu.elte.webtechnologiak.realestaterecalc.model.BaseEntity;
import hu.elte.webtechnologiak.realestaterecalc.model.entities.RealEstate;
import hu.elte.webtechnologiak.realestaterecalc.model.repositories.RealEstateRepository;
import hu.elte.webtechnologiak.realestaterecalc.services.exceptions.DataStoreException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

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

    public RealEstate findByUniqueId(String id) throws DataStoreException {
        return realEstateRepository.findByUniqueId(id)
                .orElseThrow(() -> new DataStoreException("Real estate by id " + id + " not found!"));
    }

    public RealEstate addRealEstate(RealEstate realEstate) {
        return realEstateRepository.save(realEstate);
    }

    public RealEstate updateRealEstate(RealEstate realEstate) throws DataStoreException {
        RealEstate current = findByUniqueId(realEstate.getUniqueId());
        realEstate.setVersion(current.getVersion());

        return realEstateRepository.save(realEstate);
    }

    public RealEstate deleteRealEstate(RealEstate realEstate) throws DataStoreException {
        RealEstate current = findByUniqueId(realEstate.getUniqueId());
        current.setStatus(BaseEntity.INACTIVE_ENTITY_STATUS);
        return realEstateRepository.save(current);
    }
}
