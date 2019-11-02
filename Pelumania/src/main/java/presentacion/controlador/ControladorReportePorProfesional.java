package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import dto.MovimientoCajaDTO;
import dto.ProfesionalDTO;
import modelo.Sistema;
import presentacion.Reportes.ReportePorProfesional;
import presentacion.vista.VentanaReportePorProfesional;

public class ControladorReportePorProfesional {
	private VentanaReportePorProfesional ventanaReportes;
	private Sistema sistema;
	private static ControladorReportePorProfesional INSTANCE;

	private ControladorReportePorProfesional(Sistema sistema) {
		this.ventanaReportes = VentanaReportePorProfesional.getInstance();
		this.sistema = sistema;
		cargarProfesional();
		
		this.ventanaReportes.getBtnGenerarReporte().addActionListener(l -> reportePorProfesional(l));

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
	
	private void reportePorProfesional(ActionEvent l) {
		SimpleDateFormat formato = new SimpleDateFormat("YYYY-MM-dd", Locale.getDefault());
		String desde = formato.format(ventanaReportes.getJdc_Desde().getDate());
		String hasta = formato.format(ventanaReportes.getJdc_Hasta().getDate());
		
		String desdeParaReporte=desde+" 00:00:01";
		String hastaParaReporte=hasta+" 11:59:59";
		
		ArrayList<MovimientoCajaDTO>profesional=(ArrayList<MovimientoCajaDTO>) sistema.obtenerMovimientosCajaIngresosProfesional(desdeParaReporte,hastaParaReporte,this.ventanaReportes.getJcb_Profesional().getSelectedIndex());
		System.out.println("hola");
		System.out.println(profesional.get(0).getIdProfesional());
		
		ReportePorProfesional reportePorProf = new ReportePorProfesional(profesional,desde,hasta);
		reportePorProf.mostrar();
	}
}