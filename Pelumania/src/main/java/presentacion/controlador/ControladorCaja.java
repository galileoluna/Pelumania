package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import dto.CategoriaMovimientoCajaDTO;
import dto.CitaDTO;
import dto.ClienteDTO;
import dto.MovimientoCajaDTO;
import dto.ProductoDTO;
import dto.ServicioTurnoDTO;
import modelo.Sistema;
import presentacion.vista.VentanaCaja;
import util.Validador;

public class ControladorCaja implements ActionListener {

	private VentanaCaja ventanaCaja;
	private Sistema sistema;
	private List<CategoriaMovimientoCajaDTO> listaCategorias;
	private static ControladorCaja INSTANCE;
	private CitaDTO citaSeleccionada;
	private List<ServicioTurnoDTO> serviciosCita;
	private Controlador2 controladorMenu;
	private boolean clienteMoroso = false;
	private ProductoDTO productoSeleccionado;

	private ControladorCaja(Sistema sistema, Controlador2 controladorMenu) {
		this.ventanaCaja = VentanaCaja.getInstance();
		this.sistema = sistema;
		this.controladorMenu = controladorMenu;
		this.listaCategorias = this.sistema.obtenerCategoriasMovimientoCaja();
		this.citaSeleccionada = null;
		this.serviciosCita = null;

		this.ventanaCaja.getBtnCancelar().addActionListener(l -> cancelar(l));
		this.ventanaCaja.getComboTipoMovimiento().addActionListener(l -> mostrarInputs(l));
		this.ventanaCaja.getBtnAgregar().addActionListener(l -> agregarMovimiento(l));
		this.ventanaCaja.getComboCategoria().addActionListener(l -> productoSoloEfectivo(l));
		this.ventanaCaja.getButtonBuscarCita().addActionListener(l -> buscarCita(l));
		this.ventanaCaja.getButtonBuscarProducto().addActionListener(l -> buscarProducto(l));
		this.ventanaCaja.getComboBoxCantidad().addActionListener(l -> actualizarPrecio(l));
		this.mostrarIpuntsIngreso();
	}

	private void buscarProducto(ActionEvent l) {
		ControladorBuscarProducto.getInstance(this.sistema, this.ventanaCaja, this);
	}

	private void buscarCita(ActionEvent l) {
		ControladorBuscarCita.getInstance(this.sistema, this.ventanaCaja, this);
	}

	private void productoSoloEfectivo(ActionEvent l) {
		if (!esServicio() && !esEgreso()) {
			tipoPagoSoloEfectivo();
			limpiarCita();
			ocultarPanelServicios();
			mostrarPanelProducto();
		} else {
			ocultarPanelProducto();
			llenarComboTipoCambioServicio();
		}
		disablePrecio();
	}

	private boolean esEgreso() {
		if (this.ventanaCaja.getComboTipoMovimiento().getItemCount() > 0) {
			String categoria = this.ventanaCaja.getComboTipoMovimiento().getSelectedItem().toString();
			return categoria.equalsIgnoreCase("egreso") || categoria.equalsIgnoreCase("egresos");
		} else
			return false;
	}

	private void mostrarPanelProducto() {
		this.ventanaCaja.getPanelProducto().setVisible(true);
	}

	private void ocultarPanelProducto() {
		this.ventanaCaja.getPanelProducto().setVisible(false);
		this.ventanaCaja.getTxtProductoId().setText(null);
		this.ventanaCaja.getTxtPrecioDolar().setText(null);
		this.ventanaCaja.getTxtPrecioPesos().setText(null);
		this.ventanaCaja.getComboBoxCantidad().setSelectedIndex(-1);
		this.productoSeleccionado = null;
	}

	public void ocultarPanelServicios() {
		this.ventanaCaja.getPanelIngresoServicio().setVisible(false);
	}

	private void cancelar(ActionEvent l) {
		this.ventanaCaja.limpiarCampos();
		this.ventanaCaja.cerrar();
	}

	public static ControladorCaja getInstance(Sistema sistema, Controlador2 controladorMenu) {
		if (INSTANCE == null) {
			INSTANCE = new ControladorCaja(sistema, controladorMenu);
		}
		INSTANCE.listaCategorias = sistema.obtenerCategoriasMovimientoCaja();
		INSTANCE.ventanaCaja.mostrarVentana();
		INSTANCE.mostrarIpuntsIngreso();
		return INSTANCE;
	}

	private void mostrarInputs(ActionEvent l) {
		if (esIngreso()) {
			mostrarIpuntsIngreso();
		} else {
			ocultarPanelProducto();
			mostrarInputsEgreso();
		}
	}

