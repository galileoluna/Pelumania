package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JOptionPane;

import dto.PromocionDTO;
import modelo.Sistema;
import presentacion.vista.VentanaModificarPromocion;

public class ControladorEditarPromo {
	private VentanaModificarPromocion ventanaEditarPromo;
	private ControladorPromocion controlPromo;
	private Sistema sistema;
	private static ControladorEditarPromo INSTANCE;
	
	private ControladorEditarPromo(Sistema sistema) {
		this.ventanaEditarPromo = VentanaModificarPromocion.getInstance();
		
		this.ventanaEditarPromo.getBtnAgregar().addActionListener(l -> editarPromocion(l));
		this.sistema = sistema;
	}
	
	public static ControladorEditarPromo getInstance(Sistema sistema, List<PromocionDTO> promocion) {
		if ( INSTANCE == null) {
			INSTANCE = new ControladorEditarPromo(sistema);
		}
		for (PromocionDTO p : promocion) {
			INSTANCE.ventanaEditarPromo.getDescripcion().setText(p.getDescripcion());
			INSTANCE.ventanaEditarPromo.getDateFechaInic().setDate(p.getFechaInicio());
			INSTANCE.ventanaEditarPromo.getDateFechaFin().setDate(p.getFechaFin());
			INSTANCE.ventanaEditarPromo.getDescuento().setText(p.getDescuento().toString());
			INSTANCE.ventanaEditarPromo.getPuntos().setText(p.getPuntos().toString());
		}
		INSTANCE.ventanaEditarPromo.mostrarVentana();
		return INSTANCE;
	}
	

	private void editarPromocion(ActionEvent l) {
		String desc=this.ventanaEditarPromo.getDescripcion().getText();
		java.util.Date utilDate =this.ventanaEditarPromo.getDateFechaInic().getDate();
		java.util.Date utilDate2 =this.ventanaEditarPromo.getDateFechaFin().getDate();
		Double descuento=(this.ventanaEditarPromo.getDescuento().getText().equals("")?null:Double.parseDouble(this.ventanaEditarPromo.getDescuento().getText()));
		Integer puntos=(this.ventanaEditarPromo.getPuntos().getText().equals("")?null:Integer.parseInt(this.ventanaEditarPromo.getPuntos().getText()));
		String estado=this.ventanaEditarPromo.getEstado().getSelectedItem().toString();
		if(validar(desc,utilDate,utilDate2,descuento,puntos,estado)) {
			java.sql.Date fechaIn = new java.sql.Date(utilDate.getDate());
			java.sql.Date fechaF = new java.sql.Date(utilDate2.getDate());
			PromocionDTO promo= new PromocionDTO (0,desc,fechaIn,fechaF,descuento,puntos,estado);
			this.sistema.editarPromocion(promo);
			this.ventanaEditarPromo.cerrar();
			this.controlPromo.getInstance(sistema);
		}else {
			JOptionPane.showMessageDialog(null, "Por favor ingrese todos los datos", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private boolean validar( String descripcion, java.util.Date utilDate, java.util.Date utilDate2, Double desc ,Integer puntos,String estado) {
		if(descripcion==null || descripcion.equals("") || utilDate==null || utilDate2==null || estado.equals("") || (desc==null && puntos==null)) {
			return false;
		}
		return true;
	}
}
