package presentacion.vista;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.toedter.calendar.JCalendar;

import dto.ServicioDTO;
import persistencia.conexion.Conexion;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

public class Vista {
	
	private JFrame frame;
	private JCalendar calendario;
	private JButton btnServicios;
	private JButton btnAgregarCliente;
	
	private JMenu menu;
		private JMenu mnServicio;
			private JMenuItem mntmConsultarServicios;
		private JMenu mnProfesional;
		private JMenuItem menuConsultarProf;
		private JMenu mnCliente;

	
	public Vista() 
	{
		super();
		initialize();
	}
	
	private void initialize() 
	{
		frame = new JFrame();
		frame.setBounds(100, 40, 1011, 683);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("imagenes/images.jpg"));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.setTitle("Hair & Head");
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 995, 21);
		frame.getContentPane().add(menuBar);
		
		JMenu mnArchivo = new JMenu("Archivo");
		menuBar.add(mnArchivo);
		
		mnServicio = new JMenu("Servicio");
		menuBar.add(mnServicio);
		
		mntmConsultarServicios = new JMenuItem("Consultar Servicios");
		mnServicio.add(mntmConsultarServicios);
		
		
		mnProfesional = new JMenu("Profesional");
		menuBar.add(mnProfesional);
		
		menuConsultarProf= new JMenuItem("Consultar Profesionales");
		mnProfesional.add(menuConsultarProf);
		
		mnCliente = new JMenu("Cliente");
		menuBar.add(mnCliente);
		
		menu = new JMenu("");
		menuBar.add(menu);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 985, 633);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		calendario = new JCalendar();
		calendario.getYearChooser().getSpinner().setEnabled(false);
		calendario.setTodayButtonVisible(true);
		calendario.setBounds(10, 33, 578, 600);
		panel.add(calendario);
		calendario.setVisible(true);
		
		btnAgregarCliente = new JButton("Agregar Cliente");
		btnAgregarCliente.setBounds(682, 132, 152, 39);
		panel.add(btnAgregarCliente);
		
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
	public JButton getBtnAgregarCliente () {
		return btnAgregarCliente;
	}
	
	
	public JMenuItem getMnItmConsultarServicios() {
		return mntmConsultarServicios;
	}
	
	public JMenuItem getMenuProfesional() {
		return menuConsultarProf;
	}
 
}
