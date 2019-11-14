package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;

import org.apache.log4j.Logger;

import dto.CitaDTO;
import dto.ClienteDTO;
import dto.ProfesionalDTO;
import dto.PromocionDTO;
import dto.ServicioDTO;
import dto.ServicioTurnoDTO;
import dto.SucursalDTO;
import modelo.Sistema;
import presentacion.Reportes.ReporteComprobante;
import presentacion.vista.nuevaVentanaCita;
import util.MailService;
import util.PropertyManager;

public class ControladorCita implements ActionListener{
	private Sistema sistema;
	private nuevaVentanaCita ventanaCita;
	private static ControladorCita INSTANCE;
	
	private LocalDate fechaCita;
	private SucursalDTO sucursal;
	
	private ClienteDTO clienteGenerico;
	
	private Logger log = Logger.getLogger(ControladorCita.class);	
	
	private List<SucursalDTO> listaSucursales;
	private List<ServicioTurnoDTO> serviciosAgregados;
	
	private List<ServicioDTO> servicios_panel_servicios;
	private List<ProfesionalDTO> profesionales_panel_profesionales;
	
	private List<ServicioDTO> servicios_panel_profesionales;
	private List<ProfesionalDTO> profesionales_panel_servicios;
	
	private List<PromocionDTO> promociones;
	private int sumePuntosPromo=0;
	private PromocionDTO promocionSeleccionada;
	ServicioDTO servicioSeleccionado;
	
	private Locale locale;
	private ResourceBundle idioma;
	
	private static String errorHora;
	private static String errorServicio;
	private static String errorCargarCita;
	
	public CitaDTO citaAEditar; 
	
	public ControladorCita(Sistema s) {
		
		this.locale = new Locale (PropertyManager.leer("configuracion", "idioma"), PropertyManager.leer("configuracion", "pais"));
		this.idioma = ResourceBundle.getBundle("presentacion/idioma/bundle", locale);
		System.out.println(this.idioma.getString("cita.error.dia.pasado"));
		
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
		
		this.ventanaCita.getBtnAgregarServicio().addActionListener(d -> agregarServicio(d));
		this.ventanaCita.getBtnEliminarServicio().addActionListener(e-> eliminarServicio(e));
		
		this.ventanaCita.getBtnConfirmar().addActionListener (a -> guardarCita(a));
		this.ventanaCita.getBtnCancelar().addActionListener (b -> cancelar(b));
		inicializarArreglos();
	}	

	public static ControladorCita getInstance(Sistema sistema) {
		if ( INSTANCE == null) {
			INSTANCE = new ControladorCita(sistema);
		}
		nuevaVentanaCita.getInstance();
		return INSTANCE;
	}

	public ControladorCita(Sistema s, CitaDTO citaParaEditar) {
		
		this.locale = new Locale (PropertyManager.leer("configuracion", "idioma"), PropertyManager.leer("configuracion", "pais"));
		this.idioma = ResourceBundle.getBundle("presentacion/idioma/bundle", locale);
		System.out.println(this.idioma.getString("cita.error.dia.pasado"));
		
		this.ventanaCita = nuevaVentanaCita.getInstance();
		this.citaAEditar = citaParaEditar;
		this.sistema = s; 
		clienteGenerico = this.sistema.obtenerClienteById(-1);
		this.ventanaCita.getFrame().setTitle(this.idioma.getString("cita.agregar"));
		
		this.ventanaCita.getJDChooserFecha().addPropertyChangeListener(q -> validarFechaElegida(q));
		this.ventanaCita.getBtnEditarFecha().addActionListener(a -> habilitarEditarFecha(a));
		this.ventanaCita.getJCBoxSucursal().addActionListener(b -> seleccionarSucursal(b));
		
//		this.ventanaCita.getChckbxGenerico().addActionListener(c -> mostrarOpcionesClienteGenerico(c));
//		this.ventanaCita.getChckbxRegistrado().addActionListener(d -> mostrarOpcionesClienteRegistrado(d));
		this.ventanaCita.getBtnRegistrar().addActionListener(e -> ventanaRegistrarCliente(e));
		
		this.ventanaCita.getRdBtnServicio().addActionListener(a -> mostrarPanelServicio(a));
		this.ventanaCita.getRdBtnProfesional().addActionListener(b -> mostrarPanelProfesional(b));
		this.ventanaCita.getRdbtnPromocion().addActionListener(c -> mostrarPanelPromociones(c));
		
		this.ventanaCita.getBtnAgregarServicio().addActionListener(d -> agregarServicio(d));
		this.ventanaCita.getBtnEliminarServicio().addActionListener(e-> eliminarServicio(e));
		
		this.ventanaCita.getBtnConfirmar().addActionListener (a -> guardarCita(a));
		this.ventanaCita.getBtnCancelar().addActionListener (b -> cancelar(b));
		inicializarArreglos();
	}
	
