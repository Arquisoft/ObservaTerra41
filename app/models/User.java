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
	
}
