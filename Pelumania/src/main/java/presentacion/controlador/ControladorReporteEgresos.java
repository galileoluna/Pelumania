package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

import modelo.Sistema;
import presentacion.Reportes.ReporteDeEgresos;
import presentacion.Reportes.ReporteDeIngresos;
import presentacion.vista.VentanaReporteEgresos;
import presentacion.vista.VentanaReporteIngresos;

public class ControladorReporteEgresos  {
	private VentanaReporteEgresos ventanaReporte;
	private Sistema sistema;
	private static ControladorReporteEgresos INSTANCE;

	private ControladorReporteEgresos(Sistema sistema) {
		this.ventanaReporte = VentanaReporteEgresos.getInstance();
		this.sistema = sistema;

		this.ventanaReporte.getBtnGenerarReporte().addActionListener(l -> reporteIngresos(l));

	}

	public static ControladorReporteEgresos getInstance(Sistema sistema) {
		if ( INSTANCE == null) {
			INSTANCE = new ControladorReporteEgresos(sistema);
		}
		INSTANCE.ventanaReporte.mostrar();
		return INSTANCE;
	}
	
	private void reporteIngresos(ActionEvent l) {
	SimpleDateFormat formato = new SimpleDateFormat("YYYY-MM-dd", Locale.getDefault());
		String desde = formato.format(ventanaReporte.getJdc_Desde().getDate());
		String hasta = formato.format(ventanaReporte.getJdc_Hasta().getDate());

		Date Desde = Date.valueOf(desde);
		Date Hasta = Date.valueOf(hasta);
		
		ReporteDeEgresos reporteEgresos = new ReporteDeEgresos(Desde,Hasta);
		reporteEgresos.mostrar();
	}	
}