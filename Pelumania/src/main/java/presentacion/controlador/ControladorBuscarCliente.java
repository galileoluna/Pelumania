package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import dto.ClienteDTO;
import modelo.Sistema;
import presentacion.vista.VentanaBuscarCliente;
import presentacion.vista.nuevaVentanaCita;

public class ControladorBuscarCliente implements ActionListener{
	
	private VentanaBuscarCliente ventanaBuscarCliente;
	private Sistema sistema;
	private List<ClienteDTO> listaClientes;
	private static ControladorBuscarCliente INSTANCE;
	private static nuevaVentanaCita ventanaCita;

	private ControladorBuscarCliente(Sistema sistema, nuevaVentanaCita VAC) {
		this.ventanaBuscarCliente = VentanaBuscarCliente.getInstance();
		this.ventanaCita = VAC;
		this.ventanaBuscarCliente.getBtnSeleccionarCliente().addActionListener(p -> seleccionarCliente(p));
		this.sistema = sistema;
	}

	public static ControladorBuscarCliente getInstance(Sistema sistema, nuevaVentanaCita VAC) {
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
		ClienteDTO cliente_seleccionado = this.listaClientes.get(filaSeleccionada);
		if (cliente_seleccionado != null) {
			this.ventanaCita.setCliente(cliente_seleccionado);
			this.ventanaCita.setDatosClienteRegistrado();
			this.ventanaBuscarCliente.cerrar();
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}

