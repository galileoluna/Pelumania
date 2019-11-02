package persistencia.dao.interfaz;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import dto.ServicioTurnoDTO;

public interface ServicioTurnoDAO {
	
	public boolean insert(ServicioTurnoDTO servicioTurno_a_insertar);

	public boolean delete(ServicioTurnoDTO servicioTurno_a_eliminar);

	public boolean update(ServicioTurnoDTO servicioTurno_a_Actualizar);

	public List<ServicioTurnoDTO> readAll();

	public List<ServicioTurnoDTO> getByIdCita(int idCita);

	List<Integer> getCitasByIdServicio(int idServicio);

	public int profesionalOcupado(Integer idProfesional, LocalTime horaInicio, 
			LocalTime horaFin, LocalDate fecha);

	int profesionalEnSucursal(Integer idProfesional, LocalTime horaInicio, LocalTime horaFin, String diaDeLaSemana);

}
