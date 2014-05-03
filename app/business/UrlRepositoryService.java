package business;

import java.util.List;

import models.UrlRepository;

public interface UrlRepositoryService {
	public List<UrlRepository> all();
	public void addURL(UrlRepository toPersists);
	public void deleteUrl(Long id);
	public void create(UrlRepository url);
	public  UrlRepository findByUrl(String url);
	public void update(UrlRepository url);
}