	public static ControladorCita getInstance(Sistema sistema, CitaDTO citaParaEditar) {
		if ( INSTANCE == null) {
			INSTANCE = new ControladorCita(sistema, citaParaEditar);
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
		serviciosAgregados = new ArrayList<ServicioTurnoDTO>();
		servicios_panel_servicios = this.sistema.obtenerServicios();

		java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		promociones = this.sistema.obtenerPromoVigente(date, date);
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
		
		this.ventanaCita.getJCBoxHora().setSelectedItem(null);
		this.ventanaCita.getJCBoxMinutos().setSelectedItem(null);
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
		//limpiar todos los campos y recargarlos
		if (!((SucursalDTO)this.ventanaCita.getJCBoxSucursal().getSelectedItem()).equals(sucursal)) {
			this.ventanaCita.getLblAlertaSucursal().setVisible(true);
		}
		else {
			this.ventanaCita.getLblAlertaSucursal().setVisible(false);
		}
	}
	
	/******************* VALIDACIONES *************/
	
	public boolean validarHora(LocalTime horaElegida) {
		if (horaElegida == null) {
			System.out.println(this.idioma.getString("cita.error.hora"));
			ControladorCita.errorHora = this.idioma.getString("cita.error.hora");
			return false;
		}
		if (this.ventanaCita.getFechaCita().equals(LocalDate.now())) {
			if (horaElegida.isBefore(LocalTime.now())){
				ControladorCita.errorHora = this.idioma.getString("cita.error.dia.pasado");
				return false;
			}
		}
		return true;
	}
	
	public void mostrarErrorHora() {
		JOptionPane.showMessageDialog(null, ControladorCita.errorHora);
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
			this.ventanaCita.setFechaCita(null);
		}else {
			this.ventanaCita.ocultarErrorFechaAnteror();
			this.ventanaCita.setFechaCita(fechaElegida);
		}
		
		if (fechaElegida.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
			JOptionPane.showMessageDialog(null, this.idioma.getString("cita.error.domingo"));
			this.ventanaCita.getJDChooserFecha().setDate(null);
			this.ventanaCita.setFechaCita(null);
			this.ventanaCita.getJDChooserFecha().setEnabled(true);
		}
		
	}
	
	public void seleccionarSucursal(ActionEvent b) {
		SucursalDTO sucuSeleccionada = (SucursalDTO) this.ventanaCita.getJCBoxSucursal().getSelectedItem();
		this.ventanaCita.setSucursal(sucuSeleccionada);
		
		mostrarPopUpSucursal();
		
		this.ventanaCita.ocultarPanelesServicios();
		this.ventanaCita.getRdBtnProfesional().setSelected(false);
		this.ventanaCita.getRdBtnServicio().setSelected(false);
		this.ventanaCita.getRdbtnPromocion().setSelected(false);
		
		serviciosAgregados.clear();
		actualizarServiciosAgregados();
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
		
		if(hora == null || minutos == null) {
		}else {
		LocalTime horaElegida = LocalTime.of(hora, minutos);
		
		if (validarHora(horaElegida)) {
			this.ventanaCita.setHoraInicio(horaElegida);
			this.ventanaCita.setearDatoInicio();
		}else {
			mostrarErrorHora();
			this.ventanaCita.getLbl_Inicio().setText(null);
			this.ventanaCita.getJCBoxMinutos().setSelectedItem(null);
			}
		}
		
		this.ventanaCita.setHoraFin(this.ventanaCita.getHoraInicio());
	}
	
	public void mostrarPanelServicio(ActionEvent a) {
		this.ventanaCita.getRdBtnProfesional().setSelected(false);
		this.ventanaCita.getRdbtnPromocion().setSelected(false);
		if (this.ventanaCita.getRdBtnServicio().isSelected()) {
			actualizarPanelDinamico("Servicios");
			llenarDatosPanelServicio();
			this.ventanaCita.getPanelDinamicoServicios().getTablaServicios().getSelectionModel().addListSelectionListener(l -> actualizarServicioSeleccionado(l));
			this.ventanaCita.getPanelDinamicoServicios().getBtnBuscarServicio().addActionListener(m -> buscarServicio(m));
			this.ventanaCita.getPanelDinamicoServicios().getBtnLimpiarBusqueda().addActionListener(n -> limpiarFiltro(n));
		}else {
			this.ventanaCita.ocultarPanelesServicios();
		}
	}

	public void mostrarPanelProfesional(ActionEvent a) {
		this.ventanaCita.getRdBtnServicio().setSelected(false);
		this.ventanaCita.getRdbtnPromocion().setSelected(false);
		if (this.ventanaCita.getRdBtnProfesional().isSelected()) {
			actualizarPanelDinamico("Profesionales");
			llenarDatosPanelProfesionales();
			this.ventanaCita.getPanelDinamicoProfesionales().getJCBoxProfesional().addActionListener(e -> mostrarServiciosDelProfesional(e));
			this.ventanaCita.getPanelDinamicoProfesionales().getTablaServicios().getSelectionModel().addListSelectionListener(b -> actualizarServicioSeleccionadoPanelProfesional(b));
		}else {
			this.ventanaCita.ocultarPanelesServicios();
		}	
	}

	public void mostrarPanelPromociones(ActionEvent a) {
		this.ventanaCita.getRdBtnProfesional().setSelected(false);
		this.ventanaCita.getRdBtnServicio().setSelected(false);
		if(this.ventanaCita.getRdbtnPromocion().isSelected()) {
			actualizarPanelDinamico("Promociones");
			llenarTablaPromociones();
		}else {
			this.ventanaCita.ocultarPanelesServicios();
		}
	}
	
	private void actualizarServicioSeleccionado(ListSelectionEvent l) {
		servicioSeleccionado = getServicioSeleccionado();
		if(servicioSeleccionado!=null) {
			profesionales_panel_servicios = this.sistema.getProfesionalesByIdServicio(servicioSeleccionado.getIdServicio());
			cargarProfesionalesAsociadosAServicio();
			}
		}
	
	private void actualizarServicioSeleccionadoPanelProfesional(ListSelectionEvent k) {
		servicioSeleccionado = getServicioSeleccionadoPanelProfesional();
	}
	
	private void guardarCita(ActionEvent a) {
		calcularHorariosServicios();
		System.out.println("Sucursal: " + this.ventanaCita.getSucursal());
		System.out.println("Fecha: "+this.ventanaCita.getFechaCita());
		System.out.println("Cliente: "+ this.ventanaCita.getCliente());
		System.out.println("Telefono: "+ this.ventanaCita.getTxtTelefono().getText());
		System.out.println("Mail: "+ this.ventanaCita.getTxtMail().getText());
		
		System.out.println("Inicio: "+ this.ventanaCita.getHoraInicio());
		imprimirServicios();
		System.out.println("Fin: "+ this.ventanaCita.getHoraFin());
		System.out.println("Total: $"+this.ventanaCita.getTotal());
		System.out.println("Total: USD"+ this.ventanaCita.getTotalUSD());
		
		registrarCita();
	}
	
