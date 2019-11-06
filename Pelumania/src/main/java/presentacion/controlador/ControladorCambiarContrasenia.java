package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.util.List;

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
			JOptionPane.showMessageDialog(null, "La contraseña nueva no coincide", "Error", JOptionPane.ERROR_MESSAGE);
			break;

		case 1:
			sistema.cambiarContrasenia(usuario.getIdUsuario(), nuevaPass);
			JOptionPane.showMessageDialog(null, "Su contraseña fue cambiada con exito", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
			ventanaChangePass.cerrar();
			break;
		
		case 2:
			
			JOptionPane.showMessageDialog(null, "La contraseña actual no coincide", "Error", JOptionPane.ERROR_MESSAGE);
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
