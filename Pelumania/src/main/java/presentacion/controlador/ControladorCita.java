package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;

import org.apache.log4j.Logger;


import dto.ClienteDTO;
import dto.ProfesionalDTO;
import dto.PromocionDTO;
import dto.ServicioDTO;
import dto.ServicioTurnoDTO;
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
	private List<ServicioTurnoDTO> serviciosAgregados;
	
	private List<ServicioDTO> servicios_panel_servicios;
	private List<ProfesionalDTO> profesionales_panel_profesionales;
	
	private List<ServicioDTO> servicios_panel_profesionales;
	private List<ProfesionalDTO> profesionales_panel_servicios;
	
	private List<PromocionDTO> promociones;
	ServicioDTO servicioSeleccionado; 
	
	
	private static String errorHora;
	private static String errorServicio;
	
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
		
		this.ventanaCita.getBtnAgregarServicio().addActionListener(d -> agregarServicio(d));
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
		serviciosAgregados = new ArrayList<ServicioTurnoDTO>();
		servicios_panel_servicios = this.sistema.obtenerServicios();
		promociones = this.sistema.obtenerPrmociones();
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
			ControladorCita.errorHora = "Debes elegir un horario!";
			return false;
		}
		if (this.ventanaCita.getFechaCita().equals(LocalDate.now())) {
			if (horaElegida.isBefore(LocalTime.now())){
				ControladorCita.errorHora = "No puedes sacar un turno para un horario que ya pasó!";
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
	}
	
	public void mostrarPanelServicio(ActionEvent a) {
		this.ventanaCita.getRdBtnProfesional().setSelected(false);
		this.ventanaCita.getRdbtnPromocion().setSelected(false);
		if (this.ventanaCita.getRdBtnServicio().isSelected()) {
			actualizarPanelDinamico("Servicios");
			llenarDatosPanelServicio();
			this.ventanaCita.getPanelDinamicoServicios().getTablaServicios().getSelectionModel().addListSelectionListener(l -> actualizarServicioSeleccionado(l));
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
		log.info("Aun no guarda nada, solo imprime los datos de la cita:");
		System.out.println("***********************"
				+ "****** DATOS CITA *************"+
				"*********************************");
		System.out.println("Sucursal: " + this.ventanaCita.getSucursal());
		System.out.println("Fecha: "+this.ventanaCita.getFechaCita());
		System.out.println("Cliente: "+ this.ventanaCita.getCliente());
		System.out.println("Telefono: "+ this.ventanaCita.getTxtTelefono().getText());
		System.out.println("Mail: "+ this.ventanaCita.getTxtMail().getText());
		
		System.out.println("Inicio: "+ this.ventanaCita.getHoraInicio());
		imprimirServicios();
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
	
	
	/* ****************************************************************** */
	/* *********** METODOS PARA EL MANEJO DE LOS SERVICIOS ************** */
	/* ****************************************************************** */
	
	public void agregarServicio(ActionEvent d) {
		if (this.ventanaCita.getRdBtnServicio().isSelected()) {
			ProfesionalDTO profesional = (ProfesionalDTO) this.ventanaCita.getPanelDinamicoServicios().getJCBoxProfesionalesDeServicio().getSelectedItem();
			Integer idProfesional = (profesional == null) ? null : profesional.getIdProfesional();
			ServicioTurnoDTO serv = new ServicioTurnoDTO(servicioSeleccionado.getIdServicio(), idProfesional);
			if (validarAntesDeAgregarServicio(serv)) {
				serviciosAgregados.add(serv);
				actualizarServiciosAgregados();
				System.out.println("Los servicios son: "+serviciosAgregados);
	}else {
		mostrarErrorServicio();
		}
		}
		
		if (this.ventanaCita.getRdBtnProfesional().isSelected()) {
			ProfesionalDTO profesional = (ProfesionalDTO) this.ventanaCita.getPanelDinamicoProfesionales().getJCBoxProfesional().getSelectedItem();
			Integer idProfesional = (profesional == null) ? null : profesional.getIdProfesional();
			ServicioTurnoDTO serv = new ServicioTurnoDTO(servicioSeleccionado.getIdServicio(), idProfesional);
			if (validarAntesDeAgregarServicio(serv)) {
				serviciosAgregados.add(serv);
				actualizarServiciosAgregados();
			}else {
				mostrarErrorServicio();
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
			ControladorCita.errorServicio = "No has seleccionado ningun servicio!";
			return false;
		}
		if (this.ventanaCita.getHoraInicio() == null) {
			ControladorCita.errorServicio = "Debes elegir la hora de inicio del turno para agregar servicios!";
			return false;
		}
		
		if (servicio.getIdProfesional() == null) {
			ControladorCita.errorServicio = "Debes seleccionar un profesional para ese servicio!";
			return false;
		}
		
		if (serviciosAgregados.contains(servicio)) {
			ControladorCita.errorServicio = "Ese servicio ya fue agregado!";
			return false;
		}
		ProfesionalDTO profesional = this.sistema.getProfesionalById(servicio.getIdProfesional());
		if (!validarDisponibilidadProfesional()) {
			ControladorCita.errorServicio = "El profesional "+ profesional.getNombre()+" "+
					profesional.getApellido()+" no está disponible en ese horario!"+ 
					"Tiene una cita desde: "+"__:__"+"hasta: "+ "__:__";
//			return false;
		}
		
		return true;
		
	}
	
	private boolean validarDisponibilidadProfesional() {
		log.info("El metodo no esta implementado, validar un profesional da siempre false");
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
	}
	/* PANEL SERVICIOS*/
	
	public void cargarServiciosEnTabla(List<ServicioDTO> servicios) {
			this.ventanaCita.getPanelDinamicoServicios().getModelServicios().setRowCount(0); //Para vaciar la tabla
			this.ventanaCita.getPanelDinamicoServicios().getModelServicios().setColumnCount(0);
			this.ventanaCita.getPanelDinamicoServicios().getModelServicios().setColumnIdentifiers(this.ventanaCita.getPanelDinamicoServicios().getNombreColumnas());

			servicios = this.sistema.obtenerServicios();
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
			if(prof.getEstado().equals("Activo"))
				this.ventanaCita.getPanelDinamicoServicios().getJCBoxProfesionalesDeServicio().addItem(prof);
		}
	}
	
	/* PANEL PROFESIONALES */
	
	public void llenarDatosPanelProfesionales() {
		cargarProfesionales();
	}
	
	private void cargarProfesionales() {
		profesionales_panel_profesionales = this.sistema.obtenerProfesional();
		this.ventanaCita.getPanelDinamicoProfesionales().getJCBoxProfesional().removeAllItems();
		for (ProfesionalDTO prof : profesionales_panel_profesionales) {
			if(prof.getEstado().equals("Activo"))
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
			Date FechaInicio = p.getFechaInicio();
			Date FechaFin=p.getFechaFin();
			Double descuento=p.getDescuento();
			int puntos=p.getPuntos();
			String estado=p.getEstado();
			Object[] fila = {descripcion, FechaInicio,FechaFin,descuento,puntos,estado};
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
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
