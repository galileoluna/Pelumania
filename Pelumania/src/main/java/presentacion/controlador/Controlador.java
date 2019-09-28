package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modelo.Sistema;
import presentacion.vista.VentanaAgregarServicio;
import presentacion.vista.Vista;

public class Controlador implements ActionListener {
	private Vista vista;

	private VentanaAgregarServicio ventanaAgregarServicio; 
	private Sistema sistema;

	public Controlador(Vista vista, Sistema sistema)
	{
		this.vista = vista;
		this.vista.getBtnServicios().addActionListener(a->ventanaAgregarServicio(a));
		this.ventanaAgregarServicio = VentanaAgregarServicio.getInstance();
		this.sistema = sistema;
	}

	private void ventanaAgregarServicio(ActionEvent a) {
		this.ventanaAgregarServicio.mostrarVentana();
	}
	
	public void actionPerformed(ActionEvent e) { }
}
