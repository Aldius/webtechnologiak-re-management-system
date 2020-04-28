package hu.elte.webtechnologiak.realestaterecalc.model.repositories;

import hu.elte.webtechnologiak.realestaterecalc.model.entities.Appraisal;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AppraisalRepository extends CrudRepository<Appraisal, Long> {

	Optional<Appraisal> findByUniqueId( String uniqueId );

}
