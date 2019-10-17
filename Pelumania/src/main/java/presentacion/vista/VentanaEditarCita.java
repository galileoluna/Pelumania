package presentacion.vista;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import dto.CitaDTO;

public class VentanaEditarCita extends JFrame{

	private static final long serialVersionUID = 1L;
	private CitaDTO cita_a_editar;
	private static VentanaEditarCita INSTANCE;
	
	public static VentanaEditarCita getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new VentanaEditarCita();
			return new VentanaEditarCita();
		} else {
			return INSTANCE;
		}
	}
	
	public VentanaEditarCita() {
		
		setBounds(100, 100, 456, 555);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-getSize().width/2, dim.height/2-getSize().height/2);
	
		ComponenteCita compCita = new ComponenteCita(10,10);
		getContentPane().add(compCita);
		
		
		setVisible(true);
	}

	public CitaDTO getCita_a_editar() {
		return cita_a_editar;
	}

	public void setCita_a_editar(CitaDTO cita_a_editar) {
		this.cita_a_editar = cita_a_editar;
	}

}
