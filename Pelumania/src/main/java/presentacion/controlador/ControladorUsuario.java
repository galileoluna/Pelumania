package presentacion.controlador;

import java.util.List;

import dto.ProfesionalDTO;
import dto.UsuarioDTO;
import modelo.Sistema;
import presentacion.vista.VentanaUsuario;
import presentacion.vista.VentanaUsuario;

public class ControladorUsuario {
	private Sistema sistema;
	private static ControladorUsuario INSTANCE;
	private static UsuarioDTO usuario;
	private  VentanaUsuario ventanaUsuario;
	
	

	private ControladorUsuario(Sistema sist, UsuarioDTO usuar) {
		ventanaUsuario = ventanaUsuario.getInstance();
		/*ventanaUsuario.getBtnAgregar().addActionListener(p ->agregarUsuario(p));
		ventanaUsuario.getBtnBorrar().addActionListener(s -> borrarUsuario(s,0));
		ventanaUsuario.getBtnEditar().addActionListener(t -> editarUsuario(t));*/
		
		sistema = sist;
		usuario = usuar;
	}
	
	public static ControladorUsuario getInstance(Sistema sistema, UsuarioDTO usuario) {
		if ( INSTANCE == null) {
			INSTANCE = new ControladorUsuario(sistema, usuario);
		}
		
		
		List<UsuarioDTO> usuariosEnTabla=sistema.obtenerUsuarios();
	
		INSTANCE.ventanaUsuario.llenarTabla(usuariosEnTabla);
		INSTANCE.ventanaUsuario.show();
		//getPermisos();
		return INSTANCE;
	}
	
}
