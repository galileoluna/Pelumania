package util;

import java.time.LocalDate;

public class DescripcionesDefault {
	String descripcion;
	private final static String ALTA = "Dada de alta por: ";
	private final static String FECHA = " el día: ";
	
	public DescripcionesDefault(String nombreUsuario, LocalDate fecha) {
		this.descripcion = ALTA+nombreUsuario+FECHA+fecha;
	}
	
	public String getDescripcion() {
		return this.descripcion;
	}

}
