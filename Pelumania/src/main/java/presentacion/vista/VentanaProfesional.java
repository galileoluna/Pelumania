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

public class VentanaProfesional
{
	private static VentanaProfesional INSTANCE;
	private JFrame frmProfesional;
	private JTable tablaProfesional;
	private JButton btnAgregar;
	private JButton btnBorrar;
	private JButton btnEditar;
	private DefaultTableModel modelProfesional;
	private  String[] nombreColumnas = {"Nombre","Apellido","Sucursal Origen","Sucursal Transferencia"};

	public VentanaProfesional() 
	{
		super();
		initialize();
	}
	public static VentanaProfesional getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new VentanaProfesional(); 	
			return new VentanaProfesional();
		}
		else
			return INSTANCE;
	}

	private void initialize() 
	{
		frmProfesional = new JFrame();
		frmProfesional.setTitle("Profesional");
		frmProfesional.setBounds(100, 100, 822, 300);
		frmProfesional.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmProfesional.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 806, 262);
		frmProfesional.getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane spPersonas = new JScrollPane();
		spPersonas.setBounds(10, 11, 786, 182);
		panel.add(spPersonas);
		
		modelProfesional = new DefaultTableModel(null,nombreColumnas);
		tablaProfesional = new JTable(modelProfesional);
		
		tablaProfesional.getColumnModel().getColumn(0).setPreferredWidth(103);
		tablaProfesional.getColumnModel().getColumn(0).setResizable(false);
		tablaProfesional.getColumnModel().getColumn(1).setPreferredWidth(100);
		tablaProfesional.getColumnModel().getColumn(1).setResizable(false);
		
		spPersonas.setViewportView(tablaProfesional);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(144, 228, 89, 23);
		panel.add(btnAgregar);
		
		btnEditar = new JButton("Editar");
		btnEditar.setBounds(292, 228, 89, 23);
		panel.add(btnEditar);
		
		btnBorrar = new JButton("Borrar");
		btnBorrar.setBounds(429, 228, 89, 23);
		panel.add(btnBorrar);
		
	}
	
	public void show()
	{
		this.frmProfesional.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.frmProfesional.setVisible(true);
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
		return btnEditar;
	}
	
	public DefaultTableModel getmodelProfesional() 
	{
		return modelProfesional;
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
		this.getmodelProfesional().setRowCount(0); //Para vaciar la tabla
		this.getmodelProfesional().setColumnCount(0);
		this.getmodelProfesional().setColumnIdentifiers(this.getNombreColumnas());

		for (ProfesionalDTO p : profesionalEnTabla)
		{ 
			String nombre = p.getNombre();
			String apellido = p.getApellido();
			String SucursalOrig=p.getSucursal(p.getIdSucursalOrigen());
			String SucursalTrans=(p.getIdSucursalTransferencia()==-1?"":p.getSucursal(p.getIdSucursalTransferencia()));
			Object[] fila = {nombre, apellido,SucursalOrig,SucursalTrans};
			this.getmodelProfesional().addRow(fila);
		}
		
	} 

}
