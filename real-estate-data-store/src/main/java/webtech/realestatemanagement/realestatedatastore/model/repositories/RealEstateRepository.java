package webtech.realestatemanagement.realestatedatastore.model.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import webtech.realestatemanagement.realestatedatastore.model.entities.RealEstate;

@Repository
public interface RealEstateRepository extends CrudRepository<RealEstate, Long> {

    Iterable<RealEstate> findAllByStatus(int status);

}
