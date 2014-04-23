package business.impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;

import persistence.Jpa;
import model.exceptions.BusinessException;

public class CommandExecuter {

	public Object execute(Command commnad) throws BusinessException {
		EntityManager em = Jpa.createEntityManager();
		EntityTransaction trx = em.getTransaction();
		trx.begin();
		Object result = null;
		try {
			result = commnad.execute();
			trx.commit();
		} catch (PersistenceException e) {

			if (trx.isActive()) {
				trx.rollback();
			}
			throw e;
		} finally {
			if (em.isOpen()) {
				em.close();
			}
		}
		return result;

	}

}
