package presentacion.controlador;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import dto.UsuarioDTO;
import modelo.Sistema;
import presentacion.vista.VentanaModificarUsuario;

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
			Integer validador=validar(nombre,apellido,mail,user,pass,estado,permiso,sucursal);
			switch (validador) {
				case 1:
					UsuarioDTO usuarioNew= new UsuarioDTO(idUsuarioAModificar,nombre,apellido,user,pass,mail,estado,permiso,sucursal);
					System.out.println(usuarioNew.getIdUsuario()+" "+usuarioNew);
					sistema.editarUsuario(usuarioNew);
					ventanaEditarUsuario.cerrar();
					controladorUsuario.getInstance(sistema,usuario);
					break;
		
				case 2:
					this.ventanaEditarUsuario.mostrarErrorMail();
					break;
					
				case 0:
					this.ventanaEditarUsuario.errorCamposVacios();
					break;
					
				case 3:
					this.ventanaEditarUsuario.errorUsuarioExistente();
					break;
			}	
		}


		private int validar(String nombre, String apellido, String mail, String user, String pass, String estado,Integer permiso , Integer sucursal) {
			List<UsuarioDTO> usuario=sistema.obtenerUsuarios1();
			if(nombre.equals("") || nombre == null || apellido.equals("") || apellido == null || user.equals("") || user == null 
			|| pass.equals("") || pass == null || estado.equals("") || estado == null || permiso == null || sucursal == null ) {
					
				return 0;
			}else {
				 Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
				 Matcher mather = pattern.matcher(mail);
				if (!mather.find()) {
					return 2;
				}
				for (UsuarioDTO u : usuario) {
					int cont=0;
					if(u.getNombreUsuario().equals(user)) {	
						cont++;
					}
					if(cont > 1) {
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


	

