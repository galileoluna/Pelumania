package persistencia.dao.mariadb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
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

//	private static final String readallIngresos = "SELECT * FROM Caja WHERE tipoMovimiento = ingreso";

//	private static final String readallEgresos = "SELECT * FROM Caja WHERE tipoMovimiento = egreso";

//	private static final String readDayIngresos = "SELECT * FROM Caja WHERE tipoMovimiento = ingreso AND Fecha=?";

//	private static final String readDayEgresos = "SELECT * FROM Caja WHERE tipoMovimiento = egreso AND Fecha=?";


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
	public boolean delete(MovimientoCajaDTO movimiento_a_eliminar) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<MovimientoCajaDTO> readAll() {
		// TODO Auto-generated method stub
		return null;
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
