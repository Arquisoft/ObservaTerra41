package business.impl.user;

import persistence.UsuariosFinder;
import model.User;
import model.exceptions.BusinessException;
import business.impl.Command;



public class FindUserByUsernameAndPass implements Command{
	private String username;
	private String pass;

	public FindUserByUsernameAndPass(String username, String pass) {
		this.username=username;
		this.pass = pass;
	}

	@Override
	public User execute() throws BusinessException {
		
		return UsuariosFinder.findUsersByUsernameAndPass(username,pass);
	}




}
