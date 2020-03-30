package user_management.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import user_management.models.DAOUser;

@Repository
public interface UserDao extends CrudRepository<DAOUser, Integer> {
	DAOUser findByUsername(String username);
}