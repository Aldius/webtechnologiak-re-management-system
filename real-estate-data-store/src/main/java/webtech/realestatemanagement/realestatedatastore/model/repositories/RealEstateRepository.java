package webtech.realestatemanagement.realestatedatastore.model.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import webtech.realestatemanagement.realestatedatastore.model.entities.RealEstate;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface RealEstateRepository extends CrudRepository<RealEstate, Long> {

    Iterable<RealEstate> findAllByStatus(int status);
    Optional<RealEstate> findByUniqueId(String uId);

    @Query(value = "SELECT COALESCE(MAX(data.id), 0) FROM RealEstate data")
    BigInteger findMaxId();

}
