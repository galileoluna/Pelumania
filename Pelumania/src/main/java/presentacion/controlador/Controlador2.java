package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;

import org.apache.log4j.Logger;

import dto.CitaDTO;
import dto.ClienteDTO;
import dto.ProfesionalDTO;
import dto.ServicioDTO;
import dto.ServicioTurnoDTO;
import dto.SucursalDTO;
import dto.UsuarioDTO;
import modelo.Sistema;
import presentacion.Reportes.ReporteComprobante;
import presentacion.vista.NuevaVista;

public class Controlador2 implements ActionListener{
	/*
	 * Variables Globales importantes 
	 */
	private Sistema sistema;
	private NuevaVista nvista;
	private UsuarioDTO usuario;
	private SucursalDTO sucursal;
	
	private Logger log = Logger.getLogger(Controlador2.class);	
	
	private static LocalDate fechaSeleccionada;
	private CitaDTO citaSeleccionada;
	
	/*
	 * Controladores a instanciar 
	 */
	
	private ControladorCita controladorCita;
	private ControladorCita controladorEditarCita;

	private ControladorCliente controladorCliente;
	private ControladorServicio controladorServicio;
	private ControladorProfesional controladorProfesional;
	private ControladorAgregarCita controladorAgregarCita;
	private ControladorPromocion controladorPromocion;
	private ControladorSucursal controladorSucursal;
	private ControladorPromocionesVigentes controladorPromoVigente;
	private ControladorCaja controladorCaja;
	private ControladorCategoriaMovimientoCaja controladorCategoriaMovimientoCaja;
	private ControladorReportesPorLocal controladorReportesPorLocal;
	private ControladorReporteGeneral controladorReporteGeneral;
	private ControladorReportePorServicio controladorReportePorServicio;
	private ControladorReportePorCliente controladorReportePorCliente;
	private ControladorReportePorProfesional controladorReportePorProfesional;
	private ControladorReporteRankingDeVentas controladorReporteRankingVentas;


	/*
	 * Arreglos que se utilizan en la vista
	 */
	
	private List<CitaDTO> citasDelDia;
	private List<CitaDTO> citasEnTabla;

	private List<ProfesionalDTO> profesionalesEnSucursal;
	
	//No son lo mismo las citas del Dia, que las que estan en la tabla. Esta segunda es por los filtros
	
