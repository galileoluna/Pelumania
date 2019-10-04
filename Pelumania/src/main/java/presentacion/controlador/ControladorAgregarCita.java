package presentacion.controlador;

import java.util.List;

import dto.ClienteDTO;
import modelo.Sistema;
import presentacion.vista.VentanaAgregarCita;

public class ControladorAgregarCita {

	private VentanaAgregarCita ventanaAgregarCita;
	private Sistema sistema;
	private ControladorAgregarCita controladorCita;
	private static ControladorAgregarCita INSTANCE;

	private ControladorAgregarCita(Sistema sistema) {
		this.ventanaAgregarCita = VentanaAgregarCita.getInstance();
		//this.ventanaAgregarCita.getBtnAgregarCita().addActionListener(p -> guardarServicio(p));
		this.sistema = sistema;
	}

	private static void inicializarDatos() {

		List<ClienteDTO> listaClientes = INSTANCE.sistema.obtenerClientes();
		INSTANCE.ventanaAgregarCita.llenarTabla(listaClientes);
		INSTANCE.ventanaAgregarCita.mostrarVentana();
	}

	public static ControladorAgregarCita getInstance(Sistema sistema) {
		if ( INSTANCE == null) {
			INSTANCE = new ControladorAgregarCita(sistema);
		}
		inicializarDatos();
		INSTANCE.ventanaAgregarCita.mostrarVentana();
		return INSTANCE;
	}



}
