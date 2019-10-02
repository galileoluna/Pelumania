package presentacion.controlador;
import dto.ProfesionalDTO;
import dto.ServicioDTO;
import modelo.Sistema;

import java.awt.event.ActionEvent;
import java.util.List;

import presentacion.vista.VentanaServicio;

public class ControladorServicio {
	private static ControladorServicio INSTANCE;
	private static VentanaServicio ventanaServicio;
	private static List<ServicioDTO> serviciosEnTabla;
	
	private ControladorAgregarServicio controladorAgregarServicio;
	private static Sistema sistema;
	
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
	
	private static void refrescarTabla()
	{
		serviciosEnTabla = sistema.obtenerServicios();
		ventanaServicio.llenarTabla(serviciosEnTabla);
	}
	
	
}
