package persistencia.dao.mariadb;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dto.ClienteDTO;
import dto.MovimientoCajaDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.MovimientoCajaDAO;

public class MovimientoCajaDAOSQL implements MovimientoCajaDAO {
	
	
	private static final String insertEgreso = "INSERT INTO Caja (idCaja, idSucursal, idCategoriaCaja, Descripcion,"
			+ " TipoDeCambio, PrecioLocal, PrecioDolar) "
			+ "VALUES(?, ?, ?, ?, ?, ?, ?)";
	
	private static final String delete = "UPDATE  Caja SET EstadoCliente=? WHERE idCaja= ?";

	private static final String insertIngresoServicio = "INSERT INTO Caja (idCaja, idSucursal, idCategoriaCaja,"
			+ " TipoDeCambio, idPromocion, PrecioLocal, PrecioDolar, idCita, idCliente, idProfesional, idServicio)" 
			+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
	
	private static final String insertIngresoServicioSinPromocion = "INSERT INTO Caja (idCaja, idSucursal, idCategoriaCaja,"
			+ " TipoDeCambio, PrecioLocal, PrecioDolar, idCita, idCliente, idProfesional, idServicio)" 
			+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
	
	private static final String deleteReal = "DELETE FROM Ingreso WHERE idIngreso = ?";

	private static final String insertIngresoProducto = "INSERT INTO Caja (idCaja, idSucursal, idCategoriaCaja,"
			+ " TipoDeCambio, PrecioLocal, PrecioDolar)" 
			+ " VALUES(?, ?, ?, ?, ?, ?)";
	
//	private static final String insert = "INSERT INTO Caja (idCaja, idSucursal, idCategoriaCaja, Descripcion, "
//			+ "TipoDeCambio, idPromocion, PrecioLocal, PrecioDolar, idCita, idCliente, idProfesional) "
//			+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	private static final String readDayIngresos = "SELECT * FROM Caja,CategoriaCaja WHERE CategoriaCaja.tipoMovimiento = ingreso and Caja.idCategoriaCaja=CategoriaCaja.idCategoriaCaja and Fecha>? and Fecha<?";

	private static final String readDayEgresos = "SELECT * FROM Caja,CategoriaCaja WHERE CategoriaCaja.tipoMovimiento = egreso and Caja.idCategoriaCaja=CategoriaCaja.idCategoriaCaja and Fecha>? and Fecha<?";

	private static final String readDayIngresosSucursal = "SELECT * FROM Caja,CategoriaCaja WHERE CategoriaCaja.tipoMovimiento = ingreso and Caja.idCategoriaCaja=CategoriaCaja.idCategoriaCaja and Fecha>? and Fecha<? and Caja.idSucursal=?";

	private static final String readDayIngresosPorCliente = "SELECT * FROM Caja,CategoriaCaja WHERE CategoriaCaja.tipoMovimiento = ingreso and Caja.idCategoriaCaja=CategoriaCaja.idCategoriaCaja and Fecha>? and Fecha<? and Caja.idCliente=?";

	private static final String readDayIngresosPorProfesional = "SELECT * FROM Caja,CategoriaCaja WHERE CategoriaCaja.tipoMovimiento = ingreso and Caja.idCategoriaCaja=CategoriaCaja.idCategoriaCaja and Fecha>? and Fecha<? and Caja.idProfesional=?";

	private static final String readDayIngresosPorServicio = "SELECT * FROM Caja,CategoriaCaja WHERE CategoriaCaja.tipoMovimiento = ingreso and Caja.idCategoriaCaja=CategoriaCaja.idCategoriaCaja and Fecha>? and Fecha<? and Caja.idServicio=?";
	
	//no damos la opcion de "dar de baja" un ingreso ya que 
	// seria como cancelar un pago
	
