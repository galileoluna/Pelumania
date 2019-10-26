package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import dto.MovimientoCajaDTO;
import modelo.Sistema;
import presentacion.Reportes.ReporteComprobante;
import presentacion.Reportes.ReporteDeCajaGeneral;
import presentacion.vista.VentanaReporteGeneral;
import presentacion.vista.VentanaReportesPorLocal;

public class ControladorReporteGeneral {
	private VentanaReporteGeneral ventanaReportes;
	private Sistema sistema;
	private static ControladorReporteGeneral INSTANCE;

	private ControladorReporteGeneral(Sistema sistema) {
		this.ventanaReportes = VentanaReporteGeneral.getInstance();
		
		this.sistema = sistema;
		this.ventanaReportes.getBtnGenerarReporte().addActionListener(a -> reporteGeneral(a));
		
	}
	
	public static ControladorReporteGeneral getInstance(Sistema sistema) {
		if ( INSTANCE == null) {
			INSTANCE = new ControladorReporteGeneral(sistema);
		}
		return INSTANCE;
	}

	private void reporteGeneral(ActionEvent a) {
		ArrayList<MovimientoCajaDTO>caja=(ArrayList<MovimientoCajaDTO>) sistema.obtenerMovimientosCaja();
		System.out.println("hola");
		System.out.println(caja.get(0).getIdCaja());
		
		ReporteDeCajaGeneral reporteDeCajaGeneral = new ReporteDeCajaGeneral(caja);
		reporteDeCajaGeneral.mostrar();
	}
}
