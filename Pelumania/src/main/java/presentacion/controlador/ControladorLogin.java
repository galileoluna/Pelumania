package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dto.UsuarioDTO;
import modelo.Sistema;
import presentacion.vista.Login;
import presentacion.vista.NuevaVista;
import presentacion.vista.Vista;

public class ControladorLogin implements ActionListener{
	Login login;
	Sistema sistema;
	Vista vista;
	Controlador controlador;
	
	public ControladorLogin (Login login, Sistema sistema) {
		this.login = login;
		this.sistema = sistema;
		this.login.getIniciar().addActionListener(l -> IniciarPelumania(l));
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
			public void keyPressed(KeyEvent arg0) {}
		});
		this.login.getIniciar().addActionListener(l -> IniciarPelumania(l));
				
	}

	private void IniciarPelumania(ActionEvent l) {
		String pass=new String (login.getPass().getPassword());
		List<UsuarioDTO> usuario=this.sistema.IniciarSesion(login.getUser().getText(),pass);
		if(usuario.size()!=0) {
			this.login.cerrar();
			Vista vista = new Vista();
	        Controlador controlador = new Controlador(vista, sistema);
		}else {
			JOptionPane.showMessageDialog(null, "Los datos ingresados son incorrectos");
		}
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
