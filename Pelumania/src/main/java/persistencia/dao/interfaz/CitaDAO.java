package persistencia.dao.interfaz;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import dto.CitaDTO;

public interface CitaDAO {

	public boolean insert(CitaDTO cita);
	
	public boolean insertSinRegistrar(CitaDTO cita);

	public boolean delete(CitaDTO cita_a_eliminar);

	public boolean update(CitaDTO cita_a_eliminar);
	
	public boolean cancelar(CitaDTO cita_a_cancelar);
	
	public boolean ponerEnCurso(CitaDTO cita_en_curso);
	
	public boolean ponerEnVencida(CitaDTO cita_vencida);

	public boolean finalizar(CitaDTO cita_a_finalizar);
	
	public boolean fiar(CitaDTO cita_a_finalizar);

	public List<CitaDTO> readAll();

	public boolean deleteReal(CitaDTO cita_a_eliminar);
	
	public List<CitaDTO> readCitaPorDia(String dia);
	
	public List<CitaDTO> getCitasPorDia(String dia);

	public CitaDTO getCitaMax();

	public List<CitaDTO> readAllActivas();

	public CitaDTO getById(int idCita);

	public boolean reprogramar(CitaDTO cita_a_reprogramar);

	public List<CitaDTO> getCitasByRangoHorario(LocalTime desde, LocalTime hasta, LocalDate dia);

	boolean updateCitaVencida();


}
