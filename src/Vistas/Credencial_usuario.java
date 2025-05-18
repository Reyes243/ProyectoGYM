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
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;

import com.formdev.flatlaf.FlatLightLaf;

public class Credencial_usuario {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Credencial_usuario window = new Credencial_usuario();
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
	public Credencial_usuario() {
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
		
		JLabel lblNewLabel = new JLabel("CONSULTA DE INFORMACION");//titulo de inicio 
		lblNewLabel.setFont(new Font("Anton", Font.PLAIN, 26));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(60, 11, 309, 28);
		panel_3.add(lblNewLabel);
		
		JLabel Imagen_de_usuario = new JLabel("");
		Imagen_de_usuario.setBounds(70, 140, 200, 300);
		Imagen_de_usuario.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
		Imagen_de_usuario.setHorizontalAlignment(SwingConstants.CENTER);
		Imagen_de_usuario.setVerticalAlignment(SwingConstants.CENTER);
		ImageIcon p1 =new ImageIcon("Imagenes/imagen credencial.png");
		Image p2= p1.getImage();
		Image p3=p2.getScaledInstance(100, 150,Image.SCALE_SMOOTH);
		Imagen_de_usuario .setIcon(new ImageIcon(p3));
		panel_2.add(Imagen_de_usuario);
		
		JLabel lblNewLabel_5 = new JLabel("MIEMBRO");
		lblNewLabel_5.setFont(new Font("Anton", Font.PLAIN, 32));
		lblNewLabel_5.setBounds(70, 61, 147, 68);
		panel_2.add(lblNewLabel_5);
		
		JLabel lblNewLabel_11 = new JLabel("Nombre:");
		lblNewLabel_11.setFont(new Font("Anton", Font.PLAIN, 22));
		lblNewLabel_11.setBounds(340, 160, 84, 32);
		panel_2.add(lblNewLabel_11);
		
		JLabel lblNewLabel_10 = new JLabel("Fecha de nacimiento:");
		lblNewLabel_10.setFont(new Font("Anton", Font.PLAIN, 22));
		lblNewLabel_10.setBounds(340, 230, 200, 32);
		panel_2.add(lblNewLabel_10);
		
		JLabel lblNewLabel_6 = new JLabel("Vigencia hasta: ");
		lblNewLabel_6.setFont(new Font("Anton", Font.PLAIN, 22));
		lblNewLabel_6.setBounds(340, 370, 144, 32);
		panel_2.add(lblNewLabel_6);
		
		JLabel lblNewLabel_13 = new JLabel("No. Identificador: ");
		lblNewLabel_13.setFont(new Font("Anton", Font.PLAIN, 22));
		lblNewLabel_13.setBounds(688, 160, 155, 32);
		panel_2.add(lblNewLabel_13);
		
		JLabel lblNewLabel_14 = new JLabel("Plan actual: ");
		lblNewLabel_14.setFont(new Font("Anton", Font.PLAIN, 22));
		lblNewLabel_14.setBounds(340, 300, 108, 32);
		panel_2.add(lblNewLabel_14);
		
		JLabel lblNewLabel_7 = new JLabel("");
		lblNewLabel_7.setBounds(779, 370, 100, 100);
		lblNewLabel_7.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setVerticalAlignment(SwingConstants.CENTER);
		ImageIcon s1 =new ImageIcon("Imagenes/logo ginmasio.png");
		Image s2= s1.getImage();
		Image s3=s2.getScaledInstance(100, 100,Image.SCALE_SMOOTH);
		lblNewLabel_7 .setIcon(new ImageIcon(s3));
		panel_2.add(lblNewLabel_7);
		
		//info de usuario/////////////////////////////////////////////////////////////////////////////////////////////////
		JLabel Info_nombre= new JLabel("Jose Lopez");
		Info_nombre.setFont(new Font("Anton", Font.PLAIN, 22));
		Info_nombre.setBounds(420, 162, 118, 29);
		panel_2.add(Info_nombre);
		
		JLabel Info_fecha_nacimiento = new JLabel("15/08/1980");
		Info_fecha_nacimiento.setFont(new Font("Anton", Font.PLAIN, 22));
		Info_fecha_nacimiento.setBounds(532, 230, 127, 32);
		panel_2.add(Info_fecha_nacimiento);
		
		JLabel Info_plan = new JLabel("PREMIUM");
		Info_plan.setFont(new Font("Anton", Font.PLAIN, 22));
		Info_plan.setBounds(444, 300, 118, 32);
		panel_2.add(Info_plan);
		
		JLabel Info_identificador = new JLabel("2");
		Info_identificador.setFont(new Font("Anton", Font.PLAIN, 22));
		Info_identificador.setBounds(843, 160, 36, 32);
		panel_2.add(Info_identificador);
		
		JLabel Info_Vigencia= new JLabel("09/06/2025");
		Info_Vigencia.setFont(new Font("Anton", Font.PLAIN, 22));
		Info_Vigencia.setBounds(478, 370, 138, 32);
		panel_2.add(Info_Vigencia);
		
		//botones de accion para el cliente ///////////////////////////////////////////////////////////////////////
		JButton boton_descraga_credencial = new JButton("Descargar ");
		boton_descraga_credencial.setBackground(new Color(255, 205, 17));
		boton_descraga_credencial.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_descraga_credencial.setBounds(740, 494, 147, 39);
		panel_2.add(boton_descraga_credencial);
		
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
		boton_CLIENTES.setBackground(new Color(255, 255, 255));
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