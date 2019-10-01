package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modelo.Sistema;
import presentacion.vista.VentanaAltaProfesional;
import presentacion.vista.VentanaHorarioProfesional;

public class ControladorHorarioProfesional implements ActionListener{
	private Sistema sistema;
	private VentanaHorarioProfesional horaProfesional;
	private static ControladorHorarioProfesional INSTANCE;
	
	
	private ControladorHorarioProfesional(Sistema sistema) {
		this.horaProfesional = VentanaHorarioProfesional.getInstance();
		this.sistema = sistema;
	}
	
	public static ControladorHorarioProfesional getInstance(Sistema sistema, String nombre,String apellido) {
		if ( INSTANCE == null) {
			INSTANCE = new ControladorHorarioProfesional(sistema);
		}
		INSTANCE.horaProfesional.setNombreEmpl(nombre+" "+apellido);
		INSTANCE.horaProfesional.show();
		return INSTANCE;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
