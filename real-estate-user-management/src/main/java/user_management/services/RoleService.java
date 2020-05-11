package user_management.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import user_management.dao.AssignedRolesDao;
import user_management.dao.RoleDao;
import user_management.dao.UserDao;
import user_management.exceptions.RoleNotFoundException;
import user_management.exceptions.UserNotFoundException;
import user_management.models.DAOAssignedRole;
import user_management.models.DAORole;
import user_management.models.DAOUser;

import java.util.Collection;

@Service
public class RoleService {
	@Autowired
	private RoleDao roleDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private AssignedRolesDao assignedRolesDao;

	public void assignRole(String roleName, String username) throws UserNotFoundException, RoleNotFoundException {
		DAOUser user = userDao.findByUsername(username);

		if (user == null) {
			throw new UserNotFoundException();
		}

		DAORole role = roleDao.findByName(roleName);

		if (role == null) {
			throw new RoleNotFoundException();
		}

		DAOAssignedRole assignedRole = assignedRolesDao.findByUserIdAndRoleId(user.getId(), role.getId());

		if (assignedRole == null) {
			assignedRole = new DAOAssignedRole();
			assignedRole.setUserId(user.getId());
			assignedRole.setRoleId(role.getId());

			assignedRolesDao.save(assignedRole);
		}
	}

	public void removeRole(String roleName, String username) throws UserNotFoundException, RoleNotFoundException {
		DAOUser user = userDao.findByUsername(username);

		if (user == null) {
			throw new UserNotFoundException();
		}

		DAORole role = roleDao.findByName(roleName);

		if (role == null) {
			throw new RoleNotFoundException();
		}

		DAOAssignedRole assignedRole = assignedRolesDao.findByUserIdAndRoleId(user.getId(), role.getId());

		if (assignedRole != null) {
			assignedRolesDao.delete(assignedRole);
		}
	}

	public Collection<DAORole> getAll(String username) throws UserNotFoundException {
		DAOUser user = userDao.findByUsername(username);

		if (user == null) {
			throw new UserNotFoundException();
		}

		return assignedRolesDao.getAssignedRoles(user.getId());
	}
}