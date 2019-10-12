


package presentacion.vista;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dto.CategoriaMovimientoCajaDTO;

public class VentanaCategoriaMovimentoCaja extends JFrame {
	private static VentanaCategoriaMovimentoCaja INSTANCE;
	private JFrame frmCategoria;
	private JTable tablaCategoria;
	private JButton btnAgregar;
	private JButton btnBorrar;
	private JButton btnEditar;
	private DefaultTableModel modelCategoria;
	private  String[] nombreColumnas = {"Nombre Categoria","Estado"};

	public VentanaCategoriaMovimentoCaja()
	{
		super();
		initialize();
	}

	public static VentanaCategoriaMovimentoCaja getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new VentanaCategoriaMovimentoCaja();
		} 
			return INSTANCE;
	}
	
	private void initialize()
	{
		setBounds(100, 100, 739, 406);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		setTitle("Manejo de Categorias de Caja");

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 713, 356);
		getContentPane().add(panel);
		panel.setLayout(null);

		JScrollPane spCategoria = new JScrollPane();
		spCategoria.setBounds(10, 11, 693, 277);
		panel.add(spCategoria);

		modelCategoria = new DefaultTableModel(null,nombreColumnas) {
			//Para que las celdas de la tabla no se puedan editar
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tablaCategoria = new JTable(modelCategoria);


		tablaCategoria.getColumnModel().getColumn(0).setPreferredWidth(103);
		tablaCategoria.getColumnModel().getColumn(0).setResizable(false);
		tablaCategoria.getColumnModel().getColumn(1).setPreferredWidth(100);
		tablaCategoria.getColumnModel().getColumn(1).setResizable(false);

		spCategoria.setViewportView(tablaCategoria);

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
	public JTable getTablaCategoria() {
		return tablaCategoria;
	}

	public void setTablaCategoria(JTable tablaCategoria) {
		this.tablaCategoria = tablaCategoria;
	}

	public DefaultTableModel getModelCategoria() {
		return modelCategoria;
	}

	public void setModelCategoria(DefaultTableModel modelCategoria) {
		this.modelCategoria = modelCategoria;
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

	public void llenarTabla(List<CategoriaMovimientoCajaDTO> CategoriaEnTabla) {
		this.getModelCategoria().setRowCount(0); //Para vaciar la tabla
		this.getModelCategoria().setColumnCount(0);
		this.getModelCategoria().setColumnIdentifiers(this.getNombreColumnas());

		for (CategoriaMovimientoCajaDTO cat : CategoriaEnTabla)
		{
			String nombre= cat.getNombre();
			String estado =cat.getEstado();
			
			Object[] fila = {nombre,estado};
			this.getModelCategoria().addRow(fila);
		}
	}
	public void mostrar() {
		setVisible(true);
	}

	public void cerrar() {
		this.dispose();
	}


}
