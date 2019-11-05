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
import presentacion.vista.VentanaModificarUsuario;
import presentacion.vista.VentanaUsuario;

	public class ControladorModificarUsuario {
		private Sistema sistema;
		private static ControladorModificarUsuario INSTANCE;
		private static UsuarioDTO usuario;
		private VentanaModificarUsuario ventanaEditarUsuario;
		private static HashMap<String,Integer> roles;
		private ControladorUsuario controladorUsuario;
		private static int idUsuarioAModificar;
		
		
		public ControladorModificarUsuario (Sistema sistem, UsuarioDTO usuar) {
			this.ventanaEditarUsuario = VentanaModificarUsuario.getInstance();
			
			this.ventanaEditarUsuario.getBtnAgregar().addActionListener(l -> guardarUsuario(l));
			this.sistema = sistem;
			this.usuario = usuar;
		}


		public static ControladorModificarUsuario getInstance(Sistema sistema,UsuarioDTO usuario,List<UsuarioDTO> usuario_a_editar) {
			if ( INSTANCE == null) {
				INSTANCE = new ControladorModificarUsuario(sistema, usuario);
			}
			
			cargarDatos(INSTANCE.ventanaEditarUsuario,usuario_a_editar);
			INSTANCE.roles=sistema.readRol();
			INSTANCE.ventanaEditarUsuario.getComboSucu().removeAllItems();
			INSTANCE.ventanaEditarUsuario.getComboSucu().addItem(usuario.getSucursal());
			llenarComboRol(INSTANCE.ventanaEditarUsuario.getComboPerm(),roles);
			INSTANCE.ventanaEditarUsuario.mostrarVentana();
			return INSTANCE;
		}
		
		
		private static void cargarDatos(VentanaModificarUsuario ventanaEditarUsuario2, List<UsuarioDTO> usuario_a_editar) {
			for(UsuarioDTO u : usuario_a_editar) {
				idUsuarioAModificar=u.getIdUsuario();
				ventanaEditarUsuario2.getTxtNombre().setText(u.getNombre());
				ventanaEditarUsuario2.getTxtApellido().setText(u.getApellido());
				ventanaEditarUsuario2.getTxtMail().setText(u.getMail());
				ventanaEditarUsuario2.getTxtUser().setText(u.getNombreUsuario());
				ventanaEditarUsuario2.getTxtPass().setText(u.getContrasenia());
				ventanaEditarUsuario2.getComboPerm().setSelectedItem(u.getRol());
				ventanaEditarUsuario2.getComboSucu().setSelectedItem(u.getSucursal());
				ventanaEditarUsuario2.getEstado().setSelectedItem(u.getEstado());
				
			}
			
		}


		private void  guardarUsuario(ActionEvent l) {
			String nombre=this.ventanaEditarUsuario.getTxtNombre().getText();
			String apellido=this.ventanaEditarUsuario.getTxtApellido().getText();
			String mail=this.ventanaEditarUsuario.getTxtMail().getText();
			String user=this.ventanaEditarUsuario.getTxtUser().getText();
			String pass=new String (this.ventanaEditarUsuario.getTxtPass().getPassword());
			String estado=this.ventanaEditarUsuario.getEstado().getSelectedItem().toString();
			int permiso=usuario.getRolById(this.ventanaEditarUsuario.getComboPerm().getSelectedItem().toString());
			int sucursal=usuario.getSucuById(this.ventanaEditarUsuario.getComboSucu().getSelectedItem().toString());
			String validador=validar(nombre,apellido,mail,user,pass,estado,permiso,sucursal);
			switch (validador) {
				case "true":
					UsuarioDTO usuarioNew= new UsuarioDTO(idUsuarioAModificar,nombre,apellido,user,pass,mail,estado,permiso,sucursal);
					System.out.println(usuarioNew.getIdUsuario()+" "+usuarioNew);
					sistema.editarUsuario(usuarioNew);
					ventanaEditarUsuario.cerrar();
					controladorUsuario.getInstance(sistema,usuario);
					break;
		
				case "casi":
					JOptionPane.showMessageDialog(null, "Por favor ingrese un mail existente", "Error", JOptionPane.ERROR_MESSAGE);
					break;
				case "false":
					JOptionPane.showMessageDialog(null, "Por favor ingrese todos los datos", "Error", JOptionPane.ERROR_MESSAGE);
					break;
					
				case "nombre":
					JOptionPane.showMessageDialog(null, "Por favor ingrese otro nombre de usuario, el que intenta usar ya existe", "Error", JOptionPane.ERROR_MESSAGE);
					break;
			}	
		}


		private String validar(String nombre, String apellido, String mail, String user, String pass, String estado,Integer permiso , Integer sucursal) {
			List<UsuarioDTO> usuario=sistema.obtenerUsuarios1();
			if(nombre.equals("") || nombre == null || apellido.equals("") || apellido == null || user.equals("") || user == null 
			|| pass.equals("") || pass == null || estado.equals("") || estado == null || permiso == null || sucursal == null ) {
					
				return "false";
			}else {
				if (!(Pattern.matches("^[a-zA-Z0-9]+[@]{1}+[a-zA-Z0-9]+[.]{1}+[a-zA-Z0-9]+$",mail))) {
					return "casi";
				}
				for (UsuarioDTO u : usuario) {
					int cont=0;
					if(u.getNombreUsuario().equals(user)) {	
						cont++;
					}
					if(cont >= 1) {
						return "nombre";
					}
				}
				
					return "true";	
			}
			
		}
		
		private static void llenarComboRol(JComboBox combo, HashMap<String, Integer> roles2) {
			combo.removeAllItems();
			for (HashMap.Entry<String, Integer> datos : roles2.entrySet()) {
					combo.addItem(datos.getKey());
			}		
		}
		
}


	

