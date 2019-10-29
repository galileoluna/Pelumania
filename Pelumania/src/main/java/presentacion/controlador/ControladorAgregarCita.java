package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import dto.CitaDTO;
import dto.ClienteDTO;
import dto.ProfesionalDTO;
import dto.ServicioDTO;
import dto.ServicioTurnoDTO;
import dto.SucursalDTO;
import modelo.Sistema;
import presentacion.Reportes.ReporteComprobante;
import presentacion.vista.VentanaAgregarCita;
import presentacion.vista.VentanaBuscarCliente;
import presentacion.vista.VentanaCliente;

public class ControladorAgregarCita implements ActionListener{

	private VentanaAgregarCita ventanaAgregarCita;
	private Sistema sistema;
	private ControladorAgregarCita controladorCita;
	private VentanaBuscarCliente ventanaBuscarCliente;
	private ControladorCliente controladorCliente;
	private VentanaCliente ventanaCliente;
	private static ControladorAgregarCita INSTANCE;
	private static int ANIO;
	private static int MES;
	private static int DIA;
	private static List<ServicioTurnoDTO> serviciosTurnoAgregados;
	private CitaDTO citaParaEditar;
	private static String errorHora;
	private static String errorCliente;

	/**
	 * @wbp.parser.entryPoint
	 */
	private ControladorAgregarCita(Sistema sistema) {
		this.ventanaAgregarCita = VentanaAgregarCita.getInstance();
		this.ventanaAgregarCita.getBtnAgregarCita().addActionListener(p -> guardarCita(p));
		this.ventanaAgregarCita.getBtnRegistrarCliente().addActionListener(q -> registrarCliente(q));
		this.ventanaAgregarCita.getBtnBuscarCliente().addActionListener(r -> buscarCliente(r));
		this.ventanaAgregarCita.getJCBoxProfesional().addActionListener(p -> seleccionarProfesional(p));
		
		this.ventanaAgregarCita.getBtn_Cancelar().addActionListener(q -> cancelar(q));
		this.ventanaAgregarCita.getBtnAgregarServicio().addActionListener(w -> agregarServicio(w));
		this.ventanaAgregarCita.getBtnBorrarServicio().addActionListener(x -> borrarServicioAgregado(x));
		this.ventanaAgregarCita.getBtnEditar().addActionListener(l -> editarCita(l));
		
		this.ventanaAgregarCita.getJCBoxHora().addActionListener(x -> llenarProfesionales(x));
		this.ventanaAgregarCita.getJCBoxMinutos().addActionListener(x -> llenarProfesionales(x));
	
		this.ventanaAgregarCita.getJCBoxHora().addItemListener(l -> ActualizarInformacionServiciosAgregados());
		this.ventanaAgregarCita.getJCBoxMinutos().addItemListener(l -> ActualizarInformacionServiciosAgregados());
		
		this.ventanaAgregarCita.getBtnVerComprobante().addActionListener(l -> generarComprobante());
		
		//Instancio la lista de servicios vacía
		serviciosTurnoAgregados = new ArrayList<ServicioTurnoDTO>();
		this.sistema = sistema;

	}

	private void llenarProfesionales(ActionEvent x) {
		List<ProfesionalDTO> profesionalesEnHorarioSeleccionado = new ArrayList<ProfesionalDTO>();
		profesionalesEnHorarioSeleccionado.clear();
		
		if(this.ventanaAgregarCita.getJCBoxHora().getSelectedItem() != null &&
			this.ventanaAgregarCita.getJCBoxMinutos().getSelectedItem() != null) {
			
			LocalTime horaInicio = LocalTime.of( (Integer)this.ventanaAgregarCita.getJCBoxHora().getSelectedItem(), 
				(Integer) this.ventanaAgregarCita.getJCBoxMinutos().getSelectedItem());
			profesionalesEnHorarioSeleccionado = 
					this.sistema.getProfesionalByHorario(horaInicio, ventanaAgregarCita.parsearDiaDeLaSemana(ventanaAgregarCita.getFechaCita()));
		
		this.ventanaAgregarCita.cargarProfesionales(profesionalesEnHorarioSeleccionado);
		}
	}

	private void generarComprobante() {
		 ReporteComprobante reporteComprobante = new ReporteComprobante(citaParaEditar);
		 reporteComprobante.mostrar();
	}

