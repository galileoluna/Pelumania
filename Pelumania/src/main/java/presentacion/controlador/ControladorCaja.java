package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

import com.google.protobuf.Timestamp;

import dto.ClienteDTO;
import dto.MovimientoCajaDTO;
import modelo.Sistema;
import presentacion.vista.VentanaCaja;

public class ControladorCaja implements ActionListener {

	private VentanaCaja ventanaCaja;
	private Sistema sistema;
	private List<MovimientoCajaDTO> listaIngresos;
	private List<MovimientoCajaDTO> listaEgresos;
	private static ControladorCaja INSTANCE;
	
	private ControladorCaja (Sistema sistema) {
		this.ventanaCaja = VentanaCaja.getInstance();
		this.sistema = sistema;
		
		this.ventanaCaja.getComboTipoMovimiento().addActionListener(l -> mostrarInputs(l));
		this.ventanaCaja.getBtnAgregar().addActionListener(l -> agregarMovimiento(l));
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
				//mostrarIpuntsIngreso()
		//es egreso
		} else {
			mostrarInputsEgreso();
		}
	}
	
	private void mostrarInputsEgreso() {
		this.ventanaCaja.getPanel_egreso().setVisible(true);
	}

	private void agregarMovimiento(ActionEvent l) {
		
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
		
		if (esIngreso()) {
			
			//validar campos ingreso
		}
		//es egreso
		else {
			//validar campos egreso
						
		MovimientoCajaDTO mov = new MovimientoCajaDTO(0, idSucursal, fecha, 
								tipoMovimiento, categoria, tipoCambio, 
								precioLocal, precioDolar, descripcion);
		
		this.sistema.insertarEgreso(mov);
		System.out.println("BIEN AHI PAPAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		
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
