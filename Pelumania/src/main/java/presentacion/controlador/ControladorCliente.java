package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.List;

import javax.swing.table.TableModel;

import dto.ClienteDTO;
import modelo.Sistema;
import presentacion.vista.VentanaCliente;
import util.Validador;

public class ControladorCliente implements ActionListener {

	private VentanaCliente ventanaCliente;
	private Sistema sistema;
	private List<ClienteDTO> listaClientes;
	private List<CitaDTO> listaCitas;
	private static ControladorCliente INSTANCE;

	private ControladorCliente(Sistema sistema) {
		this.ventanaCliente = VentanaCliente.getInstance();
		this.ventanaCliente.getBtnAgregar().addActionListener(p -> guardarCliente(p));
		this.ventanaCliente.getBtnBorrar().addActionListener(p-> borrarCliente(p));
		this.ventanaCliente.getBtnEditar().addActionListener(p-> editarCliente(p));
		this.ventanaCliente.getBtnBuscar().addActionListener(y -> buscar(y));
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


	private void guardarCliente(ActionEvent p) {


		String nombre = this.ventanaCliente.getTxtNombre().getText();
		String apellido = this.ventanaCliente.getTxtApellido().getText();
		String telefono = this.ventanaCliente.getTxtTelefono().getText();
		String mail = this.ventanaCliente.getTxtMail().getText();
		int puntos = 0 ; // por defecto al dar de alta no tiene puntos
		String estado = "Activo";
		BigDecimal deudaPesos =  new BigDecimal(0);//tampoco tendra deuda al ser dado de alta
		BigDecimal deudaDolar =  new BigDecimal(0);
		
		//validamos campos
		if ( Validador.esNombreConEspaciosValido(nombre) &&
				Validador.esNombreConEspaciosValido(apellido) &&
				Validador.esTelefono(telefono) &&
				Validador.esMail(mail)) {


			ClienteDTO nuevoCliente = new ClienteDTO(0, nombre, apellido, telefono, mail, puntos, estado, deudaPesos,deudaDolar);

			this.sistema.agregarCliente(nuevoCliente);
			this.listaClientes = this.sistema.obtenerClientes();
			this.ventanaCliente.llenarTabla(listaClientes);
			this.ventanaCliente.limpiarInputs();
			INSTANCE.ventanaCliente.mostrarExitoAlta();

		} else {

			this.ventanaCliente.mostrarErrorCamposInvalidos();
		}
		

	}

		private void borrarCliente(ActionEvent p) {

		this.listaClientes = this.sistema.obtenerClientes();
		int[] filasSeleccionadas = this.ventanaCliente.getTablaClientes().getSelectedRows();
		listaCitas=sistema.obtenerCitas();
		
		for (int fila : filasSeleccionadas)
		{
			if(listaClientes.get(fila)!=null) {
				if(!listaClientes.get(fila).getEstadoCliente().equalsIgnoreCase("Activo"))
					this.ventanaCliente.mostrarErrorClienteNoActivo();
				else {
					boolean banderaEnCurso=false;
					for(int i=0;i<listaCitas.size();i++) {
						if(listaCitas.get(i).estaEnCurso() && listaCitas.get(i).getIdCliente()==listaClientes.get(fila).getIdCliente())
							banderaEnCurso=true;
					}
					if(banderaEnCurso)this.ventanaCliente.mostrarErrorClienteConCitaEnCurso();	
					else {	
						int confirm = this.ventanaCliente.mostrarConfirmacionBorrar();
						if (confirm == 0) 
							this.sistema.borrarCliente((listaClientes.get(fila)));
					}
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
			String deudaPesos = tabla.getValueAt(filaSeleccionada, 6).toString();
			String deudaDolar = tabla.getValueAt(filaSeleccionada, 7).toString();

			if ( Validador.esNombreConEspaciosValido(nombre) &&
					Validador.esNombreConEspaciosValido(apellido) &&
					Validador.esTelefono(telefono) &&
					Validador.esMail(mail) &&
					Validador.esPuntosValido( puntos) &&
					Validador.esEstadoClienteValido(estado) &&
					Validador.esPrecioValido(deudaPesos)&&
					Validador.esPrecioValido(deudaDolar)){
				
				BigDecimal deudaAuxPesos=new BigDecimal(deudaPesos.replaceAll(",", ""));
				BigDecimal deudaAuxDolar=new BigDecimal(deudaDolar.replaceAll(",", ""));
		
				if(deudaAuxPesos.compareTo(cliente_seleccionado.getDeudaPesos())!=0 ||
						deudaAuxDolar.compareTo(cliente_seleccionado.getDeudaDolar())!=0 ){
					INSTANCE.ventanaCliente.mostrarErrorEdicionDeuda();
				}
				else if((cliente_seleccionado.getEstadoCliente().equalsIgnoreCase("moroso") && estado.equalsIgnoreCase("activo")) ||
						(cliente_seleccionado.getEstadoCliente().equalsIgnoreCase("Activo") && estado.equalsIgnoreCase("moroso")) ||
						(cliente_seleccionado.getEstadoCliente().equalsIgnoreCase("moroso") && estado.equalsIgnoreCase("vip")) ||
						(cliente_seleccionado.getEstadoCliente().equalsIgnoreCase("moroso") && estado.equalsIgnoreCase("inactivo")) ||
						(cliente_seleccionado.getEstadoCliente().equalsIgnoreCase("inactivo") && estado.equalsIgnoreCase("moroso"))||
						(cliente_seleccionado.getEstadoCliente().equalsIgnoreCase("vip") && estado.equalsIgnoreCase("moroso"))){
					estado=cliente_seleccionado.getEstadoCliente();
					INSTANCE.ventanaCliente.mostrarErrorEdicionEstado();
				}else {
					ClienteDTO cliente_a_modifcar = new ClienteDTO(id, nombre, apellido, telefono, mail, Integer.parseInt(puntos), estado, cliente_seleccionado.getDeudaPesos(),cliente_seleccionado.getDeudaDolar());
	
					INSTANCE.sistema.editarCliente(cliente_a_modifcar);
					INSTANCE.ventanaCliente.mostrarExitoEditar();
				}
			} else {
				INSTANCE.ventanaCliente.mostrarErrorCamposInvalidos();
			}

		} else {
			INSTANCE.ventanaCliente.mostrarErrorSinSeleccionar();
		}
		
		INSTANCE.ventanaCliente.llenarTabla(INSTANCE.sistema.obtenerClientes());

	}
	
	private void buscar(ActionEvent y) {
		String variable=this.ventanaCliente.getVariableBuscar().getSelectedItem().toString();
		String value=this.ventanaCliente.getBuscador().getText();
		this.ventanaCliente.llenarTabla(this.sistema.obtenerClienteConBuscador(variable,value));
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}



}