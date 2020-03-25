package user_management.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import user_management.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
