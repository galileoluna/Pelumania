package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

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
import presentacion.vista.NuevaVentanaEditarCita;
import util.MailService;

public class NuevoControladorEditarCita implements ActionListener{
	private Sistema sistema;
	private NuevaVentanaEditarCita ventanaEditarCita;
	private static NuevoControladorEditarCita INSTANCE;
	
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
	ServicioDTO servicioSeleccionado;
	
	private static String errorHora;
	private static String errorServicio;
	private static String errorCargarCita;
	
	public CitaDTO citaAEditar; 

	public NuevoControladorEditarCita(Sistema s, CitaDTO citaParaEditar) {
		
		this.ventanaEditarCita = NuevaVentanaEditarCita.getInstance();
		this.citaAEditar = citaParaEditar;
		this.sistema = s;
		clienteGenerico = this.sistema.obtenerClienteById(-1);
		this.ventanaEditarCita.getFrame().setTitle("Editar Cita");
		
		this.ventanaEditarCita.getJDChooserFecha().addPropertyChangeListener(q -> validarFechaElegida(q));
		this.ventanaEditarCita.getBtnEditarFecha().addActionListener(a -> habilitarEditarFecha(a));
		this.ventanaEditarCita.getJCBoxSucursal().addActionListener(b -> seleccionarSucursal(b));
		
//		this.ventanaCita.getChckbxGenerico().addActionListener(c -> mostrarOpcionesClienteGenerico(c));
//		this.ventanaCita.getChckbxRegistrado().addActionListener(d -> mostrarOpcionesClienteRegistrado(d));
		this.ventanaEditarCita.getBtnRegistrar().addActionListener(e -> ventanaRegistrarCliente(e));
		
		this.ventanaEditarCita.getRdBtnServicio().addActionListener(a -> mostrarPanelServicio(a));
		this.ventanaEditarCita.getRdBtnProfesional().addActionListener(b -> mostrarPanelProfesional(b));
		this.ventanaEditarCita.getRdbtnPromocion().addActionListener(c -> mostrarPanelPromociones(c));
		
		this.ventanaEditarCita.getBtnAgregarServicio().addActionListener(d -> agregarServicio(d));
		this.ventanaEditarCita.getBtnEliminarServicio().addActionListener(e-> eliminarServicio(e));
		
		this.ventanaEditarCita.getBtnConfirmar().addActionListener (a -> editarCita(a));
		this.ventanaEditarCita.getBtnCancelar().addActionListener (b -> cancelar(b));
		inicializarArreglos();
	}
	
	public static NuevoControladorEditarCita getInstance(Sistema sistema, CitaDTO citaParaEditar) {
		if ( INSTANCE == null) {
			INSTANCE = new NuevoControladorEditarCita(sistema, citaParaEditar);
		}

		NuevaVentanaEditarCita.getInstance();
		return INSTANCE;
	}
	
	public LocalDate getFecha() {
		return fechaCita;
	}

	public void setFecha(LocalDate fecha) {
		this.fechaCita = fecha;
		ventanaEditarCita.setFechaCita(fecha);
		}

	public void inicializarArreglos() {
		listaSucursales = this.sistema.obtenerSucursales();
		serviciosAgregados = new ArrayList<ServicioTurnoDTO>();
		servicios_panel_servicios = this.sistema.obtenerServicios();
		promociones = this.sistema.obtenerPrmociones();
	}
	
	public void cargarDatos() {
		cargarListaSucursales();
		setearSucursalActual();
		cargarHorarios();
		
		this.ventanaEditarCita.getJCBoxHora().addActionListener(a -> seleccionarHora(a));
		this.ventanaEditarCita.getJCBoxMinutos().addActionListener(a -> seleccionarHora(a));
	}
	
	public SucursalDTO getSucursal() {
		return sucursal;
	}

	public void setSucursal(SucursalDTO sucursal) {
		this.sucursal = sucursal;
	}

	public void cargarListaSucursales() {
		for (SucursalDTO sucu : listaSucursales) {
			this.ventanaEditarCita.getJCBoxSucursal().addItem(sucu);
		}
	}
	
	public void cargarHorarios() {
		for (int i = 8; i<20; i++)
		{
			this.ventanaEditarCita.getJCBoxHora().addItem(i);
		}
		
		for(int i=0; i<59; i=i+5) {
			this.ventanaEditarCita.getJCBoxMinutos().addItem(i);
		}
		
		this.ventanaEditarCita.getJCBoxHora().setSelectedItem(null);
		this.ventanaEditarCita.getJCBoxMinutos().setSelectedItem(null);
	}
	public void setearSucursalActual() {
		this.ventanaEditarCita.getJCBoxSucursal().setSelectedItem(sucursal);
		this.ventanaEditarCita.setSucursal(sucursal);
	}
	
