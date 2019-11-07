package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import javax.swing.JOptionPane;

import dto.ServicioDTO;
import dto.UsuarioDTO;
import modelo.Sistema;
import presentacion.vista.VentanaEditarServicio;
import util.Validador;

public class ControladorEditarServicio {

	private VentanaEditarServicio ventanaEditarServicio;
	private Sistema sistema;
	private ControladorServicio controladorServicio;
	private static int idServicio;
	private static ControladorEditarServicio INSTANCE;
	private static UsuarioDTO usuario;
	
	private ControladorEditarServicio(Sistema sistema, UsuarioDTO usuario) {
		this.ventanaEditarServicio = VentanaEditarServicio.getInstance();
		this.ventanaEditarServicio.getBtnGuardarServicio().addActionListener(a -> guardarServicio(a));
		this.sistema = sistema;
		this.usuario= usuario;
	}
	
	public static ControladorEditarServicio getInstance(Sistema sistema, ServicioDTO servicio, int id, UsuarioDTO usuario) {
		if ( INSTANCE == null) {
			INSTANCE = new ControladorEditarServicio(sistema, usuario);
		}	
			idServicio = id;
			INSTANCE.ventanaEditarServicio.getTxtNombre().setText(servicio.getNombre());
			INSTANCE.ventanaEditarServicio.getTxtPrecioLocal().setText(servicio.getPrecioLocal().toString());
			INSTANCE.ventanaEditarServicio.getTxtPrecioDolar().setText(servicio.getPrecioDolar().toString());
			INSTANCE.ventanaEditarServicio.getTxtDuracion().setText(servicio.getDuracion().toString());
			INSTANCE.ventanaEditarServicio.getTxtPuntos().setText(Integer.toString(servicio.getPuntos()));
			INSTANCE.ventanaEditarServicio.getJCBoxEstado().setSelectedItem(servicio.getEstado());
		
			INSTANCE.ventanaEditarServicio.mostrarVentana();
		return INSTANCE;
	}

	
	private void guardarServicio(ActionEvent a) {
		String S_nombre = this.ventanaEditarServicio.getTxtNombre().getText();
		String S_precioLocal = this.ventanaEditarServicio.getTxtPrecioLocal().getText();
		String S_precioDolar = this.ventanaEditarServicio.getTxtPrecioDolar().getText();
		String S_duracion = this.ventanaEditarServicio.getTxtDuracion().getText();
		String S_puntos = this.ventanaEditarServicio.getTxtPuntos().getText();
		String S_estado = (String) this.ventanaEditarServicio.getJCBoxEstado().getSelectedItem();
		
		if ( Validador.esNombreConEspaciosValido(S_nombre) &&
				 Validador.esPrecioValido(S_precioLocal) &&
				 Validador.esPrecioValido(S_precioDolar) &&
				 Validador.esPuntosValido(S_puntos)) {
			
				String nombre = S_nombre;
				BigDecimal precioLocal = new BigDecimal(S_precioLocal);
				BigDecimal precioDolar = new BigDecimal(S_precioDolar);
				int puntos = Integer.parseInt(S_puntos);
				LocalTime duracion = null;
				
				try {
				duracion = LocalTime.parse(S_duracion);
				} catch(DateTimeParseException e) {
					this.ventanaEditarServicio.mostrarErrorFormatoDuracion();
				}
				
				ServicioDTO servicio_a_actualizar = new ServicioDTO(idServicio, nombre, precioLocal, precioDolar, duracion, puntos, S_estado);
				this.sistema.editarServicio(servicio_a_actualizar);
				this.ventanaEditarServicio.cerrar();
				ControladorServicio.getInstance(sistema,usuario);
				
				} else {
				
				this.ventanaEditarServicio.mostrarErrorCampos();
				}

			}
	}
