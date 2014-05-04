package models;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.databind.JsonNode;

import conf.ServicesFactory;
import play.db.ebean.Model;
import play.libs.Json;

@Entity
public class UsersResources extends Model {
	
	private static final long serialVersionUID = -5909556816644548831L;

	@Id
	@GeneratedValue
	private Long id;
	 
	@ManyToOne
	private User owner;
	
	private Date uploadDate;
	private String type;
	private String name;
	private String contentType;
	
	public UsersResources(String username, String type, String name, String mime) {
		this.owner = ServicesFactory.getUsersService().findByUserName(username);
		this.uploadDate = Calendar.getInstance().getTime();
		this.type = type;
		this.name = name;
		this.contentType = mime;
	}
	
	public String getContentType() {
		int index = contentType.lastIndexOf("/");
		String tipo = contentType.substring(0,index);

		if(tipo.equals("audio"))
			return "audio/mpeg";
		else
			return contentType;
	}

	public boolean isAudioVideo(){
		int index = contentType.lastIndexOf("/");
		String tipo = contentType.substring(0,index);
		
		return tipo.equals("audio") || tipo.equals("video");
	}
	
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public String getFilename() {
		return id + "." + type;
	}
	
	public String getFilePath(){
		return "data/user/" + getFilename();
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public Long getId() {
		return id;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public JsonNode toJson() {
		return Json.toJson(this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsersResources other = (UsersResources) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		return true;
	}
	
}
