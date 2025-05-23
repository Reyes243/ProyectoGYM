package Vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatLightLaf;

public class Editar_tarifas_FAMILIAR {

	private JFrame frame;
	private JTextField textField;
	private JTextField txtEstandar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Editar_tarifas_FAMILIAR window = new Editar_tarifas_FAMILIAR();
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
	public Editar_tarifas_FAMILIAR() {
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
		frame.setResizable(false);
		frame.setBounds(0, 0, 1100, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(204, 204, 204));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(0, 0, 1084, 75);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(10, 11, 53, 53);
		ImageIcon portada1 = new ImageIcon("Imagenes/logo sin letras.png");
		Image portada2 = portada1.getImage();
		Image portada3 = portada2.getScaledInstance(53, 53, Image.SCALE_SMOOTH);
		lblNewLabel_1.setIcon(new ImageIcon(portada3));
		panel_1.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("EVOLVEFIT");
		lblNewLabel_2.setFont(new Font("Anton", Font.PLAIN, 30));
		lblNewLabel_2.setBounds(73, 11, 324, 35);
		panel_1.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("HEALTH & FITNESS");
		lblNewLabel_3.setFont(new Font("Anton", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(73, 42, 281, 22);
		panel_1.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Panel administrativo");
		lblNewLabel_4.setFont(new Font("Anton", Font.PLAIN, 32));
		lblNewLabel_4.setBounds(407, 11, 560, 53);
		panel_1.add(lblNewLabel_4);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBounds(156, 86, 918, 564);
		panel.add(panel_2);
		panel_2.setLayout(null);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(0, 0, 0));
		panel_3.setBounds(0, 0, 918, 50);
		panel_2.add(panel_3);
		panel_3.setLayout(null);

		JLabel lblNewLabel = new JLabel("DESCRIPCION");// titulo de inicio
		lblNewLabel.setFont(new Font("Anton", Font.PLAIN, 26));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(60, 11, 223, 28);
		panel_3.add(lblNewLabel);
		// tarifas//////////////////////////////////////////////////////////////////////
		JPanel plan_estandar = new JPanel();
		plan_estandar.setBackground(new Color(255, 255, 255));
		plan_estandar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		plan_estandar.setBounds(40, 150, 275, 185);
		panel_2.add(plan_estandar);
		plan_estandar.setLayout(null);

		JLabel lblNewLabel_5 = new JLabel("Plan");
		lblNewLabel_5.setFont(new Font("Anton", Font.PLAIN, 22));
		lblNewLabel_5.setBounds(20, 26, 100, 35);
		plan_estandar.add(lblNewLabel_5);

		JTextField lblNewLabel_6 = new JTextField("FAMILIAR");
		lblNewLabel_6.setBackground(new Color(204, 204, 204));
		lblNewLabel_6.setFont(new Font("Anton", Font.PLAIN, 28));
		lblNewLabel_6.setBounds(20, 72, 172, 35);
		plan_estandar.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("$");
		lblNewLabel_7.setFont(new Font("Anton", Font.PLAIN, 28));
		lblNewLabel_7.setBounds(20, 118, 13, 35);
		plan_estandar.add(lblNewLabel_7);

		JPanel descripcion_plan = new JPanel();
		descripcion_plan.setBackground(new Color(255, 255, 255));
		descripcion_plan.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		descripcion_plan.setBounds(325, 150, 565, 263);
		panel_2.add(descripcion_plan);
		descripcion_plan.setLayout(null);

		JLabel lblNewLabel_8 = new JLabel("Plan:");
		lblNewLabel_8.setFont(new Font("Anton", Font.PLAIN, 22));
		lblNewLabel_8.setBounds(20, 26, 44, 35);
		descripcion_plan.add(lblNewLabel_8);

		txtEstandar = new JTextField();
		txtEstandar.setBackground(new Color(204, 204, 204));
		txtEstandar.setFont(new Font("Anton", Font.PLAIN, 22));
		txtEstandar.setText("FAMILIAR");
		txtEstandar.setBounds(74, 26, 214, 32);
		descripcion_plan.add(txtEstandar);
		txtEstandar.setColumns(10);

		JTextArea txtrSeIncluyeAcceso = new JTextArea();
		txtrSeIncluyeAcceso.setLineWrap(true);
		txtrSeIncluyeAcceso.setWrapStyleWord(true);
		txtrSeIncluyeAcceso.setFont(new Font("Anton", Font.PLAIN, 17));
		txtrSeIncluyeAcceso.setText("Se incluye acceso completo a todo el equipo y áreas del gimnasio.\r\n"
				+ "Durante su membresía se le aplicara un 30% de descuento a todos los miembros del plan al comprar productos de la marca EVOLVEFIT.\r\n"
				+ "Miembros máximos para veneficios: 4\r\n"
				+ "Membrecía mensual con costo de $1099."
				+ "");
		txtrSeIncluyeAcceso.setBackground(new Color(204, 204, 204));
		txtrSeIncluyeAcceso.setBounds(20, 72, 527, 169);
		descripcion_plan.add(txtrSeIncluyeAcceso);
		ImageIcon p1 = new ImageIcon("Imagenes/editar.png");
		Image p2 = p1.getImage();
		Image p3 = p2.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		ImageIcon e1 = new ImageIcon("Imagenes/eliminar.png");
		Image e2 = e1.getImage();
		Image e3 = e2.getScaledInstance(20, 20, Image.SCALE_SMOOTH);

		textField = new JTextField();
		textField.setBackground(new Color(204, 204, 204));
		textField.setFont(new Font("Anton", Font.PLAIN, 28));
		textField.setText("1099");
		textField.setBounds(34, 118, 75, 35);
		plan_estandar.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_9 = new JLabel("/ mes");
		lblNewLabel_9.setFont(new Font("Anton", Font.PLAIN, 28));
		lblNewLabel_9.setBounds(111, 118, 100, 35);
		plan_estandar.add(lblNewLabel_9);
		ImageIcon s1 = new ImageIcon("Imagenes/editar.png");
		Image s2 = s1.getImage();
		Image s3 = s2.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		ImageIcon q1 = new ImageIcon("Imagenes/eliminar.png");
		Image q2 = q1.getImage();
		Image q3 = q2.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		ImageIcon a1 = new ImageIcon("Imagenes/editar.png");
		Image a2 = a1.getImage();
		Image a3 = a2.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		ImageIcon f1 = new ImageIcon("Imagenes/eliminar.png");
		Image f2 = f1.getImage();
		Image f3 = f2.getScaledInstance(20, 20, Image.SCALE_SMOOTH);

		JButton boton_cancelar = new JButton("Cancelar");
		boton_cancelar.setForeground(new Color(255, 255, 255));
		boton_cancelar.setBackground(new Color(255, 0, 0));
		boton_cancelar.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_cancelar.setBounds(625, 503, 128, 50);
		panel_2.add(boton_cancelar);

		JButton boton_guardar = new JButton("Guardar");
		boton_guardar.setBackground(new Color(0, 206, 82));
		boton_guardar.setForeground(new Color(255, 255, 255));
		boton_guardar.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_guardar.setBounds(763, 503, 128, 50);
		panel_2.add(boton_guardar);

		// botones de los
		// laterales/////////////////////////////////////////////////////////////////////////////////////////////////////
		JButton boton_INICIO = new JButton("INICIO");// boton de inicio
		boton_INICIO.setBackground(new Color(255, 205, 17));
		boton_INICIO.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_INICIO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		boton_INICIO.setBounds(10, 86, 136, 71);
		panel.add(boton_INICIO);

		JButton boton_CLIENTES = new JButton("CLIENTES");// boton de clientes
		boton_CLIENTES.setBackground(new Color(255, 205, 17));
		boton_CLIENTES.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CLIENTES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		boton_CLIENTES.setBounds(10, 168, 136, 71);
		panel.add(boton_CLIENTES);

		JButton boton_TARIFAS = new JButton("TARIFAS");// boton de tarifas
		boton_TARIFAS.setBackground(new Color(255, 255, 255));
		boton_TARIFAS.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_TARIFAS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		boton_TARIFAS.setBounds(10, 250, 136, 71);
		panel.add(boton_TARIFAS);

		JButton boton_INSTRUCTORES = new JButton("INSTRUCTORES");// boton de instructores
		boton_INSTRUCTORES.setBackground(new Color(255, 205, 17));
		boton_INSTRUCTORES.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_INSTRUCTORES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		boton_INSTRUCTORES.setBounds(10, 332, 136, 71);
		panel.add(boton_INSTRUCTORES);

		JButton boton_CLASES = new JButton("CLASES");// boton de clases
		boton_CLASES.setBackground(new Color(255, 205, 17));
		boton_CLASES.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CLASES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		boton_CLASES.setBounds(10, 414, 136, 71);
		panel.add(boton_CLASES);

		JButton boton_CHECADOR = new JButton("CHECADOR");// boton de checador
		boton_CHECADOR.setBackground(new Color(255, 205, 17));
		boton_CHECADOR.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CHECADOR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		boton_CHECADOR.setBounds(10, 496, 136, 71);
		panel.add(boton_CHECADOR);

		JButton boton_CERRAR_SESION = new JButton("CERRAR SESION");// boton de cerrar sesion
		boton_CERRAR_SESION.setBackground(new Color(255, 205, 17));
		boton_CERRAR_SESION.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CERRAR_SESION.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		boton_CERRAR_SESION.setBounds(10, 579, 136, 71);
		panel.add(boton_CERRAR_SESION);
	}
}
