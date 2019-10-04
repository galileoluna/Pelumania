package presentacion.vista;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import javax.swing.table.DefaultTableModel;

import dto.ServicioDTO;

public class VentanaServicio{

	JFrame frame;
	private static VentanaServicio INSTANCE;
	private JTable tablaServicios;
	private DefaultTableModel modelServicios;
	private String[] nombreColumnas = {"Nombre","Precio en $",
	"Precio en USD","Duracion", "Puntos que brinda", "Estado"};


	private JButton btnAgregar;
	private JButton btnEditar;
	private JButton btnBorrar;

	public VentanaServicio() 
	{
		super();
		initialize();
	}
	
	public static VentanaServicio getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new VentanaServicio(); 	
			return new VentanaServicio();
		}
		else
			return INSTANCE;
	}

	private void initialize() 
	{
		frame = new JFrame();
		frame.setBounds(100, 100, 739, 406);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Manejo de Servicios");
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 713, 356);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane spServicios = new JScrollPane();
		spServicios.setBounds(10, 11, 693, 277);
		panel.add(spServicios);

		modelServicios = new DefaultTableModel(null,nombreColumnas) {
			//Para que las celdas de la tabla no se puedan editar
		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};
		tablaServicios = new JTable(modelServicios);


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

	public void llenarTabla(List<ServicioDTO> serviciosEnTabla) {
		this.getModelServicios().setRowCount(0); //Para vaciar la tabla
		this.getModelServicios().setColumnCount(0);
		this.getModelServicios().setColumnIdentifiers(this.getNombreColumnas());

		for (ServicioDTO s : serviciosEnTabla)
		{
			String nombre = s.getNombre();
			BigDecimal precioLocal = s.getPrecioLocal();
			BigDecimal precioDolar = s.getPrecioDolar();
			LocalTime duracion = s.getDuracion();
			int puntos = s.getPuntos();
			String estado = s.getEstado();
			Object[] fila = {nombre, precioLocal, precioDolar, duracion, puntos, estado};
			this.getModelServicios().addRow(fila);
		}
	}
	public void mostrar() {
		frame.setVisible(true);
		
	}
}
