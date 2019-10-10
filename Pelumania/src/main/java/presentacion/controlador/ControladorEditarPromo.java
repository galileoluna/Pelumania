package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.util.List;

import dto.PromocionDTO;
import modelo.Sistema;
import presentacion.vista.VentanaAltaPromocion;
import presentacion.vista.VentanaModificarPromocion;

public class ControladorEditarPromo {
	private VentanaModificarPromocion ventanaEditarPromo;
	private ControladorPromocion controlPromo;
	private Sistema sistema;
	private static ControladorEditarPromo INSTANCE;
	
	private ControladorEditarPromo(Sistema sistema) {
		this.ventanaEditarPromo = VentanaModificarPromocion.getInstance();
		
		this.ventanaEditarPromo.getBtnAgregar().addActionListener(l -> editarPromocion(l));
		this.sistema = sistema;
	}
	
	public static ControladorEditarPromo getInstance(Sistema sistema, List<PromocionDTO> promocion) {
		if ( INSTANCE == null) {
			INSTANCE = new ControladorEditarPromo(sistema);
		}
		for (PromocionDTO p : promocion) {
			INSTANCE.ventanaEditarPromo.getDescripcion().setText(p.getDescripcion());
			INSTANCE.ventanaEditarPromo.getDateFechaInic().setDate(p.getFechaInicio());
			INSTANCE.ventanaEditarPromo.getDateFechaFin().setDate(p.getFechaFin());
			INSTANCE.ventanaEditarPromo.getDescuento().setText(p.getDescuento().toString());
			INSTANCE.ventanaEditarPromo.getPuntos().setText(p.getPuntos().toString());
		}
		INSTANCE.ventanaEditarPromo.mostrarVentana();
		return INSTANCE;
	}
	

	private void editarPromocion(ActionEvent l) {
		
	}
}
