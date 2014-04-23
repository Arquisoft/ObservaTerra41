package model;

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
		// TODO Auto-generated constructor stub
	}
	
}
