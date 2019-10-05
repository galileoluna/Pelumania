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
import java.awt.Toolkit;

public class VentanaProfesional
{
	private static VentanaProfesional INSTANCE;
	private JFrame frmProfesional;
	private JTable tablaProfesional;
	private JButton btnAgregar;
	private JButton btnBorrar;
	private JButton btnEditar;
	private JButton btnHorario;
	private JButton btnBorrarSanti;
	private DefaultTableModel modelProfesional;
	private  String[] nombreColumnas = {"Nombre","Apellido","Sucursal Origen","Sucursal Transferencia","Estado"};
	private JButton btnAsignarServicio;

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
		frmProfesional.setIconImage(Toolkit.getDefaultToolkit().getImage("imagenes/barber-scissors.png"));
		frmProfesional.setTitle("Profesional");
		frmProfesional.setBounds(100, 100, 1001, 300);
		frmProfesional.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmProfesional.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 975, 262);
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
		
		btnAgregar = new JButton("Agregar Profesional");
		btnAgregar.setBounds(111, 228, 153, 23);
		panel.add(btnAgregar);
		
		btnEditar = new JButton("Editar Profesional");
		btnEditar.setBounds(357, 228, 153, 23);
		panel.add(btnEditar);
		
		btnBorrar = new JButton("Borrar Profesional");
		btnBorrar.setBounds(608, 228, 159, 23);
		panel.add(btnBorrar);
		
		btnHorario = new JButton("Ver Horario");
		btnHorario.setBounds(806, 61, 159, 23);
		panel.add(btnHorario);
		
		btnAsignarServicio = new JButton("Asignar Servicio");
		btnAsignarServicio.setBounds(806, 130, 159, 23);
		panel.add(btnAsignarServicio);
		
		btnBorrarSanti = new JButton("Borrar Santi");
		btnBorrarSanti.setBounds(806, 200, 159, 23);
		panel.add(btnBorrarSanti);
		
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
	
	public JButton getBtnSanti() 
	{
		return btnBorrarSanti;
	}
	
	public JButton getBtnEditar() 
	{
		return btnEditar;
	}
	
	public JButton getBtnHorario() 
	{
		return btnHorario;
	}
	
	public DefaultTableModel getmodelProfesional() 
	{
		return modelProfesional;
	}
	
	public JTable gettablaProfesional()
	{
		return tablaProfesional;
	}

	public String[] getNombreColumnas(){
		return nombreColumnas;
	}
	
	public JButton getBtnAsignar() {
		return this.btnAsignarServicio;
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
			String estado=p.getEstado();
			Object[] fila = {nombre, apellido,SucursalOrig,SucursalTrans,estado};
			this.getmodelProfesional().addRow(fila);
		}
		
	} 
}
