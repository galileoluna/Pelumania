package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modelo.Sistema;
import presentacion.vista.VentanaAltaProfesional;
import presentacion.vista.VentanaCliente;

public class ControladorAltaProfesional  implements ActionListener{
	private VentanaAltaProfesional altaProfesional;
	private Sistema sistema;
	private static ControladorAltaProfesional INSTANCE;
	
	private ControladorAltaProfesional(Sistema sistema) {
		this.altaProfesional = VentanaAltaProfesional.getInstance();
		this.altaProfesional.getBtnAgregar().addActionListener(l -> guardarProfesional(l));
		this.sistema = sistema;
	}
	
	public static ControladorAltaProfesional getInstance(Sistema sistema) {
		if ( INSTANCE == null) {
			INSTANCE = new ControladorAltaProfesional(sistema);
		}	
		
		INSTANCE.altaProfesional.mostrarVentana();
		return INSTANCE;
	}
	
	private Object guardarProfesional(ActionEvent l) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
