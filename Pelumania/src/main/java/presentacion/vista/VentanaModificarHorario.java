package presentacion.vista;

import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class VentanaModificarHorario extends JFrame {
	
	private JFrame frmHorario;
	private static VentanaModificarHorario INSTANCE;
	private JPanel contentPane;
	private JButton btnActualizar;
	private JLabel lblNombre;
	private JComboBox minutosEntrada;
	private JComboBox horaEntrada;
	private JComboBox minutosSalida;
	private JComboBox horaSalida;
	private JComboBox comboDias;

	public VentanaModificarHorario() 
	{
		super();
		initialize();
	}
	public static VentanaModificarHorario getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new VentanaModificarHorario(); 	
			return new VentanaModificarHorario();
		}
		else
			return INSTANCE;
	}


	public void initialize() {
		
		frmHorario = new JFrame();
		frmHorario.setTitle("Alta Horario");
		frmHorario.setIconImage(Toolkit.getDefaultToolkit().getImage("imagenes/index.png"));
		frmHorario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmHorario.setBounds(100, 100, 353, 320);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frmHorario.getContentPane().add(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEmpleado = new JLabel("Empleado: ");
		lblEmpleado.setBounds(10, 16, 80, 14);
		contentPane.add(lblEmpleado);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 41, 317, 229);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblDias = new JLabel("Dias");
		lblDias.setBounds(10, 22, 132, 14);
		panel.add(lblDias);
		
		JLabel lblHorarioEntrada = new JLabel("Horario Entrada");
		lblHorarioEntrada.setBounds(73, 51, 132, 14);
		panel.add(lblHorarioEntrada);
		
		JLabel lblHorarioSalida = new JLabel("Horario Salida");
		lblHorarioSalida.setBounds(73, 127, 132, 14);
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
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.setBounds(117, 195, 117, 23);
		panel.add(btnActualizar);
		
		JLabel lblHora = new JLabel("Hora:");
		lblHora.setBounds(10, 81, 48, 14);
		panel.add(lblHora);
		
		horaEntrada = new JComboBox();
		horaEntrada.setBounds(89, 77, 53, 22);
		cargarHora(horaEntrada);
		panel.add(horaEntrada);
		
		JLabel lblMinutos = new JLabel("Minutos:");
		lblMinutos.setBounds(162, 81, 72, 14);
		panel.add(lblMinutos);
		
		minutosEntrada = new JComboBox();
		minutosEntrada.setBounds(244, 77, 59, 22);
		cargarMinutos(minutosEntrada);
		panel.add(minutosEntrada);
		
		JLabel label = new JLabel("Hora:");
		label.setBounds(10, 159, 48, 14);
		panel.add(label);
		
		horaSalida = new JComboBox();
		horaSalida.setBounds(89, 152, 53, 22);
		cargarHora(horaSalida);
		panel.add(horaSalida);
		
		JLabel label_1 = new JLabel("Minutos:");
		label_1.setBounds(162, 152, 72, 14);
		panel.add(label_1);
		
		minutosSalida = new JComboBox();
		minutosSalida.setBounds(244, 150, 59, 22);
		cargarMinutos(minutosSalida);
		panel.add(minutosSalida);
		
		lblNombre = new JLabel("");
		lblNombre.setBounds(112, 16, 139, 14);
		contentPane.add(lblNombre);
	}
	
	public void show()
	{
		this.frmHorario.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.frmHorario.setVisible(true);
	}
	
	public JComboBox getComboDia() {
		return this.comboDias;
	}
		
	public JButton getbtnActualizar() {
		return btnActualizar;
	}
	
	public void setLblNombre(String nombre) {
		this.lblNombre.setText(nombre);
	}
	
	public JComboBox getHoraEntrada() {
		return this.horaEntrada;
	}
	
	public JComboBox getHoraSalida() {
		return this.horaSalida;
	}
	
	public JComboBox getMinutosEntrada() {
		return this.minutosEntrada;
	}
	
	public JComboBox getMinutosSalida() {
		return this.minutosSalida;
	}
	
	public void setHoraEntrada(int hora) {
		this.horaEntrada.setSelectedItem(hora);
	}
	
	public void setHoraSalida(int hora) {
		this.horaSalida.setSelectedItem(hora);
	}
	
	public void setMinutosEntrada(int min) {
		this.minutosEntrada.setSelectedItem(min);
	}
	
	public void setMinutosSalida(int min) {
		this.minutosSalida.setSelectedItem(min);
	}
	
	public void setComboDia(String dia) {
		this.comboDias.setSelectedItem(dia);
	}
	
	private void cargarHora(JComboBox hora) {
		for(int i=0;i<=23;i++) {
			hora.addItem(i);
		}
	}
	
	private void cargarMinutos(JComboBox min) {
		for(int i=0;i<=59;i++) {
			min.addItem(i);
		}
	}
	
	public void cerrar()
	{
		this.comboDias.setSelectedItem(null);
		this.horaEntrada.setSelectedItem(null);
		this.horaSalida.setSelectedItem(null);
		this.minutosEntrada.setSelectedItem(null);
		this.minutosSalida.setSelectedItem(null);
		this.frmHorario.dispose();
	}
}
