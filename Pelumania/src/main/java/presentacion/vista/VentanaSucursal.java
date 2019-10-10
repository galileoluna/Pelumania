package presentacion.vista;

import java.awt.Toolkit;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dto.ProfesionalDTO;
import dto.ServicioDTO;
import dto.SucursalDTO;

public class VentanaSucursal extends JFrame {
	private static VentanaSucursal INSTANCE;
	private JFrame frmSucursal;
	private JTable tablaSucursal;
	private JButton btnAgregar;
	private JButton btnBorrar;
	private JButton btnEditar;
	private DefaultTableModel modelSucursal;
	private  String[] nombreColumnas = {"Nombre Sucursal","Direccion","Numero","Estado"};

	public VentanaSucursal()
	{
		super();
		initialize();
	}

	public static VentanaSucursal getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new VentanaSucursal();
			return new VentanaSucursal();
		} else {
			return INSTANCE;
		}
	}
	
	private void initialize()
	{
		setBounds(100, 100, 739, 406);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		setTitle("Manejo de Sucursales");

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 713, 356);
		getContentPane().add(panel);
		panel.setLayout(null);

		JScrollPane spSucursal = new JScrollPane();
		spSucursal.setBounds(10, 11, 693, 277);
		panel.add(spSucursal);

		modelSucursal = new DefaultTableModel(null,nombreColumnas) {
			//Para que las celdas de la tabla no se puedan editar
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tablaSucursal = new JTable(modelSucursal);


		tablaSucursal.getColumnModel().getColumn(0).setPreferredWidth(103);
		tablaSucursal.getColumnModel().getColumn(0).setResizable(false);
		tablaSucursal.getColumnModel().getColumn(1).setPreferredWidth(100);
		tablaSucursal.getColumnModel().getColumn(1).setResizable(false);
		tablaSucursal.getColumnModel().getColumn(2).setPreferredWidth(100);
		tablaSucursal.getColumnModel().getColumn(2).setResizable(false);
		tablaSucursal.getColumnModel().getColumn(3).setPreferredWidth(100);
		tablaSucursal.getColumnModel().getColumn(3).setResizable(false);
		

		spSucursal.setViewportView(tablaSucursal);

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
	public JTable getTablaSucursal() {
		return tablaSucursal;
	}

	public void setTablaSucursal(JTable tablaSucursal) {
		this.tablaSucursal = tablaSucursal;
	}

	public DefaultTableModel getModelSucursal() {
		return modelSucursal;
	}

	public void setModelSucursals(DefaultTableModel modelSucursal) {
		this.modelSucursal = modelSucursal;
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
		this.getModelSucursal().setRowCount(0); //Para vaciar la tabla
		this.getModelSucursal().setColumnCount(0);
		this.getModelSucursal().setColumnIdentifiers(this.getNombreColumnas());

		for (SucursalDTO s : sucursalEnTabla)
		{
			String nombreSucursal=s.getNombreSucursal();
			String direccion=s.getDireccion();
			int numero=s.getNumero();
			String estado = s.getEstadoSucursal();
			
			Object[] fila = {nombreSucursal,direccion, numero, estado};
			this.getModelSucursal().addRow(fila);
		}
	}
	public void mostrar() {
		setVisible(true);
	}

	public void cerrar() {
		this.dispose();
	}


}
