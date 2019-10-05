package persistencia.dao.mariadb;

import java.util.List;

import dto.CitaDTO;
import persistencia.dao.interfaz.CitaDAO;

public class CitaDAOSQL implements CitaDAO{

	private static final String insert = "INSERT INTO Cita( "
			+ ") VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String delete = "UPDATE  Cita SET EstadoCita =? WHERE idCita = ?";
	private static final String readall = "SELECT * FROM Cita";
	private static final String update = "UPDATE  Cita SET ____ WHERE idCliente=?";
	private static final String deleteReal = "DELETE FROM Cita WHERE idCita = ?";

	@Override
	public boolean insert(CitaDTO cita) {
		// TODO Auto-generated method stub
		return false;
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
