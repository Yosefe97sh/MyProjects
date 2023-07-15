package pet.care.app.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import pet.care.app.entities.Login;
import pet.care.app.entities.Pets;
import pet.care.app.entities.Users;
import pet.care.app.services.ServiceInterface;

@Controller
public class PetCareController {
	
	@Autowired
	ServiceInterface si;
	
	Users mainUser = null;//sdsdsdsds
	String message = "";

	@GetMapping("/login")
	public String homePage(Model model) {
		Login login= new Login();
		model.addAttribute("login",login);
		return "login";
	}
	@PostMapping("/login")
	public String login(@ModelAttribute("login") Login login) {
		if(si.login(login.getUsername(), login.getPassword())) {
			return "redirect:/login";
		}
		mainUser = si.getUserByUsername(login.getUsername());
		return "redirect:/mypets";
	}
	
	@GetMapping("/register")
	public String registerPage(Model model){
		Users user = new Users();
		model.addAttribute("message", message);
		model.addAttribute("register", user);
		return "register";
	}
	
	@GetMapping("/mypets")
	public String myPets(Model model){
		if(mainUser == null) {
        	return "redirect:/login";
        }
		List<Pets> pets = si.listMyPets(mainUser.getUid());
		model.addAttribute("pets", pets);
		model.addAttribute("user", mainUser);
		return "mypets";
	}
	
	@PostMapping("/users")public String saveUser(@ModelAttribute("user") Users user) {
		if(si.getUserByUsername(user.getUsername()) != null) {
			message = "Username already exists";
			return "redirect:/register";
		}
		message = "";
		si.addUser(user);
		return "redirect:/pets";
	}

	@GetMapping("/pets")
    public String listPets(Model model) {
        model.addAttribute("pets", si.listAllPets());
        mainUser = null;
        return "pets";
    }

    @GetMapping("/pets/new")
    public String createPetForm(Model model) {
        if(mainUser == null) {
        	return "redirect:/login";
        }
        Pets pet = new Pets();
        model.addAttribute("pet", pet);
        return "add_pet";

    }

    @PostMapping("/pets")
    public String savePet(@ModelAttribute("pet") Pets pet) {
    	pet.setUid(mainUser.getUid());
        si.addPet(pet);
        si.updatePet(pet);
        return "redirect:/mypets";
    }
    
    @GetMapping("/pets/edit/{pid}")
	public String editPet(@PathVariable Long pid, Model model) {
    	Pets pet = si.getPetById(pid);
    	if(mainUser == null) {
        	return "redirect:/login";
        } else if(pet.getUid() != mainUser.getUid()) {
        	return "redirect:/login";
        }
		model.addAttribute("pet", pet);
		return "edit_pets";
	}
    
    @PostMapping("/pets/save/{pid}")
	public String updatePet(@PathVariable Long pid, Model model, @ModelAttribute("pet") Pets pet) {
    	Pets existingPet = si.getPetById(pid);
    	existingPet.setName(pet.getName());
    	existingPet.setType(pet.getType());
    	existingPet.setGender(pet.getGender());
    	existingPet.setDescription(pet.getDescription());
    	existingPet.setImage(pet.getImage());
    	existingPet.setAvaliable(pet.isAvaliable());
    	existingPet.setAge(pet.getAge());
    	existingPet.setHealth(pet.getHealth());
    	existingPet.setBreed(pet.getBreed());
    	
    	si.updatePet(existingPet);
    	return "redirect:/mypets";		
	}
    
    @GetMapping("/pets/{pid}")
	public String viewPet(@PathVariable Long pid, Model model) {
    	Pets pet = si.getPetById(pid);
		model.addAttribute("pet", pet);
		model.addAttribute("user", si.getUserById(pet.getUid()));
		return "view_pet";		
	}
    
    
    @GetMapping("/pets/delete/{pid}")
	public String deletePet(@PathVariable Long pid) {
    	Pets pet = si.getPetById(pid);
    	if(mainUser == null) {
        	return "redirect:/login";
        } else if(pet.getUid() != mainUser.getUid()) {
        	return "redirect:/login";
        }
		si.deletePet((pid));
		return "redirect:/mypets";
	}
    
    @GetMapping("/pets/test")
	public String addTestData() {
    	si.addPet(new Pets(
    			"JJ",
    			"Cat",
    			"Male",
    			"This is a detaild description about the cat",
    			"https://dl5zpyw5k3jeb.cloudfront.net/photos/pets/45554189/1/?bust=1565314793&width=316",
    			true,
    			"5",
    			"Good",
    			"IDK",
    			(long) 1));
    	si.addPet(new Pets(
    			"Rox",
    			"Cat",
    			"Male",
    			"",
    			"https://dl5zpyw5k3jeb.cloudfront.net/photos/pets/57372850/1/?bust=1663325993&width=316",
    			true,
    			"",
    			"",
    			"",
    			(long) 2));
    	si.addPet(new Pets(
    			"Rox2",
    			"Cat",
    			"Male",
    			"",
    			"https://dl5zpyw5k3jeb.cloudfront.net/photos/pets/57372850/1/?bust=1663325993&width=316",
    			false,  
    			"",
    			"",
    			"",
    			(long) 3));
    	si.addPet(new Pets(
    			"Joe",
    			"Cat",
    			"Male",
    			"",
    			"https://dl5zpyw5k3jeb.cloudfront.net/photos/pets/57747100/1/?bust=1665153081&width=316",
    			true,
    			"",
    			"",
    			"",
    			(long) 2));
    	return "redirect:/pets";
	}
    
    @GetMapping("/pets/test/delete")
	public String deleteTestData() {
    	si.deleteAllPets();
    	return "redirect:/pets";
    }
}


