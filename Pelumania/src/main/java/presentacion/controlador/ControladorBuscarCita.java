package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.table.TableModel;

import dto.CitaDTO;
import modelo.Sistema;
import presentacion.vista.VentanaBuscarCita;
import presentacion.vista.VentanaCaja;

public class ControladorBuscarCita implements ActionListener{
	
	private VentanaBuscarCita ventanaBuscarCita;
	private Sistema sistema;
	private List<CitaDTO> listaCitas;
	private static ControladorBuscarCita INSTANCE;
	private static VentanaCaja ventanaCaja;
	private ControladorCaja controladorCaja;
	
	private ControladorBuscarCita(Sistema sistema, VentanaCaja ventanaCaja, ControladorCaja controladorCaja) {
		this.ventanaBuscarCita = VentanaBuscarCita.getInstance();
		this.ventanaCaja = ventanaCaja;
		this.ventanaBuscarCita.getBtnSeleccionarCita().addActionListener(p -> seleccionarCita(p));
		this.sistema = sistema;
		this.controladorCaja = controladorCaja;
	}

	public static ControladorBuscarCita getInstance(Sistema sistema, VentanaCaja ventanaCaja, ControladorCaja controladorCaja) {
		if ( INSTANCE == null) {
			INSTANCE = new ControladorBuscarCita(sistema, ventanaCaja, controladorCaja);
		}
		inicializarDatos();
		return INSTANCE;
	}
	private static void inicializarDatos() {

		List<CitaDTO> listaCitas = INSTANCE.sistema.obtenerCitasActivas();
		INSTANCE.ventanaBuscarCita.llenarTabla(listaCitas);
		INSTANCE.ventanaBuscarCita.mostrarVentana();
	} 
	
	private void seleccionarCita(ActionEvent p) {
		this.listaCitas = INSTANCE.sistema.obtenerCitas();
		int filaSeleccionada = this.ventanaBuscarCita.getTablaCitas().getSelectedRow();
		
		if (filaSeleccionada != -1) {
			CitaDTO citaSeleccionada = this.listaCitas.get(filaSeleccionada);
			if (citaSeleccionada != null) {
				String idCita = Integer.toString(citaSeleccionada.getIdCita());
				this.controladorCaja.setCitaSeleccionada(citaSeleccionada);
				this.controladorCaja.mostarDatosCita();
				this.ventanaBuscarCita.cerrar();
			} 
		} else {
			this.ventanaBuscarCita.mostrarErrorSinSeleccionar();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}

