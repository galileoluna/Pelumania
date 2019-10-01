package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import dto.ProfesionalDTO;
import modelo.Sistema;
import presentacion.vista.VentanaAltaProfesional;
import presentacion.vista.VentanaCliente;
import presentacion.vista.VentanaProfesional;

public class ControladorAltaProfesional  implements ActionListener{
	private VentanaAltaProfesional altaProfesional;
	private ControladorProfesional controlProfesional;
	private Sistema sistema;
	private static ControladorAltaProfesional INSTANCE;
	
	private ControladorAltaProfesional(Sistema sistema) {
		this.altaProfesional = VentanaAltaProfesional.getInstance();
		this.altaProfesional.getBtnAgregar().addActionListener(l -> guardarProfesional(l));
		this.sistema = sistema;
	}
	
	public static ControladorAltaProfesional getInstance(Sistema sistema) {
		if ( INSTANCE == null) {
			INSTANCE = new ControladorAltaProfesional(sistema);
		}	
		
		INSTANCE.altaProfesional.mostrarVentana();
		return INSTANCE;
	}
	
	private void guardarProfesional(ActionEvent l) {
		
		String nombre= this.altaProfesional.getTxtNombre().getText();
		String apellido=this.altaProfesional.getTxtApellido().getText();
		Integer idSucursalOrig=Integer.parseInt(this.altaProfesional.getComboOrig().getSelectedItem().toString());
		Integer idSucursalTran=Integer.parseInt(this.altaProfesional.getComboTran().getSelectedItem().toString());
		if(validar(nombre,apellido,idSucursalOrig,idSucursalTran)) {
			ProfesionalDTO profesional= new ProfesionalDTO(0,nombre,apellido,idSucursalOrig,idSucursalTran);
			this.sistema.agregarProfesional(profesional);
			this.altaProfesional.cerrar();
			controlProfesional.getInstance(sistema);
		}
	}
	
	private boolean validar(String nombre,String apellido, Integer sucuOrig, Integer sucuTran) {
		if (nombre == null || nombre.equals("") || apellido == null || apellido.equals("") || sucuOrig == null || sucuTran == null ) {
			return false;
		}else {
			return true;
		}
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
