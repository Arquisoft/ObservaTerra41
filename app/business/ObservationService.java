package business;


import java.util.List;



import models.Observation;


public interface ObservationService {
	
	public  List<Observation> all();
	
	public  void  create(String countrycode, String indicatorkey, Double value) ;

	public  void delete(Long id) ;

	public  void deleteAll() ;
	
	
	public List<Observation> filterByIndicatorName(String indicatorName,
			List<Observation> observations) ;

	public  List<Observation> findByIndicatorName(String indicatorCode);

	public  List<Observation> findByCountryCode(String countryCode);
	
	public Observation findById(Long id);

}
