package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JOptionPane;

import dto.ProfesionalDTO;
import dto.UsuarioDTO;
import modelo.Sistema;
import presentacion.vista.VentanaAltaUsuario;
import presentacion.vista.VentanaUsuario;
import presentacion.vista.VentanaUsuario;

public class ControladorUsuario {
	private Sistema sistema;
	private static ControladorUsuario INSTANCE;
	private static UsuarioDTO usuario;
	private  VentanaUsuario ventanaUsuario;
	private VentanaAltaUsuario ventanaAltaUsuario;
	private List<UsuarioDTO> usuariosEnTabla;
	private ControladorAltaUsuario controladorAltaUsuario;
	private ControladorModificarUsuario controladoreditarUsuario;
	

	private ControladorUsuario(Sistema sist, UsuarioDTO usuar) {
		ventanaUsuario = ventanaUsuario.getInstance();
		ventanaUsuario.getBtnAgregar().addActionListener(p ->agregarUsuario(p));
		ventanaUsuario.getBtnBorrar().addActionListener(s -> borrarUsuario(s));
		ventanaUsuario.getBtnEditar().addActionListener(t -> editarUsuario(t));
		
		sistema = sist;
		usuario = usuar;
	}

	private void editarUsuario(ActionEvent t) {
		this.usuariosEnTabla=sistema.obtenerUsuarios(usuario.getIdSucursal());
		int[] filasSeleccionadas = this.ventanaUsuario.gettablaUsuario().getSelectedRows();
		       
	        	for (int fila : filasSeleccionadas)
	        	{
		        	if(this.usuariosEnTabla.get(fila)!=null) {	 
		        		List<UsuarioDTO>usuario_a_editar=this.sistema.obtenerUnUsuario(this.usuariosEnTabla.get(fila).getIdUsuario());
		        		System.out.println(usuario_a_editar);
		        			controladoreditarUsuario= ControladorModificarUsuario.getInstance(sistema,usuario,usuario_a_editar);
		        	}	
		        		
	        	}
	
		
	}

	public static ControladorUsuario getInstance(Sistema sistema, UsuarioDTO usuario) {
		if ( INSTANCE == null) {
			INSTANCE = new ControladorUsuario(sistema, usuario);
		}
		
		List<UsuarioDTO> usuariosEnTabla=sistema.obtenerUsuarios(usuario.getIdSucursal());
		INSTANCE.ventanaUsuario.llenarTabla(usuariosEnTabla);
		INSTANCE.ventanaUsuario.show();
		return INSTANCE;
	}
	
	private void agregarUsuario(ActionEvent p) {
		this.controladorAltaUsuario= ControladorAltaUsuario.getInstance(sistema,usuario);
	}
	
	private void borrarUsuario(ActionEvent s) {
		this.usuariosEnTabla=sistema.obtenerUsuarios(usuario.getIdSucursal());
		int[] filasSeleccionadas = this.ventanaUsuario.gettablaUsuario().getSelectedRows();
		       
	        	for (int fila : filasSeleccionadas)
	        	{
		        	if(this.usuariosEnTabla.get(fila)!=null) {	 
		        		int confirm = this.ventanaUsuario.mostrarConfirmacionBorrar();
		        		if (confirm == 0)
		        			this.sistema.eliminarUsuario(this.usuariosEnTabla.get(fila));
		        			
		        		}
		        		// llama a la instancia para refrescar tabla
		        		this.getInstance(sistema,usuario);
	        	}
	
	}
}
