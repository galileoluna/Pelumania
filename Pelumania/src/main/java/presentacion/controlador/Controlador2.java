package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;

import org.apache.log4j.Logger;

import dto.CitaDTO;
import dto.ClienteDTO;
import dto.SucursalDTO;
import dto.UsuarioDTO;
import modelo.Sistema;
import presentacion.vista.NuevaVista;

public class Controlador2 implements ActionListener{
	/*
	 * Variables Globales importantes 
	 */
	private Sistema sistema;
	private NuevaVista nvista;
	private UsuarioDTO usuario;
	
	private Logger log = Logger.getLogger(Controlador2.class);	
	
	private static LocalDate fechaSeleccionada;
	private CitaDTO citaSeleccionada;
	
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

	/*
	 * Arreglos que se utilizan en la vista
	 */
	
	private List<CitaDTO> citasDelDia;
	private List<CitaDTO> citasEnTabla;
	//No son lo mismo las citas del Dia, que las que estan en la tabla. Esta segunda es por los filtros
	
	public Controlador2 (NuevaVista nvista, Sistema sistema, UsuarioDTO usuario) {
		this.nvista = nvista;
		this.sistema = sistema;
		this.usuario=usuario;
		
		CargarDatosUsuarioYSurcursal();
		
		citasDelDia = new ArrayList<CitaDTO>();
		citasEnTabla = new ArrayList<CitaDTO>();
		
		setearFechaSeleccionadaHoy();
		RefrescarTablaCitas();
		
		this.nvista.getMntmGestionDeServicios().addActionListener(a->ventanaServicios(a));
		this.nvista.getMntmGestionDeProfesionales().addActionListener(b->ventanaProfesionales(b));
		this.nvista.getMntmGestionDeClientes().addActionListener(c -> ventanaClientes(c));
		this.nvista.getMntmGestionDePromociones().addActionListener(d -> ventanaPromociones(d));
//		this.nvista.getMntmVerPromocionesVigentes().addActionListener(e -> verPromocionesVigentes(e));
		this.nvista.getMntmGestionDeSucursales().addActionListener(f -> ventanaSucursales(f));
		this.nvista.getMntmUtilizarCaja().addActionListener(g -> ventanaCaja(g));
		this.nvista.getMntmConsultarCategorias().addActionListener(h -> ventanCategoriaMovimientoCaja(h));
		
		this.nvista.getCalendario().addPropertyChangeListener(i -> actualizarDiaSeleccionado(i));
		// AGREGARLE CONTROLADOR A LA TABLA PARA QUE AL ELEGIR UNA FILA SE HABILITEN LOS BOTONES
		
		this.nvista.getBtn_Agregar().addActionListener(k -> ventanaAgregarCita(k));
		
		this.nvista.getTablaCitas().getSelectionModel().addListSelectionListener(l -> actualizarCitaSeleccionada(l));

		log.info("Controlador inicializado! La fecha es: "+fechaSeleccionada);
	}

	private void CargarDatosUsuarioYSurcursal() {
		this.nvista.getLblUsuario().setText(usuario.getNombre()+" "+usuario.getApellido());
		this.nvista.getLblSucursal().setText(getSucursal(usuario.getIdSucursal()));
	}
	
	private String getSucursal(int idSucursal) {
		SucursalDTO sucursal= sistema.getSucursalById(idSucursal);
		return sucursal.getNombreSucursal();
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
	
	private void ventanaAgregarCita(ActionEvent k) {
		if (!validarFechaSeleccionada()) {
			JOptionPane.showMessageDialog(null, "No puedes cargar una cita para un dia que ya transcurrio!");
		}else {
			ControladorAgregarCita.setANIO(fechaSeleccionada.getYear());
			ControladorAgregarCita.setMES(fechaSeleccionada.getMonthValue());
			ControladorAgregarCita.setDIA(fechaSeleccionada.getDayOfMonth());
			this.controladorAgregarCita = ControladorAgregarCita.getInstance(sistema);
		}
	}
	
	private void actualizarDiaSeleccionado(PropertyChangeEvent i) {
		setearFechaSeleccionadaEnCalendario();
		citasDelDia = obtenerCitasDelDia(getFechaSeleccionadaAsString());
		cargarListaConCitas();
		RefrescarTablaCitas();
		habilitarBotonAgregar();
		
		log.info("Las citas del día: "+fechaSeleccionada+" son "+citasDelDia);
		log.info("Y las citas en tabla son: "+ citasEnTabla);
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
		for (CitaDTO cita : citasDelDia) {
			citasEnTabla.add(cita);
		}
	}

	private void RefrescarTablaCitas() {
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
	
	public List <CitaDTO> obtenerCitasDelDia(String dia) {
		return this.sistema.getCitasPorDia(dia);
	}
	
	public void habilitarBotonAgregar() {
		this.nvista.getBtn_Agregar().setEnabled(true);
	}
	
	public void actualizarCitaSeleccionada(ListSelectionEvent l) {
		int filaSeleccionada = this.nvista.getTablaCitas().getSelectedRow();

		citaSeleccionada = this.citasEnTabla.get(filaSeleccionada);
		if (citaSeleccionada != null) {
			this.nvista.getBtn_Cancelar().setEnabled(true);
			this.nvista.getBtn_Finalizar().setEnabled(true);
			this.nvista.getBtn_VerComprobante().setEnabled(true);
			this.nvista.getBtn_VerDetalle().setEnabled(true);
		}
		
		log.info("Id de la cita seleccionada es: "+citaSeleccionada.getIdCita());
		log.info("Hora de inicio de la cita seleccionada es: "+citaSeleccionada.getHoraInicio());
		log.info("Hora de Fin de la cita seleccionada es: "+citaSeleccionada.getHoraFin());
	}
	
	public boolean validarFechaSeleccionada() {
			LocalDate hoy = LocalDate.now();
			return fechaSeleccionada.isAfter(hoy) || fechaSeleccionada.isEqual(hoy);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
