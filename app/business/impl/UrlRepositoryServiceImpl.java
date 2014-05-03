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

	@Override
	public void deleteUrl(Long id) {
		find.ref(id).delete();
		
	}

	@Override
	public void create(UrlRepository url) {
		if (findByUrl(url.getUrl()) == null) {
			url.save();
		}		
	}
	
	public  UrlRepository findByUrl(String url) {
		return find.where().eq("url", url).findUnique();
	}

	@Override
	public void update(UrlRepository url) {
		UrlRepository toUpdate = find.ref(url.getId());
		toUpdate.setUrl(url.getUrl());
		toUpdate.save();
		
	}

}
