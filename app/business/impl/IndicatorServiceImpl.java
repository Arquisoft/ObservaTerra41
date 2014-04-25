package business.impl;

import java.util.List;

import play.db.ebean.Model.Finder;
import models.Indicator;
import business.IndicatorSercive;

public class IndicatorServiceImpl implements IndicatorSercive {
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static Finder<String, Indicator> find = new Finder(String.class,
			Indicator.class);

	public void create(Indicator indicator) {
		if (findByName(indicator.name) == null) {
			indicator.save();
		}
	}

	public List<Indicator> all() {
		return find.all();
	}

	public void remove(String id) {
		find.ref(id).delete();
	}

	public Indicator findByName(String name) {
		return find.where().eq("name", name).findUnique();
	}

	public Indicator findByCode(String code) {
		return find.byId(code);
	}

	public void deleteAll() {
		for (Indicator ind : all())
			ind.delete();
	}

}