	public Controlador2 (NuevaVista nvista, Sistema sistema, UsuarioDTO usuario) {
		this.nvista = nvista;
		this.sistema = sistema;
		this.usuario=usuario;
		
		CargarDatosUsuarioYSurcursal();
		
		citasDelDia = new ArrayList<CitaDTO>();
		citasEnTabla = new ArrayList<CitaDTO>();
		
		setearFechaSeleccionadaHoy();
		actualizarDiaSeleccionado();
		refrescarTablaCitas();
		getPermisos();
		
		this.nvista.getMntmGestionDeServicios().addActionListener(a->ventanaServicios(a));
		this.nvista.getMntmGestionDeProfesionales().addActionListener(b->ventanaProfesionales(b));
		this.nvista.getMntmGestionDeClientes().addActionListener(c -> ventanaClientes(c));
		this.nvista.getMntmGestionDePromociones().addActionListener(d -> ventanaPromociones(d));
		this.nvista.getMntmVerPromocionesVigentes().addActionListener(e -> verPromocionesVigentes(e));
		this.nvista.getMntmGestionDeSucursales().addActionListener(f -> ventanaSucursales(f));
		this.nvista.getMntmUtilizarCaja().addActionListener(g -> ventanaCaja(g));
		this.nvista.getMntmConsultarCategorias().addActionListener(h -> ventanCategoriaMovimientoCaja(h));

        this.nvista.getMntmReporteLocal().addActionListener(l -> ventanaReportesLocal(l));
		this.nvista.getMntmReporteGeneral().addActionListener(h -> ventanaReporteGeneral(h));
		this.nvista.getMntmReportePorServicio().addActionListener(l -> ventanaReporteServicio(l));
		this.nvista.getMntmReportePorCliente().addActionListener(g -> ventanaReporteCliente(g));
		this.nvista.getMntmReportePorProfesional().addActionListener(l -> ventanaReporteProfesional(l));
		this.nvista.getMntmReporteRanking().addActionListener(g ->ventanaReporteRanking(g));
		
		
		this.nvista.getCalendario().addPropertyChangeListener(i -> actualizarDiaSeleccionado(i));
		// AGREGARLE CONTROLADOR A LA TABLA PARA QUE AL ELEGIR UNA FILA SE HABILITEN LOS BOTONES
		
		this.nvista.getBtn_Agregar().addActionListener(k -> abrirVentanaCitaNueva(k));
		this.nvista.getBtn_Editar().addActionListener(j -> abrirVentanaEditarCita(j));
		this.nvista.getBtn_Cancelar().addActionListener(l -> cancelarCita(l));
		
		//cobrar la cita desde la caja
		this.nvista.getBtn_Finalizar().addActionListener(l -> finalizarCita(l));
		
		this.nvista.getTablaCitas().getSelectionModel().addListSelectionListener(l -> actualizarCitaSeleccionada(l));

		this.nvista.getRdbtnServicios().addActionListener(m -> cargarPanelDinamicoServicios(m));
		this.nvista.getRdbtnProfesional().addActionListener(m -> cargarPanelDinamicoProfesional(m));
		this.nvista.getRdbtnRangoHorario().addActionListener(m -> cargarPanelDinamicoRangoHorario(m));
		this.nvista.getRdbtnEstado().addActionListener(m -> cargarPanelDinamicoEstado(m));
		this.nvista.getBtnLimpiarFiltros().addActionListener(n -> limpiarFiltros(n));
		this.nvista.getBtn_VerComprobante().addActionListener(l -> verComprobante(l));
		
		log.info("Controlador inicializado! La fecha es: "+fechaSeleccionada);
	}

	private void abrirVentanaCitaNueva(ActionEvent x) {
		
		if (validarFechaSeleccionada())
			{
			controladorCita = ControladorCita.getInstance(sistema);
			controladorCita.setFecha(fechaSeleccionada);
			System.out.println("La sucursal en controlador es: "+sucursal);
			controladorCita.setSucursal(sucursal);
			controladorCita.setearSucursalActual();
			controladorCita.cargarDatos();
			}
		
	}
	
	private void abrirVentanaEditarCita(ActionEvent j) {
		controladorEditarCita = ControladorCita.getInstance(sistema, citaSeleccionada);
		List<ServicioTurnoDTO> serviciosDelTurno = this.sistema.getServicioTurnoByIdCita(citaSeleccionada.getIdCita());
		
		controladorEditarCita.setFecha(citaSeleccionada.getFecha().minusDays(1));
		controladorEditarCita.setSucursal(this.sistema.getSucursalById(citaSeleccionada.getIdSucursal()));
		controladorEditarCita.setearSucursalActual();
		controladorEditarCita.cargarDatos();
		controladorEditarCita.setearCliente(this.sistema.obtenerClienteById(this.citaSeleccionada.getIdCliente()));
		controladorEditarCita.setearHoraInicio(this.citaSeleccionada.getHoraInicio());
		controladorEditarCita.setearHoraFin(this.citaSeleccionada.getHoraFin());
		controladorEditarCita.setearPrecioTotal(this.citaSeleccionada.getPrecioLocal());
		controladorEditarCita.setearPrecioTotalUSD(this.citaSeleccionada.getPrecioDolar());
		controladorEditarCita.cargarServiciosDelTurno(serviciosDelTurno);
		
		
		System.out.println(controladorEditarCita.citaAEditar);
		System.out.println(controladorEditarCita.citaAEditar.getIdCita());
		System.out.println(controladorEditarCita.citaAEditar.getIdCliente());
		System.out.println(controladorEditarCita.citaAEditar.getFecha());
		System.out.println(controladorEditarCita.citaAEditar.getHoraInicio());
	}


