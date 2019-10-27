package presentacion.vista;

import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import dto.SucursalDTO;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

public class nuevaVentanaCita {

	private JFrame frame;
	private JTextField TxtNombre;
	private JTextField TxtApellido;
	private JTextField TxtTelefono;
	private JTextField TxtMail;
	private JTextField txtDescripcion;
	private JPanel JPnl_DatosGlobales;
	private JComboBox<SucursalDTO> JCBoxSucursal;
	private JDateChooser JDChooserFecha;
	private JButton btnEditarFecha;
	private JPanel JPnl_Cliente;
	private JCheckBox chckbxRegistrado;
	private JCheckBox chckbxGenerico;
	private JPanel JPnl_Servicios;
	private JComboBox<Integer> JCBoxHora;
	private JComboBox<Integer> JCBoxMinutos;
	private JRadioButton rdBtnProfesional;
	private JRadioButton rdBtnServicio;
	private JPanel JPnl_DinamicoServicios;
	private JComponent JPnl_Datos;
	
	private JTable tablaServiciosAgregados;
	private DefaultTableModel modelServiciosAgregados;
	private String[] nombreColumnasAgregadas = {"Nombre","Profesional"};
	private JScrollPane spServiciosAgregados;
	
	public nuevaVentanaCita() {
		initialize();
	}

	private void initialize() {
		crearFrame();
		
		crearPanelDatosGlobales();
		
		crearTituloDatosGlobales();
		
		crearLabelsDatosGlobales();
		
		crearJCBoxSucursal();
		
		crearJDateChooserFecha();
		
		crearBotonEditarFecha();
		
		crearPanelCliente();
		
		crearTituloCliente();
		
		crearCheckBoxexCliente();
		
		crearLabelsDatosCliente();
		
		crearTxtNombre();
		
		crearTxtApellido();
		
		crearTxtTelefono();
		
		crearTxtMail();
		
		crearBotonRegistrar();
		
		crearBotonBuscar();
		
		crearPanelServicios();
		
		crearTituloServicios();
		
		crearLabelHoraInicio();
		
		crearJCBoxHora();
		
		crearJCBoxMinutos();
		
		crearRadioButtonProfesional();
		
		crearRadioButtonServicio();
		
		crearPanelDinamicoServicios();
		
		crearTxtDescripcion();
		
		crearLabelServiciosAgregados();
		
		crearBotonAgregarServicio();
		
		crearBotonEliminarServicio();
		
		crearPanelDatos();
		
		crearTituloDatosDeLaCita();
		
		crearLabelInicio();
		
		crearLabelFin();
		
		crearLabelTotal();
		
		crearLabelTotalUSD();
		
		crearLabelAlertaSucursal();
		
		crearTablaServiciosAgregados();
		
		crearBotonConfirmar();
		
		crearBotonCancelar();
	}

	private void crearJDateChooserFecha() {
		JDChooserFecha = new JDateChooser();
		JDChooserFecha.setBounds(92, 77, 120, 20);
		JPnl_DatosGlobales.add(JDChooserFecha);
	}

	private void crearFrame() {
		frame.setTitle("Nueva Cita");
		frame = new JFrame();
		frame.setBounds(100, 100, 569, 730);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
	}

	private void crearPanelDatosGlobales() {
		JPnl_DatosGlobales = new JPanel();
		JPnl_DatosGlobales.setBorder(new LineBorder(new Color(0, 0, 0)));
		JPnl_DatosGlobales.setBounds(10, 11, 533, 105);
		frame.getContentPane().add(JPnl_DatosGlobales);
		JPnl_DatosGlobales.setLayout(null);
	}

	private void crearLabelsDatosGlobales() {
		JLabel lblSucursal = new JLabel("Sucursal");
		lblSucursal.setBounds(10, 46, 120, 20);
		JPnl_DatosGlobales.add(lblSucursal);
		
		JLabel lblFecha = new JLabel("Fecha: ");
		lblFecha.setBounds(10, 77, 120, 20);
		JPnl_DatosGlobales.add(lblFecha);
	}

	private void crearTituloDatosGlobales() {
		JLabel lblDatosGlobales = new JLabel("Datos Globales");
		lblDatosGlobales.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDatosGlobales.setBounds(10, 0, 494, 30);
		JPnl_DatosGlobales.add(lblDatosGlobales);
		
		JSeparator spr_DatosGlobales = new JSeparator();
		spr_DatosGlobales.setBounds(10, 28, 494, 2);
		JPnl_DatosGlobales.add(spr_DatosGlobales);
	}

