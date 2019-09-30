package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import dto.ServicioDTO;
import modelo.Sistema;
import presentacion.vista.VentanaAgregarServicio;
import presentacion.vista.Vista;
import presentacion.vista.ventanaServicio;

public class Controlador implements ActionListener {
	private Vista vista;

	private VentanaAgregarServicio ventanaAgregarServicio; 
	private ControladorCliente controladorCliente; 
	private ControladorServicio controladorServicio;
	private ControladorProfesional controladorProfesional;
	private Sistema sistema;

	public Controlador(Vista vista, Sistema sistema)
	{
		this.vista = vista;
		this.sistema = sistema;
		this.ventanaAgregarServicio = VentanaAgregarServicio.getInstance();
		
		this.vista.getBtnAgregarCliente().addActionListener(a->ventanaAgregarCliente(a));
		this.vista.getBtnProfesional().addActionListener(l->ventanaProfesional(l));
		this.vista.getMnItmConsultarServicios().addActionListener(c->ventanaServicios(c));
		this.vista.getMnItmAgregarServicio().addActionListener(d -> ventanaAgregarServicio(d));

	}
 
	private void ventanaServicios(ActionEvent c) {
		List<ServicioDTO> serviciosEnTabla = this.sistema.obtenerServicios();
		ventanaServicio ventanaservicio = new ventanaServicio();
		
		ventanaservicio.llenarTabla(serviciosEnTabla);
		ventanaservicio.mostrar();
	}

	private void ventanaAgregarCliente(ActionEvent a) {
			this.controladorCliente = ControladorCliente.getInstance(sistema);
		}

	private void ventanaAgregarServicio(ActionEvent d) {
		this.controladorServicio = ControladorServicio.getInstance(sistema);
	}
	
	private void ventanaProfesional (ActionEvent b) {
		this.controladorProfesional= ControladorProfesional.getInstance(sistema);
	}
	
	public void actionPerformed(ActionEvent e) { }
}
