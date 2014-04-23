package business.impl;

import model.exceptions.BusinessException;



public interface Command {
	public Object execute() throws BusinessException;
}
