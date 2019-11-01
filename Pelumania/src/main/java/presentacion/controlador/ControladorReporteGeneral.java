package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.sql.Timestamp;
import java.util.ArrayList;

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

		if(sonFechasValidas()) {
		
		Timestamp desde = Timestamp.from(ventanaReportes.getJdc_Desde().getDate().toInstant());
		Timestamp hasta = Timestamp.from(ventanaReportes.getJdc_Hasta().getDate().toInstant());
//		System.out.println(desde);
//		System.out.println(hasta);
			
			ArrayList<MovimientoCajaDTO>caja=(ArrayList<MovimientoCajaDTO>) sistema.obtenerMovimientosCaja(desde,hasta);
			
//			if (caja.size() < 0 ) {
//					System.out.println(caja.get(0).getIdCaja());
//			}
			
			ReporteDeCajaGeneral reporteDeCajaGeneral = new ReporteDeCajaGeneral(caja,desde,hasta);
			reporteDeCajaGeneral.mostrar();
		
		} else {
			//fecha invalida o vacia
			this.ventanaReportes.mostrarErrorFecha();
		}
	}

	private boolean sonFechasValidas() {
		return this.ventanaReportes.getJdc_Desde().getDate() != null && this.ventanaReportes.getJdc_Hasta().getDate() != null;
	}
}

