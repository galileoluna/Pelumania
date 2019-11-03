package componentesVistas;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import dto.ProfesionalDTO;
import util.TextPrompt;

import java.awt.Component;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;

//import util.RowsRendererBasic;

public class PanelDinamicoServicios extends JPanel{
	
	private JTable tablaServicios;
	private DefaultTableModel modelServicios;
	private String[] nombreColumnas = {"Nombre","Precio en $","Duracion",
			"Puntos que brinda", "Estado"};

	private static final long serialVersionUID = 1L;
	private JTextField txtbuscarServicios;
	private JComboBox<ProfesionalDTO> JCBoxProfesionalesDeServicio;
	private JButton btnBuscarServicio;
	private JButton btnLimpiarBusqueda;

	public PanelDinamicoServicios() {
		initialize();
	}

	private void initialize() {
		setBounds(100, 100, 372, 168);
		setLayout(null);
		
		txtbuscarServicios = new JTextField();
		txtbuscarServicios.setHorizontalAlignment(SwingConstants.CENTER);
		txtbuscarServicios.setBounds(0, 0, 319, 26);
		add(txtbuscarServicios);
		txtbuscarServicios.setColumns(10);
		
		TextPrompt placeholder = new TextPrompt("[Buscar Servicio]", txtbuscarServicios);
		placeholder.changeAlpha(0.75f);
		placeholder.changeStyle(Font.ITALIC);
		
		btnBuscarServicio = new JButton("");
		btnBuscarServicio.setBounds(323, 0, 26, 26);
		add(btnBuscarServicio);
		
		btnLimpiarBusqueda = new JButton("");
		btnLimpiarBusqueda.setBounds(346, 0, 26, 26);
		add(btnLimpiarBusqueda);
			
		JScrollPane spServicios = new JScrollPane();
		spServicios.setBounds(0, 36, 372, 105);
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
		
		JCBoxProfesionalesDeServicio = new JComboBox<ProfesionalDTO>();
		JCBoxProfesionalesDeServicio .setBounds(74, 148, 232, 20);
		add(JCBoxProfesionalesDeServicio );
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

	public JTextField getTxtbuscarServicios() {
		return txtbuscarServicios;
	}

	public void setTxtbuscarServicios(JTextField txtbuscarServicios) {
		this.txtbuscarServicios = txtbuscarServicios;
	}

	public JComboBox<ProfesionalDTO> getJCBoxProfesionalesDeServicio() {
		return JCBoxProfesionalesDeServicio;
	}

	public void setJCBoxProfesionalesDeServicio(JComboBox<ProfesionalDTO> jCBoxProfesionalesDeServicio) {
		JCBoxProfesionalesDeServicio = jCBoxProfesionalesDeServicio;
	}

	public JButton getBtnBuscarServicio() {
		return btnBuscarServicio;
	}

	public void setBtnBuscarServicio(JButton btnBuscarServicio) {
		this.btnBuscarServicio = btnBuscarServicio;
	}

	public JButton getBtnLimpiarBusqueda() {
		return btnLimpiarBusqueda;
	}

	public void setBtnLimpiarBusqueda(JButton btnLimpiarBusqueda) {
		this.btnLimpiarBusqueda = btnLimpiarBusqueda;
	}
	
	
}