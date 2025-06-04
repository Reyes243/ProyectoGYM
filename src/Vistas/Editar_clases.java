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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatLightLaf;

public class Editar_clases {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Editar_clases window = new Editar_clases();
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
	public Editar_clases() {
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
		
		JLabel lblNewLabel = new JLabel("EDITAR CLASES");//titulo de inicio 
		lblNewLabel.setFont(new Font("Anton", Font.PLAIN, 26));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(60, 11, 309, 28);
		panel_3.add(lblNewLabel);
		ImageIcon p1 =new ImageIcon("Imagenes/imagen credencial.png");
		Image p2= p1.getImage();
		Image p3=p2.getScaledInstance(100, 150,Image.SCALE_SMOOTH);
		
		JLabel lblNewLabel_11 = new JLabel("Nombre de la clase: ");
		lblNewLabel_11.setFont(new Font("Anton", Font.PLAIN, 20));
		lblNewLabel_11.setBounds(58, 90, 179, 22);
		panel_2.add(lblNewLabel_11);
		
		JLabel lblNewLabel_10 = new JLabel("Entrenador asignado:");
		lblNewLabel_10.setFont(new Font("Anton", Font.PLAIN, 20));
		lblNewLabel_10.setBounds(58, 165, 179, 22);
		panel_2.add(lblNewLabel_10);
		
		JLabel lblNewLabel_9 = new JLabel("Turno: ");
		lblNewLabel_9.setFont(new Font("Anton", Font.PLAIN, 20));
		lblNewLabel_9.setBounds(58, 242, 55, 22);
		panel_2.add(lblNewLabel_9);
		
		JLabel lblNewLabel_13 = new JLabel("Horarios disponibles: ");
		lblNewLabel_13.setFont(new Font("Anton", Font.PLAIN, 20));
		lblNewLabel_13.setBounds(58, 323, 179, 22);
		panel_2.add(lblNewLabel_13);
		//info de intructor///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		JTextField Info_nombre= new JTextField("YOGA RELAX");
		Info_nombre.setBackground(new Color(204, 204, 204));
		Info_nombre.setFont(new Font("Anton", Font.PLAIN, 20));
		Info_nombre.setBounds(247, 83, 328, 37);
		panel_2.add(Info_nombre);
		
		JTextField Info_horarrio = new JTextField("Lunes, Miércoles y Viernes de 8:00 am a 12:00 pm");
		Info_horarrio.setBackground(new Color(204, 204, 204));
		Info_horarrio.setFont(new Font("Anton", Font.PLAIN, 20));
		Info_horarrio.setBounds(58, 356, 517, 37);
		panel_2.add(Info_horarrio);
		
		//comobox//////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		UIManager.put("ComboBox.buttonBackground", new Color(255, 205, 17)); 
		JComboBox comboBox_turno = new JComboBox();
		comboBox_turno.setBackground(new Color(204, 204, 204));
		comboBox_turno.setFont(new Font("Anton", Font.PLAIN, 20));
		comboBox_turno.setBounds(123, 238, 200, 31);
		String[] turnos = {"NINGUNA", "MATUTINO", "VESPERTINO", "MIXTO"};
		for (String turno : turnos) {
			comboBox_turno.addItem(turno);
		}
		panel_2.add(comboBox_turno);
		
		JComboBox comboBox_entrenador = new JComboBox();
		comboBox_entrenador.setFont(new Font("Anton", Font.PLAIN, 20));
		comboBox_entrenador.setBackground(new Color(204, 204, 204));
		comboBox_entrenador.setBounds(247, 161, 328, 31);
		String[] entrenador = {"NINGUNA", "Sebastian Torres", "Laura Mendez", "Camila Rodriguez", "Marco Rojas"};
		for (String entrenadores : entrenador) {
			comboBox_entrenador.addItem(entrenadores);
		}
		panel_2.add(comboBox_entrenador);
		
		//botones de accion para el instructor ///////////////////////////////////////////////////////////////////////
		JButton boton_guardar = new JButton("Guardar");
		boton_guardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		boton_guardar.setForeground(new Color(255, 255, 255));
		boton_guardar.setBackground(new Color(0, 206, 82));
		boton_guardar.setFont(new Font("Anton", Font.PLAIN, 18));
		boton_guardar.setBounds(748, 510, 160, 43);
		panel_2.add(boton_guardar);
		
		JButton boton_cancelar = new JButton("Cancelar");
		boton_cancelar.setForeground(new Color(255, 255, 255));
		boton_cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		boton_cancelar.setFont(new Font("Anton", Font.PLAIN, 18));
		boton_cancelar.setBackground(new Color(255, 0, 0));
		boton_cancelar.setBounds(554, 510, 160, 43);
		panel_2.add(boton_cancelar);
		
		
		
		//Botones laterales //////////////////////////////////////////////////////////////////////////////////////////////
		JButton boton_INICIO= new JButton("INICIO");//boton de inicio
		boton_INICIO.setBackground(new Color(255, 205, 17));
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
		boton_CLASES.setBackground(new Color(255, 255, 255));
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
