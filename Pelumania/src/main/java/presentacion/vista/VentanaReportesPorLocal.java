package presentacion.vista;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.toedter.calendar.JDateChooser;

import dto.SucursalDTO;
import util.PropertyManager;

import javax.swing.JSeparator;

public class VentanaReportesPorLocal extends JFrame{

	private static final long serialVersionUID = 1L;
	private static VentanaReportesPorLocal INSTANCE;
	private JPanel contentPane;
	private JComboBox<SucursalDTO> Jcb_Sucursal;
	private JDateChooser Jdc_Hasta;
	private JDateChooser Jdc_Desde;
	private JButton btnGenerarReporte;
	// configuracion de idioma
	private Locale locale;
	private ResourceBundle idioma;
	
	private VentanaReportesPorLocal()
	{
		super();
		initialize();
	}

	public static VentanaReportesPorLocal getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new VentanaReportesPorLocal();
			return INSTANCE;
		} else {
			return INSTANCE;
		}
	}


	private void initialize()
	{
		locale = new Locale(PropertyManager.leer("configuracion", "idioma"),
				PropertyManager.leer("configuracion", "pais"));
		idioma = ResourceBundle.getBundle("presentacion/idioma/bundle", locale);
				
		setTitle(idioma.getString("reporte.sucursal"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 375, 225);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 356, 186);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblDesde = new JLabel(idioma.getString("desde"));
		lblDesde.setBounds(32, 49, 91, 23);
		panel.add(lblDesde);
		
		JLabel lblHasta = new JLabel(idioma.getString("hasta"));
		lblHasta.setBounds(32, 83, 67, 24);
		panel.add(lblHasta);
		
		Jdc_Desde = new JDateChooser();
		Jdc_Desde.setSize(142, 23);
		Jdc_Desde.setLocation(109, 49);
		Jdc_Desde.getDateEditor().setEnabled(false);
		panel.add(Jdc_Desde);
		
		Jdc_Hasta = new JDateChooser();
		Jdc_Hasta.setSize(142, 23);
		Jdc_Hasta.setLocation(109, 84);
		Jdc_Hasta.getDateEditor().setEnabled(false);
		panel.add(Jdc_Hasta);
		
		btnGenerarReporte = new JButton(idioma.getString("reporte.generar"));
		btnGenerarReporte.setBounds(195, 152, 151, 23);
		panel.add(btnGenerarReporte);
		
		JLabel lblSucursal = new JLabel(idioma.getString("sucursal"));
		lblSucursal.setBounds(32, 118, 91, 23);
		panel.add(lblSucursal);
		
		Jcb_Sucursal = new JComboBox<SucursalDTO>();
		Jcb_Sucursal.setBounds(109, 118, 142, 23);
		panel.add(Jcb_Sucursal);
		
		JLabel lblGenerarReportesPor = new JLabel(idioma.getString("reporte.sucursal"));
		lblGenerarReportesPor.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblGenerarReportesPor.setBounds(32, 11, 273, 23);
		panel.add(lblGenerarReportesPor);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(32, 36, 296, 2);
		panel.add(separator);
		
		setVisible(true);
	}

    public JButton getBtnGenerarReporte() {
	   	return btnGenerarReporte;
	}
	public JComboBox<SucursalDTO> getJcb_Sucursal() {
		return Jcb_Sucursal;
	}

	public void setJcb_Sucursal(JComboBox<SucursalDTO> jcb_Sucursal) {
		Jcb_Sucursal = jcb_Sucursal;
	}

	public JDateChooser getJdc_Hasta() {
		return Jdc_Hasta;
	}

	public void setJdc_Hasta(JDateChooser jdc_Hasta) {
		Jdc_Hasta = jdc_Hasta;
	}

	public JDateChooser getJdc_Desde() {
		return Jdc_Desde;
	}

	public void setJdc_Desde(JDateChooser jdc_Desde) {
		Jdc_Desde = jdc_Desde;
	}

	public void mostrar() {
		this.setVisible(true);

	}
	public void limpiarInputs()
	{
		
	}
}