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
		String validador=validar(nombre,apellido,mail,user,pass,estado,permiso,sucursal);
		if(validador.equals("true")) {
			UsuarioDTO usuarioNew= new UsuarioDTO(1,nombre,apellido,user,pass,mail,estado,permiso,sucursal);
			sistema.agregarUsuario(usuarioNew);
			ventanaAltaUsuario.cerrar();
			controladorUsuario.getInstance(sistema,usuario);
		}else {
			if(validador.equals("false")) {
			JOptionPane.showMessageDialog(null, "Por favor ingrese todos los datos", "Error", JOptionPane.ERROR_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(null, "Por favor ingrese un mail existente", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}


	private String validar(String nombre, String apellido, String mail, String user, String pass, String estado,Integer permiso , Integer sucursal) {
		if(nombre.equals("") || nombre == null || apellido.equals("") || apellido == null || user.equals("") || user == null 
		|| pass.equals("") || pass == null || estado.equals("") || estado == null || permiso == null || sucursal == null ) {
				
			return "false";
		}else {
			if (!(Pattern.matches("^[a-zA-Z0-9]+[@]{1}+[a-zA-Z0-9]+[.]{1}+[a-zA-Z0-9]+$",mail))) {
				return "casi";
			}else {
				return "true";
			}
		}
		
	}
	
	private static void llenarComboRol(JComboBox combo, HashMap<String, Integer> roles2) {
		for (HashMap.Entry<String, Integer> datos : roles2.entrySet()) {
				combo.addItem(datos.getKey());
		}		
	}
	
}

