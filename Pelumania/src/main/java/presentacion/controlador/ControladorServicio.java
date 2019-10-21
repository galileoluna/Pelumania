package presentacion.controlador;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JOptionPane;

import dto.CitaDTO;
import dto.ServicioDTO;
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
	
	private ControladorServicio(Sistema sist) {
		ventanaServicio = VentanaServicio.getInstance();
		ventanaServicio.getBtnAgregar().addActionListener(a ->agregarServicio(a));
		ventanaServicio.getBtnBorrar().addActionListener(b -> borrarServicio(b));
		ventanaServicio.getBtnEditar().addActionListener(c -> editarServicio(c));
		ventanaServicio.getBtnBuscar().addActionListener(y -> buscar(y));
		sistema = sist;
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
		serviciosEnTabla = sistema.obtenerServicios();
		int[] filasSeleccionadas = ventanaServicio.getTablaServicios().getSelectedRows();
	       
    	for (int fila : filasSeleccionadas)
    	{
        	if(serviciosEnTabla.get(fila)!=null) {	 
        		this.idServicio = serviciosEnTabla.get(fila).getIdServicio();
        		ServicioDTO servicio_a_editar = sistema.getServicioById(idServicio);
        		
        		ControladorEditarServicio.getInstance(sistema, servicio_a_editar, idServicio);
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
        			JOptionPane.showMessageDialog(null, "No se puede eliminar algo ya eliminado!");
        		else {
        		int confirm = JOptionPane.showOptionDialog(null, "Estas seguro que deseas borrar el servicio?","Confirmacion", JOptionPane.YES_NO_OPTION,
	   		             JOptionPane.QUESTION_MESSAGE, null, null, null);
	        		if (confirm == 0) {
		        		this.idServicio = serviciosEnTabla.get(fila).getIdServicio();
		        		ServicioDTO servicio_a_eliminar = sistema.getServicioById(idServicio);
		        		sistema.borrarServicio(servicio_a_eliminar);
		        		ReprogramarCitas(idServicio);
		        		ControladorServicio.getInstance(sistema);
		        		
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
