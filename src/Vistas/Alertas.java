package Vistas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class Alertas {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Alertas window = new Alertas();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Alertas() {
		try {
            UIManager.setLookAndFeel(new FlatLightLaf());
            UIManager.put("Button.arc", 8); // Esquinas redondeadas
        } catch (Exception ex) {
            ex.printStackTrace();
        }
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1141, 770);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel inf_tarifa_estandar = new JPanel();
		inf_tarifa_estandar.setBackground(new Color(255, 255, 255));
		inf_tarifa_estandar.setBounds(335, 234, 500, 280);
		frame.getContentPane().add(inf_tarifa_estandar);
		inf_tarifa_estandar.setLayout(null);
		
		JPanel panel_complemento= new JPanel();
		panel_complemento.setBackground(new Color(81, 151, 255));
		panel_complemento.setBounds(0, 0, 500, 33);
		inf_tarifa_estandar.add(panel_complemento);
		
		JTextArea txtrPlanEstandarSe = new JTextArea();
		txtrPlanEstandarSe.setBounds(10, 44, 480, 151);
		inf_tarifa_estandar.add(txtrPlanEstandarSe);
		txtrPlanEstandarSe.setText("Plan: FAMILIAR\r\n-Se incluye acceso completo a todo el equipo y áreas del gimnasio.\r\n-Durante su membresía se le aplicara un 30% de descuento a todos los miembros del plan al comprar productos de la marca EVOLVEFIT.\r\n-Membrecía mensual con costo de $1099.\r\n");
		txtrPlanEstandarSe.setLineWrap(true);

		txtrPlanEstandarSe.setWrapStyleWord(true);
		txtrPlanEstandarSe.setFont(new Font("Anton", Font.PLAIN, 16));
		
		JButton btn_aceptar = new JButton("Aceptar");
		btn_aceptar.setFont(new Font("Anton", Font.PLAIN, 15));
		btn_aceptar.setForeground(new Color(255, 255, 255));
		btn_aceptar.setBackground(new Color(0, 206, 82));
		btn_aceptar.setBounds(344, 230, 146, 39);
		inf_tarifa_estandar.add(btn_aceptar);
	}
}
