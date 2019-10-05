package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.TableModel;

import dto.ClienteDTO;
import modelo.Sistema;
import presentacion.vista.VentanaCliente;
import util.Validador;

public class ControladorCliente implements ActionListener {

	private VentanaCliente ventanaCliente;
	private Sistema sistema;
	private List<ClienteDTO> listaClientes;
	private static ControladorCliente INSTANCE;

	private ControladorCliente(Sistema sistema) {
		this.ventanaCliente = VentanaCliente.getInstance();
		this.ventanaCliente.getBtnAgregar().addActionListener(p -> guardarCliente(p));
		this.ventanaCliente.getBtnBorrar().addActionListener(p-> borrarCliente(p));
		this.ventanaCliente.getBtnEditar().addActionListener(p-> editarCliente(p));
		this.ventanaCliente.btnBorrarReal.addActionListener(r -> deleteReal(r));
		this.sistema = sistema;
	}

	public static ControladorCliente getInstance(Sistema sistema) {
		if ( INSTANCE == null) {
			INSTANCE = new ControladorCliente(sistema);
		}
		inicializarDatos();
		return INSTANCE;
	}
	private static void inicializarDatos() {

		List<ClienteDTO> listaClientes = INSTANCE.sistema.obtenerClientes();
		INSTANCE.ventanaCliente.llenarTabla(listaClientes);
		INSTANCE.ventanaCliente.limpiarInputs();
		INSTANCE.ventanaCliente.mostrar();
	}

	/**
	 * @param ¿que estado tiene en el alta un cliente?
	 * */
	private void guardarCliente(ActionEvent p) {


		String nombre = this.ventanaCliente.getTxtNombre().getText();
		String apellido = this.ventanaCliente.getTxtApellido().getText();
		String telefono = this.ventanaCliente.getTxtTelefono().getText();
		String mail = this.ventanaCliente.getTxtMail().getText();
		int puntos = 0 ; // por defecto al dar de alta no tiene puntos
		String estado = "activo";
		BigDecimal deuda =  new BigDecimal(0);//tampoco tendra deuda al ser dado de alta

		//validamos campos
		if ( Validador.esNombreConEspaciosValido(nombre) &&
				Validador.esNombreConEspaciosValido(apellido) &&
				Validador.esTelefono(telefono) &&
				Validador.esMail(mail)) {


			ClienteDTO nuevoCliente = new ClienteDTO(0, nombre, apellido, telefono, mail, puntos, estado, deuda);

			this.sistema.agregarCliente(nuevoCliente);
			this.listaClientes = this.sistema.obtenerClientes();
			this.ventanaCliente.llenarTabla(listaClientes);
			this.ventanaCliente.limpiarInputs();

		} else {

			this.ventanaCliente.mostrarErrorCamposInvalidos();
		}
		
		INSTANCE.ventanaCliente.mostrarExitoAlta();

	}

	private void borrarCliente(ActionEvent p) {

		this.listaClientes = this.sistema.obtenerClientes();
		int[] filasSeleccionadas = this.ventanaCliente.getTablaClientes().getSelectedRows();

		for (int fila : filasSeleccionadas)
		{
			if(listaClientes.get(fila)!=null) {
				int confirm = JOptionPane.showOptionDialog(null, "Estas seguro que deseas borrar al Cliente?","Confirmacion", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, null, null);
				if (confirm == 0) {
					this.sistema.borrarCliente((listaClientes.get(fila)));
				}
			}
		}
		this.listaClientes = INSTANCE.sistema.obtenerClientes();
		INSTANCE.ventanaCliente.llenarTabla(this.listaClientes);
	}

	private void editarCliente(ActionEvent p) {

		this.listaClientes = this.sistema.obtenerClientes();
		int filaSeleccionada = this.ventanaCliente.getTablaClientes().getSelectedRow();

		TableModel tabla = this.ventanaCliente.getTablaClientes().getModel();

		ClienteDTO cliente_seleccionado = this.listaClientes.get(filaSeleccionada);
		if (cliente_seleccionado != null) {

			int id = cliente_seleccionado.getIdCliente();
			String nombre = tabla.getValueAt(filaSeleccionada, 0).toString();
			String apellido =  tabla.getValueAt(filaSeleccionada, 1).toString();
			String telefono =  tabla.getValueAt(filaSeleccionada, 2).toString();
			String mail =  tabla.getValueAt(filaSeleccionada, 3).toString();
			String puntos = tabla.getValueAt(filaSeleccionada, 4).toString();
			String estado = tabla.getValueAt(filaSeleccionada, 5).toString();
			String deuda = tabla.getValueAt(filaSeleccionada, 6).toString();

			if ( Validador.esNombreConEspaciosValido(nombre) &&
					Validador.esNombreConEspaciosValido(apellido) &&
					Validador.esTelefono(telefono) &&
					Validador.esMail(mail) &&
					Validador.esPuntosValido( puntos) &&
					Validador.esEstadoClienteValido(estado)&&
					Validador.esPrecioValido(deuda))
			{
				ClienteDTO cliente_a_modifcar = new ClienteDTO(id, nombre, apellido, telefono, mail, Integer.parseInt(puntos), estado, new BigDecimal(deuda.replaceAll(",", "")));

				INSTANCE.sistema.editarCliente(cliente_a_modifcar);
				INSTANCE.ventanaCliente.mostrarExitoEditar();

			} else {
				INSTANCE.ventanaCliente.mostrarErrorCamposInvalidos();
			}

		} else {
			INSTANCE.ventanaCliente.mostrarErrorSinSeleccionar();
		}
		
		INSTANCE.ventanaCliente.llenarTabla(INSTANCE.sistema.obtenerClientes());

	}

	// BORRABLE
	//------------------------------------------------
	private void deleteReal(ActionEvent p) {

		this.listaClientes = this.sistema.obtenerClientes();
		int[] filasSeleccionadas = this.ventanaCliente.getTablaClientes().getSelectedRows();

		for (int fila : filasSeleccionadas)
		{
			if(listaClientes.get(fila)!=null) {
				int confirm = JOptionPane.showOptionDialog(null, "Seguro Santi? Mira que vas a borrar todo mono","Confirmacion", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, null, null);
				if (confirm == 0) {
					this.sistema.deleteReal((listaClientes.get(fila)));
				}
			}
		}
		this.listaClientes = INSTANCE.sistema.obtenerClientes();
		INSTANCE.ventanaCliente.llenarTabla(this.listaClientes);
	}

	//------------------------------------------------
	//BORRABLE
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}



}