	public void editarCita() {
		calcularHorariosServicios();
		if (validarCita()) {
			CitaDTO citaEditada = new CitaDTO(this.citaAEditar.getIdCita(), -1, this.ventanaCita.getCliente().getIdCliente(), 
					this.ventanaCita.getCliente().getNombre(), this.clienteGenerico.getApellido(), 
					this.ventanaCita.getTxtTelefono().getText(), this.ventanaCita.getTxtMail().getText(),
					"Activa", this.ventanaCita.getTotal(), this.ventanaCita.getTotalUSD(), 
					this.ventanaCita.getHoraInicio(), this.ventanaCita.getHoraFin(),
					this.ventanaCita.getFechaCita(), this.ventanaCita.getSucursal().getIdSucursal(),
					this.citaAEditar.getIdPromocion());
		
			if (this.sistema.editarCita(citaEditada))
				for (ServicioTurnoDTO servicioViejo : this.sistema.getServicioTurnoByIdCita(citaEditada.getIdCita())) 
				{
					this.sistema.deleteServicioTurno(servicioViejo);
				}
				
				for (ServicioTurnoDTO st : serviciosAgregados) 
				{
					st.setIdCita(citaEditada.getIdCita());
					this.sistema.insertServicioTurno(st);
	
				}
				mostrarExitoCargarCita(citaEditada.getIdCita());
				this.ventanaCita.cerrar();
			} else {
					JOptionPane.showMessageDialog(null, this.idioma.getString("cita.error.editar"));
				}
		mostrarErrorCita();
			}

	
	public void imprimirServicios() {
		System.out.print("[");
		for (ServicioTurnoDTO st : serviciosAgregados) {
			ServicioDTO serv = this.sistema.getServicioById(st.getIdServicio());
			ProfesionalDTO prof = this.sistema.getProfesionalById(st.getIdProfesional());
			
			System.out.println(serv.getNombre()+" por: "+ prof.getNombre()+" "+prof.getApellido());
			System.out.println("Inicia: "+ st.getHoraInicio() +" y termina: "+ st.getHoraFin());
		}
	}
	
	public boolean validarCita() {
		if (this.ventanaCita.getFechaCita().getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
			errorCargarCita = this.idioma.getString("cita.error.domingo");
			return false;
		}
		if (this.ventanaCita.getFechaCita().isBefore(LocalDate.now())) {
			errorCargarCita = this.idioma.getString("error.fecha.invalida");
			return false;
		}
		if (this.ventanaCita.getSucursal().getIdSucursal() == -1) {
			errorCargarCita = this.idioma.getString("cita.error.sucursal");
			return false;
		}
		if (this.ventanaCita.getCliente()==null) {
			String error = this.idioma.getString("cita.error.cliente");
			errorCargarCita = error;
			return false;
		}
		if (serviciosAgregados.isEmpty()) {
			errorCargarCita = this.idioma.getString("cita.error.sin.servicios");
			return false;
		}
		
		Boolean ret = true;
		ArrayList<ServicioTurnoDTO> serviciosNoValidos = new ArrayList<ServicioTurnoDTO>();
		for (ServicioTurnoDTO st : serviciosAgregados) {
			boolean servicioValido = validarDisponibilidadProfesional(st.getHoraInicio(), st.getHoraFin(), st.getIdProfesional());
			ret = ret && servicioValido;
			
			if (!servicioValido)
				serviciosNoValidos.add(st);
		}
		
		if (!ret) {
			errorCargarCita = this.idioma.getString("cita.error.tiempo");
			for (ServicioTurnoDTO snv : serviciosNoValidos) {
				ServicioDTO serv = this.sistema.getServicioById(snv.getIdServicio());
				ProfesionalDTO prof = this.sistema.getProfesionalById(snv.getIdProfesional());
				errorCargarCita = errorCargarCita + " "+ serv.getNombre() + "" + this.idioma.getString("cita.error.cargar.servicio")+
				this.idioma.getString("profesional")+ " " + prof.getNombre()+" "+prof.getApellido()+ "" + this.idioma.getString("cita.error.servicio.no.disponible");
			}
			return false;
		}
		return true;
	}
	
	
	public void registrarCita() {
		if (validarCita()) {
			Integer idCitaAgregada;

			CitaDTO nuevaCita = new CitaDTO(0, -1, this.ventanaCita.getCliente().getIdCliente(), 
					this.ventanaCita.getCliente().getNombre(), this.ventanaCita.getCliente().getApellido(), 
					this.ventanaCita.getTxtTelefono().getText(), this.ventanaCita.getTxtMail().getText(),
					"Activa", this.ventanaCita.getTotal(), this.ventanaCita.getTotalUSD(), 
					this.ventanaCita.getHoraInicio(), this.ventanaCita.getHoraFin(),
					this.ventanaCita.getFechaCita(), this.ventanaCita.getSucursal().getIdSucursal(),-1);
			if(promocionSeleccionada.getIdPromocion() > 0) {
				nuevaCita = new CitaDTO(0, -1, this.ventanaCita.getCliente().getIdCliente(), 
				this.ventanaCita.getCliente().getNombre(), this.ventanaCita.getCliente().getApellido(), 
				this.ventanaCita.getTxtTelefono().getText(), this.ventanaCita.getTxtMail().getText(),
				"Activa", this.ventanaCita.getTotal(), this.ventanaCita.getTotalUSD(), 
				this.ventanaCita.getHoraInicio(), this.ventanaCita.getHoraFin(),
				this.ventanaCita.getFechaCita(), this.ventanaCita.getSucursal().getIdSucursal(),promocionSeleccionada.getIdPromocion());
			}
			
			if (this.sistema.agregarCita(nuevaCita)) {
				idCitaAgregada = this.sistema.getCitaMax().getIdCita();
				
				for (ServicioTurnoDTO st : serviciosAgregados) 
				{
					st.setIdCita(idCitaAgregada);
					this.sistema.insertServicioTurno(st);
				}
				
				this.mostrarExitoCargarCita(idCitaAgregada);
				this.ventanaCita.cerrar();
				//tomamos el mail del input porque el usuario generico posee otro mail en la bdd
				String mail = this.ventanaCita.getTxtMail().getText();
				
				//una vez que se hizo todo bien mandamos el mail
				MailService.enviarComprobanteCita(this.sistema, this.sistema.getCitaById(idCitaAgregada), mail);
			}
		}
		else
			mostrarErrorCita();
	}

		
	public void mostrarErrorCita() {
		JOptionPane.showMessageDialog(null, errorCargarCita);
	}
	