	private void getPermisos() {
		int rol= this.usuario.getIdRol();
		switch(rol) {
		  case 1:
		  case 5:
		    
		    break;
		  case 2:
			  	 this.nvista.getJM_Profesional().setVisible(false);
				 this.nvista.getJM_Servicio().setVisible(false);
				 this.nvista.getJM_Cliente().setVisible(false);
				 this.nvista.getJM_Promociones().setVisible(false);
				 this.nvista.getJM_Sucursales().setVisible(false);
				 this.nvista.getJM_Caja().setVisible(false);
				 this.nvista.getBtn_Agregar().setEnabled(false);
				 this.nvista.getMntmReporteGeneral().setVisible(false);
				 this.nvista.getMntmReportePorCliente().setVisible(false);
				 this.nvista.getMntmReportePorProfesional().setVisible(false);
				 this.nvista.getMntmReportePorServicio().setVisible(false);
				 this.nvista.getMntmReporteLocal().setVisible(false);
		    break;
		  case 3:
			  	this.nvista.getJM_Reportes().setVisible(false);
				this.nvista.getMntmGestionDePromociones().setVisible(false);
				this.nvista.getJM_Sucursales().setVisible(false);
			  break;
		  case 4:
				 this.nvista.getJM_Servicio().setVisible(false);
				 this.nvista.getJM_Cliente().setVisible(false);
				 this.nvista.getJM_Caja().setVisible(false);
				 this.nvista.getBtn_Agregar().setEnabled(false);
				 this.nvista.getMntmReporteGeneral().setVisible(false);
				 this.nvista.getMntmReportePorCliente().setVisible(false);
				 this.nvista.getMntmReportePorProfesional().setVisible(false);
				 this.nvista.getMntmReportePorServicio().setVisible(false);
				 this.nvista.getMntmReporteRanking().setVisible(false);
			 break;
		}		
	}

	private void CargarDatosUsuarioYSurcursal() {
		sucursal = this.sistema.getSucursalById(usuario.getIdSucursal());
		this.nvista.getLblUsuario().setText(usuario.getNombre()+" "+usuario.getApellido());
		this.nvista.getLblSucursal().setText(sucursal.getNombreSucursal());
	}
	
	private String getSucursal(int idSucursal) {
		SucursalDTO sucursal= sistema.getSucursalById(idSucursal);
		return sucursal.getNombreSucursal(); 
	}

	private void ventanaServicios(ActionEvent a) {
		this.controladorServicio = ControladorServicio.getInstance(sistema,usuario);
	}

	private void ventanaClientes(ActionEvent b) {
		this.controladorCliente = ControladorCliente.getInstance(sistema);
	}

	private void ventanaProfesionales (ActionEvent c) {
		this.controladorProfesional= ControladorProfesional.getInstance(sistema,usuario);
	}
	private void ventanaPromociones(ActionEvent d) {
		this.controladorPromocion= ControladorPromocion.getInstance(sistema);
	}
	private void ventanaSucursales (ActionEvent e) {
		this.controladorSucursal= ControladorSucursal.getInstance(sistema,usuario);
	}
	private void verPromocionesVigentes(ActionEvent f) {
		this.controladorPromoVigente=ControladorPromocionesVigentes.getInstance(sistema,nvista);
	}

	private void ventanCategoriaMovimientoCaja(ActionEvent g) {
		this.controladorCategoriaMovimientoCaja = ControladorCategoriaMovimientoCaja.getInstance(sistema);
	}

	private void ventanaCaja(ActionEvent h) {
		this.controladorCaja = ControladorCaja.getInstance(sistema, this);
	}
	
		private void ventanaReportesLocal(ActionEvent l) {
		this.controladorReportesPorLocal= ControladorReportesPorLocal.getInstance(sistema);
	}
	
	private void ventanaReporteGeneral(ActionEvent h) {
		this.controladorReporteGeneral=ControladorReporteGeneral.getInstance(sistema);
	}

	private void ventanaReporteServicio(ActionEvent l) {
		this.controladorReportePorServicio=ControladorReportePorServicio.getInstance(sistema);
	}
	
	private void ventanaReporteCliente(ActionEvent g) {
		this.controladorReportePorCliente=ControladorReportePorCliente.getInstance(sistema);
	}
	
	private void ventanaReporteProfesional(ActionEvent l) {
		this.controladorReportePorProfesional=ControladorReportePorProfesional.getInstance(sistema);
	}

