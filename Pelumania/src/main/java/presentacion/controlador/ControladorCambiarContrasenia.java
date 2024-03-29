package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;

import dto.UsuarioDTO;
import modelo.Sistema;
import presentacion.vista.VentanaCambioContrasenia;

public class ControladorCambiarContrasenia {
	private Sistema sistema;
	private UsuarioDTO usuario;
	//private Controlador2 controlador;
	private VentanaCambioContrasenia ventanaChangePass;
	private static ControladorCambiarContrasenia INSTANCE;
	
	private ControladorCambiarContrasenia(Sistema sist, UsuarioDTO usuar) {
		ventanaChangePass = VentanaCambioContrasenia.getInstance();
		ventanaChangePass.getBtnguardar().addActionListener(p ->cambiarPass(p));
		this.ventanaChangePass.getContraVieja().addKeyListener(new KeyListener() {
					
					@Override
					public void keyTyped(KeyEvent e) {
						String pass= new String (ventanaChangePass.getContraVieja().getPassword());
						if(pass.length()==8) {
							e.consume();
						}
					}
					@Override
					public void keyReleased(KeyEvent arg0) {}
					@Override
					public void keyPressed(KeyEvent arg0) {}
				});
		
		this.ventanaChangePass.getContraNueva().addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				String pass= new String (ventanaChangePass.getContraNueva().getPassword());
				if(pass.length()==8) {
					e.consume();
				}
			}
			@Override
			public void keyReleased(KeyEvent arg0) {}
			@Override
			public void keyPressed(KeyEvent arg0) {}
		});
		
		this.ventanaChangePass.getContraNueva2().addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				String pass= new String (ventanaChangePass.getContraNueva2().getPassword());
				if(pass.length()==8) {
					e.consume();
				}
			}
			@Override
			public void keyReleased(KeyEvent arg0) {}
			@Override
			public void keyPressed(KeyEvent arg0) {}
		});

		sistema = sist;
		usuario = usuar;
	}

	public static ControladorCambiarContrasenia getInstance(Sistema sistema, UsuarioDTO usuario) {
		if ( INSTANCE == null) {
			INSTANCE = new ControladorCambiarContrasenia(sistema, usuario);
		}
		INSTANCE.ventanaChangePass.getNombreUser().setText(usuario.getNombre()+" "+usuario.getApellido());
		INSTANCE.ventanaChangePass.show();
		return INSTANCE;
	}

	
	private void cambiarPass(ActionEvent p) {
		String vieja=new String (ventanaChangePass.getContraVieja().getPassword());
		String nuevaPass=new String (ventanaChangePass.getContraNueva().getPassword());
		String nuevaPass2=new String (ventanaChangePass.getContraNueva2().getPassword());
		int valid=validar(vieja,nuevaPass,nuevaPass2);
		switch (valid) {
		case 0:
			this.ventanaChangePass.mostrarErrorPassNoCoincide();
			break;

		case 1:
			if (sistema.cambiarContrasenia(usuario.getIdUsuario(), nuevaPass)) {
				this.ventanaChangePass.mostrarExito();
			}
			
			ventanaChangePass.cerrar();
			break;
		
		case 2:
			
			this.ventanaChangePass.mostrarErrorPassActual();
			break;
		}
		
	}

	private int validar(String vieja, String nuevaPass, String nuevaPass2) {
		if(vieja.equals(usuario.getContrasenia())) {
			if(nuevaPass.equals(nuevaPass2)) {
				return 1;
			}else {
				return 0;
			}
		}else {
			return 2;
		}
	}
	
}
