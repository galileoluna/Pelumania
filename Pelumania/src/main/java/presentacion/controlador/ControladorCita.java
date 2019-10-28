package presentacion.controlador;

import java.time.LocalDate;
import java.util.List;

import dto.SucursalDTO;
import modelo.Sistema;
import presentacion.vista.nuevaVentanaCita;

public class ControladorCita {
	private Sistema sistema;
	private nuevaVentanaCita ventanaCita;
	private static ControladorCita INSTANCE;
	
	private LocalDate fechaCita;
	private SucursalDTO sucursal; 
	
	private List<SucursalDTO> listaSucursales;
	
	public ControladorCita(Sistema s) {
		this.ventanaCita = nuevaVentanaCita.getInstance();
		this.sistema = s;
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
	
}
