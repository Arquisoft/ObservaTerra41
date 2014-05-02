package business;

import java.util.List;

import models.User;

public interface UsersService {
	public List<User> findAllUsers();

	public User findByUserName(String userName);

	public void removeUser(Long id);

	public void createUser(User user);

	public void update(User user);

}