	@Override
	public boolean insertEgreso(MovimientoCajaDTO movimiento) {
		
			PreparedStatement statement;
			Connection conexion = Conexion.getConexion().getSQLConexion();
			boolean isInsertExitoso = false;
			try
			{
				statement = conexion.prepareStatement(insertEgreso);
				statement.setInt(1, movimiento.getIdCaja());
				statement.setInt (2, movimiento.getIdSucursal());
				statement.setInt(3, movimiento.getCategoria());
				statement.setString(4, movimiento.getDescripcion());
				statement.setString(5, movimiento.getTipoCambio());
				statement.setBigDecimal(6, movimiento.getPrecioLocal());
				statement.setBigDecimal(7, movimiento.getPrecioDolar());

				if(statement.executeUpdate() > 0)
				{
					conexion.commit();
					isInsertExitoso = true;
				}
			}
			catch (SQLException e)
			{
				e.printStackTrace();
				try {
					conexion.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			return isInsertExitoso;
		  }



	@Override
	public List<MovimientoCajaDTO> readDayIngresos(String fecha,String fecha2) {
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<MovimientoCajaDTO> caja = new ArrayList<MovimientoCajaDTO>();
		Conexion conexion = Conexion.getConexion();
		try
		{
			statement = conexion.getSQLConexion().prepareStatement(readDayIngresos);
			statement.setString(1, fecha);
			statement.setString(2, fecha2);

			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				caja.add(getCajaDTO(resultSet));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return caja;
	}
	
	@Override
	public List<MovimientoCajaDTO>  readDayEgresos(String fecha,String fecha2) {
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<MovimientoCajaDTO> caja = new ArrayList<MovimientoCajaDTO>();
		Conexion conexion = Conexion.getConexion();
		try
		{
			statement = conexion.getSQLConexion().prepareStatement(readDayEgresos);
			statement.setString(1, fecha);
			statement.setString(2, fecha2);
			
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				caja.add(getCajaDTO(resultSet));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return caja;
	}
	
	@Override
	public List<MovimientoCajaDTO> readDayIngresosSucursal(String fecha,String fecha2,int sucursal) {
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<MovimientoCajaDTO> caja = new ArrayList<MovimientoCajaDTO>();
		Conexion conexion = Conexion.getConexion();
		try
		{
			statement = conexion.getSQLConexion().prepareStatement(readDayIngresosSucursal);
			statement.setString(1, fecha);
			statement.setString(2, fecha2);
			statement.setInt(3, sucursal);

			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				caja.add(getCajaDTO(resultSet));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return caja;
	}
	
	@Override
	public List<MovimientoCajaDTO> readDayIngresosCliente(String fecha,String fecha2,int cliente) {
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<MovimientoCajaDTO> caja = new ArrayList<MovimientoCajaDTO>();
		Conexion conexion = Conexion.getConexion();
		try
		{
			statement = conexion.getSQLConexion().prepareStatement(readDayIngresosPorCliente);
			statement.setString(1, fecha);
			statement.setString(2, fecha2);
			statement.setInt(3, cliente);

			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				caja.add(getCajaDTO(resultSet));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return caja;
	}

	@Override
	public List<MovimientoCajaDTO> readDayIngresosProfesional(String fecha,String fecha2,int profesional) {
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<MovimientoCajaDTO> caja = new ArrayList<MovimientoCajaDTO>();
		Conexion conexion = Conexion.getConexion();
		try
		{
			statement = conexion.getSQLConexion().prepareStatement(readDayIngresosPorProfesional);
			statement.setString(1, fecha);
			statement.setString(2, fecha2);
			statement.setInt(3, profesional);

			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				caja.add(getCajaDTO(resultSet));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return caja;
	}

	@Override
	public List<MovimientoCajaDTO> readDayIngresosServicio(String fecha,String fecha2,int servicio) {
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<MovimientoCajaDTO> caja = new ArrayList<MovimientoCajaDTO>();
		Conexion conexion = Conexion.getConexion();
		try
		{
			statement = conexion.getSQLConexion().prepareStatement(readDayIngresosPorServicio);
			statement.setString(1, fecha);
			statement.setString(2, fecha2);
			statement.setInt(3, servicio);

			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				caja.add(getCajaDTO(resultSet));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return caja;
	}
	
	private MovimientoCajaDTO getCajaDTO(ResultSet resultSet) throws SQLException
	{
		int idCaja = resultSet.getInt("idCaja");
		int idSucursal = resultSet.getInt("idSucursal");
		int idCategoriaCaja = resultSet.getInt("idCategoriaCaja");
		Instant fecha=Instant.parse(resultSet.getString("Fecha"));
		String TipoDeCambio= resultSet.getString("TipoDeCambio");
		int idPromocion = resultSet.getInt("idPromocion");
		BigDecimal local=resultSet.getBigDecimal("precioLocal");
		BigDecimal dolar=resultSet.getBigDecimal("precioDolar");
		int idProf = resultSet.getInt("idCaja");
		int idCita = resultSet.getInt("idCita");
		int idCliente = resultSet.getInt("idCliente");
		int idServicio = resultSet.getInt("idServicio");


		return new MovimientoCajaDTO(idCaja,idSucursal,idCategoriaCaja,fecha,TipoDeCambio,idPromocion,
				local,dolar,idProf,idCita,idCliente,idServicio);
	}

	@Override
	public boolean deleteReal(MovimientoCajaDTO movimiento_a_eliminar) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isDeleteExitoso = false;
		try {
			statement = conexion.prepareStatement(deleteReal); 
			statement.setInt(1, movimiento_a_eliminar.getIdCaja());
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return isDeleteExitoso;
	}
	
	@Override
	public boolean insertIngresoServicioSinPromocion(MovimientoCajaDTO movimiento) {
		
			PreparedStatement statement;
			Connection conexion = Conexion.getConexion().getSQLConexion();
			boolean isInsertExitoso = false;
			try
			{
				//la fecha se autogenera pero si la necesito en el DTO
				statement = conexion.prepareStatement(insertIngresoServicioSinPromocion);
				statement.setInt(1, movimiento.getIdCaja());
				statement.setInt (2, movimiento.getIdSucursal());
				statement.setInt(3, movimiento.getCategoria());
				statement.setString(4, movimiento.getTipoCambio());
				statement.setBigDecimal(5, movimiento.getPrecioLocal());
				statement.setBigDecimal(6, movimiento.getPrecioDolar());
				statement.setInt(7, movimiento.getIdCita());
				statement.setInt(8, movimiento.getIdCliente());
				statement.setInt(9, movimiento.getIdProfesional());
				statement.setInt(10, movimiento.getIdServicio());


				if(statement.executeUpdate() > 0)
				{
					conexion.commit();
					isInsertExitoso = true;
				}
			}
			catch (SQLException e)
			{
				e.printStackTrace();
				try {
					conexion.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			return isInsertExitoso;
		  }
	
	@Override
	public boolean insertIngresoProducto(MovimientoCajaDTO movimiento_a_insertar) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try
		{
			statement = conexion.prepareStatement(insertIngresoProducto);
			statement.setInt(1, movimiento_a_insertar.getIdCaja());
			statement.setInt (2, movimiento_a_insertar.getIdSucursal());
			statement.setInt(3, movimiento_a_insertar.getCategoria());
			statement.setString(4, movimiento_a_insertar.getTipoCambio());
			statement.setBigDecimal(5, movimiento_a_insertar.getPrecioLocal());
			statement.setBigDecimal(6, movimiento_a_insertar.getPrecioDolar());

			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				isInsertExitoso = true;
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return isInsertExitoso;
	}

}
