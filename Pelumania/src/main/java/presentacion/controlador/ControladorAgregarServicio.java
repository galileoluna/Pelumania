package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import javax.swing.JOptionPane;

import dto.ServicioDTO;
import dto.UsuarioDTO;
import modelo.Sistema;
import presentacion.vista.VentanaAgregarServicio;
import util.Validador;

public class ControladorAgregarServicio implements ActionListener {

	private VentanaAgregarServicio ventanaAgregarServicio;
	private Sistema sistema;
	private ControladorServicio controladorServicio;
	private static ControladorAgregarServicio INSTANCE;
	private static UsuarioDTO usuario;

	private ControladorAgregarServicio(Sistema sistema, UsuarioDTO usuario) {
		this.ventanaAgregarServicio = VentanaAgregarServicio.getInstance();
		this.ventanaAgregarServicio.getBtnAgregarServicio().addActionListener(p -> guardarServicio(p));
		this.ventanaAgregarServicio.getBtn_Cancelar().addActionListener(q -> cancelar(q));
		this.sistema = sistema;
		this.usuario = usuario;
	}

	public static ControladorAgregarServicio getInstance(Sistema sistema,UsuarioDTO usuario) {
		if ( INSTANCE == null) {
			INSTANCE = new ControladorAgregarServicio(sistema,usuario);
		}
		INSTANCE.ventanaAgregarServicio.limpiarCampos();
		INSTANCE.ventanaAgregarServicio.mostrarVentana();
		return INSTANCE;
	}

	private void guardarServicio(ActionEvent p) {


		String S_nombre = this.ventanaAgregarServicio.getTxtNombre().getText();
		String S_precioLocal = this.ventanaAgregarServicio.getTxtPrecioLocal().getText();
		String S_precioDolar = this.ventanaAgregarServicio.getTxtPrecioDolar().getText();
		String S_duracion = this.ventanaAgregarServicio.getTxtDuracion().getText();
		String S_puntos = this.ventanaAgregarServicio.getTxtPuntos().getText();

		//validamos campos
		if ( Validador.esNombreConEspaciosValido(S_nombre) &&
				Validador.esPrecioValido(S_precioLocal) &&
				Validador.esPrecioValido(S_precioDolar) &&
				//Validador.esDuracionValida(S_duracion) &&
				Validador.esPuntosValido(S_puntos)) {

			String nombre = S_nombre;
			BigDecimal precioLocal = new BigDecimal(S_precioLocal);
			BigDecimal precioDolar = new BigDecimal(S_precioDolar);
			int puntos = Integer.parseInt(S_puntos);
			LocalTime duracion = null;

			try {
				duracion = LocalTime.parse(S_duracion);
			} catch(DateTimeParseException e) {
				JOptionPane.showMessageDialog(null, "El formato de la duracion debe ser HH : MM");
			}

			ServicioDTO nuevoServicio = new ServicioDTO(0, nombre, precioLocal, precioDolar, duracion, puntos, "Activo");
			this.sistema.agregarServicio(nuevoServicio);
			this.sistema.agregarServicioProfesional(this.sistema.getServicioMax().getIdServicio(), -1);
			ControladorServicio.getInstance(sistema,usuario);
			this.ventanaAgregarServicio.cerrar();

		} else {

			this.ventanaAgregarServicio.mostrarErrorCampos();
		}
	}
	
	public void cancelar(ActionEvent q) {
		this.ventanaAgregarServicio.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}

}
