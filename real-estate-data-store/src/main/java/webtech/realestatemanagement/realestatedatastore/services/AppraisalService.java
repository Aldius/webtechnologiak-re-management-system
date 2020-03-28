package webtech.realestatemanagement.realestatedatastore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import webtech.realestatemanagement.realestatedatastore.model.BaseEntity;
import webtech.realestatemanagement.realestatedatastore.model.entities.Appraisal;
import webtech.realestatemanagement.realestatedatastore.model.repositories.AppraisalRepository;
import webtech.realestatemanagement.realestatedatastore.services.exceptions.DataStoreException;

@Service
@SessionScope
public class AppraisalService {

    private AppraisalRepository appraisalRepository;
    private RealEstateService realEstateService;

    @Autowired
    public AppraisalService(AppraisalRepository appraisalRepository,
                            RealEstateService realEstateService) {
        this.appraisalRepository = appraisalRepository;
        this.realEstateService = realEstateService;
    }

    public Iterable<Appraisal> findAll() {
        return appraisalRepository.findAll();
    }

    public Iterable<Appraisal> findActives() {
        return appraisalRepository.findAllByStatus(BaseEntity.ACTIVE_ENTITY_STATUS);
    }

    public Iterable<Appraisal> findByRealEstateId(String realEstateId) {
        return appraisalRepository.findAllByRealEstateId(realEstateId);
    }

    public Appraisal findById(long id) throws DataStoreException {
        return appraisalRepository.findById(id)
                .orElseThrow(() -> new DataStoreException("Appraisal by id " + id + " not found!"));
    }

    public Appraisal findByUId(String uniqueId) throws DataStoreException {
        return appraisalRepository.findByUniqueId(uniqueId)
                .orElseThrow(() -> new DataStoreException("Appraisal by unique id " + uniqueId + " not found!"));
    }

    public Appraisal addAppraisal(Appraisal appraisal) throws DataStoreException {
        realEstateService.findByUId(appraisal.getRealEstateId());
        appraisal.setUniqueId("AP" + appraisalRepository.findMaxId().longValue());
        return appraisalRepository.save(appraisal);
    }

    public Appraisal updateAppraisal(Appraisal appraisal) throws DataStoreException {
        realEstateService.findByUId(appraisal.getRealEstateId());
        Appraisal current = findByUId(appraisal.getUniqueId());
        appraisal.setVersion(current.getVersion());

        return appraisalRepository.save(appraisal);
    }

    public Appraisal deleteAppraisal(String uId) throws DataStoreException {
        Appraisal current = findByUId(uId);
        current.setStatus(BaseEntity.INACTIVE_ENTITY_STATUS);
        return appraisalRepository.save(current);
    }
}
