package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;

import dto.HorarioDTO;
import modelo.Sistema;
import presentacion.vista.VentanaHorarioProfesional;

public class ControladorHorarioProfesional implements ActionListener{
	private Sistema sistema;
	private VentanaHorarioProfesional horaProfesional;
	private List<HorarioDTO> horariolEnTabla;
	private int idProfesional;
	private String nombre;
	private String apellido;
	private ControladorAltaHorario altaHorario;
	private ControladorModificarHorario modificarHorario;
	
	private static ControladorHorarioProfesional INSTANCE;
	
	
	private ControladorHorarioProfesional(Sistema sistema) {
		this.horaProfesional = VentanaHorarioProfesional.getInstance();
		this.horaProfesional.getBtnBorrar().addActionListener(m -> borrarDia(m));
		this.horaProfesional.getBtnAgregar().addActionListener(n -> agregarDia(n));
		this.horaProfesional.getBtnEditar().addActionListener(v -> editarDia(v));
		this.sistema = sistema;
	}

	// inicializa la instancia de la ventana que muestra todos los horarios de un solo profesional  (VentanaHorarioProfesional)
	// recibe el sistema , nombre, apellido del profesional y el id del profesional
	public static ControladorHorarioProfesional getInstance(Sistema sistema, String nombre,String apellido, int id) {
		if ( INSTANCE == null) {
			INSTANCE = new ControladorHorarioProfesional(sistema);
		}
		INSTANCE.horaProfesional.setNombreEmpl(nombre+" "+apellido);
		INSTANCE.idProfesional=id;
		INSTANCE.nombre=nombre;
		INSTANCE.apellido=apellido;
		List<HorarioDTO> HorarioEnTabla=sistema.obtenerHorario(id);
		INSTANCE.horaProfesional.llenarTabla(HorarioEnTabla);
		INSTANCE.horaProfesional.show();
		return INSTANCE;
	}
	
	//borra el horario laboral asociado al profesional seleccionado 
	// creo que aca esta bien el delete en caso de que este hay que debatir que hacemos porque no tengo estado para esto
	private void borrarDia(ActionEvent m) {
		this.horariolEnTabla=sistema.obtenerHorario(idProfesional);
		int[] filasSeleccionadas = this.horaProfesional.getTablaHorarioProfesional().getSelectedRows();
		       
	        	for (int fila : filasSeleccionadas) {
		        	if(this.horariolEnTabla.get(fila)!=null) {	 
		        		int confirm = this.horaProfesional.mostrarConfirmacionBorrar();
		        		if (confirm == 0) {
						this.sistema.borrarHorario(this.horariolEnTabla.get(fila));
		        		}
		        		// llama a la instancia de esta clase (la que esta arriba), para refresco de tabla
		        		this.getInstance(sistema, nombre, apellido, idProfesional);
		        	} 
				}	 
	}
	
	//obtiene los datos del dia laboral del profesional y llama a la instancia de la ventana encargada de la edicion (VentanaModificarHorario)
	// la instancia de la ventana recibe el sistema, la lista del horario, nombre y apellido del profesional
	private void editarDia(ActionEvent v) {
		this.horariolEnTabla=sistema.obtenerHorario(idProfesional);
		int[] filasSeleccionadas = this.horaProfesional.getTablaHorarioProfesional().getSelectedRows();
       
    	for (int fila : filasSeleccionadas)
    	{
        	if(this.horariolEnTabla.get(fila)!=null) {	 
        		List<HorarioDTO>horario=this.sistema.obtenerUnHorarios(this.horariolEnTabla.get(fila).idDiasLaborales);
        		this.modificarHorario.getInstance(sistema,horario,nombre,apellido);
        	}
		}	
	}

	
	// llamo a la instancia de la Ventana encargada de la alta de un nuevo dia laboral para el profesional (VentanaAltaHorario)
	private void agregarDia(ActionEvent n) {
		this.altaHorario= ControladorAltaHorario.getInstance(sistema,nombre,apellido,idProfesional);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
