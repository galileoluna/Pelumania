package presentacion.controlador;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JOptionPane;

import dto.CitaDTO;
import dto.ServicioDTO;
import dto.UsuarioDTO;
import modelo.Sistema;
import presentacion.vista.VentanaServicio;

public class ControladorServicio {
	private static ControladorServicio INSTANCE;
	private static VentanaServicio ventanaServicio;
	private static List<ServicioDTO> serviciosEnTabla;
	private int idServicio;
	
	private ControladorAgregarServicio controladorAgregarServicio;
	private ControladorEditarServicio controladorEditarServicio;
	private static Sistema sistema;
	private static UsuarioDTO usuario;
	
	private ControladorServicio(Sistema sist,UsuarioDTO usuar) {
		ventanaServicio = VentanaServicio.getInstance();
		ventanaServicio.getBtnAgregar().addActionListener(a ->agregarServicio(a));
		ventanaServicio.getBtnBorrar().addActionListener(b -> borrarServicio(b));
		ventanaServicio.getBtnEditar().addActionListener(c -> editarServicio(c));
		ventanaServicio.getBtnBuscar().addActionListener(y -> buscar(y));
		sistema = sist;
		usuario = usuar;
	}
	
	

	public static ControladorServicio getInstance(Sistema sistema, UsuarioDTO usuario) {
		if ( INSTANCE == null) {
			INSTANCE = new ControladorServicio(sistema,usuario);
		}
		getPermisos();
		List<ServicioDTO> serviciosEnTabla = sistema.obtenerServicios();
		INSTANCE.ventanaServicio.llenarTabla(serviciosEnTabla);
		INSTANCE.ventanaServicio.mostrar();
		return INSTANCE;
	}
	
	private static  void getPermisos() {
		int rol= usuario.getIdRol();
		switch(rol) {
		  case 1:
		  case 5:
		    
		    break;
		  case 2:
		    // code block
		    break;
		  case 3:
			ventanaServicio.getBtnAgregar().setEnabled(false);
			ventanaServicio.getBtnBorrar().setEnabled(false);
			ventanaServicio.getBtnBuscar().setEnabled(false);
			ventanaServicio.getBtnEditar().setEnabled(false);
			  break;
		  case 4:
			 
			 break;
		}		
	}



	private void agregarServicio(ActionEvent a) {
		this.controladorAgregarServicio = ControladorAgregarServicio.getInstance(sistema,usuario);
	}
	
	private void editarServicio(ActionEvent c) {
		serviciosEnTabla = sistema.obtenerServicios();
		int[] filasSeleccionadas = ventanaServicio.getTablaServicios().getSelectedRows();
	       
    	for (int fila : filasSeleccionadas)
    	{
        	if(serviciosEnTabla.get(fila)!=null) {	 
        		this.idServicio = serviciosEnTabla.get(fila).getIdServicio();
        		ServicioDTO servicio_a_editar = sistema.getServicioById(idServicio);
        		
        		ControladorEditarServicio.getInstance(sistema, servicio_a_editar, idServicio,usuario);
	}
    	}
	}

	private void borrarServicio(ActionEvent b) {
		serviciosEnTabla = sistema.obtenerServicios();
		int[] filasSeleccionadas = ventanaServicio.getTablaServicios().getSelectedRows();
	       
    	for (int fila : filasSeleccionadas)
    	{
        	if(serviciosEnTabla.get(fila)!=null) {
        		if (serviciosEnTabla.get(fila).getEstado().toLowerCase().equals("inactivo"))
        			this.ventanaServicio.mostrarErrorBorrar();
        		else {
        		int confirm = this.ventanaServicio.mostrarConfirmacionBorrar();
	        		if (confirm == 0) {
		        		this.idServicio = serviciosEnTabla.get(fila).getIdServicio();
		        		ServicioDTO servicio_a_eliminar = sistema.getServicioById(idServicio);
		        		sistema.borrarServicio(servicio_a_eliminar);
		        		ReprogramarCitas(idServicio);
		        		ControladorServicio.getInstance(sistema,usuario);
		        		
	        		}
        	}
        	}
    	}
	}
	
	private void ReprogramarCitas(int idServicio) {
		System.out.println("ta aca");
		List<Integer> citasAReprogramar = sistema.getCitasByIdServicio(idServicio);
		for (Integer idCita : citasAReprogramar) {
			CitaDTO cita_a_reprogramar = sistema.getCitaById(idCita);
			sistema.reprogramarCita(cita_a_reprogramar);
		}
			
	}
	private void buscar(ActionEvent y) {
		String variable=this.ventanaServicio.getVariableBuscar().getSelectedItem().toString();
		String value=this.ventanaServicio.getBuscador().getText();
		this.ventanaServicio.llenarTabla(this.sistema.obtenerServicioConBuscador(variable,value));
	}
}
