package presentacion.controlador;

import modelo.Sistema;
import presentacion.vista.VentanaEditarCita;

public class ControladorEditarCita {

	private VentanaEditarCita ventanaEditarCita;
	private Sistema sistema;
	private ControladorEditarCita controladorEditarCita;
	private static ControladorEditarCita INSTANCE;

	private ControladorEditarCita(Sistema sistema) {
		this.ventanaEditarCita = VentanaEditarCita.getInstance();
		this.sistema = sistema;
	}

	public static ControladorEditarCita getInstance(Sistema sistema) {
		if ( INSTANCE == null) {
			INSTANCE = new ControladorEditarCita(sistema);
		}

		return INSTANCE;
	}

}
