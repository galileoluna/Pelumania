package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import javax.swing.JOptionPane;

import dto.ServicioDTO;
import modelo.Sistema;
import presentacion.vista.VentanaAgregarServicio;
import util.Validador;

public class ControladorAgregarServicio implements ActionListener {

	
	private VentanaAgregarServicio ventanaServicio;
	private Sistema sistema;
	private static ControladorAgregarServicio INSTANCE;
	
	private ControladorAgregarServicio(Sistema sistema) {
		this.ventanaServicio = VentanaAgregarServicio.getInstance();
		this.ventanaServicio.getBtnAgregarServicio().addActionListener(p -> guardarServicio(p));
		this.sistema = sistema;
	}
	
	public static ControladorAgregarServicio getInstance(Sistema sistema) {
		if ( INSTANCE == null) {
			INSTANCE = new ControladorAgregarServicio(sistema);
		}	
		INSTANCE.ventanaServicio.mostrarVentana();
		return INSTANCE;
	}

	private void guardarServicio(ActionEvent p) {
		
		
		String S_nombre = this.ventanaServicio.getTxtNombre().getText();
		String S_precioLocal = this.ventanaServicio.getTxtPrecioLocal().getText();
		String S_precioDolar = this.ventanaServicio.getTxtPrecioDolar().getText();
		String S_duracion = this.ventanaServicio.getTxtDuracion().getText();
		String S_puntos = this.ventanaServicio.getTxtPuntos().getText();
		
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
		
		ServicioDTO nuevoServicio = new ServicioDTO(0, nombre, precioLocal, precioDolar, duracion, puntos, "activo");
		this.sistema.agregarServicio(nuevoServicio);

		this.ventanaServicio.cerrar();
		
		} else {
		
		this.ventanaServicio.mostrarErrorCampos();
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	

}
