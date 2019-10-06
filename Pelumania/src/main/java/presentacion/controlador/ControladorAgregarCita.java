package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	
	private static int idCliente;

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
		ventanaBuscarCliente = VentanaBuscarCliente.getInstance();
		ventanaBuscarCliente.mostrarVentana();

	}

	public void registrarCliente(ActionEvent q) {
		controladorCliente = ControladorCliente.getInstance(sistema);
	}

	public void guardarCita(ActionEvent p) {
		int idcliente = idCliente;
		String nombre = this.ventanaAgregarCita.getTxtNombre().getText();
		String apellido = this.ventanaAgregarCita.getTxtApellido().getText();
		String estado = "Activa";
		ProfesionalDTO Profesional = (ProfesionalDTO) this.ventanaAgregarCita.getJCBoxProfesional().getSelectedItem();
		int idProfesional = Profesional.getIdProfesional();
		ServicioDTO Servicio = (ServicioDTO) this.ventanaAgregarCita.getJCBoxServicio().getSelectedItem();
		int idServicio = Servicio.getIdServicio();
		String S_precioLocal = Servicio.getPrecioLocal().toString();
		String S_precioDolar = Servicio.getPrecioLocal().toString();
		String S_hora = ""; 
		String S_fecha = ""; 
		int idSucursal = -1; 
		
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

	public static int getIdCliente() {
		return idCliente;
	}

	public static void setIdCliente(int idCliente) {
		ControladorAgregarCita.idCliente = idCliente;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
