package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.util.List;

import modelo.Sistema;
import presentacion.vista.VentanaServicioPromocion;

public class ControladorServicioPromocion {
	private Sistema sistema;
	private static ControladorServicioPromocion INSTANCE;
	private VentanaServicioPromocion ventanaServPromo;
	private List<String> servCombo;
	private int idPromocion;
	
	private ControladorServicioPromocion(Sistema sistema) {
		this.ventanaServPromo = ventanaServPromo.getInstance();
		this.ventanaServPromo.getBtnAgregar().addActionListener(l -> agregarServ(l));
		this.ventanaServPromo.getBtnBorrar().addActionListener(k -> borrarServ(k));
		this.sistema=sistema;
	}

	public static ControladorServicioPromocion getInstance(Sistema sistema, String nombre, int idPromo) {
		if ( INSTANCE == null) {
			INSTANCE = new ControladorServicioPromocion(sistema);
		}
		INSTANCE.ventanaServPromo.setDescripcion(nombre);
		INSTANCE.idPromocion=idPromo;
		INSTANCE.ventanaServPromo.llenarTabla(INSTANCE.sistema.obtenerServPromo(idPromo));
		INSTANCE.ventanaServPromo.show();
		return INSTANCE;
	}
	
	private void borrarServ(ActionEvent k) {
		
	}

	private Object agregarServ(ActionEvent l) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
