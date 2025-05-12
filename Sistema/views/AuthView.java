package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;

import com.formdev.flatlaf.FlatLightLaf;

import models.AuthModel;

public class AuthView {
	
	public AuthView() { 
	}
	
	public void login() {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
            UIManager.put("Button.arc", 8); // Esquinas redondeadas
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        initialize();
    }
	
    private void initialize() {
        JFrame frame = new JFrame();
        frame.setResizable(false);
        frame.setBounds(0, 0, 1100, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(204, 204, 204));
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(255, 255, 255));
        panel_1.setBounds(15, 16, 1054, 629);
        panel.add(panel_1);
        panel_1.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("EVOLVEFIT");//titulo del gym
        lblNewLabel.setFont(new Font("Anton", Font.PLAIN, 38));
        lblNewLabel.setBounds(20, 20, 177, 87);
        panel_1.add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("HEALTH & FITNESS");//subtitulo de gym
        lblNewLabel_1.setFont(new Font("Anton", Font.PLAIN, 14));
        lblNewLabel_1.setBounds(20, 93, 177, 14);
        panel_1.add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("Panel de administración");//subtitulo de gym 2 
        lblNewLabel_2.setFont(new Font("Anton", Font.PLAIN, 22));
        lblNewLabel_2.setBounds(20, 152, 319, 41);
        panel_1.add(lblNewLabel_2);
        
        JLabel lblNewLabel_3 = new JLabel("Correo electronico:");//titulo del correo
        lblNewLabel_3.setFont(new Font("Anton", Font.PLAIN, 18));
        lblNewLabel_3.setBounds(20, 219, 193, 41);
        panel_1.add(lblNewLabel_3);
        
        JTextField cuadro_txt_correo = new JTextField();//panel de texto del correo electronico
        cuadro_txt_correo.setBackground(new Color(204, 204, 204));
        cuadro_txt_correo.setFont(new Font("Anton", Font.PLAIN, 12));
        cuadro_txt_correo.setBounds(20, 271, 389, 30);
        cuadro_txt_correo.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        panel_1.add(cuadro_txt_correo);
        cuadro_txt_correo.setColumns(10);
        
        JLabel lblNewLabel_4 = new JLabel("");//cuadro de la imaegen del login 
        lblNewLabel_4.setBounds(439, 0, 615, 629);
        panel_1.add(lblNewLabel_4);
        ImageIcon portada1 =new ImageIcon("Imagenes/fondo login.png");
		Image portada2= portada1.getImage();
		Image portada3=portada2.getScaledInstance(615, 629,Image.SCALE_SMOOTH);
		lblNewLabel_4 .setIcon(new ImageIcon(portada3));
		
		JLabel lblNewLabel_5 = new JLabel("Contraseña:");//titulo de la contraseña
		lblNewLabel_5.setFont(new Font("Anton", Font.PLAIN, 18));
		lblNewLabel_5.setBounds(20, 323, 193, 41);
		panel_1.add(lblNewLabel_5);
		
		JPasswordField cuadro_txt_contraseña = new JPasswordField();//panel de texto de la contraseña
		cuadro_txt_contraseña.setBackground(new Color(204, 204, 204));
		cuadro_txt_contraseña.setFont(new Font("Anton", Font.PLAIN, 12));
		cuadro_txt_contraseña.setBounds(20, 375, 389, 30);
		cuadro_txt_contraseña.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
		panel_1.add(cuadro_txt_contraseña);
		cuadro_txt_contraseña.setColumns(10);
		
		JButton boton_inicio_sesion = new JButton("INICIAR SESION");//boton de inicio de sesion
		boton_inicio_sesion.setForeground(new Color(255, 255, 255));
		boton_inicio_sesion.setBackground(new Color(0, 143, 57));
		boton_inicio_sesion.setFont(new Font("Anton", Font.PLAIN, 20));
		boton_inicio_sesion.setBounds(80, 445, 279, 56);
		boton_inicio_sesion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Boolean flag1 = false, flag2 = false;
				
				if (cuadro_txt_correo.getText().equals("")) {
					cuadro_txt_correo.setBorder(BorderFactory.createLineBorder(Color.RED, 3));

				}else {
					cuadro_txt_correo.setBorder(BorderFactory.createLineBorder(Color.green, 3));
					flag1 = true;
				}
				String myPassword = new String(cuadro_txt_contraseña.getPassword());
				if (myPassword.equals("")) {
					cuadro_txt_contraseña.setBorder(BorderFactory.createLineBorder(Color.RED, 3));

				} else {
					cuadro_txt_contraseña.setBorder(BorderFactory.createLineBorder(Color.green, 3));
					flag2 = true;
				}
				
