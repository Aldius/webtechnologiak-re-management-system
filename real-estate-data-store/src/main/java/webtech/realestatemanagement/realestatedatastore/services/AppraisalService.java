package webtech.realestatemanagement.realestatedatastore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import webtech.realestatemanagement.realestatedatastore.model.BaseEntity;
import webtech.realestatemanagement.realestatedatastore.model.entities.Appraisal;
import webtech.realestatemanagement.realestatedatastore.model.entities.dto.AppraisalDto;
import webtech.realestatemanagement.realestatedatastore.model.repositories.AppraisalRepository;
import webtech.realestatemanagement.realestatedatastore.services.exceptions.DataStoreException;

import java.time.ZoneId;
import java.util.Date;

@Service
@SessionScope
public class AppraisalService {

    private AppraisalRepository appraisalRepository;
    private RealEstateService realEstateService;
    private RestCommunicator restCommunicator;

    @Autowired
    public AppraisalService( AppraisalRepository appraisalRepository,
        RealEstateService realEstateService, RestCommunicator restCommunicator ) {
        this.appraisalRepository = appraisalRepository;
        this.realEstateService = realEstateService;
        this.restCommunicator = restCommunicator;
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
        final Appraisal savedAppraisal = appraisalRepository.save(appraisal);

        final AppraisalDto appraisalDto = new AppraisalDto();
        appraisalDto.setUniqueId(savedAppraisal.getUniqueId());
        appraisalDto.setRealEstateUniqueId(savedAppraisal.getRealEstateId());
        appraisalDto.setAppraisalDate(Date.from(savedAppraisal.getAppraisalDate().atZone(ZoneId.systemDefault()).toInstant()));
        appraisalDto.setAppraisedMarketValueOfBuildingsOccy(savedAppraisal.getAppraisedMarketValueOfBuildingsOccy());
        appraisalDto.setAppraisedMarketValueOfBuildingsCcy(savedAppraisal.getAppraisedMarketValueOfBuildingsCcy());
        appraisalDto.setAppraisedMarketValueOfLandOccy(savedAppraisal.getAppraisedMarketValueOfLandOccy());
        appraisalDto.setAppraisedMarketValueOfLandCcy(savedAppraisal.getAppraisedMarketValueOfLandCcy());

        try {
            System.out.println(restCommunicator.sendPostRequest("http://real-estate-recalc/notification/appraisal/add", appraisalDto));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return savedAppraisal;
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
        final Appraisal savedAppraisal = appraisalRepository.save(current);

        final AppraisalDto appraisalDto = new AppraisalDto();
        appraisalDto.setUniqueId(savedAppraisal.getUniqueId());

        try {
            System.out.println(restCommunicator.sendPostRequest("http://real-estate-recalc/notification/appraisal/remove", appraisalDto));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return savedAppraisal;
    }
}
