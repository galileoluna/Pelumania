package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.util.List;
import dto.ProfesionalDTO;
import dto.UsuarioDTO;
import modelo.Sistema;
import presentacion.vista.VentanaProfesional;

public class ControladorProfesional {
	private static ControladorProfesional INSTANCE;
	private List<ProfesionalDTO> profesionalEnTabla;
	private static VentanaProfesional ventanaProfesional;
	private ControladorHorarioProfesional horarioProfesional;
	private ControladorAltaProfesional altaProfesional;
	public int idProfesional;
	private ControladorModificarProfesional modificarProfesional;
	private Sistema sistema;
	private ControladorServicioProfesional controlServProf;
	private static UsuarioDTO usuario;
	
	private ControladorProfesional(Sistema sist, UsuarioDTO usuar) {
		ventanaProfesional = VentanaProfesional.getInstance();
		ventanaProfesional.getBtnAgregar().addActionListener(p ->agregarProfesional(p));
		ventanaProfesional.getBtnBorrar().addActionListener(s -> borrarProfesional(s,0));
		ventanaProfesional.getBtnEditar().addActionListener(t -> editarProfesional(t));
		ventanaProfesional.getBtnHorario().addActionListener(k -> verHorarios(k));
		ventanaProfesional.getBtnAsignar().addActionListener(f -> asignarServ(f));
		ventanaProfesional.getBtnBuscar().addActionListener(y -> buscar(y));
		sistema = sist;
		usuario = usuar;
	}

	// inicializo la intancia hago el new de la clase
	//lleno la tabla de los profesionales agregados
	public static ControladorProfesional getInstance(Sistema sistema, UsuarioDTO usuario) {
		if ( INSTANCE == null) {
			INSTANCE = new ControladorProfesional(sistema, usuario);
		}
		
		
		List<ProfesionalDTO> profesionalEnTabla=sistema.obtenerProfesional();
		if(usuario.getIdRol() == 1 || usuario.getIdRol() == 5) {
			INSTANCE.ventanaProfesional.llenarTabla(profesionalEnTabla,-1);
			INSTANCE.ventanaProfesional.show();
		}else {
			INSTANCE.ventanaProfesional.llenarTabla(profesionalEnTabla,usuario.getIdSucursal());
			INSTANCE.ventanaProfesional.show();
		}
		getPermisos();
		return INSTANCE;
	}
	
	// llamo a la ventana que se encarga de el agregado de un profesional (VentanaAltaProfesional)
	private void agregarProfesional(ActionEvent p) {
		this.altaProfesional = ControladorAltaProfesional.getInstance(sistema,usuario);
		
	}
	
	// Cambia el estado del profesional a inactivo 
	public void borrarProfesional(ActionEvent s, int borro)
	{
		this.profesionalEnTabla=sistema.obtenerProfesional();
		int[] filasSeleccionadas = this.ventanaProfesional.gettablaProfesional().getSelectedRows();
		       
	        	for (int fila : filasSeleccionadas)
	        	{
		        	if(this.profesionalEnTabla.get(fila)!=null) {	 
		        		int confirm = this.ventanaProfesional.mostrarConfirmacionBorrar();
		        		if (confirm == 0)
		        			this.sistema.borrarProfesional(this.profesionalEnTabla.get(fila));
		        			
		        		}
		        		// llama a la instancia para refrescar tabla
		        		this.getInstance(sistema,usuario);
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
        		this.modificarProfesional.getInstance(sistema,profesional,this.idProfesional,usuario);
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
	
	private static void getPermisos() {
		int rol= usuario.getIdRol();
		switch(rol) {
		  case 1:
		  case 5:
		    
		    break;
		  case 2:
		    // code block
		    break;
		  case 3:
			  ventanaProfesional.getBtnAgregar().setEnabled(false);
			  ventanaProfesional.getBtnAsignar().setEnabled(false);
			  ventanaProfesional.getBtnBorrar().setEnabled(false);
			  ventanaProfesional.getBtnEditar().setEnabled(false);
			  ventanaProfesional.getBtnHorario().setEnabled(false);
			  break;
		  case 4:
			 
			 break;
		}		
	}
	
	private void buscar(ActionEvent y) {
		String variable=this.ventanaProfesional.getVariableBuscar().getSelectedItem().toString();
		String value=this.ventanaProfesional.getBuscador().getText();
		
		if(usuario.getIdRol() == 1 || usuario.getIdRol() == 5 ) {
			this.ventanaProfesional.llenarTabla(this.sistema.obtenerProfesionalConBuscador(variable,value),-1);
		}else {
			this.ventanaProfesional.llenarTabla(this.sistema.obtenerProfesionalConBuscador(variable,value),usuario.getIdSucursal());
		}
	}
	
}