	private void crearJCBoxSucursal() {
		JCBoxSucursal = new JComboBox<SucursalDTO>();
		JCBoxSucursal.setBounds(92, 46, 120, 20);
		JPnl_DatosGlobales.add(JCBoxSucursal);
	}

	private void crearBotonEditarFecha() {
		btnEditarFecha = new JButton("Editar");
		btnEditarFecha.setBounds(231, 76, 89, 23);
		JPnl_DatosGlobales.add(btnEditarFecha);
	}

	private void crearPanelCliente() {
		JPnl_Cliente = new JPanel();
		JPnl_Cliente.setBorder(new LineBorder(new Color(0, 0, 0)));
		JPnl_Cliente.setBounds(10, 115, 533, 136);
		frame.getContentPane().add(JPnl_Cliente);
		JPnl_Cliente.setLayout(null);
	}

	private void crearTituloCliente() {
		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCliente.setBounds(10, 0, 494, 30);
		JPnl_Cliente.add(lblCliente);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 28, 494, 2);
		JPnl_Cliente.add(separator);
	}

	private void crearCheckBoxexCliente() {
		chckbxRegistrado = new JCheckBox("Registrado");
		chckbxRegistrado.setBounds(10, 41, 97, 23);
		JPnl_Cliente.add(chckbxRegistrado);
		
		chckbxGenerico = new JCheckBox("Generico");
		chckbxGenerico.setBounds(10, 67, 97, 23);
		JPnl_Cliente.add(chckbxGenerico);
	}

	private void crearLabelsDatosCliente() {
		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setBounds(113, 72, 97, 23);
		JPnl_Cliente.add(lblTelefono);
		
		JLabel lblMail = new JLabel("Mail:");
		lblMail.setBounds(269, 72, 97, 23);
		JPnl_Cliente.add(lblMail);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(113, 45, 46, 14);
		JPnl_Cliente.add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(269, 45, 46, 14);
		JPnl_Cliente.add(lblApellido);
	}

	private void crearTxtNombre() {
		TxtNombre = new JTextField();
		TxtNombre.setBounds(169, 42, 86, 20);
		JPnl_Cliente.add(TxtNombre);
		TxtNombre.setColumns(10);
	}

	private void crearTxtApellido() {
		TxtApellido = new JTextField();
		TxtApellido.setBounds(325, 42, 86, 20);
		JPnl_Cliente.add(TxtApellido);
		TxtApellido.setColumns(10);
	}

	private void crearTxtTelefono() {
		TxtTelefono = new JTextField();
		TxtTelefono.setBounds(169, 73, 86, 20);
		JPnl_Cliente.add(TxtTelefono);
		TxtTelefono.setColumns(10);
	}

	private void crearTxtMail() {
		TxtMail = new JTextField();
		TxtMail.setBounds(325, 73, 198, 20);
		JPnl_Cliente.add(TxtMail);
		TxtMail.setColumns(10);
	}

	private void crearBotonRegistrar() {
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.setBounds(314, 106, 97, 23);
		JPnl_Cliente.add(btnRegistrar);
	}

	private void crearBotonBuscar() {
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(426, 104, 97, 23);
		JPnl_Cliente.add(btnBuscar);
	}

	private void crearPanelServicios() {
		JPnl_Servicios = new JPanel();
		JPnl_Servicios.setBorder(new LineBorder(new Color(0, 0, 0)));
		JPnl_Servicios.setBounds(10, 249, 533, 282);
		frame.getContentPane().add(JPnl_Servicios);
		JPnl_Servicios.setLayout(null);
	}

	private void crearTituloServicios() {
		JLabel lblServicios = new JLabel("Servicios");
		lblServicios.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblServicios.setBounds(10, 0, 494, 30);
		JPnl_Servicios.add(lblServicios);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 28, 494, 2);
		JPnl_Servicios.add(separator_1);
	}

	private void crearLabelHoraInicio() {
		JLabel lblHoraInicio = new JLabel("Hora Inicio");
		lblHoraInicio.setBounds(10, 34, 84, 20);
		JPnl_Servicios.add(lblHoraInicio);
	}

	private void crearJCBoxHora() {
		JCBoxHora = new JComboBox<Integer>();
		JCBoxHora.setBounds(104, 34, 49, 20);
		JPnl_Servicios.add(JCBoxHora);
	}

	private void crearJCBoxMinutos() {
		JCBoxMinutos = new JComboBox<Integer>();
		JCBoxMinutos.setBounds(163, 34, 49, 20);
		JPnl_Servicios.add(JCBoxMinutos);
	}

	private void crearRadioButtonProfesional() {
		rdBtnProfesional = new JRadioButton("Profesional");
		rdBtnProfesional.setBounds(256, 33, 109, 23);
		JPnl_Servicios.add(rdBtnProfesional);
	}

	private void crearRadioButtonServicio() {
		rdBtnServicio = new JRadioButton("Servicio");
		rdBtnServicio.setBounds(395, 33, 109, 23);
		JPnl_Servicios.add(rdBtnServicio);
	}

	private void crearPanelDinamicoServicios() {
		JPnl_DinamicoServicios = new JPanel();
		JPnl_DinamicoServicios.setBorder(new LineBorder(new Color(0, 0, 0)));
		JPnl_DinamicoServicios.setBounds(10, 65, 232, 157);
		JPnl_Servicios.add(JPnl_DinamicoServicios);
	}

	private void crearTxtDescripcion() {
		txtDescripcion = new JTextField();
		txtDescripcion.setHorizontalAlignment(SwingConstants.CENTER);
		txtDescripcion.setText("[Descripcion de la cita]");
		txtDescripcion.setBounds(8, 233, 515, 38);
		
		JPnl_Servicios.add(txtDescripcion);
		txtDescripcion.setColumns(10);
	}

	private void crearLabelServiciosAgregados() {
		JLabel lblServiciosAgregados = new JLabel("Servicios Agregados");
		lblServiciosAgregados.setHorizontalAlignment(SwingConstants.CENTER);
		lblServiciosAgregados.setBounds(282, 63, 241, 20);
		JPnl_Servicios.add(lblServiciosAgregados);
	}

	private void crearBotonAgregarServicio() {
		JButton btnAgregarServicio = new JButton("");
		btnAgregarServicio.setBounds(249, 120, 25, 25);
		JPnl_Servicios.add(btnAgregarServicio);
	}

	private void crearBotonEliminarServicio() {
		JButton btnEliminarServicio = new JButton("");
		btnEliminarServicio.setBounds(249, 156, 25, 25);
		JPnl_Servicios.add(btnEliminarServicio);
	}

	private void crearPanelDatos() {
		JPnl_Datos = new JPanel();
		JPnl_Datos.setBorder(new LineBorder(new Color(0, 0, 0)));
		JPnl_Datos.setBounds(10, 531, 533, 115);
		frame.getContentPane().add(JPnl_Datos);
		JPnl_Datos.setLayout(null);
	}

	private void crearTituloDatosDeLaCita() {
		JLabel lblDatosDeLaCita = new JLabel("Datos de la cita");
		lblDatosDeLaCita.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDatosDeLaCita.setBounds(10, 0, 494, 30);
		JPnl_Datos.add(lblDatosDeLaCita);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(10, 28, 494, 2);
		JPnl_Datos.add(separator_2);
	}

	private void crearLabelInicio() {
		JLabel lblInicio = new JLabel("Inicio:");
		lblInicio.setBounds(10, 41, 86, 14);
		JPnl_Datos.add(lblInicio);
	}

	private void crearLabelFin() {
		JLabel lblFin = new JLabel("Fin:");
		lblFin.setBounds(10, 66, 86, 14);
		JPnl_Datos.add(lblFin);
	}

	private void crearLabelTotal() {
		JLabel lblTotal = new JLabel("Total: $");
		lblTotal.setBounds(211, 41, 73, 14);
		JPnl_Datos.add(lblTotal);
	}

	private void crearLabelTotalUSD() {
		JLabel lblTotalUsd = new JLabel("Total: USD");
		lblTotalUsd.setBounds(211, 66, 73, 14);
		JPnl_Datos.add(lblTotalUsd);
	}

	private void crearLabelAlertaSucursal() {
		JLabel lblAlertaSucursal = new JLabel("[Pop-up Otra sucursal]");
		lblAlertaSucursal.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlertaSucursal.setBounds(10, 85, 494, 30);
		JPnl_Datos.add(lblAlertaSucursal);
	}

	private void crearBotonConfirmar() {
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBounds(294, 657, 89, 23);
		frame.getContentPane().add(btnConfirmar);
	}

	private void crearBotonCancelar() {
		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.setBounds(393, 657, 89, 23);
		frame.getContentPane().add(btnNewButton);
	}
	
	private void crearTablaServiciosAgregados() {
		spServiciosAgregados = new JScrollPane();
		spServiciosAgregados.setBounds(282, 83, 241, 139);
		JPnl_Servicios.add(spServiciosAgregados);
		
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
		
		spServiciosAgregados.setViewportView(tablaServiciosAgregados);
	}

}
