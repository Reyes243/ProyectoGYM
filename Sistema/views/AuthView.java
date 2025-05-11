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
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatLightLaf;

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
        
        JTextField textField = new JTextField();//panel de texto del correo electronico
        textField.setBackground(new Color(204, 204, 204));
        textField.setFont(new Font("Anton", Font.PLAIN, 12));
        textField.setBounds(20, 271, 389, 30);
        textField.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        panel_1.add(textField);
        textField.setColumns(10);
        
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
		
		JPasswordField textField_1 = new JPasswordField();//panel de texto de la contraseña
		textField_1.setBackground(new Color(204, 204, 204));
		textField_1.setFont(new Font("Anton", Font.PLAIN, 12));
		textField_1.setBounds(20, 375, 389, 30);
		textField_1.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("INICIAR SESION");//boton de inicio de sesion
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(0, 143, 57));
		btnNewButton.setFont(new Font("Anton", Font.PLAIN, 20));
		btnNewButton.setBounds(80, 445, 279, 56);
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Boolean flag1 = false, flag2 = false;
				
				if (textField.getText().equals("")) {
					textField.setBorder(BorderFactory.createLineBorder(Color.RED, 3));

				}else {
					textField.setBorder(BorderFactory.createLineBorder(Color.green, 3));
					flag1 = true;
				}
				String myPassword = new String(textField_1.getPassword());
				if (myPassword.equals("")) {
					textField_1.setBorder(BorderFactory.createLineBorder(Color.RED, 3));

				} else {
					textField_1.setBorder(BorderFactory.createLineBorder(Color.green, 3));
					flag2 = true;
				}
				
			}
		});
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("REGISTRARSE");//Boton de registro
		btnNewButton_1.setBackground(new Color(255, 205, 17));
		btnNewButton_1.setForeground(new Color(0, 0, 0));
		btnNewButton_1.setFont(new Font("Anton", Font.PLAIN, 20));
		btnNewButton_1.setBounds(80, 530, 279, 56);
		panel_1.add(btnNewButton_1);
		
		frame.add(panel);
		frame.repaint();
		frame.revalidate();
		frame.setVisible(true);
        
        
    }
}