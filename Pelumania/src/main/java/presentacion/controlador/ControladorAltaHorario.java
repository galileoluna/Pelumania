package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modelo.Sistema;
import presentacion.vista.VentanaAltaHorario;
import presentacion.vista.VentanaAltaProfesional;

public class ControladorAltaHorario implements ActionListener{
	private static ControladorAltaHorario INSTANCE;
	private Sistema sistema;
	private VentanaAltaHorario altaHorario;
	private ControladorHorarioProfesional controladorHorarioProf;
	
	private ControladorAltaHorario(Sistema sistema) {
		this.altaHorario = VentanaAltaHorario.getInstance();
		this.sistema = sistema;
	}
	
	public static ControladorAltaHorario getInstance(Sistema sistema) {
		if ( INSTANCE == null) {
			INSTANCE = new ControladorAltaHorario(sistema);
		}
		INSTANCE.altaHorario.show();
		return INSTANCE;
	}

	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
