package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import dto.SucursalDTO;
import modelo.Sistema;
import presentacion.Reportes.ReporteDeCajaPorSucursal;
import presentacion.Reportes.ReporteDeIngresos;
import presentacion.vista.VentanaReporteIngresos;
import presentacion.vista.VentanaReportesPorLocal;

public class ControladorReporteIngresos {
	private VentanaReporteIngresos ventanaReporte;
	private Sistema sistema;
	private static ControladorReporteIngresos INSTANCE;

	private ControladorReporteIngresos(Sistema sistema) {
		this.ventanaReporte = VentanaReporteIngresos.getInstance();
		this.sistema = sistema;

		this.ventanaReporte.getBtnGenerarReporte().addActionListener(l -> reporteIngresos(l));

	}

	public static ControladorReporteIngresos getInstance(Sistema sistema) {
		if ( INSTANCE == null) {
			INSTANCE = new ControladorReporteIngresos(sistema);
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
		
		ReporteDeIngresos reporteIngresos = new ReporteDeIngresos(Desde,Hasta);
		reporteIngresos.mostrar();
	}	
}