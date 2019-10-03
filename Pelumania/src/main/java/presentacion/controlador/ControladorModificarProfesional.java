package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;

import dto.ProfesionalDTO;
import modelo.Sistema;
import presentacion.vista.VentanaAltaProfesional;
import presentacion.vista.VentanaModificarProfesional;

public class ControladorModificarProfesional implements ActionListener {
	private VentanaModificarProfesional modificarProfesional;
	private ControladorProfesional controlProfesional;
	private static int idProfesional;
	private Sistema sistema;
	private static ControladorModificarProfesional INSTANCE;
	
	private ControladorModificarProfesional(Sistema sistema) {
		this.modificarProfesional = VentanaModificarProfesional.getInstance();
		this.modificarProfesional.getBtnAgregar().addActionListener(l -> guardarProfesional(l));
		this.sistema = sistema;
	}
	
	// inicializo la instancia de la pantalla que modifica al profesional (VentanaModificarProfesional)
	// recibe la lista del profesional seleccionado para editar, el sistema, y el id del profesional 
	public static ControladorModificarProfesional getInstance(Sistema sistema, List<ProfesionalDTO> profesional,int id) {
		if ( INSTANCE == null) {
			INSTANCE = new ControladorModificarProfesional(sistema);
		}
		for (ProfesionalDTO p : profesional) {
			idProfesional=id;
			INSTANCE.modificarProfesional.getTxtNombre().setText(p.nombre);
			INSTANCE.modificarProfesional.getTxtApellido().setText(p.getApellido());
			INSTANCE.modificarProfesional.getComboOrig().setSelectedItem(p.getIdSucursalOrigen());
			INSTANCE.modificarProfesional.getComboTran().setSelectedItem((p.getIdSucursalTransferencia()==-1?"--":p.getIdSucursalTransferencia()));
			INSTANCE.modificarProfesional.getEstado().setSelectedItem(p.getEstado());
		}
		
		INSTANCE.modificarProfesional.mostrarVentana();
		return INSTANCE;
	}
	
	// obtiene todos los campos de la ventana (VentanaModificarProfesional) para luego updatear la tabla 
	
	private void guardarProfesional(ActionEvent l) {
		String nombre= this.modificarProfesional.getTxtNombre().getText();
		String apellido=this.modificarProfesional.getTxtApellido().getText();
		Integer idSucursalOrig=Integer.parseInt(this.modificarProfesional.getComboOrig().getSelectedItem().toString());
		Integer idSucursalTran=(this.modificarProfesional.getComboTran().getSelectedItem().toString().equals("--")?-1:Integer.parseInt(this.modificarProfesional.getComboTran().getSelectedItem().toString()));
		String estado= this.modificarProfesional.getEstado().getSelectedItem().toString();
		if(validar(nombre,apellido,idSucursalOrig,idSucursalTran)) {
			ProfesionalDTO profesional= new ProfesionalDTO(this.idProfesional,nombre,apellido,idSucursalOrig,idSucursalTran,estado);
			this.sistema.actualizarProfesional(profesional);
			this.modificarProfesional.cerrar();
			//llama a la instancia del controladorProfesional asi se refresca la tabla 
			controlProfesional.getInstance(sistema);
		}else {
			JOptionPane.showMessageDialog(null, "Por favor ingrese todos los datos", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	// Valida que los campos ingresados no sean nulos o esten vacios	
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
