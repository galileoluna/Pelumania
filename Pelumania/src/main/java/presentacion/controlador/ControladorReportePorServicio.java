package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import dto.ServicioDTO;
import modelo.Sistema;
import presentacion.Reportes.ReportePorServicio;
import presentacion.vista.VentanaReportePorServicio;

public class ControladorReportePorServicio {
	private VentanaReportePorServicio ventanaReportes;
	private Sistema sistema;
	private static ControladorReportePorServicio INSTANCE;

	private ControladorReportePorServicio(Sistema sistema) {
		this.ventanaReportes = VentanaReportePorServicio.getInstance();
		this.sistema = sistema;
		cargarServicios();
		
		this.ventanaReportes.getBtnGenerarReporte().addActionListener(l -> reportePorServicio(l));

	}

	public static ControladorReportePorServicio getInstance(Sistema sistema) {
		if ( INSTANCE == null) {
			INSTANCE = new ControladorReportePorServicio(sistema);
		}
		INSTANCE.ventanaReportes.mostrar();
		return INSTANCE;
	}
	
	public void cargarServicios() {
		List<ServicioDTO> servicios = sistema.obtenerServicios();
		
		for (ServicioDTO servicio : servicios) {
			this.ventanaReportes.getJcb_Servicio().addItem(servicio);
		}
	}
	
	private void reportePorServicio(ActionEvent l) {
	SimpleDateFormat formato = new SimpleDateFormat("YYYY-MM-dd", Locale.getDefault());
		String desde = formato.format(ventanaReportes.getJdc_Desde().getDate());
		String hasta = formato.format(ventanaReportes.getJdc_Hasta().getDate());
		
		String desdeParaReporte=desde+" 00:00:01";
		String hastaParaReporte=hasta+" 11:59:59";
		
		Date Desde = Date.valueOf(desde);
		Date Hasta = Date.valueOf(hasta);
		
//		ArrayList<MovimientoCajaDTO>servicio=(ArrayList<MovimientoCajaDTO>) sistema.obtenerMovimientosCajaIngresosServicio(desdeParaReporte,hastaParaReporte,this.ventanaReportes.getJcb_Servicio().getSelectedIndex()+1);
		
		ServicioDTO servicio = (ServicioDTO) this.ventanaReportes.getJcb_Servicio().getSelectedItem();
		ReportePorServicio reportePorServicio = new ReportePorServicio(servicio,Desde,Hasta);
		reportePorServicio.mostrar();
	}
}
