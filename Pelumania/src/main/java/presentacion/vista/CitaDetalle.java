package presentacion.vista;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import util.RowsRendererBasic;

public class CitaDetalle extends JPanel{
	
	private String[] nombreColumnas = {"Servicio","Hora Inicio",
			"Hora Fin","Profesional"};
	private JTextField textField;

	public CitaDetalle() {
		initialize();
	}


	private void initialize() {
		setBounds(100, 100, 917, 151);
		setLayout(null);
		
		JScrollPane spServicios = new JScrollPane();
		spServicios.setBounds(502, 10, 405, 130);
		add(spServicios);
		
		
		DefaultTableModel modelServicios = new DefaultTableModel(null,nombreColumnas) {
			//Para que las celdas de la tabla no se puedan editar
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		JTable tablaServicios = new JTable(modelServicios);
		RowsRendererBasic rr = new RowsRendererBasic(5);
		tablaServicios.setDefaultRenderer(Object.class, rr);

		tablaServicios.getColumnModel().getColumn(0).setPreferredWidth(103);
		tablaServicios.getColumnModel().getColumn(0).setResizable(false);
		tablaServicios.getColumnModel().getColumn(1).setPreferredWidth(100);
		tablaServicios.getColumnModel().getColumn(1).setResizable(false);
		tablaServicios.getColumnModel().getColumn(2).setPreferredWidth(100);
		tablaServicios.getColumnModel().getColumn(2).setResizable(false);
		tablaServicios.getColumnModel().getColumn(3).setPreferredWidth(100);
		tablaServicios.getColumnModel().getColumn(3).setResizable(false);
		tablaServicios.getColumnModel().getColumn(4).setPreferredWidth(100);
		tablaServicios.getColumnModel().getColumn(4).setResizable(false);
		tablaServicios.getColumnModel().getColumn(5).setPreferredWidth(100);
		tablaServicios.getColumnModel().getColumn(5).setResizable(false);

		spServicios.setViewportView(tablaServicios);
		
		JLabel lblId = new JLabel("Id");
		lblId.setBounds(31, 25, 78, 14);
		add(lblId);
		
		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setBounds(31, 50, 46, 14);
		add(lblCliente);
		
		JLabel lblHoraInicio = new JLabel("Hora Inicio");
		lblHoraInicio.setBounds(31, 87, 93, 14);
		add(lblHoraInicio);
		
		JLabel lblHoraFin = new JLabel("Hora Fin");
		lblHoraFin.setBounds(31, 126, 93, 14);
		add(lblHoraFin);
		
		JLabel lblid = new JLabel("[id]");
		lblid.setBounds(135, 25, 60, 14);
		add(lblid);
		
		JLabel lblcliente = new JLabel("[Cliente]");
		lblcliente.setBounds(135, 50, 66, 14);
		add(lblcliente);
		
		JLabel lblhorainicio = new JLabel("[HoraInicio]");
		lblhorainicio.setBounds(135, 87, 93, 14);
		add(lblhorainicio);
		
		JLabel lblhorafin = new JLabel("[HoraFin]");
		lblhorafin.setBounds(134, 126, 93, 14);
		add(lblhorafin);
		
		textField = new JTextField();
		textField.setBounds(211, 10, 272, 130);
		add(textField);
		textField.setColumns(10);
	}
}
