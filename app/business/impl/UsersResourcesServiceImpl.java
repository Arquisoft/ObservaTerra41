package business.impl;

import models.UsersResources;
import play.db.ebean.Model.Finder;
import business.UsersResourcesService;

public class UsersResourcesServiceImpl implements UsersResourcesService{
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static Finder<String, UsersResources> find = new Finder(String.class, UsersResources.class);

	@Override
	public void addResource(UsersResources resource) {
		resource.save();
	}

	@Override
	public UsersResources findResourceByCode(String code) {
		return find.byId(code);
	}

}
