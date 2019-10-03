package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modelo.Sistema;
import presentacion.vista.VentanaProfesional;
import presentacion.vista.Vista;
import presentacion.vista.VentanaServicio;

public class Controlador implements ActionListener {
	private Vista vista;

	private ControladorCliente controladorCliente;
	private ControladorServicio controladorServicio;
	private ControladorProfesional controladorProfesional;
	private Sistema sistema;

	public Controlador(Vista vista, Sistema sistema)
	{
		this.vista = vista;
		this.sistema = sistema;
		
		this.vista.getBtnAgregarCliente().addActionListener(a->ventanaAgregarCliente(a));
		this.vista.getMnItmConsultarServicios().addActionListener(c->ventanaServicios(c));
		this.vista.getMenuProfesional().addActionListener(l->ventanaProfesional(l));

	}
	
	private void ventanaServicios(ActionEvent c) {
		this.controladorServicio = ControladorServicio.getInstance(sistema);
	}
 
	private void ventanaAgregarCliente(ActionEvent a) {
			this.controladorCliente = ControladorCliente.getInstance(sistema);
		}

	private void ventanaProfesional (ActionEvent b) {
		this.controladorProfesional= ControladorProfesional.getInstance(sistema);
	}
	
	public void actionPerformed(ActionEvent e) { }
}
