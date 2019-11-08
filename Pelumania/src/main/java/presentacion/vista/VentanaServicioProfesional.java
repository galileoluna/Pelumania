package presentacion.vista;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import util.PropertyManager;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JComboBox;

public class VentanaServicioProfesional
{
	private static VentanaServicioProfesional INSTANCE;
	private JFrame frmServProf;
	private JLabel lblNombreEmpl;
	private JButton btnAgregar;
	private JButton btnBorrar;
	private JTable tablaServicioProfesional;
	private DefaultTableModel modelHorario;
	private JLabel lblServicio;
	private JComboBox comboBox;
	private Locale locale = new Locale(PropertyManager.leer("configuracion", "idioma"),
			PropertyManager.leer("configuracion", "pais"));
	private ResourceBundle idioma = ResourceBundle.getBundle("presentacion/idioma/bundle", locale);
	private String[] nombreColumnas = {idioma.getString("servicios")};


	public VentanaServicioProfesional() 
	{
		super();
		initialize();
	}
	public static VentanaServicioProfesional getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new VentanaServicioProfesional(); 	
			return new VentanaServicioProfesional();
		}
		else
			return INSTANCE;
	}

	private void initialize() 
	{
		// configuracion de idioma
		
		frmServProf = new JFrame();
		frmServProf.setTitle(idioma.getString("profesional.servicios.asignados"));
		frmServProf.setIconImage(Toolkit.getDefaultToolkit().getImage("imagenes/servicio.png"));
		frmServProf.setBounds(100, 100, 506, 442);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frmServProf.setLocation(dim.width/2-frmServProf.getSize().width/2, dim.height/2-frmServProf.getSize().height/2);
		
		frmServProf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmServProf.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 457, 381);
		frmServProf.getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane spPersonas = new JScrollPane();
		spPersonas.setBounds(33, 106, 296, 265);
		panel.add(spPersonas);
		
		modelHorario = new DefaultTableModel(null,nombreColumnas);
		tablaServicioProfesional = new JTable(modelHorario);
		
		tablaServicioProfesional.getColumnModel().getColumn(0).setPreferredWidth(103);
		tablaServicioProfesional.getColumnModel().getColumn(0).setResizable(false);
		
		
		spPersonas.setViewportView(tablaServicioProfesional);
		
		JLabel lblEmpleado = new JLabel(idioma.getString("profesional"));
		lblEmpleado.setBounds(10, 11, 85, 14);
		panel.add(lblEmpleado);
		
		lblNombreEmpl = new JLabel("");
		lblNombreEmpl.setBackground(Color.RED);
		lblNombreEmpl.setBounds(84, 10, 158, 14);
		panel.add(lblNombreEmpl);
		
		btnAgregar = new JButton(idioma.getString("agregar"));
		btnAgregar.setBounds(346, 62, 89, 23);
		panel.add(btnAgregar);
		
		btnBorrar = new JButton(idioma.getString("borrar"));
		btnBorrar.setBounds(346, 210, 89, 23);
		panel.add(btnBorrar);
		
		lblServicio = new JLabel(idioma.getString("servicio"));
		lblServicio.setBounds(10, 66, 85, 14);
		panel.add(lblServicio);
		
		comboBox = new JComboBox();
		comboBox.setBounds(132, 62, 182, 22);
		panel.add(comboBox);
		
	}
	
	public void show()
	{
		this.frmServProf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.frmServProf.setVisible(true);
	}
	
	public JButton getBtnAgregar() 
	{
		return btnAgregar;
	}
 
	public JButton getBtnBorrar() 
	{
		return btnBorrar;
	}
	
	public DefaultTableModel getModelHorario() 
	{
		return modelHorario;
	}
	
	public JComboBox getCombo() {
		return comboBox;
	}
	
	public JTable getTablServicioProfesional()
	{
		return tablaServicioProfesional;
	}

	public String[] getNombreColumnas() 
	{
		return nombreColumnas;
	}
	
	public void setNombreEmpl(String nombre) {
		this.lblNombreEmpl.setText(nombre);
	}

	public void llenarTabla(List<String> servicio) {
		this.getModelHorario().setRowCount(0); //Para vaciar la tabla
		this.getModelHorario().setColumnCount(0);
		this.getModelHorario().setColumnIdentifiers(this.getNombreColumnas());

		for (String obj : servicio) {
			String serv=obj;
			Object[] fila = {serv};
			this.getModelHorario().addRow(fila);	
		} 
			
	}
	public void mostrarErrorServicioAsignado() {
		 JOptionPane.showMessageDialog(null, idioma.getString("profesional.error.servicio.asignado"), "Error", JOptionPane.ERROR_MESSAGE);
		
	} 
}
