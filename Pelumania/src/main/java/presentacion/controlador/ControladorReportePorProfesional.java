package presentacion.controlador;

import java.util.List;

import dto.ProfesionalDTO;
import modelo.Sistema;
import presentacion.vista.VentanaReportePorProfesional;

public class ControladorReportePorProfesional {
	private VentanaReportePorProfesional ventanaReportes;
	private Sistema sistema;
	private static ControladorReportePorProfesional INSTANCE;

	private ControladorReportePorProfesional(Sistema sistema) {
		this.ventanaReportes = VentanaReportePorProfesional.getInstance();
		this.sistema = sistema;
		cargarProfesional();
	}

	public static ControladorReportePorProfesional getInstance(Sistema sistema) {
		if ( INSTANCE == null) {
			INSTANCE = new ControladorReportePorProfesional(sistema);
		}
		return INSTANCE;
	}
	
	public void cargarProfesional() {
		List<ProfesionalDTO> profesionales = sistema.obtenerProfesional();
		
		for (ProfesionalDTO profesional : profesionales) {
			this.ventanaReportes.getJcb_Profesional().addItem(profesional);
		}
	}
}
