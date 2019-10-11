package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import dto.CitaDTO;
import dto.ProfesionalDTO;
import dto.ServicioDTO;
import modelo.Sistema;
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
	


	private ControladorAgregarCita(Sistema sistema) {
		this.ventanaAgregarCita = VentanaAgregarCita.getInstance();
		//this.ventanaAgregarCita.getBtnAgregarCita().addActionListener(p -> guardarCita(p));
		this.ventanaAgregarCita.getBtnRegistrarCliente().addActionListener(q -> registrarCliente(q));
		this.ventanaAgregarCita.getBtnBuscarCliente().addActionListener(r -> buscarCliente(r));
		this.ventanaAgregarCita.getJCBoxProfesional().addActionListener(p -> seleccionarProfesional(p));
		this.sistema = sistema;
		prueba();
	}

	private static void inicializarDatos() {

		INSTANCE.ventanaAgregarCita.limpiarCampos();
		
		List<ServicioDTO> listaServicios = INSTANCE.sistema.obtenerServicios();
		List<ProfesionalDTO> listaProfesionales = INSTANCE.sistema.obtenerProfesional();

		INSTANCE.ventanaAgregarCita.getJCBoxProfesional().removeAllItems();
		INSTANCE.ventanaAgregarCita.cargarServicios(listaServicios);
		INSTANCE.ventanaAgregarCita.cargarProfesionales(listaProfesionales);
		INSTANCE.ventanaAgregarCita.cargarFecha(ANIO, MES, DIA);
		INSTANCE.ventanaAgregarCita.mostrarVentana();
	}

	public static ControladorAgregarCita getInstance(Sistema sistema) {
		if ( INSTANCE == null) {
			INSTANCE = new ControladorAgregarCita(sistema);
		}
		inicializarDatos();
		INSTANCE.ventanaAgregarCita.mostrarVentana();
		return INSTANCE;
	}

	public void buscarCliente(ActionEvent r) {
		ControladorBuscarCliente.getInstance(sistema, this.ventanaAgregarCita);
	}

	public void registrarCliente(ActionEvent q) {
		controladorCliente = ControladorCliente.getInstance(sistema);
	}
	
	public void prueba () {
	List<ServicioDTO> lista = new ArrayList<ServicioDTO>();
	lista.add(this.sistema.getServicioById(1));
	lista.add(this.sistema.getServicioById(2));
	lista.add(this.sistema.getServicioById(3));
	System.out.println(lista.toString());
	}
/*
	public void guardarCita(ActionEvent p) {
		//Levanto los datos de la ventanaCita
		Integer idcliente = this.ventanaAgregarCita.getIdCliente();
		if (idcliente == -1);
		
		//HARDCODEADO IDUSUARIO
		Integer idUsuario = -1;
		//---------------------
		String nombre = this.ventanaAgregarCita.getTxtNombre().getText();
		String apellido = this.ventanaAgregarCita.getTxtApellido().getText();
		String estado = "Activa";
		ProfesionalDTO Profesional = (ProfesionalDTO) this.ventanaAgregarCita.getJCBoxProfesional().getSelectedItem();
		int idProfesional = Profesional.getIdProfesional();
		ServicioDTO Servicio = (ServicioDTO) this.ventanaAgregarCita.getJCBoxServicio().getSelectedItem();
		int idServicio = Servicio.getIdServicio();
		String S_precioLocal = Servicio.getPrecioLocal().toString();
		String S_precioDolar = Servicio.getPrecioDolar().toString();
		
		Integer hora = (Integer )this.ventanaAgregarCita.getJCBoxHora().getSelectedItem();
		Integer minutos = (Integer) this.ventanaAgregarCita.getJCBoxMinutos().getSelectedItem();
		LocalTime HoraCitaInicio = LocalTime.of(hora, minutos);
		String S_HoraInicio = HoraCitaInicio.toString();
		//Obtengo la parte Hora y la parte MInutos de la duracion del servicio
		int horaDuracionServicio = Servicio.getDuracion().getHour();
		int minutosDuracionServicio = Servicio.getDuracion().getMinute();
		//Le sumo la parte de las horas
		LocalTime HoraCitaFinTemporal = HoraCitaInicio.plusHours(horaDuracionServicio);
		//Le sumo la parte de los minutos
		LocalTime HoraCitaFin = HoraCitaFinTemporal.plusMinutes(minutosDuracionServicio);
		
		String S_HoraFin = HoraCitaFin.toString();
		String S_fecha = this.ventanaAgregarCita.getFechaCita().toString();
		LocalDate fecha = this.ventanaAgregarCita.getFechaCita();
		//HARDCODEADO
		int idSucursal = 1; 
		/*
		System.out.println("Id Cliente " + idcliente + "\n" +
				"IdUsuario " + idUsuario + "\n" +
				"Nombre " + nombre + "\n" +
				"Apellido " + apellido + "\n" +
				"Estado " + estado + "\n" +
				"IdProf " + idProfesional+ "\n" +
				"IdServ " + idServicio + "\n" +
				"PrecioLocal" + S_precioLocal + "\n" +
				"PrecioDolar " + S_precioDolar + "\n" +
				"horaInicio " + S_HoraInicio + "\n" +
				"horafin " + S_HoraFin + "\n" +
				"Fecha " + S_fecha+ "\n" +
				"idSucursal " + idSucursal);
		
		CitaDTO nuevaCita = new CitaDTO(0, idUsuario, idcliente, nombre, apellido, estado, idProfesional, idServicio,
				Servicio.getPrecioLocal(), Servicio.getPrecioDolar(), HoraCitaInicio, HoraCitaFin, fecha,
				idSucursal);
		
		System.out.println(nuevaCita);
		
		//Diferencia si el cliente esta registrado o no.
		if (idcliente == -1) {
			if (this.sistema.agregarCitaSinCliente(nuevaCita)) {
				JOptionPane.showMessageDialog(null, "La cita se cargó correctamente");
				this.ventanaAgregarCita.cerrar();
			}else {
				JOptionPane.showMessageDialog(null, "No se pudo agregar la Cita");
			}
		}else{
			if (this.sistema.agregarCita(nuevaCita)) {
				JOptionPane.showMessageDialog(null, "La cita se cargó correctamente");
				this.ventanaAgregarCita.cerrar();
			}else {
				JOptionPane.showMessageDialog(null, "No se pudo agregar la Cita");
			}
		}
	}
	*/
	
	public void seleccionarProfesional(ActionEvent e) {
		ProfesionalDTO Profesional = (ProfesionalDTO) this.ventanaAgregarCita.getJCBoxProfesional().getSelectedItem();
		this.ventanaAgregarCita.getLblNombreProfesional().setText(Profesional.getNombre()+" "+Profesional.getApellido());
		int idProfesional = Profesional.getIdProfesional();
		
		List<ServicioDTO> serviciosDelProfesional = sistema.getServiciosDelProfesional(idProfesional);
		this.ventanaAgregarCita.cargarServicios(serviciosDelProfesional);
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
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