	private static void inicializarDatos() {

		INSTANCE.ventanaAgregarCita.limpiarCampos();
		
		//los limpio porque tambien se usan en editar
		INSTANCE.citaParaEditar = null;
		INSTANCE.serviciosTurnoAgregados = new ArrayList<ServicioTurnoDTO>();
		
		List<ServicioDTO> listaServicios = INSTANCE.sistema.obtenerServicios();
		List<ProfesionalDTO> listaProfesionales = INSTANCE.sistema.obtenerProfesional();
		List<SucursalDTO> listaSucursales = INSTANCE.sistema.obtenerSucursales();

		INSTANCE.ventanaAgregarCita.mostrarBuscarRegistrarCliente();

		INSTANCE.ventanaAgregarCita.getJCBoxProfesional().removeAllItems();
		INSTANCE.ventanaAgregarCita.getJCBoxSucursales().removeAllItems();
		INSTANCE.ventanaAgregarCita.mostrarBotonesDeEditar(false);
		
		INSTANCE.ventanaAgregarCita.cargarServicios(listaServicios);
//		INSTANCE.ventanaAgregarCita.cargarProfesionales(listaProfesionales);
		INSTANCE.ventanaAgregarCita.cargarSucursales(listaSucursales);
		INSTANCE.ventanaAgregarCita.cargarFecha(ANIO, MES, DIA);
		INSTANCE.ventanaAgregarCita.mostrarVentana();
		
	}

	public static ControladorAgregarCita getInstance(Sistema sistema) {
		if ( INSTANCE == null) {
			INSTANCE = new ControladorAgregarCita(sistema);
		}
		INSTANCE.ventanaAgregarCita.limpiarCampos();
		inicializarDatos();
		return INSTANCE;
	}
	
	//CONSTRUCTOR PARA EDITAR UNA CITA
	//Recibe como parametro una cita 
	public static ControladorAgregarCita getInstance(Sistema sistema, CitaDTO citaParaEditar) {
		if ( INSTANCE == null) {
			INSTANCE = new ControladorAgregarCita(sistema);
		}
		INSTANCE.citaParaEditar = citaParaEditar;
		INSTANCE.ventanaAgregarCita.limpiarCampos();
		inicializarDatosEditar();
		return INSTANCE;
	}

	private static void inicializarDatosEditar() {
		INSTANCE.ventanaAgregarCita.limpiarCampos();
		
		INSTANCE.ventanaAgregarCita.crearBotonEditarFecha();
		
		INSTANCE.ventanaAgregarCita.getBtn_EditarFecha().addActionListener(r -> editarFecha(r));
		List<ServicioDTO> listaServicios = INSTANCE.sistema.obtenerServicios();
		List<ProfesionalDTO> listaProfesionales = INSTANCE.sistema.obtenerProfesional();
		List<SucursalDTO> listaSucursales = INSTANCE.sistema.obtenerSucursales();
		
		
		//fecha de la cita a editar
		INSTANCE.ventanaAgregarCita.cargarFecha(INSTANCE.citaParaEditar.getFecha());
		//servicios que tenia
		INSTANCE.serviciosTurnoAgregados = INSTANCE.sistema.getServicioTurnoByIdCita(INSTANCE.citaParaEditar.getIdCita());
		//ocultamos el boton registrar o buscar cliente
		INSTANCE.ventanaAgregarCita.mostrarBotonesDeEditar(true);
		//cargamos los servicios que ya tenia
		INSTANCE.actualizarServiciosAgregados(INSTANCE.serviciosTurnoAgregados);
		INSTANCE.cargarNombreCliente();
		INSTANCE.setearHorarioCita();
		INSTANCE.ActualizarInformacionServiciosAgregados();
		
		INSTANCE.ventanaAgregarCita.getJCBoxProfesional().removeAllItems();
		INSTANCE.ventanaAgregarCita.getJCBoxSucursales().removeAllItems();
		
		INSTANCE.ventanaAgregarCita.cargarServicios(listaServicios);
		INSTANCE.ventanaAgregarCita.cargarProfesionales(listaProfesionales);
		INSTANCE.ventanaAgregarCita.cargarSucursales(listaSucursales);
		INSTANCE.setearSucursalCitaEditar();
		
		System.out.println(INSTANCE.ventanaAgregarCita.getIdCliente());
		INSTANCE.ventanaAgregarCita.mostrarVentana();
	}

