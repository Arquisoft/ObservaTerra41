package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.databind.JsonNode;

import play.db.ebean.Model;
import play.libs.Json;

@Entity
public class UrlRepository extends Model {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9086824113934632311L;
	@Id
	@GeneratedValue
	private Long id;
	private String url;
	private String provider;

	public UrlRepository(String url,String provider) {
		super();
		this.url = url;
		this.provider = provider;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		UrlRepository other = (UrlRepository) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getId() {
		return id;
	}

	public boolean isAdmin() {
		return false;
	}
	
	public JsonNode toJson() {
		return Json.toJson(this);
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

}
