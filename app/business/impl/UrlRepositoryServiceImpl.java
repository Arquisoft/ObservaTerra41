package business.impl;

import java.util.List;

import play.db.ebean.Model.Finder;
import models.UrlRepository;
import business.UrlRepositoryService;

public class UrlRepositoryServiceImpl implements UrlRepositoryService{
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private  Finder<Long, UrlRepository> find = new Finder(Long.class,
			UrlRepository.class);

	@Override
	public List<UrlRepository> all() {
		return find.all();
	}

	@Override
	public void addURL(UrlRepository toPersists) {
		toPersists.save();
		
	}

}
