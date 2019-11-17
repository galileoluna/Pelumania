package presentacion.vista;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import com.toedter.calendar.JDateChooser;

import util.PropertyManager;

public class VentanaReporteEgresos extends JFrame {
	private static final long serialVersionUID = 1L;
	private static VentanaReporteEgresos INSTANCE;
	private JPanel contentPane;
	private JDateChooser Jdc_Hasta;
	private JDateChooser Jdc_Desde;
	private JButton btnGenerarReporte;
	// configuracion de idioma
	private Locale locale;
	private ResourceBundle idioma;

	private VentanaReporteEgresos() {
		super();
		initialize();
	}

	public static VentanaReporteEgresos getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new VentanaReporteEgresos();
			return INSTANCE;
		} else {
			return INSTANCE;
		}
	}

	private void initialize() {
		locale = new Locale(PropertyManager.leer("configuracion", "idioma"),
				PropertyManager.leer("configuracion", "pais"));
		idioma = ResourceBundle.getBundle("presentacion/idioma/bundle", locale);
		
		setTitle(idioma.getString("reporte.general"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 375, 225);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

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
		Jdc_Desde.setDateFormatString("yyyy-MM-dd");
		Jdc_Desde.getDateEditor().setEnabled(false);
		panel.add(Jdc_Desde);

		Jdc_Hasta = new JDateChooser();
		Jdc_Hasta.setSize(142, 23);
		Jdc_Hasta.setLocation(109, 84);
		Jdc_Hasta.setDateFormatString("yyyy-MM-dd");
		Jdc_Hasta.getDateEditor().setEnabled(false);
		panel.add(Jdc_Hasta);

		btnGenerarReporte = new JButton(idioma.getString("reporte.generar"));
		btnGenerarReporte.setBounds(195, 152, 151, 23);
		panel.add(btnGenerarReporte);

		JLabel lblGenerarReportesPor = new JLabel(idioma.getString("reporte.general"));
		lblGenerarReportesPor.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblGenerarReportesPor.setBounds(32, 11, 273, 23);
		panel.add(lblGenerarReportesPor);

		JSeparator separator = new JSeparator();
		separator.setBounds(32, 36, 296, 2);
		panel.add(separator);

		this.setVisible(false);
	}

	public void mostrarVentana() {
		this.setVisible(true);
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

	public JButton getBtnGenerarReporte() {
		return btnGenerarReporte;
	}

	public void mostrar() {
		this.setVisible(true);

	}

	public void limpiarInputs() {

	}

	public void mostrarErrorFecha() {
		JOptionPane.showMessageDialog(new JFrame(), idioma.getString("error.fecha.invalida"), "Dialog", JOptionPane.ERROR_MESSAGE);
	}

	public void cerrar() {
		this.dispose();
	}
}
