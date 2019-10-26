package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import dto.SucursalDTO;
import modelo.Sistema;
import presentacion.vista.VentanaReportesPorLocal;

public class ControladorReportesPorLocal /*implements ActionListener*/ {
	private VentanaReportesPorLocal ventanaReportes;
	private Sistema sistema;
	private static ControladorReportesPorLocal INSTANCE;

	private ControladorReportesPorLocal(Sistema sistema) {
		this.ventanaReportes = VentanaReportesPorLocal.getInstance();
		this.sistema = sistema;
		cargarSucursales();
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
}