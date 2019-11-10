package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import javax.swing.JOptionPane;

import dto.ProductoDTO;
import dto.UsuarioDTO;
import modelo.Sistema;
import presentacion.vista.VentanaAgregarProducto;
import util.Validador;

public class ControladorAgregarProducto implements ActionListener {

	private VentanaAgregarProducto ventanaAgregarProducto;
	private Sistema sistema;
	private ControladorProductos controladorProducto;
	private static ControladorAgregarProducto INSTANCE;
	private static UsuarioDTO usuario;

	private ControladorAgregarProducto(Sistema sistema, UsuarioDTO usuario) {
		this.ventanaAgregarProducto = VentanaAgregarProducto.getInstance();
		this.ventanaAgregarProducto.getBtnAgregarProducto().addActionListener(p -> guardarProducto(p));
		this.ventanaAgregarProducto.getBtn_Cancelar().addActionListener(q -> cancelar(q));
		this.sistema = sistema;
		this.usuario = usuario;
	}

	public static ControladorAgregarProducto getInstance(Sistema sistema,UsuarioDTO usuario) {
		if ( INSTANCE == null) {
			INSTANCE = new ControladorAgregarProducto(sistema,usuario);
		}
		INSTANCE.ventanaAgregarProducto.limpiarCampos();
		INSTANCE.ventanaAgregarProducto.mostrarVentana();
		return INSTANCE;
	}

	private void guardarProducto(ActionEvent p) {


		String S_nombre = this.ventanaAgregarProducto.getTxtNombre().getText();
		String S_precioLocal = this.ventanaAgregarProducto.getTxtPrecioLocal().getText();
		String S_precioDolar = this.ventanaAgregarProducto.getTxtPrecioDolar().getText();
	
		String S_puntos = this.ventanaAgregarProducto.getTxtPuntos().getText();

		//validamos campos
		if ( Validador.esNombreConEspaciosValido(S_nombre) &&
				Validador.esPrecioValido(S_precioLocal) &&
				Validador.esPrecioValido(S_precioDolar) &&
				Validador.esPuntosValido(S_puntos)) {

			String nombre = S_nombre;
			BigDecimal precioLocal = new BigDecimal(S_precioLocal);
			BigDecimal precioDolar = new BigDecimal(S_precioDolar);
			int puntos = Integer.parseInt(S_puntos);
			LocalTime duracion = null;

		

			ProductoDTO nuevoProducto = new ProductoDTO(0, nombre, precioLocal, precioDolar, puntos, "Activo");
			this.sistema.agregarProducto(nuevoProducto);
		
			ControladorProductos.getInstance(sistema,usuario);
			this.ventanaAgregarProducto.cerrar();

		} else {

			this.ventanaAgregarProducto.mostrarErrorCampos();
		}
	}
	
	public void cancelar(ActionEvent q) {
		this.ventanaAgregarProducto.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}

}
