package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
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
	private int idDiaLaboral;
	private static int idProfesional;
	private Sistema sistema;
	
	private ControladorModificarHorario(Sistema sistema) {
		this.modificarHorario = VentanaModificarHorario.getInstance();
		this.modificarHorario.getbtnActualizar().addActionListener(j ->actualizarHora(j));
		this.sistema=sistema;
	}

	public static ControladorModificarHorario getInstance(Sistema sistema, List<HorarioDTO> horario,String nombre, String apellido) {
		if ( INSTANCE == null) {
			INSTANCE = new ControladorModificarHorario(sistema);
		}
		INSTANCE.nombre=nombre;
		INSTANCE.apellido=apellido;
		for (HorarioDTO h : horario) {	
			INSTANCE.idDiaLaboral=h.getidDiasLaborales();
			INSTANCE.idProfesional=h.getIdProfesional();
			INSTANCE.modificarHorario.setLblNombre(nombre+" "+apellido);
			INSTANCE.modificarHorario.setComboDia(h.getDia());
			INSTANCE.modificarHorario.setHoraEntrada(h.getHoraEntrada().getHours());
			INSTANCE.modificarHorario.setMinutosEntrada(h.getHoraEntrada().getMinutes());
			INSTANCE.modificarHorario.setHoraSalida(h.getHoraSalida().getHours());
			INSTANCE.modificarHorario.setMinutosSalida(h.getHoraSalida().getMinutes());
			
		}
		INSTANCE.modificarHorario.show();
		return INSTANCE;
	}
	

	private void actualizarHora(ActionEvent j) {
		String dias=this.modificarHorario.getComboDia().getSelectedItem().toString();
		String horaEntrada=this.modificarHorario.getHoraEntrada().getSelectedItem().toString();
		String minEntrada=this.modificarHorario.getMinutosEntrada().getSelectedItem().toString();
		String horaSalida=this.modificarHorario.getHoraSalida().getSelectedItem().toString();
		String minSalida=this.modificarHorario.getMinutosSalida().getSelectedItem().toString();
		Time entrada=new Time(Integer.parseInt(horaEntrada),Integer.parseInt(minEntrada),00);
		Time salida= new Time(Integer.parseInt(horaSalida),Integer.parseInt(minSalida),00);
		HorarioDTO hora=new HorarioDTO(idDiaLaboral,dias,entrada,salida,idProfesional);
		this.sistema.actualizarHorario(hora);
		this.modificarHorario.cerrar();
		controlHorario.getInstance(sistema, nombre, apellido, idProfesional);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
