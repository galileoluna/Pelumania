package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Date;
import java.util.List;

import javax.swing.event.AncestorListener;

import dto.CitaDTO;
import modelo.Sistema;
import presentacion.vista.VentanaCliente;
import presentacion.vista.Vista;

public class Controlador implements ActionListener {
	private Vista vista;
	private ControladorCliente controladorCliente;
	private ControladorServicio controladorServicio;
	private List<CitaDTO> citasEnTabla;
	private ControladorProfesional controladorProfesional;
	private ControladorAgregarCita controladoragregarcita;
	private ControladorPromocion controladorPromocion;
	private ControladorSucursal controladorSucursal;
	private ControladorPromosionesVigentes controladorPromoVigente;
	private ControladorCaja controladorCaja;
	
	private Sistema sistema;

	public Controlador(Vista vista, Sistema sistema)
	{
		this.vista = vista; 
		this.sistema = sistema;
		// Con esta funcion lleno la tabla dependiendo el dia que se selecciona
		this.vista.getCalendario().addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent arg0) {
				String dia = Integer.toString(Controlador.this.vista.getCalendario().getDayChooser().getDay());
				String mes =Integer.toString(Controlador.this.vista.getCalendario().getMonthChooser().getMonth()+1);
				String anio =Integer.toString(Controlador.this.vista.getCalendario().getYearChooser().getYear());
				//A obtenerTablaCita se le pasa el dia que se selecciona como un string EJ: 2019-10-06
				Controlador.this.citasEnTabla=Controlador.this.sistema.obtenerTablaCita(anio+"-"+mes+"-"+dia);
				System.out.println("estoy en el controlador"+Controlador.this.citasEnTabla);
				Controlador.this.vista.llenarTabla(Controlador.this.citasEnTabla);
				
			}
		});
		// fin funcion que llena la tabla
		this.vista.getMnItmConsultarServicios().addActionListener(c->ventanaServicios(c));
		this.vista.getMenuProfesional().addActionListener(l->ventanaProfesional(l));
		this.vista.getMenuConsultaClientes().addActionListener(l -> ventanaAgregarCliente(l));
		this.vista.getBtnAgregarCita().addActionListener(d -> ventanaAgregarCita(d));
		this.vista.getMenuPromocion().addActionListener(m -> ventanaPromocion(m));
		this.vista.getMenuPromoVigente().addActionListener(p -> verPromosVigentes(p));
		this.vista.getMenuSucursal().addActionListener(e -> ventanaSucursal(e));
		this.vista.getMenuCaja().addActionListener(a -> ventanaCaja(a));
	}


	private void ventanaCaja(ActionEvent a) {
		this.controladorCaja = ControladorCaja.getInstance(sistema);
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

	private void ventanaPromocion(ActionEvent m) {
		this.controladorPromocion= ControladorPromocion.getInstance(sistema);
	}
	
	private void ventanaSucursal (ActionEvent b) {
		this.controladorSucursal= ControladorSucursal.getInstance(sistema);
	}

	private void ventanaAgregarCita(ActionEvent d) {
		int dia = this.vista.getCalendario().getDayChooser().getDay();
		int mes = this.vista.getCalendario().getMonthChooser().getMonth();
		int anio = this.vista.getCalendario().getYearChooser().getYear();
		
		ControladorAgregarCita.setANIO(anio);
		ControladorAgregarCita.setMES(mes+1);
		ControladorAgregarCita.setDIA(dia);
		this.controladoragregarcita = ControladorAgregarCita.getInstance(sistema);
	}
	
	private void verPromosVigentes(ActionEvent p) {
		this.controladorPromoVigente=ControladorPromosionesVigentes.getInstance(sistema,vista);
	}

	@Override
	public void actionPerformed(ActionEvent e) { }
}
