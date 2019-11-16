package presentacion.controlador;


import javax.swing.JOptionPane;

import dto.BackUpDTO;
import modelo.Sistema;
import presentacion.vista.VentanaBaseDeDatos;

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
		if(sistema.importarBdd()) {
			ventanaBDD.mostrarExitoImportar();
		} else {
			ventanaBDD.mostrarError();
		}
		ventanaBDD.cerrar();
		
	}

	private static void exportar() {
		ventanaBDD.show();
		if (sistema.exportarBdd()) {
			ventanaBDD.mostrarExitoExportar();
		} else {
			ventanaBDD.mostrarError();
		}
		ventanaBDD.cerrar();
		
		
	}
}
