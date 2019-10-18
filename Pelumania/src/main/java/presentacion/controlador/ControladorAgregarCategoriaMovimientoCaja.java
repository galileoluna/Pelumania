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
	private int idCategoria;
	private static ControladorAgregarCategoriaMovimientoCaja INSTANCE;

	private ControladorAgregarCategoriaMovimientoCaja(Sistema sistema) {
		this.VentanaAgregarCategoriaMovimientosCaja = VentanaAgregarCategoriaMovimientosCaja.getInstance();
		this.VentanaAgregarCategoriaMovimientosCaja.getBtnAgregarCategoria().addActionListener(p -> agregarCategoria(p));
		this.VentanaAgregarCategoriaMovimientosCaja.getBtnEditarCategoria().addActionListener(l -> editarCategoria(l));
		this.VentanaAgregarCategoriaMovimientosCaja.getBtnCancelar().addActionListener(l -> cancelar(l));
		llenarCombos();	 
		this.sistema = sistema;
	}

	private void cancelar(ActionEvent l) {
		this.VentanaAgregarCategoriaMovimientosCaja.cerrar();
	}

	private void editarCategoria(ActionEvent l) {
		String nombre = this.VentanaAgregarCategoriaMovimientosCaja.getTxtNombre().getText();
		String estado = this.VentanaAgregarCategoriaMovimientosCaja.getComboEstado().getSelectedItem().toString();
		String tipo = this.VentanaAgregarCategoriaMovimientosCaja.getComboTipoMovimiento().getSelectedItem().toString();
		
		//validamos campos
		if ( Validador.esNombreConEspaciosValido(nombre) && Validador.esTipoMovimientoValido(tipo)) {
			 if (!existeCategoria(nombre)){
				CategoriaMovimientoCajaDTO categoria_a_editar = new CategoriaMovimientoCajaDTO(this.idCategoria,nombre,estado,tipo);
				this.sistema.updateCategoriaMovimientoCaja(categoria_a_editar);
				ControladorCategoriaMovimientoCaja.getInstance(sistema);
				this.VentanaAgregarCategoriaMovimientosCaja.cerrar();
				this.VentanaAgregarCategoriaMovimientosCaja.mostrarExitoEditar();
			
			 } else {
				 //esta repetido
				 this.VentanaAgregarCategoriaMovimientosCaja.mostrarErrorRepetido();
			 }
		
		} else {
			//los campos estan mal
			this.VentanaAgregarCategoriaMovimientosCaja.mostrarErrorCampos();
		}
	}

	private boolean existeCategoria(String nombre) {
		try {
		CategoriaMovimientoCajaDTO cat = this.sistema.getIdCategoriaMovimientoCajaByName(nombre);
		if ( cat != null) {
			return cat.getIdCategoria() != this.idCategoria;
		} else {
			return false;
			}
		} catch (IndexOutOfBoundsException e) {}
		
		return false;
	}

	private void llenarCombos() {
		JComboBox<String> comboTipo = this.VentanaAgregarCategoriaMovimientosCaja.getComboTipoMovimiento();
		comboTipo.addItem("Ingreso");
		comboTipo.addItem("Egreso");
		
		
		JComboBox<String> comboEstados = this.VentanaAgregarCategoriaMovimientosCaja.getComboEstado();
		comboEstados.addItem("Activo");
		comboEstados.addItem("Inactivo");
	}
	

	public static ControladorAgregarCategoriaMovimientoCaja getInstance(Sistema sistema) {
		if ( INSTANCE == null) {
			INSTANCE = new ControladorAgregarCategoriaMovimientoCaja(sistema);
		}
		INSTANCE.VentanaAgregarCategoriaMovimientosCaja.limpiarCampos();
		INSTANCE.VentanaAgregarCategoriaMovimientosCaja.mostrarVentana();
		INSTANCE.VentanaAgregarCategoriaMovimientosCaja.getBtnAgregarCategoria().setVisible(true);
		INSTANCE.VentanaAgregarCategoriaMovimientosCaja.getBtnEditarCategoria().setVisible(false);
		return INSTANCE;
	}
	
	//Constructor para editar, recibe un parametro extra
	public static ControladorAgregarCategoriaMovimientoCaja getInstance(Sistema sistema, int idCategoria) {
		if ( INSTANCE == null) {
			INSTANCE = new ControladorAgregarCategoriaMovimientoCaja(sistema);
		}
		INSTANCE.VentanaAgregarCategoriaMovimientosCaja.limpiarCampos();
		INSTANCE.VentanaAgregarCategoriaMovimientosCaja.mostrarVentana();
		INSTANCE.VentanaAgregarCategoriaMovimientosCaja.getBtnAgregarCategoria().setVisible(true);
		INSTANCE.VentanaAgregarCategoriaMovimientosCaja.getBtnEditarCategoria().setVisible(false);
		INSTANCE.idCategoria = idCategoria;
		return INSTANCE;
	}
	
	public void llenarInputsEditar(CategoriaMovimientoCajaDTO categoria) {
		//oculto el boton "agregar"
		this.VentanaAgregarCategoriaMovimientosCaja.getBtnAgregarCategoria().setVisible(false);
		//muesto el de editar
		this.VentanaAgregarCategoriaMovimientosCaja.getBtnEditarCategoria().setVisible(true);
		
		this.VentanaAgregarCategoriaMovimientosCaja.getTxtNombre().setText(categoria.getNombre());
	}
	
	private void agregarCategoria(ActionEvent p) {
		String nombre = this.VentanaAgregarCategoriaMovimientosCaja.getTxtNombre().getText();
		String estado = this.VentanaAgregarCategoriaMovimientosCaja.getComboEstado().getSelectedItem().toString();
		String tipo = this.VentanaAgregarCategoriaMovimientosCaja.getComboTipoMovimiento().getSelectedItem().toString();
		
		//validamos campos
		if ( Validador.esNombreConEspaciosValido(nombre) && Validador.esTipoMovimientoValido(tipo)){
			if (!existeCategoria(nombre)){
				CategoriaMovimientoCajaDTO nuevaCategoria = new CategoriaMovimientoCajaDTO(0,nombre,estado,tipo);
				this.sistema.insertarCategoriaMovimientoCaja(nuevaCategoria);
				this.VentanaAgregarCategoriaMovimientosCaja.mostrarExitoAgregar();
				ControladorCategoriaMovimientoCaja.getInstance(sistema);
				this.VentanaAgregarCategoriaMovimientosCaja.cerrar();
			} else {
				this.VentanaAgregarCategoriaMovimientosCaja.mostrarErrorRepetido();
			}
			
		
		} else {
			this.VentanaAgregarCategoriaMovimientosCaja.mostrarErrorNombre();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}

}
