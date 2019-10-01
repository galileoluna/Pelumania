package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import dto.HorarioDTO;
import dto.ProfesionalDTO;
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
	
	public static ControladorHorarioProfesional getInstance(Sistema sistema, String nombre,String apellido, int id) {
		if ( INSTANCE == null) {
			INSTANCE = new ControladorHorarioProfesional(sistema);
		}
		INSTANCE.horaProfesional.setNombreEmpl(nombre+" "+apellido);
		List<HorarioDTO> HorarioEnTabla=sistema.obtenerHorario(id);
		INSTANCE.horaProfesional.llenarTabla(HorarioEnTabla);
		INSTANCE.horaProfesional.show();
		return INSTANCE;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
