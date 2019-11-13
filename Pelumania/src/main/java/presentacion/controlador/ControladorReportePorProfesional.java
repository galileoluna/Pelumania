package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

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
		INSTANCE.ventanaReportes.mostrar();
		return INSTANCE;
	}
	
	public void cargarProfesional() {
		List<ProfesionalDTO> profesionales = sistema.obtenerProfesional();
		int cont=0;
		
		for (ProfesionalDTO profesional : profesionales) {
			if(cont==0)cont++;
			else {
				this.ventanaReportes.getJcb_Profesional().addItem(profesional);
				cont++;
			}
			
		}
	}
	
	private void reportePorProfesional(ActionEvent l) {
		SimpleDateFormat formato = new SimpleDateFormat("YYYY-MM-dd", Locale.getDefault());
		String desde = formato.format(ventanaReportes.getJdc_Desde().getDate());
		String hasta = formato.format(ventanaReportes.getJdc_Hasta().getDate());
		
		String desdeParaReporte=desde+" 00:00:01";
		String hastaParaReporte=hasta+" 11:59:59";
		
		Date Desde = Date.valueOf(desde);
		Date Hasta = Date.valueOf(hasta);
		ProfesionalDTO profesional = (ProfesionalDTO) this.ventanaReportes.getJcb_Profesional().getSelectedItem();
//		ArrayList<MovimientoCajaDTO>profesional=(ArrayList<MovimientoCajaDTO>) sistema.obtenerMovimientosCajaIngresosProfesional(desdeParaReporte,hastaParaReporte,this.ventanaReportes.getJcb_Profesional().getSelectedIndex()+1);
		ReportePorProfesional reportePorProf = new ReportePorProfesional(profesional,Desde,Hasta);
		reportePorProf.mostrar();
	}
}