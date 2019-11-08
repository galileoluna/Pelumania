package presentacion.vista;


import java.sql.Time;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import dto.HorarioDTO;
import util.PropertyManager;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Dimension;
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
	
	// configuracion de idioma
	private Locale locale = new Locale(PropertyManager.leer("configuracion", "idioma"),
				PropertyManager.leer("configuracion", "pais"));

	private ResourceBundle idioma = ResourceBundle.getBundle("presentacion/idioma/bundle", locale);
	
	private  String[] nombreColumnas = {idioma.getString("profesional.dia"),idioma.getString("profesional.horario.entrada")
										,idioma.getString("profesional.horario.salida")};

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
		frmHorario.setTitle(idioma.getString("profesional.horario.laboral"));
		frmHorario.setBounds(100, 100, 644, 300);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frmHorario.setLocation(dim.width/2-frmHorario.getSize().width/2, dim.height/2-frmHorario.getSize().height/2);
		
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
		
		JLabel lblEmpleado = new JLabel(idioma.getString("profesional"));
		lblEmpleado.setBounds(10, 11, 85, 14);
		panel.add(lblEmpleado);
		
		lblNombreEmpl = new JLabel("");
		lblNombreEmpl.setBackground(Color.RED);
		lblNombreEmpl.setBounds(84, 10, 158, 14);
		panel.add(lblNombreEmpl);
		
		btnAgregar = new JButton(idioma.getString("agregar"));	
		btnAgregar.setBounds(84, 216, 89, 23);
		panel.add(btnAgregar);
		
		btnBorrar = new JButton(idioma.getString("borrar"));
		btnBorrar.setBounds(258, 216, 89, 23);
		panel.add(btnBorrar);
		
		btnModificar = new JButton(idioma.getString("editar"));
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
	public int mostrarConfirmacionBorrar() {
		UIManager.put("OptionPane.noButtonText", idioma.getString("no"));
	    UIManager.put("OptionPane.yesButtonText", idioma.getString("si"));
		int confirm = JOptionPane.showOptionDialog(null, idioma.getString("borrar.confirmacion"), idioma.getString("confirmacion"), JOptionPane.YES_NO_OPTION,
		             JOptionPane.QUESTION_MESSAGE, null, null, null);
		return confirm;
	} 
}
