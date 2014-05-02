package models;



import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;







@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Admin() {
		super();
	}

	public Admin(String userName, String name, String surname, String password,
			String email) {
		super(userName, name, surname, password, email);
	}

	public boolean isAdmin(){
		return true;
	}

	

}
