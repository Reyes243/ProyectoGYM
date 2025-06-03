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
		
		JPanel contras_mal = new JPanel();
		contras_mal.setBackground(new Color(255, 255, 255));
		contras_mal.setBounds(335, 234, 400, 180);
		frame.getContentPane().add(contras_mal);
		contras_mal.setLayout(null);
		
		JPanel panel_complemento= new JPanel();
		panel_complemento.setBackground(new Color(81, 151, 255));
		panel_complemento.setBounds(0, 0, 400, 33);
		contras_mal.add(panel_complemento);
		
		JLabel pregunta_de_confirmacion = new JLabel("<html><div style='text-align: center;'>Las contraseñas no coinciden.<br>Error</div></html>");
		pregunta_de_confirmacion.setFont(new Font("Anton", Font.PLAIN, 16));
		pregunta_de_confirmacion.setBounds(93, 44, 297, 59);
		contras_mal.add(pregunta_de_confirmacion);
		
		JButton boton_aceptar = new JButton("Aceptar");
		boton_aceptar.setBackground(new Color(0, 206, 82));
		boton_aceptar.setForeground(new Color(255, 255, 255));
		boton_aceptar.setFont(new Font("Anton", Font.PLAIN, 14));
		boton_aceptar.setBounds(148, 100, 102, 33);
		contras_mal.add(boton_aceptar);
	}
}
