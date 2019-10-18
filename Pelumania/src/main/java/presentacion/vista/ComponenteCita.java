package presentacion.vista;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import dto.CitaDTO;

public class ComponenteCita extends JPanel{

	private static final long serialVersionUID = 1L;
	private JLabel lbl_Estado;
	private JLabel lbl_HoraFin;
	private JLabel lbl_HoraInicio;
	private JLabel lbl_IdCita;
	private JLabel lbl_NombreCliente;
	private JLabel lbl_TotalUSD;
	private JLabel lbl_Total;

	
	private JScrollPane spServicios;
	
	private JTable tablaServicios;
	private DefaultTableModel modelServicios;
	private String[] nombreColumnas = {"Nombre","Precio en $","Precio en USD",
			"Duracion", "Profesional"};
	
	public CitaDTO citaAsociada;

	public ComponenteCita(int x, int y, CitaDTO cita) {
		initialize(x,y, cita);
	}

	private void initialize(int x, int y, CitaDTO cita) {
		//bounds 450 300
		setCitaAsociada(cita);
		setBounds(x, y, 440, 327);
		setLayout(null);
		
		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setBounds(20, 10, 112, 22);
		add(lblCliente);
		
		JSeparator separador1 = new JSeparator();
		separador1.setBounds(20, 35, 402, 2);
		add(separador1);
		
		lbl_NombreCliente = new JLabel("");
		lbl_NombreCliente.setBounds(66, 10, 231, 22);
		add(lbl_NombreCliente);
		
		JLabel lblHoraInicio = new JLabel("Hora Inicio: ");
		lblHoraInicio.setBounds(20, 43, 81, 22);
		add(lblHoraInicio);
		
		JLabel lblHoraFin = new JLabel("Hora Fin:");
		lblHoraFin.setBounds(181, 43, 81, 22);
		add(lblHoraFin);
		
		JLabel lblCitaNro = new JLabel("Cita Nro.");
		lblCitaNro.setBounds(316, 10, 64, 22);
		add(lblCitaNro);
		
		lbl_IdCita = new JLabel("");
		lbl_IdCita.setBounds(364, 10, 46, 22);
		add(lbl_IdCita);
		
		lbl_HoraInicio = new JLabel("");
		lbl_HoraInicio.setBounds(86, 43, 85, 22);
		add(lbl_HoraInicio);
		
		lbl_HoraFin = new JLabel("");
		lbl_HoraFin.setBounds(244, 43, 85, 22);
		add(lbl_HoraFin);
		
		JSeparator separador2 = new JSeparator();
		separador2.setBounds(20, 68, 402, 2);
		add(separador2);
		
		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setBounds(20, 107, 81, 22);
		add(lblEstado);
		
		lbl_Estado = new JLabel("");
		lbl_Estado.setBounds(78, 107, 64, 22);
		add(lbl_Estado);
		
		JSeparator separador3 = new JSeparator();
		separador3.setBounds(20, 101, 402, 2);
		add(separador3);
		
		JLabel lblPrecioTotal = new JLabel("Total: ");
		lblPrecioTotal.setBounds(20, 76, 64, 22);
		add(lblPrecioTotal);
		
		lbl_Total = new JLabel("");
		lbl_Total.setBounds(66, 76, 85, 22);
		add(lbl_Total);
		
		JLabel lblTotalEnUsd = new JLabel("Total en USD: ");
		lblTotalEnUsd.setBounds(181, 76, 81, 22);
		add(lblTotalEnUsd);
		
		lbl_TotalUSD = new JLabel("");
		lbl_TotalUSD.setBounds(268, 76, 112, 22);
		add(lbl_TotalUSD);
	
		crearTablaServicios();
		
		setBorder(new LineBorder(new Color(0, 0, 0)));
		
		llenarDatos();
		cambiarColor();
		this.setVisible(true);
	}
	
