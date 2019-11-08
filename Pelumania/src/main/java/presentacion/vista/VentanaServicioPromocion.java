package presentacion.vista;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import util.PropertyManager;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JComboBox;

public class VentanaServicioPromocion {
	private static VentanaServicioPromocion INSTANCE;
	private JFrame frmServProf;
	private JLabel lblProm;
	private JButton btnAgregar;
	private JButton btnBorrar;
	private JTable tablaServicioPromocion;
	private DefaultTableModel modelServProm;

	// configuracion de idioma
	private Locale locale = new Locale(PropertyManager.leer("configuracion", "idioma"),
			PropertyManager.leer("configuracion", "pais"));
	private ResourceBundle idioma = ResourceBundle.getBundle("presentacion/idioma/bundle", locale);

	private String[] nombreColumnas = { idioma.getString("promocion.servicio.asignado") };
	private JLabel lblServicio;
	private JComboBox comboBox;

	public VentanaServicioPromocion() {
		super();
		initialize();
	}

	public static VentanaServicioPromocion getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new VentanaServicioPromocion();
			return new VentanaServicioPromocion();
		} else
			return INSTANCE;
	}

	private void initialize() {
		frmServProf = new JFrame();
		frmServProf.setTitle(idioma.getString("promocion.servicio.asignado"));
		frmServProf.setBounds(100, 100, 506, 442);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frmServProf.setLocation(dim.width / 2 - frmServProf.getSize().width / 2,
				dim.height / 2 - frmServProf.getSize().height / 2);

		frmServProf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmServProf.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 457, 381);
		frmServProf.getContentPane().add(panel);
		panel.setLayout(null);

		JScrollPane spPersonas = new JScrollPane();
		spPersonas.setBounds(33, 106, 296, 265);
		panel.add(spPersonas);

		modelServProm = new DefaultTableModel(null, nombreColumnas);
		tablaServicioPromocion = new JTable(modelServProm);

		tablaServicioPromocion.getColumnModel().getColumn(0).setPreferredWidth(103);
		tablaServicioPromocion.getColumnModel().getColumn(0).setResizable(false);

		spPersonas.setViewportView(tablaServicioPromocion);

		JLabel lblEmpleado = new JLabel(idioma.getString("promocion"));
		lblEmpleado.setBounds(10, 11, 85, 14);
		panel.add(lblEmpleado);

		lblProm = new JLabel("");
		lblProm.setBackground(Color.RED);
		lblProm.setBounds(84, 10, 158, 14);
		panel.add(lblProm);

		btnAgregar = new JButton(idioma.getString("agregar"));
		btnAgregar.setBounds(346, 62, 89, 23);
		panel.add(btnAgregar);

		btnBorrar = new JButton(idioma.getString("borrar"));
		btnBorrar.setBounds(346, 210, 89, 23);
		panel.add(btnBorrar);

		lblServicio = new JLabel(idioma.getString("servicios"));
		lblServicio.setBounds(10, 66, 85, 14);
		panel.add(lblServicio);

		comboBox = new JComboBox();
		comboBox.setBounds(132, 62, 182, 22);
		panel.add(comboBox);

	}

	public void show() {
		this.frmServProf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.frmServProf.setVisible(true);
	}

	public JButton getBtnAgregar() {
		return btnAgregar;
	}

	public JButton getBtnBorrar() {
		return btnBorrar;
	}

	public DefaultTableModel getModelServProm() {
		return modelServProm;
	}

	public JComboBox getCombo() {
		return comboBox;
	}

	public JTable getTablServicioPromocion() {
		return tablaServicioPromocion;
	}

	public String[] getNombreColumnas() {
		return nombreColumnas;
	}

	public void setDescripcion(String nombre) {
		this.lblProm.setText(nombre);
	}

	public void llenarTabla(List<String> servicio) {
		this.getModelServProm().setRowCount(0); // Para vaciar la tabla
		this.getModelServProm().setColumnCount(0);
		this.getModelServProm().setColumnIdentifiers(this.getNombreColumnas());

		for (String obj : servicio) {
			String serv = obj;
			Object[] fila = { serv };
			this.getModelServProm().addRow(fila);
		}

	}

	public void mostrarErrorAsignarServicio() {
		JOptionPane.showMessageDialog(null, idioma.getString("promocion.error.asignar.servicio"), "Error",
				JOptionPane.ERROR_MESSAGE);

	}

	public int mostrarConfirmacionBorrar() {
		UIManager.put("OptionPane.noButtonText", idioma.getString("no"));
		UIManager.put("OptionPane.yesButtonText", idioma.getString("si"));
		return JOptionPane.showOptionDialog(null, idioma.getString("borrar.confirmacion"),
				idioma.getString("confirmacion"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null,
				null);
	}
}
