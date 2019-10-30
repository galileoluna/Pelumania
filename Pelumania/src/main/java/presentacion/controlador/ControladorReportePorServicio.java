package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import dto.MovimientoCajaDTO;
import dto.ServicioDTO;
import modelo.Sistema;
import presentacion.reportes.ReportePorServicio;
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
		return INSTANCE;
	}
	
	public void cargarServicios() {
		List<ServicioDTO> servicios = sistema.obtenerServicios();
		
		for (ServicioDTO servicio : servicios) {
			this.ventanaReportes.getJcb_Servicio().addItem(servicio);
		}
	}
	
	private void reportePorServicio(ActionEvent l) {
		SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
		String desde = formato.format(ventanaReportes.getJdc_Desde().getDate());
		String hasta = formato.format(ventanaReportes.getJdc_Hasta().getDate());
		//System.out.println(desde+" "+hasta);
		
		ArrayList<MovimientoCajaDTO>servicio=(ArrayList<MovimientoCajaDTO>) sistema.obtenerMovimientosCajaIngresosServicio(desde,hasta,this.ventanaReportes.getJcb_Servicio().getSelectedIndex());
		System.out.println("hola");
		System.out.println(servicio.get(0).getIdServicio());
		
		ReportePorServicio reportePorServicio = new ReportePorServicio(servicio);
		reportePorServicio.mostrar();
	}
}
