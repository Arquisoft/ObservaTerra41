package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;

@Entity
public class User extends Model {

	@Id
	public String userName;

	public String name;
	public String surname;
	public String password;
	public String email;

	public User(String userName, String name, String surname, String password,
			String email) {
		this.userName = userName;
		this.name = name;
		this.surname = surname;
		this.password = password;
		this.email = email;
	}

	public static Finder<String, User> find = new Finder(String.class, User.class);

	public static List<User> all() {
		return find.all();
	}
	
	public static User findByUserName(String userName){
		return find.byId(userName);
	}

	public static void remove(String userName) {
		find.ref(userName).delete();
	}

	public static void create(User user) {
		if (findByUserName(user.userName) == null) {
			user.save();
		}
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public static void update(User user){
		User toUpdate = find.ref(user.getUserName());
		toUpdate.setEmail(user.getEmail());
		toUpdate.setName(user.getName());
		toUpdate.setPassword(user.getPassword());
		toUpdate.setSurname(user.getSurname());
		toUpdate.save();
	}
	
	public static void addUser(User user){
		user.save();
	}
	
	
}
