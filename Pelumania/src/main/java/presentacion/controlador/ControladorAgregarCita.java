package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.util.List;

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
		this.ventanaAgregarCita.getBtnAgregarCita().addActionListener(p -> guardarCita(p));
		this.ventanaAgregarCita.getBtnRegistrarCliente().addActionListener(q -> registrarCliente(q));
		this.ventanaAgregarCita.getBtnBuscarCliente().addActionListener(r -> buscarCliente(r));
		this.sistema = sistema;
	}

	private static void inicializarDatos() {

		List<ServicioDTO> listaServicios = INSTANCE.sistema.obtenerServicios();
		List<ProfesionalDTO> listaProfesionales = INSTANCE.sistema.obtenerProfesional();

		INSTANCE.ventanaAgregarCita.getJCBoxServicio().removeAllItems();
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

	public void guardarCita(ActionEvent p) {
		int idcliente = this.ventanaAgregarCita.getIdCliente();
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
		LocalTime HoraCita = LocalTime.of(hora, minutos);
		String S_hora = HoraCita.toString();
		String S_fecha = this.ventanaAgregarCita.getFechaCita().toString(); 
		int idSucursal = -1; 
		
		System.out.println(idcliente + "\n" +
				nombre + "\n" +
				apellido + "\n" +
				estado + "\n" +
				idProfesional+ "\n" +
				idServicio + "\n" +
				S_precioLocal + "\n" +
				S_precioDolar + "\n" +
				S_hora + "\n" +
				S_fecha+ "\n" +
				idSucursal);
		/*
		CitaDTO nuevaCita = new CitaDTO();
		this.sistema.agregarCita(nuevaCita);
		*/
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
