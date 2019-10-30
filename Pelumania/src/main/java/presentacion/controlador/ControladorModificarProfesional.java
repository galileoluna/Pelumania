package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import dto.ProfesionalDTO;
import dto.UsuarioDTO;
import modelo.Sistema;
import persistencia.conexion.Conexion;
import presentacion.vista.VentanaModificarProfesional;

public class ControladorModificarProfesional implements ActionListener {
	private VentanaModificarProfesional modificarProfesional;
	private ControladorProfesional controlProfesional;
	private static HashMap<String,Integer>sucursal;
	private static int idProfesional;
	private Sistema sistema;
	private static UsuarioDTO usuario;
	private static ControladorModificarProfesional INSTANCE;
	
	private ControladorModificarProfesional(Sistema sistema, UsuarioDTO usuar) {
		this.modificarProfesional = VentanaModificarProfesional.getInstance();
		this.modificarProfesional.getBtnAgregar().addActionListener(l -> guardarProfesional(l));
		this.sistema = sistema;
		this.usuario = usuar;
	}
	
	// inicializo la instancia de la pantalla que modifica al profesional (VentanaModificarProfesional)
	// recibe la lista del profesional seleccionado para editar, el sistema, y el id del profesional 
	public static ControladorModificarProfesional getInstance(Sistema sistema, List<ProfesionalDTO> profesional,int id,UsuarioDTO usuario) {
		if ( INSTANCE == null) {
			INSTANCE = new ControladorModificarProfesional(sistema,usuario);
		}
		INSTANCE.sucursal=getSucursales();
		llenarCombo(INSTANCE.modificarProfesional.getComboOrig(),INSTANCE.sucursal);
		llenarCombo(INSTANCE.modificarProfesional.getComboTran(),INSTANCE.sucursal);
		for (ProfesionalDTO p : profesional) {
			idProfesional=id;
			INSTANCE.modificarProfesional.getTxtNombre().setText(p.nombre);
			INSTANCE.modificarProfesional.getTxtApellido().setText(p.getApellido());
			INSTANCE.modificarProfesional.getComboOrig().setSelectedItem(getNombreSucursal(p.getIdSucursalOrigen()));
			INSTANCE.modificarProfesional.getComboTran().setSelectedItem(getNombreSucursal(p.getIdSucursalTransferencia()));
			INSTANCE.modificarProfesional.getEstado().setSelectedItem(p.getEstado());
		}
		
		INSTANCE.modificarProfesional.mostrarVentana();
		return INSTANCE;
	}
	
	// obtiene todos los campos de la ventana (VentanaModificarProfesional) para luego updatear la tabla 
	
	private void guardarProfesional(ActionEvent l) {
		String nombre= this.modificarProfesional.getTxtNombre().getText();
		String apellido=this.modificarProfesional.getTxtApellido().getText();
		Integer idSucursalOrig=getIdCombo(this.modificarProfesional.getComboOrig().getSelectedItem().toString());
		Integer idSucursalTran=getIdCombo(this.modificarProfesional.getComboTran().getSelectedItem().toString().equals("--")?"-1":this.modificarProfesional.getComboTran().getSelectedItem().toString());
		String estado= this.modificarProfesional.getEstado().getSelectedItem().toString();
		if(validar(nombre,apellido,idSucursalOrig,idSucursalTran)) {
			ProfesionalDTO profesional= new ProfesionalDTO(this.idProfesional,nombre,apellido,idSucursalOrig,idSucursalTran,estado);
			this.sistema.actualizarProfesional(profesional);
			this.modificarProfesional.cerrar();
			//llama a la instancia del controladorProfesional asi se refresca la tabla 
			controlProfesional.getInstance(sistema,usuario);
		}else {
			JOptionPane.showMessageDialog(null, "Por favor ingrese todos los datos", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	// Valida que los campos ingresados no sean nulos o esten vacios	
	public boolean validar(String nombre,String apellido, Integer sucuOrig, Integer sucuTran) {
		if (nombre == null || nombre.equals("") || apellido == null || apellido.equals("") || sucuOrig == null || sucuTran == null ) {
			
			return false;
		}else {
			return true;
		}
		
	}
	
	//obtiene el nombre de la sucursal para luego Cargar el combo 
	private static String getNombreSucursal(int idSucursalOrigen) {
		for (HashMap.Entry<String, Integer> datos : sucursal.entrySet()) {
			if(datos.getValue()==idSucursalOrigen) {
				return datos.getKey();
			}
		}
		return "";
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
