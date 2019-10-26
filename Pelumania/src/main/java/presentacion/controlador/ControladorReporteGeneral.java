package presentacion.controlador;

import java.util.List;

import modelo.Sistema;
import presentacion.vista.VentanaReporteGeneral;
import presentacion.vista.VentanaReportesPorLocal;

public class ControladorReporteGeneral {
	private VentanaReporteGeneral ventanaReportes;
	private Sistema sistema;
	private static ControladorReporteGeneral INSTANCE;

	private ControladorReporteGeneral(Sistema sistema) {
		this.ventanaReportes = VentanaReporteGeneral.getInstance();
		this.sistema = sistema;
	}

	public static ControladorReporteGeneral getInstance(Sistema sistema) {
		if ( INSTANCE == null) {
			INSTANCE = new ControladorReporteGeneral(sistema);
		}
		return INSTANCE;
	}
}
