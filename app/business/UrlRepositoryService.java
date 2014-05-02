package business;

import java.util.List;

import models.UrlRepository;

public interface UrlRepositoryService {
	public List<UrlRepository> all();
	public void addURL(UrlRepository toPersists);
}