	public void mostrarExitoCargarCita(Integer idCitaAgregada) {
		
		Object[] opciones = {this.idioma.getString("aceptar"),
                this.idioma.getString("comprobante")};
				int n = JOptionPane.showOptionDialog(null,
				this.idioma.getString("cita.exito"),
				this.idioma.getString("informacion"),
				JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.INFORMATION_MESSAGE,
				null,
				opciones,
				opciones[1]);
				
				if (n==1) {
					generarComprobante(idCitaAgregada);
				}

	}
	
	private void generarComprobante(Integer idCita) {
		 ReporteComprobante reporteComprobante = new ReporteComprobante(this.sistema.getCitaById(idCita));
		 reporteComprobante.mostrar();
	}
	private void generarComprobante(CitaDTO cita) {
		 ReporteComprobante reporteComprobante = new ReporteComprobante(cita);
		 reporteComprobante.mostrar();
	}
	
	public void cancelar(ActionEvent b) {
		this.ventanaCita.cerrar();
	}
	/* ****************************************************************** */
	/* *********** METODOS PARA EL MANEJO DE LOS SERVICIOS ************** */
	/* ****************************************************************** */
	
	public void agregarServicio(ActionEvent d) {
		if (this.ventanaCita.getRdBtnServicio().isSelected()) {
			ProfesionalDTO profesional = (ProfesionalDTO) this.ventanaCita.getPanelDinamicoServicios().getJCBoxProfesionalesDeServicio().getSelectedItem();
			Integer idProfesional = (profesional == null) ? null : profesional.getIdProfesional();
			ServicioTurnoDTO serv = new ServicioTurnoDTO(servicioSeleccionado.getIdServicio(), idProfesional);
			if (validarHora(this.ventanaCita.getHoraInicio())) {
				calcularHorario(serv);

				if (validarAntesDeAgregarServicio(serv)) {
					serviciosAgregados.add(serv);
					actualizarServiciosAgregados();
					actualizarHoraFin();
					actualizarPrecioTotal();
					actualizarPrecioTotalDolar();
					actualizarPuntos();
					System.out.println("Los servicios son: "+serviciosAgregados);
				}else {
					mostrarErrorServicio();
				}
			}else {
				
				mostrarErrorHora();
			}
		}
		
		
		if (this.ventanaCita.getRdBtnProfesional().isSelected()) {
			ProfesionalDTO profesional = (ProfesionalDTO) this.ventanaCita.getPanelDinamicoProfesionales().getJCBoxProfesional().getSelectedItem();
			Integer idProfesional = (profesional == null) ? null : profesional.getIdProfesional();
			ServicioTurnoDTO serv = new ServicioTurnoDTO(servicioSeleccionado.getIdServicio(), idProfesional);
			if (validarHora(this.ventanaCita.getHoraInicio())) {
				calcularHorario(serv);
				if (validarAntesDeAgregarServicio(serv)) {
					serviciosAgregados.add(serv);
					actualizarServiciosAgregados();
					actualizarHoraFin();
					actualizarPrecioTotal();
					actualizarPrecioTotalDolar();
					actualizarPuntos();
					System.out.println("Los servicios son: "+serviciosAgregados);
				}else { 
					mostrarErrorServicio();
				}
			}else {
				mostrarErrorHora();
			}
		}
		
		if (this.ventanaCita.getRdbtnPromocion().isSelected()) {
			int errorHora=0;
			int errorServ=0;
			if(promocionSeleccionada == null) {
				promocionSeleccionada=new PromocionDTO(getPromocionSeleccionada().getIdPromocion(),getPromocionSeleccionada().getDescripcion(),getPromocionSeleccionada().getFechaInicio(),getPromocionSeleccionada().getFechaFin(),getPromocionSeleccionada().getDescuento(),getPromocionSeleccionada().getPuntos(),getPromocionSeleccionada().getEstado());
				List<Integer> servicio= sistema.obtenerIdServPromo(promocionSeleccionada.idPromocion);
				ProfesionalDTO profesional = (ProfesionalDTO) this.ventanaCita.getPanelDinamicoProfesionales().getJCBoxProfesional().getSelectedItem();
				Integer idProfesional = -1;
				for(Integer i : servicio) {
					ServicioTurnoDTO serv = new ServicioTurnoDTO(i, idProfesional);
					if (validarHora(this.ventanaCita.getHoraInicio())) {
						calcularHorario(serv);
						if (validarAntesDeAgregarServicio(serv)) {	
							serviciosAgregados.add(serv);
							actualizarServiciosAgregados();
							actualizarHoraFin();
							actualizarPrecioTotal();
							actualizarPrecioTotalDolar();
							actualizarPuntos();
							System.out.println("Los servicios son: "+serviciosAgregados);
						}else {
							errorServ=1;
						}
					}else {
						errorHora=1;
					}	
				}
				if(errorHora==1) {
					errorHora=0;
					promocionSeleccionada = null;
					mostrarErrorHora();
				}
				if(errorServ==1) {
					promocionSeleccionada = null;
					errorServ=0;
					mostrarErrorServicio();
				}
			}
		}
}
	private void eliminarServicio(ActionEvent e) {
		int[] filasSeleccionadas = ventanaCita.getTablaServiciosAgregados().getSelectedRows();
	       
    	for (int fila : filasSeleccionadas)
    	{
        	if(serviciosAgregados.get(fila)!=null) {
        		this.sistema.getServicioById(serviciosAgregados.get(fila).getIdServicio());
        		List<Integer> servicio= (promocionSeleccionada==null?null:sistema.obtenerIdServPromo(promocionSeleccionada.getIdPromocion()));
	        		if(promocionSeleccionada != null && servicio != null ) {
	        					borrarPromocionAsociada(servicio,serviciosAgregados.get(fila).getIdServicio(),serviciosAgregados.get(fila));
	        		}else {
		        		ServicioTurnoDTO servicioSeleccionado = serviciosAgregados.get(fila);
		        		serviciosAgregados.remove(servicioSeleccionado);
	        		}
	        	calcularHorariosServicios();
				actualizarServiciosAgregados();
				actualizarHoraFin();
				actualizarPrecioTotal();
				actualizarPrecioTotalDolar();
				actualizarPuntos();
        	}
    	}
	}
	
	


