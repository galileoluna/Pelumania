package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

import javax.swing.JComboBox;

import dto.CategoriaMovimientoCajaDTO;
import dto.ClienteDTO;
import dto.MovimientoCajaDTO;
import modelo.Sistema;
import presentacion.vista.VentanaCaja;

public class ControladorCaja implements ActionListener {

	private VentanaCaja ventanaCaja;
	private Sistema sistema;
	private List<MovimientoCajaDTO> listaIngresos;
	private List<MovimientoCajaDTO> listaEgresos;
	private List<CategoriaMovimientoCajaDTO> listaCategorias;
	private static ControladorCaja INSTANCE;
	
	private ControladorCaja (Sistema sistema) {
		this.ventanaCaja = VentanaCaja.getInstance();
		this.sistema = sistema;
		
		this.ventanaCaja.getComboTipoMovimiento().addActionListener(l -> mostrarInputs(l));
		this.ventanaCaja.getBtnAgregar().addActionListener(l -> agregarMovimiento(l));
		this.listaCategorias = this.sistema.obtenerCategoriasMovimientoCaja();
	}




	public static ControladorCaja getInstance(Sistema sistema) {
		if ( INSTANCE == null) {
			INSTANCE = new ControladorCaja(sistema);
		}
		inicializarDatos();
		return INSTANCE;
	}
	private static void inicializarDatos() {

//		INSTANCE.ventanaCaja.limpiarInputs();
		INSTANCE.ventanaCaja.mostrarVentana();

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
		this.ventanaCaja.getPanel_egreso().setVisible(false);
		
	}




	private void mostrarInputsEgreso() {
		this.ventanaCaja.getPanel_egreso().setVisible(true);
//		this.ventanaCaja.getPanel_Ingreso().setVisible(false);
		
		agregarCategoriasEgreso();
		tipoPagoEgreso();
//		this.ventanaCaja.getComboTipoPago().addItem("Efectivo");
	}

	private void tipoPagoEgreso() {
		JComboBox<String> comboTiposPago = this.ventanaCaja.getComboTipoPago();
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
		
		if (esIngreso()) {
			//validar campos ingreso

		//obtengo datos del movimiento
		int idSucursal = 1; //0; //de donde lo saca de la sesion del usuario?
		String categoria = this.ventanaCaja.getComboCategoria().getSelectedItem().toString();
		Instant fecha = Instant.now();
		String descripcion = this.ventanaCaja.getTxtDescripcion();
		String tipoMovimiento = this.ventanaCaja.getComboTipoMovimiento().getSelectedItem().toString();
		String tipoCambio = "efectivo"; // cambiar
//		int idPromocion;
		BigDecimal precioLocal = new BigDecimal(100);// cambiar
		BigDecimal precioDolar = new BigDecimal(69); // cambiar
//		int idProfesional;
//		int idCita;
//		int idCliente;
		
			
		}
		//es egreso
		else {
			//validar campos egreso
						
//		MovimientoCajaDTO mov = new MovimientoCajaDTO(0, idSucursal, fecha, 
//								tipoMovimiento, categoria, tipoCambio, 
//								precioLocal, precioDolar, descripcion);
		
//		this.sistema.insertarEgreso(mov);
		System.out.println("NANANNAAN CLAVE ESE METODO BEBETO NDEAAHHHHHHH DE RUTAAAAAA");
		
		}
		
		
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
