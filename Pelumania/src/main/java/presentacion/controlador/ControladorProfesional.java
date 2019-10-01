package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.util.List;

import dto.ProfesionalDTO;
import modelo.Sistema;
import presentacion.vista.VentanaAltaProfesional;
import presentacion.vista.VentanaCliente;
import presentacion.vista.VentanaProfesional;

public class ControladorProfesional {
	private static ControladorProfesional INSTANCE;
	private VentanaProfesional ventanaProfesional;
	private ControladorAltaProfesional altaProfesional;
	private Sistema sistema;
	
	private ControladorProfesional(Sistema sistema) {
		this.ventanaProfesional = VentanaProfesional.getInstance();
		this.ventanaProfesional.getBtnAgregar().addActionListener(p ->agregarProfesional(p));
		
		this.sistema = sistema;
	}

	public static ControladorProfesional getInstance(Sistema sistema) {
		if ( INSTANCE == null) {
			INSTANCE = new ControladorProfesional(sistema);
		}
		
		List<ProfesionalDTO> profesionalEnTabla=sistema.obtenerProfesional();
		
		INSTANCE.ventanaProfesional.llenarTabla(profesionalEnTabla);
		INSTANCE.ventanaProfesional.show();
		return INSTANCE;
	}
	
	private void agregarProfesional(ActionEvent p) {
		this.altaProfesional = ControladorAltaProfesional.getInstance(sistema);
		
	}
	
	public void refrescarTabla() {
		List<ProfesionalDTO>personasEnTabla=sistema.obtenerProfesional();
		this.ventanaProfesional.llenarTabla(personasEnTabla);	
	}

}
