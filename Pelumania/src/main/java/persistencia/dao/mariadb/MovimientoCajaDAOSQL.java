package persistencia.dao.mariadb;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import dto.MovimientoCajaDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.MovimientoCajaDAO;

public class MovimientoCajaDAOSQL implements MovimientoCajaDAO {
	
	private static final String insert = "INSERT INTO Caja (idCaja, idSucursal, Categoria, Fecha, Descripcion, "
			+ "TipoMovimiento, TipoDeCambio, idPromocion, PrecioLocal, PrecioDolar, idCita, idCliente, idProfesional	 ) "
			+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	private static final String delete = "UPDATE  Caja SET EstadoCliente=? WHERE idCaja= ?";
	private static final String readallIngresos = "SELECT * FROM Caja WHERE tipoMovimiento = ingreso";
	private static final String readallEgresos = "SELECT * FROM Caja WHERE tipoMovimiento = egreso";
	private static final String readDayIngresos = "SELECT * FROM Caja WHERE tipoMovimiento = ingreso AND Fecha=?";
	private static final String readDayEgresos = "SELECT * FROM Caja WHERE tipoMovimiento = egreso AND Fecha=?";

//	private static final String update = "UPDATE  Ingreso SET Nombre=? , Apellido=? , Telefono=? , Mail=? , Puntos=? , EstadoCliente=?, Deuda=? WHERE idCliente=?";
	private static final String deleteReal = "DELETE FROM Ingreso WHERE idIngreso = ?";

	//no damos la opcion de "dar de baja" un ingreso ya que 
	// seria como cancelar un pago
	
	@Override
	public boolean insert(MovimientoCajaDTO movimiento) {
		
			PreparedStatement statement;
			Connection conexion = Conexion.getConexion().getSQLConexion();
			boolean isInsertExitoso = false;
//			if(esClienteValido(cliente)) {
			try
			{
				statement = conexion.prepareStatement(insert);
				statement.setInt(1, movimiento.getIdCaja());
				statement.setInt (2, movimiento.getIdSucursal());
				statement.setString(3, movimiento.getCategoria());
				statement.setDate(4, Date.valueOf(movimiento.getFecha().toString()));
				statement.setString(5, movimiento.getDescripcion());
				statement.setString(6, movimiento.getTipoMovimiento());
				statement.setString(7, movimiento.getTipoCambio());
				statement.setInt(8, movimiento.getIdPromocion());
				statement.setBigDecimal(9, movimiento.getPrecioLocal());
				statement.setBigDecimal(10, movimiento.getPrecioDolar());
				statement.setInt(11, movimiento.getIdCita());
				statement.setInt(12, movimiento.getIdCliente());
				statement.setInt(13, movimiento.getIdProfesional());

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
//		}

	@Override
	public boolean update(MovimientoCajaDTO movimiento_a_actualizar) {
		// TODO Auto-generated method stub
		return false;
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

}