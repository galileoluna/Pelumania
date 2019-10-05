package presentacion.controlador;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import dto.ProfesionalDTO;
import modelo.Sistema;
import persistencia.conexion.Conexion;
import presentacion.vista.VentanaProfesional;
import presentacion.vista.VentanaServicioProfesional;

public class ControladorServicioProfesional {
	private static ControladorServicioProfesional INSTANCE;
	private Sistema sistema;
	private String nombre;
	private int idProfesional;
	private List<String> servEnTabla; 
	private List<String> allServ;
	private List<Integer> idServ = new ArrayList<Integer>();
	private VentanaServicioProfesional ventServProf;

	
	private ControladorServicioProfesional(Sistema sistema) {
		this.ventServProf = ventServProf.getInstance();
		this.ventServProf.getBtnAgregar().addActionListener(l -> agregarServ(l));
		this.ventServProf.getBtnBorrar().addActionListener(k -> borrarServ(k));
		this.sistema=sistema;
	}
	
	// Instancia de la pantalla que muestra la lista de servicios asociados a un profesional
	// Recibe el sistema, el id del profesional y el nombre del empleado
	
	public static ControladorServicioProfesional getInstance(Sistema sistema, int idProf, String nombre) {
		if ( INSTANCE == null) {
			INSTANCE = new ControladorServicioProfesional(sistema);
		}
	
		INSTANCE.ventServProf.setNombreEmpl(nombre);
		INSTANCE.idProfesional=idProf;
		INSTANCE.servEnTabla=llenarLista(idProf);
		INSTANCE.allServ=llenarServ(INSTANCE.idServ);
		llenarCombo(INSTANCE.ventServProf.getCombo(),INSTANCE.allServ);
		INSTANCE.ventServProf.llenarTabla(INSTANCE.servEnTabla);
		INSTANCE.ventServProf.show();
		return INSTANCE;
	}
	// Obtiene el campo seleccionado de la pantalla (VentanaServicioProfesional) y hace el insert en la tabla
	// es villero pero fue lo que se me ocurrio para no hacer clases que tengan tres lineas
	private void agregarServ(ActionEvent l) {
		String serv=this.ventServProf.getCombo().getSelectedItem().toString();
		if(validar(serv)) {
			PreparedStatement statement;
			Connection conexion = Conexion.getConexion().getSQLConexion();
			boolean isInsertExitoso = false;
			
			try{
				statement = conexion.prepareStatement("INSERT INTO servicioprofesional (idServicio,idProfesional) VALUES (?,?) ");
				statement.setInt(1, idServ.get(allServ.indexOf(serv)) );
				statement.setInt(2, idProfesional);
				if(statement.executeUpdate() > 0){
					conexion.commit();
					isInsertExitoso = true;
				}
			} 
			catch (SQLException e) {
				e.printStackTrace();
				try {
					conexion.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			// llamos  a la instancia para que vuelva a cargar la tabla con el servicio nuevo
			this.getInstance(sistema, idProfesional, nombre);
		}else {
			JOptionPane.showMessageDialog(null, "El profesional ya tiene relacionado el servicio que intenta asociar", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	//borra el servicio seleccionado, el delete lo voy a dejar porque solo borra una relacion creo que no afecta en nada si hay citas asociado a un profesional y ya no realiza este servicio tendriamos que definir que hacer
	private void borrarServ(ActionEvent k) {
		int[] filasSeleccionadas = this.ventServProf.getTablServicioProfesional().getSelectedRows();
       
    	for (int fila : filasSeleccionadas)
    	{
        	if(this.servEnTabla.get(fila)!=null) {
        		delete(idServ.get(allServ.indexOf(this.servEnTabla.get(fila))),idProfesional);
        	}
		}
    	this.getInstance(sistema, idProfesional, nombre);
	}
	
	// llena la lista de los servicios asociados a un cliente 
	private static List<String> llenarLista(int id) {
		PreparedStatement statement;
		ResultSet resultSet;
		Conexion conexion = Conexion.getConexion();
		List<String> servicio = new ArrayList<String>();
		try 
		{
			// que buena query 
			statement = conexion.getSQLConexion().prepareStatement("SELECT s.Nombre FROM servicioprofesional sp JOIN profesional p USING(idProfesional) JOIN servicio s USING (idServicio) WHERE p.IdProfesional=?");
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			while (resultSet.next()){
				servicio.add(resultSet.getString("s.Nombre"));
			}
			return servicio;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return null;
			
	}
	// esta funcion hace dos cosas llena la lista con los servicios que se van a cargar en el desplegable y ademas llena una segunda lista con los indices(idServ) de dicha tabla para luego poder hacer mas facil los insert y delete
	// la lista que recibe por parametros el la lista de los id (idServ), devuelve la lista de nombres de los servicios
	private static List<String> llenarServ(List<Integer> id) {
		PreparedStatement statement;
		ResultSet resultSet;
		Conexion conexion = Conexion.getConexion();
		List<String> servicio = new ArrayList<String>();		
		
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement("SELECT nombre, IdServicio FROM servicio;");
			resultSet = statement.executeQuery();
			while (resultSet.next()){
				servicio.add(resultSet.getString("nombre"));
				id.add(resultSet.getInt("IdServicio"));
			}
			return servicio;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return null;
			
	}
	// llena el combo con la lista que fue cargada anteriormente
	//recibe el combo y la lista
	private static void llenarCombo(JComboBox combo,List<String> lista) {
		combo.removeAllItems();
		for (String  s : lista) {
			combo.addItem(s);
		}
		
	}
	// borra dde la tabla la relacion seleccionada
	// recibe los id del servicio y del profesional
	private void delete(int idServicio, int idProf) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		try {
			statement = conexion.prepareStatement("DELETE FROM servicioprofesional WHERE idServicio = ? AND idProfesional = ?");
			statement.setInt(1, idServicio);
			statement.setInt(2, idProf);
			if(statement.executeUpdate() > 0){
				conexion.commit();
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private boolean validar(String serv) {
		int encontro=0;
		for(String s : servEnTabla) {
			if(s.equals(serv)) {
				encontro++;
			}
		}
		if(encontro==0) {
			return true;
		}else {
			return false;
	
		}
	}
}
