package models;

import play.db.ebean.*;
import play.data.validation.Constraints.Required;
import javax.persistence.*;

@Entity
public class Indicator extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7091678348041541147L;


	@Id
	private String code;

	@Required
	private String name;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected static Finder<String, Indicator> find = new Finder(String.class,
			Indicator.class);

	public String getName() {
		return name;
	}
	

	public Indicator() {
		super();
	}

	@Override
	public String toString() {
		return "Indicator [code=" + code + ", name=" + name + "]";
	}




	public void setName(String name) {
		this.name = name;
	}



	public String getCode() {
		return code;
	}

	public Indicator(String name) {
		this.name = name;
		this.code = generatedId(name);
	}
	
	private String generatedId(String name){
		char[] code = name.toCharArray();
		String co ="";
		for(char c : code){
			if(c != ' '){
				co+=c;
			}
		}
		return co;
	}

	

}