package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.table.TableModel;

import dto.ClienteDTO;
import modelo.Sistema;
import presentacion.vista.VentanaAgregarCita;
import presentacion.vista.VentanaBuscarCliente;

public class ControladorBuscarCliente implements ActionListener{
	
	private VentanaBuscarCliente ventanaBuscarCliente;
	private Sistema sistema;
	private List<ClienteDTO> listaClientes;
	private static ControladorBuscarCliente INSTANCE;
	private static VentanaAgregarCita ventanaAgregarCita;

	private ControladorBuscarCliente(Sistema sistema, VentanaAgregarCita VAC) {
		this.ventanaBuscarCliente = VentanaBuscarCliente.getInstance();
		this.ventanaAgregarCita = VAC;
		this.ventanaBuscarCliente.getBtnSeleccionarCliente().addActionListener(p -> seleccionarCliente(p));
		this.sistema = sistema;
	}

	public static ControladorBuscarCliente getInstance(Sistema sistema, VentanaAgregarCita VAC) {
		if ( INSTANCE == null) {
			INSTANCE = new ControladorBuscarCliente(sistema, VAC);
		}
		inicializarDatos();
		return INSTANCE;
	}
	private static void inicializarDatos() {

		List<ClienteDTO> listaClientes = INSTANCE.sistema.obtenerClientes();
		INSTANCE.ventanaBuscarCliente.llenarTabla(listaClientes);
		INSTANCE.ventanaBuscarCliente.mostrarVentana();
	}
	
	private void seleccionarCliente(ActionEvent p) {
		this.listaClientes = INSTANCE.sistema.obtenerClientes();
		int filaSeleccionada = this.ventanaBuscarCliente.getTablaClientes().getSelectedRow();
		System.out.println(listaClientes);
		ClienteDTO cliente_seleccionado = this.listaClientes.get(filaSeleccionada);
		System.out.println(cliente_seleccionado);
		if (cliente_seleccionado != null) {
			String nombre = cliente_seleccionado.getNombre();
			String apellido = cliente_seleccionado.getApellido();
			int id = cliente_seleccionado.getIdCliente();
			
			this.ventanaAgregarCita.getTxtNombre().setText(nombre);
			this.ventanaAgregarCita.getTxtNombre().setEditable(false);
			this.ventanaAgregarCita.getTxtApellido().setText(apellido);
			this.ventanaAgregarCita.getTxtApellido().setEditable(false);
			this.ventanaAgregarCita.setIdCliente(id);
			
			this.ventanaBuscarCliente.cerrar();
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}

