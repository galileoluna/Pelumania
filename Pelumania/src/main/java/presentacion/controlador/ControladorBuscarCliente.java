package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import dto.ClienteDTO;
import modelo.Sistema;
import presentacion.vista.VentanaAgregarCita;
import presentacion.vista.VentanaBuscarCliente;
import presentacion.vista.nuevaVentanaCita;

public class ControladorBuscarCliente implements ActionListener{
	
	private VentanaBuscarCliente ventanaBuscarCliente;
	private Sistema sistema;
	private List<ClienteDTO> listaClientes;
	private static ControladorBuscarCliente INSTANCE;
	private static nuevaVentanaCita ventanaCita;
	private static VentanaAgregarCita ventanaViejaCita;

	private ControladorBuscarCliente(Sistema sistema, nuevaVentanaCita VAC) {
		this.ventanaBuscarCliente = VentanaBuscarCliente.getInstance();
		this.ventanaCita = VAC;
		this.ventanaBuscarCliente.getBtnSeleccionarCliente().addActionListener(p -> seleccionarCliente(p));
//		this.ventanaBuscarCliente.getBtnSeleccionarCliente().addActionListener(p -> seleccionarClienteViejo(p));
		this.sistema = sistema;
	}

	public static ControladorBuscarCliente getInstance(Sistema sistema, nuevaVentanaCita VAC) {
		if ( INSTANCE == null) {
			INSTANCE = new ControladorBuscarCliente(sistema, VAC);
		}
		inicializarDatos();
		return INSTANCE;
	}
	
	private ControladorBuscarCliente(Sistema sistema, VentanaAgregarCita VAC) {
		this.ventanaBuscarCliente = VentanaBuscarCliente.getInstance();
		this.ventanaViejaCita = VAC;
		this.ventanaBuscarCliente.getBtnSeleccionarCliente().addActionListener(p -> seleccionarClienteViejo(p));
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

		List<ClienteDTO> listaClientesAux = INSTANCE.sistema.obtenerClientes();
		List<ClienteDTO> listaClientes=new ArrayList<ClienteDTO>();
		
		for(int i=0; i<listaClientesAux.size();i++) {
			if(!listaClientesAux.get(i).getEstadoCliente().equalsIgnoreCase("inactivo"))
				listaClientes.add(listaClientesAux.get(i));
		}
		
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
	
	private void seleccionarClienteViejo(ActionEvent p) {
		this.listaClientes = INSTANCE.sistema.obtenerClientes();
		int filaSeleccionada = this.ventanaBuscarCliente.getTablaClientes().getSelectedRow();
		ClienteDTO cliente_seleccionado = this.listaClientes.get(filaSeleccionada);
		if (cliente_seleccionado != null) {
		String nombre = cliente_seleccionado.getNombre();
		String apellido = cliente_seleccionado.getApellido();
		int id = cliente_seleccionado.getIdCliente();
		
		this.ventanaViejaCita.getTxtNombre().setText(nombre);
		this.ventanaViejaCita.getTxtNombre().setEditable(false);
		this.ventanaViejaCita.getTxtApellido().setText(apellido);
		this.ventanaViejaCita.getTxtApellido().setEditable(false);
		this.ventanaViejaCita.setIdCliente(id);

		}
		this.ventanaBuscarCliente.cerrar();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub		
	}
}