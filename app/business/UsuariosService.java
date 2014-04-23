package business;



import model.User;
import model.exceptions.BusinessException;

public interface UsuariosService {
	public User findUserByUsernameAndPass(String username,String pass) throws BusinessException;
}
