package presentacion.vista;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;

import javax.swing.JFrame;

import dto.CitaDTO;
import dto.ProfesionalDTO;
import dto.ServicioDTO;
import dto.ServicioTurnoDTO;

public class VentanaEditarCita extends JFrame{

	private static final long serialVersionUID = 1L;
	private static VentanaEditarCita INSTANCE;
	
	private CitaDTO citaAsociada;
	private ComponenteCita compCita;
	
	public static VentanaEditarCita getInstance(CitaDTO cita_a_editar)
	{
		if(INSTANCE == null)
		{
			INSTANCE = new VentanaEditarCita(cita_a_editar);
			return INSTANCE;
		} else {
			return INSTANCE;
		}
	}
	
	public VentanaEditarCita(CitaDTO cita_a_editar) {
		
		this.citaAsociada = cita_a_editar;
		setBounds(100, 100, 456, 555);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-getSize().width/2, dim.height/2-getSize().height/2);
	
		compCita = new ComponenteCita(10,10, cita_a_editar);
		getContentPane().add(compCita);
		
		setVisible(true);
	}

	public CitaDTO getCitaAsociada() {
		return citaAsociada;
	}

	public void setCitaAsociada(CitaDTO cita_a_editar) {
		this.citaAsociada = cita_a_editar;
	}

	public ComponenteCita getCompCita() {
		return compCita;
	}

	public void setCompCita(ComponenteCita compCita) {
		this.compCita = compCita;
	}

}

