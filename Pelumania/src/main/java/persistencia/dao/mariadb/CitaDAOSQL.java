package persistencia.dao.mariadb;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;

import dto.CitaDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.CitaDAO;

public class CitaDAOSQL implements CitaDAO{

	private static final String insert = "INSERT INTO Cita( idCita, idUsuario, idCliente,"
			+ "NombreCliente, ApellidoCliente, EstadoTurno, IdProfesional, IdServicio,"
			+ "PrecioLocal, PrecioDolar, Hora, Dia, IdSucursal) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String delete = "UPDATE  Cita SET EstadoCita =? WHERE idCita = ?";
	private static final String readall = "SELECT * FROM Cita";
	private static final String update = "UPDATE  Cita SET ____ WHERE idCliente=?";
	private static final String deleteReal = "DELETE FROM Cita WHERE idCita = ?";

	@Override
	public boolean insert(CitaDTO cita) {
		{
			PreparedStatement statement;
			Connection conexion = Conexion.getConexion().getSQLConexion();
			boolean isInsertExitoso = false;
			try
			{
				statement = conexion.prepareStatement(insert);
				statement.setInt	    (1, cita.getIdCita());
				statement.setInt	    (2, cita.getIdUsuario());
				statement.setInt	    (3, cita.getIdCliente());
				statement.setString     (4, cita.getNombre());
				statement.setString     (5, cita.getApellido());
				statement.setString     (6, cita.getEstado());
				statement.setInt        (7, cita.getIdProfesional());
				statement.setInt        (8, cita.getIdServicio());
				statement.setBigDecimal (9, cita.getPrecioLocal());
				statement.setBigDecimal (10, cita.getPrecioDolar());
				statement.setTime       (11, Time.valueOf(cita.getHoraInicio()));
				statement.setTime       (12, Time.valueOf(cita.getHoraFin()));
				statement.setDate       (13, Date.valueOf(cita.getFecha()));
				statement.setInt        (14, cita.getIdSucursal());

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
	@Override
	public boolean delete(CitaDTO cita_a_eliminar) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean update(CitaDTO cita_a_eliminar) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public List<CitaDTO> readAll() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean deleteReal(CitaDTO cita_a_eliminar) {
		// TODO Auto-generated method stub
		return false;
	}

}
