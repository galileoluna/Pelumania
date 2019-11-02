package presentacion.vista;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import dto.SucursalDTO;

public class VentanaModificarUsuario extends JFrame {
	private static VentanaModificarUsuario INSTANCE;
	private JFrame frmUsuario;
	private JTable tablaUsuario;
	private JButton btnAgregar;
	private JButton btnBorrar;
	private JButton btnEditar;
	private DefaultTableModel modelUsuario;
	private  String[] nombreColumnas = {"Nombre","Usuario","Mail","Permisos","Sucursal","Estado"};

	public VentanaModificarUsuario()
	{
		super();
		initialize();
	}

	public static VentanaModificarUsuario getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new VentanaModificarUsuario();
			return new VentanaModificarUsuario();
		} else {
			return INSTANCE;
		}
	}
	
	private void initialize()
	{
		setBounds(100, 100, 739, 406);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		setTitle("Usuarios");

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 713, 356);
		getContentPane().add(panel);
		panel.setLayout(null);

		JScrollPane spSucursal = new JScrollPane();
		spSucursal.setBounds(10, 11, 693, 277);
		panel.add(spSucursal);

		modelUsuario = new DefaultTableModel(null,nombreColumnas) {
			//Para que las celdas de la tabla no se puedan editar
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tablaUsuario = new JTable(modelUsuario);


		tablaUsuario.getColumnModel().getColumn(0).setPreferredWidth(103);
		tablaUsuario.getColumnModel().getColumn(0).setResizable(false);
		tablaUsuario.getColumnModel().getColumn(1).setPreferredWidth(100);
		tablaUsuario.getColumnModel().getColumn(1).setResizable(false);
		tablaUsuario.getColumnModel().getColumn(2).setPreferredWidth(100);
		tablaUsuario.getColumnModel().getColumn(2).setResizable(false);
		tablaUsuario.getColumnModel().getColumn(3).setPreferredWidth(100);
		tablaUsuario.getColumnModel().getColumn(3).setResizable(false);
		

		spSucursal.setViewportView(tablaUsuario);

		btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(172, 322, 89, 23);
		panel.add(btnAgregar);

		btnEditar = new JButton("Editar");
		btnEditar.setBounds(282, 322, 89, 23);
		panel.add(btnEditar);

		btnBorrar = new JButton("Borrar");
		btnBorrar.setBounds(394, 322, 89, 23);
		panel.add(btnBorrar);
	}
	public JTable gettablaUsuario() {
		return tablaUsuario;
	}

	public void settablaUsuario(JTable tablaUsuario) {
		this.tablaUsuario = tablaUsuario;
	}

	public DefaultTableModel getModelUsuario() {
		return modelUsuario;
	}

	public void setModelUsuario(DefaultTableModel modelUsuario) {
		this.modelUsuario = modelUsuario;
	}

	public String[] getNombreColumnas() {
		return nombreColumnas;
	}

	public void setNombreColumnas(String[] nombreColumnas) {
		this.nombreColumnas = nombreColumnas;
	}

	public JButton getBtnAgregar() {
		return btnAgregar;
	}

	public void setBtnAgregar(JButton btnAgregar) {
		this.btnAgregar = btnAgregar;
	}

	public JButton getBtnEditar() {
		return btnEditar;
	}

	public void setBtnEditar(JButton btnEditar) {
		this.btnEditar = btnEditar;
	}

	public JButton getBtnBorrar() {
		return btnBorrar;
	}

	public void setBtnBorrar(JButton btnBorrar) {
		this.btnBorrar = btnBorrar;
	}

	public void llenarTabla(List<SucursalDTO> sucursalEnTabla) {
		this.getModelUsuario().setRowCount(0); //Para vaciar la tabla
		this.getModelUsuario().setColumnCount(0);
		this.getModelUsuario().setColumnIdentifiers(this.getNombreColumnas());

		for (SucursalDTO s : sucursalEnTabla)
		{
			String nombreSucursal=s.getNombreSucursal();
			String direccion=s.getDireccion();
			int numero=s.getNumero();
			String estado = s.getEstadoSucursal();
			
			Object[] fila = {nombreSucursal,direccion, numero, estado};
			this.getModelUsuario().addRow(fila);
		}
	}
	public void mostrar() {
		setVisible(true);
	}

	public void cerrar() {
		this.dispose();
	}


}