	public void mostrarServiciosDelProfesional(ActionEvent a) {
		ProfesionalDTO profesionalSeleccionado = (ProfesionalDTO) this.ventanaCita.getPanelDinamicoProfesionales().getJCBoxProfesional().getSelectedItem();
		if (profesionalSeleccionado != null) {
			cargarServiciosDelProfesional(profesionalSeleccionado.getIdProfesional());
			}
		}
	
	public boolean validarAntesDeAgregarServicio(ServicioTurnoDTO servicio) {
		if (servicio == null) {
			ControladorCita.errorServicio = this.idioma.getString("cita.error.seleccionar.servicio");
			return false;
		}
		if (this.ventanaCita.getHoraInicio() == null) {
			ControladorCita.errorServicio = this.idioma.getString("cita.error.hora.inicio");
			return false;
		}
		
		if (servicio.getIdProfesional() == null) {
			ControladorCita.errorServicio = this.idioma.getString("cita.error.seleccionar.profesional");
			return false;
		}
		
		if (serviciosAgregados.contains(servicio)) {
			ControladorCita.errorServicio = this.idioma.getString("cita.error.agregar.servicio");
			return false;
		}
		ProfesionalDTO profesional = this.sistema.getProfesionalById(servicio.getIdProfesional());
		
		System.out.println("Horarios servicio: "+servicio.getHoraInicio() +"\n"+ servicio.getHoraFin());
		if (!validarDisponibilidadProfesional(servicio.getHoraInicio(), servicio.getHoraFin(), profesional.getIdProfesional())) {
			ControladorCita.errorServicio = "El profesional "+ profesional.getNombre()+" "+
					profesional.getApellido()+" no está disponible en ese horario!"+ 
					"Tiene una cita desde: "+"__:__"+"hasta: "+ "__:__";
			return false;
		}
		
		String diaDeLaSemana = diaDeLaSemana();
		if (!validarProfesionalEnSucursal(profesional.getIdProfesional(), servicio.getHoraInicio(), servicio.getHoraFin(), diaDeLaSemana)) {
			ControladorCita.errorServicio = this.idioma.getString("cita.error.profesiona.fuera.horarario");
			return false;
		}
		return true;
	}
	
	private boolean validarDisponibilidadProfesional(LocalTime inicio, LocalTime fin, int idProfesional) {
		if (idProfesional == -1) {
			return true;
		}
		Integer ocupado = this.sistema.profesionalOcupado(idProfesional, inicio, fin, this.ventanaCita.getFechaCita());
		System.out.println(ocupado);
		if (ocupado == 1) {
			ControladorCita.errorServicio = this.idioma.getString("cita.error.profesional.ocupado");
			return false;
		}else 
			return true;
		}
	
	public boolean validarProfesionalEnSucursal(Integer idProfesional, LocalTime horaInicio,
		LocalTime horaFin, String diaDeLaSemana) {
		if (idProfesional == -1) {
			return true;
		}
		Integer disponible = this.sistema.profesionalTrabaja(idProfesional, horaInicio, horaFin, diaDeLaSemana);
		System.out.println("Disponible = "+ disponible);
		
		if (disponible == 1){
			return true;
		}		
		return false;
		}
	
	private void mostrarErrorServicio() {
		JOptionPane.showMessageDialog(null, ControladorCita.errorServicio);
	}
	
	public void calcularHorariosServicios() {
		LocalTime horaInicial = this.ventanaCita.getHoraInicio();
		LocalTime horaFinalizacionServicio = null;
		for (ServicioTurnoDTO st : serviciosAgregados) {
			ServicioDTO servicio = sistema.getServicioById(st.getIdServicio());
			st.setHoraInicio(horaInicial);
			
			horaFinalizacionServicio = horaInicial.plusHours(servicio.getDuracion().getHour());
			horaFinalizacionServicio = horaFinalizacionServicio.plusMinutes(servicio.getDuracion().getMinute());
			st.setHoraFin(horaFinalizacionServicio);	
			horaInicial = horaFinalizacionServicio;	
		}
		
		this.ventanaCita.setHoraFin(horaFinalizacionServicio);
	}
	
	public void calcularHorario(ServicioTurnoDTO serv) {
		ServicioDTO servicio = this.sistema.getServicioById(serv.getIdServicio());
		System.out.println("HoraFin al calcular horario es:"+ this.ventanaCita.getHoraFin() );
		serv.setHoraInicio(this.ventanaCita.getHoraFin());
		LocalTime horaFin = serv.getHoraInicio().plusHours(servicio.getDuracion().getHour());
		horaFin = horaFin.plusMinutes(servicio.getDuracion().getMinute());
		serv.setHoraFin(horaFin);
	}

