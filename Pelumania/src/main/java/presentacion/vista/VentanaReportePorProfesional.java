package presentacion.vista;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import com.toedter.calendar.JDateChooser;

import dto.ClienteDTO;
import dto.ProfesionalDTO;

public class VentanaReportePorProfesional extends JFrame{
	private static final long serialVersionUID = 1L;
	private static VentanaReportePorProfesional INSTANCE;
	private JPanel contentPane;
	private JComboBox<ProfesionalDTO> Jcb_Profesional;
	private JDateChooser Jdc_Hasta;
	private JDateChooser Jdc_Desde;
	
	private VentanaReportePorProfesional()
	{
		super();
		initialize();
	}

	public static VentanaReportePorProfesional getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new VentanaReportePorProfesional();
			return INSTANCE;
		} else {
			return INSTANCE;
		}
	}


	private void initialize()
	{
		setTitle("Reporte de ventas por profesional");
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
		
		JLabel lblDesde = new JLabel("Desde : ");
		lblDesde.setBounds(32, 49, 91, 23);
		panel.add(lblDesde);
		
		JLabel lblHasta = new JLabel("Hasta: ");
		lblHasta.setBounds(32, 83, 67, 24);
		panel.add(lblHasta);
		
		Jdc_Desde = new JDateChooser();
		Jdc_Desde.setSize(142, 23);
		Jdc_Desde.setLocation(109, 49);
		panel.add(Jdc_Desde);
		
		Jdc_Hasta = new JDateChooser();
		Jdc_Hasta.setSize(142, 23);
		Jdc_Hasta.setLocation(109, 84);
		panel.add(Jdc_Hasta);
		
		JButton btnGenerarReporte = new JButton("Generar Reporte");
		btnGenerarReporte.setBounds(195, 152, 151, 23);
		panel.add(btnGenerarReporte);
		
		JLabel lblProfesional = new JLabel("Profesional:");
		lblProfesional.setBounds(32, 118, 91, 23);
		panel.add(lblProfesional);
		
		Jcb_Profesional = new JComboBox<ProfesionalDTO>();
		Jcb_Profesional.setBounds(109, 118, 142, 23);
		panel.add(Jcb_Profesional);
		
		JLabel lblGenerarReportesPor = new JLabel("Generar reporte por profesional");
		lblGenerarReportesPor.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblGenerarReportesPor.setBounds(32, 11, 273, 23);
		panel.add(lblGenerarReportesPor);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(32, 36, 296, 2);
		panel.add(separator);
		
		setVisible(true);
	}

	public JComboBox<ProfesionalDTO> getJcb_Profesional() {
		return Jcb_Profesional;
	}

	public void setJcb_Profesional(JComboBox<ProfesionalDTO> Jcb_Profesional) {
		Jcb_Profesional = Jcb_Profesional;
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