	private void ventanaReporteRanking(ActionEvent g) {
		this.controladorReporteRankingVentas=ControladorReporteRankingDeVentas.getInstance(sistema);
	}
	
	private void ventanaAgregarCita(ActionEvent k) {
		if (validarFechaSeleccionada()) {
			ControladorAgregarCita.setANIO(fechaSeleccionada.getYear());
			ControladorAgregarCita.setMES(fechaSeleccionada.getMonthValue());
			ControladorAgregarCita.setDIA(fechaSeleccionada.getDayOfMonth());
			
			this.controladorAgregarCita = ControladorAgregarCita.getInstance(sistema);
		}
	}
	
	private void cancelarCita(ActionEvent e) {
		int confirm = JOptionPane.showOptionDialog(null, "Estas seguro que deseas cancelar la cita?","Confirmacion", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, null, null);
		if (confirm == 0) {
			this.sistema.cancelarCita(citaSeleccionada);
			
			limpiarTablas();
			citasDelDia = obtenerCitasDelDia(getFechaSeleccionadaAsString());
			cargarListaConCitas();
			refrescarTablaCitas();
		}
	}
	
	private void cargarPanelDinamicoServicios(ActionEvent m) {
		this.nvista.getRdbtnProfesional().setEnabled(false);
		this.nvista.getRdbtnEstado().setEnabled(false);
		this.nvista.getRdbtnRangoHorario().setEnabled(false);
		this.nvista.cargarPanelDinamicoFiltros("Servicio");
		
		List<ServicioDTO> servicios = this.sistema.obtenerServicios();
		for (ServicioDTO serv : servicios) {
			this.nvista.getJCBoxFiltroServicio().addItem(serv);
		}
		
		this.nvista.getJCBoxFiltroServicio().updateUI();
		this.nvista.getJCBoxFiltroServicio().addActionListener(s -> filtrarPorServicio(s));
	}
	
	private void cargarPanelDinamicoProfesional(ActionEvent m) {
		this.nvista.getRdbtnServicios().setEnabled(false);
		this.nvista.getRdbtnEstado().setEnabled(false);
		this.nvista.getRdbtnRangoHorario().setEnabled(false);
		this.nvista.cargarPanelDinamicoFiltros("Profesional");
		
		profesionalesEnSucursal = this.sistema.getProfesionalByIdSucursal(sucursal.getIdSucursal());
		for (ProfesionalDTO profEnSucu : profesionalesEnSucursal) {
			this.nvista.getJCBoxFiltroProfesional().addItem(profEnSucu);
		}
		this.nvista.getJCBoxFiltroProfesional().updateUI();
		this.nvista.getJCBoxFiltroProfesional().addActionListener(s -> filtrarPorProfesional(s));
	}
	
	private void cargarPanelDinamicoRangoHorario(ActionEvent m) {
		this.nvista.getRdbtnProfesional().setEnabled(false);
		this.nvista.getRdbtnEstado().setEnabled(false);
		this.nvista.getRdbtnServicios().setEnabled(false);
		this.nvista.cargarPanelDinamicoFiltros("RangoHorario");
		
		for (int i = 8; i<20; i++) {
			this.nvista.getJCBoxDe().addItem(i);
			this.nvista.getJCBoxA().addItem(i);
		}
		
		this.nvista.getJCBoxDe().updateUI();
		this.nvista.getJCBoxA().updateUI();
		
		this.nvista.getJCBoxDe().addActionListener(s -> filtrarPorRangoHorario(s));
		this.nvista.getJCBoxA().addActionListener(s -> filtrarPorRangoHorario(s));
	}
	
	private void cargarPanelDinamicoEstado(ActionEvent m) {		
		this.nvista.getRdbtnProfesional().setEnabled(false);
		this.nvista.getRdbtnServicios().setEnabled(false);
		this.nvista.getRdbtnRangoHorario().setEnabled(false);
		this.nvista.cargarPanelDinamicoFiltros("Estado");
		
		this.nvista.getJCBoxFiltroEstado().addActionListener(s -> filtrarPorEstado(s));
	}
	
