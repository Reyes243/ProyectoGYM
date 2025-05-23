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

import controllers.HomeController;
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

		JLabel lblNewLabel = new JLabel("EVOLVEFIT");// titulo del gym
		lblNewLabel.setFont(new Font("Anton", Font.PLAIN, 38));
		lblNewLabel.setBounds(20, 20, 200, 87);
		panel_1.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("HEALTH & FITNESS");// subtitulo de gym
		lblNewLabel_1.setFont(new Font("Anton", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(20, 93, 200, 14);
		panel_1.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Panel de administración");// subtitulo de gym 2
		lblNewLabel_2.setFont(new Font("Anton", Font.PLAIN, 22));
		lblNewLabel_2.setBounds(20, 152, 400, 41);
		panel_1.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Correo electronico:");// titulo del correo
		lblNewLabel_3.setFont(new Font("Anton", Font.PLAIN, 18));
		lblNewLabel_3.setBounds(20, 219, 193, 41);
		panel_1.add(lblNewLabel_3);

		JTextField cuadro_txt_correo = new JTextField();// panel de texto del correo electronico
		cuadro_txt_correo.setBackground(new Color(204, 204, 204));
		cuadro_txt_correo.setFont(new Font("Anton", Font.PLAIN, 12));
		cuadro_txt_correo.setBounds(20, 271, 389, 30);
		cuadro_txt_correo.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		panel_1.add(cuadro_txt_correo);
		cuadro_txt_correo.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("");// cuadro de la imaegen del login
		lblNewLabel_4.setBounds(439, 0, 615, 629);
		ImageIcon icon = new ImageIcon(getClass().getResource("/Imagenes/fondo login.png"));
		Image imagen = icon.getImage().getScaledInstance(615, 629, Image.SCALE_SMOOTH);
		lblNewLabel_4.setIcon(new ImageIcon(imagen));
		panel_1.add(lblNewLabel_4);
		

		JLabel lblNewLabel_5 = new JLabel("Contraseña:");// titulo de la contraseña
		lblNewLabel_5.setFont(new Font("Anton", Font.PLAIN, 18));
		lblNewLabel_5.setBounds(20, 323, 193, 41);
		panel_1.add(lblNewLabel_5);

		JPasswordField cuadro_txt_contraseña = new JPasswordField();// panel de texto de la contraseña
		cuadro_txt_contraseña.setBackground(new Color(204, 204, 204));
		cuadro_txt_contraseña.setFont(new Font("Anton", Font.PLAIN, 12));
		cuadro_txt_contraseña.setBounds(20, 375, 389, 30);
		cuadro_txt_contraseña.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		panel_1.add(cuadro_txt_contraseña);
		cuadro_txt_contraseña.setColumns(10);

		JButton boton_inicio_sesion = new JButton("INICIAR SESION");// boton de inicio de sesion
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
				} else {
					cuadro_txt_correo.setBorder(BorderFactory.createLineBorder(Color.green, 3));
					flag1 = true;
				}

				String password = new String(cuadro_txt_contraseña.getPassword());
				if (password.equals("")) {
					cuadro_txt_contraseña.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
				} else {
					cuadro_txt_contraseña.setBorder(BorderFactory.createLineBorder(Color.green, 3));
					flag2 = true;
				}

				if (flag1 && flag2) {
					AuthModel am = new AuthModel();
					boolean is_login = am.login(cuadro_txt_correo.getText(), password);

					if (is_login) {
						JOptionPane.showMessageDialog(null, "Bienvenido.");
						frame.dispose();
						HomeController hc = new HomeController();
						hc.Panel_inicio();
					} else {
						JOptionPane.showMessageDialog(null, "Error al acceder", "Verifique su información",
								JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		panel_1.add(boton_inicio_sesion);

		JButton boton_registro = new JButton("REGISTRARSE");// Boton de registro
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

		JLabel lblNewLabel = new JLabel("EVOLVEFIT");// titulo del gym
		lblNewLabel.setFont(new Font("Anton", Font.PLAIN, 38));
		lblNewLabel.setBounds(20, 15, 177, 87);
		panel_1.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("HEALTH & FITNESS");// subtitulo de gym
		lblNewLabel_1.setFont(new Font("Anton", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(20, 93, 177, 14);
		panel_1.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Registro de administrador");// subtitulo de gym 2
		lblNewLabel_2.setFont(new Font("Anton", Font.PLAIN, 22));
		lblNewLabel_2.setBounds(20, 142, 319, 41);
		panel_1.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Correo electronico:");// titulo del correo
		lblNewLabel_3.setFont(new Font("Anton", Font.PLAIN, 18));
		lblNewLabel_3.setBounds(20, 209, 193, 41);
		panel_1.add(lblNewLabel_3);

		JTextField cuadro_txt_correo = new JTextField();// panel de texto del correo electronico
		cuadro_txt_correo.setBackground(new Color(204, 204, 204));
		cuadro_txt_correo.setFont(new Font("Anton", Font.PLAIN, 12));
		cuadro_txt_correo.setBounds(20, 261, 389, 30);
		cuadro_txt_correo.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		panel_1.add(cuadro_txt_correo);
		cuadro_txt_correo.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("");// cuadro de la imaegen del login
		lblNewLabel_4.setBounds(439, 0, 615, 629);
		panel_1.add(lblNewLabel_4);
		ImageIcon icon1 = new ImageIcon(getClass().getResource("/Imagenes/fondo registro.jpg"));
		Image imagen1 = icon1.getImage().getScaledInstance(615, 629, Image.SCALE_SMOOTH);
		lblNewLabel_4.setIcon(new ImageIcon(imagen1));
		

		JLabel lblNewLabel_5 = new JLabel("Contraseña:");// titulo de la contraseña
		lblNewLabel_5.setFont(new Font("Anton", Font.PLAIN, 18));
		lblNewLabel_5.setBounds(20, 313, 193, 41);
		panel_1.add(lblNewLabel_5);

		JPasswordField cuadro_txt_contraseña = new JPasswordField();// panel de texto de la contraseña
		cuadro_txt_contraseña.setBackground(new Color(204, 204, 204));
		cuadro_txt_contraseña.setFont(new Font("Anton", Font.PLAIN, 12));
		cuadro_txt_contraseña.setBounds(20, 365, 389, 30);
		cuadro_txt_contraseña.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		panel_1.add(cuadro_txt_contraseña);
		cuadro_txt_contraseña.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("Confirmar contraseña:");// titulo de la contraseña
		lblNewLabel_6.setFont(new Font("Anton", Font.PLAIN, 18));
		lblNewLabel_6.setBounds(20, 417, 193, 41);
		panel_1.add(lblNewLabel_6);

		JPasswordField cuadro_txt_confirmar_contraseña = new JPasswordField();// panel de texto de confirmar contraseña
		cuadro_txt_confirmar_contraseña.setBackground(new Color(204, 204, 204));
		cuadro_txt_confirmar_contraseña.setFont(new Font("Anton", Font.PLAIN, 12));
		cuadro_txt_confirmar_contraseña.setBounds(20, 469, 389, 30);
		cuadro_txt_confirmar_contraseña.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		panel_1.add(cuadro_txt_confirmar_contraseña);
		cuadro_txt_confirmar_contraseña.setColumns(10);

		JButton boton_registro = new JButton("REGISTRARSE");// Boton de registro
		boton_registro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email = cuadro_txt_correo.getText();
				String password = new String(cuadro_txt_contraseña.getPassword());
				String confirmPassword = new String(cuadro_txt_confirmar_contraseña.getPassword());

				if (email.isEmpty() || password.isEmpty()) {
					JOptionPane.showMessageDialog(frame, "Todos los campos son obligatorios.", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				if (!password.equals(confirmPassword)) {
					JOptionPane.showMessageDialog(frame, "Las contraseñas no coinciden.", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				AuthModel modelo = new AuthModel();
				boolean registroExitoso = modelo.registrarAdmin(email, password);

				if (registroExitoso) {
					JOptionPane.showMessageDialog(frame, "¡Registro exitoso!", "Éxito",
							JOptionPane.INFORMATION_MESSAGE);
					frame.dispose();
					AuthView av = new AuthView();
					av.login();

				} else {
					JOptionPane.showMessageDialog(frame, "Error al guardar los datos.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}

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

}