				frame.dispose();
				AuthView.this.Panel_inicio();
				
				
			}
		});
		panel_1.add(boton_inicio_sesion);
		
		JButton boton_registro = new JButton("REGISTRARSE");//Boton de registro
		boton_registro.setBackground(new Color(255, 205, 17));
		boton_registro.setForeground(new Color(0, 0, 0));
		boton_registro.setFont(new Font("Anton", Font.PLAIN, 20));
		boton_registro.setBounds(80, 530, 279, 56);
		boton_registro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				AuthView.this.registro();
			}
		});
		panel_1.add(boton_registro);
		
		frame.add(panel);
		frame.repaint();
		frame.revalidate();
		frame.setVisible(true);
        
        
    }
    public void registro() {
		try {
            UIManager.setLookAndFeel(new FlatLightLaf());
            UIManager.put("Button.arc", 8); // Esquinas redondeadas
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        initialize2();
    }

    private void initialize2() {
        JFrame frame = new JFrame();
        frame.setResizable(false);
        frame.setBounds(0, 0, 1100, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(204, 204, 204));
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(255, 255, 255));
        panel_1.setBounds(15, 16, 1054, 629);
        panel.add(panel_1);
        panel_1.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("EVOLVEFIT");//titulo del gym
        lblNewLabel.setFont(new Font("Anton", Font.PLAIN, 38));
        lblNewLabel.setBounds(20, 15, 177, 87);
        panel_1.add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("HEALTH & FITNESS");//subtitulo de gym
        lblNewLabel_1.setFont(new Font("Anton", Font.PLAIN, 14));
        lblNewLabel_1.setBounds(20, 93, 177, 14);
        panel_1.add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("Registro de administrador");//subtitulo de gym 2 
        lblNewLabel_2.setFont(new Font("Anton", Font.PLAIN, 22));
        lblNewLabel_2.setBounds(20, 142, 319, 41);
        panel_1.add(lblNewLabel_2);
        
        JLabel lblNewLabel_3 = new JLabel("Correo electronico:");//titulo del correo
        lblNewLabel_3.setFont(new Font("Anton", Font.PLAIN, 18));
        lblNewLabel_3.setBounds(20, 209, 193, 41);
        panel_1.add(lblNewLabel_3);
        
        JTextField cuadro_txt_correo = new JTextField();//panel de texto del correo electronico
        cuadro_txt_correo.setBackground(new Color(204, 204, 204));
        cuadro_txt_correo.setFont(new Font("Anton", Font.PLAIN, 12));
        cuadro_txt_correo.setBounds(20, 261, 389, 30);
        cuadro_txt_correo.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        panel_1.add(cuadro_txt_correo);
        cuadro_txt_correo.setColumns(10);
        
        JLabel lblNewLabel_4 = new JLabel("");//cuadro de la imaegen del login 
        lblNewLabel_4.setBounds(439, 0, 615, 629);
        panel_1.add(lblNewLabel_4);
        ImageIcon portada1 =new ImageIcon("Imagenes/fondo registro.jpg");
		Image portada2= portada1.getImage();
		Image portada3=portada2.getScaledInstance(615, 629,Image.SCALE_SMOOTH);
		lblNewLabel_4 .setIcon(new ImageIcon(portada3));
		
		JLabel lblNewLabel_5 = new JLabel("Contraseña:");//titulo de la contraseña
		lblNewLabel_5.setFont(new Font("Anton", Font.PLAIN, 18));
		lblNewLabel_5.setBounds(20, 313, 193, 41);
		panel_1.add(lblNewLabel_5);
		
		JPasswordField cuadro_txt_contraseña = new JPasswordField();//panel de texto de la contraseña
		cuadro_txt_contraseña.setBackground(new Color(204, 204, 204));
		cuadro_txt_contraseña.setFont(new Font("Anton", Font.PLAIN, 12));
		cuadro_txt_contraseña.setBounds(20, 365, 389, 30);
		cuadro_txt_contraseña.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
		panel_1.add(cuadro_txt_contraseña);
		cuadro_txt_contraseña.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Confirmar contraseña:");//titulo de la contraseña
		lblNewLabel_6.setFont(new Font("Anton", Font.PLAIN, 18));
		lblNewLabel_6.setBounds(20, 417, 193, 41);
		panel_1.add(lblNewLabel_6);
		
		JPasswordField cuadro_txt_confirmar_contraseña = new JPasswordField();//panel de texto de la contraseña
		cuadro_txt_confirmar_contraseña.setBackground(new Color(204, 204, 204));
		cuadro_txt_confirmar_contraseña.setFont(new Font("Anton", Font.PLAIN, 12));
		cuadro_txt_confirmar_contraseña.setBounds(20, 469, 389, 30);
		cuadro_txt_confirmar_contraseña.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
		panel_1.add(cuadro_txt_confirmar_contraseña);
		cuadro_txt_confirmar_contraseña.setColumns(10);
		
		JButton boton_registro = new JButton("REGISTRARSE");//Boton de registro
		boton_registro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Boolean flag1 = false, flag2 = false;
				
				if (cuadro_txt_correo.getText().equals("")) {
					cuadro_txt_correo.setBorder(BorderFactory.createLineBorder(Color.RED, 3));

				}else {
					cuadro_txt_correo.setBorder(BorderFactory.createLineBorder(Color.green, 3));
					flag1 = true;
				}
				String myPassword = new String(cuadro_txt_contraseña.getPassword());
				if (myPassword.equals("")) {
					cuadro_txt_contraseña.setBorder(BorderFactory.createLineBorder(Color.RED, 3));

				} else {
					cuadro_txt_contraseña.setBorder(BorderFactory.createLineBorder(Color.green, 3));
					flag2 = true;
				}
				
				frame.dispose();
				AuthView.this.login();
			}
		});
		boton_registro.setBackground(new Color(255, 205, 17));
		boton_registro.setForeground(new Color(0, 0, 0));
		boton_registro.setFont(new Font("Anton", Font.PLAIN, 20));
		boton_registro.setBounds(80, 535, 279, 56);
		panel_1.add(boton_registro);
		
		frame.add(panel);
		frame.repaint();
		frame.revalidate();
		frame.setVisible(true);
           
    }
    public void Panel_inicio() {
		try {
            UIManager.setLookAndFeel(new FlatLightLaf());
            UIManager.put("Button.arc", 8); // Esquinas redondeadas
        } catch (Exception ex) {
            ex.printStackTrace();
        }
		initialize3();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize3() {
		JFrame frame = new JFrame();
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
		
		frame.add(panel);
		frame.repaint();
		frame.revalidate();
		frame.setVisible(true);
	}
}