
package presentacion.vista;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dto.CategoriaMovimientoCajaDTO;
import util.PropertyManager;

public class VentanaCategoriaMovimentoCaja extends JFrame {
	private static VentanaCategoriaMovimentoCaja INSTANCE;
	private JTable tablaCategoria;
	private JButton btnAgregar;
	private JButton btnBorrar;
	private JButton btnEditar;
	private DefaultTableModel modelCategoria;

	// configuracion de idioma
	private Locale locale = new Locale(PropertyManager.leer("configuracion", "idioma"),
			PropertyManager.leer("configuracion", "pais"));
	private ResourceBundle idioma = ResourceBundle.getBundle("presentacion/idioma/bundle", locale);

	private String[] nombreColumnas = { idioma.getString("nombre"), idioma.getString("tipo"),
			idioma.getString("estado") };

	public VentanaCategoriaMovimentoCaja() {
		super();
		initialize();
	}

	public static VentanaCategoriaMovimentoCaja getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new VentanaCategoriaMovimentoCaja();
		}
		return INSTANCE;
	}

	private void initialize() {
		setBounds(100, 100, 600, 406);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		setTitle(idioma.getString("caja.categorias"));

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 584, 356);
		getContentPane().add(panel);
		panel.setLayout(null);

		JScrollPane spCategoria = new JScrollPane();
		spCategoria.setBounds(10, 11, 572, 277);
		panel.add(spCategoria);

		modelCategoria = new DefaultTableModel(null, nombreColumnas) {
			// Para que las celdas de la tabla no se puedan editar
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
		tablaCategoria.getColumnModel().getColumn(2).setPreferredWidth(100);
		tablaCategoria.getColumnModel().getColumn(2).setResizable(false);

		spCategoria.setViewportView(tablaCategoria);

		btnAgregar = new JButton(idioma.getString("agregar"));
		btnAgregar.setBounds(56, 322, 123, 23);
		panel.add(btnAgregar);

		btnEditar = new JButton(idioma.getString("editar"));
		btnEditar.setBounds(239, 322, 118, 23);
		panel.add(btnEditar);

		btnBorrar = new JButton(idioma.getString("borrar"));
		btnBorrar.setBounds(411, 322, 118, 23);
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
		this.getModelCategoria().setRowCount(0); // Para vaciar la tabla
		this.getModelCategoria().setColumnCount(0);
		this.getModelCategoria().setColumnIdentifiers(this.getNombreColumnas());

		for (CategoriaMovimientoCajaDTO cat : CategoriaEnTabla) {
			String nombre = cat.getNombre();
			String estado = cat.getEstado();
			String tipo = cat.getTipoMovimiento();

			Object[] fila = { nombre, tipo, estado };
			this.getModelCategoria().addRow(fila);
		}
	}

	public void mostrar() {
		setVisible(true);
	}

	public void cerrar() {
		this.dispose();
	}

	public void mostrarErrorSinSeleccionar() {
		JOptionPane.showMessageDialog(new JFrame(), idioma.getString("caja.error.seleccionar.categoria"), "Dialog",
				JOptionPane.ERROR_MESSAGE);

	}

	public int mostrarConfirmacionBorrar() {
		return JOptionPane.showOptionDialog(null, idioma.getString("borrar.confirmacion"),
				idioma.getString("confirmacion"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null,
				null);
	}

}
