package webtech.realestatemanagement.realestatedatastore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import webtech.realestatemanagement.realestatedatastore.model.entities.RealEstate;
import webtech.realestatemanagement.realestatedatastore.model.repositories.RealEstateRepository;

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
}
