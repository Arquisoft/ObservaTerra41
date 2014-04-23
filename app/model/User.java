package model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	protected Long id;
	protected String user;
	protected String name;
	protected String surname;
	protected String mail;
	protected String pass;
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}



	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}



	public void setUser(String user) {
		this.user = user;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}



	public void setMail(String mail) {
		this.mail = mail;
	}

	

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}



	public String getUser() {
		return user;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getMail() {
		return mail;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}



	@Override
	public String toString() {
		return "User [id=" + id + ", user=" + user + ", name=" + name
				+ ", surname=" + surname + ", mail=" + mail + ", pass=" + pass
				+ "]";
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

}
