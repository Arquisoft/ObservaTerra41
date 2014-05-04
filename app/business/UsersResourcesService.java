package business;

import java.util.List;

import models.UsersResources;

public interface UsersResourcesService {
	public void addResource(UsersResources resource);
	
	public UsersResources findResourceByCode(Long code);
	
	public void deleteResource(Long id);

	List<UsersResources> all();
}
