package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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
