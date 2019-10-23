package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.time.LocalDate;

import modelo.Sistema;
import presentacion.vista.NuevaVista;

public class Controlador2 implements ActionListener{
	/*
	 * Variables Globales importantes 
	 */
	private Sistema sistema;
	private NuevaVista nvista;
	
	private LocalDate fechaSeleccionada;
	
	/*
	 * Controladores a instanciar 
	 */
	private ControladorCliente controladorCliente;
	private ControladorServicio controladorServicio;
	private ControladorProfesional controladorProfesional;
	private ControladorAgregarCita controladorAgregarCita;
	private ControladorPromocion controladorPromocion;
	private ControladorSucursal controladorSucursal;
	private ControladorPromocionesVigentes controladorPromoVigente;
	private ControladorCaja controladorCaja;
	private ControladorCategoriaMovimientoCaja controladorCategoriaMovimientoCaja;

	public Controlador2 (NuevaVista nvista, Sistema sistema) {
		this.nvista = nvista;
		this.sistema = sistema;
		
		this.nvista.getMntmGestionDeServicios().addActionListener(a->ventanaServicios(a));
		this.nvista.getMntmGestionDeProfesionales().addActionListener(b->ventanaProfesionales(b));
		this.nvista.getMntmGestionDeClientes().addActionListener(c -> ventanaClientes(c));
		this.nvista.getMntmGestionDePromociones().addActionListener(d -> ventanaPromociones(d));
//		this.nvista.getMntmVerPromocionesVigentes().addActionListener(e -> verPromocionesVigentes(e));
		this.nvista.getMntmGestionDeSucursales().addActionListener(f -> ventanaSucursales(f));
		this.nvista.getMntmUtilizarCaja().addActionListener(g -> ventanaCaja(g));
		this.nvista.getMntmConsultarCategorias().addActionListener(h -> ventanCategoriaMovimientoCaja(h));
		
		this.nvista.getCalendario().addPropertyChangeListener(i -> actualizarDiaSeleccionado(i));

	}

	
	private void ventanaServicios(ActionEvent a) {
		this.controladorServicio = ControladorServicio.getInstance(sistema);
	}

	private void ventanaClientes(ActionEvent b) {
		this.controladorCliente = ControladorCliente.getInstance(sistema);
	}

	private void ventanaProfesionales (ActionEvent c) {
		this.controladorProfesional= ControladorProfesional.getInstance(sistema);
	}
	private void ventanaPromociones(ActionEvent d) {
		this.controladorPromocion= ControladorPromocion.getInstance(sistema);
	}
	private void ventanaSucursales (ActionEvent e) {
		this.controladorSucursal= ControladorSucursal.getInstance(sistema);
	}
//	private void verPromocionesVigentes(ActionEvent f) {
//		this.controladorPromoVigente=ControladorPromocionesVigentes.getInstance(sistema,vista);
//	}

	private void ventanCategoriaMovimientoCaja(ActionEvent g) {
		this.controladorCategoriaMovimientoCaja = ControladorCategoriaMovimientoCaja.getInstance(sistema);
	}

	private void ventanaCaja(ActionEvent h) {
		this.controladorCaja = ControladorCaja.getInstance(sistema);
	}
	
	private void actualizarDiaSeleccionado(PropertyChangeEvent i) {
		int dia = this.nvista.getCalendario().getDayChooser().getDay();
		int mes = this.nvista.getCalendario().getMonthChooser().getMonth()+1;
		int anio = this.nvista.getCalendario().getYearChooser().getYear();
		
		this.fechaSeleccionada = LocalDate.of(anio, mes, dia);
		System.out.println(fechaSeleccionada);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
