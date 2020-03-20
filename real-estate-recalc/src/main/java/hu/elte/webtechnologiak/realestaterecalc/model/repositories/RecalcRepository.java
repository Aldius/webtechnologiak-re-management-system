package hu.elte.webtechnologiak.realestaterecalc.model.repositories;

import hu.elte.webtechnologiak.realestaterecalc.model.entities.RealEstate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecalcRepository extends CrudRepository<RealEstate, Long> {

}


