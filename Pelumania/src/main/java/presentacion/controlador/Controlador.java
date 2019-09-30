package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modelo.Sistema;
import presentacion.vista.VentanaAgregarServicio;
import presentacion.vista.Vista;

public class Controlador implements ActionListener {
	private Vista vista;

	private VentanaAgregarServicio ventanaAgregarServicio; 
	private ControladorCliente controladorCliente; 
	private ControladorServicio controladorServicio;
	private Sistema sistema;

	public Controlador(Vista vista, Sistema sistema)
	{
		this.vista = vista;
		this.sistema = sistema;
		this.vista.getBtnServicios().addActionListener(a->ventanaAgregarServicio(a));
		this.ventanaAgregarServicio = VentanaAgregarServicio.getInstance();
		
		this.vista.getBtnAgregarCliente().addActionListener(a->ventanaAgregarCliente(a));

	}

	private void ventanaAgregarCliente(ActionEvent a) {
			this.controladorCliente = ControladorCliente.getInstance(sistema);
		}

	private void ventanaAgregarServicio(ActionEvent a) {
		this.controladorServicio = ControladorServicio.getInstance(sistema);
	}
	
	public void actionPerformed(ActionEvent e) { }
}