	private static void editarFecha(ActionEvent e) {
		INSTANCE.ventanaAgregarCita.crearCalendarioEditarFecha();
		INSTANCE.ventanaAgregarCita.getCalendario().addPropertyChangeListener(a -> cambiarFecha(a));
	}
	
	private static void cambiarFecha(PropertyChangeEvent a) {
		int dia = INSTANCE.ventanaAgregarCita.getCalendario().getDayChooser().getDay();
		int mes =INSTANCE.ventanaAgregarCita.getCalendario().getMonthChooser().getMonth()+1;
		
		String S_dia;
		if (dia<10)
			S_dia = "0"+Integer.toString(INSTANCE.ventanaAgregarCita.getCalendario().getDayChooser().getDay());
		else
			S_dia = Integer.toString(INSTANCE.ventanaAgregarCita.getCalendario().getDayChooser().getDay());
		
		String S_mes;
		if (mes<10)
			S_mes = "0"+Integer.toString(INSTANCE.ventanaAgregarCita.getCalendario().getMonthChooser().getMonth()+1);
		else
			S_mes = Integer.toString(INSTANCE.ventanaAgregarCita.getCalendario().getMonthChooser().getMonth()+1);
		
		String anio =Integer.toString(INSTANCE.ventanaAgregarCita.getCalendario().getYearChooser().getYear());
		int año =INSTANCE.ventanaAgregarCita.getCalendario().getYearChooser().getYear();
		
		INSTANCE.ventanaAgregarCita.cargarFecha(LocalDate.of(año, mes, dia));
		INSTANCE.ventanaAgregarCita.setFechaCita(LocalDate.of(año, mes, dia));

	}
	private void setearSucursalCitaEditar() {
		this.ventanaAgregarCita.getJCBoxSucursales().setSelectedIndex(this.citaParaEditar.getIdSucursal()-1);
	}

	private void setearHorarioCita() {
		this.ventanaAgregarCita.getJCBoxHora().setSelectedItem(citaParaEditar.getHoraInicio().getHour());
		this.ventanaAgregarCita.getJCBoxMinutos().setSelectedItem(citaParaEditar.getHoraInicio().getMinute());
	}



	private void cargarNombreCliente() {
			this.ventanaAgregarCita.setIdCliente(this.citaParaEditar.getIdCliente());
			this.ventanaAgregarCita.getTxtNombre().setText(this.citaParaEditar.getNombre());
			this.ventanaAgregarCita.getTxtNombre().setEditable(false);
			this.ventanaAgregarCita.getTxtApellido().setText(this.citaParaEditar.getApellido());
			this.ventanaAgregarCita.getTxtApellido().setEditable(false);
	}

	public void buscarCliente(ActionEvent r) {
//		ControladorBuscarCliente.getInstance(sistema, this.ventanaAgregarCita);
	}

	public void registrarCliente(ActionEvent q) {
		controladorCliente = ControladorCliente.getInstance(sistema);
	}

