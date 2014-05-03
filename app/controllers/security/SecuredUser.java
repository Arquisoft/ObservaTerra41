package controllers.security;

import controllers.routes;
import play.mvc.*;
import play.mvc.Http.*;

public class SecuredUser extends Security.Authenticator {

	@Override
	public String getUsername(Context ctx) {
		return ctx.session().get("user");
	}

	@Override
	public Result onUnauthorized(Context ctx) {
		return redirect(routes.Application.login());
	}

}
