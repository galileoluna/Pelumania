package presentacion.vista;

import java.awt.Font;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dto.ServicioDTO;

public class VentanaServicio extends JFrame{

	private static VentanaServicio INSTANCE;
	private JTable tablaServicios;
	private DefaultTableModel modelServicios;
	private String[] nombreColumnas = {"Nombre","Precio en $",
			"Precio en USD","Duracion", "Puntos que brinda", "Estado"};


	private JButton btnBuscar;
	private JTextField buscador;
	private JComboBox variableBuscar;


	private JButton btnAgregar;
	private JButton btnEditar;
	private JButton btnBorrar;
	private JButton btnVerTodo;

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
		} else {
			return INSTANCE;
		}
	}

	private void initialize()
	{
		setBounds(100, 100, 739, 406);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		setTitle("Manejo de Servicios");

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 713, 356);
		getContentPane().add(panel);
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
		btnAgregar.setBounds(10, 322, 89, 23);
		panel.add(btnAgregar);

		btnEditar = new JButton("Editar");
		btnEditar.setBounds(120, 322, 89, 23);
		panel.add(btnEditar);

		btnBorrar = new JButton("Borrar");
		btnBorrar.setBounds(230, 322, 89, 23);
		panel.add(btnBorrar);
		
		JLabel lblBuscarPor = new JLabel("Buscar por:");
		lblBuscarPor.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblBuscarPor.setBounds(350, 322, 200, 23);
		panel.add(lblBuscarPor);
		
		variableBuscar = new JComboBox();
		variableBuscar.setBounds(430, 322, 89, 23);
		panel.add(variableBuscar);
		variableBuscar.addItem("Todos");
		variableBuscar.addItem("Nombre");
		variableBuscar.addItem("Estado");
		
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(610, 322, 89, 23);
		panel.add(btnBuscar);
		
		buscador = new JTextField();
		buscador.setBounds(520, 322, 89, 23);
		panel.add(buscador);
		buscador.setColumns(10);
		
		
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
		setVisible(true);
	}

	public void cerrar() {
		this.dispose();
	}
}