	public void llenarDatos() {
		getLbl_IdCita().setText(Integer.toString(citaAsociada.getIdCita()));
		getLbl_HoraInicio().setText(citaAsociada.getHoraInicio().toString());
		getLbl_HoraFin().setText(citaAsociada.getHoraFin().toString());
		getLbl_NombreCliente().setText(citaAsociada.getNombre()+citaAsociada.getApellido());	
		getLbl_Estado().setText(citaAsociada.getEstado());
		getLbl_Total().setText(citaAsociada.getPrecioLocal().toString());
		getLbl_TotalUSD().setText(citaAsociada.getPrecioDolar().toString());
	}
	
	public void cambiarColor() {
		Color rojo = new Color(225,64,68);
		Color verde = new Color(129,152,48);
		Color azul = new Color(0,85,121);
		switch (citaAsociada.getEstado()) {
		case "Activa":
			setBackground(verde);
			break;
		case "Cancelada":
			setBackground(rojo);
			break;
		case "Finalizada":
			setBackground(azul);
			break;
		}
	}
	
	public void crearTablaServicios() {
		spServicios = new JScrollPane();
		spServicios.setBounds(20, 137, 402, 175);
		add(spServicios);
		
		
		modelServicios = new DefaultTableModel(null,nombreColumnas) {
			//Para que las celdas de la tabla no se puedan editar
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		
		tablaServicios = new JTable(modelServicios);
		tablaServicios.getTableHeader().setReorderingAllowed(false) ;

		tablaServicios.getColumnModel().getColumn(0).setPreferredWidth(103);
		tablaServicios.getColumnModel().getColumn(0).setResizable(true);
		tablaServicios.getColumnModel().getColumn(1).setPreferredWidth(100);
		tablaServicios.getColumnModel().getColumn(1).setResizable(true);
		tablaServicios.getColumnModel().getColumn(2).setPreferredWidth(100);
		tablaServicios.getColumnModel().getColumn(2).setResizable(true);
		tablaServicios.getColumnModel().getColumn(3).setPreferredWidth(100);
		tablaServicios.getColumnModel().getColumn(3).setResizable(true);
		tablaServicios.getColumnModel().getColumn(4).setPreferredWidth(100);
		tablaServicios.getColumnModel().getColumn(4).setResizable(true);

		spServicios.setViewportView(tablaServicios);
	}
	
	public JLabel getLbl_Estado() {
		return lbl_Estado;
	}

	public void setLbl_Estado(JLabel lbl_Estado) {
		this.lbl_Estado = lbl_Estado;
	}

	public JLabel getLbl_HoraFin() {
		return lbl_HoraFin;
	}

	public void setLbl_HoraFin(JLabel lbl_HoraFin) {
		this.lbl_HoraFin = lbl_HoraFin;
	}

	public JLabel getLbl_HoraInicio() {
		return lbl_HoraInicio;
	}

	public void setLbl_HoraInicio(JLabel lbl_HoraInicio) {
		this.lbl_HoraInicio = lbl_HoraInicio;
	}

	public JLabel getLbl_IdCita() {
		return lbl_IdCita;
	}

	public void setLbl_IdCita(JLabel lbl_IdCita) {
		this.lbl_IdCita = lbl_IdCita;
	}

	public JLabel getLbl_NombreCliente() {
		return lbl_NombreCliente;
	}

	public void setLbl_NombreCliente(JLabel lbl_NombreCliente) {
		this.lbl_NombreCliente = lbl_NombreCliente;
	}

	public JLabel getLbl_TotalUSD() {
		return lbl_TotalUSD;
	}

	public void setLbl_TotalUSD(JLabel lbl_TotalUSD) {
		this.lbl_TotalUSD = lbl_TotalUSD;
	}

	public JLabel getLbl_Total() {
		return lbl_Total;
	}

	public void setLbl_Total(JLabel lbl_Total) {
		this.lbl_Total = lbl_Total;
	}

	
	// ____________________________________________________
	
	// ____________________________________________________
	
	public JScrollPane getSpServicios() {
		return spServicios;
	}

	public void setSpServicios(JScrollPane spServicios) {
		this.spServicios = spServicios;
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
	// ____________________________________________________

	public CitaDTO getCitaAsociada() {
		return citaAsociada;
	}

	public void setCitaAsociada(CitaDTO citaAsociada) {
		this.citaAsociada = citaAsociada;
	}
	// ____________________________________________________
	
	

}

