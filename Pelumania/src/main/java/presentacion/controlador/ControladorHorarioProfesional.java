package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;

import dto.HorarioDTO;
import dto.ProfesionalDTO;
import modelo.Sistema;
import presentacion.vista.VentanaAltaHorario;
import presentacion.vista.VentanaAltaProfesional;
import presentacion.vista.VentanaHorarioProfesional;

public class ControladorHorarioProfesional implements ActionListener{
	private Sistema sistema;
	private VentanaHorarioProfesional horaProfesional;
	private List<HorarioDTO> horariolEnTabla;
	private int idProfesional;
	private String nombre;
	private String apellido;
	private ControladorAltaHorario altaHorario;
	private ControladorAltaHorario controladorAltaHorario;
	private static ControladorHorarioProfesional INSTANCE;
	
	
	private ControladorHorarioProfesional(Sistema sistema) {
		this.horaProfesional = VentanaHorarioProfesional.getInstance();
		this.horaProfesional.getBtnBorrar().addActionListener(m -> borrarDia(m));
		this.horaProfesional.getBtnAgregar().addActionListener(n -> agregarDia(n));
		this.sistema = sistema;
	}
	
	

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
	
	private void borrarDia(ActionEvent m) {
		this.horariolEnTabla=sistema.obtenerHorario(idProfesional);
		int[] filasSeleccionadas = this.horaProfesional.getTablaHorarioProfesional().getSelectedRows();
		       
	        	for (int fila : filasSeleccionadas) {
		        	if(this.horariolEnTabla.get(fila)!=null) {	 
		        		int confirm = JOptionPane.showOptionDialog(null, "Estas seguro que deseas borrar al Profesional?","Confirmacion", JOptionPane.YES_NO_OPTION,
		   		             JOptionPane.QUESTION_MESSAGE, null, null, null);
		        		if (confirm == 0) {
						this.sistema.borrarHorario(this.horariolEnTabla.get(fila));
		        		}
		        		this.getInstance(sistema, nombre, apellido, idProfesional);
		        	} 
				}	 
	}
	
	private void agregarDia(ActionEvent n) {
		this.altaHorario= ControladorAltaHorario.getInstance(sistema);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
