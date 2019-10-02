package presentacion.controlador;

import java.awt.Component;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;

import dto.ProfesionalDTO;
import modelo.Sistema;
import persistencia.conexion.Conexion;
import presentacion.vista.VentanaProfesional;
import presentacion.vista.VentanaServicioProfecional;

public class ControladorServicioProfesional {
	private static ControladorServicioProfesional INSTANCE;
	private Sistema sistema;
	private String nombre;
	private int idProfesional;
	private int idServicio;
	private List<String> servEnTabla; 
	private List<String> allServ; 
	private VentanaServicioProfecional ventServProf;

	private ControladorServicioProfesional(Sistema sistema) {
		this.ventServProf = ventServProf.getInstance();
		this.sistema=sistema;
	}
	
	public static ControladorServicioProfesional getInstance(Sistema sistema, int idProf, String nombre) {
		if ( INSTANCE == null) {
			INSTANCE = new ControladorServicioProfesional(sistema);
		}
	
		INSTANCE.ventServProf.setNombreEmpl(nombre);
		INSTANCE.idProfesional=idProf;
		INSTANCE.servEnTabla=llenarLista(idProf);
		INSTANCE.allServ=llenarServ();
		llenarCombo(INSTANCE.ventServProf.getCombo(),INSTANCE.allServ);
		INSTANCE.ventServProf.llenarTabla(INSTANCE.servEnTabla);
		INSTANCE.ventServProf.show();
		return INSTANCE;
	}
	
	
	private static List<String> llenarLista(int id) {
		PreparedStatement statement;
		ResultSet resultSet;
		Conexion conexion = Conexion.getConexion();
		List<String> servicio = new ArrayList<String>();
		try 
		{
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
	
	private static List<String> llenarServ() {
		PreparedStatement statement;
		ResultSet resultSet;
		Conexion conexion = Conexion.getConexion();
		List<String> servicio = new ArrayList<String>();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement("SELECT nombre FROM servicio;");
			resultSet = statement.executeQuery();
			while (resultSet.next()){
				servicio.add(resultSet.getString("nombre"));
			}
			return servicio;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return null;
			
	}
	
	private static void llenarCombo(JComboBox combo,List<String> lista) {
		combo.removeAllItems();
		for (String  s : lista) {
			combo.addItem(s);
		}
		
	}


}