	private void actualizarHoraFin() {
		if (serviciosAgregados.isEmpty()) {
			this.ventanaCita.setHoraFin(ventanaCita.getHoraInicio());
			this.ventanaCita.getLbl_Fin().setText(this.ventanaCita.getHoraFin().toString());
		}else {
			this.ventanaCita.setHoraFin(this.serviciosAgregados.get(serviciosAgregados.size()-1).getHoraFin());
			this.ventanaCita.getLbl_Fin().setText(this.ventanaCita.getHoraFin().toString());
		}
	}
	
	private BigDecimal actualizarPrecioTotal() {
		BigDecimal total = BigDecimal.valueOf(0);
		int listoPromo=(promocionSeleccionada == null?-1:sistema.obtenerIdServPromo(promocionSeleccionada.getIdPromocion()).size());
		for (ServicioTurnoDTO st : serviciosAgregados) {
			if(promocionSeleccionada != null && listoPromo > -1) {
				listoPromo--;
				actualizaPrecioPromo(total,st);
				System.out.println("Despues de mi funcion total?= "+total);
			}else {
				Integer idServicio = st.getIdServicio();
				ServicioDTO servicio = this.sistema.getServicioById(idServicio);
				System.out.println("total else: "+total+" con el servicio "+servicio.getNombre());
				total = total.add(servicio.getPrecioLocal());
				System.out.println("total despues del add: "+total);
			}
		}
		System.out.println("total termino el ciclo: "+total);
		//Setear el total a la ventana
		this.ventanaCita.setTotal(total);
		this.ventanaCita.getLbl_Total().setText(this.ventanaCita.getTotal().toString());
		return total;
}

	
	public BigDecimal actualizarPrecioTotalDolar() {
		BigDecimal total = BigDecimal.valueOf(0);
		for (ServicioTurnoDTO st : serviciosAgregados) {
			if(promocionSeleccionada != null) {
				total=actualizaDolarPromo(total,st); 
			}else {
				Integer idServicio = st.getIdServicio();
				ServicioDTO servicio = this.sistema.getServicioById(idServicio);
				total = total.add(servicio.getPrecioDolar());
			}
		}
		this.ventanaCita.setTotalUSD(total);
		this.ventanaCita.getLbl_TotalUSD().setText(this.ventanaCita.getTotalUSD().toString());
		return total;
	}
	
	public int actualizarPuntos() {
		int totalPuntos = 0;
	int listo=0;
		for (ServicioTurnoDTO st : serviciosAgregados) {
			if(promocionSeleccionada != null && st.getIdServicio() == sistema.obtenerIdServPromo(promocionSeleccionada.getIdPromocion()).get(0) && listo ==0) {
				totalPuntos=promocionSeleccionada.getPuntos()+totalPuntos;
				listo++;
			}
			Integer idServicio = st.getIdServicio();
			ServicioDTO servicio = this.sistema.getServicioById(idServicio);
			totalPuntos = totalPuntos + (servicio.getPuntos());
		}
		this.ventanaCita.setPuntos(totalPuntos);
		this.ventanaCita.getLbl_Puntos().setText(Integer.toString(this.ventanaCita.getPuntos()));
		return totalPuntos;
	}

	
	public String diaDeLaSemana() {
		String dds = "";
		switch ( this.ventanaCita.getFechaCita().getDayOfWeek() ) {
		case MONDAY:
			dds = "Lunes";
			break;
		case THURSDAY:
			dds = "Martes";
			break;
		case WEDNESDAY:
			dds = "Miercoles";
			break;
		case TUESDAY: 
			dds = "Jueves";
			break;
		case FRIDAY: 	
			dds = "Viernes";
			break;
		case SATURDAY: 
			dds = "Sabado";
			break;
		case SUNDAY: 
			dds = "Domingo";
			break;
		}
			return dds;
	}
	/* PANEL SERVICIOS*/
	
	public void cargarServiciosEnTabla(List<ServicioDTO> servicios) {
			this.ventanaCita.getPanelDinamicoServicios().getModelServicios().setRowCount(0); //Para vaciar la tabla
			this.ventanaCita.getPanelDinamicoServicios().getModelServicios().setColumnCount(0);
			this.ventanaCita.getPanelDinamicoServicios().getModelServicios().setColumnIdentifiers(this.ventanaCita.getPanelDinamicoServicios().getNombreColumnas());

//			servicios = this.sistema.obtenerServicios();
			for (ServicioDTO s : servicios)
			{
				String nombre = s.getNombre();
				BigDecimal precioLocal = s.getPrecioLocal();
				BigDecimal precioDolar = s.getPrecioDolar();
				LocalTime duracion = s.getDuracion();
				int puntos = s.getPuntos();
				String estado = s.getEstado();
				Object[] fila = {nombre, precioLocal, duracion, puntos, estado};
				this.ventanaCita.getPanelDinamicoServicios().getModelServicios().addRow(fila);
			}
	}
	
	public void actualizarServiciosAgregados() {
		this.ventanaCita.getModelServiciosAgregados().setRowCount(0); //Para vaciar la tabla
		this.ventanaCita.getModelServiciosAgregados().setColumnCount(0);
		this.ventanaCita.getModelServiciosAgregados().setColumnIdentifiers(this.ventanaCita.getNombreColumnasAgregadas());

		for (ServicioTurnoDTO st : serviciosAgregados)
		{
			ServicioDTO servicio = sistema.getServicioById(st.getIdServicio());
			ProfesionalDTO profesional = sistema.getProfesionalById(st.getIdProfesional());
			String nombreServicio = servicio.getNombre();
			String nombreProfesional = profesional.getNombre()+" "+profesional.getApellido();
			Object[] fila = {nombreServicio, nombreProfesional};
			this.ventanaCita.getModelServiciosAgregados().addRow(fila);
		}
}
	
