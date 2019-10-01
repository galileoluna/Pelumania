package persistencia.dao.interfaz;

import java.util.List;

import dto.HorarioDTO;

public interface HorarioDAO {

	public boolean insert(HorarioDTO horario);

	public boolean delete(HorarioDTO horario_a_eliminar);
	
	public List<HorarioDTO> readAll();
	
	public List<HorarioDTO> readOne(int id);
	
	public  boolean update(HorarioDTO horario_a_editar);
}