	public void actualizarPanelDinamico(String PanelAMostrar) {
		switch (PanelAMostrar){
		case "Servicios":
			this.ventanaEditarCita.mostrarPanelServicios();
			this.ventanaEditarCita.ocultarPanelProfesionales();
			this.ventanaEditarCita.ocultarPanelPromociones();
			break;
		case "Profesionales":
			this.ventanaEditarCita.mostrarPanelProfesionales();
			this.ventanaEditarCita.ocultarPanelPromociones();
			this.ventanaEditarCita.ocultarPanelServicios();
			break;
		case "Promociones":
			this.ventanaEditarCita.mostrarPanelPromociones();
			this.ventanaEditarCita.ocultarPanelProfesionales();
			this.ventanaEditarCita.ocultarPanelServicios();
			break;
			}
	}
	
	public void mostrarPopUpSucursal() {
		//limpiar todos los campos y recargarlos
		if (!((SucursalDTO)this.ventanaEditarCita.getJCBoxSucursal().getSelectedItem()).equals(sucursal)) {
			this.ventanaEditarCita.getLblAlertaSucursal().setVisible(true);
		}
		else {
			this.ventanaEditarCita.getLblAlertaSucursal().setVisible(false);
		}
	}
	
	/******************* VALIDACIONES *************/
	
	public boolean validarHora(LocalTime horaElegida) {
		if (horaElegida == null) {
			NuevoControladorEditarCita.errorHora = "Debes elegir un horario!";
			return false;
		}
		if (this.ventanaEditarCita.getFechaCita().equals(LocalDate.now())) {
			if (horaElegida.isBefore(LocalTime.now())){
				NuevoControladorEditarCita.errorHora = "No puedes sacar un turno para un horario que ya pasó!";
				return false;
			}
		}
		return true;
	}
	
	public void mostrarErrorHora() {
		JOptionPane.showMessageDialog(null, NuevoControladorEditarCita.errorHora);
	}
	
	/* METODOS PARA LOS CONTROLADORES */
	public void habilitarEditarFecha(ActionEvent a) {
		this.ventanaEditarCita.getJDChooserFecha().setEnabled(true);
	}
	
	public void validarFechaElegida(PropertyChangeEvent b) {
		java.util.Date D_fechaElegida = this.ventanaEditarCita.getJDChooserFecha().getDate();
		LocalDate fechaElegida = D_fechaElegida.toInstant()
			      .atZone(ZoneId.systemDefault())
			      .toLocalDate();
		
		if (fechaElegida.isBefore(LocalDate.now())) {
			this.ventanaEditarCita.mostrarErrorFechaAnterior();
			this.ventanaEditarCita.setFechaCita(null);
		}else {
			this.ventanaEditarCita.ocultarErrorFechaAnteror();
			this.ventanaEditarCita.setFechaCita(fechaElegida);
		}
		
		if (fechaElegida.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
			JOptionPane.showMessageDialog(null, "No puedes reservar citas los Domingos!");
			this.ventanaEditarCita.getJDChooserFecha().setDate(null);
			this.ventanaEditarCita.setFechaCita(null);
			this.ventanaEditarCita.getJDChooserFecha().setEnabled(true);
		}
		
	}
	
	public void seleccionarSucursal(ActionEvent b) {
		SucursalDTO sucuSeleccionada = (SucursalDTO) this.ventanaEditarCita.getJCBoxSucursal().getSelectedItem();
		this.ventanaEditarCita.setSucursal(sucuSeleccionada);
		
		mostrarPopUpSucursal();
		
		this.ventanaEditarCita.ocultarPanelesServicios();
		this.ventanaEditarCita.getRdBtnProfesional().setSelected(false);
		this.ventanaEditarCita.getRdBtnServicio().setSelected(false);
		this.ventanaEditarCita.getRdbtnPromocion().setSelected(false);
		
		serviciosAgregados.clear();
		actualizarServiciosAgregados();
	}

	
	public void mostrarOpcionesClienteRegistrado( ActionEvent d) {
		this.ventanaEditarCita.limpiarTxtCliente();
		this.ventanaEditarCita.setearTxt(false);
		this.ventanaEditarCita.getChckbxGenerico().setSelected(false);
		this.ventanaEditarCita.getBtnBuscar().setEnabled(true);
		this.ventanaEditarCita.getBtnBuscar().addActionListener(r -> buscarCliente(r));
	}
	