	public void llenarDatosPanelServicio() {
		cargarServiciosEnTabla(servicios_panel_servicios);
	}
	
	public ServicioDTO getServicioSeleccionado() {
		ServicioDTO servicioSeleccionado = null;;
		int[] filasSeleccionadas = this.ventanaCita.getPanelDinamicoServicios().getTablaServicios().getSelectedRows();
	       
    	for (int fila : filasSeleccionadas)
    	{
        	if(servicios_panel_servicios.get(fila)!=null) {	 
        		Integer idServicio = servicios_panel_servicios.get(fila).getIdServicio();
        		servicioSeleccionado = sistema.getServicioById(idServicio);
        	}
    	}
    	return servicioSeleccionado;
	}
	
	
	private void cargarProfesionalesAsociadosAServicio() {
		this.ventanaCita.getPanelDinamicoServicios().getJCBoxProfesionalesDeServicio().removeAllItems();
		for (ProfesionalDTO prof : profesionales_panel_servicios) {
			if(prof.getEstado().equals("Activo") && 
			prof.getIdSucursalOrigen() == this.ventanaCita.getSucursal().getIdSucursal()
			|| prof.getIdProfesional() == -1)
				this.ventanaCita.getPanelDinamicoServicios().getJCBoxProfesionalesDeServicio().addItem(prof);
		}
	}
	
	private void buscarServicio(ActionEvent m) {
		String variable = "Nombre";
		String value= this.ventanaCita.getPanelDinamicoServicios().getTxtbuscarServicios().getText();
		this.servicios_panel_servicios = this.sistema.obtenerServicioConBuscador(variable, value);
		llenarDatosPanelServicio();
}
	
	private void limpiarFiltro(ActionEvent n) {
		this.servicios_panel_servicios = this.sistema.obtenerServicios();
		llenarDatosPanelServicio();
		this.ventanaCita.getPanelDinamicoServicios().getTxtbuscarServicios().setText(null);
	}
	
	/* PANEL PROFESIONALES */
	
	public void llenarDatosPanelProfesionales() {
		cargarProfesionales();
	}
	
	private void cargarProfesionales() {
		profesionales_panel_profesionales = this.sistema.obtenerProfesional();
		this.ventanaCita.getPanelDinamicoProfesionales().getJCBoxProfesional().removeAllItems();
		for (ProfesionalDTO prof : profesionales_panel_profesionales) {
			if(prof.getEstado().equals("Activo") && 
				prof.getIdSucursalOrigen() == this.ventanaCita.getSucursal().getIdSucursal()
				|| prof.getIdProfesional() == -1)
				this.ventanaCita.getPanelDinamicoProfesionales().getJCBoxProfesional().addItem(prof);
		}
		
		this.ventanaCita.getPanelDinamicoProfesionales().getJCBoxProfesional().setSelectedItem(null);
	}
	
	public void cargarServiciosDelProfesional(Integer idProfesional) {
		this.ventanaCita.getPanelDinamicoProfesionales().getModelServicios().setRowCount(0); //Para vaciar la tabla
		this.ventanaCita.getPanelDinamicoProfesionales().getModelServicios().setColumnCount(0);
		this.ventanaCita.getPanelDinamicoProfesionales().getModelServicios().setColumnIdentifiers(this.ventanaCita.getPanelDinamicoServicios().getNombreColumnas());

		List<ServicioDTO> serviciosDeProfesional = this.sistema.getServiciosDelProfesional(idProfesional);
		for (ServicioDTO s : serviciosDeProfesional)
		{
			String nombre = s.getNombre();
			BigDecimal precioLocal = s.getPrecioLocal();
			BigDecimal precioDolar = s.getPrecioDolar();
			LocalTime duracion = s.getDuracion();
			int puntos = s.getPuntos();
			String estado = s.getEstado();
			Object[] fila = {nombre, precioLocal, duracion, puntos, estado};
			this.ventanaCita.getPanelDinamicoProfesionales().getModelServicios().addRow(fila);
		}
	}
	
	public ServicioDTO getServicioSeleccionadoPanelProfesional() {
		ServicioDTO servicioSeleccionado = null;;
		int[] filasSeleccionadas = this.ventanaCita.getPanelDinamicoProfesionales().getTablaServicios().getSelectedRows();
		ProfesionalDTO profSeleccionado = (ProfesionalDTO) this.ventanaCita.getPanelDinamicoProfesionales().getJCBoxProfesional().getSelectedItem();
		servicios_panel_profesionales = this.sistema.getServiciosDelProfesional(profSeleccionado.getIdProfesional()); 
    	for (int fila : filasSeleccionadas)
    	{
        	if(servicios_panel_profesionales.get(fila)!=null) {	 
        		Integer idServicio = servicios_panel_profesionales.get(fila).getIdServicio();
        		servicioSeleccionado = sistema.getServicioById(idServicio);
        	}
    	}
    	return servicioSeleccionado;
	}
	
	/* PANEL PROMOCIONES */
	
	public void llenarTablaPromociones() {
		this.ventanaCita.getPanelDinamicoPromociones().getModelPromocion().setRowCount(0); //Para vaciar la tabla
		this.ventanaCita.getPanelDinamicoPromociones().getModelPromocion().setColumnCount(0);
		this.ventanaCita.getPanelDinamicoPromociones().getModelPromocion().setColumnIdentifiers(this.ventanaCita.getPanelDinamicoPromociones().getNombreColumnas());

		for (PromocionDTO p : promociones)		{ 
			String descripcion = p.getDescripcion();
			Double descuento=p.getDescuento();
			int puntos=p.getPuntos();
			String estado=p.getEstado();
			Object[] fila = {descripcion, descuento,puntos,estado};
			this.ventanaCita.getPanelDinamicoPromociones().getModelPromocion().addRow(fila);
		}
		
	} 
	
