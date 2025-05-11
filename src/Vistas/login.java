package Vistas;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.UIManager;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class login {

    private JFrame frame;
    private JTextField textField;
    private JTextField textField_1;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    login window = new login();
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
    public login() {
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
		panel_1.add(boton_inicio_sesion);
		
		JButton boton_registro = new JButton("REGISTRARSE");//Boton de registro
		boton_registro.setBackground(new Color(255, 205, 17));
		boton_registro.setForeground(new Color(0, 0, 0));
		boton_registro.setFont(new Font("Anton", Font.PLAIN, 20));
		boton_registro.setBounds(80, 530, 279, 56);
		panel_1.add(boton_registro);
        
        
    }
}
