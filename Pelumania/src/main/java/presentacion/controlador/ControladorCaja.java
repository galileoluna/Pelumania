package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import javax.swing.JComboBox;

import dto.CategoriaMovimientoCajaDTO;
import dto.CitaDTO;
import dto.ClienteDTO;
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
		if(esServicio()) {
			llenarComboTipoCambioServicio();
			this.ventanaCaja.getPanelIngresoServicio().setVisible(true);
		}
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
					ClienteDTO cliente = this.sistema.obtenerClienteById(citaSeleccionada.getIdCliente());
					boolean exito = true; 
					boolean esFiado = false;
					int puntosTotales = 0;
					for (ServicioTurnoDTO servicio : serviciosCita) {
						
						//buscamos el precio individual de ese servicio
						BigDecimal precioPesosServicio =  this.sistema.getServicioById(servicio.getIdServicio()).getPrecioLocal();
						BigDecimal precioDolarServicio =  this.sistema.getServicioById(servicio.getIdServicio()).getPrecioDolar();
						
						//buscamos los puntos de cada servicio particular
						int puntos = this.sistema.getServicioById(servicio.getIdServicio()).getPuntos();
						
						MovimientoCajaDTO movimientoServicio = new MovimientoCajaDTO(0, citaSeleccionada.getIdSucursal(),idCategoria, 
																					//por ahora  las promos en null o -1
																				fecha, tipoCambio, -1, precioPesosServicio, 
																				
																				precioDolarServicio, 1, citaSeleccionada.getIdCita(),
																				
																				citaSeleccionada.getIdCliente(), servicio.getIdServicio());
						
						//por is falla algo en la bdd
						exito = this.sistema.insertarIngresoServicio(movimientoServicio) && exito;
						
						//por si es fiado
						esFiado = movimientoServicio.getTipoCambio().equalsIgnoreCase("Fiado");
						
						//sumamos puntos
						puntosTotales += puntos; 
					} 
					
					if (exito) {
						//se guardo bien
						
						//finalizamos la cita
						this.sistema.finalizarCita(citaSeleccionada);
						
						if (esEfectivo()) {
						//sumamos los puntos solo si paga en efectivo
						cliente.setPuntos(cliente.getPuntos() + puntosTotales);
						}
						
						if (esFiado) {
							//actualizamos la deuda
							cliente.setDeuda(cliente.getDeuda().add(citaSeleccionada.getPrecioLocal()));
						}
							
						this.sistema.editarCliente(cliente);
						this.ventanaCaja.mostrarExito();
					}
					
				} else if (esProducto()) {
					//es un producto y tiene menos campos que un servico
					//parseamos 
					BigDecimal precioPesosProducto = new BigDecimal(this.ventanaCaja.getTxtPrecioPesos().getText());
					BigDecimal precioDolarProducto = new BigDecimal(this.ventanaCaja.getTxtPrecioDolar().getText());
					
					MovimientoCajaDTO movimientoProducto = new MovimientoCajaDTO(0, idSucursal, idCategoria,
																			fecha, tipoCambio, precioPesosProducto, precioDolarProducto);
					//lo insertamos en la bdd
					if (this.sistema.insertarIngresoProducto(movimientoProducto)) {
						//exito
						System.out.println("en teoria salio todo bien");
						this.ventanaCaja.mostrarExito();
					
					} else {
						//rompio algo
						this.ventanaCaja.mostrarErrorBDD();
					}
				
				} else {
						//CONSTRUCTOR PARA EGRESOS ES DISTINTO
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
		return tipoMov.equalsIgnoreCase("ingreso") || tipoMov.equalsIgnoreCase("ingresos");
	}
	
	private boolean esServicio() {
		if (this.ventanaCaja.getComboCategoria().getItemCount() > 0) {
			String categoria = this.ventanaCaja.getComboCategoria().getSelectedItem().toString();
			return categoria.equalsIgnoreCase("servicio") || categoria.equalsIgnoreCase("servicios");
		} else
			return false;
	}
	
	private boolean esEfectivo() {
		return this.ventanaCaja.getComboTipoCambio().getSelectedItem().toString().equalsIgnoreCase("Efectivo");
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
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