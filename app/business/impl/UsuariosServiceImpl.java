package business.impl;



import model.User;
import model.exceptions.BusinessException;
import business.UsuariosService;
import business.impl.user.FindUserByUsernameAndPass;

public class UsuariosServiceImpl implements UsuariosService{


	@Override
	public User findUserByUsernameAndPass(String username,String pass) throws BusinessException {
		return (User) new CommandExecuter().execute(new FindUserByUsernameAndPass(username,pass));
	}

}
