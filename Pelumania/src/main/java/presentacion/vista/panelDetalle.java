package presentacion.vista;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class panelDetalle extends JPanel {


	private JTable tablaServiciosAgregados;
	private DefaultTableModel modelServiciosAgregados;
	private String[] nombreColumnasAgregadas = {"Servicio","Hora inicio", "Hora fin","Profesional"};
	private JScrollPane spServiciosAgregados;
	private static final long serialVersionUID = 1L;

	public panelDetalle() {
		initialize();
	}
	
	public void initialize() {
		setBounds(100, 100, 917, 151);
		setLayout(null);
		
		spServiciosAgregados = new JScrollPane();
		spServiciosAgregados.setBounds(553, 11, 354, 135);
		add(spServiciosAgregados);

		modelServiciosAgregados = new DefaultTableModel(null,nombreColumnasAgregadas) {

			private static final long serialVersionUID = 1L;

			//Para que las celdas de la tabla no se puedan editar
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		tablaServiciosAgregados = new JTable(modelServiciosAgregados);


		tablaServiciosAgregados.getColumnModel().getColumn(0).setPreferredWidth(103);
		tablaServiciosAgregados.getColumnModel().getColumn(0).setResizable(false);
		tablaServiciosAgregados.getColumnModel().getColumn(1).setPreferredWidth(103);
		tablaServiciosAgregados.getColumnModel().getColumn(1).setResizable(false);
		tablaServiciosAgregados.getColumnModel().getColumn(2).setPreferredWidth(103);
		tablaServiciosAgregados.getColumnModel().getColumn(2).setResizable(false);
		tablaServiciosAgregados.getColumnModel().getColumn(3).setPreferredWidth(103);
		tablaServiciosAgregados.getColumnModel().getColumn(3).setResizable(false);
		
		spServiciosAgregados.setViewportView(tablaServiciosAgregados);
		
		JLabel lblCliente = new JLabel("ID: ");
		lblCliente.setBounds(10, 12, 80, 20);
		add(lblCliente);
		
		JLabel label = new JLabel("Cliente:");
		label.setBounds(10, 43, 80, 20);
		add(label);
		
		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setBounds(10, 75, 80, 20);
		add(lblTotal);
		
		JLabel lblHoraDeInicio = new JLabel("Estado:");
		lblHoraDeInicio.setBounds(10, 106, 80, 20);
		add(lblHoraDeInicio);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(93, 12, 109, 20);
		add(lblNewLabel);
		
		JLabel label_1 = new JLabel("New label");
		label_1.setBounds(93, 43, 109, 20);
		add(label_1);
		
		JLabel label_2 = new JLabel("New label");
		label_2.setBounds(93, 75, 109, 20);
		add(label_2);
		
		JLabel label_3 = new JLabel("New label");
		label_3.setBounds(93, 106, 109, 20);
		add(label_3);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(212, 11, 295, 135);
		add(textPane);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(180, 11, 1, 135);
		add(separator);
		
	}

	public void ocultar() {
		setVisible(false);
		updateUI();
	}
}
