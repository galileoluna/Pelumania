package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import dto.MovimientoCajaDTO;
import modelo.Sistema;
import presentacion.Reportes.ReporteDeCajaGeneral;
import presentacion.vista.VentanaReporteGeneral;

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
		INSTANCE.ventanaReportes.mostrarVentana();
		return INSTANCE;
	}

	private void reporteGeneral(ActionEvent a) {
		SimpleDateFormat formato = new SimpleDateFormat("YYYY-MM-dd", Locale.getDefault());
		String desde = formato.format(ventanaReportes.getJdc_Desde().getDate());
		String hasta = formato.format(ventanaReportes.getJdc_Hasta().getDate());
		
		String desdeParaReporte=desde+" 00:00:01";
		String hastaParaReporte=hasta+" 11:59:59";
		
		ArrayList<MovimientoCajaDTO>caja=(ArrayList<MovimientoCajaDTO>) sistema.obtenerMovimientosCaja(desde,hasta);
			
		ReporteDeCajaGeneral reporteDeCajaGeneral = new ReporteDeCajaGeneral(caja,desde,hasta);
		reporteDeCajaGeneral.mostrar();
		
		
	}
}