	public void guardarCita(ActionEvent p) {
		if(!validarCliente())
			mostrarErrorClienteInvalido();
		else {
		
		if(serviciosTurnoAgregados.isEmpty())
		{
			this.ventanaAgregarCita.mostrarErrorSinServicios();
		}else {
		
		//Levanto los datos de la ventanaCita
		Integer idcliente = this.ventanaAgregarCita.getIdCliente();
		//HARDCODEADO IDUSUARIO
		Integer idUsuario = -1;
		//---------------------
		String nombre = this.ventanaAgregarCita.getTxtNombre().getText();
		String apellido = this.ventanaAgregarCita.getTxtApellido().getText();
		String estado = "Activa";

		Integer hora = (Integer )this.ventanaAgregarCita.getJCBoxHora().getSelectedItem();
		Integer minutos = (Integer) this.ventanaAgregarCita.getJCBoxMinutos().getSelectedItem();
		LocalTime HoraCitaInicio = LocalTime.of(hora, minutos);
		
		String S_HoraInicio = HoraCitaInicio.toString();
		String S_HoraFin = this.ventanaAgregarCita.getLblHoraTotal().getText();
		LocalTime HoraCitaFin = this.ventanaAgregarCita.getHoraFin();
		
		String S_fecha = this.ventanaAgregarCita.getFechaCita().toString();
		LocalDate fecha = this.ventanaAgregarCita.getFechaCita();
		
		SucursalDTO sucursalSeleccionada = (SucursalDTO) this.ventanaAgregarCita.getJCBoxSucursales().getSelectedItem();
		int idSucursal = sucursalSeleccionada.getIdSucursal();
		
		Integer idCitaAgregada;
		
		System.out.println("Id Cliente " + idcliente + "\n" +
				"IdUsuario " + idUsuario + "\n" +
				"Nombre " + nombre + "\n" +
				"Apellido " + apellido + "\n" +
				"Estado " + estado + "\n" +
				"PrecioLocal" + this.ventanaAgregarCita.getTotal$()+ "\n" +
				"PrecioDolar " + this.ventanaAgregarCita.getTotalUSD() + "\n" +
				"horaInicio " + S_HoraInicio + "\n" +
				"horafin " + S_HoraFin + "\n" +
				"Fecha " + S_fecha+ "\n" +
				"idSucursal " + idSucursal);
		
		CitaDTO nuevaCita = new CitaDTO(0, idUsuario, idcliente, nombre, apellido, estado,
				this.ventanaAgregarCita.getTotal$(), this.ventanaAgregarCita.getTotalUSD(), 
				HoraCitaInicio, HoraCitaFin, fecha,	idSucursal);
		
		calcularHorariosServicios();
		//Diferencia si el cliente esta registrado o no.
		if (idcliente == -1) {
			if (this.sistema.agregarCitaSinCliente(nuevaCita)) {
				CitaDTO CitaAgregada = this.sistema.getCitaMax();
				idCitaAgregada = CitaAgregada.getIdCita();
				
				
				for (ServicioTurnoDTO st : serviciosTurnoAgregados) 
				{
				st.setIdCita(idCitaAgregada);
				this.sistema.insertServicioTurno(st);
				}
				
				this.ventanaAgregarCita.mostrarExitoCargarCita();
				this.ventanaAgregarCita.cerrar();
			}else {
				this.ventanaAgregarCita.mostrarErrorCargarCita();
			}
		}else{
			if (this.sistema.agregarCita(nuevaCita)) {
				CitaDTO CitaAgregada = this.sistema.getCitaMax();
				idCitaAgregada = CitaAgregada.getIdCita();
				ClienteDTO cliente = this.sistema.obtenerClienteById(idcliente);
				
				
				for (ServicioTurnoDTO st : serviciosTurnoAgregados) 
				{
				st.setIdCita(idCitaAgregada);
				this.sistema.insertServicioTurno(st);
				}
				calcularHorariosServicios();
				this.ventanaAgregarCita.mostrarExitoCargarCita();
				this.ventanaAgregarCita.cerrar();
				
				//una vez que se hizo todo bien mandamos el mail
//				MailService.enviar(this.sistema, CitaAgregada, cliente);
				
			}else {
				JOptionPane.showMessageDialog(null, "No se pudo agregar la Cita");
			}
		}
	}
		}
	}

	
	private void editarCita(ActionEvent l) {
		if(serviciosTurnoAgregados.isEmpty()){
			this.ventanaAgregarCita.mostrarErrorSinServicios();
		
		} else {
			
			//se le cambia el estado a una cita al editarla?
			String estado = "Activa";

			Integer hora = (Integer )this.ventanaAgregarCita.getJCBoxHora().getSelectedItem();
			Integer minutos = (Integer) this.ventanaAgregarCita.getJCBoxMinutos().getSelectedItem();
			LocalTime HoraCitaInicio = LocalTime.of(hora, minutos);
			
			validarHora();
			LocalTime HoraCitaFin = this.ventanaAgregarCita.getHoraFin();
			
			//falta permitir cambiar el dia
			LocalDate fecha = this.citaParaEditar.getFecha();
			
			SucursalDTO sucursalSeleccionada = (SucursalDTO) this.ventanaAgregarCita.getJCBoxSucursales().getSelectedItem();
			int idSucursal = sucursalSeleccionada.getIdSucursal();
			
			//modifico el dto antes de mandarlo a la bdd
			citaParaEditar.setIdCliente(this.ventanaAgregarCita.getIdCliente());
			citaParaEditar.setDiaTurno(Date.valueOf(fecha));
			citaParaEditar.setIdSucursal(idSucursal);
			citaParaEditar.setPrecioLocal(this.ventanaAgregarCita.getTotal$());
			citaParaEditar.setPrecioDolar(this.ventanaAgregarCita.getTotalUSD());
			citaParaEditar.setFecha(this.ventanaAgregarCita.getFechaCita());
			System.out.println(citaParaEditar.getFecha());
			citaParaEditar.setHoraInicio(HoraCitaInicio);
			citaParaEditar.setHoraFin(HoraCitaFin);
			
			calcularHorariosServicios();

			if (this.sistema.editarCita(citaParaEditar)) {
					
				//borramos todos los servicios que tenia la cita vieja
				for (ServicioTurnoDTO servicioViejo : this.sistema.getServicioTurnoByIdCita(citaParaEditar.getIdCita())) {
					this.sistema.deleteServicioTurno(servicioViejo);
				}
				
				for (ServicioTurnoDTO st : serviciosTurnoAgregados) {
					st.setIdCita(citaParaEditar.getIdCita());
					this.sistema.insertServicioTurno(st);
	
				}
					
				JOptionPane.showMessageDialog(null, "La cita se editó correctamente");
				this.ventanaAgregarCita.cerrar();
					
			} else {
					JOptionPane.showMessageDialog(null, "No se pudo editar la Cita");
				}
			}
	}

