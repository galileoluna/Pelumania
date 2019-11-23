package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import org.junit.platform.engine.discovery.FileSelector;

import dto.CitaDTO;
import dto.ProductoDTO;
import modelo.Sistema;
import presentacion.vista.VentanaBuscarProducto;
import presentacion.vista.VentanaCaja;

public class ControladorBuscarProducto implements ActionListener {
	
	private VentanaBuscarProducto ventanaBuscarProducto;
	private Sistema sistema;
	private List<ProductoDTO> listaProductos;
	private ControladorCaja controladorCaja;
	private static ControladorBuscarProducto INSTANCE;

	private ControladorBuscarProducto(Sistema sistema, VentanaCaja ventanaCaja, ControladorCaja controladorCaja) {
		this.ventanaBuscarProducto = VentanaBuscarProducto.getInstance();
		this.ventanaBuscarProducto.getBtnSeleccionarProducto().addActionListener(p -> seleccionarProducto(p));
		this.controladorCaja = controladorCaja;
		this.sistema = sistema;
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
	
	private void seleccionarProducto(ActionEvent p) {
		int filaSeleccionada = this.ventanaBuscarProducto.getTablaProductos().getSelectedRow();
		if (filaSeleccionada != -1) {
			ProductoDTO produtctoSeleccionado = this.listaProductos.get(filaSeleccionada);
			if (produtctoSeleccionado != null) {
				this.controladorCaja.setProductoSeleccionada(produtctoSeleccionado);
				this.controladorCaja.mostrarDatosProducto();
				this.ventanaBuscarProducto.cerrar();
			} else {
				this.ventanaBuscarProducto.mostrarErrorSinSeleccionar();
			}
		
		} else {
			this.ventanaBuscarProducto.mostrarErrorSinSeleccionar();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub		
	}

}