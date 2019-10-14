package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import dto.PromocionDTO;
import modelo.Sistema;
import presentacion.vista.VentanaPromocionesVigentes;
import presentacion.vista.Vista;


public class ControladorPromocionesVigentes implements ActionListener{
	private Sistema sistema;
	private static ControladorPromocionesVigentes INSTANCE;
	private List<PromocionDTO> promosEnTabla;
	private Vista vista;
	private VentanaPromocionesVigentes ventanaPromoVigente;
	
	private ControladorPromocionesVigentes(Sistema sistema,Vista vista) {
		this.ventanaPromoVigente = ventanaPromoVigente.getInstance();
		this.sistema=sistema;
		this.vista=vista;
	}

	public static ControladorPromocionesVigentes getInstance(Sistema sistema,Vista vista) {
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
