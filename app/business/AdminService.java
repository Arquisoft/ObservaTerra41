package business;

import java.util.List;

import models.Admin;

public interface AdminService {
	public List<Admin> findAllAdmins();

	public Admin findAdminByUserName(String userName);

	public void createAdmin(Admin user);

}
