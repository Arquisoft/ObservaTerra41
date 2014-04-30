package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class Observation extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = -960050308782230568L;

	@Id
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


	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected static Finder<Long, Observation> find = new Finder(Long.class,
			Observation.class);

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
		return "Observation [id=" + id + ", obsValue=" + obsValue
				+ ", country=" + country + ", indicator=" + indicator + "]";
	}
	
	

}