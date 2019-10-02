package presentacion.vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;

public class VentanaAltaHorario extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAltaHorario frame = new VentanaAltaHorario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaAltaHorario() {
		setTitle("Alta Horario");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 414, 239);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblDias = new JLabel("Dias");
		lblDias.setBounds(10, 22, 132, 14);
		panel.add(lblDias);
		
		JLabel lblHorarioEntrada = new JLabel("Horario Entrada");
		lblHorarioEntrada.setBounds(10, 66, 132, 14);
		panel.add(lblHorarioEntrada);
		
		JLabel lblHorarioSalida = new JLabel("Horario Salida");
		lblHorarioSalida.setBounds(10, 116, 132, 14);
		panel.add(lblHorarioSalida);
		
		JComboBox comboDias = new JComboBox();
		comboDias.setBounds(212, 18, 141, 22);
		panel.add(comboDias);
	}
}
