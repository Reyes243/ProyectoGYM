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
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.JSpinner;
import javax.swing.JComboBox;

public class Informacion_de_cliente {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Informacion_de_cliente window = new Informacion_de_cliente();
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
	public Informacion_de_cliente() {
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
		Imagen_de_usuario.setBounds(15, 90, 200, 300);
		Imagen_de_usuario.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
		Imagen_de_usuario.setHorizontalAlignment(SwingConstants.CENTER);
		Imagen_de_usuario.setVerticalAlignment(SwingConstants.CENTER);
		ImageIcon p1 =new ImageIcon("Imagenes/imagen credencial.png");
		Image p2= p1.getImage();
		Image p3=p2.getScaledInstance(100, 150,Image.SCALE_SMOOTH);
		Imagen_de_usuario .setIcon(new ImageIcon(p3));
		panel_2.add(Imagen_de_usuario);
		
		JLabel lblNewLabel_5 = new JLabel("ID Cliente: ");
		lblNewLabel_5.setFont(new Font("Anton", Font.PLAIN, 20));
		lblNewLabel_5.setBounds(235, 90, 85, 32);
		panel_2.add(lblNewLabel_5);
		
		JLabel lblNewLabel_11 = new JLabel("Nombre:");
		lblNewLabel_11.setFont(new Font("Anton", Font.PLAIN, 16));
		lblNewLabel_11.setBounds(235, 160, 60, 22);
		panel_2.add(lblNewLabel_11);
		
		JLabel lblNewLabel_10 = new JLabel("Fecha de nacimiento:");
		lblNewLabel_10.setFont(new Font("Anton", Font.PLAIN, 16));
		lblNewLabel_10.setBounds(235, 210, 147, 22);
		panel_2.add(lblNewLabel_10);
		
		JLabel lblNewLabel_9 = new JLabel("Correo electronico:");
		lblNewLabel_9.setFont(new Font("Anton", Font.PLAIN, 16));
		lblNewLabel_9.setBounds(235, 260, 132, 22);
		panel_2.add(lblNewLabel_9);
		
		JLabel lblNewLabel_8 = new JLabel("Teléfono:");
		lblNewLabel_8.setFont(new Font("Anton", Font.PLAIN, 16));
		lblNewLabel_8.setBounds(235, 310, 117, 22);
		panel_2.add(lblNewLabel_8);
		
		JLabel lblNewLabel_7 = new JLabel("Clases inscritas:");
		lblNewLabel_7.setFont(new Font("Anton", Font.PLAIN, 16));
		lblNewLabel_7.setBounds(235, 362, 117, 28);
		panel_2.add(lblNewLabel_7);
		
		JLabel lblNewLabel_6 = new JLabel("Fecha del siguiente cargo:");
		lblNewLabel_6.setFont(new Font("Anton", Font.PLAIN, 16));
		lblNewLabel_6.setBounds(575, 310, 173, 22);
		panel_2.add(lblNewLabel_6);
		
		JLabel lblNewLabel_12 = new JLabel("Siguiente pago:");
		lblNewLabel_12.setFont(new Font("Anton", Font.PLAIN, 16));
		lblNewLabel_12.setBounds(575, 260, 100, 22);
		panel_2.add(lblNewLabel_12);
		
		JLabel lblNewLabel_13 = new JLabel("Pago anterior:");
		lblNewLabel_13.setFont(new Font("Anton", Font.PLAIN, 16));
		lblNewLabel_13.setBounds(575, 210, 93, 22);
		panel_2.add(lblNewLabel_13);
		
		JLabel lblNewLabel_14 = new JLabel("Plan:");
		lblNewLabel_14.setFont(new Font("Anton", Font.PLAIN, 16));
		lblNewLabel_14.setBounds(575, 160, 32, 22);
		panel_2.add(lblNewLabel_14);
		
		//informacion del usuario ////////////////////////////////////////////////////////////
		JLabel Info_ID = new JLabel("2");
		Info_ID.setFont(new Font("Anton", Font.PLAIN, 20));
		Info_ID.setBounds(321, 92, 46, 28);
		panel_2.add(Info_ID);
		
		JLabel Info_nombre= new JLabel("Jose Lopez");
		Info_nombre.setFont(new Font("Anton", Font.PLAIN, 16));
		Info_nombre.setBounds(296, 160, 76, 21);
		panel_2.add(Info_nombre);
		
		JLabel Info_fecha_nacimiento = new JLabel("15/08/1980");
		Info_fecha_nacimiento.setFont(new Font("Anton", Font.PLAIN, 16));
		Info_fecha_nacimiento.setBounds(382, 210, 147, 22);
		panel_2.add(Info_fecha_nacimiento);
		
		JLabel Info_correo = new JLabel("JoseLg@hotmai.com");
		Info_correo.setFont(new Font("Anton", Font.PLAIN, 16));
		Info_correo.setBounds(377, 260, 152, 22);
		panel_2.add(Info_correo);
		
		JLabel Info_telefono = new JLabel("612 187 0000");
		Info_telefono.setFont(new Font("Anton", Font.PLAIN, 16));
		Info_telefono.setBounds(296, 310, 152, 22);
		panel_2.add(Info_telefono);
		
		JLabel Info_clase = new JLabel("TECNICA EN MAQUINAS");
		Info_clase.setFont(new Font("Anton", Font.PLAIN, 16));
		Info_clase.setBounds(362, 362, 167, 28);
		panel_2.add(Info_clase);
		
		JLabel Info_plan = new JLabel("PREMIUM");
		Info_plan.setFont(new Font("Anton", Font.PLAIN, 16));
		Info_plan.setBounds(617, 160, 161, 22);
		panel_2.add(Info_plan);
		
		JLabel Info_pago_ant = new JLabel("$600");
		Info_pago_ant.setFont(new Font("Anton", Font.PLAIN, 16));
		Info_pago_ant.setBounds(678, 210, 152, 22);
		panel_2.add(Info_pago_ant);
		
		JLabel Info_pago_sig = new JLabel("$600");
		Info_pago_sig.setFont(new Font("Anton", Font.PLAIN, 16));
		Info_pago_sig.setBounds(685, 260, 145, 22);
		panel_2.add(Info_pago_sig);
		
		JLabel Info_Fecha_pago = new JLabel("09/06/2025");
		Info_Fecha_pago.setFont(new Font("Anton", Font.PLAIN, 16));
		Info_Fecha_pago.setBounds(758, 309, 138, 23);
		panel_2.add(Info_Fecha_pago);
		
		//botones de accion para el cliente ///////////////////////////////////////////////////////////////////////
		JButton boton_descraga_credencial = new JButton("Descargar credencial");
		boton_descraga_credencial.setBackground(new Color(255, 205, 17));
		boton_descraga_credencial.setFont(new Font("Anton", Font.PLAIN, 12));
		boton_descraga_credencial.setBounds(727, 501, 160, 32);
		panel_2.add(boton_descraga_credencial);
		
		JButton boton_descargar_info = new JButton("Descargar información");
		boton_descargar_info.setBackground(new Color(255, 205, 17));
		boton_descargar_info.setFont(new Font("Anton", Font.PLAIN, 12));
		boton_descargar_info.setBounds(557, 501, 160, 32);
		panel_2.add(boton_descargar_info);
		
		JButton boton_editar_info = new JButton("Editar información");
		boton_editar_info.setBackground(new Color(255, 205, 17));
		boton_editar_info.setFont(new Font("Anton", Font.PLAIN, 12));
		boton_editar_info.setBounds(387, 501, 160, 32);
		panel_2.add(boton_editar_info);
		
		JButton boton_historial_asistencias = new JButton("Historial de asistencia");
		boton_historial_asistencias.setBackground(new Color(255, 205, 17));
		boton_historial_asistencias.setFont(new Font("Anton", Font.PLAIN, 12));
		boton_historial_asistencias.setBounds(217, 501, 160, 32);
		panel_2.add(boton_historial_asistencias);
		
		JButton boton_historial_pagos = new JButton("Historial de pagos");
		boton_historial_pagos.setBackground(new Color(255, 205, 17));
		boton_historial_pagos.setFont(new Font("Anton", Font.PLAIN, 12));
		boton_historial_pagos.setBounds(47, 501, 160, 32);
		panel_2.add(boton_historial_pagos);
		
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