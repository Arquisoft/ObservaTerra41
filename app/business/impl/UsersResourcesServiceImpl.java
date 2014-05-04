package business.impl;

import java.util.List;

import models.UsersResources;
import play.db.ebean.Model.Finder;
import business.UsersResourcesService;

public class UsersResourcesServiceImpl implements UsersResourcesService{
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static Finder<Long, UsersResources> find = new Finder(Long.class, UsersResources.class);

	@Override
	public void addResource(UsersResources resource) {
		resource.save();
	}

	@Override
	public UsersResources findResourceByCode(Long id) {
		return find.byId(id);
	}
	
	@Override
	public void deleteResource(Long id) {
		find.ref(id).delete();
		
	}
	
	@Override
	public List<UsersResources> all() {
		return find.order().desc("uploadDate").findList();
	}

}
