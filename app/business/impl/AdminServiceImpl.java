package business.impl;

import java.util.List;

import conf.ServicesFactory;
import play.db.ebean.Model.Finder;
import models.Admin;
import business.AdminService;

public class AdminServiceImpl implements AdminService {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Finder<String, Admin> find = new Finder(Long.class, Admin.class);

	@Override
	public List<Admin> findAllAdmins() {
		return find.all();
	}

	@Override
	public Admin findAdminByUserName(String userName) {
		return find.where().eq("userName", userName).findUnique();
	}

	@Override
	public void createAdmin(Admin user) {
		if (ServicesFactory.getUsersService().findByUserName(user.getName()) == null) {
			user.save();
		}

	}

}