	public PromocionDTO getPromocionSeleccionada() {
		PromocionDTO promocionSeleccionada = null;;
		
		int[] filasSeleccionadas = this.ventanaCita.getPanelDinamicoPromociones().getTablaPromocion().getSelectedRows();
    	for (int fila : filasSeleccionadas)
    	{
        	if(promociones.get(fila)!=null) {	 
        		Integer idPromocion = promociones.get(fila).getIdPromocion();
        		promocionSeleccionada = this.sistema.getPromocionById(idPromocion);
        	}
    	}
    	return promocionSeleccionada;
	}
	
	private void borrarPromocionAsociada(List<Integer> servicio, Integer seleccion, ServicioTurnoDTO servicioABorrar) {
		int encontro=0;
		for(Integer i : servicio) {
			if(seleccion == i) {
				ControladorCita.errorServicio = this.idioma.getString("cita.error.promocion.borrar");
				mostrarErrorServicio();
				encontro ++;
			}
			if(encontro > 0) {
				for(Integer idServ : servicio) {		
					System.out.println("Tengo que entrar");
					promocionSeleccionada=null;
					for(ServicioTurnoDTO serv: serviciosAgregados) {
						if(serv.getIdServicio() == idServ ) {
							serviciosAgregados.remove(serv);
							break;
						}
					}
				}
			}	
		}
		if( encontro == 0) {
        		serviciosAgregados.remove(servicioABorrar);			
		}
	}
	
	private BigDecimal actualizaPrecioPromo(BigDecimal total, ServicioTurnoDTO st) {
		
		if(promocionSeleccionada.getDescuento() > 0) {
			List<Integer> idservicio= sistema.obtenerIdServPromo(promocionSeleccionada.getIdPromocion());
			for(Integer i : idservicio) {
					if(st.getIdServicio() == i) {
						ServicioDTO servicio = this.sistema.getServicioById(st.getIdServicio());
						BigDecimal divisor=new BigDecimal("100");
						BigDecimal desc= servicio.getPrecioLocal().divide(divisor, 3, RoundingMode.CEILING);
						BigDecimal multiplicacion = new BigDecimal(promocionSeleccionada.getDescuento(), MathContext.DECIMAL64);
						BigDecimal precio=desc.multiply(multiplicacion);
						System.out.println("precio despues de todas las cuentas "+precio);
						total = total.add(precio);
						return total;
						
					}else {
						Integer idServicio = st.getIdServicio();
						ServicioDTO servicio = this.sistema.getServicioById(idServicio);
						System.out.println("total else dentro del for: "+total);
						total = total.add(servicio.getPrecioLocal());
						return total;
						
					}
				
			}
		}
		return total;
	}
	
	private BigDecimal actualizaDolarPromo(BigDecimal total, ServicioTurnoDTO st) {
		
			if(promocionSeleccionada != null) {
				if(promocionSeleccionada.getDescuento() > 0) {
					List<Integer> idservicio= sistema.obtenerIdServPromo(promocionSeleccionada.getIdPromocion());
						for(Integer i : idservicio) {
							for(ServicioTurnoDTO serv: serviciosAgregados) {
								if(serv.getIdServicio() == i) {
									ServicioDTO servicio = this.sistema.getServicioById(serv.getIdServicio());
									BigDecimal divisor=new BigDecimal("100");
									BigDecimal desc= servicio.getPrecioDolar().divide(divisor, 3, RoundingMode.CEILING);
									BigDecimal multiplicacion = new BigDecimal(promocionSeleccionada.getDescuento(), MathContext.DECIMAL64);
									BigDecimal precio=desc.multiply(multiplicacion);
									//System.out.println("precio despues de todas las cuentas "+precio);
									total = total.add(precio);
									break;
								}
							}
						}
				}else {
					Integer idServicio = st.getIdServicio();
					ServicioDTO servicio = this.sistema.getServicioById(idServicio);
					total = total.add(servicio.getPrecioDolar());
				}
			}
		return total;
	}
	
	
	/* EDICION DE CITAS */

	public void setearCliente(ClienteDTO clienteAsociado) {
		this.ventanaCita.setCliente(clienteAsociado);
		this.ventanaCita.getTxtNombre().setText(this.ventanaCita.getCliente().getNombre());
		this.ventanaCita.getTxtApellido().setText(this.ventanaCita.getCliente().getApellido());
//		this.ventanaCita.getTxtTelefono().setText(this.citaAEditar.getTelefono());
//		this.ventanaCita.getTxtMail().setText(this.citaAEditar.getMail());
		this.ventanaCita.setearTxt(false);
		this.ventanaCita.getChckbxGenerico().setEnabled(false);
		this.ventanaCita.getChckbxRegistrado().setEnabled(false);
	}
	
	public void setearHoraInicio(LocalTime horaInicio) {
		this.ventanaCita.setHoraInicio(horaInicio);
		this.ventanaCita.getJCBoxHora().setSelectedItem(horaInicio.getHour());
		this.ventanaCita.getJCBoxMinutos().setSelectedItem(horaInicio.getMinute());
	}
	
	public void setearHoraFin(LocalTime horaFin) {
		this.ventanaCita.setHoraFin(horaFin);
		this.ventanaCita.getLbl_Fin().setText(this.ventanaCita.getHoraFin().toString());
	}
	
	public void setearPrecioTotal(BigDecimal total) {
		this.ventanaCita.setTotal(total);
		this.ventanaCita.getLbl_Total().setText(this.ventanaCita.getTotal().toString());
	}
	
	public void setearPrecioTotalUSD (BigDecimal totalUSD) {
		this.ventanaCita.setTotalUSD(totalUSD);
		this.ventanaCita.getLbl_TotalUSD().setText(this.ventanaCita.getTotalUSD().toString());
	}
	
	public void cargarServiciosDelTurno(List<ServicioTurnoDTO> servicios) {
		for (ServicioTurnoDTO st : servicios) {
			this.serviciosAgregados.add(st);
			actualizarServiciosAgregados();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
