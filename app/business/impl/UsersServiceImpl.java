package business.impl;

import java.util.List;

import models.User;
import play.db.ebean.Model.Finder;
import business.UsersService;

public class UsersServiceImpl implements UsersService {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Finder<String, User> find = new Finder(String.class, User.class);

	public List<User> findAllUsers() {
		return find.all();
	}

	public User findByUserName(String userName) {
		return find.byId(userName);
	}

	public void removeUser(String userName) {
		find.ref(userName).delete();
	}

	public void createUser(User user) {
		if (findByUserName(user.getName()) == null) {
			user.save();
		}
	}

	public void update(User user) {
		User toUpdate = find.ref(user.getUserName());
		toUpdate.setEmail(user.getEmail());
		toUpdate.setName(user.getName());
		toUpdate.setPassword(user.getPassword());
		toUpdate.setSurname(user.getSurname());
		toUpdate.save();
	}



}
