package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import dto.CategoriaMovimientoCajaDTO;
import modelo.Sistema;
import presentacion.vista.VentanaAgregarCategoriaMovimientosCaja;
import util.Validador;

public class ControladorAgregarCategoriaMovimientoCaja   implements ActionListener {
	
	private VentanaAgregarCategoriaMovimientosCaja VentanaAgregarCategoriaMovimientosCaja;
	private Sistema sistema;
	private ControladorCategoriaMovimientoCaja controladorSucursal;
	private static ControladorAgregarCategoriaMovimientoCaja INSTANCE;

	private ControladorAgregarCategoriaMovimientoCaja(Sistema sistema) {
		this.VentanaAgregarCategoriaMovimientosCaja = VentanaAgregarCategoriaMovimientosCaja.getInstance();
		this.VentanaAgregarCategoriaMovimientosCaja.getBtnAgregarSucursal().addActionListener(p -> guardarCateforia(p));
		llenarCombos();	
		this.sistema = sistema;
	}

	private void llenarCombos() {
		JComboBox<String> comboEstados = this.VentanaAgregarCategoriaMovimientosCaja.getComboEstado();
		comboEstados.addItem("activo");
		comboEstados.addItem("inactivo");
	}

	public static ControladorAgregarCategoriaMovimientoCaja getInstance(Sistema sistema) {
		if ( INSTANCE == null) {
			INSTANCE = new ControladorAgregarCategoriaMovimientoCaja(sistema);
		}
		INSTANCE.VentanaAgregarCategoriaMovimientosCaja.limpiarCampos();
		INSTANCE.VentanaAgregarCategoriaMovimientosCaja.mostrarVentana();
		return INSTANCE;
	}
	
	private void guardarCateforia(ActionEvent p) {
		String nombre = this.VentanaAgregarCategoriaMovimientosCaja.getTxtNombre().getText();
		String estado = this.VentanaAgregarCategoriaMovimientosCaja.getComboEstado().getSelectedItem().toString();

		//validamos campos
		if ( Validador.esNombreConEspaciosValido(nombre)){

			CategoriaMovimientoCajaDTO nuevaCategoria = new CategoriaMovimientoCajaDTO(0,nombre,estado);
			this.sistema.insertarCategoriaMovimientoCaja(nuevaCategoria);
			ControladorCategoriaMovimientoCaja.getInstance(sistema);
			this.VentanaAgregarCategoriaMovimientosCaja.cerrar();
		} else {

			this.VentanaAgregarCategoriaMovimientosCaja.mostrarErrorCampos();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}

}
