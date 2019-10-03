package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

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

	// obtengo los datos ingresados en la pantalla (VentanaAltaHorario) para luego hacer el insert en la tabla
	
	private void guardarProfesional(ActionEvent l) {
		
		String nombre= this.altaProfesional.getTxtNombre().getText();
		String apellido=this.altaProfesional.getTxtApellido().getText();
		Integer idSucursalOrig=Integer.parseInt(this.altaProfesional.getComboOrig().getSelectedItem().toString());
		Integer idSucursalTran=(this.altaProfesional.getComboTran().getSelectedItem().toString().equals("--")?-1:Integer.parseInt(this.altaProfesional.getComboTran().getSelectedItem().toString()));
		String estado=this.altaProfesional.getEstado().getSelectedItem().toString();
		if(validar(nombre,apellido,idSucursalOrig,idSucursalTran)) {
			ProfesionalDTO profesional= new ProfesionalDTO(0,nombre,apellido,idSucursalOrig,idSucursalTran,estado);
			this.sistema.agregarProfesional(profesional);
			this.altaProfesional.cerrar();
			// llamo a la instancia del ControladorProfesional para refrescar la tabla 
			controlProfesional.getInstance(sistema);
		}else {
			JOptionPane.showMessageDialog(null, "Por favor ingrese todos los datos", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	// valido que los datos no sean nullos o vacios
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
