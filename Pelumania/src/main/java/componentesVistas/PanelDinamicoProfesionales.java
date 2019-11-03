package componentesVistas;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dto.ProfesionalDTO;

import javax.swing.JComboBox;

public class PanelDinamicoProfesionales extends JPanel {
	
	private JTable tablaServicios;
	private DefaultTableModel modelServicios;
	private String[] nombreColumnas = {"Nombre","Precio en $","Duracion",
			"Puntos que brinda", "Estado"};
	
	private JComboBox<ProfesionalDTO> JCBoxProfesional;

	private static final long serialVersionUID = 1L;

	public PanelDinamicoProfesionales() {
		initialize();
	}

	private void initialize() {
		setBounds(0, 0, 373, 168);
		setLayout(null);
		
		JScrollPane spServicios = new JScrollPane();
		spServicios.setBounds(0, 31, 373, 137);
		add(spServicios);
		
		
		modelServicios = new DefaultTableModel(null,nombreColumnas) {
			//Para que las celdas de la tabla no se puedan editar
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tablaServicios = new JTable(modelServicios);
//		RowsRendererBasic rr = new RowsRendererBasic(5);
//		tablaServicios.setDefaultRenderer(Object.class, rr);

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

		spServicios.setViewportView(tablaServicios);
		
		JCBoxProfesional = new JComboBox<ProfesionalDTO>();
		JCBoxProfesional.setBounds(66, 0, 232, 20);
		add(JCBoxProfesional);
		
		this.setVisible(true);
	}

	public JTable getTablaServicios() {
		return tablaServicios;
	}

	public void setTablaServicios(JTable tablaServicios) {
		this.tablaServicios = tablaServicios;
	}

	public DefaultTableModel getModelServicios() {
		return modelServicios;
	}

	public void setModelServicios(DefaultTableModel modelServicios) {
		this.modelServicios = modelServicios;
	}

	public String[] getNombreColumnas() {
		return nombreColumnas;
	}

	public void setNombreColumnas(String[] nombreColumnas) {
		this.nombreColumnas = nombreColumnas;
	}

	public JComboBox<ProfesionalDTO> getJCBoxProfesional() {
		return JCBoxProfesional;
	}

	public void setJCBoxProfesional(JComboBox<ProfesionalDTO> jCBoxProfesional) {
		JCBoxProfesional = jCBoxProfesional;
	}

}
