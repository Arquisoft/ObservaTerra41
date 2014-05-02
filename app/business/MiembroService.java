package business;

import java.util.List;

import models.Miembro;


public interface MiembroService {
	public List<Miembro> findAllMiembros();

	public Miembro findMiembroByUserName(String userName);

	public void createMiembro(Miembro user);

}