	public void agregarServicio(ActionEvent w) {
		ProfesionalDTO prof = (ProfesionalDTO) this.ventanaAgregarCita.getJCBoxProfesional().getSelectedItem();
		List<ServicioDTO> serviciosDelProfesional = sistema.getServiciosDelProfesional(prof.getIdProfesional());
		int[] filasSeleccionadas = ventanaAgregarCita.getTablaServicios().getSelectedRows();
	       
    	for (int fila : filasSeleccionadas)
    	{
        	if(serviciosDelProfesional.get(fila)!=null) {
        		ServicioDTO servicioSeleccionado = serviciosDelProfesional.get(fila);
        		
        		if (existeServicio(servicioSeleccionado)) {
        			JOptionPane.showMessageDialog(null, "No puedes agregar 2 veces el mismo servicio!");
        		}else {
        			if (validarHora()) {
        				ServicioTurnoDTO servTurno = new ServicioTurnoDTO(servicioSeleccionado.getIdServicio(),
        						prof.getIdProfesional());
        				this.serviciosTurnoAgregados.add(servTurno);
        				ActualizarInformacionServiciosAgregados();
        			}else {
        				mostrarErrorHoraInvalida();
        		}
	}
        	}
  	}
	}
	
	public void borrarServicioAgregado(ActionEvent x) {
		int[] filasSeleccionadas = ventanaAgregarCita.getTablaServiciosAgregados().getSelectedRows();
	       
    	for (int fila : filasSeleccionadas)
    	{
        	if(serviciosTurnoAgregados.get(fila)!=null) {
        		this.sistema.getServicioById(serviciosTurnoAgregados.get(fila).getIdServicio());
        		ServicioTurnoDTO servicioSeleccionado = serviciosTurnoAgregados.get(fila);
        		serviciosTurnoAgregados.remove(servicioSeleccionado);
        		this.actualizarServiciosAgregados(serviciosTurnoAgregados);
        		this.ActualizarInformacionServiciosAgregados();
        	}
    	}
	}
	
	
	public void calcularHorariosServicios() {
		LocalTime horaInicio = this.ventanaAgregarCita.getHoraInicio();
		for (ServicioTurnoDTO st : serviciosTurnoAgregados) {
			ServicioDTO servicio = sistema.getServicioById(st.getIdServicio());
			st.setHoraInicio(horaInicio);
			
			LocalTime horaFinalizacionServicio = horaInicio.plusHours(servicio.getDuracion().getHour());
			horaFinalizacionServicio = horaFinalizacionServicio.plusMinutes(servicio.getDuracion().getMinute());
			
			st.setHoraFin(horaFinalizacionServicio);
			
			horaInicio = horaFinalizacionServicio;
			
		}
	}
	
	/*
	 * Se encarga de cargar en la tabla los servicios asociados al profesional que se selecciona
	 * en el JComboBox profesional*/
	
