package presentacion.vista;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dto.HorarioDTO;
import dto.ProfesionalDTO;

import javax.swing.JButton;

import persistencia.conexion.Conexion;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JComboBox;

public class VentanaServicioProfecional
{
	private static VentanaServicioProfecional INSTANCE;
	private JFrame frmServProf;
	private JLabel lblNombreEmpl;
	private JButton btnAgregar;
	private JButton btnBorrar;
	private JTable tablaHorarioProfesional;
	private DefaultTableModel modelHorario;
	private  String[] nombreColumnas = {"Servicio"};
	private JLabel lblServicio;
	private JComboBox comboBox;

	public VentanaServicioProfecional() 
	{
		super();
		initialize();
	}
	public static VentanaServicioProfecional getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new VentanaServicioProfecional(); 	
			return new VentanaServicioProfecional();
		}
		else
			return INSTANCE;
	}

	private void initialize() 
	{
		frmServProf = new JFrame();
		frmServProf.setTitle("Relacion Servicio Profesional");
		frmServProf.setBounds(100, 100, 506, 442);
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
		tablaHorarioProfesional = new JTable(modelHorario);
		
		tablaHorarioProfesional.getColumnModel().getColumn(0).setPreferredWidth(103);
		tablaHorarioProfesional.getColumnModel().getColumn(0).setResizable(false);
		
		
		spPersonas.setViewportView(tablaHorarioProfesional);
		
		JLabel lblEmpleado = new JLabel("Empleado: ");
		lblEmpleado.setBounds(10, 11, 85, 14);
		panel.add(lblEmpleado);
		
		lblNombreEmpl = new JLabel("");
		lblNombreEmpl.setBackground(Color.RED);
		lblNombreEmpl.setBounds(84, 10, 158, 14);
		panel.add(lblNombreEmpl);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(346, 62, 89, 23);
		panel.add(btnAgregar);
		
		btnBorrar = new JButton("Borrar");
		btnBorrar.setBounds(346, 210, 89, 23);
		panel.add(btnBorrar);
		
		lblServicio = new JLabel("Servicio:");
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
}
