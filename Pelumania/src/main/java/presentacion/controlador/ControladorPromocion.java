package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JOptionPane;

import dto.ProfesionalDTO;
import dto.PromocionDTO;
import modelo.Sistema;
import presentacion.vista.VentanaProfesional;
import presentacion.vista.VentanaPromocion;

public class ControladorPromocion {
	private static ControladorPromocion INSTANCE;
	private Sistema sistema;
	private VentanaPromocion ventanaPromocion;
	List<PromocionDTO> promosEnTabla;
	
	private ControladorPromocion(Sistema sistema) {
		this.ventanaPromocion = VentanaPromocion.getInstance();
		this.sistema=sistema;
		this.ventanaPromocion.getBtnBorrar().addActionListener(l -> borrarPromocion(l));
	}

	public static ControladorPromocion getInstance(Sistema sistema) {
		if ( INSTANCE == null) {
			INSTANCE = new ControladorPromocion(sistema);
		}
		
		List<PromocionDTO> promosEnTabla=sistema.obtenerPrmociones();
		INSTANCE.ventanaPromocion.llenarTabla(promosEnTabla);
		INSTANCE.ventanaPromocion.show();
		return INSTANCE;
	}
	

	private void borrarPromocion(ActionEvent l) {
		this.promosEnTabla=sistema.obtenerPrmociones();
		int[] filasSeleccionadas = this.ventanaPromocion.gettablaPromocion().getSelectedRows();
		       
	        	for (int fila : filasSeleccionadas)
	        	{
		        	if(this.promosEnTabla.get(fila)!=null) {	 
		        		int confirm = JOptionPane.showOptionDialog(null, "Estas seguro que deseas borrar la Promocion?","Confirmacion", JOptionPane.YES_NO_OPTION,
		   		             JOptionPane.QUESTION_MESSAGE, null, null, null);
		        		if (confirm == 0) {
		        			// if para el boton de borrado de santi
		        				this.sistema.borrarPromocion(this.promosEnTabla.get(fila));
		        			
		        		}
		        		// llama a la instancia para refrescar tabla
		        		this.getInstance(sistema);
		        	}
				}	
	}

}
