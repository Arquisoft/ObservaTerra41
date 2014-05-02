package business;

import models.UsersResources;

public interface UsersResourcesService {
	public void addResource(UsersResources resource);
	
	public UsersResources findResourceByCode(String code);
}
