package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.Sistema;
import presentacion.vista.Login;
import presentacion.vista.NuevaVista;

public class ControladorLogin implements ActionListener{
	Login login;
	Sistema sistema;
	
	public ControladorLogin (Login login, Sistema sistema) {
		this.login = login;
		this.sistema = sistema;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
