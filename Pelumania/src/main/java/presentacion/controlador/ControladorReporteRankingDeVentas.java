package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import dto.MovimientoCajaDTO;
import modelo.Sistema;
import presentacion.Reportes.ReporteDeCajaPorSucursal;
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
		return INSTANCE;
	}
	
	private void reporteVentas(ActionEvent l) {
		SimpleDateFormat formato = new SimpleDateFormat("YYYY-MM-dd", Locale.getDefault());
		String desde = formato.format(ventanaReportes.getJdc_Desde().getDate());
		String hasta = formato.format(ventanaReportes.getJdc_Hasta().getDate());
			
		String desdeParaReporte=desde+" 00:00:01";
		String hastaParaReporte=hasta+" 11:59:59";
			
		ArrayList<MovimientoCajaDTO>caja=(ArrayList<MovimientoCajaDTO>) sistema.obtenerMovimientoCajaRanking(desdeParaReporte,hastaParaReporte);

		ReporteDeCajaPorSucursal reporteDeCajaSucursal = new ReporteDeCajaPorSucursal(caja,desde,hasta);
		reporteDeCajaSucursal.mostrar();
			
	}
	
}

