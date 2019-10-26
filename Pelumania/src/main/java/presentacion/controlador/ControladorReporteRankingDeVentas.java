package presentacion.controlador;

import modelo.Sistema;
import presentacion.vista.VentanaReporteRankingVentas;

public class ControladorReporteRankingDeVentas {
	private VentanaReporteRankingVentas ventanaReportes;
	private Sistema sistema;
	private static ControladorReporteRankingDeVentas INSTANCE;

	private ControladorReporteRankingDeVentas(Sistema sistema) {
		this.ventanaReportes = VentanaReporteRankingVentas.getInstance();
		this.sistema = sistema;
	}

	public static ControladorReporteRankingDeVentas getInstance(Sistema sistema) {
		if ( INSTANCE == null) {
			INSTANCE = new ControladorReporteRankingDeVentas(sistema);
		}
		return INSTANCE;
	}
}
