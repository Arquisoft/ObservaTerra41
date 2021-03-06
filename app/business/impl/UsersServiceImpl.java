package business.impl;

import java.util.List;

import models.User;
import play.db.ebean.Model.Finder;
import business.UsersService;

public class UsersServiceImpl implements UsersService {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Finder<Long, User> find = new Finder(Long.class, User.class);

	public List<User> findAllUsers() {
		return find.all();
	}

	public User findByUserName(String userName) {
		return find.where().eq("userName", userName).findUnique();
	}

	public void removeUser(Long id) {
		find.ref(id).delete();
	}

	public void createUser(User user) {
		if (findByUserName(user.getName()) == null) {
			user.save();
		}
	}

	public void update(User user) {
		User toUpdate = find.ref(user.getId());
		toUpdate.setEmail(user.getEmail());
		toUpdate.setName(user.getName());
		toUpdate.setPassword(user.getPassword());
		toUpdate.setSurname(user.getSurname());
		toUpdate.save();
	}


}
