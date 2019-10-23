package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
		
	}

	private void IniciarPelumania(ActionEvent l) {
		//PONER VALIDACIONES PARA EL INICIAR SESION ACA
		
		this.login.cerrar();
		Vista vista = new Vista();
        Controlador controlador = new Controlador(vista, sistema);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
