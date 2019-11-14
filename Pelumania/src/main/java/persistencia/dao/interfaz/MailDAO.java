package persistencia.dao.interfaz;

import java.time.LocalDate;
import java.util.List;

import dto.MailDTO;

public interface MailDAO {

	public boolean insert(MailDTO horario);

	public boolean delete(MailDTO horario_a_eliminar);
	
	public List<MailDTO> readAll(int id);
	
	public List<MailDTO> readOne(int id);

	public List<MailDTO> readAllOneDay(LocalDate dia);
	
}
