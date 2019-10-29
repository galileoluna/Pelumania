package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import javax.swing.JOptionPane;

import dto.ProfesionalDTO;
import dto.ServicioDTO;
import dto.SucursalDTO;
import dto.UsuarioDTO;
import modelo.Sistema;
import presentacion.vista.VentanaEditarServicio;
import presentacion.vista.VentanaEditarSucursal;
import util.Validador;

public class ControladorEditarSucursal {
	private VentanaEditarSucursal ventanaEditarSucursal;
	private Sistema sistema;
	private ControladorSucursal controladorSucursal;
	private static int idSucursal;
	private static ControladorEditarSucursal INSTANCE;
	private static UsuarioDTO usuario;
	
	private ControladorEditarSucursal(Sistema sistema, UsuarioDTO usuar) {
		this.ventanaEditarSucursal = VentanaEditarSucursal.getInstance();
		this.ventanaEditarSucursal.getBtnGuardarSucursal().addActionListener(a -> guardarSucursal(a));
		this.sistema = sistema;
		usuario = usuar;
	}
	
	public static ControladorEditarSucursal getInstance(Sistema sistema, SucursalDTO sucursal, int id, UsuarioDTO usuario) {
		if ( INSTANCE == null) {
			INSTANCE = new ControladorEditarSucursal(sistema, usuario);
		}	
			idSucursal = id;
			INSTANCE.ventanaEditarSucursal.getTxtNombreSucursal().setText(sucursal.getNombreSucursal());
			INSTANCE.ventanaEditarSucursal.getTxtDireccion().setText(sucursal.getDireccion());
			INSTANCE.ventanaEditarSucursal.getTxtNumero().setText(Integer.toString(sucursal.getNumero()));
			INSTANCE.ventanaEditarSucursal.getJCBoxEstado().setSelectedItem(sucursal.getEstadoSucursal());

			INSTANCE.ventanaEditarSucursal.mostrarVentana();
		return INSTANCE;
	}

	
	/*private void guardarSucursal(ActionEvent a) {
		String S_nombreSucursal = this.ventanaEditarSucursal.getTxtNombreSucursal().getText();
		String S_direccion = this.ventanaEditarSucursal.getTxtDireccion().getText();
		int S_numero = Integer.parseInt(this.ventanaEditarSucursal.getTxtNumero().getText());
		String S_estado = (String) this.ventanaEditarSucursal.getJCBoxEstado().getSelectedItem();
		
		SucursalDTO sucursal_a_actualizar = new SucursalDTO(idSucursal,S_nombreSucursal, S_direccion, S_numero, S_estado);
				this.sistema.editarSucursal(sucursal_a_actualizar);
				this.ventanaEditarSucursal.cerrar();
				ControladorServicio.getInstance(sistema);
		
	}*/
	
	private void guardarSucursal(ActionEvent l) {
		String S_nombreSucursal = this.ventanaEditarSucursal.getTxtNombreSucursal().getText();
		String S_direccion = this.ventanaEditarSucursal.getTxtDireccion().getText();
		int S_numero = Integer.parseInt(this.ventanaEditarSucursal.getTxtNumero().getText());
		String S_estado = (String) this.ventanaEditarSucursal.getJCBoxEstado().getSelectedItem();

		//validamos campos
		if ( Validador.esNombreConEspaciosValido(S_nombreSucursal) &&
			Validador.esNumeroSucursalValido(Integer.toString(S_numero)) &&
			Validador.esNombreConEspaciosValido(S_direccion)){
			if(validar(S_nombreSucursal,S_direccion,S_numero,S_estado)) {
				SucursalDTO sucursal= new SucursalDTO(this.idSucursal,S_nombreSucursal,S_direccion,S_numero,S_estado);
				this.sistema.editarSucursal(sucursal);
				this.ventanaEditarSucursal.cerrar();
				//llama a la instancia del controladorSucursal asi se refresca la tabla 
				controladorSucursal.getInstance(sistema, usuario);
			}else JOptionPane.showMessageDialog(null, "Por favor ingrese todos los datos", "Error", JOptionPane.ERROR_MESSAGE);
		}else {
			this.ventanaEditarSucursal.mostrarErrorCampos();
		}
			
	}
	// Valida que los campos ingresados no sean nulos o esten vacios	
	private boolean validar(String nombre,String direccion, int numero, String estado) {
		if (nombre == null || nombre.equals("") || direccion == null || direccion.equals("") || estado == null || estado == null || numero==0) {
			
			return false;
		}else {
			return true;
		}
		
	}
}
