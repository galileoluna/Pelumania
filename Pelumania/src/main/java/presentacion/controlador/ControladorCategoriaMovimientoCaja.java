package presentacion.controlador;


import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JOptionPane;

import dto.CategoriaMovimientoCajaDTO;
import dto.SucursalDTO;
import modelo.Sistema;
import presentacion.vista.VentanaCategoriaMovimentoCaja;

public class ControladorCategoriaMovimientoCaja {
	private static ControladorCategoriaMovimientoCaja INSTANCE;
	private static VentanaCategoriaMovimentoCaja ventanaCategoria;
	private static List<CategoriaMovimientoCajaDTO> categoriaEnTabla;
	private int idCategoria;
	
//	private ControladorAgregarSucursal controladorAgregarSucursal;
//	private ControladorEditarSucursal controladorEditarSucursal;
	private static Sistema sistema;
	
	private ControladorCategoriaMovimientoCaja(Sistema sist) {
		ventanaCategoria = VentanaCategoriaMovimentoCaja.getInstance();
//		ventanaCategoria.getBtnAgregar().addActionListener(a ->agregarCategoria(a));
//		ventanaCategoria.getBtnBorrar().addActionListener(b -> borrarCategoria(b));
//		ventanaCategoria.getBtnEditar().addActionListener(c -> editarCategoria(c));
		sistema = sist;
	}
	
	public static ControladorCategoriaMovimientoCaja getInstance(Sistema sistema) {
		if ( INSTANCE == null) {
			INSTANCE = new ControladorCategoriaMovimientoCaja(sistema);
		}
		
		List<CategoriaMovimientoCajaDTO> categoriaEnTabla = sistema.obtenerCategoriasMovimientoCaja();
		INSTANCE.ventanaCategoria.llenarTabla(categoriaEnTabla);
		INSTANCE.ventanaCategoria.mostrar();
		return INSTANCE;
	}
	

//	private void agregarCategoria(ActionEvent a) {
//		this.controladorAgregarSucursal = ControladorAgregarSucursal.getInstance(sistema);
//	}
//	
//	private void editarCategoria(ActionEvent c) {
//		categoriaEnTabla = sistema.obtenerCategoriasMovimientoCaja();
//		int[] filasSeleccionadas = ventanaCategoria.getTablaCategoria().getSelectedRows();
//	       
//    	for (int fila : filasSeleccionadas)
//    	{
//        	if(categoriaEnTabla.get(fila)!=null) {	 
//        		this.idCategoria = categoriaEnTabla.get(fila).getIdCategoria();
//        		SucursalDTO sucursal_a_editar = sistema.getSucursalById(idCategoria);
//        		
//        		ControladorEditarSucursal.getInstance(sistema, sucursal_a_editar, idCategoria);
//	}
//    	}
//	}

//	private void borrarCategoria(ActionEvent b) {
//		categoriaEnTabla = sistema.obtenerCategoriasMovimientoCaja();
//		int[] filasSeleccionadas = ventanaCategoria.getTablaCategoria().getSelectedRows();
//	       
//    	for (int fila : filasSeleccionadas){
//        	if(categoriaEnTabla.get(fila)!=null) {
//        		if (categoriaEnTabla.get(fila).getEstado().toLowerCase().equals("Inactivo")) {
//        			JOptionPane.showMessageDialog(null, "No se puede eliminar algo ya eliminado!");
//        		}
//        		else {
//        			int confirm = JOptionPane.showOptionDialog(null, "Estas seguro que deseas borrar la categoria?","Confirmacion", JOptionPane.YES_NO_OPTION,
//	   		        JOptionPane.QUESTION_MESSAGE, null, null, null);
//	        		if (confirm == 0) {
//	        			this.idCategoria = categoriaEnTabla.get(fila).getIdCategoria();
//	        			SucursalDTO categoria_a_eliminar = sistema.getSucursalById(idCategoria);
//	        			sistema.borrarSucursal(categoria_a_eliminar);
//	        			ControladorSucursal.getInstance(sistema);
//	        		}
//        	}
//        	}
//    	}
//	}
	
}