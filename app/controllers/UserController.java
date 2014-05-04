package controllers;

import java.io.File;
import java.io.IOException;

import com.google.common.io.Files;

import conf.ServicesFactory;
import models.User;
import models.UsersResources;
import play.data.Form;
import play.i18n.Messages;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;
import utils.MD5Hash;
import views.html.*;
import controllers.security.SecuredUser;

@Security.Authenticated(SecuredUser.class)
public class UserController extends Controller {
	
	public static Result showUpload(){
		return ok(upload.render("none"));
	}
	
	public static Result saveFile() {
		MultipartFormData body = request().body().asMultipartFormData();
		FilePart filePart = body.getFile("fichero");
		
		String filename = filePart.getFilename();
		int index = filename.lastIndexOf(".");
		
		String type = filename.substring(index + 1, filename.length());
		
		if(index != -1)
			filename = filename.substring(0,index);
		
		String mime = filePart.getContentType();
		
		UsersResources ur = new UsersResources(Util.getSessionUser(), type, filename, mime);
		ServicesFactory.getUsersResourcesService().addResource(ur);
		
		File file = filePart.getFile();
		File newfile = new File("public/data/user/" + ur.getFilename());
		
		try {
			Files.copy(file, newfile);
		} catch (IOException e) {
			ServicesFactory.getUsersResourcesService().deleteResource(ur.getId());
			return ok(upload.render("fail"));
		}
		return ok(upload.render("success"));
	}
	
	public static Result cerrarSesion() {
		session().clear();
		return redirect(routes.Application.index());
	}

	public static Result fillRegister(String name) {
		Form<UserInfoForm> infoForm = fillForm(name);
		Form<UserPassForm> passForm = Form.form(UserPassForm.class);

		return ok(modify.render(infoForm, passForm, name));
	}

	public static Result modifyInfo(String name) {

		Form<UserInfoForm> infoForm = Form.form(UserInfoForm.class).bindFromRequest();
		
		if (infoForm.hasErrors()) {
			Form<UserPassForm> passForm = Form.form(UserPassForm.class);
			return badRequest(modify.render(infoForm, passForm, name));
		}

		User user = ServicesFactory.getUsersService().findByUserName(name);

		user.setName(infoForm.get().getName());
		user.setSurname(infoForm.get().getSurname());
		user.setEmail(infoForm.get().getEmail());

		ServicesFactory.getUsersService().update(user);
		
		return redirect(routes.UserController.fillRegister(name));
	}
	
	public static Result modifyPass(String name) {
		
		Form<UserPassForm> passForm = Form.form(UserPassForm.class).bindFromRequest();
		
		if (passForm.hasErrors()) {
			Form<UserInfoForm> infoForm = fillForm(name);
			return badRequest(modify.render(infoForm, passForm, name));
		}
		
		User user = ServicesFactory.getUsersService().findByUserName(name);

		user.setPassword(passForm.get().getPass2());

		ServicesFactory.getUsersService().update(user);
		
		return redirect(routes.UserController.fillRegister(name));
	}
	
	private static Form<UserInfoForm> fillForm(String name){
		UserInfoForm info = new UserInfoForm(ServicesFactory.getUsersService().findByUserName(name));
		return Form.form(UserInfoForm.class).fill(info);
	}

	public static class UserInfoForm{
		private String name;
		private String surname;
		private String email;
		
		public UserInfoForm() {}
		
		public UserInfoForm(User usuario){
			this.name = usuario.getName();
			this.surname = usuario.getSurname();
			this.email = usuario.getEmail();
		}
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getSurname() {
			return surname;
		}
		public void setSurname(String surname) {
			this.surname = surname;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		
		public String validate() {
			if(name.equals("") || name == null || surname.equals("") || surname == null || email.equals("") || email == null)
				return Messages.get("user.form.err5");
			
			if(!Util.validateEmail(email))
				return Messages.get("user.form.err3");
			
			return null;
		}
		
	}
	
	public static class UserPassForm{
		private String pass;
		private String pass2;
		private String pass3;
		private String name;
		
		public String getPass() {
			return pass;
		}
		public void setPass(String pass) {
			this.pass = MD5Hash.codeToMD5(pass);
		}
		public String getPass2() {
			return pass2;
		}
		public void setPass2(String pass2) {
			this.pass2 = MD5Hash.codeToMD5(pass2);
		}
		public String getPass3() {
			return pass3;
		}
		public void setPass3(String pass3) {
			this.pass3 = MD5Hash.codeToMD5(pass3);
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
		public String validate() {
			
			if(!this.pass2.equals(this.pass3))
				return Messages.get("user.form.err2");
			
			if(!Util.updateByAdmin(this.name)){
				String userPass = ServicesFactory.getUsersService().findByUserName(Util.getSessionUser()).getPassword();
				if(!userPass.equals(this.pass))
					return Messages.get("user.form.err4");
			}
			
			return null;
		}
	}
}
