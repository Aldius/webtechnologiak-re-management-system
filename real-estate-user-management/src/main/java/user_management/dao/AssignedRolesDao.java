package user_management.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import user_management.models.DAOAssignedRole;
import user_management.models.DAORole;

import java.util.Collection;

@Repository
public interface AssignedRolesDao extends CrudRepository<DAOAssignedRole, Integer> {
	DAOAssignedRole findByUserIdAndRoleId(long userId, long roleId);

	@Query("select r from DAOAssignedRole ar join DAORole r on ar.roleId = r.id where ar.userId = (:user_id)")
	Collection<DAORole> getAssignedRoles(@Param("user_id")long userId);
}