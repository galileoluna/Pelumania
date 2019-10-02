package presentacion.vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Time;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import lu.tudor.santec.jtimechooser.demo.JTimeChooserDemo;
import lu.tudor.santec.jtimechooser.JTimeChooser;
import javax.swing.JButton;

public class VentanaAltaHorario extends JFrame {
	
	private JFrame frmHorario;
	private static VentanaAltaHorario INSTANCE;
	private JPanel contentPane;
	private JButton btnAgregar;
	private JTimeChooser timeEntrada;
	private JTimeChooser timeSalida;
	private JComboBox comboDias;

	public VentanaAltaHorario() 
	{
		super();
		initialize();
	}
	public static VentanaAltaHorario getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new VentanaAltaHorario(); 	
			return new VentanaAltaHorario();
		}
		else
			return INSTANCE;
	}


	public void initialize() {
		
		frmHorario = new JFrame();
		frmHorario.setTitle("Alta Horario");
		frmHorario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmHorario.setBounds(100, 100, 353, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frmHorario.getContentPane().add(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 317, 189);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblDias = new JLabel("Dias");
		lblDias.setBounds(10, 22, 132, 14);
		panel.add(lblDias);
		
		JLabel lblHorarioEntrada = new JLabel("Horario Entrada");
		lblHorarioEntrada.setBounds(10, 66, 132, 14);
		panel.add(lblHorarioEntrada);
		
		JLabel lblHorarioSalida = new JLabel("Horario Salida");
		lblHorarioSalida.setBounds(10, 116, 132, 14);
		panel.add(lblHorarioSalida);
		
		comboDias = new JComboBox();
		 comboDias.setBounds(162, 18, 141, 22);
		 comboDias.addItem("Lunes");
		 comboDias.addItem("Martes");
		 comboDias.addItem("Miercoles");
		 comboDias.addItem("Jueves");
		 comboDias.addItem("Viernes");
		 comboDias.addItem("Sabado");
		panel.add(comboDias);
		
		 timeEntrada = new JTimeChooser();
		timeEntrada.setBounds(160, 60, 63, 20);
		panel.add(timeEntrada);
		
		timeSalida = new JTimeChooser();
		timeSalida.setBounds(160, 110, 63, 20);
		panel.add(timeSalida);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(101, 155, 89, 23);
		panel.add(btnAgregar);
	}
	
	public void show()
	{
		this.frmHorario.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.frmHorario.setVisible(true);
	}
	
	public JComboBox getComboDia() {
		return this.comboDias;
	}
	
	public JTimeChooser getHoraEntrada() {
		return timeEntrada;
	}
	
	public JTimeChooser getHoraSalida() {
		return this.timeSalida;
	}
	
	public void setComboDia(String dia) {
		this.comboDias.setSelectedItem(dia);
	}
	
	public void setHoraEntrada(Time entrada) {
		this.timeEntrada.setTime(entrada);
	}
	
	public void setHoraSalida(Time salida) {
		this.timeSalida.setTime(salida);
	}
	
	
}
