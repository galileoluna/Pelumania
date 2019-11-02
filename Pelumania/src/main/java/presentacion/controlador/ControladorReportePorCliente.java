package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import dto.ClienteDTO;
import dto.MovimientoCajaDTO;
import modelo.Sistema;
import presentacion.Reportes.ReportePorCliente;
import presentacion.vista.VentanaReportePorCliente;

public class ControladorReportePorCliente {
	private VentanaReportePorCliente ventanaReportes;
	private Sistema sistema;
	private static ControladorReportePorCliente INSTANCE;

	private ControladorReportePorCliente(Sistema sistema) {
		this.ventanaReportes = VentanaReportePorCliente.getInstance();
		this.sistema = sistema;
		cargarCliente();
		
		this.ventanaReportes.getBtnGenerarReporte().addActionListener(l -> reportePorCliente(l));

	}

	public static ControladorReportePorCliente getInstance(Sistema sistema) {
		if ( INSTANCE == null) {
			INSTANCE = new ControladorReportePorCliente(sistema);
		}
		return INSTANCE;
	}
	
	private void reportePorCliente(ActionEvent l) {
		SimpleDateFormat formato = new SimpleDateFormat("YYYY-MM-dd", Locale.getDefault());
		String desde = formato.format(ventanaReportes.getJdc_Desde().getDate());
		String hasta = formato.format(ventanaReportes.getJdc_Hasta().getDate());
		
		desde+=" 00:00:01";
		hasta+=" 11:59:59";
		
		ArrayList<MovimientoCajaDTO>cliente=(ArrayList<MovimientoCajaDTO>) sistema.obtenerMovimientosCajaIngresosCliente(desde,hasta,this.ventanaReportes.getJcb_Cliente().getSelectedIndex()+1);
		
		//System.out.println(cliente.get(0).getIdCliente());
		
		ReportePorCliente reportePorCliente = new ReportePorCliente(cliente);
		reportePorCliente.mostrar();
	}
	
	public void cargarCliente() {
		List<ClienteDTO> clientes = sistema.obtenerClientes();
		
		for (ClienteDTO cliente : clientes) {
			this.ventanaReportes.getJcb_Cliente().addItem(cliente);
		}
	}
}