package pet.care.app.dao;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pet.care.app.entities.Pets;

public interface PetsDao extends JpaRepository<Pets,Long>{
	
	@Query("SELECT p FROM Pets p WHERE p.avaliable = true")
	public List<Pets> listAvaliablePets();
	
	@Query("SELECT p FROM Pets p WHERE p.uid = :uid")
	public List<Pets> listMyPets(Long uid);

	@Query("SELECT p FROM Pets p WHERE p.name LIKE %?1%"
            + " OR p.type LIKE %?1%")
	public List<Pets> search(String keyword);
}