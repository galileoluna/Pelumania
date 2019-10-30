package componentesVistas;


import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PanelDinamicoPromociones extends JPanel{

	private static final long serialVersionUID = 1L;
	private JTable tablaPromocion;
	private DefaultTableModel modelPromocion;
	private  String[] nombreColumnas = {"Decripcion","Fecha Inicio","Fecha Fin","Descuento","Puntos","Estado"};
	
	
	public PanelDinamicoPromociones() {
		initialize();
	}

	private void initialize() {
		setBounds(100, 100, 232, 168);
		setLayout(null);
		
		JScrollPane spPersonas = new JScrollPane();
		spPersonas.setBounds(0, 0, 232, 168);
		add(spPersonas);
		
		modelPromocion = new DefaultTableModel(null,nombreColumnas);
		tablaPromocion = new JTable(modelPromocion);
		
		tablaPromocion.getColumnModel().getColumn(0).setPreferredWidth(103);
		tablaPromocion.getColumnModel().getColumn(0).setResizable(false);
		tablaPromocion.getColumnModel().getColumn(1).setPreferredWidth(100);
		tablaPromocion.getColumnModel().getColumn(1).setResizable(false);
		
		spPersonas.setViewportView(tablaPromocion);
	}

}
