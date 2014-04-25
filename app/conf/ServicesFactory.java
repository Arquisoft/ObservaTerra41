package conf;

import business.IndicatorSercive;
import business.UsersService;
import business.impl.IndicatorServiceImpl;
import business.impl.UsersServiceImpl;

public class ServicesFactory {
	public static UsersService getUsersService(){
		return new UsersServiceImpl();
	}
	public static IndicatorSercive getIndicatorService() {
		return new IndicatorServiceImpl();
	}
}
