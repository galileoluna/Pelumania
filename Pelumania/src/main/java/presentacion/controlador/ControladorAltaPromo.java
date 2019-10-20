package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JOptionPane;

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
		boolean validarError=false;
		char[] auxDesc=this.ventanaAltaPromo.getDescuento().getText().toCharArray();
		for(int i=0;i<auxDesc.length;i++) {
			if(!Character.isDigit(auxDesc[i])) {
				JOptionPane.showMessageDialog(null, "Dato incorrecto en el descuento (poner solo numeros)", "Error", JOptionPane.ERROR_MESSAGE);
				validarError=true;}
		}
		char[] auxPuntos=this.ventanaAltaPromo.getPuntos().getText().toCharArray();
		for(int i=0;i<auxPuntos.length;i++) {
			if(Character.isDigit(auxPuntos[i])) {
				JOptionPane.showMessageDialog(null, "Dato incorrecto en los puntos (poner solo numeros)", "Error", JOptionPane.ERROR_MESSAGE);
				validarError=true;}
		}
		if(validarError==false) {
			String desc=this.ventanaAltaPromo.getDescripcion().getText();
			java.util.Date utilDate =this.ventanaAltaPromo.getDateFechaInic().getDate();
			System.out.println(utilDate);
			java.util.Date utilDate2 =this.ventanaAltaPromo.getDateFechaFin().getDate();
			Double descuento=(this.ventanaAltaPromo.getDescuento().getText().equals("")?null:Double.parseDouble(this.ventanaAltaPromo.getDescuento().getText()));
			Integer puntos=(this.ventanaAltaPromo.getPuntos().getText().equals("")?null:Integer.parseInt(this.ventanaAltaPromo.getPuntos().getText()));
			String estado=this.ventanaAltaPromo.getEstado().getSelectedItem().toString();
			if(validar(desc,utilDate,utilDate2,descuento,puntos,estado)) {
				java.sql.Date fechaIn =new java.sql.Date(utilDate.getYear(),utilDate.getMonth(),  utilDate.getDate()+1);
				java.sql.Date fechaF = new java.sql.Date(utilDate2.getYear(),utilDate2.getMonth(),  utilDate2.getDate()+1);
				PromocionDTO promo= new PromocionDTO (0,desc,fechaIn,fechaF,descuento,puntos,estado);
				this.sistema.insertarPromocion(promo);
				this.ventanaAltaPromo.cerrar();
				this.controlPromo.getInstance(sistema);
			}else {
				JOptionPane.showMessageDialog(null, "Por favor ingrese todos los datos", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private boolean validar( String descripcion, java.util.Date utilDate, java.util.Date utilDate2, Double desc ,Integer puntos,String estado) {
		if(descripcion==null || descripcion.equals("") || utilDate==null || utilDate2==null || estado.equals("") || (desc==null && puntos==null)) {
			return false;
		}
		return true;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
