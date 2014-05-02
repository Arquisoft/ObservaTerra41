package controllers;

import play.mvc.*;
import play.mvc.Http.*;

public class SecuredAdmin extends Security.Authenticator {

	@Override
	public String getUsername(Context ctx) {
		return ctx.session().get("admin");
	}

	@Override
	public Result onUnauthorized(Context ctx) {
		return redirect(routes.Application.login());
	}

}