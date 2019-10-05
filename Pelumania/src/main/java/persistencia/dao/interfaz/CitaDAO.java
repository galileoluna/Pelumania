package persistencia.dao.interfaz;

import java.util.List;

import dto.CitaDTO;

public interface CitaDAO {

	public boolean insert(CitaDTO cita);

	public boolean delete(CitaDTO cita_a_eliminar);

	public boolean update(CitaDTO cita_a_eliminar);

	public List<CitaDTO> readAll();

	public boolean deleteReal(CitaDTO cita_a_eliminar);


}
