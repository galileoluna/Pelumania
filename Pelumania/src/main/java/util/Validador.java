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
		public static boolean esNombreConEspaciosValido(String nombre) {	
			String NOMBRE_REGEX = "[a-zA-Z ñ]+";
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


		@SuppressWarnings("static-access")
		public static boolean esPuntosValido(String puntos) {
			String PUNTOS_REGEX =
					"\\d{1,4}";
			Pattern PUNTOS_PATTERN = Pattern.compile(PUNTOS_REGEX);
			return PUNTOS_PATTERN.matches(PUNTOS_REGEX, puntos);
		}
		
		
		@SuppressWarnings("static-access")
		public static boolean esPrecioValido(String precio) {
			String PRECIO_REGEX=
					"^[0-9]+(\\.[0-9]{1,2})?$";
			Pattern PRECIO_PATTERN = Pattern.compile(PRECIO_REGEX);
			return PRECIO_PATTERN.matches(PRECIO_REGEX, precio);
		}
		
		public static boolean esEstadoServicioValido(String estado) {
			String input = estado.toLowerCase();
			String[] ESTADOS = {"activo", "inactivo"};
			
			for (String  e : ESTADOS) {
				if (e.equals(estado)){
					return true;
				}
			}
			return false;
		}
		
		@SuppressWarnings("static-access")
		public static boolean esNumeroSucursalValido(String numero) {
			String PRECIO_REGEX=
					"^[0-9]+(\\.[0-9]{1,2})?$";
			Pattern PRECIO_PATTERN = Pattern.compile(PRECIO_REGEX);
			return PRECIO_PATTERN.matches(PRECIO_REGEX, numero);
		}
		
		public static boolean esEstadoClienteValido(String estado) {
			String[] ESTADOS = {"activo","inactivo","moroso","vip"};
			for (String  e : ESTADOS) {
				if (e.equals(estado)){
					return true;
				}
			}
			return false;
		}
		
		public static boolean esEstadoSucursalValido(String estado) {
			String input = estado.toLowerCase();
			String[] ESTADOS = {"activo", "inactivo"};
			
			for (String  e : ESTADOS) {
				if (e.equals(estado)){
					return true;
				}
			}
			return false;
		}

}
