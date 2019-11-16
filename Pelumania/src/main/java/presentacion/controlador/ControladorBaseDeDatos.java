package presentacion.controlador;

import java.io.FileInputStream;

import javax.swing.JOptionPane;

import dto.BackUpDTO;
import modelo.Sistema;
import presentacion.vista.VentanaBaseDeDatos;
import presentacion.vista.VentanaCliente;

public class ControladorBaseDeDatos {
	private static VentanaBaseDeDatos ventanaBDD;
	private static Sistema sistema;
	private static BackUpDTO dto;
	private static ControladorBaseDeDatos INSTANCE;
	
	private ControladorBaseDeDatos(Sistema sis, int exportar) {
		this.ventanaBDD = VentanaBaseDeDatos.getInstance();
		this.sistema = sis;
	}

	public static ControladorBaseDeDatos getInstance(Sistema sistema, int exportar) {
		if ( INSTANCE == null) {
			INSTANCE = new ControladorBaseDeDatos(sistema,exportar);
		}
		if(exportar == 1) {
			exportar();
		}else {
			importar();
		}
		return INSTANCE;
	}

	private static void importar() {
		ventanaBDD.show();
		sistema.importarBdd();
		JOptionPane.showMessageDialog(null, "Se importo la base de datos con Exito!, Para que los cambios sean aplicados se debe cerrar la Aplicacion y volverse a abrir");
		ventanaBDD.cerrar();
		
	}

	private static void exportar() {
		ventanaBDD.show();
		sistema.exportarBdd();
		JOptionPane.showMessageDialog(null, "Se exporto la base de datos con Exito!");
		ventanaBDD.cerrar();
		
		
	}
}
