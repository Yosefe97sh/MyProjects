package pet.care.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pet.care.app.entities.Users;

public interface UsersDao extends JpaRepository<Users, Long>{

	@Query("SELECT p FROM Users p WHERE p.username = :username"
			 + " AND p.password= :password")
	public List<Users> login(String username, String password);

	@Query("SELECT p FROM Users p WHERE p.username = :username")
	public Users findByUsername(String username);

}
