package presentacion.controlador;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;

import dto.CitaDTO;
import dto.ProfesionalDTO;
import dto.ServicioDTO;
import dto.ServicioTurnoDTO;
import modelo.Sistema;
import presentacion.vista.VentanaEditarCita;

public class ControladorEditarCita {

	private VentanaEditarCita ventanaEditarCita;
	private Sistema sistema;
	private ControladorEditarCita controladorEditarCita;
	private static ControladorEditarCita INSTANCE;

	private ControladorEditarCita(Sistema sistema, CitaDTO citaAEditar) {
		this.ventanaEditarCita = VentanaEditarCita.getInstance(citaAEditar);
		this.sistema = sistema;
		List<ServicioTurnoDTO> serviciosDeCita = this.sistema.getByIdCita(citaAEditar.getIdCita());
		cargarServicios(serviciosDeCita);
	}

	public static ControladorEditarCita getInstance(Sistema sistema, CitaDTO citaAEditar) {
		if ( INSTANCE == null) {
			INSTANCE = new ControladorEditarCita(sistema, citaAEditar);
		}

		return INSTANCE;
	}

	public void cargarServicios(List<ServicioTurnoDTO> serviciosDeCita) {
		this.ventanaEditarCita.getCompCita().getModelServicios().setRowCount(0); //Para vaciar la tabla
		this.ventanaEditarCita.getCompCita().getModelServicios().setColumnCount(0);
		this.ventanaEditarCita.getCompCita().getModelServicios().setColumnIdentifiers(this.ventanaEditarCita.getCompCita().getNombreColumnas());

		for (ServicioTurnoDTO st : serviciosDeCita)
		{
			
			int idServ = st.getIdServicio();
			int idProf = st.getIdProfesional();
			
			ServicioDTO serv = this.sistema.getServicioById(idServ);
			ProfesionalDTO prof = this.sistema.getProfesionalById(idProf);
			
			String nombre = serv.getNombre();
			BigDecimal precioLocal = serv.getPrecioLocal();
			BigDecimal precioDolar = serv.getPrecioDolar();
			LocalTime duracion = serv.getDuracion();
			String nombreProf = prof.getNombre()+" "+prof.getApellido();
			Object[] fila = {nombre, precioLocal, precioDolar, duracion, nombreProf};
			this.ventanaEditarCita.getCompCita().getModelServicios().addRow(fila);
		}
	}
}
