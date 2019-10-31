package persistencia.dao.interfaz;

import java.time.LocalTime;
import java.util.List;

import dto.ProfesionalDTO;
import dto.ServicioDTO;

public interface ProfesionalDAO {
	
	public boolean insert(ProfesionalDTO profesional);

	public boolean delete(ProfesionalDTO profesional_a_eliminar);
	
	public boolean deleteSanti(ProfesionalDTO profesional_a_eliminar);
	
	public List<ProfesionalDTO> readAll();
	
	public List<ProfesionalDTO> readOne(int id);
	
	public  boolean update(ProfesionalDTO profesional_a_editar);
	
	public boolean insertServProf(int idServ, int idProf);
	
	public boolean deleteServProf(int idServ, int idPof);
	
	public ProfesionalDTO getById(int idProf);
	
	public List<ServicioDTO> getServiciosDelProfesional(int id);
	
	public List<String> obtenerServEnTabla(int id);

	public List<ProfesionalDTO> obtenerProfBuscado(String variable, String value);

	public List<ProfesionalDTO> getProfesionalByHorario(LocalTime inicioTurno, String diaDeLaSemana);
	
	public List<ProfesionalDTO> getProfesionalByIdSucursal(int idSucursal);

	public List<ProfesionalDTO> getProfesionalesByIdServicio(int idServicio);
}
