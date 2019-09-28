package presentacion.vista;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.toedter.calendar.JCalendar;

import persistencia.conexion.Conexion;
import javax.swing.JButton;

public class Vista {
	private JFrame frame;
	private JCalendar calendario;
	private JButton btnServicios;
	
	public Vista() 
	{
		super();
		initialize();
	}
	
	private void initialize() 
	{
		frame = new JFrame();
		frame.setBounds(100, 100, 1011, 683);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 985, 633);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		calendario = new JCalendar();
		calendario.setTodayButtonVisible(true);
		calendario.setBounds(10, 11, 578, 622);
		panel.add(calendario);
		
		btnServicios = new JButton("Servicios");
		btnServicios.setBounds(10, 362, 152, 39);
		panel.add(btnServicios);
		btnServicios.setVisible(true);
		
		calendario.setVisible(true);

	}
	
	public void show()
	{
		this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.frame.addWindowListener(new WindowAdapter() 
		{
			@Override
		    public void windowClosing(WindowEvent e) {
		        int confirm = JOptionPane.showOptionDialog(
		             null, "¿Deseas cerrar Pelumanía?", 
		             "Confirmación", JOptionPane.YES_NO_OPTION,
		             JOptionPane.QUESTION_MESSAGE, null, null, null);
		        if (confirm == 0) {
		        	Conexion.getConexion().cerrarConexion();
		           System.exit(0);
		        }
		    }
		});
		this.frame.setVisible(true);
	}

	public JButton getBtnServicios() {
		return btnServicios;
	}

	public void setBtnServicios(JButton btnServicios) {
		this.btnServicios = btnServicios;
	}
	
}
