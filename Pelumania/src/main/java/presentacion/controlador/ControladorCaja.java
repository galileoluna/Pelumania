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
		this.listaCategorias = this.sistema.obtenerCategoriasMovimientoCaja();
		this.citaSeleccionada = null;
		this.serviciosCita = null;
		
		this.ventanaCaja.getBtnCancelar().addActionListener(l -> cancelar(l));
		this.ventanaCaja.getComboTipoMovimiento().addActionListener(l -> mostrarInputs(l));
		this.ventanaCaja.getBtnAgregar().addActionListener(l -> agregarMovimiento(l));
		this.ventanaCaja.getComboCategoria().addActionListener(l -> productoSoloEfectivo(l));
		this.ventanaCaja.getButtonBuscarCita().addActionListener(l -> buscarCita(l));
		
		this.mostrarIpuntsIngreso();
	}

	private void buscarCita(ActionEvent l) {
		ControladorBuscarCita.getInstance(this.sistema, this.ventanaCaja, this);
	}

	private void productoSoloEfectivo(ActionEvent l) {
		if (!esServicio()) {
			tipoPagoSoloEfectivo();
			enablePrecio();
			limpiarCita();
			this.ventanaCaja.getPanelIngresoServicio().setVisible(false);
		} else {
			llenarComboTipoCambioServicio();
			disablePrecio();
		}
	}

	private void cancelar(ActionEvent l) {
		this.ventanaCaja.limpiarCampos();
		this.ventanaCaja.cerrar();
	}

	public static ControladorCaja getInstance(Sistema sistema) {
		if ( INSTANCE == null) {
			INSTANCE = new ControladorCaja(sistema);
		}
		INSTANCE.listaCategorias = sistema.obtenerCategoriasMovimientoCaja();
		INSTANCE.ventanaCaja.mostrarVentana();
		INSTANCE.mostrarIpuntsIngreso();
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
		this.listaCategorias = this.sistema.obtenerCategoriasMovimientoCaja();
		this.ventanaCaja.limpiarCampos();
		//hay que limpiar la descripcion y demas para que no se guarde
		JComboBox<String> comboCategoria = this.ventanaCaja.getComboCategoria();
		comboCategoria.removeAllItems();
		
		//agregamos solo las categorias de ingresos
		for (CategoriaMovimientoCajaDTO categoria : listaCategorias) {
			if(esCategoriaActiva(categoria.getEstado()) && categoria.esIngreso()) {
				
				comboCategoria.addItem(categoria.getNombre());
			}
		}
		
		llenarComboTipoCambioServicio();
		this.ventanaCaja.getPanelIngresoServicio().setVisible(true);
		this.ventanaCaja.getPanelEgreso().setVisible(false);
		
	}

	private void llenarComboTipoCambioServicio() {
		this.ventanaCaja.getPanelIngresoServicio().setVisible(true);
		
		
		JComboBox<String> comboTipoCambio = this.ventanaCaja.getComboTipoCambio();
		comboTipoCambio.removeAllItems();
		comboTipoCambio.setEnabled(true);
		comboTipoCambio.addItem("Efectivo");
		comboTipoCambio.addItem("Puntos");
		//validar que el cliente no sea moroso
		comboTipoCambio.addItem("Fiado");
	}

	private void mostrarInputsEgreso() {
		this.ventanaCaja.getPanelEgreso().setVisible(true);
		//ocultamos lo de ingreso
		this.ventanaCaja.getPanelIngresoServicio().setVisible(false);
		limpiarCita();
		
		agregarCategoriasEgreso();
		tipoPagoSoloEfectivo();
		enablePrecio();
	}

	private void limpiarCita() {
		this.ventanaCaja.getTxtIdCita().setText(null);
		
		//borramos si habia alguna cita seleccionada junto a sus servicios
		this.citaSeleccionada = null;
		this.serviciosCita = null;
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
			//pongo solo las que son egreso
			if(esCategoriaActiva(categoria.getEstado()) && !categoria.esIngreso()) {
				comboCategorias.addItem(categoria.getNombre());
			}
		}
		
	}

	private boolean esCategoriaActiva(String estado) {
		return estado.equalsIgnoreCase("activo");
	}

	private void agregarMovimiento(ActionEvent l) {
		
		int idSucursal = 1; //de donde sacamos esto?
		Instant fecha = Instant.now();
		String descripcion = this.ventanaCaja.getTxtDescripcion();
		String tipoMovimiento = this.ventanaCaja.getComboTipoMovimiento().getSelectedItem().toString();
		String tipoCambio = this.ventanaCaja.getComboTipoCambio().getSelectedItem().toString();		
		String precioPesosTotal = this.ventanaCaja.getTxtPrecioPesos().getText();
		String precioDolarTotal = this.ventanaCaja.getTxtPrecioDolar().getText();

		String strCategoria = this.ventanaCaja.getComboCategoria().getSelectedItem().toString();
		int idCategoria = this.sistema.getIdCategoriaMovimientoCajaByName(strCategoria).getIdCategoria();
		
		//valido los campos en comun entre ingreso y egreso
		if ( Validador.esTipoMovimientoValido(tipoMovimiento) && 
				Validador.esPrecioValido(precioPesosTotal) &&
				Validador.esPrecioValido(precioDolarTotal) &&
				Validador.esTipoCambioValido(tipoCambio) &&
				Validador.esDescripcionValida(descripcion)) {
				
				if (esServicio()) {
					//tomamos datos de la cita seleccionada
					//tomamos datos de los servicios asociados a esa cita
					//por cada servicio sera una transaccion de "caja" porque hay que marcar lo que hizo cada profesional
					boolean exito = true; 
					 
					for (ServicioTurnoDTO servicio : serviciosCita) {
						
						//buscamos el precio individual de ese servicio
						BigDecimal precioPesosServicio =  this.sistema.getServicioById(servicio.getIdServicio()).getPrecioLocal();
						BigDecimal precioDolarServicio =  this.sistema.getServicioById(servicio.getIdServicio()).getPrecioDolar();
						
						MovimientoCajaDTO ingresoServicio = new MovimientoCajaDTO(0, citaSeleccionada.getIdSucursal(),idCategoria, 
																							//por ahora  las promos en null o -1
																				fecha, tipoCambio, -1, precioPesosServicio, 
																				
																				precioDolarServicio, 1, citaSeleccionada.getIdCita(),
																				
																				citaSeleccionada.getIdCliente(), servicio.getIdServicio());
						
					 exito = this.sistema.insertarIngresoServicio(ingresoServicio) && exito;
					
					} 
					
//					exito ? this.ventanaCaja.mostrarExito() ?  this.ventanaCaja.mostrarErrorBDD();
					
//				}
					
					
					 
					
							
//					}
					//es un producto
//					else {
						
//					}

				} else if (esProducto()) {
					//do something 
				
				} else {
						//CONSTRUCTOR PARA EGRESOS
																			//revisar sucursal en un egreso
						MovimientoCajaDTO egreso = new MovimientoCajaDTO(0, idSucursal, fecha,
																			idCategoria, tipoCambio, new BigDecimal(precioPesosTotal),
																			new BigDecimal(precioDolarTotal), descripcion);
						
						if (this.sistema.insertarEgreso(egreso)) {
							//se guardo piola
							this.ventanaCaja.mostrarExito();
						} else {
							//rompio la bdd
							this.ventanaCaja.mostrarErrorBDD();
						}
				}
								
			} else {
			//hubo un error en los inputs
			//no paso los validadores
			this.ventanaCaja.mostrarErrorCampos();
		}
		
		this.ventanaCaja.limpiarCampos();
	}

	public void mostarDatosCita() {
		//llenamos los campos con la cita que se selecciono en el buscador
		this.ventanaCaja.getTxtIdCita().setText(String.valueOf(this.citaSeleccionada.getIdCita()));
		this.ventanaCaja.getTxtPrecioPesos().setText(this.citaSeleccionada.getPrecioLocal().toString());
		this.ventanaCaja.getTxtPrecioDolar().setText(this.citaSeleccionada.getPrecioDolar().toString());
		disablePrecio();
	}

	private void disablePrecio() {
		this.ventanaCaja.getTxtPrecioPesos().setEditable(false); //seteamos como no editable el precio
		this.ventanaCaja.getTxtPrecioDolar().setEditable(false); //seteamos como no editable el precio
	}
	
	private void enablePrecio() {
		this.ventanaCaja.getTxtPrecioPesos().setEditable(true); //seteamos como no editable el precio
		this.ventanaCaja.getTxtPrecioPesos().setText(null);
		this.ventanaCaja.getTxtPrecioDolar().setEditable(true); //seteamos como no editable el precio
		this.ventanaCaja.getTxtPrecioDolar().setText(null);
	}
	private boolean esProducto() {
		return this.ventanaCaja.getComboCategoria().getSelectedItem().toString().equalsIgnoreCase("Producto");
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
	
	
}