	private void filtrarPorEstado(ActionEvent s) {
		String estadoSeleccionado = (String) this.nvista.getJCBoxFiltroEstado().getSelectedItem();
		if (this.nvista.getRdbtnEstado().isSelected()) {
			this.nvista.filtrar(estadoSeleccionado);
			actualizarCitasPorEstado(estadoSeleccionado);
		}else {
			this.nvista.noOrdenar();
			obtenerCitasDelDia(getFechaSeleccionadaAsString());
			cargarListaConCitas();
			refrescarTablaCitas();
		}
	}
	
	private void limpiarFiltros(ActionEvent n) {
		this.nvista.limpiarFiltros();
		limpiarTablas();
		citasDelDia = obtenerCitasDelDia(getFechaSeleccionadaAsString());
		cargarListaConCitas();
		refrescarTablaCitas();
		habilitarBotonAgregar();
	}
	
	private void actualizarCitasPorEstado(String estado) {
		for (CitaDTO cita : citasDelDia) {
			if (cita.getEstado().equals(estado))
				citasEnTabla.add(cita);
		}
	}
	
	private void filtrarPorProfesional(ActionEvent s) {
		ProfesionalDTO profesionalSeleccionado = (ProfesionalDTO) 
				this.nvista.getJCBoxFiltroProfesional().getSelectedItem();
		if (this.nvista.getRdbtnProfesional().isSelected()) {
			limpiarTablas();
			citasDelDia = obtenerCitasDelDia(getFechaSeleccionadaAsString());
			actualizarCitasPorProfesional(profesionalSeleccionado.getIdProfesional());
			refrescarTablaCitas();
		}else {
			limpiarTablas();
			refrescarTablaCitas();
		}
	}
	
	private void filtrarPorServicio(ActionEvent s) {
		ServicioDTO servicioSeleccionado = (ServicioDTO) 
				this.nvista.getJCBoxFiltroServicio().getSelectedItem();
		if (this.nvista.getRdbtnServicios().isSelected()) {
			limpiarTablas();
			citasDelDia = obtenerCitasDelDia(getFechaSeleccionadaAsString());
			actualizarCitasPorServicio(servicioSeleccionado.getIdServicio());
			refrescarTablaCitas();
		}else {
			limpiarTablas();
			refrescarTablaCitas();
		}
	}
	
	private void filtrarPorRangoHorario(ActionEvent s) {
		
		Integer intDeSeleccionada = (Integer) this.nvista.getJCBoxDe().getSelectedItem();
		Integer intASeleccionada = (Integer) this.nvista.getJCBoxA().getSelectedItem();
		
		LocalTime horaDeSeleccionada = LocalTime.of(intDeSeleccionada, 0);
		LocalTime horaASeleccionada = LocalTime.of(intASeleccionada, 0);
		
		if (this.nvista.getRdbtnRangoHorario().isSelected()) {
			limpiarTablas();
			citasDelDia = obtenerCitasDelDia(getFechaSeleccionadaAsString());
			actualizarCitasPorRangoHorario(horaDeSeleccionada, horaASeleccionada);
			refrescarTablaCitas();
		}else {
			limpiarTablas();
			refrescarTablaCitas();
		}
	}
	
	private void actualizarCitasPorProfesional(int idProfesional) {
		for (CitaDTO cita : citasDelDia) {
			int idCita = cita.getIdCita();
			
			List<ServicioTurnoDTO> serviciosDeCita = 
					this.sistema.getServicioTurnoByIdCita(idCita);
			
			for (ServicioTurnoDTO servTurno : serviciosDeCita) {
				if (idProfesional == servTurno.getIdProfesional())
					citasEnTabla.add(cita);
		}
		}
	}
		
		private void actualizarCitasPorServicio(int idServicio) {
		List<Integer> citasConServicioSeleccionado = 
						this.sistema.getCitasByIdServicio(idServicio);
				
		for (Integer idCita : citasConServicioSeleccionado) {
			CitaDTO cita = this.sistema.getCitaById(idCita);
			citasEnTabla.add(cita);
			}
	}
		
