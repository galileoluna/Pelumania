package presentacion.vista;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dto.ProfesionalDTO;
import util.RowsRendererBasic;

public class VentanaProfesional
{
	private static VentanaProfesional INSTANCE;
	private JFrame frmProfesional;
	private JTable tablaProfesional;
	private JButton btnAgregar;
	private JButton btnBuscar;
	private JComboBox variableBuscar;
	private JButton btnBorrar;
	private JButton btnEditar;
	private JButton btnHorario;
	private DefaultTableModel modelProfesional;
	private  String[] nombreColumnas = {"Nombre","Apellido","Sucursal Origen","Sucursal Transferencia","Estado"};
	private JButton btnAsignarServicio;
	private JTextField buscador;

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
		frmProfesional.setBounds(100, 100, 1001, 341);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frmProfesional.setLocation(dim.width/2-frmProfesional.getSize().width/2, dim.height/2-frmProfesional.getSize().height/2);
		
		frmProfesional.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmProfesional.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 975, 291);
		frmProfesional.getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane spPersonas = new JScrollPane();
		spPersonas.setBounds(10, 64, 786, 182);
		panel.add(spPersonas);
		
		modelProfesional = new DefaultTableModel(null,nombreColumnas);
		tablaProfesional = new JTable(modelProfesional);
		
		RowsRendererBasic rr = new RowsRendererBasic(4);
		tablaProfesional.setDefaultRenderer(Object.class, rr);
		
		tablaProfesional.getColumnModel().getColumn(0).setPreferredWidth(103);
		tablaProfesional.getColumnModel().getColumn(0).setResizable(false);
		tablaProfesional.getColumnModel().getColumn(1).setPreferredWidth(100);
		tablaProfesional.getColumnModel().getColumn(1).setResizable(false);
		
		spPersonas.setViewportView(tablaProfesional);
		
		btnAgregar = new JButton("Agregar Profesional");
		btnAgregar.setBounds(114, 257, 153, 23);
		panel.add(btnAgregar);
		
		btnEditar = new JButton("Editar Profesional");
		btnEditar.setBounds(356, 257, 153, 23);
		panel.add(btnEditar);
		
		btnBorrar = new JButton("Borrar Profesional");
		btnBorrar.setBounds(611, 257, 159, 23);
		panel.add(btnBorrar);
		
		btnHorario = new JButton("Ver Horario");
		btnHorario.setBounds(806, 112, 159, 23);
		panel.add(btnHorario);
		
		btnAsignarServicio = new JButton("Asignar Servicio");
		btnAsignarServicio.setBounds(806, 167, 159, 23);
		panel.add(btnAsignarServicio);
		
		JLabel lblBuscarPor = new JLabel("Buscar por:");
		lblBuscarPor.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblBuscarPor.setBounds(10, 11, 95, 24);
		panel.add(lblBuscarPor);
		
		variableBuscar = new JComboBox();
		variableBuscar.setBounds(136, 13, 153, 22);
		panel.add(variableBuscar);
		variableBuscar.addItem("Todos");
		variableBuscar.addItem("Nombre");
		variableBuscar.addItem("Apellido");
		variableBuscar.addItem("Estado");
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(572, 13, 123, 23);
		panel.add(btnBuscar);
		
		buscador = new JTextField();
		buscador.setBounds(327, 14, 221, 20);
		panel.add(buscador);
		buscador.setColumns(10);
		
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
	
	public JButton getBtnBuscar() {
		return btnBuscar;
	}
	public JComboBox getVariableBuscar() {
		return variableBuscar;
	}
	
	public JTextField getBuscador() {
		return buscador;
	}

	public void llenarTabla(List<ProfesionalDTO> profesionalEnTabla,int idSucursal) {
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
			if(idSucursal == -1) {
				this.getmodelProfesional().addRow(fila);			
			}else {

				if(p.getIdSucursalOrigen() == idSucursal || p.getIdSucursalTransferencia() == idSucursal) {

					this.getmodelProfesional().addRow(fila);
				}
			}
		}
	
	} 
}