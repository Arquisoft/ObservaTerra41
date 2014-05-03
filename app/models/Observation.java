package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class Observation extends Model {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result
				+ ((indicator == null) ? 0 : indicator.hashCode());
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
		Observation other = (Observation) obj;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (indicator == null) {
			if (other.indicator != null)
				return false;
		} else if (!indicator.equals(other.indicator))
			return false;
		return true;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -960050308782230568L;

	@Id@GeneratedValue
	private Long id;
	

	@Required
	private Double obsValue;

	@ManyToOne
	private Country country;

	@ManyToOne
	private Indicator indicator;

	public Double getObsValue() {
		return obsValue;
	}

	public void setObsValue(Double obsValue) {
		this.obsValue = obsValue;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Indicator getIndicator() {
		return indicator;
	}

	public void setIndicator(Indicator indicator) {
		this.indicator = indicator;
	}

	public Long getId() {
		return id;
	}


	

	public Observation(Country country, Indicator indicator, Double value) {
		this.country = country;
		this.indicator = indicator;
		this.obsValue = value;
	}

	public Observation(String countryCode, String indicatorCode, Double value) {
		this.country = Country.find.ref(countryCode);
		this.indicator = Indicator.find.ref(indicatorCode);
		this.obsValue = value;
	}

	
	public static Double average(List<Observation> observations) {
		Double sum = 0.0;
		for (Observation obs : observations) {
			sum += obs.obsValue;
		}
		return sum / observations.size();
	}

	
	@Override
	public String toString() {
		return "Observation [obsValue=" + obsValue + ", country=" + country
				+ ", indicator=" + indicator + "]";
	}
	
	

}