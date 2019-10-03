package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JOptionPane;

import dto.ProfesionalDTO;
import modelo.Sistema;
import presentacion.vista.VentanaAltaProfesional;
import presentacion.vista.VentanaCliente;
import presentacion.vista.VentanaHorarioProfesional;
import presentacion.vista.VentanaProfesional;

public class ControladorProfesional {
	private static ControladorProfesional INSTANCE;
	private List<ProfesionalDTO> profesionalEnTabla;
	private VentanaProfesional ventanaProfesional;
	private ControladorHorarioProfesional horarioProfesional;
	private ControladorAltaProfesional altaProfesional;
	public int idProfesional;
	private ControladorModificarProfesional modificarProfesional;
	private Sistema sistema;
	private ControladorServicioProfesional controlServProf;
	
	private ControladorProfesional(Sistema sistema) {
		this.ventanaProfesional = VentanaProfesional.getInstance();
		this.ventanaProfesional.getBtnAgregar().addActionListener(p ->agregarProfesional(p));
		this.ventanaProfesional.getBtnBorrar().addActionListener(s -> borrarProfesional(s));
		this.ventanaProfesional.getBtnEditar().addActionListener(t -> editarProfesional(t));
		this.ventanaProfesional.getBtnHorario().addActionListener(k -> verHorarios(k));
		this.ventanaProfesional.getBtnAsignar().addActionListener(f -> asignarServ(f));
		this.sistema = sistema;
	}

	// inicializo la intancia hago el new de la clase
	//lleno la tabla de los profesionales agregados
	public static ControladorProfesional getInstance(Sistema sistema) {
		if ( INSTANCE == null) {
			INSTANCE = new ControladorProfesional(sistema);
		}
		
		List<ProfesionalDTO> profesionalEnTabla=sistema.obtenerProfesional();
		INSTANCE.ventanaProfesional.llenarTabla(profesionalEnTabla);
		INSTANCE.ventanaProfesional.show();
		return INSTANCE;
	}
	
	// llamo a la ventana que se encarga de el agregado de un profesional (VentanaAltaProfesional)
	private void agregarProfesional(ActionEvent p) {
		this.altaProfesional = ControladorAltaProfesional.getInstance(sistema);
		
	}
	
	// Cambia el estado del profesional a inactivo 
	public void borrarProfesional(ActionEvent s)
	{
		this.profesionalEnTabla=sistema.obtenerProfesional();
		int[] filasSeleccionadas = this.ventanaProfesional.gettablaProfesional().getSelectedRows();
		       
	        	for (int fila : filasSeleccionadas)
	        	{
		        	if(this.profesionalEnTabla.get(fila)!=null) {	 
		        		int confirm = JOptionPane.showOptionDialog(null, "Estas seguro que deseas borrar al Profesional?","Confirmacion", JOptionPane.YES_NO_OPTION,
		   		             JOptionPane.QUESTION_MESSAGE, null, null, null);
		        		if (confirm == 0) {
						this.sistema.borrarProfesional(this.profesionalEnTabla.get(fila));
		        		}
		        		// llama a la instancia para refrescar tabla
		        		this.getInstance(sistema);
		        	}
				}	
	}
	
	//Obtiene el profesional seleccionado y llama a la instancia del controlador encargado de editar el profesional (ControladorModificarProfesional)
	//a la instancia del controlador le pasa todos los campos del profesional seleccionado, sistema y el id del profesional
	private void editarProfesional(ActionEvent t) {
		this.profesionalEnTabla=sistema.obtenerProfesional();
		int[] filasSeleccionadas = this.ventanaProfesional.gettablaProfesional().getSelectedRows();
       
    	for (int fila : filasSeleccionadas)
    	{
        	if(this.profesionalEnTabla.get(fila)!=null) {	 
        		this.idProfesional=this.profesionalEnTabla.get(fila).idProfesional;
        		
        		List<ProfesionalDTO>profesional=this.sistema.editarProfesional(this.profesionalEnTabla.get(fila).idProfesional);
        		this.modificarProfesional.getInstance(sistema,profesional,this.idProfesional);
        	}
		}	
	}
	
	//Obtiene el profesional seleccionado y llama a la instancia del controlador encargado de los horarios de cada profesional (ControladorHorarioProfesional)
	//a la instancia le pasa el sistema, nombre, apellido del profesional y id del profesional
	private void verHorarios(ActionEvent k) {
		this.profesionalEnTabla=sistema.obtenerProfesional();
		int[] filasSeleccionadas = this.ventanaProfesional.gettablaProfesional().getSelectedRows();
       
    	for (int fila : filasSeleccionadas)
    	{
        	if(this.profesionalEnTabla.get(fila)!=null) {	 
        		this.horarioProfesional= ControladorHorarioProfesional.getInstance(sistema,this.profesionalEnTabla.get(fila).getNombre(),this.profesionalEnTabla.get(fila).getApellido(),this.profesionalEnTabla.get(fila).getIdProfesional());
        	}
		}
		
	}
	
	// Obtiene el profesional seleccionado y llama a la instancia del controlador de la relacion servicio profesional (ControladorServicioProfesional)
	// a la instancia le pasa el sistema, id del profesional, nombre y apellido del empleado
	private void asignarServ(ActionEvent f) {
		this.profesionalEnTabla=sistema.obtenerProfesional();
		int[] filasSeleccionadas = this.ventanaProfesional.gettablaProfesional().getSelectedRows();
       
    	for (int fila : filasSeleccionadas)
    	{
        	if(this.profesionalEnTabla.get(fila)!=null) {
        		String nombreEmpl=this.profesionalEnTabla.get(fila).getNombre()+" "+this.profesionalEnTabla.get(fila).getApellido();
        		this.controlServProf=ControladorServicioProfesional.getInstance(sistema,this.profesionalEnTabla.get(fila).idProfesional, nombreEmpl);
        	}
		}	
	}
	
}