		private void actualizarCitasPorRangoHorario(LocalTime desde, LocalTime hasta) {
			List<CitaDTO> citasEntreRangoHorario = 
					this.sistema.getCitasPorRangoHorario(desde, hasta, fechaSeleccionada);
			
			for (CitaDTO cita : citasEntreRangoHorario) {
				citasEnTabla.add(cita);
				}
		}
		
	private void actualizarDiaSeleccionado(PropertyChangeEvent i) {
		actualizarDiaSeleccionado();
		log.info("Las citas del día: "+fechaSeleccionada+" son "+citasDelDia);
		log.info("Y las citas en tabla son: "+ citasEnTabla);
		log.info("__________________________________________________________");
	}
	
public void actualizarDiaSeleccionado() {
	limpiarTablas();
	this.citaSeleccionada = null;
	actualizarPanelCitaSeleccionada();
	setearFechaSeleccionadaEnCalendario();
	citasDelDia = obtenerCitasDelDia(getFechaSeleccionadaAsString());
	cargarListaConCitas();
	refrescarTablaCitas();
	habilitarBotonAgregar();
	}

	private static String getFechaSeleccionadaAsString() {
		int dia = fechaSeleccionada.getDayOfMonth();
		int mes = fechaSeleccionada.getMonthValue();
		int anio = fechaSeleccionada.getYear();
		
		String S_dia = (dia < 10) ? "0"+dia : Integer.toString(dia);
		String S_mes = (mes < 10) ? "0"+mes : Integer.toString(mes);
		String S_anio = Integer.toString(anio);
		
		return S_anio+S_mes+S_dia;
	}
	
	public void setearFechaSeleccionadaEnCalendario() {
		int dia = this.nvista.getCalendario().getDayChooser().getDay();
		int mes = this.nvista.getCalendario().getMonthChooser().getMonth()+1;
		int anio = this.nvista.getCalendario().getYearChooser().getYear();
		
		fechaSeleccionada = LocalDate.of(anio, mes, dia);
	}
	
	public void setearFechaSeleccionadaHoy() {
		fechaSeleccionada = LocalDate.now();
	}
	
	private void cargarListaConCitas(){
		citasEnTabla.clear();
		for (CitaDTO cita : citasDelDia) {
			citasEnTabla.add(cita);
		}
	}
	
	public void cargarServiciosDeCitas(List<ServicioTurnoDTO> servicios) {
		this.nvista.getDetalleDeCita().getModelServiciosAgregados().setRowCount(0); //Para vaciar la tabla
		this.nvista.getDetalleDeCita().getModelServiciosAgregados().setColumnCount(0);
		this.nvista.getDetalleDeCita().getModelServiciosAgregados().setColumnIdentifiers(this.nvista.getDetalleDeCita().getNombreColumnas());

		for (ServicioTurnoDTO s : servicios)
		{
			ServicioDTO serv = this.sistema.getServicioById(s.getIdServicio());
			ProfesionalDTO prof = this.sistema.getProfesionalById(s.getIdProfesional());
			
			String nombreServicio = serv.getNombre();
			LocalTime horaInicio = s.getHoraInicio();
			LocalTime horaFin = s.getHoraFin();
			String nombreProfesional = prof.getNombre()+" "+prof.getApellido();
			Object[] fila = {nombreServicio, horaInicio, horaFin, nombreProfesional};
			this.nvista.getDetalleDeCita().getModelServiciosAgregados().addRow(fila);
	}
}

	public void refrescarTablaCitas() {
		this.nvista.getModelCitas().setRowCount(0); //Para vaciar la tabla
		this.nvista.getModelCitas().setColumnCount(0);
		this.nvista.getModelCitas().setColumnIdentifiers(this.nvista.getNombreColumnas());

		for (CitaDTO cita : citasEnTabla)
		{
			ClienteDTO cliente = sistema.obtenerClienteById(cita.getIdCliente());
			
			if(cliente == null)
			{log.error("El cliente no esta registrado y devuelve NullPointerException");}
			
			String nombre = cliente.getNombre()+" "+cliente.getApellido();
			BigDecimal precioLocal = cita.getPrecioLocal();
			BigDecimal precioDolar = cita.getPrecioDolar();
			LocalTime HoraInicio = cita.getHoraInicio();
			LocalTime HoraFin = cita.getHoraFin();
			String estado = cita.getEstado();
			Object[] fila = {nombre, precioLocal, precioDolar, HoraInicio, HoraFin, estado};
			this.nvista.getModelCitas().addRow(fila);
		}
	}
	
