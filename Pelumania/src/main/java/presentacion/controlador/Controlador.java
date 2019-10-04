package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modelo.Sistema;
import presentacion.vista.Vista;

public class Controlador implements ActionListener {
	private Vista vista;

	private ControladorCliente controladorCliente;
	private ControladorServicio controladorServicio;
	private ControladorProfesional controladorProfesional;
	//para pruebas
	private ControladorAgregarCita controladoragregarcita;
	// fin pruebas
	private Sistema sistema;

	public Controlador(Vista vista, Sistema sistema)
	{
		this.vista = vista;
		this.sistema = sistema;

		this.vista.getBtnAgregarCliente().addActionListener(a->ventanaAgregarCliente(a));
		this.vista.getMnItmConsultarServicios().addActionListener(c->ventanaServicios(c));
		this.vista.getMenuProfesional().addActionListener(l->ventanaProfesional(l));

		//pruebas
		this.vista.getBtnAgregarCita().addActionListener(d -> ventanaAgregarCita(d));
		//pruebas
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

	private void ventanaAgregarCita(ActionEvent d) {
		this.controladoragregarcita = ControladorAgregarCita.getInstance(sistema);
	}

	@Override
	public void actionPerformed(ActionEvent e) { }
}
