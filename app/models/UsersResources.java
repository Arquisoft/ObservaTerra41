package models;

import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.databind.JsonNode;

import conf.ServicesFactory;
import play.db.ebean.Model;
import play.libs.Json;

public class UsersResources extends Model {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5909556816644548831L;

	@Id
	private String code;
	 
	@ManyToOne
	private User owner;

	
	private String filename;

	public UsersResources(String username, String filename) {
		this.owner = User.find.ref(ServicesFactory.getUsersService()
				.findByUserName(username).getId());
		this.filename = filename;
		this.code = generatedCode(filename);
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getCode() {
		return code;
	}

	private String generatedCode(String filename){
		return owner.getUserName()+filename;
	}
	
	public JsonNode toJson() {
		return Json.toJson(this);
	}
	
}
