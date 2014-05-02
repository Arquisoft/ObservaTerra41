package business;

import java.util.List;

import models.Observation;

public interface ObservationService {

	public List<Observation> all();

	public void addObservation(Observation ob);

	public void delete(Long id);

	public void deleteAll();

	public List<Observation> filterByIndicatorName(String indicatorName,
			List<Observation> observations);

	public List<Observation> findByIndicatorName(String indicatorCode);

	public List<Observation> findByCountryCode(String countryCode);

	public Observation findByCountryIndicator(String countryCode,
			String indicatorCode);

	public Observation findById(Long id);

}
