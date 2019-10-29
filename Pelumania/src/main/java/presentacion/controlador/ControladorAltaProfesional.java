package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import dto.ProfesionalDTO;
import dto.UsuarioDTO;
import modelo.Sistema;
import persistencia.conexion.Conexion;
import presentacion.vista.VentanaAltaProfesional;
import presentacion.vista.VentanaCliente;
import presentacion.vista.VentanaProfesional;

public class ControladorAltaProfesional  implements ActionListener{
	private VentanaAltaProfesional altaProfesional;
	private ControladorProfesional controlProfesional;
	private HashMap<String,Integer>sucursal;
	private Sistema sistema;
	private static ControladorAltaProfesional INSTANCE;
	private static UsuarioDTO usuario;
	
	private ControladorAltaProfesional(Sistema sistema, UsuarioDTO usuar) {
		this.altaProfesional = VentanaAltaProfesional.getInstance();
		
		this.altaProfesional.getBtnAgregar().addActionListener(l -> guardarProfesional(l));
		this.sistema = sistema;
		this.usuario = usuar;
	}
	
	public static ControladorAltaProfesional getInstance(Sistema sistema,UsuarioDTO usuario) {
		if ( INSTANCE == null) {
			INSTANCE = new ControladorAltaProfesional(sistema, usuario);
		}
		INSTANCE.sucursal=getSucursales();
		llenarCombo(INSTANCE.altaProfesional.getComboOrig(),INSTANCE.sucursal);
		llenarCombo(INSTANCE.altaProfesional.getComboTran(),INSTANCE.sucursal);
		INSTANCE.altaProfesional.mostrarVentana();
		return INSTANCE;
	}

	// obtengo los datos ingresados en la pantalla (VentanaAltaHorario) para luego hacer el insert en la tabla
	
	private void guardarProfesional(ActionEvent l) {
		
		String nombre= this.altaProfesional.getTxtNombre().getText();
		String apellido=this.altaProfesional.getTxtApellido().getText();
		Integer idSucursalOrig=getIdCombo(this.altaProfesional.getComboOrig().getSelectedItem().toString());
		Integer idSucursalTran=getIdCombo(this.altaProfesional.getComboTran().getSelectedItem().toString().equals("--")?"-1":this.altaProfesional.getComboTran().getSelectedItem().toString());
		String estado=this.altaProfesional.getEstado().getSelectedItem().toString();
		if( idSucursalOrig == -1) {
			JOptionPane.showMessageDialog(null, "Por favor ingrese una sucursal de origen", "Error", JOptionPane.ERROR_MESSAGE);
		}else {
			if(validar(nombre,apellido,idSucursalOrig,idSucursalTran)) {
				ProfesionalDTO profesional= new ProfesionalDTO(0,nombre,apellido,idSucursalOrig,idSucursalTran,estado);
				this.sistema.agregarProfesional(profesional);
				this.altaProfesional.cerrar();
				// llamo a la instancia del ControladorProfesional para refrescar la tabla 
				controlProfesional.getInstance(sistema,usuario);
			}else {
				JOptionPane.showMessageDialog(null, "Por favor ingrese todos los datos", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}
	
	//obtiene el id de la sucursal para luego hacer el insert en la tabla 
	public Integer getIdCombo(String sucur) {
		for (HashMap.Entry<String, Integer> datos : sucursal.entrySet()) {
			if(datos.getKey().toString().equals(sucur)) {
				return datos.getValue();
			}
		}
		return -1;
		
	}

	// valido que los datos no sean nullos o vacios
	public boolean validar(String nombre,String apellido, Integer sucuOrig, Integer sucuTran) {
		if (nombre == null || nombre.equals("") || apellido == null || apellido.equals("") || sucuOrig == null || sucuTran == null ) {
			return false;
		}else {
			return true;
		}
		
	}
	
	// lleno todo el combo de las sucursales primero lo vacio para que no lo cargue dos veces 
	// recibe el combo y el hashmap que ya posee el nombre e id de las sucursales
	public static void llenarCombo(JComboBox combo, HashMap<String,Integer>sucursales) {
		combo.removeAllItems();
		combo.addItem("--");
		for (HashMap.Entry<String, Integer> datos : sucursales.entrySet()) {
			combo.addItem(datos.getKey().toString());
		}
	}
	
	// obtiene el nombre y id de las  sucursales y las agrega a un hasmap que luego se usa para agregar y modificar
	public static HashMap<String,Integer> getSucursales(){
		PreparedStatement statement;
		ResultSet resultSet;
		Conexion conexion = Conexion.getConexion();
		HashMap<String,Integer>sucursal = new HashMap<String,Integer>();		
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement("SELECT idSucursal,nombreSucursal FROM sucursal;");
			resultSet = statement.executeQuery();
			while (resultSet.next()){
				sucursal.put(resultSet.getString("nombreSucursal"), resultSet.getInt("idSucursal"));
			}
			return sucursal;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return null;
			
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
