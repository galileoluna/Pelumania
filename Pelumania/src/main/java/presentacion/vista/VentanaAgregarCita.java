package presentacion.vista;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dto.ProfesionalDTO;
import dto.ServicioDTO;
import dto.SucursalDTO;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.math.BigDecimal;

public class VentanaAgregarCita extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static VentanaAgregarCita INSTANCE;

	private JButton btn_AgregarCita;
	private JButton btn_Cancelar;

	private JLabel lblFecha;
	private JTextField txtFecha;
	private JTextField txtNombre;
	private JTextField txtApellido;

	private JButton btnBuscarCliente;
	private JButton btnRegistrarCliente;
	
	private JComboBox<Integer> JCBoxHora;
	private JComboBox<Integer> JCBoxMinutos;
	
	private JTable tablaServicios;
	private DefaultTableModel modelServicios;
	private String[] nombreColumnas = {"Nombre","Precio",
			"USD","Duracion"};
	
	private JTable tablaServiciosAgregados;
	private DefaultTableModel modelServiciosAgregados;
	private String[] nombreColumnasAgregadas = {"Nombre","Profesional"};

	private JLabel lblProfesional;
	private JComboBox<ProfesionalDTO> JCBoxProfesional;

	private LocalDate fechaCita;
	private Integer idCliente = -1;
	
	private JLabel lblNombreProfesional;
	private JLabel lblServiciosQueRealiza;
	private JLabel lblServiciosElegidos;
	private JLabel lblHoraTotal;
	private JLabel lblTotal$;
	private JLabel lblTotalUSD;
	private JLabel lblUSD;
	
	private JButton btnBorrarServicio;
	private JButton btnAgregarServicio;
	private JLabel lblSucursal;

	private JComboBox<SucursalDTO> JCBoxSucursales;

	public static VentanaAgregarCita getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new VentanaAgregarCita();
			return new VentanaAgregarCita();
		} else {
			return INSTANCE;
		}
	}

	private VentanaAgregarCita()
	{
		super();


		setTitle("Nueva Cita");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 873, 645);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 837, 584);
		contentPane.add(panel);
		panel.setLayout(null);

		btn_AgregarCita = new JButton("Agregar");
		btn_AgregarCita.setBounds(470, 556, 89, 23);
		panel.add(btn_AgregarCita);

		btn_Cancelar = new JButton("Cancelar");
		btn_Cancelar.setBounds(569, 556, 89, 23);
		panel.add(btn_Cancelar);

		lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(23, 11, 133, 23);
		panel.add(lblFecha);

		txtFecha = new JTextField();
		txtFecha.setBounds(129, 11, 208, 23);
		txtFecha.setEditable(false);
		panel.add(txtFecha);
		txtFecha.setColumns(10);

		JLabel lblHora = new JLabel("Hora:");
		lblHora.setBounds(77, 157, 59, 23);
		panel.add(lblHora);

		JCBoxHora = new JComboBox<Integer>();
		JCBoxHora.setBounds(116, 157, 53, 23);
		cargarHora(JCBoxHora);
		panel.add(JCBoxHora);

		JLabel lblMinutos = new JLabel("Minutos:");
		lblMinutos.setBounds(180, 157, 59, 23);
		panel.add(lblMinutos);

		JCBoxMinutos = new JComboBox<Integer>();
		JCBoxMinutos.setBounds(229, 157, 59, 23);
		cargarMinutos(JCBoxMinutos);
		panel.add(JCBoxMinutos);

		JLabel lblErrorHorario = new JLabel("Ese horario no se encuentra disponible!");
		lblErrorHorario.setBounds(100, 183, 208, 14);
		lblErrorHorario.setVisible(false);
		panel.add(lblErrorHorario);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(23, 45, 133, 23);
		panel.add(lblNombre);

		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(23, 79, 133, 23);
		panel.add(lblApellido);

		txtNombre = new JTextField();
		txtNombre.setBounds(129, 45, 208, 23);
		txtNombre.setColumns(10);
		panel.add(txtNombre);

		txtApellido = new JTextField();
		txtApellido.setBounds(129, 79, 208, 23);
		txtApellido.setColumns(10);
		panel.add(txtApellido);

		btnBuscarCliente = new JButton("Buscar Cliente");
		btnBuscarCliente.setBounds(196, 113, 141, 23);
		panel.add(btnBuscarCliente);

		btnRegistrarCliente = new JButton("Registrar Cliente");
		btnRegistrarCliente.setBounds(28, 113, 141, 23);
		panel.add(btnRegistrarCliente);

		lblProfesional = new JLabel("Profesional");
		lblProfesional.setBounds(23, 208, 89, 23);
		panel.add(lblProfesional);

		JCBoxProfesional = new JComboBox<ProfesionalDTO>();
		JCBoxProfesional.setBounds(100, 209, 237, 23);
		panel.add(JCBoxProfesional);

		JScrollPane spServicios = new JScrollPane();
		spServicios.setBounds(10, 271, 409, 188);
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

		spServicios.setViewportView(tablaServicios);
		
		JScrollPane spServiciosAgregados = new JScrollPane();
		spServiciosAgregados.setBounds(450, 271, 387, 188);
		panel.add(spServiciosAgregados);

		modelServiciosAgregados = new DefaultTableModel(null,nombreColumnasAgregadas) {
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
		
		lblServiciosElegidos = new JLabel("Servicios Elegidos");
		lblServiciosElegidos.setBounds(450, 246, 208, 14);
		panel.add(lblServiciosElegidos);
		
		lblServiciosQueRealiza = new JLabel("Servicios que realiza: ");
		lblServiciosQueRealiza.setBounds(10, 246, 126, 14);
		panel.add(lblServiciosQueRealiza);
		
		lblNombreProfesional = new JLabel("");
		lblNombreProfesional.setBounds(142, 246, 133, 14);
		panel.add(lblNombreProfesional);
		
		btnAgregarServicio = new JButton("Agregar Servicio");
		btnAgregarServicio.setBounds(36, 464, 133, 23);
		panel.add(btnAgregarServicio);
		
		btnBorrarServicio = new JButton("Borrar Servicio");
		btnBorrarServicio.setBounds(269, 464, 133, 23);
		panel.add(btnBorrarServicio);
		
		JLabel lblHoraDeFinalizacion = new JLabel("Hora de finalizacion: ");
		lblHoraDeFinalizacion.setBounds(450, 464, 117, 23);
		panel.add(lblHoraDeFinalizacion);
		
		JLabel lblPrecio$ = new JLabel("Total $");
		lblPrecio$.setBounds(603, 464, 67, 23);
		panel.add(lblPrecio$);
		
		lblHoraTotal = new JLabel("");
		lblHoraTotal.setBounds(565, 464, 46, 23);
		panel.add(lblHoraTotal);
		
		lblTotal$ = new JLabel("");
		lblTotal$.setBounds(641, 464, 46, 23);
		panel.add(lblTotal$);
		
		lblUSD = new JLabel("Total USD:");
		lblUSD.setBounds(708, 464, 67, 23);
		panel.add(lblUSD);
		
		lblTotalUSD = new JLabel("");
		lblTotalUSD.setBounds(766, 464, 46, 23);
		panel.add(lblTotalUSD);
		
		lblSucursal = new JLabel("Sucursal:");
		lblSucursal.setBounds(23, 512, 89, 23);
		panel.add(lblSucursal);
		
		JCBoxSucursales = new JComboBox<SucursalDTO>();
		JCBoxSucursales.setBounds(100, 513, 237, 23);
		panel.add(JCBoxSucursales);
		
		JButton btnVerComprobante = new JButton("Ver Comprobante");
		btnVerComprobante.setBounds(675, 556, 152, 23);
		panel.add(btnVerComprobante);

		this.setVisible(false);
	}


	public static VentanaAgregarCita getINSTANCE() {
		return INSTANCE;
	}

	public static void setINSTANCE(VentanaAgregarCita iNSTANCE) {
		INSTANCE = iNSTANCE;
	}

	public JButton getBtnAgregarCita () {
		return this.btn_AgregarCita;
	}
	public void setBtnAgregarCita(JButton btn_AgregarCita) {
		this.btn_AgregarCita = btn_AgregarCita;
	}

	public JButton getBtn_Cancelar() {
		return btn_Cancelar;
	}

	public void setBtn_Cancelar(JButton btn_Cancelar) {
		this.btn_Cancelar = btn_Cancelar;
	}

	public DefaultTableModel getModelServicios() {
		return modelServicios;
	}

	public void setModelServicios(DefaultTableModel modelServicios) {
		this.modelServicios = modelServicios;
	}

	public JButton getBtnBuscarCliente() {
		return btnBuscarCliente;
	}

	public void setBtnBuscarCliente(JButton btnBuscarCliente) {
		this.btnBuscarCliente = btnBuscarCliente;
	}

	public JButton getBtnRegistrarCliente() {
		return btnRegistrarCliente;
	}

	public void setBtnRegistrarCliente(JButton btnRegistrarCliente) {
		this.btnRegistrarCliente = btnRegistrarCliente;
	}

	public JComboBox<ProfesionalDTO> getJCBoxProfesional() {
		return JCBoxProfesional;
	}

	public void setJCBoxProfesional(JComboBox<ProfesionalDTO> jCBoxProfesional) {
		JCBoxProfesional = jCBoxProfesional;
	}

	public JTextField getTxtFecha() {
		return txtFecha;
	}

	public void setTxtFecha(JTextField txtFecha) {
		this.txtFecha = txtFecha;
	}

	public JTextField getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(JTextField txtNombre) {
		this.txtNombre = txtNombre;
	}

	public JTextField getTxtApellido() {
		return txtApellido;
	}

	public void setTxtApellido(JTextField txtApellido) {
		this.txtApellido = txtApellido;
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

	public LocalDate getFechaCita() {
		return fechaCita;
	}

	public void setFechaCita(LocalDate fechaCita) {
		this.fechaCita = fechaCita;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public void mostrarVentana()
	{
		this.setVisible(true);
	}

	public JLabel getLblNombreProfesional() {
		return lblNombreProfesional;
	}

	public void setLblNombreProfesional(JLabel lblNombreProfesional) {
		this.lblNombreProfesional = lblNombreProfesional;
	}

	public String[] getNombreColumnas() {
		return nombreColumnas;
	}

	public void setNombreColumnas(String[] nombreColumnas) {
		this.nombreColumnas = nombreColumnas;
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

	public JLabel getLblHoraTotal() {
		return lblHoraTotal;
	}

	public void setLblHoraTotal(JLabel lblHora_1) {
		this.lblHoraTotal = lblHora_1;
	}

	public JLabel getLblTotal$() {
		return lblTotal$;
	}

	public void setLblTotal$(JLabel lblTotal$) {
		this.lblTotal$ = lblTotal$;
	}
	
	public JButton getBtnBorrarServicio() {
		return btnBorrarServicio;
	}

	public void setBtnBorrarServicio(JButton btnBorrarServicio) {
		this.btnBorrarServicio = btnBorrarServicio;
	}

	public JButton getBtnAgregarServicio() {
		return btnAgregarServicio;
	}

	public void setBtnAgregarServicio(JButton btnAgregarServicio) {
		this.btnAgregarServicio = btnAgregarServicio;
	}

	
	public String[] getNombreColumnasAgregadas() {
		return nombreColumnasAgregadas;
	}

	public void setNombreColumnasAgregadas(String[] nombreColumnasAgregadas) {
		this.nombreColumnasAgregadas = nombreColumnasAgregadas;
	}

	public JLabel getLblTotalUSD() {
		return lblTotalUSD;
	}

	public void setLblTotalUSD(JLabel lblTotalUSD) {
		this.lblTotalUSD = lblTotalUSD;
	}

	public JComboBox<SucursalDTO> getJCBoxSucursales() {
		return JCBoxSucursales;
	}

	public void setJCBoxSucursales(JComboBox<SucursalDTO> jCBoxSucursales) {
		JCBoxSucursales = jCBoxSucursales;
	}

	private void cargarHora(JComboBox<Integer> hora) {
		for(int i=8;i<=20;i++) {
			hora.addItem(i);
		}
	}

	private void cargarMinutos(JComboBox<Integer> min) {
		for(int i=0;i<=59;i = i+5) {
			min.addItem(i);
		}
	}
	
	public void cargarSucursales(List<SucursalDTO> sucursalesEnTabla) {
		for (SucursalDTO s : sucursalesEnTabla) {
			this.JCBoxSucursales.addItem(s);
		}
	}

	public void mostrarErrorCampos() {
		JOptionPane.showMessageDialog(new JFrame(), "Campos ingresados inválidos", "Dialog",
				JOptionPane.ERROR_MESSAGE);
	}

	public void cargarFecha(int anio,int mes, int dia) {
		LocalDate fecha = LocalDate.of(anio, mes, dia);
		String S_mes = fecha.getMonth().toString();
		txtFecha.setText(dia + " de "+ S_mes+" de "+anio);
		this.setFechaCita(fecha);
	}
	
	public JTable getTablaServicios() {
		return tablaServicios;
	}

	public void setTablaServicios(JTable tablaServicios) {
		this.tablaServicios = tablaServicios;
	}

	public void cargarServicios(List<ServicioDTO> serviciosEnTabla) {
			this.getModelServicios().setRowCount(0); //Para vaciar la tabla
			this.getModelServicios().setColumnCount(0);
			this.getModelServicios().setColumnIdentifiers(this.getNombreColumnas());

			for (ServicioDTO s : serviciosEnTabla)
			{
				String nombre = s.getNombre();
				BigDecimal precioLocal = s.getPrecioLocal();
				BigDecimal precioDolar = s.getPrecioDolar();
				LocalTime duracion = s.getDuracion();
				Object[] fila = {nombre, precioLocal, precioDolar, duracion};
				this.getModelServicios().addRow(fila);
			}
	}

	public void cargarProfesionales(List<ProfesionalDTO> profesionalesEnTabla) {
		for (ProfesionalDTO p : profesionalesEnTabla) {
			JCBoxProfesional.addItem(p);
		}
	}
	
	public void limpiarCampos() {
		this.getTxtNombre().setText(null);
		this.getTxtApellido().setText(null);
		
		this.getTxtNombre().setEditable(true);
		this.getTxtApellido().setEditable(true);
		
		this.getJCBoxHora().setSelectedItem(null);
		this.getJCBoxMinutos().setSelectedItem(null);
		
		this.JCBoxProfesional.setSelectedIndex(-1);
		
	}

	public void cerrar()
	{
		this.JCBoxProfesional.removeAllItems();
		limpiarCampos();
		dispose();
	}
}