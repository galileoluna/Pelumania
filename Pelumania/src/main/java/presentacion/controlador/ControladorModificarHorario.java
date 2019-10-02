package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import dto.HorarioDTO;
import dto.ProfesionalDTO;
import modelo.Sistema;
import presentacion.vista.VentanaHorarioProfesional;
import presentacion.vista.VentanaModificarHorario;

public class ControladorModificarHorario implements ActionListener {
	private static ControladorModificarHorario INSTANCE;
	private VentanaModificarHorario modificarHorario;
	private ControladorHorarioProfesional controlHorario;
	private String nombre;
	private String apellido;
	private static int idProfesional;
	private Sistema sistema;
	
	private ControladorModificarHorario(Sistema sistema) {
		this.modificarHorario = VentanaModificarHorario.getInstance();
		this.sistema=sistema;
	}

	public static ControladorModificarHorario getInstance(Sistema sistema, List<HorarioDTO> horario,String nombre, String apellido, int id) {
		if ( INSTANCE == null) {
			INSTANCE = new ControladorModificarHorario(sistema);
		}
		INSTANCE.idProfesional=id;
		INSTANCE.nombre=nombre;
		INSTANCE.apellido=apellido;
		for (HorarioDTO h : horario) {	
			INSTANCE.modificarHorario.setLblNombre(nombre+" "+apellido);
			INSTANCE.modificarHorario.setComboDia(h.getDia());
			System.out.println(h.getDia());
			INSTANCE.modificarHorario.setHoraEntrada(Integer.toString(h.getHoraEntrada().getHours()));
			INSTANCE.modificarHorario.setMinutosEntrada(Integer.toString(h.getHoraEntrada().getMinutes()));
			INSTANCE.modificarHorario.setHoraSalida(Integer.toString(h.getHoraSalida().getHours()));
			INSTANCE.modificarHorario.setHoraSalida(Integer.toString(h.getHoraSalida().getMinutes()));
			
		}
		INSTANCE.modificarHorario.show();
		return INSTANCE;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
