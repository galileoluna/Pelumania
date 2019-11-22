package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.JOptionPane;

import dto.UsuarioDTO;
import modelo.Sistema;
import presentacion.vista.Login;
import presentacion.vista.NuevaVista;
import util.MailService;

public class ControladorLogin implements ActionListener{
	Login login;
	Sistema sistema;
	NuevaVista nvista;
	Controlador2 controlador;
	
	public ControladorLogin (Login login, Sistema sistema) {
		this.login = login;
		this.sistema = sistema;
		// valido el largo de los caracteres usuario
		this.login.getUser().addKeyListener(new KeyListener(){
			public void keyTyped(KeyEvent e) {
				if (login.getUser().getText().length()== 8)
			     e.consume();
			}
			public void keyPressed(KeyEvent arg0) {}
			public void keyReleased(KeyEvent arg0) {}
			}
		);
		this.login.getPass().addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				String pass= new String (login.getPass().getPassword());
				if(pass.length()==8) {
					e.consume();
				}
			}
			@Override
			public void keyReleased(KeyEvent arg0) {}
			@Override
			public void keyPressed(KeyEvent event) {
				if (event.getKeyCode() == KeyEvent.VK_ENTER) {
					IniciarPelumania(null);
				}
			}
		});
		this.login.getIniciar().addActionListener(l -> IniciarPelumania(l));
		this.login.getRecuperarPass().addActionListener(l -> recuperarPass());
				
	}

	private void IniciarPelumania(ActionEvent l) {
		String pass=new String (login.getPass().getPassword());
		List<UsuarioDTO> listUser=this.sistema.IniciarSesion(login.getUser().getText(),pass);
		UsuarioDTO usuario=getUSer(listUser);
		if(listUser.size()!=0) {
			this.login.cerrar();
			NuevaVista nvista = new NuevaVista();
	        new Controlador2(nvista, sistema,usuario);
		}else {
			this.login.mostrarErrorDatos();
		}
	}


	private UsuarioDTO getUSer(List<UsuarioDTO> listUser) {
		UsuarioDTO user=null;
		for(UsuarioDTO u :listUser) {
			user= new UsuarioDTO(u.getIdUsuario(), u.getNombre(), u.getApellido(), u.getNombreUsuario(), u.getContrasenia(), u.getMail(), u.getEstado(), u.getIdRol(), u.getIdSucursal());
		}
		return user;
		
	}

	private void recuperarPass() {
		String username = login.getUser().getText();
		
		if(existeUsuario(username)){
			UsuarioDTO user = this.sistema.obtenerUsuarioByUsername(username);
			user.setContrasenia(user.getApellido().subSequence(0, 4)+"123");
			this.sistema.editarUsuario(user);
			MailService.enviarPassProvisorio(user);
			this.login.mostrarEnvioMail();
		
		} else {
			this.login.mostarErrorUsuarioInvalido();
		}
	}

	private boolean existeUsuario(String user) {
		if(user != null && !user.equals("")) {
		 	UsuarioDTO usuarioBuscado = this.sistema.obtenerUsuarioByUsername(user);
			if (usuarioBuscado != null) {
				return true;
			}
		} 
	  return false;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
