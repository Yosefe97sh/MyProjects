package pet.care.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pet.care.app.dao.PetsDao;
import pet.care.app.dao.UsersDao;
import pet.care.app.entities.Pets;
import pet.care.app.entities.Users;

@Service
public class Services implements ServiceInterface{
	
	@Autowired
	PetsDao pd;
	
	@Autowired
	UsersDao ud;
	
	@Override
	public List<Pets> listAllPets() {
		return pd.listAvaliablePets();
	}
	
	@Override
	public Pets getPetById(Long pid) {
		return pd.findById(pid).get();
		}
	
	//sddsdsdksldkklddddddddddddddddddddddddddddddddddd
	@Override
	public Users getUserByUsername(String username) {
		return ud.findByUsername(username);
		}
	
	@Override
	public Users getUserById(Long uid) {
		return ud.findById(uid).get();
	}

	@Override
	public Pets addPet(Pets pet) {
		return pd.save(pet);
	}
	@Override
	public Pets updatePet(Pets pet){
        return pd.save(pet);
    }

	@Override
    public void deletePet(Long pid){
        pd.deleteById(pid);
    }
	
	@Override
	public boolean login(String username, String password) {
		return ud.login(username,password).isEmpty();
	}

	@Override
	public Users addUser(Users user) {
		return ud.save(user);
	}
	
	@Override
	public void deleteAllPets() {
		pd.deleteAll();
	}
	@Override
	public List<Pets> listAvaliablePets(){
		return pd.listAvaliablePets();
	}
	@Override
	public List<Pets> listMyPets(Long uid){
		return pd.listMyPets(uid);
	}
	@Override
	public List<Pets> listMypetsByKeyword(String keyword){
		return pd.search(keyword);
	}
}
