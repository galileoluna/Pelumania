package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import dto.SucursalDTO;
import dto.UsuarioDTO;
import modelo.Sistema;
import presentacion.vista.VentanaAgregarSucursal;
import util.Validador;

public class ControladorAgregarSucursal  implements ActionListener {
	
	private VentanaAgregarSucursal ventanaAgregarSucursal;
	private Sistema sistema;
	private ControladorSucursal controladorSucursal;
	private static ControladorAgregarSucursal INSTANCE;
	private static UsuarioDTO usuario;

	private ControladorAgregarSucursal(Sistema sistema, UsuarioDTO usuar) {
		this.ventanaAgregarSucursal = ventanaAgregarSucursal.getInstance();
		this.ventanaAgregarSucursal.getBtnAgregarSucursal().addActionListener(p -> guardarSucursal(p));
		this.sistema = sistema;
		usuario = usuar;
	}

	public static ControladorAgregarSucursal getInstance(Sistema sistema, UsuarioDTO usuario) {
		if ( INSTANCE == null) {
			INSTANCE = new ControladorAgregarSucursal(sistema,usuario);
		}
		INSTANCE.ventanaAgregarSucursal.limpiarCampos();
		INSTANCE.ventanaAgregarSucursal.mostrarVentana();
		return INSTANCE;
	}
	
	private void guardarSucursal(ActionEvent p) {


		String S_nombreSucursal = this.ventanaAgregarSucursal.getTxtNombreSucursal().getText();
		String S_direccion = this.ventanaAgregarSucursal.getTxtDireccion().getText();
		String S_numero = this.ventanaAgregarSucursal.getTxtNumero().getText();
		//validamos campos
		if ( Validador.esNombreConEspaciosValido(S_nombreSucursal) &&
			Validador.esNumeroSucursalValido(S_numero) &&
			Validador.esNombreConEspaciosValido(S_direccion)){

			SucursalDTO nuevoSucursal = new SucursalDTO(0,S_nombreSucursal,S_direccion,Integer.parseInt(S_numero),"Activo");
			this.sistema.agregarSucursal(nuevoSucursal);
			ControladorSucursal.getInstance(sistema,usuario);
			this.ventanaAgregarSucursal.cerrar();
		} else {

			this.ventanaAgregarSucursal.mostrarErrorCampos();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}

}
