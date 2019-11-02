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
		return INSTANCE;
	}
	
	public void cargarSucursales() {
		List<SucursalDTO> sucursales = sistema.obtenerSucursales();
		
		for (SucursalDTO sucursal : sucursales) {
			this.ventanaReportes.getJcb_Sucursal().addItem(sucursal);
		}
	}

	
	private void reportePorLocal(ActionEvent l) {
		SimpleDateFormat formato = new SimpleDateFormat("YYYY-MM-dd", Locale.getDefault());
		String desde = formato.format(ventanaReportes.getJdc_Desde().getDate());
		String hasta = formato.format(ventanaReportes.getJdc_Hasta().getDate());
		
		//desde =cambiarOrdenFormato(desde)+" 00:00:01";
		//hasta =cambiarOrdenFormato(hasta)+"11:59:59";
		desde+=" 00:00:01";
		hasta+=" 11:59:59";
		//System.out.println(desde+" "+hasta);

		System.out.println(desde);
		System.out.println(hasta);
		ArrayList<MovimientoCajaDTO>caja=(ArrayList<MovimientoCajaDTO>) sistema.obtenerMovimientosCajaSucursal(desde,hasta,this.ventanaReportes.getJcb_Sucursal().getSelectedIndex()+1);

		System.out.println(caja.get(0).getIdCaja());
		
		ReporteDeCajaPorSucursal reporteDeCajaSucursal = new ReporteDeCajaPorSucursal(caja);
		reporteDeCajaSucursal.mostrar();
	}

	private String cambiarOrdenFormato(String desde) {
		String nuevoDesde=desde.charAt(6)+desde.charAt(7)+desde.charAt(8)+desde.charAt(9)+"-"+
				desde.charAt(3)+desde.charAt(4)+"-"+desde.charAt(0)+desde.charAt(1);
		return nuevoDesde;
		
	}
	
	
}