	public void mostrarOpcionesClienteGenerico( ActionEvent d) {
		this.ventanaEditarCita.setCliente(clienteGenerico);
		this.ventanaEditarCita.limpiarTxtCliente();
		this.ventanaEditarCita.habilitarCamposClienteGenerico();
		this.ventanaEditarCita.getChckbxRegistrado().setSelected(false);
		this.ventanaEditarCita.getBtnBuscar().setEnabled(false);
	}
	
	private void buscarCliente(ActionEvent r) {
//		ControladorBuscarCliente.getInstance(sistema, this.ventanaEditarCita);
	}

	private void ventanaRegistrarCliente(ActionEvent e) {
		ControladorCliente.getInstance(sistema);
	}
	
	public void seleccionarHora(ActionEvent a) {
		Integer hora = (Integer) this.ventanaEditarCita.getJCBoxHora().getSelectedItem();
		Integer minutos = (Integer) this.ventanaEditarCita.getJCBoxMinutos().getSelectedItem();
		
		if(hora == null || minutos == null) {
		}else {
		LocalTime horaElegida = LocalTime.of(hora, minutos);
		
		if (validarHora(horaElegida)) {
			this.ventanaEditarCita.setHoraInicio(horaElegida);
			this.ventanaEditarCita.setearDatoInicio();
		}else {
			mostrarErrorHora();
			this.ventanaEditarCita.getLbl_Inicio().setText(null);
			this.ventanaEditarCita.getJCBoxMinutos().setSelectedItem(null);
			}
		}
		
		this.ventanaEditarCita.setHoraFin(this.ventanaEditarCita.getHoraInicio());
	}
	
	public void mostrarPanelServicio(ActionEvent a) {
		this.ventanaEditarCita.getRdBtnProfesional().setSelected(false);
		this.ventanaEditarCita.getRdbtnPromocion().setSelected(false);
		if (this.ventanaEditarCita.getRdBtnServicio().isSelected()) {
			actualizarPanelDinamico("Servicios");
			llenarDatosPanelServicio();
			this.ventanaEditarCita.getPanelDinamicoServicios().getTablaServicios().getSelectionModel().addListSelectionListener(l -> actualizarServicioSeleccionado(l));
			this.ventanaEditarCita.getPanelDinamicoServicios().getBtnBuscarServicio().addActionListener(m -> buscarServicio(m));
			this.ventanaEditarCita.getPanelDinamicoServicios().getBtnLimpiarBusqueda().addActionListener(n -> limpiarFiltro(n));
		}else {
			this.ventanaEditarCita.ocultarPanelesServicios();
		}
	}

	public void mostrarPanelProfesional(ActionEvent a) {
		this.ventanaEditarCita.getRdBtnServicio().setSelected(false);
		this.ventanaEditarCita.getRdbtnPromocion().setSelected(false);
		if (this.ventanaEditarCita.getRdBtnProfesional().isSelected()) {
			actualizarPanelDinamico("Profesionales");
			llenarDatosPanelProfesionales();
			this.ventanaEditarCita.getPanelDinamicoProfesionales().getJCBoxProfesional().addActionListener(e -> mostrarServiciosDelProfesional(e));
			this.ventanaEditarCita.getPanelDinamicoProfesionales().getTablaServicios().getSelectionModel().addListSelectionListener(b -> actualizarServicioSeleccionadoPanelProfesional(b));
		}else {
			this.ventanaEditarCita.ocultarPanelesServicios();
		}	
	}

