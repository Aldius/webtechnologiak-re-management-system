package user_management.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import user_management.models.DAORole;

@Repository
public interface RoleDao extends CrudRepository<DAORole, Integer> {
	DAORole findByName(String name);
}