package presentacion.controlador;

import java.util.List;

import dto.ProfesionalDTO;
import modelo.Sistema;
import presentacion.vista.VentanaProfesional;
import presentacion.vista.VentanaServicioProfecional;

public class ControladorServicioProfesional {
	private static ControladorServicioProfesional INSTANCE;
	private Sistema sistema;
	private String nombre;
	private int idProfesional;
	private VentanaServicioProfecional ventServProf;

	private ControladorServicioProfesional(Sistema sistema) {
		this.ventServProf = ventServProf.getInstance();
		this.sistema=sistema;
	}
	
	public static ControladorServicioProfesional getInstance(Sistema sistema, int idProf, String nombre) {
		if ( INSTANCE == null) {
			INSTANCE = new ControladorServicioProfesional(sistema);
		}
		INSTANCE.ventServProf.setNombreEmpl(nombre);
		INSTANCE.idProfesional=idProf;
		INSTANCE.ventServProf.show();
		return INSTANCE;
	}
	
	

}
