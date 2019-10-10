package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import dto.ServicioDTO;
import modelo.Sistema;
import presentacion.vista.VentanaServicioPromocion;

public class ControladorServicioPromocion {
	private Sistema sistema;
	private static ControladorServicioPromocion INSTANCE;
	private VentanaServicioPromocion ventanaServPromo;
	private List<String> servCombo;
	private List<String> servEnTabla;
	private static List<Integer> idServEnTabla=new ArrayList<Integer>();
	private int idPromocion;
	private String descripcionPromo;
	
	private ControladorServicioPromocion(Sistema sistema) {
		this.ventanaServPromo = ventanaServPromo.getInstance();
		this.ventanaServPromo.getBtnAgregar().addActionListener(l -> agregarServ(l));
		this.ventanaServPromo.getBtnBorrar().addActionListener(k -> borrarServ(k));
		this.sistema=sistema;
	}

	public static ControladorServicioPromocion getInstance(Sistema sistema, String nombre, int idPromo) {
		if ( INSTANCE == null) {
			INSTANCE = new ControladorServicioPromocion(sistema);
		}
		INSTANCE.ventanaServPromo.setDescripcion(nombre);
		INSTANCE.idPromocion=idPromo;
		INSTANCE.descripcionPromo=nombre;
		INSTANCE.servEnTabla=INSTANCE.sistema.obtenerServPromo(idPromo);
		INSTANCE.ventanaServPromo.llenarTabla(INSTANCE.sistema.obtenerServPromo(idPromo));
		llenarCombo(INSTANCE.ventanaServPromo.getCombo(), sistema);
		INSTANCE.ventanaServPromo.show();
		return INSTANCE;
	}
	


	private void borrarServ(ActionEvent k) {
		int[] filasSeleccionadas = this.ventanaServPromo.getTablServicioPromocion().getSelectedRows();
	       
    	for (int fila : filasSeleccionadas)
    	{
        	if(this.servEnTabla.get(fila)!=null) {
        		int confirm = JOptionPane.showOptionDialog(null, "Estas seguro que deseas borrar el Servicio asociado?","Confirmacion", JOptionPane.YES_NO_OPTION,
	   		             JOptionPane.QUESTION_MESSAGE, null, null, null);
	        		if (confirm == 0) {
	        			this.sistema.borrarServProm(idPromocion, idServEnTabla.get(servEnTabla.indexOf(this.servEnTabla.get(fila))));
	        		}
        	}
		}
    	this.getInstance(sistema, descripcionPromo, idPromocion);
	}

	private Object agregarServ(ActionEvent l) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private static void llenarCombo(JComboBox combo, Sistema sistema) {
		List<ServicioDTO> servicios=sistema.obtenerServicios();
		for (ServicioDTO s : servicios) {
			combo.addItem(s.getNombre());
			idServEnTabla.add(s.getIdServicio());
		}
		
	}
	
}
