package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.util.List;

import javax.swing.JOptionPane;

import dto.HorarioDTO;
import modelo.Sistema;
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

	//Inicia la instancia de Ventana que se encarga de modificar los horarios de un determinado profesional (VentanaModificarHorario)
	// recibe el sistema, la lista del horario seleccionado, el nombre, apellido del profesional
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
	
	// obtiene los datos que se ingresaron en la ventana y luego updatea el horario del profesional
	// si neceistan avisen y les explico bien que hace para transformar la hora pero creo que no es nada complicado -- es posible que si tienen mal configurada la bdd no coincida con lo que le ingresaron en la pantalla pero guarda tod bien
	//cualquier inconveniente sobre la hora avisen y lo veo
	private void actualizarHora(ActionEvent j) {
		String dias=this.modificarHorario.getComboDia().getSelectedItem().toString();
		String horaEntrada=this.modificarHorario.getHoraEntrada().getSelectedItem().toString();
		String minEntrada=this.modificarHorario.getMinutosEntrada().getSelectedItem().toString();
		String horaSalida=this.modificarHorario.getHoraSalida().getSelectedItem().toString();
		String minSalida=this.modificarHorario.getMinutosSalida().getSelectedItem().toString();
		Time entrada=new Time(Integer.parseInt(horaEntrada),Integer.parseInt(minEntrada),00);
		Time salida= new Time(Integer.parseInt(horaSalida),Integer.parseInt(minSalida),00);
		if(validar(idProfesional,dias)) {
			HorarioDTO hora=new HorarioDTO(idDiaLaboral,dias,entrada,salida,idProfesional);
			this.sistema.actualizarHorario(hora);
			this.modificarHorario.cerrar();
			// llamo a la instancia del controlador del horario y le paso los datos necesarios para que refresque la tabla (ControladorHorarioProfesional)
			controlHorario.getInstance(sistema, nombre, apellido, idProfesional);
		}else {
			JOptionPane.showMessageDialog(null, "El profesional ya cuenta con los horarios asignados para el dia que intenta modificar", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	//Valido que el profesional no tenga mas de dos horarios por dia es decir turno mañana y tarde
	private boolean validar(int id, String dia) {
		List<HorarioDTO> horas=this.sistema.obtenerHorario(id);
		int encontro=0;
		for(HorarioDTO h : horas) {
			if(h.getDia().equals(dia)) {
				encontro++;
			}
		}
		if(encontro >= 2) {
			return false;
		}else {
			return true;
		}
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
