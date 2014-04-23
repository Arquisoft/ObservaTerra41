package conf;

import business.UsuariosService;
import business.impl.UsuariosServiceImpl;





public class ServicesFactory {
	public static UsuariosService getUsuariosService() {
		return new UsuariosServiceImpl();
	}
}
