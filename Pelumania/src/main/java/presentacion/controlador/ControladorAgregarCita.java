package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import dto.ProfesionalDTO;
import dto.ServicioDTO;
import modelo.Sistema;
import presentacion.vista.VentanaAgregarCita;
import presentacion.vista.VentanaBuscarCliente;

public class ControladorAgregarCita implements ActionListener{

	private VentanaAgregarCita ventanaAgregarCita;
	private Sistema sistema;
	private ControladorAgregarCita controladorCita;
	private VentanaBuscarCliente ventanaBuscarCliente;
	private static ControladorAgregarCita INSTANCE;
	private static int ANIO;
	private static int MES;
	private static int DIA;

	private ControladorAgregarCita(Sistema sistema) {
		this.ventanaAgregarCita = VentanaAgregarCita.getInstance();
		//this.ventanaAgregarCita.getBtnAgregarCita().addActionListener(p -> guardarServicio(p));
		this.ventanaAgregarCita.getBtnBuscarCliente().addActionListener(r -> buscarCliente(r));
		this.sistema = sistema;
	}

	private static void inicializarDatos() {

		List<ServicioDTO> listaServicios = INSTANCE.sistema.obtenerServicios();
		List<ProfesionalDTO> listaProfesionales = INSTANCE.sistema.obtenerProfesional();

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
