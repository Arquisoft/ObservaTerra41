package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import play.db.ebean.Model;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class User extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	private String userName;

	private String name;
	private String surname;
	private String password;
	private String email;

	public User(String userName, String name, String surname, String password,
			String email) {
		this.userName = userName;
		this.name = name;
		this.surname = surname;
		this.password = password;
		this.email = email;
	}

	public User() {
		super();

	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "User [userName=" + userName + ", name=" + name + ", surname="
				+ surname + ", password=" + password + ", email=" + email + "]";
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

}
