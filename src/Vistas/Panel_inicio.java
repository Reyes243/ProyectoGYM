package Vistas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;

import com.formdev.flatlaf.FlatLightLaf;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import javax.swing.JTable;

public class Panel_inicio {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Panel_inicio window = new Panel_inicio();
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
	public Panel_inicio() {
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
		lblNewLabel_2.setBounds(73, 11, 128, 35);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("HEALTH & FITNESS");
		lblNewLabel_3.setFont(new Font("Anton", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(73, 42, 107, 22);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Panel administrativo");
		lblNewLabel_4.setFont(new Font("Anton", Font.PLAIN, 32));
		lblNewLabel_4.setBounds(407, 11, 270, 53);
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
		
		JLabel lblNewLabel = new JLabel("INICIO");//titulo de inicio 
		lblNewLabel.setFont(new Font("Anton", Font.PLAIN, 26));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(60, 11, 105, 28);
		panel_3.add(lblNewLabel);
		
		Object[][] data = {
			    {null, null, null, null, "1", "2", "3"},   // Semana 1
			    {"4", "5", "6", "7", "8", "9", "10"},    // Semana 2
			    {"11", "12", "13", "14", "15", "16", "17"}, // Semana 3
			    {"18", "19", "20", "21", "22", "23", "24"}, // Semana 4
			    {"25", "26", "27", "28", "29", "30", "31"}  // Semana 5
			};
			JTable table = new JTable(data, new String[]{"", "", "", "", "", "", ""}); 
			table.setFont(new Font("Anton", Font.PLAIN, 16));
			table.setBounds(70, 153, 610, 400);
			table.setBorder(BorderFactory.createLineBorder(new Color(204, 204, 204)));
			table.setShowGrid(true);
			table.setGridColor(new Color(204, 204, 204)); // Color de las líneas
			table.setTableHeader(null);
			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment(JLabel.CENTER);
			table.setDefaultRenderer(Object.class, centerRenderer);
			table.setRowHeight(80); 
			table.setShowHorizontalLines(true);
			table.setShowVerticalLines(true);
			panel_2.add(table);
			
			JLabel lblNewLabel_5 = new JLabel("Dom");
			lblNewLabel_5.setFont(new Font("Anton", Font.PLAIN, 16));
			lblNewLabel_5.setBounds(100, 127, 35, 15);
			panel_2.add(lblNewLabel_5);
			
			JLabel lblNewLabel_5_1 = new JLabel("Lun");
			lblNewLabel_5_1.setFont(new Font("Anton", Font.PLAIN, 16));
			lblNewLabel_5_1.setBounds(190, 127, 35, 15);
			panel_2.add(lblNewLabel_5_1);
			
			JLabel lblNewLabel_5_2 = new JLabel("Mar");
			lblNewLabel_5_2.setFont(new Font("Anton", Font.PLAIN, 16));
			lblNewLabel_5_2.setBounds(271, 127, 35, 15);
			panel_2.add(lblNewLabel_5_2);
			
			JLabel lblNewLabel_5_3 = new JLabel("Mie");
			lblNewLabel_5_3.setFont(new Font("Anton", Font.PLAIN, 16));
			lblNewLabel_5_3.setBounds(359, 127, 35, 15);
			panel_2.add(lblNewLabel_5_3);
			
			JLabel lblNewLabel_5_4 = new JLabel("Jue");
			lblNewLabel_5_4.setFont(new Font("Anton", Font.PLAIN, 16));
			lblNewLabel_5_4.setBounds(445, 127, 35, 15);
			panel_2.add(lblNewLabel_5_4);
			
			JLabel lblNewLabel_5_5 = new JLabel("Vie");
			lblNewLabel_5_5.setFont(new Font("Anton", Font.PLAIN, 16));
			lblNewLabel_5_5.setBounds(533, 127, 35, 15);
			panel_2.add(lblNewLabel_5_5);
			
			JLabel lblNewLabel_5_6 = new JLabel("Sab");
			lblNewLabel_5_6.setFont(new Font("Anton", Font.PLAIN, 16));
			lblNewLabel_5_6.setBounds(620, 127, 35, 15);
			panel_2.add(lblNewLabel_5_6);
			
			JLabel lblNewLabel_6 = new JLabel("MAYO 2025");
			lblNewLabel_6.setFont(new Font("Anton", Font.PLAIN, 28));
			lblNewLabel_6.setBounds(305, 61, 145, 55);
			panel_2.add(lblNewLabel_6);
			
			JLabel lblNewLabel_7 = new JLabel("<html>" +
				    "<div style='padding:5px; text-align:left; '>" +
				    "-Procura tomar suficiente agua durante tu entrenamiento y durante el día.<br><br>" +
				    "-El descanso correcto es fundamental para progresar, así como la técnica al " +
				    "ejecutar ejercicios, sobre todo si se hacen con pesos pesados." +
				    "</div></html>");
			lblNewLabel_7.setFont(new Font("Anton", Font.PLAIN, 16));
				lblNewLabel_7.setBounds(690, 206, 218, 334);
				lblNewLabel_7.setVerticalAlignment(JLabel.TOP);  // Alinea el texto en la parte superior
				panel_2.add(lblNewLabel_7);
			
			JLabel lblNewLabel_8 = new JLabel("Consejos:");
			lblNewLabel_8.setFont(new Font("Anton", Font.PLAIN, 20));
			lblNewLabel_8.setBounds(690, 161, 95, 34);
			panel_2.add(lblNewLabel_8);
		
		JButton boton_INICIO= new JButton("INICIO");//boton de inicio
		boton_INICIO.setBackground(new Color(255, 255, 255));
		boton_INICIO.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_INICIO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
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
		boton_TARIFAS.setBackground(new Color(255, 205, 17));
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
		
		JButton boton_CLASES = new JButton("CLASES");//boton de clases 
		boton_CLASES.setBackground(new Color(255, 205, 17));
		boton_CLASES.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CLASES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
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