	public void seleccionarProfesional(ActionEvent e) {
		if (this.ventanaAgregarCita.getJCBoxProfesional().getSelectedIndex() != -1) {
		ProfesionalDTO Profesional = (ProfesionalDTO) this.ventanaAgregarCita.getJCBoxProfesional().getSelectedItem();
		this.ventanaAgregarCita.getLblNombreProfesional().setText(Profesional.getNombre()+" "+Profesional.getApellido());
		int idProfesional = Profesional.getIdProfesional();
		
		List<ServicioDTO> serviciosDelProfesional = sistema.getServiciosDelProfesional(idProfesional);
		this.ventanaAgregarCita.cargarServicios(serviciosDelProfesional);
		}
	}

	public void cancelar(ActionEvent q) {
		this.ventanaAgregarCita.dispose();
	}
	//Chequea si el servicio a agregar a la cita ya esta agregado o no.
	public boolean existeServicio(ServicioDTO servicio_a_chequear) {
		for (ServicioTurnoDTO st : serviciosTurnoAgregados) {
			
			if (st.getIdServicio() == servicio_a_chequear.getIdServicio())
				return true;
		}
		return false;
	}
	
	/*
	 * Metodo que recorre todos los servicios agregados y devuelve el total en $
	 * por todos los servicios*/
	
	public BigDecimal calcularTotal() {
		BigDecimal total = BigDecimal.valueOf(0);
		for (ServicioTurnoDTO st : serviciosTurnoAgregados) {
			Integer idServicio = st.getIdServicio();
			ServicioDTO servicio = this.sistema.getServicioById(idServicio);
			total = total.add(servicio.getPrecioLocal());
		}
		this.ventanaAgregarCita.setTotal$(total);
		return total;
	}
	
	/*
	 * Metodo que recorre todos los servicios agregados y devuelve el total en Dolares
	 * por todos los servicios*/

	public BigDecimal calcularTotalDolar() {
		BigDecimal total = BigDecimal.valueOf(0);
		for (ServicioTurnoDTO st : serviciosTurnoAgregados) {
			Integer idServicio = st.getIdServicio();
			ServicioDTO servicio = this.sistema.getServicioById(idServicio);
			total = total.add(servicio.getPrecioDolar());
		}
		this.ventanaAgregarCita.setTotalUSD(total);
		System.out.println(total);
		return total;
	}
	
	/*
	 * Metodo que recorre todos los servicios agregados y le suma a la hora inicial del turno
	 * la duracion de cada uno de ellos.*/
	public LocalTime CalcularTotalTiempo() {
		int horaInicial = (Integer) this.ventanaAgregarCita.getJCBoxHora().getSelectedItem();
		int minutoInicial = (Integer) this.ventanaAgregarCita.getJCBoxMinutos().getSelectedItem();
	
		LocalTime total = LocalTime.of(horaInicial, minutoInicial);
		for (ServicioTurnoDTO st : serviciosTurnoAgregados) {
			Integer idServicio = st.getIdServicio();
			ServicioDTO servicio = this.sistema.getServicioById(idServicio);
			total = total.plusHours(servicio.getDuracion().getHour());
			total = total.plusMinutes(servicio.getDuracion().getMinute());
		}
		this.ventanaAgregarCita.setHoraFin(total);
		return total;
	}
	
	/*
	 * Metodo que se ocupa de validar si la hora elegida en los JComboBox, si no se eligio nada
	 * devuelve false. 
	 * */
	public boolean validarHora() {
		//Si no selecciono nada en los JComBox de Hora devuelvo false
		if (this.ventanaAgregarCita.getJCBoxHora().getSelectedItem() == null 
				|| this.ventanaAgregarCita.getJCBoxMinutos().getSelectedItem() == null)
		{
			this.setErrorHora("CampoNulo");
			return false;
		}
		
		if (this.ventanaAgregarCita.getFechaCita().equals(LocalDate.now()))
			{
			Integer horaSeleccionada = (Integer) this.ventanaAgregarCita.getJCBoxHora().getSelectedItem();
			Integer minutoSeleccionado = (Integer) this.ventanaAgregarCita.getJCBoxMinutos().getSelectedItem();
				LocalTime horaCita = LocalTime.of(horaSeleccionada, minutoSeleccionado);
				if (LocalTime.now().isAfter(horaCita)) 
				{
					this.setErrorHora("HoraAtrasada");
					return false;
				}
			}
		LocalTime horaInicio = LocalTime.of((Integer)this.ventanaAgregarCita.getJCBoxHora().getSelectedItem(),
							   (Integer)this.ventanaAgregarCita.getJCBoxMinutos().getSelectedItem());
		this.ventanaAgregarCita.setHoraInicio(horaInicio);
		return true;
		
	}
	
