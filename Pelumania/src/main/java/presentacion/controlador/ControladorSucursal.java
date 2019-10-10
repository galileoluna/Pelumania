package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JOptionPane;

import dto.ProfesionalDTO;
import dto.ServicioDTO;
import dto.SucursalDTO;
import modelo.Sistema;
import presentacion.vista.VentanaProfesional;
import presentacion.vista.VentanaServicio;
import presentacion.vista.VentanaSucursal;

public class ControladorSucursal {
	private static ControladorSucursal INSTANCE;
	private static VentanaSucursal ventanaSucursal;
	private static List<SucursalDTO> sucursalEnTabla;
	private int idSucursal;
	
	private ControladorAgregarSucursal controladorAgregarSucursal;
	private ControladorEditarSucursal controladorEditarSucursal;
	private static Sistema sistema;
	
	private ControladorSucursal(Sistema sist) {
		ventanaSucursal = VentanaSucursal.getInstance();
		ventanaSucursal.getBtnAgregar().addActionListener(a ->agregarSucursal(a));
		ventanaSucursal.getBtnBorrar().addActionListener(b -> borrarSucursal(b));
		ventanaSucursal.getBtnEditar().addActionListener(c -> editarSucursal(c));
		sistema = sist;
	}
	
	public static ControladorSucursal getInstance(Sistema sistema) {
		if ( INSTANCE == null) {
			INSTANCE = new ControladorSucursal(sistema);
		}
		
		List<SucursalDTO> sucursalEnTabla = sistema.obtenerSucursales();
		INSTANCE.ventanaSucursal.llenarTabla(sucursalEnTabla);
		INSTANCE.ventanaSucursal.mostrar();
		return INSTANCE;
	}
	

	private void agregarSucursal(ActionEvent a) {
		this.controladorAgregarSucursal = ControladorAgregarSucursal.getInstance(sistema);
	}
	
	private void editarSucursal(ActionEvent c) {
		sucursalEnTabla = sistema.obtenerSucursales();
		int[] filasSeleccionadas = ventanaSucursal.getTablaSucursal().getSelectedRows();
	       
    	for (int fila : filasSeleccionadas)
    	{
        	if(sucursalEnTabla.get(fila)!=null) {	 
        		this.idSucursal = sucursalEnTabla.get(fila).getIdSucursal();
        		SucursalDTO sucursal_a_editar = sistema.getSucursalById(idSucursal);
        		
        		ControladorEditarSucursal.getInstance(sistema, sucursal_a_editar, idSucursal);
	}
    	}
	}

	private void borrarSucursal(ActionEvent b) {
		sucursalEnTabla = sistema.obtenerSucursales();
		int[] filasSeleccionadas = ventanaSucursal.getTablaSucursal().getSelectedRows();
	       
    	for (int fila : filasSeleccionadas){
        	if(sucursalEnTabla.get(fila)!=null) {
        		if (sucursalEnTabla.get(fila).getEstadoSucursal().toLowerCase().equals("Inactivo"))
        			JOptionPane.showMessageDialog(null, "No se puede eliminar algo ya eliminado!");
        		else {
        			int confirm = JOptionPane.showOptionDialog(null, "Estas seguro que deseas borrar la sucursal?","Confirmacion", JOptionPane.YES_NO_OPTION,
	   		        JOptionPane.QUESTION_MESSAGE, null, null, null);
	        		if (confirm == 0) {
	        			this.idSucursal = sucursalEnTabla.get(fila).getIdSucursal();
	        			SucursalDTO sucursal_a_eliminar = sistema.getSucursalById(idSucursal);
	        			sistema.borrarSucursal(sucursal_a_eliminar);
	        			ControladorSucursal.getInstance(sistema);
	        		}
        	}
        	}
    	}
	}
	
}