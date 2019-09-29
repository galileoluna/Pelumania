package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import dto.ClienteDTO;
import modelo.Sistema;
import presentacion.vista.VentanaCliente;
import util.Validador;

public class ControladorCliente implements ActionListener {
	
	private VentanaCliente ventanaCliente;
	private Sistema sistema;
	private static ControladorCliente INSTANCE;
	
	private ControladorCliente(Sistema sistema) {
		this.ventanaCliente = VentanaCliente.getInstance();
		this.ventanaCliente.getBtnAgregarCliente().addActionListener(p -> guardarCliente(p));
		this.sistema = sistema;
	}
	
	public static ControladorCliente getInstance(Sistema sistema) {
		if ( INSTANCE == null) {
			INSTANCE = new ControladorCliente(sistema);
		}	
		INSTANCE.ventanaCliente.mostrarVentana();
		return INSTANCE;
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
		if ( Validador.esNombreValido(nombre) &&
			 Validador.esNombreValido(apellido) &&
			 Validador.esTelefono(telefono) &&
			 Validador.esMail(mail)) {
		
		
		ClienteDTO nuevoCliente = new ClienteDTO(0, nombre, apellido, telefono, mail, puntos, estado, deuda);
		this.sistema.agregarCliente(nuevoCliente);

		//		this.refrescarTabla(); // no se que onda aca porque quizas no mostremos la tabla de clientes 
								// como lo hace la agenda, en el menu principal
		this.ventanaCliente.cerrar();
		} else {
		
		this.ventanaCliente.mostrarErrorCampos();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	

}
