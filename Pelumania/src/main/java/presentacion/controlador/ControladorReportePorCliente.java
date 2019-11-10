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
		INSTANCE.ventanaReportes.mostrar();
		return INSTANCE;
	}
	
	private void reportePorCliente(ActionEvent l) {
	SimpleDateFormat formato = new SimpleDateFormat("YYYY-MM-dd", Locale.getDefault());
		String desde = formato.format(ventanaReportes.getJdc_Desde().getDate());
		String hasta = formato.format(ventanaReportes.getJdc_Hasta().getDate());
		
		String desdeParaReporte=desde+" 00:00:01";
		String hastaParaReporte=hasta+" 11:59:59";
		
		ArrayList<MovimientoCajaDTO>cliente=(ArrayList<MovimientoCajaDTO>) sistema.obtenerMovimientosCajaIngresosCliente(desdeParaReporte,hastaParaReporte,this.ventanaReportes.getJcb_Cliente().getSelectedIndex()+1);
				
		ReportePorCliente reportePorCliente = new ReportePorCliente(cliente,desde,hasta);
		reportePorCliente.mostrar();
	}
	
	public void cargarCliente() {
		List<ClienteDTO> clientes = sistema.obtenerClientes();
		int cont=0;
		
		for (ClienteDTO cliente : clientes) {
			if(cont==0)cont++;
			else {
				this.ventanaReportes.getJcb_Cliente().addItem(cliente);
				cont++;
			}
		}
	}
}