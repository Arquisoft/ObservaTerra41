package models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;





@Entity
@DiscriminatorValue("MIEMBRO")
public class Miembro extends User{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Miembro() {
		super();

	}
	public boolean isAdmin(){
		return false;
	}
	public Miembro(String userName, String name, String surname,
			String password, String email) {
		super(userName, name, surname, password, email);
	
	}
	
}