	private void mostrarIpuntsIngreso() {
		this.listaCategorias = this.sistema.obtenerCategoriasMovimientoCaja();
		this.ventanaCaja.limpiarCampos();
		// hay que limpiar la descripcion y demas para que no se guarde
		JComboBox<String> comboCategoria = this.ventanaCaja.getComboCategoria();
		comboCategoria.removeAllItems();

		// agregamos solo las categorias de ingresos
		for (CategoriaMovimientoCajaDTO categoria : listaCategorias) {
			if (esCategoriaActiva(categoria.getEstado()) && categoria.esIngreso()) {

				comboCategoria.addItem(categoria.getNombre());
			}
		}
		if (esServicio()) {
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
		// validar que el cliente no sea moroso
		comboTipoCambio.addItem("Fiado");
	}

	private void mostrarInputsEgreso() {
		ocultarPanelServicios();
		this.ventanaCaja.getPanelEgreso().setVisible(true);
		limpiarCita();
		agregarCategoriasEgreso();
		tipoPagoSoloEfectivo();
		enablePrecio();
	}

	private void limpiarCita() {
		this.ventanaCaja.getTxtIdCita().setText(null);

		// borramos si habia alguna cita seleccionada junto a sus servicios
		this.citaSeleccionada = null;
		this.serviciosCita = null;
	}

	private void tipoPagoSoloEfectivo() {
		JComboBox<String> comboTiposPago = this.ventanaCaja.getComboTipoCambio();
		// limpio tipos de pago de ingresos
		comboTiposPago.removeAllItems();
		comboTiposPago.addItem("Efectivo");
		comboTiposPago.setEnabled(false);
	}

	private boolean esCategoriaActiva(String estado) {
		return estado.equalsIgnoreCase("activo");
	}

	private void agregarCategoriasEgreso() {

		JComboBox<String> comboCategorias = this.ventanaCaja.getComboCategoria();
		// limpio servicio y producto que son las fijas de ingreso
		comboCategorias.removeAllItems();

		for (CategoriaMovimientoCajaDTO categoria : listaCategorias) {
			// pongo solo las que son egreso
			if (esCategoriaActiva(categoria.getEstado()) && !categoria.esIngreso()) {
				comboCategorias.addItem(categoria.getNombre());
			}
		}

	}

	private void agregarMovimiento(ActionEvent l) {
		int idSucursal = 1; // de donde sacamos esto?
		Timestamp fecha = Timestamp.from(Instant.now());
		String descripcion = this.ventanaCaja.getTxtDescripcion();
		String tipoMovimiento = this.ventanaCaja.getComboTipoMovimiento().getSelectedItem().toString();
		String tipoCambio = this.ventanaCaja.getComboTipoCambio().getSelectedItem().toString();
		String precioPesosTotal = this.ventanaCaja.getTxtPrecioPesos().getText();
		String precioDolarTotal = this.ventanaCaja.getTxtPrecioDolar().getText();

		String strCategoria = this.ventanaCaja.getComboCategoria().getSelectedItem().toString();
		int idCategoria = this.sistema.getIdCategoriaMovimientoCajaByName(strCategoria).getIdCategoria();

		// valido los campos en comun entre ingreso y egreso
		if (Validador.esTipoMovimientoValido(tipoMovimiento) && Validador.esPrecioValido(precioPesosTotal)
				&& Validador.esPrecioValido(precioDolarTotal) && Validador.esTipoCambioValido(tipoCambio)
				&& Validador.esDescripcionValida(descripcion)) {

			if (esServicio()) {
				// tomamos datos de la cita seleccionada
				// tomamos datos de los servicios asociados a esa cita
				// por cada servicio sera una transaccion de "caja" porque hay que marcar lo que
				// hizo cada profesional
				ClienteDTO cliente = this.sistema.obtenerClienteById(citaSeleccionada.getIdCliente());

				// si es una cliente fiado solo puede pagar en efectivo
				if (!tipoCambio.equalsIgnoreCase("Efectivo") && cliente.getEstadoCliente().equalsIgnoreCase("Moroso"))
					this.ventanaCaja.mostrarErrorMorosoEfectivo();
				else {

					boolean exito = true;
					boolean esFiado = false;
					boolean esPagoDeuda = false;
					int puntosTotales = 0;

					for (ServicioTurnoDTO servicio : serviciosCita) {

						// buscamos el precio individual de ese servicio
						BigDecimal precioPesosServicio = this.sistema.getServicioById(servicio.getIdServicio())
								.getPrecioLocal();
						BigDecimal precioDolarServicio = this.sistema.getServicioById(servicio.getIdServicio())
								.getPrecioDolar();

						// verifico el caso que se este pagando una deuda
						if (cliente.getEstadoCliente().equalsIgnoreCase("moroso")
								&& tipoCambio.equalsIgnoreCase("Efectivo")) {
							esPagoDeuda = true;
							precioPesosServicio = new BigDecimal(0);
							precioDolarServicio = new BigDecimal(0);
						}

						// buscamos los puntos de cada servicio particular
						int puntos = this.sistema.getServicioById(servicio.getIdServicio()).getPuntos();

						MovimientoCajaDTO movimientoServicio = new MovimientoCajaDTO(0,
								citaSeleccionada.getIdSucursal(), idCategoria,
								// por ahora las promos en null o -1
								fecha, tipoCambio, -1, precioPesosServicio,

								precioDolarServicio, 1, citaSeleccionada.getIdCita(),

								citaSeleccionada.getIdCliente(), servicio.getIdServicio());

						// por is falla algo en la bdd
						exito = this.sistema.insertarIngresoServicio(movimientoServicio) && exito;

						// por si es fiado
						esFiado = movimientoServicio.getTipoCambio().equalsIgnoreCase("Fiado");

						// sumamos puntos
						if (esEfectivo())
							puntosTotales += puntos;
					}

					if (exito) {
						// se guardo bien

						// finalizamos la cita
						if (esFiado)
							this.sistema.estadoFiadoCita(citaSeleccionada);
						else
							this.sistema.finalizarCita(citaSeleccionada);

						if (esEfectivo()) {
							// sumamos los puntos solo si paga en efectivo
							cliente.setPuntos(cliente.getPuntos() + puntosTotales);
						}

						if (esFiado) {
							// actualizamos la deuda y el estado del cliente
							cliente.setDeudaPesos(cliente.getDeudaPesos().add(citaSeleccionada.getPrecioLocal()));
							cliente.setDeudaDolar(cliente.getDeudaDolar().add(citaSeleccionada.getPrecioDolar()));
							cliente.setEstadoCliente("Moroso");
						}
						if (esPagoDeuda) {
							cliente.setDeudaPesos(new BigDecimal(0));
							cliente.setDeudaDolar(new BigDecimal(0));
							cliente.setEstadoCliente("Activo");
						}

						this.sistema.editarCliente(cliente);
						this.ventanaCaja.mostrarExito();
						this.controladorMenu.actualizarDiaSeleccionado();
					}
				}
			} else if (esProducto()) {
				// es un producto y tiene menos campos que un servico
				// parseamos
				BigDecimal precioPesosProducto = new BigDecimal(this.ventanaCaja.getTxtPrecioPesos().getText());
				BigDecimal precioDolarProducto = new BigDecimal(this.ventanaCaja.getTxtPrecioDolar().getText());

				int idProducto = this.productoSeleccionado.getIdProducto();
				int cantidadProducto = (int) this.ventanaCaja.getComboBoxCantidad().getSelectedItem();
				
				MovimientoCajaDTO movimientoProducto = new MovimientoCajaDTO(0, idSucursal, idCategoria, fecha,
						tipoCambio, precioPesosProducto, precioDolarProducto, idProducto, cantidadProducto);
				// lo insertamos en la bdd
				if (this.sistema.insertarIngresoProducto(movimientoProducto)) {
					// exito
					System.out.println("en teoria salio todo bien");
					this.ventanaCaja.mostrarExito();

				} else {
					// rompio algo
					this.ventanaCaja.mostrarErrorBDD();
				}

			} else {
				// CONSTRUCTOR PARA EGRESOS ES DISTINTO
				// revisar sucursal en un egreso
				MovimientoCajaDTO egreso = new MovimientoCajaDTO(0, idSucursal, fecha, idCategoria, tipoCambio,
						new BigDecimal(precioPesosTotal), new BigDecimal(precioDolarTotal), descripcion);

				if (this.sistema.insertarEgreso(egreso)) {
					// se guardo piola
					this.ventanaCaja.mostrarExito();
				} else {
					// rompio la bdd
					this.ventanaCaja.mostrarErrorBDD();
				}
			}

		} else {
			// hubo un error en los inputs
			// no paso los validadores
			if (clienteMoroso == true)
				this.ventanaCaja.mostrarErrorMorosoEfectivo();
			else
				this.ventanaCaja.mostrarErrorCampos();
		}

		this.ventanaCaja.limpiarCampos();

	}

	public void mostarDatosCita() {
		// llenamos los campos con la cita que se selecciono en el buscador
		this.ventanaCaja.getTxtIdCita().setText(String.valueOf(this.citaSeleccionada.getIdCita()));
		this.ventanaCaja.getTxtPrecioPesos().setText(this.citaSeleccionada.getPrecioLocal().toString());
		this.ventanaCaja.getTxtPrecioDolar().setText(this.citaSeleccionada.getPrecioDolar().toString());
		disablePrecio();
	}

	private void disablePrecio() {
		this.ventanaCaja.getTxtPrecioPesos().setEditable(false);
		this.ventanaCaja.getTxtPrecioDolar().setEditable(false);
	}

	private void enablePrecio() {
		this.ventanaCaja.getTxtPrecioPesos().setEditable(true); // seteamos como no editable el precio
		this.ventanaCaja.getTxtPrecioPesos().setText(null);
		this.ventanaCaja.getTxtPrecioDolar().setEditable(true); // seteamos como no editable el precio
		this.ventanaCaja.getTxtPrecioDolar().setText(null);
	}

	private boolean esProducto() {
		if (this.ventanaCaja.getComboCategoria().getSelectedItem().toString().equalsIgnoreCase("Producto")
				|| this.ventanaCaja.getComboCategoria().getSelectedItem().toString().equalsIgnoreCase("Productos")) {

			return true;

		} else {
			return false;
		}

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

	public void cobrarCitaDesdeMenu(CitaDTO citaSeleccionada) {
		this.ventanaCaja.getComboTipoMovimiento().setSelectedItem("Ingreso");
		if (existeCategoriaServicio()) {
			if (esServicio()) {
				llenarComboTipoCambioServicio();
				this.ventanaCaja.getPanelIngresoServicio().setVisible(true);
			}
			this.ventanaCaja.getComboCategoria().setSelectedItem("Servicio");
			this.ventanaCaja.getPanelIngresoServicio().setVisible(true);
			this.setCitaSeleccionada(citaSeleccionada);
			this.setServiciosCita(this.sistema.getServicioTurnoByIdCita(citaSeleccionada.getIdCita()));
			this.mostarDatosCita();
			validarCliente(citaSeleccionada);
		}
	}

	private void validarCliente(CitaDTO citaSeleccionada2) {
		ArrayList<ClienteDTO> clientes = (ArrayList<ClienteDTO>) sistema.obtenerClientes();
		ClienteDTO actual = clientes.get(0);
		for (int i = 0; i < clientes.size(); i++) {
			if (citaSeleccionada2.getIdCliente() == clientes.get(i).getIdCliente())
				actual = clientes.get(i);
		}
		if (actual.getEstadoCliente() == "moroso" || (actual.getDeudaPesos().compareTo(new BigDecimal(0)) > 0)
				|| (actual.getDeudaDolar().compareTo(new BigDecimal(0)) > 0))
			clienteMoroso = true;
	}

	private boolean existeCategoriaServicio() {
		for (CategoriaMovimientoCajaDTO categoria : listaCategorias) {
			if (categoria.getNombre().equalsIgnoreCase("SERVICIO")) {
				this.ventanaCaja.getComboCategoria().setSelectedItem(categoria.getNombre());
			} else if (categoria.getNombre().equalsIgnoreCase("SERVICIOS")) {
				this.ventanaCaja.getComboCategoria().setSelectedItem(categoria.getNombre());
				return true;
			}
		}
		return false;
	}

	public void mostrarDatosProducto() {
		this.ventanaCaja.getTxtProductoId().setText(this.productoSeleccionado.getNombre());
		actualizarPrecio();

	}

	private void actualizarPrecio() {
		BigDecimal pesos = this.productoSeleccionado.getPrecioLocal();
		BigDecimal dolares = this.productoSeleccionado.getPrecioDolar();
		int cantidad = (int) this.ventanaCaja.getComboBoxCantidad().getSelectedItem();

		pesos = pesos.multiply(new BigDecimal(cantidad));
		dolares = dolares.multiply(new BigDecimal(cantidad));

		this.ventanaCaja.getTxtPrecioPesos().setText(pesos.toString());
		this.ventanaCaja.getTxtPrecioDolar().setText(dolares.toString());
	}

	private void actualizarPrecio(ActionEvent l) {
		if (this.productoSeleccionado != null && esProducto()) {
			actualizarPrecio();
		}
	}

	public void setProductoSeleccionada(ProductoDTO productoSeleccionado) {
		this.productoSeleccionado = productoSeleccionado;
	}

}