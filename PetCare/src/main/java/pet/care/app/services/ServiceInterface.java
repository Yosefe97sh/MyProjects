package pet.care.app.services;

import java.util.List;

import pet.care.app.entities.Pets;
import pet.care.app.entities.Users;

public interface ServiceInterface {

	List<Pets> listAllPets();

	Pets getPetById(Long pid);
	
	Users getUserById(Long uid);

	Pets addPet(Pets pet);

	Pets updatePet(Pets pet);

	void deletePet(Long pid);

	boolean login(String username, String password);

	Users addUser(Users user);
	
	void deleteAllPets();

	List<Pets> listAvaliablePets();

	List<Pets> listMyPets(Long uid);

	List<Pets> listMypetsByKeyword(String keyword);

	Users getUserByUsername(String username);
	
	

}
