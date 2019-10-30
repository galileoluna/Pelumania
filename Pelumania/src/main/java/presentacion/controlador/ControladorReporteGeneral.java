package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import dto.MovimientoCajaDTO;
import modelo.Sistema;
import presentacion.reportes.ReporteDeCajaGeneral;
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
		return INSTANCE;
	}

	private void reporteGeneral(ActionEvent a) {
		SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
		String desde = formato.format(ventanaReportes.getJdc_Desde().getDate());
		String hasta = formato.format(ventanaReportes.getJdc_Hasta().getDate());
		//System.out.println(desde+" "+hasta);
		
		ArrayList<MovimientoCajaDTO>caja=(ArrayList<MovimientoCajaDTO>) sistema.obtenerMovimientosCaja(desde,hasta);
		
		System.out.println(caja.get(0).getIdCaja());
		
		ReporteDeCajaGeneral reporteDeCajaGeneral = new ReporteDeCajaGeneral(caja);
		reporteDeCajaGeneral.mostrar();
	}
}

