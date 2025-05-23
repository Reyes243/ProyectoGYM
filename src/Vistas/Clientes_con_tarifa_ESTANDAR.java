package Vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.JTableHeader;

import com.formdev.flatlaf.FlatLightLaf;

public class Clientes_con_tarifa_ESTANDAR {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Clientes_con_tarifa_ESTANDAR window = new Clientes_con_tarifa_ESTANDAR();
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
	public Clientes_con_tarifa_ESTANDAR() {
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
		ImageIcon portada1 =new ImageIcon("Imagenes/logo sin letras.png");
		Image portada2= portada1.getImage();
		Image portada3=portada2.getScaledInstance(53, 53,Image.SCALE_SMOOTH);
		lblNewLabel_1 .setIcon(new ImageIcon(portada3));
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("EVOLVEFIT");
		lblNewLabel_2.setFont(new Font("Anton", Font.PLAIN, 30));
		lblNewLabel_2.setBounds(73, 11, 218, 35);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("HEALTH & FITNESS");
		lblNewLabel_3.setFont(new Font("Anton", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(73, 42, 232, 22);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Panel administrativo");
		lblNewLabel_4.setFont(new Font("Anton", Font.PLAIN, 32));
		lblNewLabel_4.setBounds(407, 11, 501, 53);
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
		
		JLabel lblNewLabel = new JLabel("TARIFA: ESTANDAR");//titulo de inicio 
		lblNewLabel.setFont(new Font("Anton", Font.PLAIN, 26));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(60, 11, 523, 28);
		panel_3.add(lblNewLabel);
		
		Object[][] data = {
				{2, "Laura", "Martínez", "0987654321", "laura@mail.com"}
			};
		
		String[] columnas ={"ID cliente", "Nombre(s)", "Primer apellido", "Teléfono", "Correo electrónico"};
		
		JScrollPane scrollPane_Usuario = new JScrollPane();//tabla del usario
		scrollPane_Usuario.setBounds(10, 61, 898, 427);
		panel_2.add(scrollPane_Usuario);
		table = new JTable(data, columnas);
		table.setFont(new Font("Anton", Font.PLAIN, 12));
		table.setBackground(new Color(204, 204, 204));
		scrollPane_Usuario.setViewportView(table);
		
		JTableHeader header = table.getTableHeader();
		header.setBackground(Color.BLACK);
		header.setForeground(Color.WHITE);
		header.setFont(new Font("Anton", Font.PLAIN, 14));
		header.setReorderingAllowed(false);
		scrollPane_Usuario.setViewportView(table);
		
		JButton boton_regresar = new JButton("Regresar");//boton regresar
		boton_regresar.setFont(new Font("Anton", Font.PLAIN, 20));
		boton_regresar.setBackground(new Color(255, 205, 17));
		boton_regresar.setBounds(724, 503, 184, 50);
		panel_2.add(boton_regresar);
		
		JButton boton_INICIO= new JButton("INICIO");
		boton_INICIO.setBackground(new Color(255, 205, 17));
		boton_INICIO.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_INICIO.setBounds(10, 86, 136, 71);
		panel.add(boton_INICIO);
		
		JButton boton_CLIENTES = new JButton("CLIENTES");//boton de clientes
		boton_CLIENTES.setBackground(new Color(255, 205, 17));
		boton_CLIENTES.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CLIENTES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		boton_CLIENTES.setBounds(10, 168, 136, 71);
		panel.add(boton_CLIENTES);
		
		JButton boton_TARIFAS = new JButton("TARIFAS");//boton de tarifas
		boton_TARIFAS.setBackground(new Color(255, 255, 255));
		boton_TARIFAS.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_TARIFAS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		boton_TARIFAS.setBounds(10, 250, 136, 71);
		panel.add(boton_TARIFAS);
		
		JButton boton_INSTRUCTORES = new JButton("INSTRUCTORES");//boton de instructores
		boton_INSTRUCTORES.setBackground(new Color(255, 205, 17));
		boton_INSTRUCTORES.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_INSTRUCTORES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		boton_INSTRUCTORES.setBounds(10, 332, 136, 71);
		panel.add(boton_INSTRUCTORES);
		
		JButton boton_CLASES = new JButton("CLASES");
		boton_CLASES.setBackground(new Color(255, 205, 17));
		boton_CLASES.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CLASES.setBounds(10, 414, 136, 71);
		panel.add(boton_CLASES);
		
		JButton boton_CHECADOR = new JButton("CHECADOR");//boton de checador
		boton_CHECADOR.setBackground(new Color(255, 205, 17));
		boton_CHECADOR.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CHECADOR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		boton_CHECADOR.setBounds(10, 496, 136, 71);
		panel.add(boton_CHECADOR);
		
		JButton boton_CERRAR_SESION = new JButton("CERRAR SESION");//boton de cerrar sesion 
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