	public void mostrarPanelPromociones(ActionEvent a) {
		this.ventanaEditarCita.getRdBtnProfesional().setSelected(false);
		this.ventanaEditarCita.getRdBtnServicio().setSelected(false);
		if(this.ventanaEditarCita.getRdbtnPromocion().isSelected()) {
			actualizarPanelDinamico("Promociones");
			llenarTablaPromociones();
		}else {
			this.ventanaEditarCita.ocultarPanelesServicios();
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
		System.out.println("Sucursal: " + this.ventanaEditarCita.getSucursal());
		System.out.println("Fecha: "+this.ventanaEditarCita.getFechaCita());
		System.out.println("Cliente: "+ this.ventanaEditarCita.getCliente());
		System.out.println("Telefono: "+ this.ventanaEditarCita.getTxtTelefono().getText());
		System.out.println("Mail: "+ this.ventanaEditarCita.getTxtMail().getText());
		
		System.out.println("Inicio: "+ this.ventanaEditarCita.getHoraInicio());
		imprimirServicios();
		System.out.println("Fin: "+ this.ventanaEditarCita.getHoraFin());
		System.out.println("Total: $"+this.ventanaEditarCita.getTotal());
		System.out.println("Total: USD"+ this.ventanaEditarCita.getTotalUSD());
		
		registrarCita();
	}
	
	public void editarCita(ActionEvent a) {
		CitaDTO citaEditada;
		calcularHorariosServicios();
		this.sistema.reprogramarCita(citaAEditar);
		if (validarCita()) {
			
			if (this.citaAEditar.getIdCita() == -1) {
				citaEditada = new CitaDTO(this.citaAEditar.getIdCita(), -1, this.ventanaEditarCita.getCliente().getIdCliente(), 
					this.ventanaEditarCita.getCliente().getNombre(), this.ventanaEditarCita.getCliente().getApellido(), 
					this.ventanaEditarCita.getTxtTelefono().getText(), this.ventanaEditarCita.getTxtMail().getText(),
					"Activa", this.ventanaEditarCita.getTotal(), this.ventanaEditarCita.getTotalUSD(), 
					this.ventanaEditarCita.getHoraInicio(), this.ventanaEditarCita.getHoraFin(),
					this.ventanaEditarCita.getFechaCita().plusDays(1), this.ventanaEditarCita.getSucursal().getIdSucursal());
			}
			
			else {
					citaEditada = new CitaDTO(this.citaAEditar.getIdCita(), -1, this.ventanaEditarCita.getCliente().getIdCliente(), 
						this.ventanaEditarCita.getCliente().getNombre(), this.ventanaEditarCita.getCliente().getApellido(), 
						this.ventanaEditarCita.getCliente().getTelefono(), this.ventanaEditarCita.getCliente().getMail(),
						"Activa", this.ventanaEditarCita.getTotal(), this.ventanaEditarCita.getTotalUSD(), 
						this.ventanaEditarCita.getHoraInicio(), this.ventanaEditarCita.getHoraFin(),
						this.ventanaEditarCita.getFechaCita().plusDays(1), this.ventanaEditarCita.getSucursal().getIdSucursal());

			}
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
				this.ventanaEditarCita.cerrar();
			} else {
					JOptionPane.showMessageDialog(null, "No se pudo editar la Cita");
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
		if (this.ventanaEditarCita.getFechaCita().getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
			errorCargarCita = "No puedes cargar una cita los Domingos!";
			return false;
		}
		if (this.ventanaEditarCita.getFechaCita().isBefore(LocalDate.now())) {
			errorCargarCita = "La fecha que has elegido no es valida!";
			return false;
		}
		if (this.ventanaEditarCita.getSucursal().getIdSucursal() == -1) {
			errorCargarCita = "No puedes elegir esa sucursal!";
			return false;
		}
		if (this.ventanaEditarCita.getCliente()==null) {
			errorCargarCita = "Debes seleccionar un cliente primero!";
			return false;
		}
		if (serviciosAgregados.isEmpty()) {
			errorCargarCita = "No puedes cargar una cita sin servicios!";
			return false;
		}
		/*
		Boolean ret = true;
		ArrayList<ServicioTurnoDTO> serviciosNoValidos = new ArrayList<ServicioTurnoDTO>();
		for (ServicioTurnoDTO st : serviciosAgregados) {
			boolean servicioValido = validarDisponibilidadProfesional(st.getHoraInicio(), st.getHoraFin(), st.getIdProfesional());
			ret = ret && servicioValido;
			
			if (!servicioValido)
				serviciosNoValidos.add(st);
		}
		
		if (!ret) {
			errorCargarCita = "Uups, has tardado demasiado! ";
			for (ServicioTurnoDTO snv : serviciosNoValidos) {
				ServicioDTO serv = this.sistema.getServicioById(snv.getIdServicio());
				ProfesionalDTO prof = this.sistema.getProfesionalById(snv.getIdProfesional());
				errorCargarCita = errorCargarCita + "El servicio "+ serv.getNombre()+ "no puede ser agregado! "+
				"El profesional "+ prof.getNombre()+" "+prof.getApellido()+ "ya no está disponible en ese horario. \n";
			}
			return false;
		}*/
		return true;
	}
	
	
	public void registrarCita() {
		if (validarCita()) {
			Integer idCitaAgregada;
			
			CitaDTO nuevaCita = new CitaDTO(0, -1, this.ventanaEditarCita.getCliente().getIdCliente(), 
					this.ventanaEditarCita.getCliente().getNombre(), this.clienteGenerico.getApellido(), 
					this.ventanaEditarCita.getTxtTelefono().getText(), this.ventanaEditarCita.getTxtMail().getText(),
					"Activa", this.ventanaEditarCita.getTotal(), this.ventanaEditarCita.getTotalUSD(), 
					this.ventanaEditarCita.getHoraInicio(), this.ventanaEditarCita.getHoraFin(),
					this.ventanaEditarCita.getFechaCita(), this.ventanaEditarCita.getSucursal().getIdSucursal());
		
			if (this.sistema.agregarCita(nuevaCita)) {
				idCitaAgregada = this.sistema.getCitaMax().getIdCita();
				
				for (ServicioTurnoDTO st : serviciosAgregados) 
				{
					st.setIdCita(idCitaAgregada);
					this.sistema.insertServicioTurno(st);
				}
				
				this.mostrarExitoCargarCita(idCitaAgregada);
				this.ventanaEditarCita.cerrar();
				//una vez que se hizo todo bien mandamos el mail
				MailService.enviar(this.sistema, nuevaCita, this.ventanaEditarCita.getCliente());
			}
		}
		else
			mostrarErrorCita();
	}

		
	public void mostrarErrorCita() {
		JOptionPane.showMessageDialog(null, errorCargarCita);
	}
	
	public void mostrarExitoCargarCita(Integer idCitaAgregada) {
		
		Object[] opciones = {"Aceptar",
                "Ver Comprobante"};
				int n = JOptionPane.showOptionDialog(null,
				"La cita se editó correctamente",
				"Información",
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
		this.ventanaEditarCita.cerrar();
	}
	/* ****************************************************************** */
	/* *********** METODOS PARA EL MANEJO DE LOS SERVICIOS ************** */
	/* ****************************************************************** */
	
	public void agregarServicio(ActionEvent d) {
		if (this.ventanaEditarCita.getRdBtnServicio().isSelected()) {
			ProfesionalDTO profesional = (ProfesionalDTO) this.ventanaEditarCita.getPanelDinamicoServicios().getJCBoxProfesionalesDeServicio().getSelectedItem();
			Integer idProfesional = (profesional == null) ? null : profesional.getIdProfesional();
			ServicioTurnoDTO serv = new ServicioTurnoDTO(servicioSeleccionado.getIdServicio(), idProfesional);
			if (validarHora(this.ventanaEditarCita.getHoraInicio())) {
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
			}else
				mostrarErrorHora();
		}
		
		
		if (this.ventanaEditarCita.getRdBtnProfesional().isSelected()) {
			ProfesionalDTO profesional = (ProfesionalDTO) this.ventanaEditarCita.getPanelDinamicoProfesionales().getJCBoxProfesional().getSelectedItem();
			Integer idProfesional = (profesional == null) ? null : profesional.getIdProfesional();
			ServicioTurnoDTO serv = new ServicioTurnoDTO(servicioSeleccionado.getIdServicio(), idProfesional);
			if (validarHora(this.ventanaEditarCita.getHoraInicio())) {
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
			}else
				mostrarErrorHora();
		}
	}
	
	private void eliminarServicio(ActionEvent e) {
		int[] filasSeleccionadas = ventanaEditarCita.getTablaServiciosAgregados().getSelectedRows();
	       
    	for (int fila : filasSeleccionadas)
    	{
        	if(serviciosAgregados.get(fila)!=null) {
        		this.sistema.getServicioById(serviciosAgregados.get(fila).getIdServicio());
        		
        		ServicioTurnoDTO servicioSeleccionado = serviciosAgregados.get(fila);
        		serviciosAgregados.remove(servicioSeleccionado);
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
		ProfesionalDTO profesionalSeleccionado = (ProfesionalDTO) this.ventanaEditarCita.getPanelDinamicoProfesionales().getJCBoxProfesional().getSelectedItem();
		if (profesionalSeleccionado != null) {
			cargarServiciosDelProfesional(profesionalSeleccionado.getIdProfesional());
			}
		}
	
	public boolean validarAntesDeAgregarServicio(ServicioTurnoDTO servicio) {
		if (servicio == null) {
			NuevoControladorEditarCita.errorServicio = "No has seleccionado ningun servicio!";
			return false;
		}
		if (this.ventanaEditarCita.getHoraInicio() == null) {
			NuevoControladorEditarCita.errorServicio = "Debes elegir la hora de inicio del turno para agregar servicios!";
			return false;
		}
		
		if (servicio.getIdProfesional() == null) {
			NuevoControladorEditarCita.errorServicio = "Debes seleccionar un profesional para ese servicio!";
			return false;
		}
		
		if (serviciosAgregados.contains(servicio)) {
			NuevoControladorEditarCita.errorServicio = "Ese servicio ya fue agregado!";
			return false;
		}
		ProfesionalDTO profesional = this.sistema.getProfesionalById(servicio.getIdProfesional());
		
		System.out.println("Horarios servicio: "+servicio.getHoraInicio() +"\n"+ servicio.getHoraFin());
		if (!validarDisponibilidadProfesional(servicio.getHoraInicio(), servicio.getHoraFin(), profesional.getIdProfesional())) {
			NuevoControladorEditarCita.errorServicio = "El profesional "+ profesional.getNombre()+" "+
					profesional.getApellido()+" no está disponible en ese horario!"+ 
					"Tiene una cita desde: "+"__:__"+"hasta: "+ "__:__";
			return false;
		}
		
		String diaDeLaSemana = diaDeLaSemana();
		if (!validarProfesionalEnSucursal(profesional.getIdProfesional(), servicio.getHoraInicio(), servicio.getHoraFin(), diaDeLaSemana)) {
			NuevoControladorEditarCita.errorServicio = "El profesional no trabaja en ese horario!";
			return false;
		}
		return true;
	}
	
	private boolean validarDisponibilidadProfesional(LocalTime inicio, LocalTime fin, int idProfesional) {
		if (idProfesional == -1) {
			return true;
		}
		Integer ocupado = this.sistema.profesionalOcupado(idProfesional, inicio, fin, this.ventanaEditarCita.getFechaCita());
		System.out.println(ocupado);
		if (ocupado == 1) {
			NuevoControladorEditarCita.errorServicio = "El profesional esta ocupado en ese horario!";
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
		JOptionPane.showMessageDialog(null, NuevoControladorEditarCita.errorServicio);
	}
	
	public void calcularHorariosServicios() {
		LocalTime horaInicial = this.ventanaEditarCita.getHoraInicio();
		LocalTime horaFinalizacionServicio = null;
		for (ServicioTurnoDTO st : serviciosAgregados) {
			ServicioDTO servicio = sistema.getServicioById(st.getIdServicio());
			st.setHoraInicio(horaInicial);
			
			horaFinalizacionServicio = horaInicial.plusHours(servicio.getDuracion().getHour());
			horaFinalizacionServicio = horaFinalizacionServicio.plusMinutes(servicio.getDuracion().getMinute());
			st.setHoraFin(horaFinalizacionServicio);	
			horaInicial = horaFinalizacionServicio;	
		}
		
		this.ventanaEditarCita.setHoraFin(horaFinalizacionServicio);
	}
	
	public void calcularHorario(ServicioTurnoDTO serv) {
		ServicioDTO servicio = this.sistema.getServicioById(serv.getIdServicio());
		System.out.println("HoraFin al calcular horario es:"+ this.ventanaEditarCita.getHoraFin() );
		serv.setHoraInicio(this.ventanaEditarCita.getHoraFin());
		LocalTime horaFin = serv.getHoraInicio().plusHours(servicio.getDuracion().getHour());
		horaFin = horaFin.plusMinutes(servicio.getDuracion().getMinute());
		serv.setHoraFin(horaFin);
	}

	private void actualizarHoraFin() {
		if (serviciosAgregados.isEmpty()) {
			this.ventanaEditarCita.setHoraFin(ventanaEditarCita.getHoraInicio());
			this.ventanaEditarCita.getLbl_Fin().setText(this.ventanaEditarCita.getHoraFin().toString());
		}else {
			this.ventanaEditarCita.setHoraFin(this.serviciosAgregados.get(serviciosAgregados.size()-1).getHoraFin());
			this.ventanaEditarCita.getLbl_Fin().setText(this.ventanaEditarCita.getHoraFin().toString());
		}
	}
	
	private BigDecimal actualizarPrecioTotal() {
			BigDecimal total = BigDecimal.valueOf(0);
			for (ServicioTurnoDTO st : serviciosAgregados) {
				Integer idServicio = st.getIdServicio();
				ServicioDTO servicio = this.sistema.getServicioById(idServicio);
				total = total.add(servicio.getPrecioLocal());
			}
			//Setear el total a la ventana
			this.ventanaEditarCita.setTotal(total);
			this.ventanaEditarCita.getLbl_Total().setText(this.ventanaEditarCita.getTotal().toString());
			return total;
	}
	
	public BigDecimal actualizarPrecioTotalDolar() {
		BigDecimal total = BigDecimal.valueOf(0);
		for (ServicioTurnoDTO st : serviciosAgregados) {
			Integer idServicio = st.getIdServicio();
			ServicioDTO servicio = this.sistema.getServicioById(idServicio);
			total = total.add(servicio.getPrecioDolar());
		}
		this.ventanaEditarCita.setTotalUSD(total);
		this.ventanaEditarCita.getLbl_TotalUSD().setText(this.ventanaEditarCita.getTotalUSD().toString());
		return total;
	}
	
	public int actualizarPuntos() {
		int totalPuntos = 0;
		for (ServicioTurnoDTO st : serviciosAgregados) {
			Integer idServicio = st.getIdServicio();
			ServicioDTO servicio = this.sistema.getServicioById(idServicio);
			totalPuntos = totalPuntos + (servicio.getPuntos());
		}
		this.ventanaEditarCita.setPuntos(totalPuntos);
		this.ventanaEditarCita.getLbl_Puntos().setText(Integer.toString(this.ventanaEditarCita.getPuntos()));
		return totalPuntos;
	}
	
	public String diaDeLaSemana() {
		String dds = "";
		switch ( this.ventanaEditarCita.getFechaCita().getDayOfWeek() ) {
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
			this.ventanaEditarCita.getPanelDinamicoServicios().getModelServicios().setRowCount(0); //Para vaciar la tabla
			this.ventanaEditarCita.getPanelDinamicoServicios().getModelServicios().setColumnCount(0);
			this.ventanaEditarCita.getPanelDinamicoServicios().getModelServicios().setColumnIdentifiers(this.ventanaEditarCita.getPanelDinamicoServicios().getNombreColumnas());

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
				this.ventanaEditarCita.getPanelDinamicoServicios().getModelServicios().addRow(fila);
			}
	}
	
	public void actualizarServiciosAgregados() {
		this.ventanaEditarCita.getModelServiciosAgregados().setRowCount(0); //Para vaciar la tabla
		this.ventanaEditarCita.getModelServiciosAgregados().setColumnCount(0);
		this.ventanaEditarCita.getModelServiciosAgregados().setColumnIdentifiers(this.ventanaEditarCita.getNombreColumnasAgregadas());

		for (ServicioTurnoDTO st : serviciosAgregados)
		{
			ServicioDTO servicio = sistema.getServicioById(st.getIdServicio());
			ProfesionalDTO profesional = sistema.getProfesionalById(st.getIdProfesional());
			String nombreServicio = servicio.getNombre();
			String nombreProfesional = profesional.getNombre()+" "+profesional.getApellido();
			Object[] fila = {nombreServicio, nombreProfesional};
			this.ventanaEditarCita.getModelServiciosAgregados().addRow(fila);
		}
}
	
	public void llenarDatosPanelServicio() {
		cargarServiciosEnTabla(servicios_panel_servicios);
	}
	
	public ServicioDTO getServicioSeleccionado() {
		ServicioDTO servicioSeleccionado = null;;
		int[] filasSeleccionadas = this.ventanaEditarCita.getPanelDinamicoServicios().getTablaServicios().getSelectedRows();
	       
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
		this.ventanaEditarCita.getPanelDinamicoServicios().getJCBoxProfesionalesDeServicio().removeAllItems();
		for (ProfesionalDTO prof : profesionales_panel_servicios) {
			if(prof.getEstado().equals("Activo") && 
			prof.getIdSucursalOrigen() == this.ventanaEditarCita.getSucursal().getIdSucursal()
			|| prof.getIdProfesional() == -1)
				this.ventanaEditarCita.getPanelDinamicoServicios().getJCBoxProfesionalesDeServicio().addItem(prof);
		}
	}
	
	private void buscarServicio(ActionEvent m) {
		String variable = "Nombre";
		String value= this.ventanaEditarCita.getPanelDinamicoServicios().getTxtbuscarServicios().getText();
		this.servicios_panel_servicios = this.sistema.obtenerServicioConBuscador(variable, value);
		llenarDatosPanelServicio();
}
	
	private void limpiarFiltro(ActionEvent n) {
		this.servicios_panel_servicios = this.sistema.obtenerServicios();
		llenarDatosPanelServicio();
		this.ventanaEditarCita.getPanelDinamicoServicios().getTxtbuscarServicios().setText(null);
	}
	
	/* PANEL PROFESIONALES */
	
	public void llenarDatosPanelProfesionales() {
		cargarProfesionales();
	}
	
	private void cargarProfesionales() {
		profesionales_panel_profesionales = this.sistema.obtenerProfesional();
		this.ventanaEditarCita.getPanelDinamicoProfesionales().getJCBoxProfesional().removeAllItems();
		for (ProfesionalDTO prof : profesionales_panel_profesionales) {
			if(prof.getEstado().equals("Activo") && 
				prof.getIdSucursalOrigen() == this.ventanaEditarCita.getSucursal().getIdSucursal()
				|| prof.getIdProfesional() == -1)
				this.ventanaEditarCita.getPanelDinamicoProfesionales().getJCBoxProfesional().addItem(prof);
		}
		
		this.ventanaEditarCita.getPanelDinamicoProfesionales().getJCBoxProfesional().setSelectedItem(null);
	}
	
	public void cargarServiciosDelProfesional(Integer idProfesional) {
		this.ventanaEditarCita.getPanelDinamicoProfesionales().getModelServicios().setRowCount(0); //Para vaciar la tabla
		this.ventanaEditarCita.getPanelDinamicoProfesionales().getModelServicios().setColumnCount(0);
		this.ventanaEditarCita.getPanelDinamicoProfesionales().getModelServicios().setColumnIdentifiers(this.ventanaEditarCita.getPanelDinamicoServicios().getNombreColumnas());

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
			this.ventanaEditarCita.getPanelDinamicoProfesionales().getModelServicios().addRow(fila);
		}
	}
	
	public ServicioDTO getServicioSeleccionadoPanelProfesional() {
		ServicioDTO servicioSeleccionado = null;;
		int[] filasSeleccionadas = this.ventanaEditarCita.getPanelDinamicoProfesionales().getTablaServicios().getSelectedRows();
		ProfesionalDTO profSeleccionado = (ProfesionalDTO) this.ventanaEditarCita.getPanelDinamicoProfesionales().getJCBoxProfesional().getSelectedItem();
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
		this.ventanaEditarCita.getPanelDinamicoPromociones().getModelPromocion().setRowCount(0); //Para vaciar la tabla
		this.ventanaEditarCita.getPanelDinamicoPromociones().getModelPromocion().setColumnCount(0);
		this.ventanaEditarCita.getPanelDinamicoPromociones().getModelPromocion().setColumnIdentifiers(this.ventanaEditarCita.getPanelDinamicoPromociones().getNombreColumnas());

		for (PromocionDTO p : promociones)		{ 
			String descripcion = p.getDescripcion();
			Date FechaInicio = p.getFechaInicio();
			Date FechaFin=p.getFechaFin();
			Double descuento=p.getDescuento();
			int puntos=p.getPuntos();
			String estado=p.getEstado();
			Object[] fila = {descripcion, FechaInicio,FechaFin,descuento,puntos,estado};
			this.ventanaEditarCita.getPanelDinamicoPromociones().getModelPromocion().addRow(fila);
		}
		
	} 
	
	public PromocionDTO getPromocionSeleccionada() {
		PromocionDTO promocionSeleccionada = null;;
		
		int[] filasSeleccionadas = this.ventanaEditarCita.getPanelDinamicoPromociones().getTablaPromocion().getSelectedRows();
    	for (int fila : filasSeleccionadas)
    	{
        	if(promociones.get(fila)!=null) {	 
        		Integer idPromocion = promociones.get(fila).getIdPromocion();
        		promocionSeleccionada = this.sistema.getPromocionById(idPromocion);
        	}
    	}
    	return promocionSeleccionada;
	}
	
	/* EDICION DE CITAS */

	public void setearCliente(ClienteDTO clienteAsociado) {
		this.ventanaEditarCita.setCliente(clienteAsociado);
		this.ventanaEditarCita.getTxtNombre().setText(this.ventanaEditarCita.getCliente().getNombre());
		this.ventanaEditarCita.getTxtApellido().setText(this.ventanaEditarCita.getCliente().getApellido());
		this.ventanaEditarCita.getTxtTelefono().setText(this.citaAEditar.getTelefono());
		this.ventanaEditarCita.getTxtMail().setText(this.citaAEditar.getMail());
		this.ventanaEditarCita.setearTxt(false);
		this.ventanaEditarCita.getChckbxGenerico().setEnabled(false);
		this.ventanaEditarCita.getChckbxRegistrado().setEnabled(false);
	}
	
	public void setearHoraInicio(LocalTime horaInicio) {
		this.ventanaEditarCita.setHoraInicio(horaInicio);
		this.ventanaEditarCita.getJCBoxHora().setSelectedItem(horaInicio.getHour());
		this.ventanaEditarCita.getJCBoxMinutos().setSelectedItem(horaInicio.getMinute());
	}
	
	public void setearHoraFin(LocalTime horaFin) {
		this.ventanaEditarCita.setHoraFin(horaFin);
		this.ventanaEditarCita.getLbl_Fin().setText(this.ventanaEditarCita.getHoraFin().toString());
	}
	
	public void setearPrecioTotal(BigDecimal total) {
		this.ventanaEditarCita.setTotal(total);
		this.ventanaEditarCita.getLbl_Total().setText(this.ventanaEditarCita.getTotal().toString());
	}
	
	public void setearPrecioTotalUSD (BigDecimal totalUSD) {
		this.ventanaEditarCita.setTotalUSD(totalUSD);
		this.ventanaEditarCita.getLbl_TotalUSD().setText(this.ventanaEditarCita.getTotalUSD().toString());
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