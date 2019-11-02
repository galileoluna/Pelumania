package presentacion.vista;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
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

import componentesVistas.PanelDinamicoProfesionales;
import componentesVistas.PanelDinamicoPromociones;
import componentesVistas.PanelDinamicoServicios;
import dto.ClienteDTO;
import dto.ServicioTurnoDTO;
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
	private JButton btnBuscar;
	private JButton btnRegistrar;
	private JPanel JPnl_Servicios;
	private JComboBox<Integer> JCBoxHora;
	private JComboBox<Integer> JCBoxMinutos;
	private JRadioButton rdBtnProfesional;
	private JRadioButton rdBtnServicio;
	private JRadioButton rdbtnPromocion;
	private JPanel JPnl_DinamicoServicios;
		private PanelDinamicoServicios panelDinamicoServicios;
		private PanelDinamicoProfesionales panelDinamicoProfesionales;
		private PanelDinamicoPromociones panelDinamicoPromociones;
	private JComponent JPnl_Datos;
		private JLabel lbl_Inicio;
		private JLabel lbl_Fin;
		private JLabel lbl_Total;
		private JLabel lbl_TotalUSD;
		private JLabel lblAlertaSucursal;
	
	private JTable tablaServiciosAgregados;
	private DefaultTableModel modelServiciosAgregados;
	private String[] nombreColumnasAgregadas = {"Nombre","Profesional"};
	private JScrollPane spServiciosAgregados;
	
	private JButton btnAgregarServicio;
	private JButton btnEliminarServicio;
	
	private JButton btnCancelar;
	private JButton btnConfirmar;

	
	/* *************************************************************************************
	 * ******************* VARIABLES PARA MANEJAR DATOS ************************************
	 * *************************************************************************************/
	
	private LocalDate fechaCita;
	private SucursalDTO sucursal;
	private ClienteDTO cliente;
	private LocalTime horaInicio;
	private LocalTime horaFin;
	private BigDecimal total;
	private BigDecimal totalUSD;
	
	private JLabel lblErrorFecha;

	public static nuevaVentanaCita getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new nuevaVentanaCita();
		}
		INSTANCE.limpiarTodo();
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
		
		crearLabelErrorFecha();
		
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
		
		crearPanelesDinamicos();
		
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
		
		crearLabelsDatosCita();
		
		crearLabelAlertaSucursal();
		
		crearTablaServiciosAgregados();
		
		crearBotonConfirmar();
		
		crearBotonCancelar();
	
	}

	private void crearJDateChooserFecha() {
		JDChooserFecha = new JDateChooser();
		JDChooserFecha.setBounds(92, 77, 120, 23);
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
		spr_DatosGlobales.setBounds(10, 28, 513, 2);
		JPnl_DatosGlobales.add(spr_DatosGlobales);
	}

	private void crearJCBoxSucursal() {
		JCBoxSucursal = new JComboBox<SucursalDTO>();
		JCBoxSucursal.setBounds(92, 46, 120, 20);
		JPnl_DatosGlobales.add(JCBoxSucursal);
	}

	private void crearBotonEditarFecha() {
		btnEditarFecha = new JButton("Editar");
		btnEditarFecha.setBounds(231, 77, 89, 23);
		JPnl_DatosGlobales.add(btnEditarFecha);
	}
	
	private void crearLabelErrorFecha()	{
		Color rojo = new Color(242,108,102);
		lblErrorFecha = new JLabel("Ese día ya transcurrio!");
		lblErrorFecha.setHorizontalAlignment(SwingConstants.CENTER);
		lblErrorFecha.setVisible(false);
		lblErrorFecha.setBackground(rojo);
		lblErrorFecha.setOpaque(true);
		lblErrorFecha.setBounds(330, 77, 193, 23);
		JPnl_DatosGlobales.add(lblErrorFecha);
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
		separator.setBounds(10, 28, 513, 2);
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
		TxtNombre.setEnabled(false);
		JPnl_Cliente.add(TxtNombre);
		TxtNombre.setColumns(10);
	}

	private void crearTxtApellido() {
		TxtApellido = new JTextField();
		TxtApellido.setBounds(325, 42, 86, 20);
		TxtApellido.setEnabled(false);
		JPnl_Cliente.add(TxtApellido);
		TxtApellido.setColumns(10);
	}

	private void crearTxtTelefono() {
		TxtTelefono = new JTextField();
		TxtTelefono.setBounds(169, 73, 86, 20);
		TxtTelefono.setEnabled(false);
		JPnl_Cliente.add(TxtTelefono);
		TxtTelefono.setColumns(10);
	}

	private void crearTxtMail() {
		TxtMail = new JTextField();
		TxtMail.setBounds(325, 73, 198, 20);
		TxtMail.setEnabled(false);
		JPnl_Cliente.add(TxtMail);
		TxtMail.setColumns(10);
	}

	private void crearBotonRegistrar() {
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.setBounds(314, 106, 97, 23);
		JPnl_Cliente.add(btnRegistrar);
	}

	private void crearBotonBuscar() {
		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(426, 104, 97, 23);
		btnBuscar.setEnabled(false);
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
		separator_1.setBounds(10, 28, 513, 2);
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
		JPnl_DinamicoServicios.setBounds(10, 65, 232, 168);
		JPnl_Servicios.add(JPnl_DinamicoServicios);
		JPnl_DinamicoServicios.setLayout(null);
	}

	private void crearTxtDescripcion() {
		txtDescripcion = new JTextField();
		txtDescripcion.setHorizontalAlignment(SwingConstants.CENTER);
		txtDescripcion.setText("[Descripcion de la cita]");
		txtDescripcion.setBounds(8, 244, 515, 38);
		
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
		btnAgregarServicio = new JButton("");
		btnAgregarServicio.setBounds(249, 120, 25, 25);
		JPnl_Servicios.add(btnAgregarServicio);
	}

	private void crearBotonEliminarServicio() {
		btnEliminarServicio = new JButton("");
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
		separator_2.setBounds(10, 28, 513, 2);
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

	private void crearLabelsDatosCita() {
		lbl_Inicio = new JLabel("");
		lbl_Inicio.setBounds(50, 41, 120, 14);
		JPnl_Datos.add(lbl_Inicio);
		
		lbl_Fin = new JLabel("");
		lbl_Fin.setBounds(34, 66, 120, 14);
		JPnl_Datos.add(lbl_Fin);
		
		lbl_Total = new JLabel("");
		lbl_Total.setBounds(256, 41, 120, 14);
		JPnl_Datos.add(lbl_Total);
		
		lbl_TotalUSD = new JLabel("");
		lbl_TotalUSD.setBounds(279, 66, 120, 14);
		JPnl_Datos.add(lbl_TotalUSD);
	}

	private void crearBotonConfirmar() {
		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBounds(333, 657, 100, 23);
		frame.getContentPane().add(btnConfirmar);
	}

	private void crearBotonCancelar() {
		btnCancelar = new JButton("Cancelar");
		btnCancelar .setBounds(443, 657, 100, 23);
		frame.getContentPane().add(btnCancelar );
	}
	
	private void crearTablaServiciosAgregados() {
		spServiciosAgregados = new JScrollPane();
		spServiciosAgregados.setBounds(282, 83, 241, 150);
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
	
	public void crearPanelesDinamicos() {
		panelDinamicoServicios = new PanelDinamicoServicios();
		panelDinamicoServicios.setLocation(0, 0);
		panelDinamicoServicios.setVisible(false);
		JPnl_DinamicoServicios.add(panelDinamicoServicios);
		
		panelDinamicoPromociones = new PanelDinamicoPromociones();
		panelDinamicoPromociones.setLocation(0, 0);
		panelDinamicoPromociones.setVisible(false);
		JPnl_DinamicoServicios.add(panelDinamicoPromociones);
		
		panelDinamicoProfesionales = new PanelDinamicoProfesionales();
		panelDinamicoProfesionales.setVisible(false);
		JPnl_DinamicoServicios.add(panelDinamicoProfesionales);
		
		JPnl_DinamicoServicios.updateUI();
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
	
	public void setDatosClienteRegistrado() {
		this.getTxtNombre().setText(cliente.getNombre());
		this.getTxtApellido().setText(cliente.getApellido());
		this.getTxtMail().setText(cliente.getMail());
		this.getTxtTelefono().setText(cliente.getApellido());
		
		setearTxt(false);
	}
	
	private void setearDatosClienteGenerico() {
		this.TxtNombre.setText(cliente.getNombre());
		this.TxtApellido.setText(cliente.getApellido());
	}
	
	public void setearTxt(boolean b) {
		this.getTxtNombre().setEnabled(b);
		this.getTxtApellido().setEnabled(b);
		this.getTxtMail().setEnabled(b);
		this.getTxtTelefono().setEnabled(b);
	}
	
	public void habilitarCamposClienteGenerico() {
		setearDatosClienteGenerico();
		this.setearTxt(false);
		this.getTxtMail().setEnabled(true);
		this.getTxtTelefono().setEnabled(true);
	}
	
	public void limpiarTxtCliente() {
		this.getTxtNombre().setText(null);
		this.getTxtApellido().setText(null);
		this.getTxtMail().setText(null);
		this.getTxtTelefono().setText(null);
	}
	
	public void habilitarJDateChooser() {
		JDChooserFecha.setEnabled(true);
	}

	public void mostrarPanelServicios() {
		panelDinamicoServicios.setVisible(true);
	}
	
	public void ocultarPanelServicios() {
		panelDinamicoServicios.setVisible(false);
	}
	
	public void mostrarPanelProfesionales() {
		panelDinamicoProfesionales.setVisible(true);
	}
	
	public void ocultarPanelProfesionales() {
		panelDinamicoProfesionales.setVisible(false);
	}
	
	public void mostrarPanelPromociones() {
		panelDinamicoPromociones.setVisible(true);
	}
	
	public void ocultarPanelPromociones() {
		this.panelDinamicoPromociones.setVisible(false);
	}
	
	public void mostrarErrorFechaAnterior() {
		this.lblErrorFecha.setVisible(true);
	}
	
	public void ocultarErrorFechaAnteror() {
		this.lblErrorFecha.setVisible(false);
	}		
	
	public void ocultarPanelesServicios() {
		this.panelDinamicoProfesionales.setVisible(false);
		this.panelDinamicoPromociones.setVisible(false);
		this.panelDinamicoServicios.setVisible(false);
	}
	
	public void setearDatoInicio() {
		lbl_Inicio.setText(horaInicio.toString());
	}
	
	public void limpiarTodo() {
		this.btnBuscar.setEnabled(false);
		this.chckbxGenerico.setSelected(false);
		this.chckbxRegistrado.setSelected(false);
		limpiarTxtCliente();
		setearTxt(true);
		
		this.JCBoxHora.setSelectedItem(null);
		this.JCBoxMinutos.setSelectedItem(null);
		
		this.getRdBtnProfesional().setSelected(false);
		this.getRdbtnPromocion().setSelected(false);
		this.getRdBtnServicio().setSelected(false);
		
		ocultarPanelProfesionales();
		ocultarPanelPromociones();
		ocultarPanelServicios();
		
		getModelServiciosAgregados().setRowCount(0); //Para vaciar la tabla
		getModelServiciosAgregados().setColumnCount(0);
		this.lbl_Inicio.setText(null);
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

	public JLabel getLblAlertaSucursal() {
		return lblAlertaSucursal;
	}


	public void setLblAlertaSucursal(JLabel lblAlertaSucursal) {
		this.lblAlertaSucursal = lblAlertaSucursal;
	}


	public JButton getBtnBuscar() {
		return btnBuscar;
	}


	public void setBtnBuscar(JButton btnBuscar) {
		this.btnBuscar = btnBuscar;
	}

	public JButton getBtnRegistrar() {
		return btnRegistrar;
	}

	public void setBtnRegistrar(JButton btnRegistrar) {
		this.btnRegistrar = btnRegistrar;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}


	public void setBtnCancelar(JButton btnCancelar) {
		this.btnCancelar = btnCancelar;
	}

	public JButton getBtnConfirmar() {
		return btnConfirmar;
	}

	public void setBtnConfirmar(JButton btnConfirmar) {
		this.btnConfirmar = btnConfirmar;
	}
	
	public JLabel getLbl_Inicio() {
		return lbl_Inicio;
	}

	public void setLbl_Inicio(JLabel lbl_Inicio) {
		this.lbl_Inicio = lbl_Inicio;
	}

	public JLabel getLbl_Fin() {
		return lbl_Fin;
	}

	public void setLbl_Fin(JLabel lbl_Fin) {
		this.lbl_Fin = lbl_Fin;
	}

	public JLabel getLbl_Total() {
		return lbl_Total;
	}

	public void setLbl_Total(JLabel lbl_Total) {
		this.lbl_Total = lbl_Total;
	}

	public JLabel getLbl_TotalUSD() {
		return lbl_TotalUSD;
	}

	public void setLbl_TotalUSD(JLabel lbl_TotalUSD) {
		this.lbl_TotalUSD = lbl_TotalUSD;
	}

	public PanelDinamicoServicios getPanelDinamicoServicios() {
		return panelDinamicoServicios;
	}

	public void setPanelDinamicoServicios(PanelDinamicoServicios panelDinamicoServicios) {
		this.panelDinamicoServicios = panelDinamicoServicios;
	}

	public PanelDinamicoProfesionales getPanelDinamicoProfesionales() {
		return panelDinamicoProfesionales;
	}

	public void setPanelDinamicoProfesionales(PanelDinamicoProfesionales panelDinamicoProfesionales) {
		this.panelDinamicoProfesionales = panelDinamicoProfesionales;
	}

	public PanelDinamicoPromociones getPanelDinamicoPromociones() {
		return panelDinamicoPromociones;
	}

	public void setPanelDinamicoPromociones(PanelDinamicoPromociones panelDinamicoPromociones) {
		this.panelDinamicoPromociones = panelDinamicoPromociones;
	}
	
	public JButton getBtnAgregarServicio() {
		return btnAgregarServicio;
	}

	public void setBtnAgregarServicio(JButton btnAgregarServicio) {
		this.btnAgregarServicio = btnAgregarServicio;
	}

	public JButton getBtnEliminarServicio() {
		return btnEliminarServicio;
	}

	public void setBtnEliminarServicio(JButton btnEliminarServicio) {
		this.btnEliminarServicio = btnEliminarServicio;
	}
	
	public void cerrar() {
		this.frame.dispose();
	}
	
	/* *************************************************************************************
	 * ********************* GETTERS Y SETTERS DE LAS VARIABLES ****************************
	 * *************************************************************************************/

	public SucursalDTO getSucursal() {
		return sucursal;
	}

	public void setSucursal(SucursalDTO sucursal) {
		this.sucursal = sucursal;
	}
	
	public ClienteDTO getCliente() {
		return cliente;
	}

	public void setCliente(ClienteDTO cliente) {
		this.cliente = cliente;
	}

	public LocalTime getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(LocalTime horaInicio) {
		this.horaInicio = horaInicio;
	}
	
	public LocalTime getHoraFin() {
		return horaFin;
	}
	
	public void setHoraFin(LocalTime horaFin) {
		this.horaFin = horaFin;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getTotalUSD() {
		return totalUSD;
	}

	public void setTotalUSD(BigDecimal totalUSD) {
		this.totalUSD = totalUSD;
	}
}
