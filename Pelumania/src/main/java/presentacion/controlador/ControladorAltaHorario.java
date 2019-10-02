package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import dto.HorarioDTO;
import modelo.Sistema;
import presentacion.vista.VentanaAltaHorario;
import presentacion.vista.VentanaAltaProfesional;

public class ControladorAltaHorario implements ActionListener{
	private static ControladorAltaHorario INSTANCE;
	private Sistema sistema;
	private String nombre;
	private String Apellido;
	private int id;
	private VentanaAltaHorario altaHorario;
	private ControladorHorarioProfesional controladorHorarioProf;
	private ControladorHorarioProfesional controlHorario;
	
	private ControladorAltaHorario(Sistema sistema) {
		this.altaHorario = VentanaAltaHorario.getInstance();
		this.sistema = sistema;
		this.altaHorario.getBtnAgregar().addActionListener(m -> agregarHorario(m));
	}


	public static ControladorAltaHorario getInstance(Sistema sistema, String nombre, String apellido, int idProf) {
		if ( INSTANCE == null) {
			INSTANCE = new ControladorAltaHorario(sistema);
		}
		INSTANCE.nombre=nombre;
		INSTANCE.id=idProf;
		INSTANCE.Apellido=apellido;
		INSTANCE.altaHorario.setLblNombre(nombre+" "+apellido);
		INSTANCE.altaHorario.show();
		return INSTANCE;
	}

	
	private void agregarHorario(ActionEvent m) {
		String dias=this.altaHorario.getComboDia().getSelectedItem().toString();
		String horaEntrada=this.altaHorario.getHoraEntrada().getSelectedItem().toString();
		String minEntrada=this.altaHorario.getMinutosEntrada().getSelectedItem().toString();
		String horaSalida=this.altaHorario.getHoraSalida().getSelectedItem().toString();
		String minSalida=this.altaHorario.getMinutosSalida().getSelectedItem().toString();
		Time entrada=new Time(Integer.parseInt(horaEntrada),Integer.parseInt(minEntrada),00);
		Time salida= new Time(Integer.parseInt(horaSalida),Integer.parseInt(minSalida),00);
		HorarioDTO hora=new HorarioDTO(0,dias,entrada,salida,3);
		this.sistema.agregarHorario(hora);
		altaHorario.dispose();
		controlHorario.getInstance(sistema, nombre, Apellido, id);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
