package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import dto.PromocionDTO;
import modelo.Sistema;
import presentacion.vista.NuevaVista;
import presentacion.vista.VentanaPromocionesVigentes;

public class ControladorPromocionesVigentes implements ActionListener{
	private Sistema sistema;
	private static ControladorPromocionesVigentes INSTANCE;
	private List<PromocionDTO> promosEnTabla;
	private NuevaVista vista;
	private VentanaPromocionesVigentes ventanaPromoVigente;
	
	private ControladorPromocionesVigentes(Sistema sistema,NuevaVista vista) {
		this.ventanaPromoVigente = ventanaPromoVigente.getInstance();
		this.sistema=sistema;
		this.vista=vista;
	}

	public static ControladorPromocionesVigentes getInstance(Sistema sistema,NuevaVista vista) {
		if ( INSTANCE == null) {
			INSTANCE = new ControladorPromocionesVigentes(sistema,vista);
		}

		java.util.Date utilDate =INSTANCE.vista.getCalendario().getDate();
		java.sql.Date hoy=new java.sql.Date(utilDate.getYear(),utilDate.getMonth(),utilDate.getDate());
		INSTANCE.ventanaPromoVigente.llenarTabla(INSTANCE.sistema.obtenerPromoVigente(hoy, hoy));
		INSTANCE.ventanaPromoVigente.show();
		return INSTANCE;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
