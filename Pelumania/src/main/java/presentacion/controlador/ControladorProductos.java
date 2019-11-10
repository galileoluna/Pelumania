package presentacion.controlador;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JOptionPane;

import dto.CitaDTO;
import dto.ProductoDTO;
import dto.UsuarioDTO;
import modelo.Sistema;
import presentacion.vista.VentanaProducto;

public class ControladorProductos {
	private static ControladorProductos INSTANCE;
	private static VentanaProducto ventanaProducto;
	private static List<ProductoDTO> ProductosEnTabla;
	private int idProducto;
	
	private ControladorAgregarProducto controladorAgregarProducto;
	private ControladorEditarProducto controladorEditarProducto;
	private static Sistema sistema;
	private static UsuarioDTO usuario;
	
	private ControladorProductos(Sistema sist,UsuarioDTO usuar) {
		ventanaProducto = VentanaProducto.getInstance();
		ventanaProducto.getBtnAgregar().addActionListener(a ->agregarProducto(a));
		ventanaProducto.getBtnBorrar().addActionListener(b -> borrarProducto(b));
		ventanaProducto.getBtnEditar().addActionListener(c -> editarProducto(c));
		ventanaProducto.getBtnBuscar().addActionListener(y -> buscar(y));
		sistema = sist;
		usuario = usuar;
	}
	
	

	public static ControladorProductos getInstance(Sistema sistema, UsuarioDTO usuario) {
		if ( INSTANCE == null) {
			INSTANCE = new ControladorProductos(sistema,usuario);
		}
		getPermisos();
		List<ProductoDTO> ProductosEnTabla = sistema.obtenerProducto();
		INSTANCE.ventanaProducto.llenarTabla(ProductosEnTabla);
		INSTANCE.ventanaProducto.mostrar();
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
			ventanaProducto.getBtnAgregar().setEnabled(false);
			ventanaProducto.getBtnBorrar().setEnabled(false);
			ventanaProducto.getBtnBuscar().setEnabled(false);
			ventanaProducto.getBtnEditar().setEnabled(false);
			  break;
		  case 4:
			 
			 break;
		}		
	}



	private void agregarProducto(ActionEvent a) {
		this.controladorAgregarProducto = ControladorAgregarProducto.getInstance(sistema,usuario);
	}
	
	private void editarProducto(ActionEvent c) {
		ProductosEnTabla = sistema.obtenerProducto();
		int[] filasSeleccionadas = ventanaProducto.getTablaProductos().getSelectedRows();
	       
    	for (int fila : filasSeleccionadas)
    	{
        	if(ProductosEnTabla.get(fila)!=null) {	 
        		this.idProducto = ProductosEnTabla.get(fila).getIdProducto();
        		ProductoDTO Producto_a_editar = sistema.obtenerPorId(idProducto);
        		
        		ControladorEditarProducto.getInstance(sistema, Producto_a_editar, idProducto,usuario);
	}
    	}
	}

	private void borrarProducto(ActionEvent b) {
		ProductosEnTabla = sistema.obtenerProducto();
		int[] filasSeleccionadas = ventanaProducto.getTablaProductos().getSelectedRows();
	       
    	for (int fila : filasSeleccionadas)
    	{
        	if(ProductosEnTabla.get(fila)!=null) {
        		if (ProductosEnTabla.get(fila).getEstado().toLowerCase().equals("inactivo"))
        			this.ventanaProducto.mostrarErrorBorrar();
        		else {
        		int confirm = this.ventanaProducto.mostrarConfirmacionBorrar();
	        		if (confirm == 0) {
		        		this.idProducto = ProductosEnTabla.get(fila).getIdProducto();
		        		ProductoDTO Producto_a_eliminar = sistema.obtenerPorId(idProducto);
		        		sistema.borrarProducto(Producto_a_eliminar);
		        		
		        		ControladorProductos.getInstance(sistema,usuario);
		        		
	        		}
        	}
        	}
    	}
	}

	private void buscar(ActionEvent y) {
		String variable=this.ventanaProducto.getVariableBuscar().getSelectedItem().toString();
		String value=this.ventanaProducto.getBuscador().getText();
		this.ventanaProducto.llenarTabla(this.sistema.obtenerProductoConBuscador(variable,value));
	}
}
