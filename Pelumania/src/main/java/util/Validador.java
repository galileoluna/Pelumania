package util;

import java.util.regex.Pattern;

/**
 * 
 * @author Tomi
 * Clase estatica para validar
 * inputs 
 *
 */

public class Validador {
	
		@SuppressWarnings("static-access")
		public static boolean esNombreValido(String nombre) {
			String NOMBRE_REGEX = "[A-Za-z]{3,45}$";
			Pattern NOMBRE_PATTERN = Pattern.compile(NOMBRE_REGEX);
			return NOMBRE_PATTERN.matches(NOMBRE_REGEX, nombre);
		}


		@SuppressWarnings("static-access")
		public static boolean esMail(String email) {
			String MAIL_REGEX =
					"^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*" +
					"@" + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
			
			Pattern MAIL_PATTERN = Pattern.compile(MAIL_REGEX);
			
			return MAIL_PATTERN.matches(MAIL_REGEX, email);
		}
		
		@SuppressWarnings("static-access")
		public static boolean esTelefono(String string) {
			String TELEFONO_REGEX =
					"\\d{1,11}";

			Pattern TELEFONO_PATTERN = Pattern.compile(TELEFONO_REGEX);
			return TELEFONO_PATTERN.matches(TELEFONO_REGEX, string);
		}
}
