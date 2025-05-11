package Vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatLightLaf;

public class registro {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					registro window = new registro();
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
	public registro() {
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
		boton_registro.setBackground(new Color(255, 205, 17));
		boton_registro.setForeground(new Color(0, 0, 0));
		boton_registro.setFont(new Font("Anton", Font.PLAIN, 20));
		boton_registro.setBounds(80, 535, 279, 56);
		panel_1.add(boton_registro);
        
        
    }
}
