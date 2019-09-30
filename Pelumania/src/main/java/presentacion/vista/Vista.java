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
import java.awt.event.ActionEvent;

public class Vista {
	
	private JFrame frame;
	private JCalendar calendario;
	
	private JButton btnServicios;
	private JButton btnAgregarCliente;
	
	private JMenu menu;
		private JMenu mnServicio;
			private JMenuItem mntmConsultarServicios;
			private JMenuItem mntmAgregarServicio;
		private JMenu mnProfesional;
		
		private JMenu mnCliente;
	
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
		calendario.setVisible(true);

		btnServicios = new JButton("Servicios");
		btnServicios.setBounds(682, 65, 152, 39);
		panel.add(btnServicios);
		btnServicios.setVisible(true);
		
		btnAgregarCliente = new JButton("Agregar Cliente");
		btnAgregarCliente.setBounds(682, 138, 152, 39);
		panel.add(btnAgregarCliente);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnArchivo = new JMenu("Archivo");
		menuBar.add(mnArchivo);
		
		mnServicio = new JMenu("Servicio");
		menuBar.add(mnServicio);
		
		mntmAgregarServicio = new JMenuItem("Agregar Servicio");
		mnServicio.add(mntmAgregarServicio);
		
		mntmConsultarServicios = new JMenuItem("Consultar Servicios");
		mnServicio.add(mntmConsultarServicios);
		
		
		mnProfesional = new JMenu("Profesional");
		menuBar.add(mnProfesional);
		
		mnCliente = new JMenu("Cliente");
		menuBar.add(mnCliente);
		
		menu = new JMenu("");
		menuBar.add(menu);
		btnAgregarCliente.setVisible(true);
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
	
	public JMenuItem getMnItmAgregarServicio() {
		return mntmAgregarServicio;
	}
	
}
