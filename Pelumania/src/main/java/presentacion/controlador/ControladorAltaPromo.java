package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import dto.PromocionDTO;
import modelo.Sistema;
import presentacion.vista.VentanaAltaProfesional;
import presentacion.vista.VentanaAltaPromocion;

public class ControladorAltaPromo implements ActionListener{
	private VentanaAltaPromocion ventanaAltaPromo;
	private ControladorPromocion controlPromo;
	private Sistema sistema;
	private static ControladorAltaPromo INSTANCE;

	
	private ControladorAltaPromo(Sistema sistema) {
		this.ventanaAltaPromo = VentanaAltaPromocion.getInstance();
		
		this.ventanaAltaPromo.getBtnAgregar().addActionListener(l -> guardarPromocion(l));
		this.sistema = sistema;
	}
	
	public static ControladorAltaPromo getInstance(Sistema sistema) {
		if ( INSTANCE == null) {
			INSTANCE = new ControladorAltaPromo(sistema);
		}
		INSTANCE.ventanaAltaPromo.mostrarVentana();
		return INSTANCE;
	}
	
	private void guardarPromocion(ActionEvent l) {
		String desc=this.ventanaAltaPromo.getDescripcion().getText();
		java.util.Date utilDate =this.ventanaAltaPromo.getDateFechaInic().getDate();
		java.sql.Date fechaIn = new java.sql.Date(utilDate.getDate());
		java.util.Date utilDate2 =this.ventanaAltaPromo.getDateFechaFin().getDate();
		java.sql.Date fechaF = new java.sql.Date(utilDate2.getDate());
		Double descuento=(this.ventanaAltaPromo.getDescuento().getText().equals("")?-1:Double.parseDouble(this.ventanaAltaPromo.getDescuento().getText()));
		int puntos=(this.ventanaAltaPromo.getPuntos().getText().contentEquals("")?-1:Integer.parseInt(this.ventanaAltaPromo.getPuntos().getText()));
		String estado=this.ventanaAltaPromo.getEstado().getSelectedItem().toString();
		PromocionDTO promo= new PromocionDTO (0,desc,fechaIn,fechaF,descuento,puntos,estado);
		this.sistema.insertarPromocion(promo);
		this.ventanaAltaPromo.cerrar();
		this.controlPromo.getInstance(sistema);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
