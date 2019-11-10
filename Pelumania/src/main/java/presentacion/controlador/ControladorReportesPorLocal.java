
package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import dto.MovimientoCajaDTO;
import dto.SucursalDTO;
import modelo.Sistema;
import presentacion.Reportes.ReporteDeCajaPorSucursal;
import presentacion.vista.VentanaReportesPorLocal;

public class ControladorReportesPorLocal /*implements ActionListener*/ {
	private VentanaReportesPorLocal ventanaReportes;
	private Sistema sistema;
	private static ControladorReportesPorLocal INSTANCE;

	private ControladorReportesPorLocal(Sistema sistema) {
		this.ventanaReportes = VentanaReportesPorLocal.getInstance();
		this.sistema = sistema;
		cargarSucursales();

		this.ventanaReportes.getBtnGenerarReporte().addActionListener(l -> reportePorLocal(l));

	}

	public static ControladorReportesPorLocal getInstance(Sistema sistema) {
		if ( INSTANCE == null) {
			INSTANCE = new ControladorReportesPorLocal(sistema);
		}
		INSTANCE.ventanaReportes.mostrar();
		return INSTANCE;
	}
	
	public void cargarSucursales() {
		List<SucursalDTO> sucursales = sistema.obtenerSucursales();
		int cont=0;
		
		for (SucursalDTO sucursal : sucursales) {
			if(cont==0)cont++;
			else {
				this.ventanaReportes.getJcb_Sucursal().addItem(sucursal);
				cont++;
			}
		}
	}

	
	private void reportePorLocal(ActionEvent l) {
	SimpleDateFormat formato = new SimpleDateFormat("YYYY-MM-dd", Locale.getDefault());
		String desde = formato.format(ventanaReportes.getJdc_Desde().getDate());
		String hasta = formato.format(ventanaReportes.getJdc_Hasta().getDate());
		
		String desdeParaReporte=desde+" 00:00:01";
		String hastaParaReporte=hasta+" 11:59:59";
		
		ArrayList<MovimientoCajaDTO>caja=(ArrayList<MovimientoCajaDTO>) sistema.obtenerMovimientosCajaSucursal(desdeParaReporte,hastaParaReporte,this.ventanaReportes.getJcb_Sucursal().getSelectedIndex()+1);

		ReporteDeCajaPorSucursal reporteDeCajaSucursal = new ReporteDeCajaPorSucursal(caja,desde,hasta);
		reporteDeCajaSucursal.mostrar();
	}	
}