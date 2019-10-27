package presentacion.vista;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dto.CitaDTO;
import dto.ServicioDTO;
import dto.ServicioTurnoDTO;

import java.awt.Color;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class panelDetalle extends JPanel {


	private JTable tablaServiciosAgregados;
	private DefaultTableModel modelServiciosAgregados;
	private String[] nombreColumnas = {"Servicio","Hora inicio", "Hora fin","Profesional"};
	private JScrollPane spServiciosAgregados;
	
	private JLabel lblId;
	private JLabel lblNombre;
	private JLabel lblPrecioTotal;
	private JLabel lblEstado;
	
	public static final Color rojo = new Color(225,64,68);
	public static final Color verde = new Color(129,152,48);
	public static final Color azul = new Color(22,138,197);
	public static final Color amarillo = new Color(248,214,115);
	public static final Color naranja = new Color(239,169,74);
	public static final Color gris = new Color(128,128,128);
	
	private static final long serialVersionUID = 1L;

	public panelDetalle() {
		initialize();
	}
	
	public void initialize() {
		setBounds(100, 100, 917, 151);
		setLayout(null);
		
		spServiciosAgregados = new JScrollPane();
		spServiciosAgregados.setBounds(411, 11, 496, 135);
		add(spServiciosAgregados);

		modelServiciosAgregados = new DefaultTableModel(null,nombreColumnas) {

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
		tablaServiciosAgregados.getColumnModel().getColumn(1).setPreferredWidth(103);
		tablaServiciosAgregados.getColumnModel().getColumn(1).setResizable(false);
		tablaServiciosAgregados.getColumnModel().getColumn(2).setPreferredWidth(103);
		tablaServiciosAgregados.getColumnModel().getColumn(2).setResizable(false);
		tablaServiciosAgregados.getColumnModel().getColumn(3).setPreferredWidth(103);
		tablaServiciosAgregados.getColumnModel().getColumn(3).setResizable(false);
		
		spServiciosAgregados.setViewportView(tablaServiciosAgregados);
		
		JLabel lblCliente = new JLabel("ID: ");
		lblCliente.setBounds(10, 12, 68, 20);
		add(lblCliente);
		
		JLabel label = new JLabel("Cliente:");
		label.setBounds(10, 43, 68, 20);
		add(label);
		
		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setBounds(10, 75, 68, 20);
		add(lblTotal);
		
		JLabel lblHoraDeInicio = new JLabel("Estado:");
		lblHoraDeInicio.setBounds(10, 106, 68, 20);
		add(lblHoraDeInicio);
		
		lblId = new JLabel("");
		lblId.setBounds(80, 12, 122, 20);
		add(lblId);
		
		lblNombre = new JLabel("");
		lblNombre.setBounds(80, 43, 122, 20);
		add(lblNombre);
		
		lblPrecioTotal = new JLabel("");
		lblPrecioTotal.setBounds(80, 75, 122, 20);
		add(lblPrecioTotal);
		
		lblEstado = new JLabel("");
		lblEstado.setBounds(80, 106, 122, 20);
		add(lblEstado);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(231, 11, 170, 135);
		add(textPane);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(212, 11, 1, 135);
		add(separator);
		
	}
	public void ocultar() {
		setVisible(false);
		updateUI();
	}
	
	public void cargarDatosCitaAsociada(CitaDTO cita) {
		int id = cita.getIdCita();
		String nombre = cita.getNombre() + " " + cita.getApellido();
		String estado = cita.getEstado();
		BigDecimal precioTotal = cita.getPrecioLocal();
		
		lblId.setText(Integer.toString(id));
		lblNombre.setText(nombre);
		lblEstado.setText(estado);
		lblPrecioTotal.setText(precioTotal.toString());
		/*
		switch (estado) {
		case "Activa":
		    {
		    	this.setBackground(verde);
		    	break;
		    }
		case "Reprogramar":
			{
				this.setBackground(amarillo);
	    		break;
			}
		case "En Curso":
			{
	    		this.setBackground(naranja);
	    		break;
			}
		case "Cancelada":
			{
	    		this.setBackground(rojo);
	    		break;
			}
		case "Vencida":
			{
	    		this.setBackground(gris);
	    		break;
			}
		case "Finalizada":
			{
	    		this.setBackground(azul);
	    		break;
			}
		}*/
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

	public String[] getNombreColumnas() {
		return nombreColumnas;
	}

	public void setNombreColumnas(String[] nombreColumnas) {
		this.nombreColumnas = nombreColumnas;
	}

	public JScrollPane getSpServiciosAgregados() {
		return spServiciosAgregados;
	}

	public void setSpServiciosAgregados(JScrollPane spServiciosAgregados) {
		this.spServiciosAgregados = spServiciosAgregados;
	}

}
