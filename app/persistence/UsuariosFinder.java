package persistence;

import java.util.List;

import javax.persistence.Query;

import model.User;

/**
 * Siguiend el modelo en n capas, un finder por tabla en bd
 * @author Sergio
 *
 */
public class UsuariosFinder {

	public static User findUsersByUsernameAndPass(String username, String pass) {
		Query query = Jpa.getManager().createNamedQuery(
				"Ususarios.findUserByIdAndPass");
		query.setParameter(1, username);
		query.setParameter(2, pass);
		@SuppressWarnings("unchecked")
		List<User> results = query.getResultList();
		if (!results.isEmpty()) {
			return results.get(0);
		}

		return null;
	}

}
