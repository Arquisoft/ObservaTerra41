package utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.avaje.ebean.enhance.ant.StringReplace;

public class MD5Hash {
	

	/**
	 * Método que convierte una contraseña a MD5, necesario cuando se recibe una contraseña en una vista, puesto en los setter de los formularios
	 * 
	 * @param password Contraseña que se va codificar a MD5
	 * @return
	 */
	public static String codeToMD5(String password) {
		String digest = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] hash = md.digest(password.getBytes("UTF-8"));

			// convierte de byte array a Hexadecimal String
			StringBuilder sb = new StringBuilder(2 * hash.length);
			for (byte b : hash) {
				sb.append(String.format("%02x", b & 0xff));
			}

			digest = sb.toString();

		} catch (UnsupportedEncodingException ex) {
			Logger.getLogger(StringReplace.class.getName()).log(Level.SEVERE,
					null, ex);
		} catch (NoSuchAlgorithmException ex) {
			Logger.getLogger(StringReplace.class.getName()).log(Level.SEVERE,
					null, ex);
		}
		return digest;
	}



}