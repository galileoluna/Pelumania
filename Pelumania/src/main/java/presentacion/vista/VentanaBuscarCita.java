package presentacion.vista;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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

import dto.CitaDTO;

public class VentanaBuscarCita extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static VentanaBuscarCita INSTANCE;

	private JButton btn_Cancelar;
	private JTextField textField;
	private String[] nombreColumnas = {"Nombre", "Apellido", "Dia","HoraInicio","HoraFin","PrecioLocal","PrecioDolar"};
	private JTable tablaCitas;
	private DefaultTableModel modelCitas;

	JComboBox<String> CBoxBuscarPor;

	private static List<String> filtrosColumnas = new ArrayList<String>(Arrays.asList("ID","Nombre","Apellido"));
	private JButton btnSeleccionarCita;

	public static VentanaBuscarCita getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new VentanaBuscarCita();
			return new VentanaBuscarCita();
		} else {
			return INSTANCE;
		}
	}
	
	private VentanaBuscarCita()
	{
		super();

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 735, 318);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 699, 257);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblBuscarPor = new JLabel("Buscar por: ");
		lblBuscarPor.setBounds(23, 11, 133, 23);
		panel.add(lblBuscarPor);

		CBoxBuscarPor = new JComboBox<String>();
		CBoxBuscarPor.setBounds(162, 11, 208, 23);
		panel.add(CBoxBuscarPor);
		cargarDesplegables();

		textField = new JTextField();
		textField.setBounds(23, 45, 347, 23);
		panel.add(textField);
		textField.setColumns(10);

		JScrollPane spCitas = new JScrollPane();
		spCitas.setBounds(23, 79, 666, 143);
		panel.add(spCitas);

		modelCitas = new DefaultTableModel(null,nombreColumnas);
		tablaCitas = new JTable(modelCitas);

		tablaCitas.getColumnModel().getColumn(0).setPreferredWidth(10);
		tablaCitas.getColumnModel().getColumn(0).setResizable(false);
		tablaCitas.getColumnModel().getColumn(1).setPreferredWidth(10);
		tablaCitas.getColumnModel().getColumn(1).setResizable(false);
		tablaCitas.getColumnModel().getColumn(2).setPreferredWidth(100);
		tablaCitas.getColumnModel().getColumn(2).setResizable(false);
		tablaCitas.getColumnModel().getColumn(3).setPreferredWidth(10);
		tablaCitas.getColumnModel().getColumn(3).setResizable(false);
		tablaCitas.getColumnModel().getColumn(4).setPreferredWidth(10);
		tablaCitas.getColumnModel().getColumn(4).setResizable(false);

		spCitas.setViewportView(tablaCitas);

		btnSeleccionarCita = new JButton("Seleccionar Cita");
		btnSeleccionarCita.setBounds(571, 233, 118, 23);
		panel.add(btnSeleccionarCita);

		this.setVisible(false);
	}

	public static VentanaBuscarCita getINSTANCE() {
		return INSTANCE;
	}

	public static void setINSTANCE(VentanaBuscarCita iNSTANCE) {
		INSTANCE = iNSTANCE;
	}

	public JButton getBtnSeleccionarCita() {
		return btnSeleccionarCita;
	}

	public void setBtnSeleccionarCita(JButton btnSeleccionarCita) {
		this.btnSeleccionarCita = btnSeleccionarCita;
	}

	public JButton getBtn_Cancelar() {
		return btn_Cancelar;
	}

	public void setBtn_Cancelar(JButton btn_Cancelar) {
		this.btn_Cancelar = btn_Cancelar;
	}

	public JTable getTablaCitas() {
		return tablaCitas;
	}

	public void setTablaCitas(JTable tablaCitas) {
		this.tablaCitas = tablaCitas;
	}

	public DefaultTableModel getModelCitas() {
		return modelCitas;
	}

	
	public void mostrarVentana()
	{
		this.setVisible(true);
	}
	
	public void cargarDesplegables() {
		for (String campo : filtrosColumnas) {
			CBoxBuscarPor.addItem(campo);
		}
	} 

	public void llenarTabla(List<CitaDTO> citasEnTabla) {
		this.getModelCitas().setRowCount(0); //Para vaciar la tabla
		this.getModelCitas().setColumnCount(0);
		this.getModelCitas().setColumnIdentifiers(this.getNombreColumnas());

		for (CitaDTO cita : citasEnTabla)
		{
			String nombre = cita.getNombre();
			String apellido = cita.getApellido();
			String dia = cita.getFecha().toString();
			String horaInicio = cita.getHoraInicio().toString();
			BigDecimal precioPesos = cita.getPrecioLocal();
			BigDecimal precioDolar = cita.getPrecioDolar();
			Object[] fila = {nombre, apellido, dia, horaInicio, precioPesos, precioDolar};
			this.getModelCitas().addRow(fila);
		}

	}

	private String[] getNombreColumnas() {
		return nombreColumnas;
	}

	public void cerrar()
	{
		this.dispose();
	}
}