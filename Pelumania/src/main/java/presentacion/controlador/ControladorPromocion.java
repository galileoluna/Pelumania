package presentacion.controlador;

import java.util.List;

import dto.ProfesionalDTO;
import dto.PromocionDTO;
import modelo.Sistema;
import presentacion.vista.VentanaProfesional;
import presentacion.vista.VentanaPromocion;

public class ControladorPromocion {
	private static ControladorPromocion INSTANCE;
	private Sistema sistema;
	private VentanaPromocion ventanaPromocion;
	List<PromocionDTO> promosEnTabla;
	
	private ControladorPromocion(Sistema sistema) {
		this.ventanaPromocion = VentanaPromocion.getInstance();
		this.sistema=sistema;
	}

	public static ControladorPromocion getInstance(Sistema sistema) {
		if ( INSTANCE == null) {
			INSTANCE = new ControladorPromocion(sistema);
		}
		
		List<PromocionDTO> promosEnTabla=sistema.obtenerPrmociones();
		INSTANCE.ventanaPromocion.llenarTabla(promosEnTabla);
		INSTANCE.ventanaPromocion.show();
		return INSTANCE;
	}

}
