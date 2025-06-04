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
	private JTextField textField;

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
		
		JPanel id_de_usuario_clase  = new JPanel();
		id_de_usuario_clase.setBackground(new Color(255, 255, 255));
		id_de_usuario_clase.setBounds(335, 234, 400, 180);
		frame.getContentPane().add(id_de_usuario_clase);
		id_de_usuario_clase.setLayout(null);
		
		JPanel panel_complemento= new JPanel();
		panel_complemento.setBackground(new Color(81, 151, 255));
		panel_complemento.setBounds(0, 0, 400, 33);
		id_de_usuario_clase.add(panel_complemento);
		
		JLabel pregunta_de_confirmacion = new JLabel("<html><div style='text-align: center;'>Ingrese el ID del usuario a inscribir a la clase:<br></div></html>");
		pregunta_de_confirmacion.setFont(new Font("Anton", Font.PLAIN, 16));
		pregunta_de_confirmacion.setBounds(48, 33, 352, 59);
		id_de_usuario_clase.add(pregunta_de_confirmacion);
		
		JButton boton_aceptar = new JButton("Aceptar");
		boton_aceptar.setBackground(new Color(0, 206, 82));
		boton_aceptar.setForeground(new Color(255, 255, 255));
		boton_aceptar.setFont(new Font("Anton", Font.PLAIN, 14));
		boton_aceptar.setBounds(255, 136, 102, 33);
		id_de_usuario_clase.add(boton_aceptar);
		
		JButton boton_aceptar_1 = new JButton("Cancelar");
		boton_aceptar_1.setForeground(Color.WHITE);
		boton_aceptar_1.setFont(new Font("Anton", Font.PLAIN, 14));
		boton_aceptar_1.setBackground(new Color(255, 0, 0));
		boton_aceptar_1.setBounds(48, 136, 102, 33);
		id_de_usuario_clase.add(boton_aceptar_1);
		
		textField = new JTextField();
		textField.setBounds(153, 90, 102, 33);
		id_de_usuario_clase.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("ID:");
		lblNewLabel.setFont(new Font("Anton", Font.PLAIN, 16));
		lblNewLabel.setBounds(136, 90, 46, 35);
		id_de_usuario_clase.add(lblNewLabel);
	}
}
