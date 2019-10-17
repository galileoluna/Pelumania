package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import javax.swing.JComboBox;

import dto.CategoriaMovimientoCajaDTO;
import dto.CitaDTO;
import dto.MovimientoCajaDTO;
import dto.ServicioTurnoDTO;
import modelo.Sistema;
import presentacion.vista.VentanaBuscarCita;
import presentacion.vista.VentanaCaja;
import util.Validador;

public class ControladorCaja implements ActionListener {

	private VentanaCaja ventanaCaja;
	private Sistema sistema;
	private List<CategoriaMovimientoCajaDTO> listaCategorias;
	private static ControladorCaja INSTANCE;
	private static VentanaBuscarCita ventanaBuscarCita;
	private CitaDTO citaSeleccionada;
	private List<ServicioTurnoDTO> serviciosCita;
	
	private ControladorCaja (Sistema sistema) {
		this.ventanaCaja = VentanaCaja.getInstance();
		this.sistema = sistema;
		this.citaSeleccionada = null;
		this.serviciosCita = null;
		
		this.ventanaCaja.getBtnCancelar().addActionListener(l -> cancelar(l));
		this.ventanaCaja.getComboTipoMovimiento().addActionListener(l -> mostrarInputs(l));
		this.ventanaCaja.getBtnAgregar().addActionListener(l -> agregarMovimiento(l));
		this.ventanaCaja.getComboCategoria().addActionListener(l -> productoSoloEfectivo(l));
		this.listaCategorias = this.sistema.obtenerCategoriasMovimientoCaja();
		this.ventanaCaja.getButtonBuscarCita().addActionListener(l -> buscarCita(l));
		
		this.mostrarIpuntsIngreso();
	}

	private void buscarCita(ActionEvent l) {
		ControladorBuscarCita.getInstance(this.sistema, this.ventanaCaja, this);
	}

	private void productoSoloEfectivo(ActionEvent l) {
		if (!esServicio()) {
			tipoPagoSoloEfectivo();
		} else {
			llenarComboTipoCambioServicio();
		}
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
		
		llenarComboTipoCambioServicio();

		this.ventanaCaja.getPanel_egreso().setVisible(false);
		
	}

	private void llenarComboTipoCambioServicio() {
		JComboBox<String> comboTipoCambio = this.ventanaCaja.getComboTipoCambio();
		comboTipoCambio.removeAllItems();
		comboTipoCambio.setEnabled(true);
		comboTipoCambio.addItem("Efectivo");
		
		comboTipoCambio.addItem("Puntos");
		//validar que el cliente no sea moroso
		comboTipoCambio.addItem("Fiado");
	}

	private void mostrarInputsEgreso() {
		this.ventanaCaja.getPanel_egreso().setVisible(true);
		this.ventanaCaja.getTxtIdCita().setText(null);
		this.citaSeleccionada = null;
		this.serviciosCita = null;
		agregarCategoriasEgreso();
		tipoPagoSoloEfectivo();
	}

	private void tipoPagoSoloEfectivo() {
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
			if(esCategoriaActiva(categoria.getEstado()))
			comboCategorias.addItem(categoria.getNombre());
		}
		
	}

	private boolean esCategoriaActiva(String estado) {
		return estado.equalsIgnoreCase("activo");
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
				Validador.esDescripcionValida(descripcion)) {
				
				if (esIngreso()) {
				
//					if(esServicio()) {
//						
//						MovimientoCajaDTO ingresoCita = new MovimientoCajaDTO(0, idSucursal, categoria, 
//																			
//																			fecha, tipoMovimiento, tipoCambio,
//																//promocion null
//																			null, precioPesos, precioDolar, 
//																			
//																			idProfesional, idCita, idCliente)
//					}
					//es un producto
//					else {
						
//					}

				
				
				
				} else {
						//constructor de egreso
						int idCategoria = this.sistema.getIdCategoriaMovimientoCajaByName(categoria).getIdCategoria();
					
						MovimientoCajaDTO egreso = new MovimientoCajaDTO(0, idSucursal, fecha, tipoMovimiento, 
																			idCategoria, tipoCambio, new BigDecimal(precioPesos),
																			new BigDecimal(precioDolar), descripcion);
						
						this.sistema.insertarEgreso(egreso);
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
	
	private boolean esServicio() {
		if (this.ventanaCaja.getComboCategoria().getItemCount() > 0) {
			String categoria = this.ventanaCaja.getComboCategoria().getSelectedItem().toString();
			return categoria.equalsIgnoreCase("servicio");
		} else
			return false;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public CitaDTO getCitaSeleccionada() {
		return this.citaSeleccionada;
	}
	public void setCitaSeleccionada(CitaDTO citaSeleccionada) {
		this.citaSeleccionada = citaSeleccionada;
	}
	public List<ServicioTurnoDTO> getServiciosCita() {
		return this.serviciosCita;
	}
	public void setServiciosCita(List<ServicioTurnoDTO> serviciosCita) {
		this.serviciosCita = serviciosCita;
	}

	public void mostarDatosCita() {
		//llenamos los campos con la cita que se selecciono en el buscador
		this.ventanaCaja.getTxtIdCita().setText(String.valueOf(this.citaSeleccionada.getIdCita()));
		
		this.ventanaCaja.getTxtPrecioPesos().setText(this.citaSeleccionada.getPrecioLocal().toString());
		this.ventanaCaja.getTxtPrecioPesos().setEditable(false); //seteamos como no editable el precio
		
		this.ventanaCaja.getTxtPrecioDolar().setText(this.citaSeleccionada.getPrecioDolar().toString());
		this.ventanaCaja.getTxtPrecioDolar().setEditable(false); //seteamos como no editable el precio
	}
}
