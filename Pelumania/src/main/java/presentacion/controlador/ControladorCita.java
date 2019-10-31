package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;

import org.apache.log4j.Logger;


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
	
	private ClienteDTO clienteGenerico;
	
	private Logger log = Logger.getLogger(ControladorCita.class);	
	
	private List<SucursalDTO> listaSucursales;
	
	public ControladorCita(Sistema s) {
		
		this.ventanaCita = nuevaVentanaCita.getInstance();
		this.sistema = s;
		clienteGenerico = this.sistema.obtenerClienteById(-1);
		
		this.ventanaCita.getJDChooserFecha().addPropertyChangeListener(q -> validarFechaElegida(q));
		this.ventanaCita.getBtnEditarFecha().addActionListener(a -> habilitarEditarFecha(a));
		this.ventanaCita.getJCBoxSucursal().addActionListener(b -> seleccionarSucursal(b));
		
		this.ventanaCita.getChckbxGenerico().addActionListener(c -> mostrarOpcionesClienteGenerico(c));
		this.ventanaCita.getChckbxRegistrado().addActionListener(d -> mostrarOpcionesClienteRegistrado(d));
		this.ventanaCita.getBtnRegistrar().addActionListener(e -> ventanaRegistrarCliente(e));
		
		this.ventanaCita.getRdBtnServicio().addActionListener(a -> mostrarPanelServicio(a));
		this.ventanaCita.getRdBtnProfesional().addActionListener(b -> mostrarPanelProfesional(b));
		this.ventanaCita.getRdbtnPromocion().addActionListener(c -> mostrarPanelPromociones(c));
		
		this.ventanaCita.getBtnConfirmar().addActionListener (a -> guardarCita(a));
		
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
		
		this.ventanaCita.getJCBoxHora().addActionListener(a -> seleccionarHora(a));
		this.ventanaCita.getJCBoxMinutos().addActionListener(a -> seleccionarHora(a));
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
		this.ventanaCita.getJCBoxSucursal().setSelectedItem(sucursal);
		this.ventanaCita.setSucursal(sucursal);
	}
	
	public void actualizarPanelDinamico(String PanelAMostrar) {
		switch (PanelAMostrar){
		case "Servicios":
			this.ventanaCita.mostrarPanelServicios();
			this.ventanaCita.ocultarPanelProfesionales();
			this.ventanaCita.ocultarPanelPromociones();
			break;
		case "Profesionales":
			this.ventanaCita.mostrarPanelProfesionales();
			this.ventanaCita.ocultarPanelPromociones();
			this.ventanaCita.ocultarPanelServicios();
			break;
		case "Promociones":
			this.ventanaCita.mostrarPanelPromociones();
			this.ventanaCita.ocultarPanelProfesionales();
			this.ventanaCita.ocultarPanelServicios();
			break;
			}
	}
	
	public void mostrarPopUpSucursal() {
		//limpiar todos los cmapos y recargarlos
		if (!((SucursalDTO)this.ventanaCita.getJCBoxSucursal().getSelectedItem()).equals(sucursal)) {
			this.ventanaCita.getLblAlertaSucursal().setVisible(true);
		}
		else {
			this.ventanaCita.getLblAlertaSucursal().setVisible(false);
		}
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
	
	public void seleccionarSucursal(ActionEvent b) {
		SucursalDTO sucuSeleccionada = (SucursalDTO) this.ventanaCita.getJCBoxSucursal().getSelectedItem();
		this.ventanaCita.setSucursal(sucuSeleccionada);
		
		mostrarPopUpSucursal();
	}

	
	public void mostrarOpcionesClienteRegistrado( ActionEvent d) {
		this.ventanaCita.limpiarTxtCliente();
		this.ventanaCita.setearTxt(false);
		this.ventanaCita.getChckbxGenerico().setSelected(false);
		this.ventanaCita.getBtnBuscar().setEnabled(true);
		this.ventanaCita.getBtnBuscar().addActionListener(r -> buscarCliente(r));
	}
	
	public void mostrarOpcionesClienteGenerico( ActionEvent d) {
		this.ventanaCita.setCliente(clienteGenerico);
		this.ventanaCita.limpiarTxtCliente();
		this.ventanaCita.habilitarCamposClienteGenerico();
		this.ventanaCita.getChckbxRegistrado().setSelected(false);
		this.ventanaCita.getBtnBuscar().setEnabled(false);
	}
	
	private void buscarCliente(ActionEvent r) {
		ControladorBuscarCliente.getInstance(sistema, this.ventanaCita);
	}

	private void ventanaRegistrarCliente(ActionEvent e) {
		ControladorCliente.getInstance(sistema);
	}
	
	public void seleccionarHora(ActionEvent a) {
		Integer hora = (Integer) this.ventanaCita.getJCBoxHora().getSelectedItem();
		Integer minutos = (Integer) this.ventanaCita.getJCBoxMinutos().getSelectedItem();
		
		LocalTime horaElegida = LocalTime.of(hora, minutos);
		
		this.ventanaCita.setHoraInicio(horaElegida);
		this.ventanaCita.setearDatoInicio();
	}
	
	public void mostrarPanelServicio(ActionEvent a) {
		this.ventanaCita.getRdBtnProfesional().setSelected(false);
		this.ventanaCita.getRdbtnPromocion().setSelected(false);
		if (this.ventanaCita.getRdBtnServicio().isSelected()) {
			actualizarPanelDinamico("Servicios");
		}else {
			this.ventanaCita.ocultarPanelesServicios();
		}
	}
	
	public void mostrarPanelProfesional(ActionEvent a) {
		this.ventanaCita.getRdBtnServicio().setSelected(false);
		this.ventanaCita.getRdbtnPromocion().setSelected(false);
		if (this.ventanaCita.getRdBtnProfesional().isSelected()) {
			actualizarPanelDinamico("Profesionales");
		}else {
			this.ventanaCita.ocultarPanelesServicios();
		}	
	}
	
	public void mostrarPanelPromociones(ActionEvent a) {
		this.ventanaCita.getRdBtnProfesional().setSelected(false);
		this.ventanaCita.getRdBtnServicio().setSelected(false);
		if(this.ventanaCita.getRdbtnPromocion().isSelected()) {
			actualizarPanelDinamico("Promociones");
		}else {
			this.ventanaCita.ocultarPanelesServicios();
		}
	}
	
	private void guardarCita(ActionEvent a) {
		log.info("Aun no guarda nada, solo imprime los datos de la cita:");
		System.out.println("Fecha: "+this.ventanaCita.getFechaCita());
		System.out.println("Cliente:"+ this.ventanaCita.getCliente());
		System.out.println("Sucursal:" + this.ventanaCita.getSucursal());
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
