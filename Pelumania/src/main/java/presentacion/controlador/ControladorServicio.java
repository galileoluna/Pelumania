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
	private int idServicio;
	
	private ControladorAgregarServicio controladorAgregarServicio;
	private ControladorEditarServicio controladorEditarServicio;
	private static Sistema sistema;
	
	private ControladorServicio(Sistema sistema) {
		this.ventanaServicio = ventanaServicio.getInstance();
		this.ventanaServicio.getBtnAgregar().addActionListener(a ->agregarServicio(a));
		//this.ventanaServicio.getBtnBorrar().addActionListener(b -> borrarServicio(b));
		this.ventanaServicio.getBtnEditar().addActionListener(c -> editarServicio(c));
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
	
	private void agregarServicio(ActionEvent a) {
		this.controladorAgregarServicio = ControladorAgregarServicio.getInstance(sistema);
	}
	
	private void editarServicio(ActionEvent c) {
		this.serviciosEnTabla = sistema.obtenerServicios();
		int[] filasSeleccionadas = this.ventanaServicio.getTablaServicios().getSelectedRows();
	       
    	for (int fila : filasSeleccionadas)
    	{
        	if(this.serviciosEnTabla.get(fila)!=null) {	 
        		this.idServicio = this.serviciosEnTabla.get(fila).getIdServicio();
        		ServicioDTO servicio_a_editar = this.sistema.getServicioById(idServicio);
        		
        		this.controladorEditarServicio.getInstance(sistema, servicio_a_editar, idServicio);
	}
 
    	}
	}
}