	/* Metodo que se ocupa de actualizar en la ventana la tabla de los servicios agregados,
	 * como asi tambien el precio y la duracion total de la cita 
	 * */
	public void ActualizarInformacionServiciosAgregados() {
		//tienen que haberse cargado los horarios
		if (this.ventanaAgregarCita.getJCBoxHora().getSelectedItem() != null && this.ventanaAgregarCita.getJCBoxMinutos().getSelectedItem() != null ) {
			
			this.ventanaAgregarCita.getLblHoraTotal().setText(CalcularTotalTiempo().toString());
			this.ventanaAgregarCita.getLblTotal$().setText(calcularTotal().toString());
			this.ventanaAgregarCita.getLblTotalUSD().setText(calcularTotalDolar().toString());
	    	this.actualizarServiciosAgregados(serviciosTurnoAgregados);
		}
	}
	
	/*
	 * Metodo que actualiza la tabla de serviciosAgregados*/
	public void actualizarServiciosAgregados(List<ServicioTurnoDTO> serviciosTurnoAgregados)
	{
	this.ventanaAgregarCita.getModelServiciosAgregados().setRowCount(0); //Para vaciar la tabla
	this.ventanaAgregarCita.getModelServiciosAgregados().setColumnCount(0);
	this.ventanaAgregarCita.getModelServiciosAgregados().setColumnIdentifiers(this.ventanaAgregarCita.getNombreColumnasAgregadas());

	for (ServicioTurnoDTO st : serviciosTurnoAgregados) {
		ServicioDTO serv = this.sistema.getServicioById(st.getIdServicio());
		ProfesionalDTO prof = this.sistema.getProfesionalById(st.getIdProfesional());
		
		String nombreServicio = serv.getNombre();
		String nombreProfesional = prof.getNombre()+" "+prof.getApellido();
		
		Object[] fila = {nombreServicio, nombreProfesional};
		this.ventanaAgregarCita.getModelServiciosAgregados().addRow(fila);
	}
}
	
	public void mostrarErrorHoraInvalida() {
		switch (this.errorHora) {
		case "CampoNulo":
			JOptionPane.showMessageDialog(null, "Debes ingresar un horario antes de elegir un servicio!");
			break;
		case "HoraAtrasada":
			JOptionPane.showMessageDialog(null, "No puedes elegir un horario que ya paso.");
			break;
		}
	}
	
	public boolean validarCliente() {
		if ((this.ventanaAgregarCita.getTxtNombre().getText().isEmpty()) &&
			(this.ventanaAgregarCita.getTxtApellido().getText().isEmpty()))
		{
			this.errorCliente = "CamposClienteNulos";
			return false;
		}else {
		if (this.ventanaAgregarCita.getTxtNombre().getText().isEmpty()) {
			this.errorCliente = "CampoNombreNulo";
			return false;
		}
		if (this.ventanaAgregarCita.getTxtApellido().getText().isEmpty()) {
			this.errorCliente = "CampoApellidoNulo";
			return false;
		}
		return true;
	}
	}
	public void mostrarErrorClienteInvalido() {
		switch (this.errorCliente) {
		case "CamposClienteNulos":
			JOptionPane.showMessageDialog(null, "Debes completar ambos campos para un cliente no registrado.");
			break;
		case "CampoNombreNulo":
			JOptionPane.showMessageDialog(null, "Debe ingresar un nombre para el cliente antes de continuar.");
			break;
		case "CampoApellidoNulo":
			JOptionPane.showMessageDialog(null, "Debe ingresar un apellido para el cliente antes de continuar.");
			break;
		}
	}
	
	public boolean comprobarDisponibilidadHorarios() {
		return false;
	}
	
	public static int getANIO() {
		return ANIO;
	}

	public static void setANIO(int aNIO) {
		ANIO = aNIO;
	}

	public static int getMES() {
		return MES;
	}

	public static void setMES(int mES) {
		MES = mES;
	}

	public static int getDIA() {
		return DIA;
	}

	public static void setDIA(int dIA) {
		DIA = dIA;
	}

	public static String getErrorHora() {
		return errorHora;
	}

	public static void setErrorHora(String errorHora) {
		ControladorAgregarCita.errorHora = errorHora;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
