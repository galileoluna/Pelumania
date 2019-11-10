package presentacion.vista;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import dto.ProductoDTO;
import util.PropertyManager;
import util.RowsRendererBasic;

public class VentanaProducto extends JFrame {

	private static VentanaProducto INSTANCE;
	private JTable tablaProductos;
	private DefaultTableModel modelProductos;
	private JButton btnBuscar;
	private JTextField buscador;
	private JComboBox variableBuscar;
	private JButton btnAgregar;
	private JButton btnEditar;
	private JButton btnBorrar;
	private JButton btnVerTodo;

	// configuracion de idioma
	private Locale locale = new Locale(PropertyManager.leer("configuracion", "idioma"),
			PropertyManager.leer("configuracion", "pais"));

	private ResourceBundle idioma = ResourceBundle.getBundle("presentacion/idioma/bundle", locale);

	private String[] nombreColumnas = { idioma.getString("nombre"), idioma.getString("precio.pesos"),
			idioma.getString("precio.dolares"), idioma.getString("puntos"),
			idioma.getString("estado") };

	public VentanaProducto() {
		super();
		initialize();
	}

	public static VentanaProducto getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new VentanaProducto();
			return new VentanaProducto();
		} else {
			return INSTANCE;
		}
	}

	private void initialize() {
		setBounds(100, 100, 739, 406);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		setTitle(idioma.getString("producto.titulo"));

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 713, 356);
		getContentPane().add(panel);
		panel.setLayout(null);

		JScrollPane spProductos = new JScrollPane();
		spProductos.setBounds(10, 11, 693, 277);
		panel.add(spProductos);

		modelProductos = new DefaultTableModel(null, nombreColumnas) {
			// Para que las celdas de la tabla no se puedan editar
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tablaProductos = new JTable(modelProductos);
		RowsRendererBasic rr = new RowsRendererBasic(4);
		tablaProductos.setDefaultRenderer(Object.class, rr);

		tablaProductos.getColumnModel().getColumn(0).setPreferredWidth(103);
		tablaProductos.getColumnModel().getColumn(0).setResizable(false);
		tablaProductos.getColumnModel().getColumn(1).setPreferredWidth(100);
		tablaProductos.getColumnModel().getColumn(1).setResizable(false);
		tablaProductos.getColumnModel().getColumn(2).setPreferredWidth(100);
		tablaProductos.getColumnModel().getColumn(2).setResizable(false);
		tablaProductos.getColumnModel().getColumn(3).setPreferredWidth(100);
		tablaProductos.getColumnModel().getColumn(3).setResizable(false);
		tablaProductos.getColumnModel().getColumn(4).setPreferredWidth(100);
		tablaProductos.getColumnModel().getColumn(4).setResizable(false);
		

		spProductos.setViewportView(tablaProductos);

		btnAgregar = new JButton(idioma.getString("agregar"));
		btnAgregar.setBounds(10, 322, 89, 23);
		panel.add(btnAgregar);

		btnEditar = new JButton(idioma.getString("editar"));
		btnEditar.setBounds(120, 322, 89, 23);
		panel.add(btnEditar);

		btnBorrar = new JButton(idioma.getString("borrar"));
		btnBorrar.setBounds(230, 322, 89, 23);
		panel.add(btnBorrar);

		JLabel lblBuscarPor = new JLabel(idioma.getString("filtrar"));
		lblBuscarPor.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblBuscarPor.setBounds(350, 322, 200, 23);
		panel.add(lblBuscarPor);

		variableBuscar = new JComboBox();
		variableBuscar.setBounds(430, 322, 89, 23);
		panel.add(variableBuscar);
		variableBuscar.addItem("Todos");
		variableBuscar.addItem("Nombre");
		variableBuscar.addItem("Estado");

		btnBuscar = new JButton(idioma.getString("buscar"));
		btnBuscar.setBounds(610, 322, 89, 23);
		panel.add(btnBuscar);

		buscador = new JTextField();
		buscador.setBounds(520, 322, 89, 23);
		panel.add(buscador);
		buscador.setColumns(10);

	}

	public JTable getTablaProductos() {
		return tablaProductos;
	}

	public void setTablaProductos(JTable tablaProductos) {
		this.tablaProductos = tablaProductos;
	}

	public DefaultTableModel getModelProductos() {
		return modelProductos;
	}

	public void setModelProductos(DefaultTableModel modelProductos) {
		this.modelProductos = modelProductos;
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

	public JButton getBtnVerTodo() {
		return btnVerTodo;
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

	public JButton getBtnBuscar() {
		return btnBuscar;
	}

	public JComboBox getVariableBuscar() {
		return variableBuscar;
	}

	public JTextField getBuscador() {
		return buscador;
	}

	public void llenarTabla(List<ProductoDTO> ProductosEnTabla) {
		this.getModelProductos().setRowCount(0); // Para vaciar la tabla
		this.getModelProductos().setColumnCount(0);
		this.getModelProductos().setColumnIdentifiers(this.getNombreColumnas());

		for (ProductoDTO s : ProductosEnTabla) {
			String nombre = s.getNombre();
			BigDecimal precioLocal = s.getPrecioLocal();
			BigDecimal precioDolar = s.getPrecioDolar();
			int puntos = s.getPuntos();
			String estado = s.getEstado();
			Object[] fila = { nombre, precioLocal, precioDolar, puntos, estado };
			this.getModelProductos().addRow(fila);
		}
	}

	public void mostrar() {
		setVisible(true);
	}

	public void cerrar() {
		this.dispose();
	}

	public int mostrarConfirmacionBorrar() {
	    UIManager.put("OptionPane.noButtonText", idioma.getString("no"));
	    UIManager.put("OptionPane.yesButtonText", idioma.getString("si"));

		int confirm = JOptionPane.showOptionDialog(null, idioma.getString("borrar.confirmacion"),idioma.getString("confirmacion"), JOptionPane.YES_NO_OPTION,
		             JOptionPane.QUESTION_MESSAGE, null, null, null);
		return confirm;
	}

	public void mostrarErrorBorrar() {
		JOptionPane.showMessageDialog(null, this.idioma.getString("error.borrar.inactivo"));		
	}
}
