package presentacion.controlador;

import java.util.List;

import dto.ServicioDTO;
import modelo.Sistema;
import presentacion.vista.VentanaReportePorServicio;

public class ControladorReportePorServicio {
	private VentanaReportePorServicio ventanaReportes;
	private Sistema sistema;
	private static ControladorReportePorServicio INSTANCE;

	private ControladorReportePorServicio(Sistema sistema) {
		this.ventanaReportes = VentanaReportePorServicio.getInstance();
		this.sistema = sistema;
		cargarServicios();
	}

	public static ControladorReportePorServicio getInstance(Sistema sistema) {
		if ( INSTANCE == null) {
			INSTANCE = new ControladorReportePorServicio(sistema);
		}
		return INSTANCE;
	}
	
	public void cargarServicios() {
		List<ServicioDTO> servicios = sistema.obtenerServicios();
		
		for (ServicioDTO servicio : servicios) {
			this.ventanaReportes.getJcb_Servicio().addItem(servicio);
		}
	}
}
