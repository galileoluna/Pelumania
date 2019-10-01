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
	
	private ControladorProfesional(Sistema sistema) {
		this.ventanaProfesional = VentanaProfesional.getInstance();
		this.ventanaProfesional.getBtnAgregar().addActionListener(p ->agregarProfesional(p));
		this.ventanaProfesional.getBtnBorrar().addActionListener(s -> borrarProfesional(s));
		this.ventanaProfesional.getBtnEditar().addActionListener(t -> editarProfesional(t));
		this.ventanaProfesional.getBtnHorario().addActionListener(k -> verHorarios(k));
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
		        		this.getInstance(sistema);
		        	}
				}	
	}
	
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
	
}
