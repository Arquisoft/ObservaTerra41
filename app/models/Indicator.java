package models;

import play.db.ebean.*;
import play.data.validation.Constraints.*;

import javax.persistence.*;

@Entity
public class Indicator extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7091678348041541147L;


	@Id
	private String code;

	public String getName() {
		return name;
	}
	



	public Indicator() {
		super();
		// TODO Auto-generated constructor stub
	}




	public void setName(String name) {
		this.name = name;
	}



	public String getCode() {
		return code;
	}



	@Required
	private String name;

	public Indicator(String code, String name) {
		this.code = code;
		this.name = name;
	}
	
	

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected static Finder<String, Indicator> find = new Finder(String.class,
			Indicator.class);

	

}