	public void limpiarTablas() {
		this.citaSeleccionada = null;
		OperacionesCita(false);
		this.citasDelDia.clear();
		this.citasEnTabla.clear();
	}
	
	public List <CitaDTO> obtenerCitasDelDia(String dia) {
		return this.sistema.getCitasPorDia(dia);
	}
	
	public void habilitarBotonAgregar() {
		this.nvista.getBtn_Agregar().setEnabled(true);
		getPermisos();
	}
	
	public void actualizarCitaSeleccionada(ListSelectionEvent l) {
		OperacionesCita(false);
		int filaSeleccionada = this.nvista.getTablaCitas().getSelectedRow();
		
		if (filaSeleccionada != -1) {
			this.citaSeleccionada = this.citasEnTabla.get(filaSeleccionada);
			if (citaSeleccionada != null) {
				if(!citaSeleccionada.getEstado().equals("Cancelada") &&
				   !citaSeleccionada.getEstado().equals("Finalizada") &&
				   !citaSeleccionada.getEstado().equals("Vencida")) {
		
				OperacionesCita(true);
				}
				this.nvista.OcultarLblCitaSeleccionadaNull();
				this.nvista.mostrarDetalleCitas(citaSeleccionada);
				this.cargarServiciosDeCitas(this.sistema.getServicioTurnoByIdCita(citaSeleccionada.getIdCita()));
				this.nvista.getBtn_VerComprobante().setEnabled(true);
				this.nvista.getBtn_VerDetalle().setEnabled(true);
			}
			log.info("Id de la cita seleccionada es: "+citaSeleccionada.getIdCita());
			log.info("Hora de inicio de la cita seleccionada es: "+citaSeleccionada.getHoraInicio());
			log.info("Hora de Fin de la cita seleccionada es: "+citaSeleccionada.getHoraFin());
			log.info("__________________________________________________________");
		}
	}
	
	private void actualizarPanelCitaSeleccionada() {
		if (citaSeleccionada != null) {
			//cargar el componente de detalles
		}else {
			this.nvista.ocultarDetalleCitas();
			this.nvista.MostrarLblCitaSeleccionadaNull();
		}
	}
	
	public void OperacionesCita(boolean habilitar) {
		this.nvista.getBtn_Cancelar().setEnabled(habilitar);
		this.nvista.getBtn_Editar().setEnabled(habilitar);
		this.nvista.getBtn_Finalizar().setEnabled(habilitar);
		this.nvista.getBtn_VerComprobante().setEnabled(habilitar);
		this.nvista.getBtn_VerDetalle().setEnabled(habilitar);
	}
	
	public boolean validarFechaSeleccionada() {
		if (esDespuesDeHoy()) {
			if (esDomingo()) {
			JOptionPane.showMessageDialog(null, "No se pueden realizar reservas los Domingos!");
			return false;
			}else
				return true;
		}
		JOptionPane.showMessageDialog(null, "No puedes realizar una reserva para un dia que ya transcurrio!");
		return false;
	}
	
	public boolean esDomingo() {
		return fechaSeleccionada.getDayOfWeek().equals(DayOfWeek.SUNDAY);
	}
	
	
	public boolean esDespuesDeHoy() {
		LocalDate hoy = LocalDate.now();
		return fechaSeleccionada.isAfter(hoy) || fechaSeleccionada.isEqual(hoy);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	private void finalizarCita(ActionEvent l) {
		this.controladorCaja = ControladorCaja.getInstance(this.sistema, this);
		this.controladorCaja.cobrarCitaDesdeMenu(this.citaSeleccionada);
	}
	private void verComprobante(ActionEvent l) {
		ReporteComprobante comprobante = new ReporteComprobante(this.citaSeleccionada);
		comprobante.mostrar();
		
	}
}
