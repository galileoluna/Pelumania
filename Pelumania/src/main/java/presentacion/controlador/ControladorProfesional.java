package presentacion.controlador;

import java.awt.event.ActionEvent;

import modelo.Sistema;
import presentacion.vista.VentanaCliente;
import presentacion.vista.VentanaProfesional;

public class ControladorProfesional {
	private static ControladorProfesional INSTANCE;
	private VentanaProfesional ventanaProfesional;
	private Sistema sistema;
	
	private ControladorProfesional(Sistema sistema) {
		this.ventanaProfesional = VentanaProfesional.getInstance();
		this.ventanaProfesional.getBtnAgregar().addActionListener(p -> guardarProfesional(p));
		this.sistema = sistema;
	}
	private Object guardarProfesional(ActionEvent p) {
		// TODO Auto-generated method stub
		return null;
	}
	public static ControladorProfesional getInstance(Sistema sistema) {
		if ( INSTANCE == null) {
			INSTANCE = new ControladorProfesional(sistema);
		}	
		INSTANCE.ventanaProfesional.show();
		return INSTANCE;
	}
}
