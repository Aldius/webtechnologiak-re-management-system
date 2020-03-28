package hu.elte.webtechnologiak.realestaterecalc.model.repositories;

import hu.elte.webtechnologiak.realestaterecalc.model.entities.RealEstate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RealEstateRepository extends CrudRepository<RealEstate, Long> {
    Iterable<RealEstate> findAllByStatus(int status);

    Optional<RealEstate> findByUniqueId(String uniqueId);

}
