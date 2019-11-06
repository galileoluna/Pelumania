
package presentacion.controlador;

import java.awt.event.ActionEvent;

import modelo.Sistema;
import presentacion.vista.VentanaIdioma;
import util.PropertyManager;

public class ControladorIdioma {
	private static VentanaIdioma ventanaIdioma;
	private Sistema sistema;
	private static ControladorIdioma INSTANCE;

	private ControladorIdioma(Sistema sistema) {
		this.ventanaIdioma = ventanaIdioma.getInstance();
		this.sistema = sistema;
		this.ventanaIdioma.getBtnAceptar().addActionListener(l -> cambiarIdioma(l));
	}
	
	public static ControladorIdioma getInstance(Sistema sistema) {
		if ( INSTANCE == null) {
			INSTANCE = new ControladorIdioma(sistema);
		}
		INSTANCE.ventanaIdioma.mostrarVentana();
		return INSTANCE;
	}

	private void cambiarIdioma(ActionEvent l) {
		String idiomaNuevo = "";
		String paisNuevo= "";
		
		if (this.ventanaIdioma.getRadioButtonEspaniol().isSelected()) {
			idiomaNuevo = "es"; 
			paisNuevo = "ES";
			
		} else if(this.ventanaIdioma.getRadioButtonIngles().isSelected()) {
			idiomaNuevo = "en"; 
			paisNuevo = "US";
		}
		String nombreArchivo = "configuracion";
		PropertyManager.escribir(nombreArchivo, "idioma", idiomaNuevo);
		PropertyManager.escribir(nombreArchivo, "pais", paisNuevo);
		
//		System.out.println(PropertyManager.leer("configuracion", "idioma"));
		System.out.println(PropertyManager.leer("configuracion", "pais"));
		
		this.ventanaIdioma.mostrarExito();
		this.ventanaIdioma.cerrar();
	}
}


