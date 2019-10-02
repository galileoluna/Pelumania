package presentacion.controlador;
import dto.ProfesionalDTO;
import dto.ServicioDTO;
import modelo.Sistema;

import java.awt.event.ActionEvent;
import java.util.List;

import presentacion.vista.VentanaProfesional;
import presentacion.vista.VentanaServicio;

public class ControladorServicio {
	private static ControladorServicio INSTANCE;
	private VentanaServicio ventanaServicio;
	private List<ServicioDTO> serviciosEnTabla;
	
	private ControladorAgregarServicio controladorAgregarServicio;
	private Sistema sistema;
	
	private ControladorServicio(Sistema sistema) {
		this.ventanaServicio = ventanaServicio.getInstance();
		this.ventanaServicio.getBtnAgregar().addActionListener(a ->agregarServicio(a));
		//this.ventanaServicio.getBtnBorrar().addActionListener(b -> borrarServicio(b));
		//this.ventanaServicio.getBtnEditar().addActionListener(c -> editarServicio(c));
		this.sistema = sistema;
	}
	
	public static ControladorServicio getInstance(Sistema sistema) {
		if ( INSTANCE == null) {
			INSTANCE = new ControladorServicio(sistema);
		}
		
		List<ServicioDTO> serviciosEnTabla = sistema.obtenerServicios();
		INSTANCE.ventanaServicio.llenarTabla(serviciosEnTabla);
		INSTANCE.ventanaServicio.mostrar();
		return INSTANCE;
	}
	
	private void agregarServicio(ActionEvent p) {
		this.controladorAgregarServicio = ControladorAgregarServicio.getInstance(sistema);
	}
}
