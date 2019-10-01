package presentacion.vista;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dto.ProfesionalDTO;

import javax.swing.JButton;

import persistencia.conexion.Conexion;
import javax.swing.JLabel;
import java.awt.Color;

public class VentanaHorarioProfesional
{
	private static VentanaHorarioProfesional INSTANCE;
	private JFrame frmHorario;
	private JButton btnAgregar;
	private JButton btnBorrar;
	private JButton btnModificar;
	private JTable tablaProfesional;
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
		tablaProfesional = new JTable(modelHorario);
		
		tablaProfesional.getColumnModel().getColumn(0).setPreferredWidth(103);
		tablaProfesional.getColumnModel().getColumn(0).setResizable(false);
		tablaProfesional.getColumnModel().getColumn(1).setPreferredWidth(100);
		tablaProfesional.getColumnModel().getColumn(1).setResizable(false);
		
		spPersonas.setViewportView(tablaProfesional);
		
		JLabel lblEmpleado = new JLabel("Empleado: ");
		lblEmpleado.setBounds(10, 11, 85, 14);
		panel.add(lblEmpleado);
		
		JLabel lblNombreEmpl = new JLabel("");
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
	
	public JTable gettablaProfesional()
	{
		return tablaProfesional;
	}

	public String[] getNombreColumnas() 
	{
		return nombreColumnas;
	}


	public void llenarTabla(List<ProfesionalDTO> profesionalEnTabla) {
		this.getModelHorario().setRowCount(0); //Para vaciar la tabla
		this.getModelHorario().setColumnCount(0);
		this.getModelHorario().setColumnIdentifiers(this.getNombreColumnas());

		for (ProfesionalDTO p : profesionalEnTabla)
		{ 
			String nombre = p.getNombre();
			String apellido = p.getApellido();
			String SucursalOrig=p.getSucursal(p.getIdSucursalOrigen());
			String SucursalTrans=(p.getIdSucursalTransferencia()==-1?"":p.getSucursal(p.getIdSucursalTransferencia()));
			Object[] fila = {nombre, apellido,SucursalOrig,SucursalTrans};
			this.getModelHorario().addRow(fila);
		}
		
	} 
}
