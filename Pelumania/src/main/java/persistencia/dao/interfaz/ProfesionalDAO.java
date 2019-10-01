package persistencia.dao.interfaz;

import java.util.List;

import dto.ProfesionalDTO;

public interface ProfesionalDAO {
	
	public boolean insert(ProfesionalDTO profesional);

	public boolean delete(ProfesionalDTO profesional_a_eliminar);
	
	public List<ProfesionalDTO> readAll();
	
	public List<ProfesionalDTO> readOne(int id);
}
