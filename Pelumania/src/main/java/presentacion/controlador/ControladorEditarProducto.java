package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import javax.swing.JOptionPane;

import dto.ProductoDTO;
import dto.UsuarioDTO;
import modelo.Sistema;
import presentacion.vista.VentanaEditarProducto;
import util.Validador;

public class ControladorEditarProducto {

	private VentanaEditarProducto ventanaEditarProducto;
	private Sistema sistema;
	private ControladorProductos controladorProducto;
	private static int idProducto;
	private static ControladorEditarProducto INSTANCE;
	private static UsuarioDTO usuario;
	
	private ControladorEditarProducto(Sistema sistema, UsuarioDTO usuario) {
		this.ventanaEditarProducto = VentanaEditarProducto.getInstance();
		this.ventanaEditarProducto.getBtnGuardarProducto().addActionListener(a -> guardarProducto(a));
		this.sistema = sistema;
		this.usuario= usuario;
	}
	
	public static ControladorEditarProducto getInstance(Sistema sistema, ProductoDTO Producto, int id, UsuarioDTO usuario) {
		if ( INSTANCE == null) {
			INSTANCE = new ControladorEditarProducto(sistema, usuario);
		}	
			idProducto = id;
			INSTANCE.ventanaEditarProducto.getTxtNombre().setText(Producto.getNombre());
			INSTANCE.ventanaEditarProducto.getTxtPrecioLocal().setText(Producto.getPrecioLocal().toString());
			INSTANCE.ventanaEditarProducto.getTxtPrecioDolar().setText(Producto.getPrecioDolar().toString());
			INSTANCE.ventanaEditarProducto.getTxtPuntos().setText(Integer.toString(Producto.getPuntos()));
			INSTANCE.ventanaEditarProducto.getJCBoxEstado().setSelectedItem(Producto.getEstado());
		
			INSTANCE.ventanaEditarProducto.mostrarVentana();
		return INSTANCE;
	}

	
	private void guardarProducto(ActionEvent a) {
		String S_nombre = this.ventanaEditarProducto.getTxtNombre().getText();
		String S_precioLocal = this.ventanaEditarProducto.getTxtPrecioLocal().getText();
		String S_precioDolar = this.ventanaEditarProducto.getTxtPrecioDolar().getText();
		String S_puntos = this.ventanaEditarProducto.getTxtPuntos().getText();
		String S_estado = (String) this.ventanaEditarProducto.getJCBoxEstado().getSelectedItem();
		
		if ( Validador.esNombreConEspaciosValido(S_nombre) &&
				 Validador.esPrecioValido(S_precioLocal) &&
				 Validador.esPrecioValido(S_precioDolar) &&
				 Validador.esPuntosValido(S_puntos)) {
			
				String nombre = S_nombre;
				BigDecimal precioLocal = new BigDecimal(S_precioLocal);
				BigDecimal precioDolar = new BigDecimal(S_precioDolar);
				int puntos = Integer.parseInt(S_puntos);
				LocalTime duracion = null;
				
	
				
				ProductoDTO Producto_a_actualizar = new ProductoDTO(idProducto, nombre, precioLocal, precioDolar, puntos, S_estado);
				this.sistema.editarProducto(Producto_a_actualizar);
				this.ventanaEditarProducto.cerrar();
				ControladorProductos.getInstance(sistema,usuario);
				
				} else {
				
				this.ventanaEditarProducto.mostrarErrorCampos();
				}

			}
	}
