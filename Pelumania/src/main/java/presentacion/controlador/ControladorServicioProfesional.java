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
	private List<String> servEnTabla; 
	private List<String> allServ;
	private List<Integer> idServ = new ArrayList<Integer>();
	private VentanaServicioProfecional ventServProf;

	
	private ControladorServicioProfesional(Sistema sistema) {
		this.ventServProf = ventServProf.getInstance();
		this.ventServProf.getBtnAgregar().addActionListener(l -> agregarServ(l));
		this.ventServProf.getBtnBorrar().addActionListener(k -> borrarServ(k));
		this.sistema=sistema;
	}
	

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
	
	private void agregarServ(ActionEvent l) {
		String serv=this.ventServProf.getCombo().getSelectedItem().toString();
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
		this.getInstance(sistema, idProfesional, nombre);
	}
	
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
	
	private static void llenarCombo(JComboBox combo,List<String> lista) {
		combo.removeAllItems();
		for (String  s : lista) {
			combo.addItem(s);
		}
		
	}

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
}
