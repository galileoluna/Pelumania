package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import dto.ProfesionalDTO;
import dto.UsuarioDTO;
import modelo.Sistema;
import presentacion.vista.VentanaAltaProfesional;
import presentacion.vista.VentanaAltaUsuario;
import presentacion.vista.VentanaUsuario;

public class ControladorAltaUsuario {
	private Sistema sistema;
	private static ControladorAltaUsuario INSTANCE;
	private static UsuarioDTO usuario;
	private VentanaAltaUsuario ventanaAltaUsuario;
	private static HashMap<String,Integer> roles;
	private ControladorUsuario controladorUsuario;
	
	
	public ControladorAltaUsuario (Sistema sistem, UsuarioDTO usuar) {
		this.ventanaAltaUsuario = VentanaAltaUsuario.getInstance();
		
		this.ventanaAltaUsuario.getBtnAgregar().addActionListener(l -> guardarUsuario(l));
		this.sistema = sistem;
		this.usuario = usuar;
	}


	public static ControladorAltaUsuario getInstance(Sistema sistema,UsuarioDTO usuario) {
		if ( INSTANCE == null) {
			INSTANCE = new ControladorAltaUsuario(sistema, usuario);
		}
		INSTANCE.roles=sistema.readRol();
		INSTANCE.ventanaAltaUsuario.getComboSucu().removeAllItems();
		INSTANCE.ventanaAltaUsuario.getComboSucu().addItem(usuario.getSucursal());
		llenarComboRol(INSTANCE.ventanaAltaUsuario.getComboPerm(),roles);
		INSTANCE.ventanaAltaUsuario.mostrarVentana();
		return INSTANCE;
	}
	
	
	private void  guardarUsuario(ActionEvent l) {
		String nombre=this.ventanaAltaUsuario.getTxtNombre().getText();
		String apellido=this.ventanaAltaUsuario.getTxtApellido().getText();
		String mail=this.ventanaAltaUsuario.getTxtMail().getText();
		String user=this.ventanaAltaUsuario.getTxtUser().getText();
		String pass=new String (this.ventanaAltaUsuario.getTxtPass().getPassword());
		String estado=this.ventanaAltaUsuario.getEstado().getSelectedItem().toString();
		int permiso=usuario.getRolById(this.ventanaAltaUsuario.getComboPerm().getSelectedItem().toString());
		int sucursal=usuario.getSucuById(this.ventanaAltaUsuario.getComboSucu().getSelectedItem().toString());
		Integer validador=validar(nombre,apellido,mail,user,pass,estado,permiso,sucursal);
		switch (validador) {
			case 1:
				UsuarioDTO usuarioNew= new UsuarioDTO(1,nombre,apellido,user,pass,mail,estado,permiso,sucursal);
				sistema.agregarUsuario(usuarioNew);
				ventanaAltaUsuario.cerrar();
				controladorUsuario.getInstance(sistema,usuario);
				break;
	
			case 2:
				this.ventanaAltaUsuario.mostrarErrorMail();
				break;
				
			case 0:
				this.ventanaAltaUsuario.errorCamposVacios();
				break;
				
			case 3:
				this.ventanaAltaUsuario.errorUsuarioExistente();
				break;
		}	
	}


	private int validar(String nombre, String apellido, String mail, String user, String pass, String estado,Integer permiso , Integer sucursal) {
		List<UsuarioDTO> usuario=sistema.obtenerUsuarios1();
		if(nombre.equals("") || nombre == null || apellido.equals("") || apellido == null || user.equals("") || user == null 
		|| pass.equals("") || pass == null || estado.equals("") || estado == null || permiso == null || sucursal == null ) {
				
			return 0;
		}else {
			if (!(Pattern.matches("^[a-zA-Z0-9]+[@]{1}+[a-zA-Z0-9]+[.]{1}+[a-zA-Z0-9]+$",mail))) {
				return 1;
			}
			for (UsuarioDTO u : usuario) {
				if(u.getNombreUsuario().equals(user)) {
					return 3;
				}
			}
				return 1;	
		}
		
	}
	
	private static void llenarComboRol(JComboBox combo, HashMap<String, Integer> roles2) {
		combo.removeAllItems();
		for (HashMap.Entry<String, Integer> datos : roles2.entrySet()) {
				combo.addItem(datos.getKey());
		}		
	}
	
}

