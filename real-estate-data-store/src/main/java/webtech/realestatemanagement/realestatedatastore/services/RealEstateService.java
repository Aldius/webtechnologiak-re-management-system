package webtech.realestatemanagement.realestatedatastore.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.annotation.SessionScope;
import webtech.realestatemanagement.realestatedatastore.model.BaseEntity;
import webtech.realestatemanagement.realestatedatastore.model.entities.RealEstate;
import webtech.realestatemanagement.realestatedatastore.model.repositories.RealEstateRepository;
import webtech.realestatemanagement.realestatedatastore.services.exceptions.DataStoreException;

@Service
@SessionScope
public class RealEstateService {

    private RealEstateRepository realEstateRepository;
    private RestCommunicator restCommunicator;

    @Autowired
    public RealEstateService(RealEstateRepository realEstateRepository, RestCommunicator restCommunicator) {
        this.realEstateRepository = realEstateRepository;
        this.restCommunicator = restCommunicator;
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

    public RealEstate findByUId(String uId) throws DataStoreException {
        return realEstateRepository.findByUniqueId(uId)
                .orElseThrow(() -> new DataStoreException("Real estate by unique id " + uId + " not found!"));
    }

    public RealEstate addRealEstate(RealEstate realEstate) {
        realEstate.setUniqueId("RE" + realEstateRepository.findMaxId().longValue());
        realEstate = realEstateRepository.save(realEstate);

        try {
            System.out.println(restCommunicator.sendPostRequest("http://real-estate-recalc/realEstate/add", realEstate));
            restCommunicator.sendPostRequest("http://real-estate-document-handling/DataStoreEntity/add", realEstate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return realEstate;
    }

    public RealEstate updateRealEstate(RealEstate realEstate) throws DataStoreException {
        RealEstate current = findByUId(realEstate.getUniqueId());
        realEstate.setVersion(current.getVersion());
        realEstate = realEstateRepository.save(realEstate);

        try {
            restCommunicator.sendPutRequest("http://real-estate-recalc/realEstate/update", realEstate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return realEstate;
    }

    public RealEstate deleteRealEstate(String uId) throws DataStoreException {
        RealEstate current = findByUId(uId);
        current.setStatus(BaseEntity.INACTIVE_ENTITY_STATUS);
        current = realEstateRepository.save(current);

        try {
            restCommunicator.sendPutRequest("http://real-estate-recalc/realEstate/delete", current);
            restCommunicator.sendPostRequest("http://real-estate-document-handling/DataStoreEntity/delete", current);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return current;
    }
}
