package presentacion.vista;


import java.sql.Time;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import dto.HorarioDTO;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Toolkit;

public class VentanaHorarioProfesional
{
	private static VentanaHorarioProfesional INSTANCE;
	private JFrame frmHorario;
	private JLabel lblNombreEmpl;
	private JButton btnAgregar;
	private JButton btnBorrar;
	private JButton btnModificar;
	private JTable tablaHorarioProfesional;
	private DefaultTableModel modelHorario;
	private  String[] nombreColumnas = {"Día","Hora Entrada","Hora Salida"};

	public VentanaHorarioProfesional() 
	{
		super();
		initialize();
	}
	public static VentanaHorarioProfesional getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new VentanaHorarioProfesional(); 	
			return new VentanaHorarioProfesional();
		}
		else
			return INSTANCE;
	}

	private void initialize() 
	{
		frmHorario = new JFrame();
		frmHorario.setIconImage(Toolkit.getDefaultToolkit().getImage("imagenes/index.png"));
		frmHorario.setTitle("Horarios Laborales");
		frmHorario.setBounds(100, 100, 644, 300);
		frmHorario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmHorario.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 628, 262);
		frmHorario.getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane spPersonas = new JScrollPane();
		spPersonas.setBounds(46, 35, 556, 165);
		panel.add(spPersonas);
		
		modelHorario = new DefaultTableModel(null,nombreColumnas);
		tablaHorarioProfesional = new JTable(modelHorario);
		
		tablaHorarioProfesional.getColumnModel().getColumn(0).setPreferredWidth(103);
		tablaHorarioProfesional.getColumnModel().getColumn(0).setResizable(false);
		tablaHorarioProfesional.getColumnModel().getColumn(1).setPreferredWidth(100);
		tablaHorarioProfesional.getColumnModel().getColumn(1).setResizable(false);
		
		spPersonas.setViewportView(tablaHorarioProfesional);
		
		JLabel lblEmpleado = new JLabel("Empleado: ");
		lblEmpleado.setBounds(10, 11, 85, 14);
		panel.add(lblEmpleado);
		
		lblNombreEmpl = new JLabel("");
		lblNombreEmpl.setBackground(Color.RED);
		lblNombreEmpl.setBounds(84, 10, 158, 14);
		panel.add(lblNombreEmpl);
		
		btnAgregar = new JButton("Agregar");	
		btnAgregar.setBounds(84, 216, 89, 23);
		panel.add(btnAgregar);
		
		btnBorrar = new JButton("Borrar");
		btnBorrar.setBounds(258, 216, 89, 23);
		panel.add(btnBorrar);
		
		btnModificar = new JButton("Modificar");
		btnModificar.setBounds(454, 216, 89, 23);
		panel.add(btnModificar);
		
	}
	
	public void show()
	{
		this.frmHorario.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.frmHorario.setVisible(true);
	}
	
	public JButton getBtnAgregar() 
	{
		return btnAgregar;
	}
 
	public JButton getBtnBorrar() 
	{
		return btnBorrar;
	}
	
	
	public JButton getBtnEditar() 
	{
		return btnModificar;
	}

	
	public DefaultTableModel getModelHorario() 
	{
		return modelHorario;
	}
	
	public JTable getTablaHorarioProfesional()
	{
		return tablaHorarioProfesional;
	}

	public String[] getNombreColumnas() 
	{
		return nombreColumnas;
	}
	
	public void setNombreEmpl(String nombre) {
		this.lblNombreEmpl.setText(nombre);
	}

	public void llenarTabla(List<HorarioDTO> HorarioEnTabla) {
		this.getModelHorario().setRowCount(0); //Para vaciar la tabla
		this.getModelHorario().setColumnCount(0);
		this.getModelHorario().setColumnIdentifiers(this.getNombreColumnas());

		for (HorarioDTO p : HorarioEnTabla){ 
			String dia = p.getDia();
			Time horaEntrada = p.getHoraEntrada();
			Time horaSalida=p.getHoraSalida();
		
			Object[] fila = {dia, horaEntrada,horaSalida};
			this.getModelHorario().addRow(fila);
		}
		
	} 
}
