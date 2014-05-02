package business.impl;

import java.util.List;

import conf.ServicesFactory;
import play.db.ebean.Model.Finder;
import models.Miembro;
import business.MiembroService;

public class MiembroServiceImpl implements MiembroService {
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Finder<String, Miembro> find = new Finder(Long.class, Miembro.class);

	@Override
	public List<Miembro> findAllMiembros() {
		return find.all();
	}

	@Override
	public Miembro findMiembroByUserName(String userName) {
		return find.where().eq("userName", userName).findUnique();
	}

	@Override
	public void createMiembro(Miembro user) {

		if (ServicesFactory.getUsersService().findByUserName(user.getName()) == null) {
			user.save();
		}

	}

}
