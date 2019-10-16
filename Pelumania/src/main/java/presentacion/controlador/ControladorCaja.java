package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

import javax.swing.ComboBoxEditor;
import javax.swing.JComboBox;

import dto.CategoriaMovimientoCajaDTO;
import dto.ClienteDTO;
import dto.MovimientoCajaDTO;
import modelo.Sistema;
import presentacion.vista.VentanaCaja;
import util.Validador;

public class ControladorCaja implements ActionListener {

	private VentanaCaja ventanaCaja;
	private Sistema sistema;
	private List<CategoriaMovimientoCajaDTO> listaCategorias;
	private static ControladorCaja INSTANCE;
	
	private ControladorCaja (Sistema sistema) {
		this.ventanaCaja = VentanaCaja.getInstance();
		this.sistema = sistema;
		
		this.ventanaCaja.getBtnCancelar().addActionListener(l -> cancelar(l));
		this.ventanaCaja.getComboTipoMovimiento().addActionListener(l -> mostrarInputs(l));
		this.ventanaCaja.getBtnAgregar().addActionListener(l -> agregarMovimiento(l));
		this.listaCategorias = this.sistema.obtenerCategoriasMovimientoCaja();
		
		this.mostrarIpuntsIngreso();
	}

	private void cancelar(ActionEvent l) {
		this.ventanaCaja.cerrar();
	}

	public static ControladorCaja getInstance(Sistema sistema) {
		if ( INSTANCE == null) {
			INSTANCE = new ControladorCaja(sistema);
		}
		INSTANCE.ventanaCaja.mostrarVentana();
		return INSTANCE;
	}
	
	private void mostrarInputs(ActionEvent l) {
		if ( esIngreso()) {
				mostrarIpuntsIngreso();
		//es egreso
		} else {
			mostrarInputsEgreso();
		}
	}
	
	private void mostrarIpuntsIngreso() {
		this.ventanaCaja.limpiarCampos();
		//hay que limpiar la descripcion y demas para que no se guarde
		JComboBox<String> comboCategoria = this.ventanaCaja.getComboCategoria();
		comboCategoria.removeAllItems();
		comboCategoria.addItem("Servicio");
		comboCategoria.addItem("Producto");
		
		JComboBox<String> comboTipoCambio = this.ventanaCaja.getComboTipoCambio();
		comboTipoCambio.removeAllItems();
		comboTipoCambio.setEnabled(true);
		comboTipoCambio.addItem("Efectivo");
		comboTipoCambio.addItem("Puntos");
		//validar que el cliente no sea moroso
		comboTipoCambio.addItem("Fiado");

		
		
		///////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////

		///////////////////////////////////////////////////////////
		this.ventanaCaja.getPanel_egreso().setVisible(false);
		
	}

	private void mostrarInputsEgreso() {
		this.ventanaCaja.getPanel_egreso().setVisible(true);
		agregarCategoriasEgreso();
		tipoPagoEgreso();
	}

	private void tipoPagoEgreso() {
		JComboBox<String> comboTiposPago = this.ventanaCaja.getComboTipoCambio();
		//limpio tipos de pago de ingresos
		comboTiposPago.removeAllItems();
		comboTiposPago.addItem("Efectivo");
		comboTiposPago.setEnabled(false);
	}


	private void agregarCategoriasEgreso() {
		
		JComboBox<String> comboCategorias = this.ventanaCaja.getComboCategoria();
		//limpio servicio y producto que son las fijas de ingreso
		comboCategorias.removeAllItems();
		
		for (CategoriaMovimientoCajaDTO categoria : listaCategorias) {
			comboCategorias.addItem(categoria.getNombre());
		}
		
	}

	private void agregarMovimiento(ActionEvent l) {
		
		int idSucursal = 1; //de donde sacamos esto?
		String categoria = this.ventanaCaja.getComboCategoria().getSelectedItem().toString();
		Instant fecha = Instant.now();
		String descripcion = this.ventanaCaja.getTxtDescripcion();
		String tipoMovimiento = this.ventanaCaja.getComboTipoMovimiento().getSelectedItem().toString();
		String tipoCambio = this.ventanaCaja.getComboTipoCambio().getSelectedItem().toString();		
		String precioPesos = this.ventanaCaja.getTxtPrecioPesos().getText();
		String precioDolar = this.ventanaCaja.getTxtPrecioDolar().getText();

		
		//valido los campos en comun entre ingreso y egreso
		if ( Validador.esTipoMovimientoValido(tipoMovimiento) && 
				Validador.esPrecioValido(precioPesos) &&
				Validador.esPrecioValido(precioDolar) &&
				Validador.esTipoCambioValido(tipoCambio) &&
				Validador.esDescripcionValida(descripcion)
				) {
				
				if (esIngreso()) {
					//validar campos ingreso
					
		
				//obtengo datos del movimiento
		//		int idSucursal = 1; //0; //de donde lo saca de la sesion del usuario?
		//		String categoria = this.ventanaCaja.getComboCategoria().getSelectedItem().toString();
		//		Instant fecha = Instant.now();
		//		String descripcion = this.ventanaCaja.getTxtDescripcion();
		//		String tipoMovimiento = this.ventanaCaja.getComboTipoMovimiento().getSelectedItem().toString();
		//		String tipoCambio = "efectivo"; // cambiar
		//		int idPromocion;
		//		BigDecimal precioLocal = new BigDecimal(100);// cambiar
		//		BigDecimal precioDolar = new BigDecimal(69); // cambiar
		//		int idProfesional;
		//		int idCita;
		//		int idCliente;
				
				} else {
						//constructor de egreso
						int idCategoria = this.sistema.getIdCategoriaMovimientoCajaByName(categoria).getIdCategoria();
					
						MovimientoCajaDTO movimiento = new MovimientoCajaDTO(0, idSucursal, fecha, tipoMovimiento, 
																			idCategoria, tipoCambio, new BigDecimal(precioPesos),
																			new BigDecimal(precioDolar), descripcion);
						
						this.sistema.insertarEgreso(movimiento);
						this.ventanaCaja.mostrarExito();
					}
								
			}
		else {
			//hubo un error en los inputs
			this.ventanaCaja.mostrarErrorCampos();
		}
		
		this.ventanaCaja.limpiarCampos();
	}

	private boolean esIngreso() {
		String tipoMov = this.ventanaCaja.getComboTipoMovimiento().getSelectedItem().toString();
		return tipoMov.equalsIgnoreCase("ingreso");
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
