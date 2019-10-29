package presentacion.vista;

import java.awt.Color;
import java.awt.Font;
import java.sql.Date;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import dto.SucursalDTO;

public class nuevaVentanaCita {

	private static nuevaVentanaCita INSTANCE;
	
	
	/* *************************************************************************************
	 * ***************** COMPONENTES VISUALES DE LA VISTA **********************************
	 * *************************************************************************************/
	
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
	private JRadioButton rdbtnPromocion;
	private JPanel JPnl_DinamicoServicios;
	private JComponent JPnl_Datos;
	private JLabel lblAlertaSucursal;
	
	private JTable tablaServiciosAgregados;
	private DefaultTableModel modelServiciosAgregados;
	private String[] nombreColumnasAgregadas = {"Nombre","Profesional"};
	private JScrollPane spServiciosAgregados;
	
	/* *************************************************************************************
	 * ******************* VARIABLES PARA MANEJAR DATOS ************************************
	 * *************************************************************************************/
	
	private LocalDate fechaCita;
	private SucursalDTO sucursal;

	
	public static nuevaVentanaCita getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new nuevaVentanaCita();
		}
		INSTANCE.frame.setVisible(true);
		return INSTANCE;	
		
		}
	
	
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
		
		crearRadioButtonPromocion();
		
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
		frame = new JFrame();
		frame.setBounds(100, 100, 569, 730);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Nueva Cita");
	}

	private void crearPanelDatosGlobales() {
		JPnl_DatosGlobales = new JPanel();
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
		lblNombre.setBounds(113, 45, 71, 14);
		JPnl_Cliente.add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(269, 45, 71, 14);
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
		rdBtnProfesional.setBounds(247, 33, 96, 23);
		JPnl_Servicios.add(rdBtnProfesional);
	}

	private void crearRadioButtonServicio() {
		rdBtnServicio = new JRadioButton("Servicio");
		rdBtnServicio.setBounds(345, 33, 74, 23);
		JPnl_Servicios.add(rdBtnServicio);
	}
	
	private void crearRadioButtonPromocion() {
		rdbtnPromocion = new JRadioButton("Promocion");
		rdbtnPromocion.setBounds(421, 33, 112, 23);
		JPnl_Servicios.add(rdbtnPromocion);
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
		Color amarilloOpaco = new Color(249,221,140);
		lblAlertaSucursal = new JLabel("Estas dando de alta una cita en una sucursal distinta a la tuya!");
		lblAlertaSucursal.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlertaSucursal.setBounds(1, 85, 531, 29);
		lblAlertaSucursal.setBackground(amarilloOpaco);
		lblAlertaSucursal.setOpaque(true);
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
	
	/* *************************************************************************************
	 * **************** METODOS PARA LOS DATOS EN LA VISTA *********************************
	 * *************************************************************************************/
	
	public void cargarFecha(LocalDate fecha) {
		JDChooserFecha.setDate(Date.valueOf(fecha));
	}

	public LocalDate getFechaCita() {
		return fechaCita;
	}

	public void setFechaCita(LocalDate fecha) {
		this.fechaCita = fecha;
		cargarFecha(fechaCita);
		JDChooserFecha.setEnabled(false);
	}
	
	public void habilitarJDateChooser() {
		JDChooserFecha.setEnabled(true);
	}
	/* *************************************************************************************
	 * ******************** GETTERS Y SETTERS DE LOS COMPONENTES ***************************
	 * *************************************************************************************/

	public JTextField getTxtNombre() {
		return TxtNombre;
	}


	public void setTxtNombre(JTextField txtNombre) {
		TxtNombre = txtNombre;
	}


	public JTextField getTxtApellido() {
		return TxtApellido;
	}


	public void setTxtApellido(JTextField txtApellido) {
		TxtApellido = txtApellido;
	}


	public JTextField getTxtTelefono() {
		return TxtTelefono;
	}


	public void setTxtTelefono(JTextField txtTelefono) {
		TxtTelefono = txtTelefono;
	}


	public JTextField getTxtMail() {
		return TxtMail;
	}


	public void setTxtMail(JTextField txtMail) {
		TxtMail = txtMail;
	}


	public JTextField getTxtDescripcion() {
		return txtDescripcion;
	}


	public void setTxtDescripcion(JTextField txtDescripcion) {
		this.txtDescripcion = txtDescripcion;
	}


	public JComboBox<SucursalDTO> getJCBoxSucursal() {
		return JCBoxSucursal;
	}


	public void setJCBoxSucursal(JComboBox<SucursalDTO> jCBoxSucursal) {
		JCBoxSucursal = jCBoxSucursal;
	}


	public JDateChooser getJDChooserFecha() {
		return JDChooserFecha;
	}


	public void setJDChooserFecha(JDateChooser jDChooserFecha) {
		JDChooserFecha = jDChooserFecha;
	}


	public JButton getBtnEditarFecha() {
		return btnEditarFecha;
	}


	public void setBtnEditarFecha(JButton btnEditarFecha) {
		this.btnEditarFecha = btnEditarFecha;
	}


	public JCheckBox getChckbxRegistrado() {
		return chckbxRegistrado;
	}


	public void setChckbxRegistrado(JCheckBox chckbxRegistrado) {
		this.chckbxRegistrado = chckbxRegistrado;
	}


	public JCheckBox getChckbxGenerico() {
		return chckbxGenerico;
	}


	public void setChckbxGenerico(JCheckBox chckbxGenerico) {
		this.chckbxGenerico = chckbxGenerico;
	}


	public JComboBox<Integer> getJCBoxHora() {
		return JCBoxHora;
	}


	public void setJCBoxHora(JComboBox<Integer> jCBoxHora) {
		JCBoxHora = jCBoxHora;
	}


	public JComboBox<Integer> getJCBoxMinutos() {
		return JCBoxMinutos;
	}


	public void setJCBoxMinutos(JComboBox<Integer> jCBoxMinutos) {
		JCBoxMinutos = jCBoxMinutos;
	}


	public JRadioButton getRdBtnProfesional() {
		return rdBtnProfesional;
	}


	public void setRdBtnProfesional(JRadioButton rdBtnProfesional) {
		this.rdBtnProfesional = rdBtnProfesional;
	}


	public JRadioButton getRdBtnServicio() {
		return rdBtnServicio;
	}


	public void setRdBtnServicio(JRadioButton rdBtnServicio) {
		this.rdBtnServicio = rdBtnServicio;
	}


	public JRadioButton getRdbtnPromocion() {
		return rdbtnPromocion;
	}


	public void setRdbtnPromocion(JRadioButton rdbtnPromocion) {
		this.rdbtnPromocion = rdbtnPromocion;
	}


	public JPanel getJPnl_DinamicoServicios() {
		return JPnl_DinamicoServicios;
	}


	public void setJPnl_DinamicoServicios(JPanel jPnl_DinamicoServicios) {
		JPnl_DinamicoServicios = jPnl_DinamicoServicios;
	}


	public JTable getTablaServiciosAgregados() {
		return tablaServiciosAgregados;
	}


	public void setTablaServiciosAgregados(JTable tablaServiciosAgregados) {
		this.tablaServiciosAgregados = tablaServiciosAgregados;
	}


	public DefaultTableModel getModelServiciosAgregados() {
		return modelServiciosAgregados;
	}


	public void setModelServiciosAgregados(DefaultTableModel modelServiciosAgregados) {
		this.modelServiciosAgregados = modelServiciosAgregados;
	}


	public String[] getNombreColumnasAgregadas() {
		return nombreColumnasAgregadas;
	}


	public void setNombreColumnasAgregadas(String[] nombreColumnasAgregadas) {
		this.nombreColumnasAgregadas = nombreColumnasAgregadas;
	}


	public JScrollPane getSpServiciosAgregados() {
		return spServiciosAgregados;
	}


	public void setSpServiciosAgregados(JScrollPane spServiciosAgregados) {
		this.spServiciosAgregados = spServiciosAgregados;
	}


	public SucursalDTO getSucursal() {
		return sucursal;
	}


	public void setSucursal(SucursalDTO sucursal) {
		this.sucursal = sucursal;
	}


	public JLabel getLblAlertaSucursal() {
		return lblAlertaSucursal;
	}


	public void setLblAlertaSucursal(JLabel lblAlertaSucursal) {
		this.lblAlertaSucursal = lblAlertaSucursal;
	}
}
