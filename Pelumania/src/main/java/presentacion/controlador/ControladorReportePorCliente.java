package presentacion.controlador;

import java.util.List;

import dto.ClienteDTO;
import dto.ServicioDTO;
import modelo.Sistema;
import presentacion.vista.VentanaReportePorCliente;
import presentacion.vista.VentanaReportePorServicio;

public class ControladorReportePorCliente {
	private VentanaReportePorCliente ventanaReportes;
	private Sistema sistema;
	private static ControladorReportePorCliente INSTANCE;

	private ControladorReportePorCliente(Sistema sistema) {
		this.ventanaReportes = VentanaReportePorCliente.getInstance();
		this.sistema = sistema;
		cargarCliente();
	}

	public static ControladorReportePorCliente getInstance(Sistema sistema) {
		if ( INSTANCE == null) {
			INSTANCE = new ControladorReportePorCliente(sistema);
		}
		return INSTANCE;
	}
	
	public void cargarCliente() {
		List<ClienteDTO> clientes = sistema.obtenerClientes();
		
		for (ClienteDTO cliente : clientes) {
			this.ventanaReportes.getJcb_Cliente().addItem(cliente);
		}
	}
}
