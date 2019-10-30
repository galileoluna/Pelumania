package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import dto.ClienteDTO;
import dto.SucursalDTO;
import modelo.Sistema;
import presentacion.vista.nuevaVentanaCita;

public class ControladorCita implements ActionListener{
	private Sistema sistema;
	private nuevaVentanaCita ventanaCita;
	private static ControladorCita INSTANCE;
	
	private LocalDate fechaCita;
	private SucursalDTO sucursal;
	private ClienteDTO cliente;
	
	private List<SucursalDTO> listaSucursales;
	
	public ControladorCita(Sistema s) {
		this.ventanaCita = nuevaVentanaCita.getInstance();
		this.sistema = s;
		
		this.ventanaCita.getJDChooserFecha().addPropertyChangeListener(q -> validarFechaElegida(q));
		this.ventanaCita.getBtnEditarFecha().addActionListener(a -> habilitarEditarFecha(a));
		this.ventanaCita.getJCBoxSucursal().addActionListener(b -> mostrarPopUpSucursal(b));
		
		this.ventanaCita.getChckbxGenerico().addActionListener(c -> mostrarOpcionesClienteGenerico(c));
		this.ventanaCita.getChckbxRegistrado().addActionListener(d -> mostrarOpcionesClienteRegistrado(d));
		this.ventanaCita.getBtnRegistrar().addActionListener(e -> ventanaRegistrarCliente(e));
		
		inicializarArreglos();
	}

	public static ControladorCita getInstance(Sistema sistema) {
		if ( INSTANCE == null) {
			INSTANCE = new ControladorCita(sistema);
		}
		nuevaVentanaCita.getInstance();
		return INSTANCE;
	}

	public LocalDate getFecha() {
		return fechaCita;
	}

	public void setFecha(LocalDate fecha) {
		this.fechaCita = fecha;
		ventanaCita.setFechaCita(fecha);
		}

	public void inicializarArreglos() {
		listaSucursales = this.sistema.obtenerSucursales();
	}
	
	public void cargarDatos() {
		cargarListaSucursales();
		setearSucursalActual();
		
		cargarHorarios();
	}
	
	public SucursalDTO getSucursal() {
		return sucursal;
	}

	public void setSucursal(SucursalDTO sucursal) {
		this.sucursal = sucursal;
	}

	public void cargarListaSucursales() {
		for (SucursalDTO sucu : listaSucursales) {
			this.ventanaCita.getJCBoxSucursal().addItem(sucu);
		}
	}
	
	public void cargarHorarios() {
		for (int i = 8; i<20; i++)
		{
			this.ventanaCita.getJCBoxHora().addItem(i);
		}
		
		for(int i=0; i<59; i=i+5) {
			this.ventanaCita.getJCBoxMinutos().addItem(i);
		}
	}
	public void setearSucursalActual() {
		System.out.println(sucursal);
		this.ventanaCita.getJCBoxSucursal().setSelectedItem(sucursal);
	}
	
	
	/* METODOS PARA LOS CONTROLADORES */
	public void habilitarEditarFecha(ActionEvent a) {
		this.ventanaCita.getJDChooserFecha().setEnabled(true);
	}
	
	public void validarFechaElegida(PropertyChangeEvent b) {
		java.util.Date D_fechaElegida = this.ventanaCita.getJDChooserFecha().getDate();
		LocalDate fechaElegida = D_fechaElegida.toInstant()
			      .atZone(ZoneId.systemDefault())
			      .toLocalDate();
		
		if (fechaElegida.isBefore(LocalDate.now())) {
			this.ventanaCita.mostrarErrorFechaAnterior();
		}else {
			this.ventanaCita.ocultarErrorFechaAnteror();
		}
	}
	
	public void mostrarPopUpSucursal(ActionEvent b) {
		//limpiar todos los cmapos y recargarlos
		if (!((SucursalDTO)this.ventanaCita.getJCBoxSucursal().getSelectedItem()).equals(sucursal)) {
			this.ventanaCita.getLblAlertaSucursal().setVisible(true);
		}
		else
			this.ventanaCita.getLblAlertaSucursal().setVisible(false);
	}
	
	public void mostrarOpcionesClienteRegistrado( ActionEvent d) {
		this.ventanaCita.limpiarTxtCliente();
		this.ventanaCita.getChckbxGenerico().setSelected(false);
		this.ventanaCita.getBtnBuscar().setEnabled(true);
		this.ventanaCita.getBtnBuscar().addActionListener(r -> buscarCliente(r));
	}
	
	public void mostrarOpcionesClienteGenerico( ActionEvent d) {
		this.ventanaCita.limpiarTxtCliente();
		this.ventanaCita.getChckbxRegistrado().setSelected(false);
		this.ventanaCita.getBtnBuscar().setEnabled(false);
	}
	
	private void buscarCliente(ActionEvent r) {
		ControladorBuscarCliente.getInstance(sistema, this.ventanaCita);
	}

	private void ventanaRegistrarCliente(ActionEvent e) {
		ControladorCliente.getInstance(sistema);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
