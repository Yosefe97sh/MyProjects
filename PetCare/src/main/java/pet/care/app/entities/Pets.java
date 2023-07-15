package pet.care.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Pets {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long pid; //Pit ID
	private String name; 
	private String type;
	private String gender;
	private String description;
	@Column(length = 2000)
	private String image;
	private boolean avaliable;
	private String age;
	private String health;
	private String breed;
	
	private Long uid;
	
	public Pets() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Pets(String name, String type, String gender, String description, String image,
			boolean avaliable, String age, String health, String breed, Long uid) {
		super();
		this.name = name;
		this.type = type;
		this.gender = gender;
		this.description = description;
		this.image = image;
		this.avaliable = avaliable;
		this.age = age;
		this.health = health;
		this.breed = breed;
		this.uid = uid;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}


	public boolean isAvaliable() {
		return avaliable;
	}

	public void setAvaliable(boolean avaliable) {
		this.avaliable = avaliable;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getHealth() {
		return health;
	}

	public void setHealth(String health) {
		this.health = health;
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}
	
}