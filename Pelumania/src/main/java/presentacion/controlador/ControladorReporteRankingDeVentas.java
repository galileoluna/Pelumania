package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import dto.MovimientoCajaDTO;
import modelo.Sistema;
import presentacion.Reportes.ReporteDeCajaPorSucursal;
import presentacion.Reportes.ReporteRankingVendedores;
import presentacion.vista.VentanaReporteRankingVentas;

public class ControladorReporteRankingDeVentas {
	private VentanaReporteRankingVentas ventanaReportes;
	private Sistema sistema;
	private static ControladorReporteRankingDeVentas INSTANCE;

	private ControladorReporteRankingDeVentas(Sistema sistema) {
		this.ventanaReportes = VentanaReporteRankingVentas.getInstance();
		this.sistema = sistema;
		this.ventanaReportes.getBtnGenerarReporte().addActionListener(l -> reporteVentas(l));

	}

	public static ControladorReporteRankingDeVentas getInstance(Sistema sistema) {
		if ( INSTANCE == null) {
			INSTANCE = new ControladorReporteRankingDeVentas(sistema);
		}
		INSTANCE.ventanaReportes.mostrar();
		return INSTANCE;
	}
	
	private void reporteVentas(ActionEvent l) {
		SimpleDateFormat formato = new SimpleDateFormat("YYYY-MM-dd", Locale.getDefault());
		String desde = formato.format(ventanaReportes.getJdc_Desde().getDate());
		String hasta = formato.format(ventanaReportes.getJdc_Hasta().getDate());
			
		String desdeParaReporte=desde+" 00:00:01";
		String hastaParaReporte=hasta+" 11:59:59";
		
		Date Desde = Date.valueOf(desde);
		Date Hasta = Date.valueOf(hasta);
			
//		ArrayList<MovimientoCajaDTO>caja=(ArrayList<MovimientoCajaDTO>) sistema.obtenerMovimientoCajaRanking(desdeParaReporte,hastaParaReporte);

		ReporteRankingVendedores reporteRankingVentas = new ReporteRankingVendedores(Desde,Hasta);
		reporteRankingVentas.mostrar();
			
	}
	
}

