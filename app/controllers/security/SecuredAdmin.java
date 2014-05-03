package controllers.security;

import controllers.Util;
import controllers.routes;
import play.mvc.*;
import play.mvc.Http.*;

public class SecuredAdmin extends Security.Authenticator {

	@Override
	public String getUsername(Context ctx) {
		if(!Util.isAdmin())
			return null;
		else
			return Util.isAdmin() + "";
	}

	@Override
	public Result onUnauthorized(Context ctx) {
		return redirect(routes.Application.login());
	}

}