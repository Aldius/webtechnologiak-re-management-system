package webtech.RealEstateManagementSystemDataStore.model.repositories;

import org.springframework.data.repository.CrudRepository;
import webtech.RealEstateManagementSystemDataStore.model.entities.RealEstate;

public interface RealEstateRepository extends CrudRepository<RealEstate, Long> {
}
