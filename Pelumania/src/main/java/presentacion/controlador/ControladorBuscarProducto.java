package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import dto.ProductoDTO;
import modelo.Sistema;
import presentacion.vista.VentanaBuscarProducto;
import presentacion.vista.VentanaCaja;

public class ControladorBuscarProducto implements ActionListener {
	
	private VentanaBuscarProducto ventanaBuscarProducto;
	private Sistema sistema;
	private List<ProductoDTO> listaProductos;
	private static ControladorBuscarProducto INSTANCE;

	private ControladorBuscarProducto(Sistema sistema, VentanaCaja ventanaCaja, ControladorCaja controladorCaja) {
		this.ventanaBuscarProducto = VentanaBuscarProducto.getInstance();
		this.ventanaBuscarProducto.getBtnSeleccionarProducto().addActionListener(p -> seleccionarProducto(p));
		this.sistema = sistema;
	}

	private void seleccionarProducto(ActionEvent p) {

	}

	public static ControladorBuscarProducto getInstance(Sistema sistema, VentanaCaja ventanaCaja, ControladorCaja controladorCaja) {
		if ( INSTANCE == null) {
			INSTANCE = new ControladorBuscarProducto(sistema, ventanaCaja, controladorCaja);
		}
		inicializarDatos();
		return INSTANCE;
	}

	private static void inicializarDatos() {
		INSTANCE.listaProductos = new ArrayList<ProductoDTO>();
		for (ProductoDTO producto : INSTANCE.sistema.obtenerProducto()) {
			if (!producto.getEstado().equalsIgnoreCase("inactivo")) {
				INSTANCE.listaProductos.add(producto);
			}
		}
		INSTANCE.ventanaBuscarProducto.llenarTabla(INSTANCE.listaProductos);
		INSTANCE.ventanaBuscarProducto.mostrarVentana();
	}
	
//	private void seleccionarCliente(ActionEvent p) {
//		int filaSeleccionada = this.ventanaBuscarCliente.getTablaClientes().getSelectedRow();
//		ClienteDTO cliente_seleccionado = this.listaClientes.get(filaSeleccionada);
//		if (cliente_seleccionado != null) {
//			this.ventanaCita.setCliente(cliente_seleccionado);
//			this.ventanaCita.setDatosClienteRegistrado();
//			this.ventanaBuscarCliente.cerrar();
//		}
//
//	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub		
	}

}