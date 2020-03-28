package webtech.realestatemanagement.realestatedatastore.model.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import webtech.realestatemanagement.realestatedatastore.model.entities.Appraisal;

import java.math.BigInteger;
import java.util.Optional;

public interface AppraisalRepository extends CrudRepository<Appraisal, Long> {

    Iterable<Appraisal> findAllByStatus(int status);
    Iterable<Appraisal> findAllByRealEstateId(String realEstateId);

    Optional<Appraisal> findByUniqueId(String uId);

    @Query(value = "SELECT COALESCE(MAX(data.id), 0) FROM Appraisal data")
    BigInteger findMaxId();
}
