package webtech.realestatemanagement.realestatedatastore.model.repositories;

import org.springframework.data.repository.CrudRepository;
import webtech.realestatemanagement.realestatedatastore.model.entities.RealEstate;

public interface RealEstateRepository extends CrudRepository<RealEstate, Long> {
}
