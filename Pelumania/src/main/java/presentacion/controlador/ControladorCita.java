package presentacion.controlador;

import modelo.Sistema;
import presentacion.vista.nuevaVentanaCita;

public class ControladorCita {
	private Sistema sistema;
	private nuevaVentanaCita ventanaCita;
	private static ControladorCita INSTANCE;
	
	public ControladorCita(Sistema s) {
		this.ventanaCita = nuevaVentanaCita.getInstance();
		this.sistema = s;
	}
	
	public static ControladorCita getInstance(Sistema sistema) {
		if ( INSTANCE == null) {
			INSTANCE = new ControladorCita(sistema);
		}
		return INSTANCE;
	}
	
}
