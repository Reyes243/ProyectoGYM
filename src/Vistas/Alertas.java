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
		
		JPanel inicio_de_sesion_correcto = new JPanel();
		inicio_de_sesion_correcto.setBackground(new Color(255, 255, 255));
		inicio_de_sesion_correcto.setBounds(335, 234, 400, 180);
		frame.getContentPane().add(inicio_de_sesion_correcto);
		inicio_de_sesion_correcto.setLayout(null);
		
		JPanel panel_complemento= new JPanel();
		panel_complemento.setBackground(new Color(81, 151, 255));
		panel_complemento.setBounds(0, 0, 400, 33);
		inicio_de_sesion_correcto.add(panel_complemento);
		
		JLabel pregunta_de_confirmacion = new JLabel("<html><div style='text-align: center;'>Inicio de sesion correcto<br>Benvenido</div></html>");
		pregunta_de_confirmacion.setFont(new Font("Anton", Font.PLAIN, 16));
		pregunta_de_confirmacion.setBounds(116, 44, 230, 59);
		inicio_de_sesion_correcto.add(pregunta_de_confirmacion);
		
		JButton boton_aceptar = new JButton("Aceptar");
		boton_aceptar.setBackground(new Color(0, 206, 82));
		boton_aceptar.setForeground(new Color(255, 255, 255));
		boton_aceptar.setFont(new Font("Anton", Font.PLAIN, 14));
		boton_aceptar.setBounds(151, 114, 102, 33);
		inicio_de_sesion_correcto.add(boton_aceptar);
	}
}
