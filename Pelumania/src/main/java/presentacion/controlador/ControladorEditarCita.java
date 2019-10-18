package presentacion.controlador;

import dto.CitaDTO;
import modelo.Sistema;
import presentacion.vista.VentanaEditarCita;

public class ControladorEditarCita {

	private VentanaEditarCita ventanaEditarCita;
	private Sistema sistema;
	private ControladorEditarCita controladorEditarCita;
	private static ControladorEditarCita INSTANCE;

	private ControladorEditarCita(Sistema sistema, CitaDTO citaAEditar) {
		this.ventanaEditarCita = VentanaEditarCita.getInstance(citaAEditar);
		this.sistema = sistema;
	}

	public static ControladorEditarCita getInstance(Sistema sistema, CitaDTO citaAEditar) {
		if ( INSTANCE == null) {
			INSTANCE = new ControladorEditarCita(sistema, citaAEditar);
		}

		return INSTANCE;
	}

}
