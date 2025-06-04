package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import com.formdev.flatlaf.FlatLightLaf;

import controllers.HomeController;
import controllers.UsersController;
import models.ConectionModel;
import models.UsersModel;

public class UsersView {
	private boolean cambiosRealizados = false;
	private Font antonFont;

	private void cargarFuentePersonalizada() {
		try {
			antonFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/fonts/Anton-Regular.ttf"))
					.deriveFont(Font.PLAIN, 18f);
			// Registra la fuente en el sistema gráfico de Java
			java.awt.GraphicsEnvironment ge = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(antonFont);
		} catch (Exception e) {
			e.printStackTrace();
			antonFont = new Font("SansSerif", Font.PLAIN, 18); // Fuente por defecto si falla
		}
	}

	public UsersView() {

	}

	public void Informacion_de_cliente(int idCliente) {
		try {
			UIManager.setLookAndFeel(new FlatLightLaf());
			UIManager.put("Button.arc", 8); // Esquinas redondeadas
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		cargarFuentePersonalizada();

		UsersModel um = new UsersModel();
		Map<String, String> datosCliente = um.obtenerDatosBasicosCliente(idCliente);

		if (datosCliente.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Cliente no encontrado. ID inválido: " + idCliente, "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
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
		panel_1.setBounds(0, 0, 1084, 75);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(10, 11, 53, 53);
		ImageIcon icon = new ImageIcon(getClass().getResource("/Imagenes/logo sin letras.png"));
		Image imagen = icon.getImage().getScaledInstance(53, 53, Image.SCALE_SMOOTH);
		lblNewLabel_1.setIcon(new ImageIcon(imagen));
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

		JLabel lblNewLabel = new JLabel("CONSULTA DE INFORMACION");// titulo de inicio
		lblNewLabel.setFont(new Font("Anton", Font.PLAIN, 26));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(60, 11, 309, 28);
		panel_3.add(lblNewLabel);

		JLabel Imagen_de_usuario = new JLabel("");
		Imagen_de_usuario.setBounds(15, 90, 200, 300);
		Imagen_de_usuario.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		Imagen_de_usuario.setHorizontalAlignment(SwingConstants.CENTER);
		Imagen_de_usuario.setVerticalAlignment(SwingConstants.CENTER);
		ImageIcon icon1 = new ImageIcon(getClass().getResource("/Imagenes/GYMBRO.jpg"));
		Image imagen1 = icon1.getImage().getScaledInstance(200, 300, Image.SCALE_SMOOTH);
		Imagen_de_usuario.setIcon(new ImageIcon(imagen1));
		panel_2.add(Imagen_de_usuario);

		JLabel lblNewLabel_5 = new JLabel("ID Cliente: ");
		lblNewLabel_5.setFont(new Font("Anton", Font.PLAIN, 20));
		lblNewLabel_5.setBounds(235, 90, 85, 32);
		panel_2.add(lblNewLabel_5);

		JLabel lblNewLabel_11 = new JLabel("Nombre:");
		lblNewLabel_11.setFont(new Font("Anton", Font.PLAIN, 16));
		lblNewLabel_11.setBounds(235, 160, 60, 22);
		panel_2.add(lblNewLabel_11);

		JLabel lblNewLabel_9 = new JLabel("Correo electronico:");
		lblNewLabel_9.setFont(new Font("Anton", Font.PLAIN, 16));
		lblNewLabel_9.setBounds(235, 260, 132, 22);
		panel_2.add(lblNewLabel_9);

		JLabel lblNewLabel_8 = new JLabel("Teléfono:");
		lblNewLabel_8.setFont(new Font("Anton", Font.PLAIN, 16));
		lblNewLabel_8.setBounds(235, 310, 67, 22);
		panel_2.add(lblNewLabel_8);

		JLabel lblNewLabel_7 = new JLabel("Clases inscritas:");
		lblNewLabel_7.setFont(new Font("Anton", Font.PLAIN, 16));
		lblNewLabel_7.setBounds(235, 362, 117, 28);
		panel_2.add(lblNewLabel_7);

		JLabel lblNewLabel_12 = new JLabel("Siguiente pago:");
		lblNewLabel_12.setFont(new Font("Anton", Font.PLAIN, 16));
		lblNewLabel_12.setBounds(575, 260, 100, 22);
		panel_2.add(lblNewLabel_12);

		JLabel lblNewLabel_13 = new JLabel("Pago anterior:");
		lblNewLabel_13.setFont(new Font("Anton", Font.PLAIN, 16));
		lblNewLabel_13.setBounds(575, 210, 93, 22);
		panel_2.add(lblNewLabel_13);

		JLabel lblNewLabel_14 = new JLabel("Tarifa:");
		lblNewLabel_14.setFont(new Font("Anton", Font.PLAIN, 16));
		lblNewLabel_14.setBounds(575, 160, 70, 22);
		panel_2.add(lblNewLabel_14);

		// informacion del usuario
		// ////////////////////////////////////////////////////////////
		JLabel Info_ID = new JLabel("");
		Info_ID.setText(datosCliente.get("id"));
		Info_ID.setFont(new Font("Anton", Font.PLAIN, 20));
		Info_ID.setBounds(321, 92, 46, 28);
		panel_2.add(Info_ID);

		JLabel Info_nombre = new JLabel("");
		Info_nombre.setText(datosCliente.get("nombre"));
		Info_nombre.setFont(new Font("Anton", Font.PLAIN, 16));
		Info_nombre.setBounds(296, 160, 150, 21);
		panel_2.add(Info_nombre);

		JLabel Info_primer_apellido = new JLabel("");
		Info_primer_apellido.setText(datosCliente.get("primer_apellido"));
		Info_primer_apellido.setFont(new Font("Anton", Font.PLAIN, 16));
		Info_primer_apellido.setBounds(342, 210, 200, 22);
		panel_2.add(Info_primer_apellido);

		JLabel lblNewLabel_10 = new JLabel("Primer apellido:");
		lblNewLabel_10.setFont(new Font("Anton", Font.PLAIN, 16));
		lblNewLabel_10.setBounds(235, 210, 147, 22);
		panel_2.add(lblNewLabel_10);

		JLabel Info_correo = new JLabel("");
		Info_correo.setText(datosCliente.get("correo"));
		Info_correo.setFont(new Font("Anton", Font.PLAIN, 16));
		Info_correo.setBounds(377, 260, 152, 22);
		panel_2.add(Info_correo);

		JLabel Info_telefono = new JLabel("");
		Info_telefono.setText(datosCliente.getOrDefault("telefono", "N/A"));
		Info_telefono.setFont(new Font("Anton", Font.PLAIN, 16));
		Info_telefono.setBounds(296, 310, 152, 22);
		panel_2.add(Info_telefono);

		JLabel Info_clase = new JLabel("TECNICA EN MAQUINAS");
		Info_clase.setFont(new Font("Anton", Font.PLAIN, 16));
		Info_clase.setBounds(362, 362, 167, 28);
		panel_2.add(Info_clase);

		JLabel Info_plan = new JLabel("");
		Info_plan.setText(datosCliente.getOrDefault("tarifa", "Ninguna"));
		Info_plan.setFont(new Font("Anton", Font.PLAIN, 16));
		Info_plan.setBounds(617, 160, 200, 22);
		panel_2.add(Info_plan);

		JLabel Info_pago_ant = new JLabel("$600");
		Info_pago_ant.setFont(new Font("Anton", Font.PLAIN, 16));
		Info_pago_ant.setBounds(678, 210, 152, 22);
		panel_2.add(Info_pago_ant);

		JLabel Info_pago_sig = new JLabel("$600");
		Info_pago_sig.setFont(new Font("Anton", Font.PLAIN, 16));
		Info_pago_sig.setBounds(685, 260, 145, 22);
		panel_2.add(Info_pago_sig);

		// botones de accion para el cliente
		// ///////////////////////////////////////////////////////////////////////
		JButton boton_descraga_credencial = new JButton("Descargar credencial");
		boton_descraga_credencial.setBackground(new Color(255, 205, 17));
		boton_descraga_credencial.setFont(new Font("Anton", Font.PLAIN, 12));
		boton_descraga_credencial.setBounds(736, 501, 160, 32);
		boton_descraga_credencial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				UsersController uc = new UsersController();
				uc.Credencial_usuario(idCliente);

			}
		});
		panel_2.add(boton_descraga_credencial);

		JButton boton_descargar_info = new JButton("Descargar");
		boton_descargar_info.setBackground(new Color(255, 205, 17));
		boton_descargar_info.setFont(new Font("Anton", Font.PLAIN, 12));
		boton_descargar_info.setBounds(626, 501, 100, 32);
		boton_descargar_info.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UsersController controller = new UsersController();
				controller.generarPDFCliente(idCliente);

			}
		});
		panel_2.add(boton_descargar_info);

		JButton boton_editar_info = new JButton("Editar ");
		boton_editar_info.setBackground(new Color(255, 205, 17));
		boton_editar_info.setFont(new Font("Anton", Font.PLAIN, 12));
		boton_editar_info.setBounds(531, 501, 85, 32);
		boton_editar_info.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				UsersController controller = new UsersController();
				controller.Edicion_de_informacion_de_cliente(idCliente);

			}
		});
		panel_2.add(boton_editar_info);

		JButton boton_historial_asistencias = new JButton("Historial de asistencia");
		boton_historial_asistencias.setBackground(new Color(255, 205, 17));
		boton_historial_asistencias.setFont(new Font("Anton", Font.PLAIN, 12));
		boton_historial_asistencias.setBounds(362, 501, 160, 32);
		boton_historial_asistencias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				UsersController controller = new UsersController();
				controller.HIstorial_de_asistencias(idCliente);
			}
		});
		panel_2.add(boton_historial_asistencias);

		JButton boton_historial_pagos = new JButton("Historial de pagos");
		boton_historial_pagos.setBackground(new Color(255, 205, 17));
		boton_historial_pagos.setFont(new Font("Anton", Font.PLAIN, 12));
		boton_historial_pagos.setBounds(192, 501, 160, 32);
		boton_historial_pagos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				UsersController controller = new UsersController();
				controller.Historial_de_pagos(idCliente);
				;

			}
		});
		panel_2.add(boton_historial_pagos);

		JButton boton_regresar = new JButton("Regresar");
		boton_regresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Clientes();
			}
		});
		boton_regresar.setFont(new Font("Anton", Font.PLAIN, 12));
		boton_regresar.setBackground(new Color(255, 205, 17));
		boton_regresar.setBounds(22, 501, 160, 32);
		panel_2.add(boton_regresar);

		// Botones laterales
		// //////////////////////////////////////////////////////////////////////////////////////////////
		JButton boton_INICIO = new JButton("INICIO");// boton de inicio
		boton_INICIO.setBackground(new Color(255, 205, 17));
		boton_INICIO.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_INICIO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Panel_inicio();
			}
		});
		boton_INICIO.setBounds(10, 86, 136, 71);
		panel.add(boton_INICIO);

		JButton boton_CLIENTES = new JButton("CLIENTES");// boton de clientes
		boton_CLIENTES.setBackground(new Color(255, 255, 255));
		boton_CLIENTES.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CLIENTES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Clientes();
			}
		});
		boton_CLIENTES.setBounds(10, 168, 136, 71);
		panel.add(boton_CLIENTES);

		JButton boton_TARIFAS = new JButton("TARIFAS");// boton de tarifas
		boton_TARIFAS.setBackground(new Color(255, 205, 17));
		boton_TARIFAS.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_TARIFAS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Tarifas();
			}
		});
		boton_TARIFAS.setBounds(10, 250, 136, 71);
		panel.add(boton_TARIFAS);

		JButton boton_INSTRUCTORES = new JButton("INSTRUCTORES");// boton de instructores
		boton_INSTRUCTORES.setBackground(new Color(255, 205, 17));
		boton_INSTRUCTORES.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_INSTRUCTORES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Instructores();
			}
		});
		boton_INSTRUCTORES.setBounds(10, 332, 136, 71);
		panel.add(boton_INSTRUCTORES);

		JButton boton_CLASES = new JButton("CLASES");// boton de clases
		boton_CLASES.setBackground(new Color(255, 205, 17));
		boton_CLASES.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CLASES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Clases();
			}
		});
		boton_CLASES.setBounds(10, 414, 136, 71);
		panel.add(boton_CLASES);

		JButton boton_CHECADOR = new JButton("CHECADOR");// boton de checador
		boton_CHECADOR.setBackground(new Color(255, 205, 17));
		boton_CHECADOR.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CHECADOR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Panel_checador();
			}
		});
		boton_CHECADOR.setBounds(10, 496, 136, 71);
		panel.add(boton_CHECADOR);

		JButton boton_CERRAR_SESION = new JButton("CERRAR SESION");
		boton_CERRAR_SESION.setBackground(new Color(255, 205, 17));
		boton_CERRAR_SESION.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CERRAR_SESION.addActionListener(e -> {
			JDialog dialog = new JDialog(frame, "Cerrar sesión", true);
			dialog.setSize(400, 220);
			dialog.setLocationRelativeTo(frame);
			dialog.setUndecorated(true);
			dialog.setLayout(null);

			// Panel principal - AHORA CON TAMAÑO COMPLETO
			JPanel Cerrar_sesion = new JPanel();
			Cerrar_sesion.setBackground(new Color(255, 255, 255));
			Cerrar_sesion.setBounds(0, 0, 400, 220);
			Cerrar_sesion.setLayout(null);
			dialog.add(Cerrar_sesion);

			// Panel superior azul
			JPanel panel_complemento = new JPanel();
			panel_complemento.setBackground(new Color(81, 151, 255));
			panel_complemento.setBounds(0, 0, 400, 33);
			Cerrar_sesion.add(panel_complemento);

			// Etiqueta con la pregunta
			JLabel pregunta_de_confirmacion = new JLabel(
					"<html><div style='text-align: center;'>Está a punto de cerrar sesión<br>¿Desea continuar?</div></html>");
			pregunta_de_confirmacion.setFont(new Font("Anton", Font.PLAIN, 16));
			pregunta_de_confirmacion.setBounds(90, 44, 310, 60);
			Cerrar_sesion.add(pregunta_de_confirmacion);

			// Botón Cancelar
			JButton boton_cancelar = new JButton("Cancelar");
			boton_cancelar.setForeground(Color.WHITE);
			boton_cancelar.setFont(new Font("Anton", Font.PLAIN, 14));
			boton_cancelar.setBackground(Color.RED);
			boton_cancelar.setBounds(50, 140, 120, 35);
			Cerrar_sesion.add(boton_cancelar);

			// Botón Aceptar
			JButton boton_aceptar = new JButton("Aceptar");
			boton_aceptar.setBackground(new Color(0, 206, 82));
			boton_aceptar.setForeground(Color.WHITE);
			boton_aceptar.setFont(new Font("Anton", Font.PLAIN, 14));
			boton_aceptar.setBounds(230, 140, 120, 35);
			Cerrar_sesion.add(boton_aceptar);

			// Acciones de los botones
			boton_cancelar.addActionListener(ev -> dialog.dispose());
			boton_aceptar.addActionListener(ev -> {
				dialog.dispose();
				closeSession(frame);
			});

			dialog.setVisible(true);
		});
		boton_CERRAR_SESION.setBounds(10, 579, 136, 71);
		panel.add(boton_CERRAR_SESION);

		frame.add(panel);
		frame.repaint();
		frame.revalidate();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	private void closeSession(JFrame frame) {
		new ConectionModel().close();

		// Crear diálogo de despedida
		JDialog goodbyeDialog = new JDialog(frame, "Sesión cerrada", true);
		goodbyeDialog.setSize(400, 220);
		goodbyeDialog.setLocationRelativeTo(frame);
		goodbyeDialog.setUndecorated(true);
		goodbyeDialog.setLayout(null);

		JPanel goodbyePanel = new JPanel(null);
		goodbyePanel.setBackground(Color.WHITE);
		goodbyePanel.setBounds(0, 0, 400, 220);

		// Panel superior azul
		JPanel headerPanel = new JPanel();
		headerPanel.setBackground(new Color(81, 151, 255));
		headerPanel.setBounds(0, 0, 400, 33);

		// Mensaje de despedida
		JLabel goodbyeMessage = new JLabel(
				"<html><div style='text-align: center;'>Sesión cerrada correctamente.<br>Que tenga un buen día :)</div></html>");
		goodbyeMessage.setFont(new Font("Anton", Font.PLAIN, 16));
		goodbyeMessage.setBounds(80, 50, 300, 60);

		// Botón Aceptar
		JButton btnAccept = new JButton("Aceptar");
		btnAccept.setBackground(new Color(0, 206, 82));
		btnAccept.setForeground(Color.WHITE);
		btnAccept.setFont(new Font("Anton", Font.PLAIN, 14));
		btnAccept.setBounds(150, 140, 102, 33);

		// Acción del botón
		btnAccept.addActionListener(e -> {
			goodbyeDialog.dispose();
			frame.dispose();
			SwingUtilities.invokeLater(() -> {
				AuthView av = new AuthView();
				av.login();
			});
		});

		// Añadir componentes
		goodbyePanel.add(headerPanel);
		goodbyePanel.add(goodbyeMessage);
		goodbyePanel.add(btnAccept);

		goodbyeDialog.add(goodbyePanel);
		goodbyeDialog.setVisible(true);
	}

	public void Edicion_de_informacion_de_cliente(int idcliente) {
		try {
			UIManager.setLookAndFeel(new FlatLightLaf());
			UIManager.put("Button.arc", 8);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		UsersModel model = new UsersModel();
		Map<String, String> datosCliente = model.obtenerDatosBasicosCliente(idcliente);

		JFrame frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(0, 0, 1100, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Map<String, String> valoresOriginales = new HashMap<>();

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
		ImageIcon icon = new ImageIcon(getClass().getResource("/Imagenes/logo sin letras.png"));
		Image imagen = icon.getImage().getScaledInstance(53, 53, Image.SCALE_SMOOTH);
		lblNewLabel_1.setIcon(new ImageIcon(imagen));
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

		JLabel lblNewLabel = new JLabel("EDITAR INFORMACIÓN");// titulo de inicio
		lblNewLabel.setFont(new Font("Anton", Font.PLAIN, 26));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(60, 11, 309, 28);

		panel_3.add(lblNewLabel);

		JLabel Imagen_de_usuario = new JLabel("");
		Imagen_de_usuario.setBounds(15, 115, 132, 180);
		Imagen_de_usuario.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		Imagen_de_usuario.setHorizontalAlignment(SwingConstants.CENTER);
		Imagen_de_usuario.setVerticalAlignment(SwingConstants.CENTER);
		ImageIcon icon1 = new ImageIcon(getClass().getResource("/Imagenes/GYMBRO.jpg"));
		Image imagen1 = icon1.getImage().getScaledInstance(132, 180, Image.SCALE_SMOOTH);
		Imagen_de_usuario.setIcon(new ImageIcon(imagen1));
		panel_2.add(Imagen_de_usuario);

		JLabel lblNewLabel_11 = new JLabel("Nombre:");
		lblNewLabel_11.setFont(new Font("Anton", Font.PLAIN, 16));
		lblNewLabel_11.setBounds(230, 115, 60, 22);
		panel_2.add(lblNewLabel_11);

		JLabel lblNewLabel_10 = new JLabel("Primer apellido:");
		lblNewLabel_10.setFont(new Font("Anton", Font.PLAIN, 16));
		lblNewLabel_10.setBounds(230, 170, 104, 22);
		panel_2.add(lblNewLabel_10);

		JLabel lblNewLabel_9 = new JLabel("Segundo apellido:");
		lblNewLabel_9.setFont(new Font("Anton", Font.PLAIN, 16));
		lblNewLabel_9.setBounds(230, 225, 117, 22);
		panel_2.add(lblNewLabel_9);

		JLabel lblNewLabel_8 = new JLabel("Teléfono:");
		lblNewLabel_8.setFont(new Font("Anton", Font.PLAIN, 16));
		lblNewLabel_8.setBounds(230, 280, 117, 22);
		panel_2.add(lblNewLabel_8);

		JLabel lblNewLabel_7 = new JLabel("Correo electronico:");
		lblNewLabel_7.setFont(new Font("Anton", Font.PLAIN, 16));
		lblNewLabel_7.setBounds(499, 115, 132, 28);
		panel_2.add(lblNewLabel_7);

	/*	JLabel lblNewLabel_6 = new JLabel("Tarifa:");
		lblNewLabel_6.setFont(new Font("Anton", Font.PLAIN, 16));
		lblNewLabel_6.setBounds(499, 280, 60, 22);
		panel_2.add(lblNewLabel_6);*/

		JLabel lblNewLabel_12 = new JLabel("Confirmar contraseña:");
		lblNewLabel_12.setFont(new Font("Anton", Font.PLAIN, 16));
		lblNewLabel_12.setBounds(499, 225, 152, 22);
		panel_2.add(lblNewLabel_12);

		JLabel lblNewLabel_13 = new JLabel("Contraseña: ");
		lblNewLabel_13.setFont(new Font("Anton", Font.PLAIN, 16));
		lblNewLabel_13.setBounds(499, 170, 85, 22);
		panel_2.add(lblNewLabel_13);

		// informacion del usuario
		// ////////////////////////////////////////////////////////////
		JTextField Info_nombre = new JTextField(datosCliente.getOrDefault("nombre", ""));
		valoresOriginales.put("nombre", Info_nombre.getText());
		Info_nombre.setBackground(new Color(204, 204, 204));
		Info_nombre.setFont(new Font("Anton", Font.PLAIN, 16));
		Info_nombre.setBounds(357, 115, 132, 22);
		panel_2.add(Info_nombre);

		JTextField Info_primer_apellido = new JTextField(datosCliente.getOrDefault("primer_apellido", ""));
		valoresOriginales.put("primer_apellido", Info_primer_apellido.getText());
		Info_primer_apellido.setBackground(new Color(204, 204, 204));
		Info_primer_apellido.setFont(new Font("Anton", Font.PLAIN, 16));
		Info_primer_apellido.setBounds(357, 170, 132, 22);
		panel_2.add(Info_primer_apellido);

		JTextField Info_segundo_apellido = new JTextField(datosCliente.getOrDefault("segundo_apellido", ""));
		valoresOriginales.put("segundo_apellido", Info_segundo_apellido.getText());
		Info_segundo_apellido.setBackground(new Color(204, 204, 204));
		Info_segundo_apellido.setFont(new Font("Anton", Font.PLAIN, 16));
		Info_segundo_apellido.setBounds(357, 225, 132, 22);
		panel_2.add(Info_segundo_apellido);

		JTextField Info_telefono = new JTextField(datosCliente.getOrDefault("telefono", ""));
		valoresOriginales.put("telefono", Info_telefono.getText());
		Info_telefono.setBackground(new Color(204, 204, 204));
		Info_telefono.setFont(new Font("Anton", Font.PLAIN, 16));
		Info_telefono.setBounds(357, 280, 132, 22);
		panel_2.add(Info_telefono);

		JTextField Info_correo = new JTextField(datosCliente.getOrDefault("correo", ""));
		valoresOriginales.put("correo", Info_correo.getText());
		Info_correo.setBackground(new Color(204, 204, 204));
		Info_correo.setFont(new Font("Anton", Font.PLAIN, 16));
		Info_correo.setBounds(649, 115, 200, 22);
		panel_2.add(Info_correo);

		JPasswordField Info_contra = new JPasswordField("");
		Info_contra.setBackground(new Color(204, 204, 204));
		Info_contra.setFont(new Font("Anton", Font.PLAIN, 16));
		Info_contra.setBounds(649, 170, 200, 22);
		panel_2.add(Info_contra);

		JPasswordField Info_confirmar_contra = new JPasswordField("");
		Info_confirmar_contra.setBackground(new Color(204, 204, 204));
		Info_confirmar_contra.setFont(new Font("Anton", Font.PLAIN, 16));
		Info_confirmar_contra.setBounds(649, 225, 200, 22);
		panel_2.add(Info_confirmar_contra);

		DocumentListener documentListener = new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				cambiosRealizados = true;
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				cambiosRealizados = true;
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				cambiosRealizados = true;
			}
		};
		Info_nombre.getDocument().addDocumentListener(documentListener);
		Info_primer_apellido.getDocument().addDocumentListener(documentListener);
		Info_segundo_apellido.getDocument().addDocumentListener(documentListener);
		Info_telefono.getDocument().addDocumentListener(documentListener);
		Info_correo.getDocument().addDocumentListener(documentListener);
		Info_contra.getDocument().addDocumentListener(documentListener);
		Info_confirmar_contra.getDocument().addDocumentListener(documentListener);

		// botones de accion para el cliente
		// ///////////////////////////////////////////////////////////////////////
		JButton boton_guardar_cambios = new JButton("Guardar cambios");
		boton_guardar_cambios.setForeground(new Color(255, 255, 255));
		boton_guardar_cambios.setBackground(new Color(0, 206, 82));
		boton_guardar_cambios.setFont(new Font("Anton", Font.PLAIN, 14));
		boton_guardar_cambios.setBounds(727, 483, 160, 50);
		boton_guardar_cambios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!cambiosRealizados) {
					// Alerta si no se han hecho cambios
					JDialog alerta = new JDialog(frame, "Información", true);
					alerta.setUndecorated(true);
					alerta.setSize(400, 180);
					alerta.setLocationRelativeTo(frame);
					alerta.setLayout(null);

					JPanel fondo = new JPanel();
					fondo.setBackground(Color.WHITE);
					fondo.setBounds(0, 0, 400, 180);
					fondo.setLayout(null);
					alerta.add(fondo);

					JPanel encabezado = new JPanel();
					encabezado.setBackground(new Color(81, 151, 255));
					encabezado.setBounds(0, 0, 400, 33);
					fondo.add(encabezado);

					JLabel mensaje = new JLabel(
							"<html><div style='text-align: center;'>No se han realizado cambios para guardar.</div></html>",
							SwingConstants.CENTER);
					mensaje.setFont(new Font("Anton", Font.PLAIN, 16));
					mensaje.setBounds(30, 50, 340, 50);
					fondo.add(mensaje);

					JButton aceptar = new JButton("Aceptar");
					aceptar.setBackground(new Color(0, 206, 82));
					aceptar.setForeground(Color.WHITE);
					aceptar.setFont(new Font("Anton", Font.PLAIN, 14));
					aceptar.setBounds(148, 110, 102, 33);
					aceptar.addActionListener(a -> alerta.dispose());
					fondo.add(aceptar);

					alerta.setVisible(true);
					return;
				}

				// Validación de campos obligatorios
				if (Info_nombre.getText().trim().isEmpty() || Info_primer_apellido.getText().trim().isEmpty()
						|| Info_telefono.getText().trim().isEmpty() || Info_correo.getText().trim().isEmpty()) {
					// Alerta si falta completar campos obligatorios
					JDialog alertaCampos = new JDialog(frame, "Campos obligatorios", true);
					alertaCampos.setUndecorated(true);
					alertaCampos.setSize(400, 180);
					alertaCampos.setLocationRelativeTo(frame);
					alertaCampos.setLayout(null);

					JPanel fondo = new JPanel();
					fondo.setBackground(Color.WHITE);
					fondo.setBounds(0, 0, 400, 180);
					fondo.setLayout(null);
					alertaCampos.add(fondo);

					JPanel encabezado = new JPanel();
					encabezado.setBackground(new Color(81, 151, 255));
					encabezado.setBounds(0, 0, 400, 33);
					fondo.add(encabezado);

					JLabel mensaje = new JLabel(
							"<html><div style='text-align: center;'>Por favor complete todos los campos obligatorios.</div></html>",
							SwingConstants.CENTER);
					mensaje.setFont(new Font("Anton", Font.PLAIN, 16));
					mensaje.setBounds(30, 50, 340, 50);
					fondo.add(mensaje);

					JButton aceptar = new JButton("Aceptar");
					aceptar.setBackground(new Color(0, 206, 82));
					aceptar.setForeground(Color.WHITE);
					aceptar.setFont(new Font("Anton", Font.PLAIN, 14));
					aceptar.setBounds(148, 110, 102, 33);
					aceptar.addActionListener(a -> alertaCampos.dispose());
					fondo.add(aceptar);

					alertaCampos.setVisible(true);
					return;
				}

				// Validación del teléfono (solo números)
				String telefono = Info_telefono.getText().trim();
				if (!telefono.matches("\\d+")) {
					JDialog alertaTelefono = new JDialog(frame, "Error de teléfono", true);
					alertaTelefono.setUndecorated(true);
					alertaTelefono.setSize(400, 180);
					alertaTelefono.setLocationRelativeTo(frame);
					alertaTelefono.setLayout(null);

					JPanel fondo = new JPanel();
					fondo.setBackground(Color.WHITE);
					fondo.setBounds(0, 0, 400, 180);
					fondo.setLayout(null);
					alertaTelefono.add(fondo);

					JPanel encabezado = new JPanel();
					encabezado.setBackground(new Color(81, 151, 255));
					encabezado.setBounds(0, 0, 400, 33);
					fondo.add(encabezado);

					JLabel mensaje = new JLabel(
							"<html><div style='text-align: center;'>El teléfono debe contener solo números.</div></html>",
							SwingConstants.CENTER);
					mensaje.setFont(new Font("Anton", Font.PLAIN, 16));
					mensaje.setBounds(30, 50, 340, 50);
					fondo.add(mensaje);

					JButton aceptar = new JButton("Aceptar");
					aceptar.setBackground(new Color(0, 206, 82));
					aceptar.setForeground(Color.WHITE);
					aceptar.setFont(new Font("Anton", Font.PLAIN, 14));
					aceptar.setBounds(148, 110, 102, 33);
					aceptar.addActionListener(a -> alertaTelefono.dispose());
					fondo.add(aceptar);

					alertaTelefono.setVisible(true);
					return;
				}

				// Validación de nombres y apellidos (solo letras y espacios)
				if (!Info_nombre.getText().matches("[a-zA-Z\\s]+")
						|| !Info_primer_apellido.getText().matches("[a-zA-Z\\s]+")
						|| !Info_segundo_apellido.getText().matches("[a-zA-Z\\s]+")) {
					JDialog alertaNombre = new JDialog(frame, "Error de nombre", true);
					alertaNombre.setUndecorated(true);
					alertaNombre.setSize(400, 180);
					alertaNombre.setLocationRelativeTo(frame);
					alertaNombre.setLayout(null);

					JPanel fondo = new JPanel();
					fondo.setBackground(Color.WHITE);
					fondo.setBounds(0, 0, 400, 180);
					fondo.setLayout(null);
					alertaNombre.add(fondo);

					JPanel encabezado = new JPanel();
					encabezado.setBackground(new Color(81, 151, 255));
					encabezado.setBounds(0, 0, 400, 33);
					fondo.add(encabezado);

					JLabel mensaje = new JLabel(
							"<html><div style='text-align: center;'>Los nombres y apellidos deben contener solo letras y espacios.</div></html>",
							SwingConstants.CENTER);
					mensaje.setFont(new Font("Anton", Font.PLAIN, 16));
					mensaje.setBounds(30, 50, 340, 50);
					fondo.add(mensaje);

					JButton aceptar = new JButton("Aceptar");
					aceptar.setBackground(new Color(0, 206, 82));
					aceptar.setForeground(Color.WHITE);
					aceptar.setFont(new Font("Anton", Font.PLAIN, 14));
					aceptar.setBounds(148, 110, 102, 33);
					aceptar.addActionListener(a -> alertaNombre.dispose());
					fondo.add(aceptar);

					alertaNombre.setVisible(true);
					return;
				}

				// Validación de correo (solo gmail y hotmail)
				String correo = Info_correo.getText().trim();
				if (!correo.matches("^[a-zA-Z0-9._%+-]+@(gmail\\.com|hotmail\\.com)$")) {
					JDialog alertaCorreo = new JDialog(frame, "Error de correo", true);
					alertaCorreo.setUndecorated(true);
					alertaCorreo.setSize(400, 180);
					alertaCorreo.setLocationRelativeTo(frame);
					alertaCorreo.setLayout(null);

					JPanel fondo = new JPanel();
					fondo.setBackground(Color.WHITE);
					fondo.setBounds(0, 0, 400, 180);
					fondo.setLayout(null);
					alertaCorreo.add(fondo);

					JPanel encabezado = new JPanel();
					encabezado.setBackground(new Color(81, 151, 255));
					encabezado.setBounds(0, 0, 400, 33);
					fondo.add(encabezado);

					JLabel mensaje = new JLabel(
							"<html><div style='text-align: center;'>El correo debe ser @gmail.com o @hotmail.com.</div></html>",
							SwingConstants.CENTER);
					mensaje.setFont(new Font("Anton", Font.PLAIN, 16));
					mensaje.setBounds(30, 50, 340, 50);
					fondo.add(mensaje);

					JButton aceptar = new JButton("Aceptar");
					aceptar.setBackground(new Color(0, 206, 82));
					aceptar.setForeground(Color.WHITE);
					aceptar.setFont(new Font("Anton", Font.PLAIN, 14));
					aceptar.setBounds(148, 110, 102, 33);
					aceptar.addActionListener(a -> alertaCorreo.dispose());
					fondo.add(aceptar);

					alertaCorreo.setVisible(true);
					return;
				}

				String nuevaContra = new String(Info_contra.getPassword());
				String confirmarContra = new String(Info_confirmar_contra.getPassword());

				if (!nuevaContra.isEmpty() && !nuevaContra.equals(confirmarContra)) {
					JDialog error = new JDialog(frame, "Error", true);
					error.setUndecorated(true);
					error.setSize(400, 180);
					error.setLocationRelativeTo(frame);
					error.setLayout(null);

					JPanel fondo = new JPanel();
					fondo.setBackground(Color.WHITE);
					fondo.setBounds(0, 0, 400, 180);
					fondo.setLayout(null);
					error.add(fondo);

					JPanel encabezado = new JPanel();
					encabezado.setBackground(new Color(81, 151, 255));
					encabezado.setBounds(0, 0, 400, 33);
					fondo.add(encabezado);

					JLabel mensaje = new JLabel(
							"<html><div style='text-align: center;'>Las contraseñas no coinciden.</div></html>",
							SwingConstants.CENTER);
					mensaje.setFont(new Font("Anton", Font.PLAIN, 16));
					mensaje.setBounds(30, 50, 340, 50);
					fondo.add(mensaje);

					JButton aceptar = new JButton("Aceptar");
					aceptar.setBackground(new Color(0, 206, 82));
					aceptar.setForeground(Color.WHITE);
					aceptar.setFont(new Font("Anton", Font.PLAIN, 14));
					aceptar.setBounds(148, 110, 102, 33);
					aceptar.addActionListener(a -> error.dispose());
					fondo.add(aceptar);

					error.setVisible(true);
					return;
				}

				// Confirmación personalizada (Sí / No)
				JDialog confirmacion = new JDialog(frame, "Confirmar cambios", true);
				confirmacion.setUndecorated(true);
				confirmacion.setSize(400, 180);
				confirmacion.setLocationRelativeTo(frame);
				confirmacion.setLayout(null);

				JPanel fondo = new JPanel();
				fondo.setBackground(Color.WHITE);
				fondo.setBounds(0, 0, 400, 180);
				fondo.setLayout(null);
				confirmacion.add(fondo);

				JPanel encabezado = new JPanel();
				encabezado.setBackground(new Color(81, 151, 255));
				encabezado.setBounds(0, 0, 400, 33);
				fondo.add(encabezado);

				JLabel mensaje = new JLabel(
						"<html><div style='text-align: center;'>¿Está seguro que desea guardar los cambios?</div></html>",
						SwingConstants.CENTER);
				mensaje.setFont(new Font("Anton", Font.PLAIN, 16));
				mensaje.setBounds(30, 50, 340, 50);
				fondo.add(mensaje);

				JButton botonSi = new JButton("Sí");
				botonSi.setBackground(new Color(0, 206, 82));
				botonSi.setForeground(Color.WHITE);
				botonSi.setFont(new Font("Anton", Font.PLAIN, 14));
				botonSi.setBounds(90, 110, 90, 33);

				JButton botonNo = new JButton("No");
				botonNo.setBackground(new Color(255, 70, 70));
				botonNo.setForeground(Color.WHITE);
				botonNo.setFont(new Font("Anton", Font.PLAIN, 14));
				botonNo.setBounds(220, 110, 90, 33);

				fondo.add(botonSi);
				fondo.add(botonNo);

				botonSi.addActionListener(a -> {
					confirmacion.dispose();
					boolean exito = actualizarCliente(idcliente, Info_nombre.getText(), Info_primer_apellido.getText(),
							Info_segundo_apellido.getText(), Info_telefono.getText(), Info_correo.getText(),
							nuevaContra.isEmpty() ? null : nuevaContra);

					JDialog resultado = new JDialog(frame, "Resultado", true);
					resultado.setUndecorated(true);
					resultado.setSize(400, 180);
					resultado.setLocationRelativeTo(frame);
					resultado.setLayout(null);

					JPanel fondoResultado = new JPanel();
					fondoResultado.setBackground(Color.WHITE);
					fondoResultado.setBounds(0, 0, 400, 180);
					fondoResultado.setLayout(null);
					resultado.add(fondoResultado);

					JPanel encabezadoResultado = new JPanel();
					encabezadoResultado.setBackground(exito ? new Color(81, 151, 255) : new Color(81, 151, 255));
					encabezadoResultado.setBounds(0, 0, 400, 33);
					fondoResultado.add(encabezadoResultado);

					JLabel mensajeFinal = new JLabel("<html><div style='text-align: center;'>"
							+ (exito ? "Cambios guardados correctamente." : "Error al guardar los cambios.")
							+ "</div></html>", SwingConstants.CENTER);
					mensajeFinal.setFont(new Font("Anton", Font.PLAIN, 16));
					mensajeFinal.setBounds(30, 50, 340, 50);
					fondoResultado.add(mensajeFinal);

					JButton aceptarFinal = new JButton("Aceptar");
					aceptarFinal.setBackground(new Color(0, 206, 82));
					aceptarFinal.setForeground(Color.WHITE);
					aceptarFinal.setFont(new Font("Anton", Font.PLAIN, 14));
					aceptarFinal.setBounds(148, 110, 102, 33);
					aceptarFinal.addActionListener(ev -> {
						frame.dispose();
					    resultado.dispose(); 
					    new UsersController().Informacion_de_cliente(idcliente);
					});
					fondoResultado.add(aceptarFinal);

					resultado.setVisible(true);

					if (exito) {
						cambiosRealizados = false;
						valoresOriginales.put("nombre", Info_nombre.getText());
						valoresOriginales.put("primer_apellido", Info_primer_apellido.getText());
						valoresOriginales.put("segundo_apellido", Info_segundo_apellido.getText());
						valoresOriginales.put("telefono", Info_telefono.getText());
						valoresOriginales.put("correo", Info_correo.getText());
					}
				});

				botonNo.addActionListener(a -> confirmacion.dispose());

				confirmacion.setVisible(true);
			}
		});
		panel_2.add(boton_guardar_cambios);

		JButton boton_cancelar_volver = new JButton("Cancelar / volver");
		boton_cancelar_volver.setForeground(new Color(255, 255, 255));
		boton_cancelar_volver.setBackground(new Color(255, 0, 0));
		boton_cancelar_volver.setFont(new Font("Anton", Font.PLAIN, 14));
		boton_cancelar_volver.setBounds(556, 483, 161, 50);
		boton_cancelar_volver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (cambiosRealizados) {
					// Crear un JDialog personalizado para confirmar si quiere salir sin guardar
					JDialog confirmacionSalida = new JDialog(frame, "Confirmar salida", true);
					confirmacionSalida.setUndecorated(true);
					confirmacionSalida.setSize(400, 180);
					confirmacionSalida.setLocationRelativeTo(frame);
					confirmacionSalida.setLayout(null);

					JPanel fondo = new JPanel();
					fondo.setBackground(Color.WHITE);
					fondo.setBounds(0, 0, 400, 180);
					fondo.setLayout(null);
					confirmacionSalida.add(fondo);

					JPanel encabezado = new JPanel();
					encabezado.setBackground(new Color(81, 151, 255));
					encabezado.setBounds(0, 0, 400, 33);
					fondo.add(encabezado);

					JLabel mensaje = new JLabel(
							"<html><div style='text-align: center;'>¿Está seguro que desea salir sin guardar los cambios?</div></html>",
							SwingConstants.CENTER);
					mensaje.setFont(new Font("Anton", Font.PLAIN, 16));
					mensaje.setBounds(30, 50, 340, 50);
					fondo.add(mensaje);

					JButton botonSi = new JButton("Sí");
					botonSi.setBackground(new Color(0, 206, 82));
					botonSi.setForeground(Color.WHITE);
					botonSi.setFont(new Font("Anton", Font.PLAIN, 14));
					botonSi.setBounds(90, 110, 90, 33);

					JButton botonNo = new JButton("No");
					botonNo.setBackground(new Color(255, 70, 70));
					botonNo.setForeground(Color.WHITE);
					botonNo.setFont(new Font("Anton", Font.PLAIN, 14));
					botonNo.setBounds(220, 110, 90, 33);

					fondo.add(botonSi);
					fondo.add(botonNo);

					// Acción del botón "Sí"
					botonSi.addActionListener(a -> {
						confirmacionSalida.dispose();
						// Cerrar el frame actual y redirigir al usuario
						frame.dispose();
						UsersController controller = new UsersController();
						controller.Informacion_de_cliente(idcliente);
					});

					// Acción del botón "No"
					botonNo.addActionListener(a -> confirmacionSalida.dispose());

					confirmacionSalida.setVisible(true);
					return;
				}

				// Si no hay cambios, directamente cerrar el frame y volver
				frame.dispose();
				UsersController controller = new UsersController();
				controller.Informacion_de_cliente(idcliente);
			}
		});
		panel_2.add(boton_cancelar_volver);

		JButton boton_cargar_foto = new JButton("Cargar foto");
		boton_cargar_foto.setBackground(new Color(255, 205, 17));
		boton_cargar_foto.setFont(new Font("Anton", Font.PLAIN, 12));
		boton_cargar_foto.setBounds(15, 296, 132, 32);
		boton_cargar_foto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel_2.add(boton_cargar_foto);

		// combox de
		// usario////////////////////////////////////////////////////////////////////////

//		JComboBox comboBox_Tarifas = new JComboBox();
//		comboBox_Tarifas.setBackground(new Color(204, 204, 204));
//		comboBox_Tarifas.setFont(new Font("Anton", Font.PLAIN, 16));
//		comboBox_Tarifas.setBounds(649, 283, 200, 22);
//		String[] tarifas = { "NINGUNA", "ESTANDAR", "PREMIUM", "FAMILIAR" };
//		for (String tarifa : tarifas) {
//			comboBox_Tarifas.addItem(tarifa);
//		}
//		panel_2.add(comboBox_Tarifas);

		// Botones laterales
		// //////////////////////////////////////////////////////////////////////////////////////////////
		JButton boton_INICIO = new JButton("INICIO");// boton de inicio
		boton_INICIO.setBackground(new Color(255, 205, 17));
		boton_INICIO.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_INICIO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Panel_inicio();
			}
		});
		boton_INICIO.setBounds(10, 86, 136, 71);
		panel.add(boton_INICIO);

		JButton boton_CLIENTES = new JButton("CLIENTES");// boton de clientes
		boton_CLIENTES.setBackground(new Color(255, 255, 255));
		boton_CLIENTES.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CLIENTES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Clientes();
			}
		});
		boton_CLIENTES.setBounds(10, 168, 136, 71);
		panel.add(boton_CLIENTES);

		JButton boton_TARIFAS = new JButton("TARIFAS");// boton de tarifas
		boton_TARIFAS.setBackground(new Color(255, 205, 17));
		boton_TARIFAS.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_TARIFAS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Tarifas();
			}
		});
		boton_TARIFAS.setBounds(10, 250, 136, 71);
		panel.add(boton_TARIFAS);

		JButton boton_INSTRUCTORES = new JButton("INSTRUCTORES");// boton de instructores
		boton_INSTRUCTORES.setBackground(new Color(255, 205, 17));
		boton_INSTRUCTORES.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_INSTRUCTORES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController HC = new HomeController();
				HC.Instructores();
			}
		});
		boton_INSTRUCTORES.setBounds(10, 332, 136, 71);
		panel.add(boton_INSTRUCTORES);

		JButton boton_CLASES = new JButton("CLASES");// boton de clases
		boton_CLASES.setBackground(new Color(255, 205, 17));
		boton_CLASES.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CLASES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Clases();
			}
		});
		boton_CLASES.setBounds(10, 414, 136, 71);
		panel.add(boton_CLASES);

		JButton boton_CHECADOR = new JButton("CHECADOR");// boton de checador
		boton_CHECADOR.setBackground(new Color(255, 205, 17));
		boton_CHECADOR.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CHECADOR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Panel_checador();
			}
		});
		boton_CHECADOR.setBounds(10, 496, 136, 71);
		panel.add(boton_CHECADOR);

		JButton boton_CERRAR_SESION = new JButton("CERRAR SESION");
		boton_CERRAR_SESION.setBackground(new Color(255, 205, 17));
		boton_CERRAR_SESION.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CERRAR_SESION.addActionListener(e -> {
			JDialog dialog = new JDialog(frame, "Cerrar sesión", true);
			dialog.setSize(400, 220);
			dialog.setLocationRelativeTo(frame);
			dialog.setUndecorated(true);
			dialog.setLayout(null);

			// Panel principal - AHORA CON TAMAÑO COMPLETO
			JPanel Cerrar_sesion = new JPanel();
			Cerrar_sesion.setBackground(new Color(255, 255, 255));
			Cerrar_sesion.setBounds(0, 0, 400, 220);
			Cerrar_sesion.setLayout(null);
			dialog.add(Cerrar_sesion);

			// Panel superior azul
			JPanel panel_complemento = new JPanel();
			panel_complemento.setBackground(new Color(81, 151, 255));
			panel_complemento.setBounds(0, 0, 400, 33);
			Cerrar_sesion.add(panel_complemento);

			// Etiqueta con la pregunta
			JLabel pregunta_de_confirmacion = new JLabel(
					"<html><div style='text-align: center;'>Está a punto de cerrar sesión<br>¿Desea continuar?</div></html>");
			pregunta_de_confirmacion.setFont(new Font("Anton", Font.PLAIN, 16));
			pregunta_de_confirmacion.setBounds(90, 44, 310, 60);
			Cerrar_sesion.add(pregunta_de_confirmacion);

			// Botón Cancelar
			JButton boton_cancelar = new JButton("Cancelar");
			boton_cancelar.setForeground(Color.WHITE);
			boton_cancelar.setFont(new Font("Anton", Font.PLAIN, 14));
			boton_cancelar.setBackground(Color.RED);
			boton_cancelar.setBounds(50, 140, 120, 35);
			Cerrar_sesion.add(boton_cancelar);

			// Botón Aceptar
			JButton boton_aceptar = new JButton("Aceptar");
			boton_aceptar.setBackground(new Color(0, 206, 82));
			boton_aceptar.setForeground(Color.WHITE);
			boton_aceptar.setFont(new Font("Anton", Font.PLAIN, 14));
			boton_aceptar.setBounds(230, 140, 120, 35);
			Cerrar_sesion.add(boton_aceptar);

			// Acciones de los botones
			boton_cancelar.addActionListener(ev -> dialog.dispose());
			boton_aceptar.addActionListener(ev -> {
				dialog.dispose();
				closeSession(frame);
			});

			dialog.setVisible(true);
		});
		boton_CERRAR_SESION.setBounds(10, 579, 136, 71);
		panel.add(boton_CERRAR_SESION);

		frame.add(panel);
		frame.repaint();
		frame.revalidate();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	private boolean actualizarCliente(int idCliente, String nombre, String primerApellido, String segundoApellido,
			String telefono, String correo, String contraseña) {
		UsersModel model = new UsersModel();
		return model.actualizarCliente(idCliente, nombre, primerApellido, segundoApellido, telefono, correo,
				contraseña);
	}

	public void Historial_de_pagos(int idcliente) {
		try {
			UIManager.setLookAndFeel(new FlatLightLaf());
			UIManager.put("Button.arc", 8); // Esquinas redondeadas
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		UsersModel um = new UsersModel();
		Map<String, String> datosCliente = um.obtenerDatosBasicosCliente(idcliente);

		if (datosCliente.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Cliente no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
			return;
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
		panel_1.setBounds(0, 0, 1084, 75);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(10, 11, 53, 53);
		ImageIcon icon = new ImageIcon(getClass().getResource("/Imagenes/logo sin letras.png"));
		Image imagen = icon.getImage().getScaledInstance(53, 53, Image.SCALE_SMOOTH);
		lblNewLabel_1.setIcon(new ImageIcon(imagen));
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

		JLabel lblNewLabel = new JLabel("HISTORIAL DE PAGO");// titulo de inicio
		lblNewLabel.setFont(new Font("Anton", Font.PLAIN, 26));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(60, 11, 309, 28);
		panel_3.add(lblNewLabel);

		Object[][] dataCliente = { { datosCliente.get("id"), datosCliente.get("nombre").split(" ")[0], // Nombre
				datosCliente.get("nombre").split(" ").length > 1 ? datosCliente.get("nombre").split(" ")[1] : "", // Apellido
				datosCliente.get("telefono"), datosCliente.get("correo") } };

		String[] columnas = { "ID cliente", "Nombre(s)", "Primer apellido", "Teléfono", "Correo electrónico" };

		JScrollPane scrollPane_Usuario = new JScrollPane();// tabla del usario
		scrollPane_Usuario.setBounds(10, 61, 898, 50);
		panel_2.add(scrollPane_Usuario);
		JTable table = new JTable(dataCliente, columnas);
		table.setFont(new Font("Anton", Font.PLAIN, 12));
		table.setBackground(new Color(204, 204, 204));
		scrollPane_Usuario.setViewportView(table);

		JTableHeader header = table.getTableHeader();
		header.setBackground(Color.BLACK);
		header.setForeground(Color.WHITE);
		header.setFont(new Font("Anton", Font.PLAIN, 14));
		header.setReorderingAllowed(false);
		scrollPane_Usuario.setViewportView(table);

		Object[][] data2 = { { 15632948, "PREMIUM", "600.00", "16/08/2024" } };

		String[] columnas2 = { "ID Pago", "Plan", "Tarifa", "Fecha" };

		JScrollPane scrollPane_Pagos = new JScrollPane();// tabla de pagos
		scrollPane_Pagos.setBounds(10, 122, 898, 168);
		panel_2.add(scrollPane_Pagos);
		JTable table_1 = new JTable(data2, columnas2);
		table_1.setFont(new Font("Anton", Font.PLAIN, 12));
		table_1.setBackground(new Color(204, 204, 204));
		scrollPane_Pagos.setViewportView(table_1);

		JTableHeader header2 = table_1.getTableHeader();
		header2.setBackground(Color.BLACK);
		header2.setForeground(Color.WHITE);
		header2.setFont(new Font("Anton", Font.PLAIN, 14));
		header2.setReorderingAllowed(false);
		scrollPane_Pagos.setViewportView(table_1);

		JButton boton_regresar = new JButton("Regresar");// boton regresar
		boton_regresar.setFont(new Font("Anton", Font.PLAIN, 20));
		boton_regresar.setBackground(new Color(255, 205, 17));
		boton_regresar.setBounds(724, 503, 184, 50);
		boton_regresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				UsersController controller = new UsersController();
				controller.Informacion_de_cliente(idcliente);
			}
		});
		panel_2.add(boton_regresar);

		JButton boton_INICIO = new JButton("INICIO");
		boton_INICIO.setBackground(new Color(255, 205, 17));
		boton_INICIO.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_INICIO.setBounds(10, 86, 136, 71);
		boton_INICIO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Panel_inicio();
			}
		});
		panel.add(boton_INICIO);

		JButton boton_CLIENTES = new JButton("CLIENTES");// boton de clientes
		boton_CLIENTES.setBackground(new Color(255, 255, 255));
		boton_CLIENTES.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CLIENTES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Clientes();
			}
		});
		boton_CLIENTES.setBounds(10, 168, 136, 71);
		panel.add(boton_CLIENTES);

		JButton boton_TARIFAS = new JButton("TARIFAS");// boton de tarifas
		boton_TARIFAS.setBackground(new Color(255, 205, 17));
		boton_TARIFAS.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_TARIFAS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Tarifas();
			}
		});
		boton_TARIFAS.setBounds(10, 250, 136, 71);
		panel.add(boton_TARIFAS);

		JButton boton_INSTRUCTORES = new JButton("INSTRUCTORES");// boton de instructores
		boton_INSTRUCTORES.setBackground(new Color(255, 205, 17));
		boton_INSTRUCTORES.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_INSTRUCTORES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Instructores();
			}
		});
		boton_INSTRUCTORES.setBounds(10, 332, 136, 71);
		panel.add(boton_INSTRUCTORES);

		JButton boton_CLASES = new JButton("CLASES");
		boton_CLASES.setBackground(new Color(255, 205, 17));
		boton_CLASES.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CLASES.setBounds(10, 414, 136, 71);
		boton_CLASES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Clases();
			}
		});
		panel.add(boton_CLASES);

		JButton boton_CHECADOR = new JButton("CHECADOR");// boton de checador
		boton_CHECADOR.setBackground(new Color(255, 205, 17));
		boton_CHECADOR.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CHECADOR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Panel_checador();
			}
		});
		boton_CHECADOR.setBounds(10, 496, 136, 71);
		panel.add(boton_CHECADOR);

		JButton boton_CERRAR_SESION = new JButton("CERRAR SESION");
		boton_CERRAR_SESION.setBackground(new Color(255, 205, 17));
		boton_CERRAR_SESION.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CERRAR_SESION.addActionListener(e -> {
			JDialog dialog = new JDialog(frame, "Cerrar sesión", true);
			dialog.setSize(400, 220);
			dialog.setLocationRelativeTo(frame);
			dialog.setUndecorated(true);
			dialog.setLayout(null);

			// Panel principal - AHORA CON TAMAÑO COMPLETO
			JPanel Cerrar_sesion = new JPanel();
			Cerrar_sesion.setBackground(new Color(255, 255, 255));
			Cerrar_sesion.setBounds(0, 0, 400, 220);
			Cerrar_sesion.setLayout(null);
			dialog.add(Cerrar_sesion);

			// Panel superior azul
			JPanel panel_complemento = new JPanel();
			panel_complemento.setBackground(new Color(81, 151, 255));
			panel_complemento.setBounds(0, 0, 400, 33);
			Cerrar_sesion.add(panel_complemento);

			// Etiqueta con la pregunta
			JLabel pregunta_de_confirmacion = new JLabel(
					"<html><div style='text-align: center;'>Está a punto de cerrar sesión<br>¿Desea continuar?</div></html>");
			pregunta_de_confirmacion.setFont(new Font("Anton", Font.PLAIN, 16));
			pregunta_de_confirmacion.setBounds(90, 44, 310, 60);
			Cerrar_sesion.add(pregunta_de_confirmacion);

			// Botón Cancelar
			JButton boton_cancelar = new JButton("Cancelar");
			boton_cancelar.setForeground(Color.WHITE);
			boton_cancelar.setFont(new Font("Anton", Font.PLAIN, 14));
			boton_cancelar.setBackground(Color.RED);
			boton_cancelar.setBounds(50, 140, 120, 35);
			Cerrar_sesion.add(boton_cancelar);

			// Botón Aceptar
			JButton boton_aceptar = new JButton("Aceptar");
			boton_aceptar.setBackground(new Color(0, 206, 82));
			boton_aceptar.setForeground(Color.WHITE);
			boton_aceptar.setFont(new Font("Anton", Font.PLAIN, 14));
			boton_aceptar.setBounds(230, 140, 120, 35);
			Cerrar_sesion.add(boton_aceptar);

			// Acciones de los botones
			boton_cancelar.addActionListener(ev -> dialog.dispose());
			boton_aceptar.addActionListener(ev -> {
				dialog.dispose();
				closeSession(frame);
			});

			dialog.setVisible(true);
		});
		boton_CERRAR_SESION.setBounds(10, 579, 136, 71);
		panel.add(boton_CERRAR_SESION);

		frame.add(panel);
		frame.repaint();
		frame.revalidate();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public void HIstorial_de_asistencias(int idcliente) {
		try {
			UIManager.setLookAndFeel(new FlatLightLaf());
			UIManager.put("Button.arc", 8); // Esquinas redondeadas
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		UsersModel um = new UsersModel();
		Map<String, String> datosCliente = um.obtenerDatosBasicosCliente(idcliente);

		if (datosCliente.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Cliente no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
			return;
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
		panel_1.setBounds(0, 0, 1084, 75);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(10, 11, 53, 53);
		ImageIcon icon = new ImageIcon(getClass().getResource("/Imagenes/logo sin letras.png"));
		Image imagen = icon.getImage().getScaledInstance(53, 53, Image.SCALE_SMOOTH);
		lblNewLabel_1.setIcon(new ImageIcon(imagen));
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

		JLabel lblNewLabel = new JLabel("HISTORIAL DE ASISTENCIA");// titulo de inicio
		lblNewLabel.setFont(new Font("Anton", Font.PLAIN, 26));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(60, 11, 309, 28);
		panel_3.add(lblNewLabel);

		Object[][] dataCliente = { { datosCliente.get("id"), datosCliente.get("nombre").split(" ")[0], // Nombre
				datosCliente.get("nombre").split(" ").length > 1 ? datosCliente.get("nombre").split(" ")[1] : "", // Apellido
				datosCliente.get("telefono"), datosCliente.get("correo") } };

		String[] columnas = { "ID cliente", "Nombre(s)", "Primer apellido", "Teléfono", "Correo electrónico" };

		JScrollPane scrollPane_Usuario = new JScrollPane();// tabla del usario
		scrollPane_Usuario.setBounds(10, 61, 898, 50);
		panel_2.add(scrollPane_Usuario);
		JTable table = new JTable(dataCliente, columnas);
		table.setFont(new Font("Anton", Font.PLAIN, 12));
		table.setBackground(new Color(204, 204, 204));
		scrollPane_Usuario.setViewportView(table);

		JTableHeader header = table.getTableHeader();
		header.setBackground(Color.BLACK);
		header.setForeground(Color.WHITE);
		header.setFont(new Font("Anton", Font.PLAIN, 14));
		header.setReorderingAllowed(false);
		scrollPane_Usuario.setViewportView(table);

		Object[][] data2 = { { "15", "06", "2024", "3:50 pm" } };

		String[] columnas2 = { "Dia", "Mes", "Año", "Hora de entrada" };

		JScrollPane scrollPane_Pagos = new JScrollPane();// tabla de pagos
		scrollPane_Pagos.setBounds(10, 122, 898, 168);
		panel_2.add(scrollPane_Pagos);
		JTable table_1 = new JTable(data2, columnas2);
		table_1.setFont(new Font("Anton", Font.PLAIN, 12));
		table_1.setBackground(new Color(204, 204, 204));
		scrollPane_Pagos.setViewportView(table_1);

		JTableHeader header2 = table_1.getTableHeader();
		header2.setBackground(Color.BLACK);
		header2.setForeground(Color.WHITE);
		header2.setFont(new Font("Anton", Font.PLAIN, 14));
		header2.setReorderingAllowed(false);
		scrollPane_Pagos.setViewportView(table_1);

		JButton boton_regresar = new JButton("Regresar");// boton regresar
		boton_regresar.setFont(new Font("Anton", Font.PLAIN, 20));
		boton_regresar.setBackground(new Color(255, 205, 17));
		boton_regresar.setBounds(724, 503, 184, 50);
		boton_regresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				UsersController controller = new UsersController();
				controller.Informacion_de_cliente(idcliente);
			}
		});
		panel_2.add(boton_regresar);

		JButton boton_INICIO = new JButton("INICIO");
		boton_INICIO.setBackground(new Color(255, 205, 17));
		boton_INICIO.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_INICIO.setBounds(10, 86, 136, 71);
		boton_INICIO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Panel_inicio();

			}
		});
		panel.add(boton_INICIO);

		JButton boton_CLIENTES = new JButton("CLIENTES");// boton de clientes
		boton_CLIENTES.setBackground(new Color(255, 255, 255));
		boton_CLIENTES.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CLIENTES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Clientes();
			}
		});
		boton_CLIENTES.setBounds(10, 168, 136, 71);
		panel.add(boton_CLIENTES);

		JButton boton_TARIFAS = new JButton("TARIFAS");// boton de tarifas
		boton_TARIFAS.setBackground(new Color(255, 205, 17));
		boton_TARIFAS.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_TARIFAS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Tarifas();
			}
		});
		boton_TARIFAS.setBounds(10, 250, 136, 71);
		panel.add(boton_TARIFAS);

		JButton boton_INSTRUCTORES = new JButton("INSTRUCTORES");// boton de instructores
		boton_INSTRUCTORES.setBackground(new Color(255, 205, 17));
		boton_INSTRUCTORES.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_INSTRUCTORES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Instructores();
			}
		});
		boton_INSTRUCTORES.setBounds(10, 332, 136, 71);
		panel.add(boton_INSTRUCTORES);

		JButton boton_CLASES = new JButton("CLASES");
		boton_CLASES.setBackground(new Color(255, 205, 17));
		boton_CLASES.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CLASES.setBounds(10, 414, 136, 71);
		boton_CLASES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Clases();
			}
		});
		panel.add(boton_CLASES);

		JButton boton_CHECADOR = new JButton("CHECADOR");// boton de checador
		boton_CHECADOR.setBackground(new Color(255, 205, 17));
		boton_CHECADOR.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CHECADOR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Clases();
			}
		});
		boton_CHECADOR.setBounds(10, 496, 136, 71);
		panel.add(boton_CHECADOR);

		JButton boton_CERRAR_SESION = new JButton("CERRAR SESION");
		boton_CERRAR_SESION.setBackground(new Color(255, 205, 17));
		boton_CERRAR_SESION.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CERRAR_SESION.addActionListener(e -> {
			JDialog dialog = new JDialog(frame, "Cerrar sesión", true);
			dialog.setSize(400, 220);
			dialog.setLocationRelativeTo(frame);
			dialog.setUndecorated(true);
			dialog.setLayout(null);

			// Panel principal - AHORA CON TAMAÑO COMPLETO
			JPanel Cerrar_sesion = new JPanel();
			Cerrar_sesion.setBackground(new Color(255, 255, 255));
			Cerrar_sesion.setBounds(0, 0, 400, 220);
			Cerrar_sesion.setLayout(null);
			dialog.add(Cerrar_sesion);

			// Panel superior azul
			JPanel panel_complemento = new JPanel();
			panel_complemento.setBackground(new Color(81, 151, 255));
			panel_complemento.setBounds(0, 0, 400, 33);
			Cerrar_sesion.add(panel_complemento);

			// Etiqueta con la pregunta
			JLabel pregunta_de_confirmacion = new JLabel(
					"<html><div style='text-align: center;'>Está a punto de cerrar sesión<br>¿Desea continuar?</div></html>");
			pregunta_de_confirmacion.setFont(new Font("Anton", Font.PLAIN, 16));
			pregunta_de_confirmacion.setBounds(90, 44, 310, 60);
			Cerrar_sesion.add(pregunta_de_confirmacion);

			// Botón Cancelar
			JButton boton_cancelar = new JButton("Cancelar");
			boton_cancelar.setForeground(Color.WHITE);
			boton_cancelar.setFont(new Font("Anton", Font.PLAIN, 14));
			boton_cancelar.setBackground(Color.RED);
			boton_cancelar.setBounds(50, 140, 120, 35);
			Cerrar_sesion.add(boton_cancelar);

			// Botón Aceptar
			JButton boton_aceptar = new JButton("Aceptar");
			boton_aceptar.setBackground(new Color(0, 206, 82));
			boton_aceptar.setForeground(Color.WHITE);
			boton_aceptar.setFont(new Font("Anton", Font.PLAIN, 14));
			boton_aceptar.setBounds(230, 140, 120, 35);
			Cerrar_sesion.add(boton_aceptar);

			// Acciones de los botones
			boton_cancelar.addActionListener(ev -> dialog.dispose());
			boton_aceptar.addActionListener(ev -> {
				dialog.dispose();
				closeSession(frame);
			});

			dialog.setVisible(true);
		});
		boton_CERRAR_SESION.setBounds(10, 579, 136, 71);
		panel.add(boton_CERRAR_SESION);

		frame.add(panel);
		frame.repaint();
		frame.revalidate();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public void Credencial_usuario(int idCliente) {
		try {
			UIManager.setLookAndFeel(new FlatLightLaf());
			UIManager.put("Button.arc", 8); // Esquinas redondeadas
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		UsersModel um = new UsersModel();
		Map<String, String> datosCliente = um.obtenerDatosBasicosCliente(idCliente);

		if (datosCliente.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Cliente no encontrado. ID inválido: " + idCliente, "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
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
		panel_1.setBounds(0, 0, 1084, 75);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(10, 11, 53, 53);
		ImageIcon icon = new ImageIcon(getClass().getResource("/Imagenes/logo sin letras.png"));
		Image imagen = icon.getImage().getScaledInstance(53, 53, Image.SCALE_SMOOTH);
		lblNewLabel_1.setIcon(new ImageIcon(imagen));
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

		JLabel lblNewLabel = new JLabel("Credencial");// titulo de inicio
		lblNewLabel.setFont(new Font("Anton", Font.PLAIN, 26));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(60, 11, 309, 28);
		panel_3.add(lblNewLabel);

		JLabel Imagen_de_usuario = new JLabel("");
		Imagen_de_usuario.setBounds(70, 140, 200, 300);
		Imagen_de_usuario.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		Imagen_de_usuario.setHorizontalAlignment(SwingConstants.CENTER);
		Imagen_de_usuario.setVerticalAlignment(SwingConstants.CENTER);
		ImageIcon icon1 = new ImageIcon(getClass().getResource("/Imagenes/GYMBRO.jpg"));
		Image imagen1 = icon1.getImage().getScaledInstance(200, 300, Image.SCALE_SMOOTH);
		Imagen_de_usuario.setIcon(new ImageIcon(imagen1));
		panel_2.add(Imagen_de_usuario);

		JLabel lblNewLabel_5 = new JLabel("MIEMBRO");
		lblNewLabel_5.setFont(new Font("Anton", Font.PLAIN, 32));
		lblNewLabel_5.setBounds(70, 61, 147, 68);
		panel_2.add(lblNewLabel_5);

		JLabel lblNewLabel_11 = new JLabel("Nombre:");
		lblNewLabel_11.setFont(new Font("Anton", Font.PLAIN, 18));
		lblNewLabel_11.setBounds(340, 160, 150, 32);
		panel_2.add(lblNewLabel_11);

		JLabel lblNewLabel_10 = new JLabel("Primer apellido:");
		lblNewLabel_10.setFont(new Font("Anton", Font.PLAIN, 18));
		lblNewLabel_10.setBounds(340, 230, 150, 32);
		panel_2.add(lblNewLabel_10);

		JLabel lblNewLabel_6 = new JLabel("Vigencia hasta: ");
		lblNewLabel_6.setFont(new Font("Anton", Font.PLAIN, 18));
		lblNewLabel_6.setBounds(340, 370, 150, 32);
		panel_2.add(lblNewLabel_6);

		JLabel lblNewLabel_13 = new JLabel("No. Identificador: ");
		lblNewLabel_13.setFont(new Font("Anton", Font.PLAIN, 18));
		lblNewLabel_13.setBounds(688, 160, 150, 32);
		panel_2.add(lblNewLabel_13);

		JLabel lblNewLabel_14 = new JLabel("Plan actual: ");
		lblNewLabel_14.setFont(new Font("Anton", Font.PLAIN, 18));
		lblNewLabel_14.setBounds(340, 300, 150, 32);
		panel_2.add(lblNewLabel_14);

		JLabel lblNewLabel_7 = new JLabel("");
		lblNewLabel_7.setBounds(779, 370, 100, 100);
		lblNewLabel_7.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setVerticalAlignment(SwingConstants.CENTER);
		ImageIcon icon2 = new ImageIcon(getClass().getResource("/Imagenes/logo ginmasio.png"));
		Image imagen2 = icon2.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		lblNewLabel_7.setIcon(new ImageIcon(imagen2));
		panel_2.add(lblNewLabel_7);

		// info de
		// usuario/////////////////////////////////////////////////////////////////////////////////////////////////
		JLabel Info_nombre = new JLabel("");
		Info_nombre.setText(datosCliente.get("nombre"));
		Info_nombre.setFont(new Font("Anton", Font.PLAIN, 22));
		Info_nombre.setBounds(340, 185, 200, 30);
		panel_2.add(Info_nombre);

		JLabel Info_primer_apellido = new JLabel("");
		Info_primer_apellido.setText(datosCliente.get("primer_apellido"));
		Info_primer_apellido.setFont(new Font("Anton", Font.PLAIN, 22));
		Info_primer_apellido.setBounds(340, 255, 200, 30);
		panel_2.add(Info_primer_apellido);

		JLabel Info_plan = new JLabel("");
		Info_plan.setText(datosCliente.getOrDefault("tarifa", "Ninguna"));
		Info_plan.setFont(new Font("Anton", Font.PLAIN, 22));
		Info_plan.setBounds(340, 325, 200, 30);
		panel_2.add(Info_plan);

		JLabel Info_identificador = new JLabel("");
		Info_identificador.setText(datosCliente.get("id"));
		Info_identificador.setFont(new Font("Anton", Font.PLAIN, 22));
		Info_identificador.setBounds(688, 185, 200, 30);
		panel_2.add(Info_identificador);

		JLabel Info_Vigencia = new JLabel("09/06/2025");
		Info_Vigencia.setFont(new Font("Anton", Font.PLAIN, 22));
		Info_Vigencia.setBounds(340, 395, 200, 30);
		panel_2.add(Info_Vigencia);

		// botones de accion para el cliente
		// ///////////////////////////////////////////////////////////////////////
		JButton boton_descraga_credencial = new JButton("Descargar ");
		boton_descraga_credencial.setBackground(new Color(255, 205, 17));
		boton_descraga_credencial.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_descraga_credencial.setBounds(740, 494, 147, 39);
		panel_2.add(boton_descraga_credencial);

		JButton boton_regresar = new JButton("Regresar");// boton regresar
		boton_regresar.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_regresar.setBackground(new Color(255, 205, 17));
		boton_regresar.setBounds(583, 494, 147, 39);
		boton_regresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				UsersController controller = new UsersController();
				controller.Informacion_de_cliente(idCliente);
			}
		});
		panel_2.add(boton_regresar);

		// Botones laterales
		// //////////////////////////////////////////////////////////////////////////////////////////////
		JButton boton_INICIO = new JButton("INICIO");// boton de inicio
		boton_INICIO.setBackground(new Color(255, 205, 17));
		boton_INICIO.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_INICIO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Panel_inicio();
			}
		});
		boton_INICIO.setBounds(10, 86, 136, 71);
		panel.add(boton_INICIO);

		JButton boton_CLIENTES = new JButton("CLIENTES");// boton de clientes
		boton_CLIENTES.setBackground(new Color(255, 255, 255));
		boton_CLIENTES.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CLIENTES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Clientes();
			}
		});
		boton_CLIENTES.setBounds(10, 168, 136, 71);
		panel.add(boton_CLIENTES);

		JButton boton_TARIFAS = new JButton("TARIFAS");// boton de tarifas
		boton_TARIFAS.setBackground(new Color(255, 205, 17));
		boton_TARIFAS.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_TARIFAS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Tarifas();
			}
		});
		boton_TARIFAS.setBounds(10, 250, 136, 71);
		panel.add(boton_TARIFAS);

		JButton boton_INSTRUCTORES = new JButton("INSTRUCTORES");// boton de instructores
		boton_INSTRUCTORES.setBackground(new Color(255, 205, 17));
		boton_INSTRUCTORES.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_INSTRUCTORES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Instructores();
			}
		});
		boton_INSTRUCTORES.setBounds(10, 332, 136, 71);
		panel.add(boton_INSTRUCTORES);

		JButton boton_CLASES = new JButton("CLASES");// boton de clases
		boton_CLASES.setBackground(new Color(255, 205, 17));
		boton_CLASES.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CLASES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Clases();
			}
		});
		boton_CLASES.setBounds(10, 414, 136, 71);
		panel.add(boton_CLASES);

		JButton boton_CHECADOR = new JButton("CHECADOR");// boton de checador
		boton_CHECADOR.setBackground(new Color(255, 205, 17));
		boton_CHECADOR.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CHECADOR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Panel_checador();
			}
		});
		boton_CHECADOR.setBounds(10, 496, 136, 71);
		panel.add(boton_CHECADOR);

		JButton boton_CERRAR_SESION = new JButton("CERRAR SESION");
		boton_CERRAR_SESION.setBackground(new Color(255, 205, 17));
		boton_CERRAR_SESION.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CERRAR_SESION.addActionListener(e -> {
			JDialog dialog = new JDialog(frame, "Cerrar sesión", true);
			dialog.setSize(400, 220);
			dialog.setLocationRelativeTo(frame);
			dialog.setUndecorated(true);
			dialog.setLayout(null);

			// Panel principal - AHORA CON TAMAÑO COMPLETO
			JPanel Cerrar_sesion = new JPanel();
			Cerrar_sesion.setBackground(new Color(255, 255, 255));
			Cerrar_sesion.setBounds(0, 0, 400, 220);
			Cerrar_sesion.setLayout(null);
			dialog.add(Cerrar_sesion);

			// Panel superior azul
			JPanel panel_complemento = new JPanel();
			panel_complemento.setBackground(new Color(81, 151, 255));
			panel_complemento.setBounds(0, 0, 400, 33);
			Cerrar_sesion.add(panel_complemento);

			// Etiqueta con la pregunta
			JLabel pregunta_de_confirmacion = new JLabel(
					"<html><div style='text-align: center;'>Está a punto de cerrar sesión<br>¿Desea continuar?</div></html>");
			pregunta_de_confirmacion.setFont(new Font("Anton", Font.PLAIN, 16));
			pregunta_de_confirmacion.setBounds(90, 44, 310, 60);
			Cerrar_sesion.add(pregunta_de_confirmacion);

			// Botón Cancelar
			JButton boton_cancelar = new JButton("Cancelar");
			boton_cancelar.setForeground(Color.WHITE);
			boton_cancelar.setFont(new Font("Anton", Font.PLAIN, 14));
			boton_cancelar.setBackground(Color.RED);
			boton_cancelar.setBounds(50, 140, 120, 35);
			Cerrar_sesion.add(boton_cancelar);

			// Botón Aceptar
			JButton boton_aceptar = new JButton("Aceptar");
			boton_aceptar.setBackground(new Color(0, 206, 82));
			boton_aceptar.setForeground(Color.WHITE);
			boton_aceptar.setFont(new Font("Anton", Font.PLAIN, 14));
			boton_aceptar.setBounds(230, 140, 120, 35);
			Cerrar_sesion.add(boton_aceptar);

			// Acciones de los botones
			boton_cancelar.addActionListener(ev -> dialog.dispose());
			boton_aceptar.addActionListener(ev -> {
				dialog.dispose();
				closeSession(frame);
			});

			dialog.setVisible(true);
		});
		boton_CERRAR_SESION.setBounds(10, 579, 136, 71);
		panel.add(boton_CERRAR_SESION);

		frame.add(panel);
		frame.repaint();
		frame.revalidate();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public void Añadir_cliente() {
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
		panel_1.setBounds(0, 0, 1084, 75);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(10, 11, 53, 53);
		ImageIcon icon = new ImageIcon(getClass().getResource("/Imagenes/logo sin letras.png"));
		Image imagen = icon.getImage().getScaledInstance(53, 53, Image.SCALE_SMOOTH);
		lblNewLabel_1.setIcon(new ImageIcon(imagen));
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

		JLabel lblNewLabel = new JLabel("Añadir Clientes");// titulo de inicio
		lblNewLabel.setFont(new Font("Anton", Font.PLAIN, 26));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(60, 11, 309, 28);
		panel_3.add(lblNewLabel);

		JLabel Imagen_de_usuario = new JLabel("");
		Imagen_de_usuario.setBounds(15, 115, 132, 180);
		Imagen_de_usuario.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		Imagen_de_usuario.setHorizontalAlignment(SwingConstants.CENTER);
		Imagen_de_usuario.setVerticalAlignment(SwingConstants.CENTER);
		ImageIcon icon1 = new ImageIcon(getClass().getResource("/Imagenes/GYMBRO.jpg"));
		Image imagen1 = icon1.getImage().getScaledInstance(132, 180, Image.SCALE_SMOOTH);
		Imagen_de_usuario.setIcon(new ImageIcon(imagen1));
		panel_2.add(Imagen_de_usuario);

		JLabel lblNewLabel_5 = new JLabel("Datos del nuevo miembro:");
		lblNewLabel_5.setFont(new Font("Anton", Font.PLAIN, 16));
		lblNewLabel_5.setBounds(15, 61, 223, 32);
		panel_2.add(lblNewLabel_5);

		JLabel lblNewLabel_11 = new JLabel("Nombre:");
		lblNewLabel_11.setFont(new Font("Anton", Font.PLAIN, 16));
		lblNewLabel_11.setBounds(230, 115, 60, 22);
		panel_2.add(lblNewLabel_11);

		JLabel lblNewLabel_10 = new JLabel("Primer apellido:");
		lblNewLabel_10.setFont(new Font("Anton", Font.PLAIN, 16));
		lblNewLabel_10.setBounds(230, 170, 104, 22);
		panel_2.add(lblNewLabel_10);

		JLabel lblNewLabel_9 = new JLabel("Segundo apellido:");
		lblNewLabel_9.setFont(new Font("Anton", Font.PLAIN, 16));
		lblNewLabel_9.setBounds(230, 225, 117, 22);
		panel_2.add(lblNewLabel_9);

		JLabel lblNewLabel_8 = new JLabel("Teléfono:");
		lblNewLabel_8.setFont(new Font("Anton", Font.PLAIN, 16));
		lblNewLabel_8.setBounds(230, 280, 117, 22);
		panel_2.add(lblNewLabel_8);

		JLabel lblNewLabel_7 = new JLabel("Correo electronico:");
		lblNewLabel_7.setFont(new Font("Anton", Font.PLAIN, 16));
		lblNewLabel_7.setBounds(499, 115, 132, 28);
		panel_2.add(lblNewLabel_7);

		JLabel lblNewLabel_6 = new JLabel("Tarifa:");
		lblNewLabel_6.setFont(new Font("Anton", Font.PLAIN, 16));
		lblNewLabel_6.setBounds(499, 280, 60, 22);
		panel_2.add(lblNewLabel_6);

		JLabel lblNewLabel_12 = new JLabel("Confirmar contraseña:");
		lblNewLabel_12.setFont(new Font("Anton", Font.PLAIN, 16));
		lblNewLabel_12.setBounds(499, 225, 152, 22);
		panel_2.add(lblNewLabel_12);

		JLabel lblNewLabel_13 = new JLabel("Contraseña: ");
		lblNewLabel_13.setFont(new Font("Anton", Font.PLAIN, 16));
		lblNewLabel_13.setBounds(499, 170, 85, 22);
		panel_2.add(lblNewLabel_13);

		// informacion del usuario
		// ////////////////////////////////////////////////////////////
		JTextField txtNombre = new JTextField("");
		txtNombre.setBackground(new Color(204, 204, 204));
		txtNombre.setFont(new Font("Anton", Font.PLAIN, 16));
		txtNombre.setBounds(357, 115, 132, 22);
		panel_2.add(txtNombre);

		JTextField txtPrimerApellido = new JTextField("");
		txtPrimerApellido.setBackground(new Color(204, 204, 204));
		txtPrimerApellido.setFont(new Font("Anton", Font.PLAIN, 16));
		txtPrimerApellido.setBounds(357, 170, 132, 22);
		panel_2.add(txtPrimerApellido);

		JTextField txtSegundoApellido = new JTextField("");
		txtSegundoApellido.setBackground(new Color(204, 204, 204));
		txtSegundoApellido.setFont(new Font("Anton", Font.PLAIN, 16));
		txtSegundoApellido.setBounds(357, 225, 132, 22);
		panel_2.add(txtSegundoApellido);

		JTextField txtTelefono = new JTextField("");
		txtTelefono.setBackground(new Color(204, 204, 204));
		txtTelefono.setFont(new Font("Anton", Font.PLAIN, 16));
		txtTelefono.setBounds(357, 280, 132, 22);
		panel_2.add(txtTelefono);

		JTextField txtCorreo = new JTextField("");
		txtCorreo.setBackground(new Color(204, 204, 204));
		txtCorreo.setFont(new Font("Anton", Font.PLAIN, 16));
		txtCorreo.setBounds(649, 115, 200, 22);
		panel_2.add(txtCorreo);

		JPasswordField txtContrasena = new JPasswordField("");
		txtContrasena.setBackground(new Color(204, 204, 204));
		txtContrasena.setFont(new Font("Anton", Font.PLAIN, 16));
		txtContrasena.setBounds(649, 170, 200, 22);
		panel_2.add(txtContrasena);

		JPasswordField txtConfirmarContrasena = new JPasswordField("");
		txtConfirmarContrasena.setBackground(new Color(204, 204, 204));
		txtConfirmarContrasena.setFont(new Font("Anton", Font.PLAIN, 16));
		txtConfirmarContrasena.setBounds(649, 225, 200, 22);
		panel_2.add(txtConfirmarContrasena);

		JComboBox comboBox_Tarifas = new JComboBox();
		comboBox_Tarifas.setBackground(new Color(204, 204, 204));
		comboBox_Tarifas.setFont(new Font("Anton", Font.PLAIN, 16));
		comboBox_Tarifas.setBounds(649, 283, 200, 22);
		String[] tarifas = { "NINGUNA", "estandar", "premium", "familiar" };
		for (String tarifa : tarifas) {
			comboBox_Tarifas.addItem(tarifa);
		}
		panel_2.add(comboBox_Tarifas);

		// botones de accion para el cliente
		// ///////////////////////////////////////////////////////////////////////
		JButton boton_registar = new JButton("Registrar usuario");
		boton_registar.setForeground(new Color(255, 255, 255));
		boton_registar.setBackground(new Color(0, 206, 82));
		boton_registar.setFont(new Font("Anton", Font.PLAIN, 14));
		boton_registar.setBounds(727, 483, 160, 50);
		boton_registar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = txtNombre.getText().trim();
				String primerApellido = txtPrimerApellido.getText().trim();
				String segundoApellido = txtSegundoApellido.getText().trim();
				String telefono = txtTelefono.getText().trim();
				String correo = txtCorreo.getText().trim();
				String contrasena = new String(txtContrasena.getPassword());
				String confirmarContrasena = new String(txtConfirmarContrasena.getPassword());
				String tarifaSeleccionada = (String) comboBox_Tarifas.getSelectedItem();

				Frame parentFrame = JOptionPane.getFrameForComponent(frame); // Para el JDialog

				// Validación de campos obligatorios
				if (nombre.isEmpty() || primerApellido.isEmpty() || correo.isEmpty() || contrasena.isEmpty()
						|| confirmarContrasena.isEmpty()) {

					// Alerta personalizada de campos obligatorios
					JDialog camposDialog = new JDialog(parentFrame, "Advertencia", true);
					camposDialog.setSize(400, 180);
					camposDialog.setLayout(null);
					camposDialog.setUndecorated(true);
					camposDialog.setLocationRelativeTo(frame);

					JPanel camposObligatorios = new JPanel();
					camposObligatorios.setBackground(new Color(255, 255, 255));
					camposObligatorios.setBounds(0, 0, 400, 180);
					camposObligatorios.setLayout(null);
					camposDialog.add(camposObligatorios);

					JPanel panelComplemento = new JPanel();
					panelComplemento.setBackground(new Color(81, 151, 255));
					panelComplemento.setBounds(0, 0, 400, 33);
					camposObligatorios.add(panelComplemento);

					JLabel mensajeCampos = new JLabel(
							"<html><div style='text-align: center;'>Todos los campos obligatorios deben estar completos<br></div></html>");
					mensajeCampos.setFont(new Font("Anton", Font.PLAIN, 16));
					mensajeCampos.setBounds(43, 44, 312, 59);
					camposObligatorios.add(mensajeCampos);

					JButton botonAceptarCampos = new JButton("Aceptar");
					botonAceptarCampos.setBackground(new Color(0, 206, 82));
					botonAceptarCampos.setForeground(Color.WHITE);
					botonAceptarCampos.setFont(new Font("Anton", Font.PLAIN, 14));
					botonAceptarCampos.setBounds(151, 121, 102, 33);
					camposObligatorios.add(botonAceptarCampos);

					botonAceptarCampos.addActionListener(ev -> camposDialog.dispose());
					camposDialog.setVisible(true);
					return;
				}

				// Validación de caracteres en el nombre y apellidos (solo letras)
				if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚÑñ]+") || !primerApellido.matches("[a-zA-ZáéíóúÁÉÍÓÚÑñ]+")
						|| !segundoApellido.matches("[a-zA-ZáéíóúÁÉÍÓÚÑñ]*")) {

					// Alerta personalizada si el nombre o los apellidos tienen caracteres no
					// válidos
					JDialog caracteresDialog = new JDialog(parentFrame, "Advertencia", true);
					caracteresDialog.setSize(400, 180);
					caracteresDialog.setLayout(null);
					caracteresDialog.setUndecorated(true);
					caracteresDialog.setLocationRelativeTo(frame);

					JPanel panel = new JPanel();
					panel.setBackground(Color.WHITE);
					panel.setBounds(0, 0, 400, 180);
					panel.setLayout(null);
					caracteresDialog.add(panel);

					JPanel panelSuperior = new JPanel();
					panelSuperior.setBackground(new Color(81, 151, 255));
					panelSuperior.setBounds(0, 0, 400, 33);
					panel.add(panelSuperior);

					JLabel mensaje = new JLabel(
							"<html><div style='text-align: center;'>El nombre y los apellidos solo deben contener letras.</div></html>");
					mensaje.setFont(new Font("Anton", Font.PLAIN, 16));
					mensaje.setBounds(50, 44, 300, 59);
					panel.add(mensaje);

					JButton botonAceptar = new JButton("Aceptar");
					botonAceptar.setBackground(new Color(0, 206, 82));
					botonAceptar.setForeground(Color.WHITE);
					botonAceptar.setFont(new Font("Anton", Font.PLAIN, 14));
					botonAceptar.setBounds(151, 121, 102, 33);
					panel.add(botonAceptar);

					botonAceptar.addActionListener(ev -> caracteresDialog.dispose());
					caracteresDialog.setVisible(true);
					return;
				}

				// Validación del teléfono (solo números)
				if (!telefono.matches("[0-9]+")) {
					JDialog telefonoDialog = new JDialog(parentFrame, "Advertencia", true);
					telefonoDialog.setSize(400, 180);
					telefonoDialog.setLayout(null);
					telefonoDialog.setUndecorated(true);
					telefonoDialog.setLocationRelativeTo(frame);

					JPanel panel = new JPanel();
					panel.setBackground(Color.WHITE);
					panel.setBounds(0, 0, 400, 180);
					panel.setLayout(null);
					telefonoDialog.add(panel);

					JPanel panelSuperior = new JPanel();
					panelSuperior.setBackground(new Color(81, 151, 255));
					panelSuperior.setBounds(0, 0, 400, 33);
					panel.add(panelSuperior);

					JLabel mensaje = new JLabel(
							"<html><div style='text-align: center;'>El teléfono solo debe contener números.</div></html>");
					mensaje.setFont(new Font("Anton", Font.PLAIN, 16));
					mensaje.setBounds(50, 44, 300, 59);
					panel.add(mensaje);

					JButton botonAceptar = new JButton("Aceptar");
					botonAceptar.setBackground(new Color(0, 206, 82));
					botonAceptar.setForeground(Color.WHITE);
					botonAceptar.setFont(new Font("Anton", Font.PLAIN, 14));
					botonAceptar.setBounds(151, 121, 102, 33);
					panel.add(botonAceptar);

					botonAceptar.addActionListener(ev -> telefonoDialog.dispose());
					telefonoDialog.setVisible(true);
					return;
				}

				// Validación del correo (solo @gmail.com o @hotmail.com)
				if (!correo.matches("^[a-zA-Z0-9._-]+@(gmail|hotmail)\\.com$")) {
					JDialog correoDialog = new JDialog(parentFrame, "Advertencia", true);
					correoDialog.setSize(400, 180);
					correoDialog.setLayout(null);
					correoDialog.setUndecorated(true);
					correoDialog.setLocationRelativeTo(frame);

					JPanel panel = new JPanel();
					panel.setBackground(Color.WHITE);
					panel.setBounds(0, 0, 400, 180);
					panel.setLayout(null);
					correoDialog.add(panel);

					JPanel panelSuperior = new JPanel();
					panelSuperior.setBackground(new Color(81, 151, 255));
					panelSuperior.setBounds(0, 0, 400, 33);
					panel.add(panelSuperior);

					JLabel mensaje = new JLabel(
							"<html><div style='text-align: center;'>El correo debe ser @gmail.com o @hotmail.com</div></html>");
					mensaje.setFont(new Font("Anton", Font.PLAIN, 16));
					mensaje.setBounds(50, 44, 300, 59);
					panel.add(mensaje);

					JButton botonAceptar = new JButton("Aceptar");
					botonAceptar.setBackground(new Color(0, 206, 82));
					botonAceptar.setForeground(Color.WHITE);
					botonAceptar.setFont(new Font("Anton", Font.PLAIN, 14));
					botonAceptar.setBounds(151, 121, 102, 33);
					panel.add(botonAceptar);

					botonAceptar.addActionListener(ev -> correoDialog.dispose());
					correoDialog.setVisible(true);
					return;
				}

				// Validación de contraseñas
				if (!contrasena.equals(confirmarContrasena)) {
					JDialog contrasenaDialog = new JDialog(parentFrame, "Advertencia", true);
					contrasenaDialog.setSize(400, 180);
					contrasenaDialog.setLayout(null);
					contrasenaDialog.setUndecorated(true);
					contrasenaDialog.setLocationRelativeTo(frame);

					JPanel panel = new JPanel();
					panel.setBackground(Color.WHITE);
					panel.setBounds(0, 0, 400, 180);
					panel.setLayout(null);
					contrasenaDialog.add(panel);

					JPanel panelSuperior = new JPanel();
					panelSuperior.setBackground(new Color(81, 151, 255));
					panelSuperior.setBounds(0, 0, 400, 33);
					panel.add(panelSuperior);

					JLabel mensaje = new JLabel(
							"<html><div style='text-align: center;'>Las contraseñas no coinciden<br></div></html>");
					mensaje.setFont(new Font("Anton", Font.PLAIN, 16));
					mensaje.setBounds(85, 44, 300, 59);
					panel.add(mensaje);

					JButton botonAceptar = new JButton("Aceptar");
					botonAceptar.setBackground(new Color(0, 206, 82));
					botonAceptar.setForeground(Color.WHITE);
					botonAceptar.setFont(new Font("Anton", Font.PLAIN, 14));
					botonAceptar.setBounds(151, 121, 102, 33);
					panel.add(botonAceptar);

					botonAceptar.addActionListener(ev -> contrasenaDialog.dispose());
					contrasenaDialog.setVisible(true);
					return;
				}

				UsersModel um = new UsersModel();
				int idUsuario = um.registrarCliente(nombre, primerApellido,
						segundoApellido.isEmpty() ? null : segundoApellido, telefono.isEmpty() ? null : telefono,
						correo, contrasena, tarifaSeleccionada);

				if (idUsuario != -1) {
					// Éxito - Cliente registrado correctamente
					JDialog exitoDialog = new JDialog(parentFrame, "Éxito", true);
					exitoDialog.setSize(400, 180);
					exitoDialog.setLayout(null);
					exitoDialog.setUndecorated(true);
					exitoDialog.setLocationRelativeTo(frame);

					JPanel panelExito = new JPanel();
					panelExito.setBackground(Color.WHITE);
					panelExito.setBounds(0, 0, 400, 180);
					panelExito.setLayout(null);
					exitoDialog.add(panelExito);

					JPanel panelSuperior = new JPanel();
					panelSuperior.setBackground(new Color(81, 151, 255));
					panelSuperior.setBounds(0, 0, 400, 33);
					panelExito.add(panelSuperior);

					JLabel mensajeExito = new JLabel(
							"<html><div style='text-align: center;'>Cliente registrado correctamente<br></div></html>");
					mensajeExito.setFont(new Font("Anton", Font.PLAIN, 16));
					mensajeExito.setBounds(70, 44, 300, 59);
					panelExito.add(mensajeExito);

					JButton botonAceptar = new JButton("Aceptar");
					botonAceptar.setBackground(new Color(0, 206, 82));
					botonAceptar.setForeground(Color.WHITE);
					botonAceptar.setFont(new Font("Anton", Font.PLAIN, 14));
					botonAceptar.setBounds(151, 121, 102, 33);
					panelExito.add(botonAceptar);

					botonAceptar.addActionListener(ev -> {
						exitoDialog.dispose();
						frame.dispose();
						new HomeController().Clientes();
					});

					exitoDialog.setVisible(true);
				} else {
					// Error al registrar el cliente
					JDialog errorDialog = new JDialog(parentFrame, "Error", true);
					errorDialog.setSize(400, 180);
					errorDialog.setLayout(null);
					errorDialog.setUndecorated(true);
					errorDialog.setLocationRelativeTo(frame);

					JPanel panelError = new JPanel();
					panelError.setBackground(Color.WHITE);
					panelError.setBounds(0, 0, 400, 180);
					panelError.setLayout(null);
					errorDialog.add(panelError);

					JPanel panelSuperior = new JPanel();
					panelSuperior.setBackground(new Color(81, 151, 255));
					panelSuperior.setBounds(0, 0, 400, 33);
					panelError.add(panelSuperior);

					JLabel mensajeError = new JLabel(
							"<html><div style='text-align: center;'>Error al registrar el cliente<br></div></html>");
					mensajeError.setFont(new Font("Anton", Font.PLAIN, 16));
					mensajeError.setBounds(70, 44, 300, 59);
					panelError.add(mensajeError);

					JButton botonAceptar = new JButton("Aceptar");
					botonAceptar.setBackground(new Color(0, 206, 82));
					botonAceptar.setForeground(Color.WHITE);
					botonAceptar.setFont(new Font("Anton", Font.PLAIN, 14));
					botonAceptar.setBounds(151, 121, 102, 33);
					panelError.add(botonAceptar);

					botonAceptar.addActionListener(ev -> errorDialog.dispose());
					errorDialog.setVisible(true);
				}
			}
		});
		panel_2.add(boton_registar);

		JButton boton_cancelar = new JButton("Cancelar / volver");
		boton_cancelar.setForeground(new Color(255, 255, 255));
		boton_cancelar.setBackground(new Color(255, 0, 0));
		boton_cancelar.setFont(new Font("Anton", Font.PLAIN, 14));
		boton_cancelar.setBounds(556, 483, 161, 50);
		boton_cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Clientes();
			}
		});
		panel_2.add(boton_cancelar);

		JButton cargar_foto = new JButton("Cargar foto");
		cargar_foto.setBackground(new Color(255, 205, 17));
		cargar_foto.setFont(new Font("Anton", Font.PLAIN, 12));
		cargar_foto.setBounds(15, 296, 132, 32);
		panel_2.add(cargar_foto);

		// Botones laterales
		// //////////////////////////////////////////////////////////////////////////////////////////////
		JButton boton_INICIO = new JButton("INICIO");// boton de inicio
		boton_INICIO.setBackground(new Color(255, 205, 17));
		boton_INICIO.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_INICIO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HomeController hc = new HomeController();
				hc.Panel_inicio();

			}
		});
		boton_INICIO.setBounds(10, 86, 136, 71);
		panel.add(boton_INICIO);

		JButton boton_CLIENTES = new JButton("CLIENTES");// boton de clientes
		boton_CLIENTES.setBackground(new Color(255, 255, 255));
		boton_CLIENTES.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CLIENTES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Clientes();
			}
		});
		boton_CLIENTES.setBounds(10, 168, 136, 71);
		panel.add(boton_CLIENTES);

		JButton boton_TARIFAS = new JButton("TARIFAS");// boton de tarifas
		boton_TARIFAS.setBackground(new Color(255, 205, 17));
		boton_TARIFAS.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_TARIFAS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Tarifas();
			}
		});
		boton_TARIFAS.setBounds(10, 250, 136, 71);
		panel.add(boton_TARIFAS);

		JButton boton_INSTRUCTORES = new JButton("INSTRUCTORES");// boton de instructores
		boton_INSTRUCTORES.setBackground(new Color(255, 205, 17));
		boton_INSTRUCTORES.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_INSTRUCTORES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Instructores();
			}
		});
		boton_INSTRUCTORES.setBounds(10, 332, 136, 71);
		panel.add(boton_INSTRUCTORES);

		JButton boton_CLASES = new JButton("CLASES");// boton de clases
		boton_CLASES.setBackground(new Color(255, 205, 17));
		boton_CLASES.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CLASES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Clases();
			}
		});
		boton_CLASES.setBounds(10, 414, 136, 71);
		panel.add(boton_CLASES);

		JButton boton_CHECADOR = new JButton("CHECADOR");// boton de checador
		boton_CHECADOR.setBackground(new Color(255, 205, 17));
		boton_CHECADOR.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CHECADOR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Panel_checador();
			}
		});
		boton_CHECADOR.setBounds(10, 496, 136, 71);
		panel.add(boton_CHECADOR);

		JButton boton_CERRAR_SESION = new JButton("CERRAR SESION");
		boton_CERRAR_SESION.setBackground(new Color(255, 205, 17));
		boton_CERRAR_SESION.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CERRAR_SESION.addActionListener(e -> {
			JDialog dialog = new JDialog(frame, "Cerrar sesión", true);
			dialog.setSize(400, 220);
			dialog.setLocationRelativeTo(frame);
			dialog.setUndecorated(true);
			dialog.setLayout(null);

			// Panel principal - AHORA CON TAMAÑO COMPLETO
			JPanel Cerrar_sesion = new JPanel();
			Cerrar_sesion.setBackground(new Color(255, 255, 255));
			Cerrar_sesion.setBounds(0, 0, 400, 220);
			Cerrar_sesion.setLayout(null);
			dialog.add(Cerrar_sesion);

			// Panel superior azul
			JPanel panel_complemento = new JPanel();
			panel_complemento.setBackground(new Color(81, 151, 255));
			panel_complemento.setBounds(0, 0, 400, 33);
			Cerrar_sesion.add(panel_complemento);

			// Etiqueta con la pregunta
			JLabel pregunta_de_confirmacion = new JLabel(
					"<html><div style='text-align: center;'>Está a punto de cerrar sesión<br>¿Desea continuar?</div></html>");
			pregunta_de_confirmacion.setFont(new Font("Anton", Font.PLAIN, 16));
			pregunta_de_confirmacion.setBounds(90, 44, 310, 60);
			Cerrar_sesion.add(pregunta_de_confirmacion);

			// Botón Cancelar
			JButton boton_cancelar_alerta = new JButton("Cancelar");
			boton_cancelar_alerta.setForeground(Color.WHITE);
			boton_cancelar_alerta.setFont(new Font("Anton", Font.PLAIN, 14));
			boton_cancelar_alerta.setBackground(Color.RED);
			boton_cancelar_alerta.setBounds(50, 140, 120, 35);
			Cerrar_sesion.add(boton_cancelar_alerta);

			// Botón Aceptar
			JButton boton_aceptar = new JButton("Aceptar");
			boton_aceptar.setBackground(new Color(0, 206, 82));
			boton_aceptar.setForeground(Color.WHITE);
			boton_aceptar.setFont(new Font("Anton", Font.PLAIN, 14));
			boton_aceptar.setBounds(230, 140, 120, 35);
			Cerrar_sesion.add(boton_aceptar);

			// Acciones de los botones
			boton_cancelar.addActionListener(ev -> dialog.dispose());
			boton_aceptar.addActionListener(ev -> {
				dialog.dispose();
				closeSession(frame);
			});

			dialog.setVisible(true);
		});
		boton_CERRAR_SESION.setBounds(10, 579, 136, 71);
		panel.add(boton_CERRAR_SESION);

		frame.add(panel);
		frame.repaint();
		frame.revalidate();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public void Editar_tarifas() {
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
		panel_1.setBounds(0, 0, 1084, 75);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(10, 11, 53, 53);
		ImageIcon icon = new ImageIcon(getClass().getResource("/Imagenes/logo sin letras.png"));
		Image imagen = icon.getImage().getScaledInstance(53, 53, Image.SCALE_SMOOTH);
		lblNewLabel_1.setIcon(new ImageIcon(imagen));
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

		JLabel lblNewLabel = new JLabel("EDITAR TARIFAS Y DESCRIPCION");// titulo de inicio
		lblNewLabel.setFont(new Font("Anton", Font.PLAIN, 26));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(60, 11, 436, 28);
		panel_3.add(lblNewLabel);
		// tarifas//////////////////////////////////////////////////////////////////////
		JPanel plan_estandar = new JPanel();
		plan_estandar.setBackground(new Color(255, 255, 255));
		plan_estandar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		plan_estandar.setBounds(30, 81, 275, 185);
		panel_2.add(plan_estandar);
		plan_estandar.setLayout(null);

		JLabel lblNewLabel_5 = new JLabel("Plan");
		lblNewLabel_5.setFont(new Font("Anton", Font.PLAIN, 22));
		lblNewLabel_5.setBounds(20, 26, 100, 35);
		plan_estandar.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("ESTANDAR");
		lblNewLabel_6.setFont(new Font("Anton", Font.PLAIN, 28));
		lblNewLabel_6.setBounds(20, 72, 172, 35);
		plan_estandar.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("$300 / mes");
		lblNewLabel_7.setFont(new Font("Anton", Font.PLAIN, 28));
		lblNewLabel_7.setBounds(20, 118, 146, 35);
		plan_estandar.add(lblNewLabel_7);

		JPanel plan_premium = new JPanel();
		plan_premium.setBackground(new Color(255, 255, 255));
		plan_premium.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		plan_premium.setBounds(323, 81, 275, 185);
		panel_2.add(plan_premium);
		plan_premium.setLayout(null);

		JLabel lblNewLabel_8 = new JLabel("Plan");
		lblNewLabel_8.setFont(new Font("Anton", Font.PLAIN, 22));
		lblNewLabel_8.setBounds(20, 26, 100, 35);
		plan_premium.add(lblNewLabel_8);

		JLabel lblNewLabel_9 = new JLabel("PREMIUM");
		lblNewLabel_9.setFont(new Font("Anton", Font.PLAIN, 28));
		lblNewLabel_9.setBounds(20, 72, 172, 35);
		plan_premium.add(lblNewLabel_9);

		JLabel lblNewLabel_10 = new JLabel("$600 / mes");
		lblNewLabel_10.setFont(new Font("Anton", Font.PLAIN, 28));
		lblNewLabel_10.setBounds(20, 118, 146, 35);
		plan_premium.add(lblNewLabel_10);

		JPanel plan_familiar = new JPanel();
		plan_familiar.setBackground(new Color(255, 255, 255));
		plan_familiar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		plan_familiar.setBounds(615, 81, 275, 185);
		panel_2.add(plan_familiar);
		plan_familiar.setLayout(null);

		JLabel lblNewLabel_11 = new JLabel("Plan");
		lblNewLabel_11.setFont(new Font("Anton", Font.PLAIN, 22));
		lblNewLabel_11.setBounds(20, 26, 100, 35);
		plan_familiar.add(lblNewLabel_11);

		JLabel lblNewLabel_12 = new JLabel("FAMILIAR");
		lblNewLabel_12.setFont(new Font("Anton", Font.PLAIN, 28));
		lblNewLabel_12.setBounds(20, 72, 172, 35);
		plan_familiar.add(lblNewLabel_12);

		JLabel lblNewLabel_13 = new JLabel("$1099 / mes");
		lblNewLabel_13.setFont(new Font("Anton", Font.PLAIN, 28));
		lblNewLabel_13.setBounds(20, 118, 146, 35);
		plan_familiar.add(lblNewLabel_13);

		// botones de
		// tarifas///////////////////////////////////////////////////////////////////////////////
		JButton boton_inf_plan_estandar = new JButton("");
		boton_inf_plan_estandar.setBackground(new Color(255, 205, 17));
		boton_inf_plan_estandar.setBounds(205, 0, 35, 35);
		boton_inf_plan_estandar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		ImageIcon icon1 = new ImageIcon(getClass().getResource("/Imagenes/editar.png"));
		Image imagen1 = icon1.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		boton_inf_plan_estandar.setIcon(new ImageIcon(imagen1));
		boton_inf_plan_estandar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				UsersController uc = new UsersController();
				uc.Editar_tarifas2();
			}
		});
		plan_estandar.add(boton_inf_plan_estandar);

		JButton boton_eliminar_instcibsion_estandar = new JButton("");
		boton_eliminar_instcibsion_estandar.setBackground(new Color(255, 0, 0));
		boton_eliminar_instcibsion_estandar.setBounds(240, 0, 35, 35);
		boton_eliminar_instcibsion_estandar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		ImageIcon icon4 = new ImageIcon(getClass().getResource("/Imagenes/eliminar.png"));
		Image imagen4 = icon4.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		boton_eliminar_instcibsion_estandar.setIcon(new ImageIcon(imagen4));
		boton_eliminar_instcibsion_estandar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JDialog dialog = new JDialog((Frame) null, "Confirmar Eliminación", true);
				dialog.setSize(400, 220);
				dialog.setLocationRelativeTo(null);
				dialog.setUndecorated(true);
				dialog.setLayout(null);

				// Panel principal de la alerta
				JPanel confirmar_eliminacion = new JPanel();
				confirmar_eliminacion.setBackground(new Color(255, 255, 255));
				confirmar_eliminacion.setBounds(0, 0, 400, 220);
				confirmar_eliminacion.setLayout(null);
				dialog.add(confirmar_eliminacion);

				// Panel superior de la alerta (azul)
				JPanel panel_complemento = new JPanel();
				panel_complemento.setBackground(new Color(81, 151, 255));
				panel_complemento.setBounds(0, 0, 400, 33);
				confirmar_eliminacion.add(panel_complemento);

				// Etiqueta de confirmación
				JLabel pregunta_de_confirmacion = new JLabel(
						"<html><div style='text-align: center;'>La tarifa se borrará permanentemente<br>¿Desea continuar?</div></html>");
				pregunta_de_confirmacion.setFont(new Font("Anton", Font.PLAIN, 16));
				pregunta_de_confirmacion.setBounds(66, 44, 346, 59);
				confirmar_eliminacion.add(pregunta_de_confirmacion);

				// Botón "Cancelar"
				JButton boton_cancelar_alerta = new JButton("Cancelar");
				boton_cancelar_alerta.setForeground(Color.WHITE);
				boton_cancelar_alerta.setFont(new Font("Anton", Font.PLAIN, 14));
				boton_cancelar_alerta.setBackground(Color.RED);
				boton_cancelar_alerta.setBounds(50, 140, 120, 35);
				confirmar_eliminacion.add(boton_cancelar_alerta);

				// Botón "Aceptar"
				JButton boton_aceptar = new JButton("Aceptar");
				boton_aceptar.setBackground(new Color(0, 206, 82));
				boton_aceptar.setForeground(Color.WHITE);
				boton_aceptar.setFont(new Font("Anton", Font.PLAIN, 14));
				boton_aceptar.setBounds(230, 140, 120, 35);
				confirmar_eliminacion.add(boton_aceptar);

				// Acción al hacer clic en "Cancelar"
				boton_cancelar_alerta.addActionListener(ev -> dialog.dispose());

				// Acción al hacer clic en "Aceptar"
				boton_aceptar.addActionListener(ev -> {
					dialog.dispose();

				});

				dialog.setVisible(true);
			}
		});

		plan_estandar.add(boton_eliminar_instcibsion_estandar);

		JButton boton_inf_plan_premium = new JButton("");
		boton_inf_plan_premium.setBackground(new Color(255, 205, 17));
		boton_inf_plan_premium.setBounds(205, 0, 35, 35);
		boton_inf_plan_premium.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		ImageIcon icon2 = new ImageIcon(getClass().getResource("/Imagenes/editar.png"));
		Image imagen2 = icon2.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		boton_inf_plan_premium.setIcon(new ImageIcon(imagen2));
		boton_inf_plan_premium.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				UsersController uc = new UsersController();
				uc.Editar_tarifas_PREMIUM();
			}
		});
		plan_premium.add(boton_inf_plan_premium);

		JButton boton_eliminar_instcibsion_premium = new JButton("");
		boton_eliminar_instcibsion_premium.setBackground(new Color(255, 0, 0));
		boton_eliminar_instcibsion_premium.setBounds(240, 0, 35, 35);
		boton_eliminar_instcibsion_premium.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		ImageIcon icon5 = new ImageIcon(getClass().getResource("/Imagenes/eliminar.png"));
		Image imagen5 = icon5.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		boton_eliminar_instcibsion_premium.setIcon(new ImageIcon(imagen5));
		boton_eliminar_instcibsion_premium.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JDialog dialog = new JDialog((Frame) null, "Confirmar Eliminación", true);
				dialog.setSize(400, 220);
				dialog.setLocationRelativeTo(null);
				dialog.setUndecorated(true);
				dialog.setLayout(null);

				// Panel principal de la alerta
				JPanel confirmar_eliminacion = new JPanel();
				confirmar_eliminacion.setBackground(new Color(255, 255, 255));
				confirmar_eliminacion.setBounds(0, 0, 400, 220);
				confirmar_eliminacion.setLayout(null);
				dialog.add(confirmar_eliminacion);

				// Panel superior de la alerta (azul)
				JPanel panel_complemento = new JPanel();
				panel_complemento.setBackground(new Color(81, 151, 255));
				panel_complemento.setBounds(0, 0, 400, 33);
				confirmar_eliminacion.add(panel_complemento);

				// Etiqueta de confirmación
				JLabel pregunta_de_confirmacion = new JLabel(
						"<html><div style='text-align: center;'>La tarifa se borrará permanentemente<br>¿Desea continuar?</div></html>");
				pregunta_de_confirmacion.setFont(new Font("Anton", Font.PLAIN, 16));
				pregunta_de_confirmacion.setBounds(66, 44, 346, 59);
				confirmar_eliminacion.add(pregunta_de_confirmacion);

				// Botón "Cancelar"
				JButton boton_cancelar_alerta = new JButton("Cancelar");
				boton_cancelar_alerta.setForeground(Color.WHITE);
				boton_cancelar_alerta.setFont(new Font("Anton", Font.PLAIN, 14));
				boton_cancelar_alerta.setBackground(Color.RED);
				boton_cancelar_alerta.setBounds(50, 140, 120, 35);
				confirmar_eliminacion.add(boton_cancelar_alerta);

				// Botón "Aceptar"
				JButton boton_aceptar = new JButton("Aceptar");
				boton_aceptar.setBackground(new Color(0, 206, 82));
				boton_aceptar.setForeground(Color.WHITE);
				boton_aceptar.setFont(new Font("Anton", Font.PLAIN, 14));
				boton_aceptar.setBounds(230, 140, 120, 35);
				confirmar_eliminacion.add(boton_aceptar);

				// Acción al hacer clic en "Cancelar"
				boton_cancelar_alerta.addActionListener(ev -> dialog.dispose());

				// Acción al hacer clic en "Aceptar"
				boton_aceptar.addActionListener(ev -> {
					dialog.dispose();

				});

				dialog.setVisible(true);
			}
		});
		plan_premium.add(boton_eliminar_instcibsion_premium);

		JButton boton_inf_plan_familiar = new JButton("");
		boton_inf_plan_familiar.setBackground(new Color(255, 205, 17));
		boton_inf_plan_familiar.setBounds(205, 0, 35, 35);
		boton_inf_plan_familiar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		ImageIcon icon3 = new ImageIcon(getClass().getResource("/Imagenes/editar.png"));
		Image imagen3 = icon3.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		boton_inf_plan_familiar.setIcon(new ImageIcon(imagen3));
		boton_inf_plan_familiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				UsersController uc = new UsersController();
				uc.Editar_tarifas_FAMILIAR();
			}
		});
		plan_familiar.add(boton_inf_plan_familiar);

		JButton boton_eliminar_instcibsion_familiar = new JButton("");
		boton_eliminar_instcibsion_familiar.setBackground(new Color(255, 0, 0));
		boton_eliminar_instcibsion_familiar.setBounds(240, 0, 35, 35);
		boton_eliminar_instcibsion_familiar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		ImageIcon icon6 = new ImageIcon(getClass().getResource("/Imagenes/eliminar.png"));
		Image imagen6 = icon6.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		boton_eliminar_instcibsion_familiar.setIcon(new ImageIcon(imagen6));
		boton_eliminar_instcibsion_familiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JDialog dialog = new JDialog((Frame) null, "Confirmar Eliminación", true);
				dialog.setSize(400, 220);
				dialog.setLocationRelativeTo(null);
				dialog.setUndecorated(true);
				dialog.setLayout(null);

				// Panel principal de la alerta
				JPanel confirmar_eliminacion = new JPanel();
				confirmar_eliminacion.setBackground(new Color(255, 255, 255));
				confirmar_eliminacion.setBounds(0, 0, 400, 220);
				confirmar_eliminacion.setLayout(null);
				dialog.add(confirmar_eliminacion);

				// Panel superior de la alerta (azul)
				JPanel panel_complemento = new JPanel();
				panel_complemento.setBackground(new Color(81, 151, 255));
				panel_complemento.setBounds(0, 0, 400, 33);
				confirmar_eliminacion.add(panel_complemento);

				// Etiqueta de confirmación
				JLabel pregunta_de_confirmacion = new JLabel(
						"<html><div style='text-align: center;'>La tarifa se borrará permanentemente<br>¿Desea continuar?</div></html>");
				pregunta_de_confirmacion.setFont(new Font("Anton", Font.PLAIN, 16));
				pregunta_de_confirmacion.setBounds(66, 44, 346, 59);
				confirmar_eliminacion.add(pregunta_de_confirmacion);

				// Botón "Cancelar"
				JButton boton_cancelar_alerta = new JButton("Cancelar");
				boton_cancelar_alerta.setForeground(Color.WHITE);
				boton_cancelar_alerta.setFont(new Font("Anton", Font.PLAIN, 14));
				boton_cancelar_alerta.setBackground(Color.RED);
				boton_cancelar_alerta.setBounds(50, 140, 120, 35);
				confirmar_eliminacion.add(boton_cancelar_alerta);

				// Botón "Aceptar"
				JButton boton_aceptar = new JButton("Aceptar");
				boton_aceptar.setBackground(new Color(0, 206, 82));
				boton_aceptar.setForeground(Color.WHITE);
				boton_aceptar.setFont(new Font("Anton", Font.PLAIN, 14));
				boton_aceptar.setBounds(230, 140, 120, 35);
				confirmar_eliminacion.add(boton_aceptar);

				// Acción al hacer clic en "Cancelar"
				boton_cancelar_alerta.addActionListener(ev -> dialog.dispose());

				// Acción al hacer clic en "Aceptar"
				boton_aceptar.addActionListener(ev -> {
					dialog.dispose();

				});

				dialog.setVisible(true);
			}
		});
		plan_familiar.add(boton_eliminar_instcibsion_familiar);

		JButton boton_editar_tarifas = new JButton("Cancelar");
		boton_editar_tarifas.setForeground(new Color(255, 255, 255));
		boton_editar_tarifas.setBackground(new Color(255, 0, 0));
		boton_editar_tarifas.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_editar_tarifas.setBounds(762, 503, 128, 50);
		boton_editar_tarifas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Tarifas();
			}
		});
		panel_2.add(boton_editar_tarifas);

		JButton boton_agregar_tarifa = new JButton("+");
		boton_agregar_tarifa.setForeground(new Color(204, 204, 204));
		boton_agregar_tarifa.setFont(new Font("Anton", Font.PLAIN, 98));
		boton_agregar_tarifa.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
		boton_agregar_tarifa.setBounds(30, 307, 275, 185);
		boton_agregar_tarifa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				UsersController uc = new UsersController();
				uc.Añadir_tarifa();
			}
		});
		panel_2.add(boton_agregar_tarifa);

		// botones de los
		// laterales/////////////////////////////////////////////////////////////////////////////////////////////////////
		JButton boton_INICIO = new JButton("INICIO");// boton de inicio
		boton_INICIO.setBackground(new Color(255, 205, 17));
		boton_INICIO.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_INICIO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Panel_inicio();
			}
		});
		boton_INICIO.setBounds(10, 86, 136, 71);
		panel.add(boton_INICIO);

		JButton boton_CLIENTES = new JButton("CLIENTES");// boton de clientes
		boton_CLIENTES.setBackground(new Color(255, 205, 17));
		boton_CLIENTES.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CLIENTES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Clientes();
			}
		});
		boton_CLIENTES.setBounds(10, 168, 136, 71);
		panel.add(boton_CLIENTES);

		JButton boton_TARIFAS = new JButton("TARIFAS");// boton de tarifas
		boton_TARIFAS.setBackground(new Color(255, 255, 255));
		boton_TARIFAS.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_TARIFAS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		boton_TARIFAS.setBounds(10, 250, 136, 71);
		panel.add(boton_TARIFAS);

		JButton boton_INSTRUCTORES = new JButton("INSTRUCTORES");// boton de instructores
		boton_INSTRUCTORES.setBackground(new Color(255, 205, 17));
		boton_INSTRUCTORES.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_INSTRUCTORES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Instructores();
			}
		});
		boton_INSTRUCTORES.setBounds(10, 332, 136, 71);
		panel.add(boton_INSTRUCTORES);

		JButton boton_CLASES = new JButton("CLASES");// boton de clases
		boton_CLASES.setBackground(new Color(255, 205, 17));
		boton_CLASES.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CLASES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Clases();
			}
		});
		boton_CLASES.setBounds(10, 414, 136, 71);
		panel.add(boton_CLASES);

		JButton boton_CHECADOR = new JButton("CHECADOR");// boton de checador
		boton_CHECADOR.setBackground(new Color(255, 205, 17));
		boton_CHECADOR.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CHECADOR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Panel_checador();
			}
		});
		boton_CHECADOR.setBounds(10, 496, 136, 71);
		panel.add(boton_CHECADOR);

		JButton boton_CERRAR_SESION = new JButton("CERRAR SESION");
		boton_CERRAR_SESION.setBackground(new Color(255, 205, 17));
		boton_CERRAR_SESION.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CERRAR_SESION.addActionListener(e -> {
			JDialog dialog = new JDialog(frame, "Cerrar sesión", true);
			dialog.setSize(400, 220);
			dialog.setLocationRelativeTo(frame);
			dialog.setUndecorated(true);
			dialog.setLayout(null);

			// Panel principal - AHORA CON TAMAÑO COMPLETO
			JPanel Cerrar_sesion = new JPanel();
			Cerrar_sesion.setBackground(new Color(255, 255, 255));
			Cerrar_sesion.setBounds(0, 0, 400, 220);
			Cerrar_sesion.setLayout(null);
			dialog.add(Cerrar_sesion);

			// Panel superior azul
			JPanel panel_complemento = new JPanel();
			panel_complemento.setBackground(new Color(81, 151, 255));
			panel_complemento.setBounds(0, 0, 400, 33);
			Cerrar_sesion.add(panel_complemento);

			// Etiqueta con la pregunta
			JLabel pregunta_de_confirmacion = new JLabel(
					"<html><div style='text-align: center;'>Está a punto de cerrar sesión<br>¿Desea continuar?</div></html>");
			pregunta_de_confirmacion.setFont(new Font("Anton", Font.PLAIN, 16));
			pregunta_de_confirmacion.setBounds(90, 44, 310, 60);
			Cerrar_sesion.add(pregunta_de_confirmacion);

			// Botón Cancelar
			JButton boton_cancelar = new JButton("Cancelar");
			boton_cancelar.setForeground(Color.WHITE);
			boton_cancelar.setFont(new Font("Anton", Font.PLAIN, 14));
			boton_cancelar.setBackground(Color.RED);
			boton_cancelar.setBounds(50, 140, 120, 35);
			Cerrar_sesion.add(boton_cancelar);

			// Botón Aceptar
			JButton boton_aceptar = new JButton("Aceptar");
			boton_aceptar.setBackground(new Color(0, 206, 82));
			boton_aceptar.setForeground(Color.WHITE);
			boton_aceptar.setFont(new Font("Anton", Font.PLAIN, 14));
			boton_aceptar.setBounds(230, 140, 120, 35);
			Cerrar_sesion.add(boton_aceptar);

			// Acciones de los botones
			boton_cancelar.addActionListener(ev -> dialog.dispose());
			boton_aceptar.addActionListener(ev -> {
				dialog.dispose();
				closeSession(frame);
			});

			dialog.setVisible(true);
		});
		boton_CERRAR_SESION.setBounds(10, 579, 136, 71);
		panel.add(boton_CERRAR_SESION);

		frame.add(panel);
		frame.repaint();
		frame.revalidate();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public void Editar_tarifas_PREMIUM() {
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
		panel_1.setBounds(0, 0, 1084, 75);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(10, 11, 53, 53);
		ImageIcon icon = new ImageIcon(getClass().getResource("/Imagenes/logo sin letras.png"));
		Image imagen = icon.getImage().getScaledInstance(53, 53, Image.SCALE_SMOOTH);
		lblNewLabel_1.setIcon(new ImageIcon(imagen));
		panel_1.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("EVOLVEFIT");
		lblNewLabel_2.setFont(new Font("Anton", Font.PLAIN, 30));
		lblNewLabel_2.setBounds(73, 11, 324, 35);
		panel_1.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("HEALTH & FITNESS");
		lblNewLabel_3.setFont(new Font("Anton", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(73, 42, 281, 22);
		panel_1.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Panel administrativo");
		lblNewLabel_4.setFont(new Font("Anton", Font.PLAIN, 32));
		lblNewLabel_4.setBounds(407, 11, 560, 53);
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

		JLabel lblNewLabel = new JLabel("DESCRIPCION");// titulo de inicio
		lblNewLabel.setFont(new Font("Anton", Font.PLAIN, 26));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(60, 11, 223, 28);
		panel_3.add(lblNewLabel);
		// tarifas//////////////////////////////////////////////////////////////////////
		JPanel plan_estandar = new JPanel();
		plan_estandar.setBackground(new Color(255, 255, 255));
		plan_estandar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		plan_estandar.setBounds(40, 150, 275, 185);
		panel_2.add(plan_estandar);
		plan_estandar.setLayout(null);

		JLabel lblNewLabel_5 = new JLabel("Plan");
		lblNewLabel_5.setFont(new Font("Anton", Font.PLAIN, 22));
		lblNewLabel_5.setBounds(20, 26, 100, 35);
		plan_estandar.add(lblNewLabel_5);

		JTextField lblNewLabel_6 = new JTextField("PREMIUM");
		lblNewLabel_6.setBackground(new Color(204, 204, 204));
		lblNewLabel_6.setFont(new Font("Anton", Font.PLAIN, 28));
		lblNewLabel_6.setBounds(20, 72, 172, 35);
		plan_estandar.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("$");
		lblNewLabel_7.setFont(new Font("Anton", Font.PLAIN, 28));
		lblNewLabel_7.setBounds(20, 118, 13, 35);
		plan_estandar.add(lblNewLabel_7);

		JPanel descripcion_plan = new JPanel();
		descripcion_plan.setBackground(new Color(255, 255, 255));
		descripcion_plan.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		descripcion_plan.setBounds(325, 150, 565, 263);
		panel_2.add(descripcion_plan);
		descripcion_plan.setLayout(null);

		JLabel lblNewLabel_8 = new JLabel("Plan:");
		lblNewLabel_8.setFont(new Font("Anton", Font.PLAIN, 22));
		lblNewLabel_8.setBounds(20, 26, 44, 35);
		descripcion_plan.add(lblNewLabel_8);

		JTextField txtEstandar = new JTextField();
		txtEstandar.setBackground(new Color(204, 204, 204));
		txtEstandar.setFont(new Font("Anton", Font.PLAIN, 22));
		txtEstandar.setText("PREMIUM");
		txtEstandar.setBounds(74, 26, 214, 32);
		descripcion_plan.add(txtEstandar);
		txtEstandar.setColumns(10);

		JTextArea txtrSeIncluyeAcceso = new JTextArea();
		txtrSeIncluyeAcceso.setLineWrap(true);
		txtrSeIncluyeAcceso.setWrapStyleWord(true);
		txtrSeIncluyeAcceso.setFont(new Font("Anton", Font.PLAIN, 17));
		txtrSeIncluyeAcceso.setText("Se incluye acceso completo a todo el equipo y áreas del gimnasio.\r\n"
				+ "Durante su membresía se le aplicara un 25% de descuento al comprar productos de la marca EVOLVEFIT.\r\n"
				+ "Miembros máximos para veneficios: 1\r\n" + "Membrecía mensual con costo de $600.");
		txtrSeIncluyeAcceso.setBackground(new Color(204, 204, 204));
		txtrSeIncluyeAcceso.setBounds(20, 72, 527, 169);
		descripcion_plan.add(txtrSeIncluyeAcceso);
		ImageIcon p1 = new ImageIcon("Imagenes/editar.png");
		Image p2 = p1.getImage();
		Image p3 = p2.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		ImageIcon e1 = new ImageIcon("Imagenes/eliminar.png");
		Image e2 = e1.getImage();
		Image e3 = e2.getScaledInstance(20, 20, Image.SCALE_SMOOTH);

		JTextField textField = new JTextField();
		textField.setBackground(new Color(204, 204, 204));
		textField.setFont(new Font("Anton", Font.PLAIN, 28));
		textField.setText("600");
		textField.setBounds(34, 118, 62, 35);
		plan_estandar.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_9 = new JLabel("/ mes");
		lblNewLabel_9.setFont(new Font("Anton", Font.PLAIN, 28));
		lblNewLabel_9.setBounds(95, 118, 100, 35);
		plan_estandar.add(lblNewLabel_9);
		ImageIcon s1 = new ImageIcon("Imagenes/editar.png");
		Image s2 = s1.getImage();
		Image s3 = s2.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		ImageIcon q1 = new ImageIcon("Imagenes/eliminar.png");
		Image q2 = q1.getImage();
		Image q3 = q2.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		ImageIcon a1 = new ImageIcon("Imagenes/editar.png");
		Image a2 = a1.getImage();
		Image a3 = a2.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		ImageIcon f1 = new ImageIcon("Imagenes/eliminar.png");
		Image f2 = f1.getImage();
		Image f3 = f2.getScaledInstance(20, 20, Image.SCALE_SMOOTH);

		JButton boton_cancelar = new JButton("Cancelar");
		boton_cancelar.setForeground(new Color(255, 255, 255));
		boton_cancelar.setBackground(new Color(255, 0, 0));
		boton_cancelar.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_cancelar.setBounds(625, 503, 128, 50);
		boton_cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				UsersController uc = new UsersController();
				uc.Editar_tarifas();

			}
		});
		panel_2.add(boton_cancelar);

		JButton boton_guardar = new JButton("Guardar");
		boton_guardar.setBackground(new Color(0, 206, 82));
		boton_guardar.setForeground(new Color(255, 255, 255));
		boton_guardar.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_guardar.setBounds(763, 503, 128, 50);
		panel_2.add(boton_guardar);

		// botones de los
		// laterales/////////////////////////////////////////////////////////////////////////////////////////////////////
		JButton boton_INICIO = new JButton("INICIO");// boton de inicio
		boton_INICIO.setBackground(new Color(255, 205, 17));
		boton_INICIO.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_INICIO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Panel_inicio();
			}
		});
		boton_INICIO.setBounds(10, 86, 136, 71);
		panel.add(boton_INICIO);

		JButton boton_CLIENTES = new JButton("CLIENTES");// boton de clientes
		boton_CLIENTES.setBackground(new Color(255, 205, 17));
		boton_CLIENTES.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CLIENTES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Clientes();
			}
		});
		boton_CLIENTES.setBounds(10, 168, 136, 71);
		panel.add(boton_CLIENTES);

		JButton boton_TARIFAS = new JButton("TARIFAS");// boton de tarifas
		boton_TARIFAS.setBackground(new Color(255, 255, 255));
		boton_TARIFAS.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_TARIFAS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		boton_TARIFAS.setBounds(10, 250, 136, 71);
		panel.add(boton_TARIFAS);

		JButton boton_INSTRUCTORES = new JButton("INSTRUCTORES");// boton de instructores
		boton_INSTRUCTORES.setBackground(new Color(255, 205, 17));
		boton_INSTRUCTORES.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_INSTRUCTORES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Instructores();
			}
		});
		boton_INSTRUCTORES.setBounds(10, 332, 136, 71);
		panel.add(boton_INSTRUCTORES);

		JButton boton_CLASES = new JButton("CLASES");// boton de clases
		boton_CLASES.setBackground(new Color(255, 205, 17));
		boton_CLASES.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CLASES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Clases();
			}
		});
		boton_CLASES.setBounds(10, 414, 136, 71);
		panel.add(boton_CLASES);

		JButton boton_CHECADOR = new JButton("CHECADOR");// boton de checador
		boton_CHECADOR.setBackground(new Color(255, 205, 17));
		boton_CHECADOR.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CHECADOR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Panel_checador();
			}
		});
		boton_CHECADOR.setBounds(10, 496, 136, 71);
		panel.add(boton_CHECADOR);

		JButton boton_CERRAR_SESION = new JButton("CERRAR SESION");
		boton_CERRAR_SESION.setBackground(new Color(255, 205, 17));
		boton_CERRAR_SESION.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CERRAR_SESION.addActionListener(e -> {
			JDialog dialog = new JDialog(frame, "Cerrar sesión", true);
			dialog.setSize(400, 220);
			dialog.setLocationRelativeTo(frame);
			dialog.setUndecorated(true);
			dialog.setLayout(null);

			// Panel principal - AHORA CON TAMAÑO COMPLETO
			JPanel Cerrar_sesion = new JPanel();
			Cerrar_sesion.setBackground(new Color(255, 255, 255));
			Cerrar_sesion.setBounds(0, 0, 400, 220);
			Cerrar_sesion.setLayout(null);
			dialog.add(Cerrar_sesion);

			// Panel superior azul
			JPanel panel_complemento = new JPanel();
			panel_complemento.setBackground(new Color(81, 151, 255));
			panel_complemento.setBounds(0, 0, 400, 33);
			Cerrar_sesion.add(panel_complemento);

			// Etiqueta con la pregunta
			JLabel pregunta_de_confirmacion = new JLabel(
					"<html><div style='text-align: center;'>Está a punto de cerrar sesión<br>¿Desea continuar?</div></html>");
			pregunta_de_confirmacion.setFont(new Font("Anton", Font.PLAIN, 16));
			pregunta_de_confirmacion.setBounds(90, 44, 310, 60);
			Cerrar_sesion.add(pregunta_de_confirmacion);

			// Botón Cancelar
			JButton boton_cancelar_alerta = new JButton("Cancelar");
			boton_cancelar_alerta.setForeground(Color.WHITE);
			boton_cancelar_alerta.setFont(new Font("Anton", Font.PLAIN, 14));
			boton_cancelar_alerta.setBackground(Color.RED);
			boton_cancelar_alerta.setBounds(50, 140, 120, 35);
			Cerrar_sesion.add(boton_cancelar_alerta);

			// Botón Aceptar
			JButton boton_aceptar = new JButton("Aceptar");
			boton_aceptar.setBackground(new Color(0, 206, 82));
			boton_aceptar.setForeground(Color.WHITE);
			boton_aceptar.setFont(new Font("Anton", Font.PLAIN, 14));
			boton_aceptar.setBounds(230, 140, 120, 35);
			Cerrar_sesion.add(boton_aceptar);

			// Acciones de los botones
			boton_cancelar.addActionListener(ev -> dialog.dispose());
			boton_aceptar.addActionListener(ev -> {
				dialog.dispose();
				closeSession(frame);
			});

			dialog.setVisible(true);
		});
		boton_CERRAR_SESION.setBounds(10, 579, 136, 71);
		panel.add(boton_CERRAR_SESION);

		frame.add(panel);
		frame.repaint();
		frame.revalidate();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public void Editar_tarifas_FAMILIAR() {
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
		panel_1.setBounds(0, 0, 1084, 75);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(10, 11, 53, 53);
		ImageIcon icon = new ImageIcon(getClass().getResource("/Imagenes/logo sin letras.png"));
		Image imagen = icon.getImage().getScaledInstance(53, 53, Image.SCALE_SMOOTH);
		lblNewLabel_1.setIcon(new ImageIcon(imagen));
		panel_1.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("EVOLVEFIT");
		lblNewLabel_2.setFont(new Font("Anton", Font.PLAIN, 30));
		lblNewLabel_2.setBounds(73, 11, 324, 35);
		panel_1.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("HEALTH & FITNESS");
		lblNewLabel_3.setFont(new Font("Anton", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(73, 42, 281, 22);
		panel_1.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Panel administrativo");
		lblNewLabel_4.setFont(new Font("Anton", Font.PLAIN, 32));
		lblNewLabel_4.setBounds(407, 11, 560, 53);
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

		JLabel lblNewLabel = new JLabel("DESCRIPCION");// titulo de inicio
		lblNewLabel.setFont(new Font("Anton", Font.PLAIN, 26));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(60, 11, 223, 28);
		panel_3.add(lblNewLabel);
		// tarifas//////////////////////////////////////////////////////////////////////
		JPanel plan_estandar = new JPanel();
		plan_estandar.setBackground(new Color(255, 255, 255));
		plan_estandar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		plan_estandar.setBounds(40, 150, 275, 185);
		panel_2.add(plan_estandar);
		plan_estandar.setLayout(null);

		JLabel lblNewLabel_5 = new JLabel("Plan");
		lblNewLabel_5.setFont(new Font("Anton", Font.PLAIN, 22));
		lblNewLabel_5.setBounds(20, 26, 100, 35);
		plan_estandar.add(lblNewLabel_5);

		JTextField lblNewLabel_6 = new JTextField("FAMILIAR");
		lblNewLabel_6.setBackground(new Color(204, 204, 204));
		lblNewLabel_6.setFont(new Font("Anton", Font.PLAIN, 28));
		lblNewLabel_6.setBounds(20, 72, 172, 35);
		plan_estandar.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("$");
		lblNewLabel_7.setFont(new Font("Anton", Font.PLAIN, 28));
		lblNewLabel_7.setBounds(20, 118, 13, 35);
		plan_estandar.add(lblNewLabel_7);

		JPanel descripcion_plan = new JPanel();
		descripcion_plan.setBackground(new Color(255, 255, 255));
		descripcion_plan.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		descripcion_plan.setBounds(325, 150, 565, 263);
		panel_2.add(descripcion_plan);
		descripcion_plan.setLayout(null);

		JLabel lblNewLabel_8 = new JLabel("Plan:");
		lblNewLabel_8.setFont(new Font("Anton", Font.PLAIN, 22));
		lblNewLabel_8.setBounds(20, 26, 44, 35);
		descripcion_plan.add(lblNewLabel_8);

		JTextField txtEstandar = new JTextField();
		txtEstandar.setBackground(new Color(204, 204, 204));
		txtEstandar.setFont(new Font("Anton", Font.PLAIN, 22));
		txtEstandar.setText("FAMILIAR");
		txtEstandar.setBounds(74, 26, 214, 32);
		descripcion_plan.add(txtEstandar);
		txtEstandar.setColumns(10);

		JTextArea txtrSeIncluyeAcceso = new JTextArea();
		txtrSeIncluyeAcceso.setLineWrap(true);
		txtrSeIncluyeAcceso.setWrapStyleWord(true);
		txtrSeIncluyeAcceso.setFont(new Font("Anton", Font.PLAIN, 17));
		txtrSeIncluyeAcceso.setText("Se incluye acceso completo a todo el equipo y áreas del gimnasio.\r\n"
				+ "Durante su membresía se le aplicara un 30% de descuento a todos los miembros del plan al comprar productos de la marca EVOLVEFIT.\r\n"
				+ "Miembros máximos para veneficios: 4\r\n" + "Membrecía mensual con costo de $1099." + "");
		txtrSeIncluyeAcceso.setBackground(new Color(204, 204, 204));
		txtrSeIncluyeAcceso.setBounds(20, 72, 527, 169);
		descripcion_plan.add(txtrSeIncluyeAcceso);
		ImageIcon p1 = new ImageIcon("Imagenes/editar.png");
		Image p2 = p1.getImage();
		Image p3 = p2.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		ImageIcon e1 = new ImageIcon("Imagenes/eliminar.png");
		Image e2 = e1.getImage();
		Image e3 = e2.getScaledInstance(20, 20, Image.SCALE_SMOOTH);

		JTextField textField = new JTextField();
		textField.setBackground(new Color(204, 204, 204));
		textField.setFont(new Font("Anton", Font.PLAIN, 28));
		textField.setText("1099");
		textField.setBounds(34, 118, 75, 35);
		plan_estandar.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_9 = new JLabel("/ mes");
		lblNewLabel_9.setFont(new Font("Anton", Font.PLAIN, 28));
		lblNewLabel_9.setBounds(111, 118, 100, 35);
		plan_estandar.add(lblNewLabel_9);
		ImageIcon s1 = new ImageIcon("Imagenes/editar.png");
		Image s2 = s1.getImage();
		Image s3 = s2.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		ImageIcon q1 = new ImageIcon("Imagenes/eliminar.png");
		Image q2 = q1.getImage();
		Image q3 = q2.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		ImageIcon a1 = new ImageIcon("Imagenes/editar.png");
		Image a2 = a1.getImage();
		Image a3 = a2.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		ImageIcon f1 = new ImageIcon("Imagenes/eliminar.png");
		Image f2 = f1.getImage();
		Image f3 = f2.getScaledInstance(20, 20, Image.SCALE_SMOOTH);

		JButton boton_cancelar = new JButton("Cancelar");
		boton_cancelar.setForeground(new Color(255, 255, 255));
		boton_cancelar.setBackground(new Color(255, 0, 0));
		boton_cancelar.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_cancelar.setBounds(625, 503, 128, 50);
		boton_cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				UsersController uc = new UsersController();
				uc.Editar_tarifas();

			}
		});
		panel_2.add(boton_cancelar);

		JButton boton_guardar = new JButton("Guardar");
		boton_guardar.setBackground(new Color(0, 206, 82));
		boton_guardar.setForeground(new Color(255, 255, 255));
		boton_guardar.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_guardar.setBounds(763, 503, 128, 50);
		panel_2.add(boton_guardar);

		// botones de los
		// laterales/////////////////////////////////////////////////////////////////////////////////////////////////////
		JButton boton_INICIO = new JButton("INICIO");// boton de inicio
		boton_INICIO.setBackground(new Color(255, 205, 17));
		boton_INICIO.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_INICIO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Panel_inicio();
			}
		});
		boton_INICIO.setBounds(10, 86, 136, 71);
		panel.add(boton_INICIO);

		JButton boton_CLIENTES = new JButton("CLIENTES");// boton de clientes
		boton_CLIENTES.setBackground(new Color(255, 205, 17));
		boton_CLIENTES.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CLIENTES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Clientes();
			}
		});
		boton_CLIENTES.setBounds(10, 168, 136, 71);
		panel.add(boton_CLIENTES);

		JButton boton_TARIFAS = new JButton("TARIFAS");// boton de tarifas
		boton_TARIFAS.setBackground(new Color(255, 255, 255));
		boton_TARIFAS.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_TARIFAS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		boton_TARIFAS.setBounds(10, 250, 136, 71);
		panel.add(boton_TARIFAS);

		JButton boton_INSTRUCTORES = new JButton("INSTRUCTORES");// boton de instructores
		boton_INSTRUCTORES.setBackground(new Color(255, 205, 17));
		boton_INSTRUCTORES.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_INSTRUCTORES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Instructores();
			}
		});
		boton_INSTRUCTORES.setBounds(10, 332, 136, 71);
		panel.add(boton_INSTRUCTORES);

		JButton boton_CLASES = new JButton("CLASES");// boton de clases
		boton_CLASES.setBackground(new Color(255, 205, 17));
		boton_CLASES.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CLASES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Clases();
			}
		});
		boton_CLASES.setBounds(10, 414, 136, 71);
		panel.add(boton_CLASES);

		JButton boton_CHECADOR = new JButton("CHECADOR");// boton de checador
		boton_CHECADOR.setBackground(new Color(255, 205, 17));
		boton_CHECADOR.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CHECADOR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Panel_checador();
			}
		});
		boton_CHECADOR.setBounds(10, 496, 136, 71);
		panel.add(boton_CHECADOR);

		JButton boton_CERRAR_SESION = new JButton("CERRAR SESION");
		boton_CERRAR_SESION.setBackground(new Color(255, 205, 17));
		boton_CERRAR_SESION.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CERRAR_SESION.addActionListener(e -> {
			JDialog dialog = new JDialog(frame, "Cerrar sesión", true);
			dialog.setSize(400, 220);
			dialog.setLocationRelativeTo(frame);
			dialog.setUndecorated(true);
			dialog.setLayout(null);

			// Panel principal - AHORA CON TAMAÑO COMPLETO
			JPanel Cerrar_sesion = new JPanel();
			Cerrar_sesion.setBackground(new Color(255, 255, 255));
			Cerrar_sesion.setBounds(0, 0, 400, 220);
			Cerrar_sesion.setLayout(null);
			dialog.add(Cerrar_sesion);

			// Panel superior azul
			JPanel panel_complemento = new JPanel();
			panel_complemento.setBackground(new Color(81, 151, 255));
			panel_complemento.setBounds(0, 0, 400, 33);
			Cerrar_sesion.add(panel_complemento);

			// Etiqueta con la pregunta
			JLabel pregunta_de_confirmacion = new JLabel(
					"<html><div style='text-align: center;'>Está a punto de cerrar sesión<br>¿Desea continuar?</div></html>");
			pregunta_de_confirmacion.setFont(new Font("Anton", Font.PLAIN, 16));
			pregunta_de_confirmacion.setBounds(90, 44, 310, 60);
			Cerrar_sesion.add(pregunta_de_confirmacion);

			// Botón Cancelar
			JButton boton_cancelar_alerta = new JButton("Cancelar");
			boton_cancelar_alerta.setForeground(Color.WHITE);
			boton_cancelar_alerta.setFont(new Font("Anton", Font.PLAIN, 14));
			boton_cancelar_alerta.setBackground(Color.RED);
			boton_cancelar_alerta.setBounds(50, 140, 120, 35);
			Cerrar_sesion.add(boton_cancelar_alerta);

			// Botón Aceptar
			JButton boton_aceptar = new JButton("Aceptar");
			boton_aceptar.setBackground(new Color(0, 206, 82));
			boton_aceptar.setForeground(Color.WHITE);
			boton_aceptar.setFont(new Font("Anton", Font.PLAIN, 14));
			boton_aceptar.setBounds(230, 140, 120, 35);
			Cerrar_sesion.add(boton_aceptar);

			// Acciones de los botones
			boton_cancelar_alerta.addActionListener(ev -> dialog.dispose());
			boton_aceptar.addActionListener(ev -> {
				dialog.dispose();
				closeSession(frame);
			});

			dialog.setVisible(true);
		});
		boton_CERRAR_SESION.setBounds(10, 579, 136, 71);
		panel.add(boton_CERRAR_SESION);

		frame.add(panel);
		frame.repaint();
		frame.revalidate();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public void Editar_tarifas_2() {
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
		panel_1.setBounds(0, 0, 1084, 75);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(10, 11, 53, 53);
		ImageIcon icon = new ImageIcon(getClass().getResource("/Imagenes/logo sin letras.png"));
		Image imagen = icon.getImage().getScaledInstance(53, 53, Image.SCALE_SMOOTH);
		lblNewLabel_1.setIcon(new ImageIcon(imagen));
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

		JLabel lblNewLabel = new JLabel("DESCRIPCION");// titulo de inicio
		lblNewLabel.setFont(new Font("Anton", Font.PLAIN, 26));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(60, 11, 223, 28);
		panel_3.add(lblNewLabel);
		// tarifas//////////////////////////////////////////////////////////////////////
		JPanel plan_estandar = new JPanel();
		plan_estandar.setBackground(new Color(255, 255, 255));
		plan_estandar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		plan_estandar.setBounds(40, 150, 275, 185);
		panel_2.add(plan_estandar);
		plan_estandar.setLayout(null);

		JLabel lblNewLabel_5 = new JLabel("Plan");
		lblNewLabel_5.setFont(new Font("Anton", Font.PLAIN, 22));
		lblNewLabel_5.setBounds(20, 26, 100, 35);
		plan_estandar.add(lblNewLabel_5);

		JTextField lblNewLabel_6 = new JTextField("ESTANDAR");
		lblNewLabel_6.setBackground(new Color(204, 204, 204));
		lblNewLabel_6.setFont(new Font("Anton", Font.PLAIN, 28));
		lblNewLabel_6.setBounds(20, 72, 172, 35);
		plan_estandar.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("$");
		lblNewLabel_7.setFont(new Font("Anton", Font.PLAIN, 28));
		lblNewLabel_7.setBounds(20, 118, 13, 35);
		plan_estandar.add(lblNewLabel_7);

		JPanel descripcion_plan = new JPanel();
		descripcion_plan.setBackground(new Color(255, 255, 255));
		descripcion_plan.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		descripcion_plan.setBounds(325, 150, 565, 263);
		panel_2.add(descripcion_plan);
		descripcion_plan.setLayout(null);

		JLabel lblNewLabel_8 = new JLabel("Plan:");
		lblNewLabel_8.setFont(new Font("Anton", Font.PLAIN, 22));
		lblNewLabel_8.setBounds(20, 26, 44, 35);
		descripcion_plan.add(lblNewLabel_8);

		JTextField txtEstandar = new JTextField();
		txtEstandar.setBackground(new Color(204, 204, 204));
		txtEstandar.setFont(new Font("Anton", Font.PLAIN, 22));
		txtEstandar.setText("ESTANDAR");
		txtEstandar.setBounds(74, 26, 214, 32);
		descripcion_plan.add(txtEstandar);
		txtEstandar.setColumns(10);

		JTextArea txtrSeIncluyeAcceso = new JTextArea();
		txtrSeIncluyeAcceso.setLineWrap(true);
		txtrSeIncluyeAcceso.setWrapStyleWord(true);
		txtrSeIncluyeAcceso.setFont(new Font("Anton", Font.PLAIN, 17));
		txtrSeIncluyeAcceso.setText(
				"Se incluye acceso al área de cardio únicamente y al equipo correspondiente.\nDurante su membresía se le aplicara un 15% de descuento al comprar productos de la marca EVOLVEFIT.\nMiembros máximos para veneficios: 1\nMembrecía mensual con costo de $300.");
		txtrSeIncluyeAcceso.setBackground(new Color(204, 204, 204));
		txtrSeIncluyeAcceso.setBounds(20, 72, 527, 169);
		descripcion_plan.add(txtrSeIncluyeAcceso);

		JTextField textField = new JTextField();
		textField.setBackground(new Color(204, 204, 204));
		textField.setFont(new Font("Anton", Font.PLAIN, 28));
		textField.setText("300");
		textField.setBounds(34, 118, 62, 35);
		plan_estandar.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_9 = new JLabel("/ mes");
		lblNewLabel_9.setFont(new Font("Anton", Font.PLAIN, 28));
		lblNewLabel_9.setBounds(95, 118, 100, 35);
		plan_estandar.add(lblNewLabel_9);

		JButton boton_cancelar = new JButton("Cancelar");
		boton_cancelar.setForeground(new Color(255, 255, 255));
		boton_cancelar.setBackground(new Color(255, 0, 0));
		boton_cancelar.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_cancelar.setBounds(625, 503, 128, 50);
		boton_cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				UsersController uc = new UsersController();
				uc.Editar_tarifas();
			}
		});
		panel_2.add(boton_cancelar);

		JButton boton_guardar = new JButton("Guardar");
		boton_guardar.setBackground(new Color(0, 206, 82));
		boton_guardar.setForeground(new Color(255, 255, 255));
		boton_guardar.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_guardar.setBounds(763, 503, 128, 50);
		panel_2.add(boton_guardar);

		// botones de los
		// laterales/////////////////////////////////////////////////////////////////////////////////////////////////////
		JButton boton_INICIO = new JButton("INICIO");// boton de inicio
		boton_INICIO.setBackground(new Color(255, 205, 17));
		boton_INICIO.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_INICIO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Panel_inicio();
			}
		});
		boton_INICIO.setBounds(10, 86, 136, 71);
		panel.add(boton_INICIO);

		JButton boton_CLIENTES = new JButton("CLIENTES");// boton de clientes
		boton_CLIENTES.setBackground(new Color(255, 205, 17));
		boton_CLIENTES.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CLIENTES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Clientes();
			}
		});
		boton_CLIENTES.setBounds(10, 168, 136, 71);
		panel.add(boton_CLIENTES);

		JButton boton_TARIFAS = new JButton("TARIFAS");// boton de tarifas
		boton_TARIFAS.setBackground(new Color(255, 255, 255));
		boton_TARIFAS.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_TARIFAS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Tarifas();
			}
		});
		boton_TARIFAS.setBounds(10, 250, 136, 71);
		panel.add(boton_TARIFAS);

		JButton boton_INSTRUCTORES = new JButton("INSTRUCTORES");// boton de instructores
		boton_INSTRUCTORES.setBackground(new Color(255, 205, 17));
		boton_INSTRUCTORES.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_INSTRUCTORES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Instructores();
			}
		});
		boton_INSTRUCTORES.setBounds(10, 332, 136, 71);
		panel.add(boton_INSTRUCTORES);

		JButton boton_CLASES = new JButton("CLASES");// boton de clases
		boton_CLASES.setBackground(new Color(255, 205, 17));
		boton_CLASES.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CLASES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Clases();
			}
		});
		boton_CLASES.setBounds(10, 414, 136, 71);
		panel.add(boton_CLASES);

		JButton boton_CHECADOR = new JButton("CHECADOR");// boton de checador
		boton_CHECADOR.setBackground(new Color(255, 205, 17));
		boton_CHECADOR.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CHECADOR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Panel_checador();
			}
		});
		boton_CHECADOR.setBounds(10, 496, 136, 71);
		panel.add(boton_CHECADOR);

		JButton boton_CERRAR_SESION = new JButton("CERRAR SESION");
		boton_CERRAR_SESION.setBackground(new Color(255, 205, 17));
		boton_CERRAR_SESION.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CERRAR_SESION.addActionListener(e -> {
			JDialog dialog = new JDialog(frame, "Cerrar sesión", true);
			dialog.setSize(400, 220);
			dialog.setLocationRelativeTo(frame);
			dialog.setUndecorated(true);
			dialog.setLayout(null);

			// Panel principal - AHORA CON TAMAÑO COMPLETO
			JPanel Cerrar_sesion = new JPanel();
			Cerrar_sesion.setBackground(new Color(255, 255, 255));
			Cerrar_sesion.setBounds(0, 0, 400, 220);
			Cerrar_sesion.setLayout(null);
			dialog.add(Cerrar_sesion);

			// Panel superior azul
			JPanel panel_complemento = new JPanel();
			panel_complemento.setBackground(new Color(81, 151, 255));
			panel_complemento.setBounds(0, 0, 400, 33);
			Cerrar_sesion.add(panel_complemento);

			// Etiqueta con la pregunta
			JLabel pregunta_de_confirmacion = new JLabel(
					"<html><div style='text-align: center;'>Está a punto de cerrar sesión<br>¿Desea continuar?</div></html>");
			pregunta_de_confirmacion.setFont(new Font("Anton", Font.PLAIN, 16));
			pregunta_de_confirmacion.setBounds(90, 44, 310, 60);
			Cerrar_sesion.add(pregunta_de_confirmacion);

			// Botón Cancelar
			JButton boton_cancelar_alerta = new JButton("Cancelar");
			boton_cancelar_alerta.setForeground(Color.WHITE);
			boton_cancelar_alerta.setFont(new Font("Anton", Font.PLAIN, 14));
			boton_cancelar_alerta.setBackground(Color.RED);
			boton_cancelar_alerta.setBounds(50, 140, 120, 35);
			Cerrar_sesion.add(boton_cancelar_alerta);

			// Botón Aceptar
			JButton boton_aceptar = new JButton("Aceptar");
			boton_aceptar.setBackground(new Color(0, 206, 82));
			boton_aceptar.setForeground(Color.WHITE);
			boton_aceptar.setFont(new Font("Anton", Font.PLAIN, 14));
			boton_aceptar.setBounds(230, 140, 120, 35);
			Cerrar_sesion.add(boton_aceptar);

			// Acciones de los botones
			boton_cancelar_alerta.addActionListener(ev -> dialog.dispose());
			boton_aceptar.addActionListener(ev -> {
				dialog.dispose();
				closeSession(frame);
			});

			dialog.setVisible(true);
		});
		boton_CERRAR_SESION.setBounds(10, 579, 136, 71);
		panel.add(boton_CERRAR_SESION);

		frame.add(panel);
		frame.repaint();
		frame.revalidate();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public void Añadir_tarifa() {
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
		panel_1.setBounds(0, 0, 1084, 75);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(10, 11, 53, 53);
		ImageIcon icon = new ImageIcon(getClass().getResource("/Imagenes/logo sin letras.png"));
		Image imagen = icon.getImage().getScaledInstance(53, 53, Image.SCALE_SMOOTH);
		lblNewLabel_1.setIcon(new ImageIcon(imagen));
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

		JLabel lblNewLabel = new JLabel("DESCRIPCION");// titulo de inicio
		lblNewLabel.setFont(new Font("Anton", Font.PLAIN, 26));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(60, 11, 223, 28);
		panel_3.add(lblNewLabel);
		// tarifas//////////////////////////////////////////////////////////////////////
		JPanel plan_estandar = new JPanel();
		plan_estandar.setBackground(new Color(255, 255, 255));
		plan_estandar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		plan_estandar.setBounds(40, 150, 275, 185);
		panel_2.add(plan_estandar);
		plan_estandar.setLayout(null);

		JLabel lblNewLabel_5 = new JLabel("Plan");
		lblNewLabel_5.setFont(new Font("Anton", Font.PLAIN, 22));
		lblNewLabel_5.setBounds(20, 26, 100, 35);
		plan_estandar.add(lblNewLabel_5);

		JTextField lblNewLabel_6 = new JTextField("");
		lblNewLabel_6.setBackground(new Color(204, 204, 204));
		lblNewLabel_6.setFont(new Font("Anton", Font.PLAIN, 28));
		lblNewLabel_6.setBounds(20, 72, 172, 35);
		plan_estandar.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("$");
		lblNewLabel_7.setFont(new Font("Anton", Font.PLAIN, 28));
		lblNewLabel_7.setBounds(20, 118, 13, 35);
		plan_estandar.add(lblNewLabel_7);

		JPanel descripcion_plan = new JPanel();
		descripcion_plan.setBackground(new Color(255, 255, 255));
		descripcion_plan.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		descripcion_plan.setBounds(325, 150, 565, 263);
		panel_2.add(descripcion_plan);
		descripcion_plan.setLayout(null);

		JLabel lblNewLabel_8 = new JLabel("Plan:");
		lblNewLabel_8.setFont(new Font("Anton", Font.PLAIN, 22));
		lblNewLabel_8.setBounds(20, 26, 44, 35);
		descripcion_plan.add(lblNewLabel_8);

		JTextField txtEstandar = new JTextField();
		txtEstandar.setBackground(new Color(204, 204, 204));
		txtEstandar.setFont(new Font("Anton", Font.PLAIN, 22));
		txtEstandar.setBounds(74, 26, 214, 32);
		descripcion_plan.add(txtEstandar);
		txtEstandar.setColumns(10);

		JTextArea txtrSeIncluyeAcceso = new JTextArea();
		txtrSeIncluyeAcceso.setLineWrap(true);
		txtrSeIncluyeAcceso.setWrapStyleWord(true);
		txtrSeIncluyeAcceso.setFont(new Font("Anton", Font.PLAIN, 17));
		txtrSeIncluyeAcceso.setBackground(new Color(204, 204, 204));
		txtrSeIncluyeAcceso.setBounds(20, 72, 527, 169);
		descripcion_plan.add(txtrSeIncluyeAcceso);

		JTextField textField = new JTextField();
		textField.setBackground(new Color(204, 204, 204));
		textField.setFont(new Font("Anton", Font.PLAIN, 28));
		textField.setBounds(34, 118, 62, 35);
		plan_estandar.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_9 = new JLabel("/ mes");
		lblNewLabel_9.setFont(new Font("Anton", Font.PLAIN, 28));
		lblNewLabel_9.setBounds(95, 118, 100, 35);
		plan_estandar.add(lblNewLabel_9);

		JButton boton_cancelar = new JButton("Cancelar");
		boton_cancelar.setForeground(new Color(255, 255, 255));
		boton_cancelar.setBackground(new Color(255, 0, 0));
		boton_cancelar.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_cancelar.setBounds(625, 503, 128, 50);
		boton_cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				UsersController uc = new UsersController();
				uc.Editar_tarifas();
			}
		});
		panel_2.add(boton_cancelar);

		JButton boton_guardar = new JButton("Guardar");
		boton_guardar.setBackground(new Color(0, 206, 82));
		boton_guardar.setForeground(new Color(255, 255, 255));
		boton_guardar.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_guardar.setBounds(763, 503, 128, 50);
		panel_2.add(boton_guardar);

		// botones de los
		// laterales/////////////////////////////////////////////////////////////////////////////////////////////////////
		JButton boton_INICIO = new JButton("INICIO");// boton de inicio
		boton_INICIO.setBackground(new Color(255, 205, 17));
		boton_INICIO.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_INICIO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Panel_inicio();
			}
		});
		boton_INICIO.setBounds(10, 86, 136, 71);
		panel.add(boton_INICIO);

		JButton boton_CLIENTES = new JButton("CLIENTES");// boton de clientes
		boton_CLIENTES.setBackground(new Color(255, 205, 17));
		boton_CLIENTES.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CLIENTES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Clientes();
			}
		});
		boton_CLIENTES.setBounds(10, 168, 136, 71);
		panel.add(boton_CLIENTES);

		JButton boton_TARIFAS = new JButton("TARIFAS");// boton de tarifas
		boton_TARIFAS.setBackground(new Color(255, 255, 255));
		boton_TARIFAS.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_TARIFAS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		boton_TARIFAS.setBounds(10, 250, 136, 71);
		panel.add(boton_TARIFAS);

		JButton boton_INSTRUCTORES = new JButton("INSTRUCTORES");// boton de instructores
		boton_INSTRUCTORES.setBackground(new Color(255, 205, 17));
		boton_INSTRUCTORES.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_INSTRUCTORES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Instructores();
			}
		});
		boton_INSTRUCTORES.setBounds(10, 332, 136, 71);
		panel.add(boton_INSTRUCTORES);

		JButton boton_CLASES = new JButton("CLASES");// boton de clases
		boton_CLASES.setBackground(new Color(255, 205, 17));
		boton_CLASES.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CLASES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Clases();
			}
		});
		boton_CLASES.setBounds(10, 414, 136, 71);
		panel.add(boton_CLASES);

		JButton boton_CHECADOR = new JButton("CHECADOR");// boton de checador
		boton_CHECADOR.setBackground(new Color(255, 205, 17));
		boton_CHECADOR.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CHECADOR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Panel_checador();
			}
		});
		boton_CHECADOR.setBounds(10, 496, 136, 71);
		panel.add(boton_CHECADOR);

		JButton boton_CERRAR_SESION = new JButton("CERRAR SESION");
		boton_CERRAR_SESION.setBackground(new Color(255, 205, 17));
		boton_CERRAR_SESION.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CERRAR_SESION.addActionListener(e -> {
			JDialog dialog = new JDialog(frame, "Cerrar sesión", true);
			dialog.setSize(400, 220);
			dialog.setLocationRelativeTo(frame);
			dialog.setUndecorated(true);
			dialog.setLayout(null);

			// Panel principal - AHORA CON TAMAÑO COMPLETO
			JPanel Cerrar_sesion = new JPanel();
			Cerrar_sesion.setBackground(new Color(255, 255, 255));
			Cerrar_sesion.setBounds(0, 0, 400, 220);
			Cerrar_sesion.setLayout(null);
			dialog.add(Cerrar_sesion);

			// Panel superior azul
			JPanel panel_complemento = new JPanel();
			panel_complemento.setBackground(new Color(81, 151, 255));
			panel_complemento.setBounds(0, 0, 400, 33);
			Cerrar_sesion.add(panel_complemento);

			// Etiqueta con la pregunta
			JLabel pregunta_de_confirmacion = new JLabel(
					"<html><div style='text-align: center;'>Está a punto de cerrar sesión<br>¿Desea continuar?</div></html>");
			pregunta_de_confirmacion.setFont(new Font("Anton", Font.PLAIN, 16));
			pregunta_de_confirmacion.setBounds(90, 44, 310, 60);
			Cerrar_sesion.add(pregunta_de_confirmacion);

			// Botón Cancelar
			JButton boton_cancelar_alerta = new JButton("Cancelar");
			boton_cancelar_alerta.setForeground(Color.WHITE);
			boton_cancelar_alerta.setFont(new Font("Anton", Font.PLAIN, 14));
			boton_cancelar_alerta.setBackground(Color.RED);
			boton_cancelar_alerta.setBounds(50, 140, 120, 35);
			Cerrar_sesion.add(boton_cancelar_alerta);

			// Botón Aceptar
			JButton boton_aceptar = new JButton("Aceptar");
			boton_aceptar.setBackground(new Color(0, 206, 82));
			boton_aceptar.setForeground(Color.WHITE);
			boton_aceptar.setFont(new Font("Anton", Font.PLAIN, 14));
			boton_aceptar.setBounds(230, 140, 120, 35);
			Cerrar_sesion.add(boton_aceptar);

			// Acciones de los botones
			boton_cancelar_alerta.addActionListener(ev -> dialog.dispose());
			boton_aceptar.addActionListener(ev -> {
				dialog.dispose();
				closeSession(frame);
			});

			dialog.setVisible(true);
		});
		boton_CERRAR_SESION.setBounds(10, 579, 136, 71);
		panel.add(boton_CERRAR_SESION);

		frame.add(panel);
		frame.repaint();
		frame.revalidate();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public void Clientes_con_tarifa_ESTANDAR() {
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
		panel_1.setBounds(0, 0, 1084, 75);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(10, 11, 53, 53);
		ImageIcon icon = new ImageIcon(getClass().getResource("/Imagenes/logo sin letras.png"));
		Image imagen = icon.getImage().getScaledInstance(53, 53, Image.SCALE_SMOOTH);
		lblNewLabel_1.setIcon(new ImageIcon(imagen));
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

		JLabel lblNewLabel = new JLabel("TARIFA: ESTANDAR");// titulo de inicio
		lblNewLabel.setFont(new Font("Anton", Font.PLAIN, 26));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(60, 11, 523, 28);
		panel_3.add(lblNewLabel);

		Object[][] data = { { 2, "Laura", "Martínez", "0987654321", "laura@mail.com" } };

		String[] columnas = { "ID cliente", "Nombre(s)", "Primer apellido", "Teléfono", "Correo electrónico" };

		JScrollPane scrollPane_Usuario = new JScrollPane();// tabla del usario
		scrollPane_Usuario.setBounds(10, 61, 898, 427);
		panel_2.add(scrollPane_Usuario);
		JTable table = new JTable(data, columnas);
		table.setFont(new Font("Anton", Font.PLAIN, 12));
		table.setBackground(new Color(204, 204, 204));
		scrollPane_Usuario.setViewportView(table);

		JTableHeader header = table.getTableHeader();
		header.setBackground(Color.BLACK);
		header.setForeground(Color.WHITE);
		header.setFont(new Font("Anton", Font.PLAIN, 14));
		header.setReorderingAllowed(false);
		scrollPane_Usuario.setViewportView(table);

		JButton boton_regresar = new JButton("Regresar");// boton regresar
		boton_regresar.setFont(new Font("Anton", Font.PLAIN, 20));
		boton_regresar.setBackground(new Color(255, 205, 17));
		boton_regresar.setBounds(724, 503, 184, 50);
		boton_regresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Tarifas();
			}
		});
		panel_2.add(boton_regresar);

		JButton boton_INICIO = new JButton("INICIO");
		boton_INICIO.setBackground(new Color(255, 205, 17));
		boton_INICIO.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_INICIO.setBounds(10, 86, 136, 71);
		boton_INICIO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Panel_inicio();
			}
		});
		panel.add(boton_INICIO);

		JButton boton_CLIENTES = new JButton("CLIENTES");// boton de clientes
		boton_CLIENTES.setBackground(new Color(255, 205, 17));
		boton_CLIENTES.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CLIENTES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Clientes();
			}
		});
		boton_CLIENTES.setBounds(10, 168, 136, 71);
		panel.add(boton_CLIENTES);

		JButton boton_TARIFAS = new JButton("TARIFAS");// boton de tarifas
		boton_TARIFAS.setBackground(new Color(255, 255, 255));
		boton_TARIFAS.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_TARIFAS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		boton_TARIFAS.setBounds(10, 250, 136, 71);
		panel.add(boton_TARIFAS);

		JButton boton_INSTRUCTORES = new JButton("INSTRUCTORES");// boton de instructores
		boton_INSTRUCTORES.setBackground(new Color(255, 205, 17));
		boton_INSTRUCTORES.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_INSTRUCTORES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Instructores();
			}
		});
		boton_INSTRUCTORES.setBounds(10, 332, 136, 71);
		panel.add(boton_INSTRUCTORES);

		JButton boton_CLASES = new JButton("CLASES");
		boton_CLASES.setBackground(new Color(255, 205, 17));
		boton_CLASES.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CLASES.setBounds(10, 414, 136, 71);
		boton_CLASES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Clases();
			}
		});
		panel.add(boton_CLASES);

		JButton boton_CHECADOR = new JButton("CHECADOR");// boton de checador
		boton_CHECADOR.setBackground(new Color(255, 205, 17));
		boton_CHECADOR.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CHECADOR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Panel_checador();
			}
		});
		boton_CHECADOR.setBounds(10, 496, 136, 71);
		panel.add(boton_CHECADOR);

		JButton boton_CERRAR_SESION = new JButton("CERRAR SESION");
		boton_CERRAR_SESION.setBackground(new Color(255, 205, 17));
		boton_CERRAR_SESION.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CERRAR_SESION.addActionListener(e -> {
			JDialog dialog = new JDialog(frame, "Cerrar sesión", true);
			dialog.setSize(400, 220);
			dialog.setLocationRelativeTo(frame);
			dialog.setUndecorated(true);
			dialog.setLayout(null);

			// Panel principal - AHORA CON TAMAÑO COMPLETO
			JPanel Cerrar_sesion = new JPanel();
			Cerrar_sesion.setBackground(new Color(255, 255, 255));
			Cerrar_sesion.setBounds(0, 0, 400, 220);
			Cerrar_sesion.setLayout(null);
			dialog.add(Cerrar_sesion);

			// Panel superior azul
			JPanel panel_complemento = new JPanel();
			panel_complemento.setBackground(new Color(81, 151, 255));
			panel_complemento.setBounds(0, 0, 400, 33);
			Cerrar_sesion.add(panel_complemento);

			// Etiqueta con la pregunta
			JLabel pregunta_de_confirmacion = new JLabel(
					"<html><div style='text-align: center;'>Está a punto de cerrar sesión<br>¿Desea continuar?</div></html>");
			pregunta_de_confirmacion.setFont(new Font("Anton", Font.PLAIN, 16));
			pregunta_de_confirmacion.setBounds(90, 44, 310, 60);
			Cerrar_sesion.add(pregunta_de_confirmacion);

			// Botón Cancelar
			JButton boton_cancelar = new JButton("Cancelar");
			boton_cancelar.setForeground(Color.WHITE);
			boton_cancelar.setFont(new Font("Anton", Font.PLAIN, 14));
			boton_cancelar.setBackground(Color.RED);
			boton_cancelar.setBounds(50, 140, 120, 35);
			Cerrar_sesion.add(boton_cancelar);

			// Botón Aceptar
			JButton boton_aceptar = new JButton("Aceptar");
			boton_aceptar.setBackground(new Color(0, 206, 82));
			boton_aceptar.setForeground(Color.WHITE);
			boton_aceptar.setFont(new Font("Anton", Font.PLAIN, 14));
			boton_aceptar.setBounds(230, 140, 120, 35);
			Cerrar_sesion.add(boton_aceptar);

			// Acciones de los botones
			boton_cancelar.addActionListener(ev -> dialog.dispose());
			boton_aceptar.addActionListener(ev -> {
				dialog.dispose();
				closeSession(frame);
			});

			dialog.setVisible(true);
		});
		boton_CERRAR_SESION.setBounds(10, 579, 136, 71);
		panel.add(boton_CERRAR_SESION);

		frame.add(panel);
		frame.repaint();
		frame.revalidate();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public void Clientes_con_tarifa_FAMILIAR() {
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
		panel_1.setBounds(0, 0, 1084, 75);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(10, 11, 53, 53);
		ImageIcon icon = new ImageIcon(getClass().getResource("/Imagenes/logo sin letras.png"));
		Image imagen = icon.getImage().getScaledInstance(53, 53, Image.SCALE_SMOOTH);
		lblNewLabel_1.setIcon(new ImageIcon(imagen));
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

		JLabel lblNewLabel = new JLabel("TARIFA: FAMILIAR");// titulo de inicio
		lblNewLabel.setFont(new Font("Anton", Font.PLAIN, 26));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(60, 11, 523, 28);
		panel_3.add(lblNewLabel);

		Object[][] data = { { 2, "Laura", "Martínez", "0987654321", "laura@mail.com" } };

		String[] columnas = { "ID cliente", "Nombre(s)", "Primer apellido", "Teléfono", "Correo electrónico" };

		JScrollPane scrollPane_Usuario = new JScrollPane();// tabla del usario
		scrollPane_Usuario.setBounds(10, 61, 898, 427);
		panel_2.add(scrollPane_Usuario);
		JTable table = new JTable(data, columnas);
		table.setFont(new Font("Anton", Font.PLAIN, 12));
		table.setBackground(new Color(204, 204, 204));
		scrollPane_Usuario.setViewportView(table);

		JTableHeader header = table.getTableHeader();
		header.setBackground(Color.BLACK);
		header.setForeground(Color.WHITE);
		header.setFont(new Font("Anton", Font.PLAIN, 14));
		header.setReorderingAllowed(false);
		scrollPane_Usuario.setViewportView(table);

		JButton boton_regresar = new JButton("Regresar");// boton regresar
		boton_regresar.setFont(new Font("Anton", Font.PLAIN, 20));
		boton_regresar.setBackground(new Color(255, 205, 17));
		boton_regresar.setBounds(724, 503, 184, 50);
		boton_regresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Tarifas();
			}
		});
		panel_2.add(boton_regresar);

		JButton boton_INICIO = new JButton("INICIO");
		boton_INICIO.setBackground(new Color(255, 205, 17));
		boton_INICIO.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_INICIO.setBounds(10, 86, 136, 71);
		boton_INICIO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Panel_inicio();
			}
		});
		panel.add(boton_INICIO);

		JButton boton_CLIENTES = new JButton("CLIENTES");// boton de clientes
		boton_CLIENTES.setBackground(new Color(255, 205, 17));
		boton_CLIENTES.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CLIENTES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Clientes();
			}
		});
		boton_CLIENTES.setBounds(10, 168, 136, 71);
		panel.add(boton_CLIENTES);

		JButton boton_TARIFAS = new JButton("TARIFAS");// boton de tarifas
		boton_TARIFAS.setBackground(new Color(255, 255, 255));
		boton_TARIFAS.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_TARIFAS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		boton_TARIFAS.setBounds(10, 250, 136, 71);
		panel.add(boton_TARIFAS);

		JButton boton_INSTRUCTORES = new JButton("INSTRUCTORES");// boton de instructores
		boton_INSTRUCTORES.setBackground(new Color(255, 205, 17));
		boton_INSTRUCTORES.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_INSTRUCTORES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Instructores();
			}
		});
		boton_INSTRUCTORES.setBounds(10, 332, 136, 71);
		panel.add(boton_INSTRUCTORES);

		JButton boton_CLASES = new JButton("CLASES");
		boton_CLASES.setBackground(new Color(255, 205, 17));
		boton_CLASES.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CLASES.setBounds(10, 414, 136, 71);
		boton_CLASES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Clases();
			}
		});
		panel.add(boton_CLASES);

		JButton boton_CHECADOR = new JButton("CHECADOR");// boton de checador
		boton_CHECADOR.setBackground(new Color(255, 205, 17));
		boton_CHECADOR.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CHECADOR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Panel_checador();

			}
		});
		boton_CHECADOR.setBounds(10, 496, 136, 71);
		panel.add(boton_CHECADOR);

		JButton boton_CERRAR_SESION = new JButton("CERRAR SESION");
		boton_CERRAR_SESION.setBackground(new Color(255, 205, 17));
		boton_CERRAR_SESION.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CERRAR_SESION.addActionListener(e -> {
			JDialog dialog = new JDialog(frame, "Cerrar sesión", true);
			dialog.setSize(400, 220);
			dialog.setLocationRelativeTo(frame);
			dialog.setUndecorated(true);
			dialog.setLayout(null);

			// Panel principal - AHORA CON TAMAÑO COMPLETO
			JPanel Cerrar_sesion = new JPanel();
			Cerrar_sesion.setBackground(new Color(255, 255, 255));
			Cerrar_sesion.setBounds(0, 0, 400, 220);
			Cerrar_sesion.setLayout(null);
			dialog.add(Cerrar_sesion);

			// Panel superior azul
			JPanel panel_complemento = new JPanel();
			panel_complemento.setBackground(new Color(81, 151, 255));
			panel_complemento.setBounds(0, 0, 400, 33);
			Cerrar_sesion.add(panel_complemento);

			// Etiqueta con la pregunta
			JLabel pregunta_de_confirmacion = new JLabel(
					"<html><div style='text-align: center;'>Está a punto de cerrar sesión<br>¿Desea continuar?</div></html>");
			pregunta_de_confirmacion.setFont(new Font("Anton", Font.PLAIN, 16));
			pregunta_de_confirmacion.setBounds(90, 44, 310, 60);
			Cerrar_sesion.add(pregunta_de_confirmacion);

			// Botón Cancelar
			JButton boton_cancelar = new JButton("Cancelar");
			boton_cancelar.setForeground(Color.WHITE);
			boton_cancelar.setFont(new Font("Anton", Font.PLAIN, 14));
			boton_cancelar.setBackground(Color.RED);
			boton_cancelar.setBounds(50, 140, 120, 35);
			Cerrar_sesion.add(boton_cancelar);

			// Botón Aceptar
			JButton boton_aceptar = new JButton("Aceptar");
			boton_aceptar.setBackground(new Color(0, 206, 82));
			boton_aceptar.setForeground(Color.WHITE);
			boton_aceptar.setFont(new Font("Anton", Font.PLAIN, 14));
			boton_aceptar.setBounds(230, 140, 120, 35);
			Cerrar_sesion.add(boton_aceptar);

			// Acciones de los botones
			boton_cancelar.addActionListener(ev -> dialog.dispose());
			boton_aceptar.addActionListener(ev -> {
				dialog.dispose();
				closeSession(frame);
			});

			dialog.setVisible(true);
		});
		boton_CERRAR_SESION.setBounds(10, 579, 136, 71);
		panel.add(boton_CERRAR_SESION);

		frame.add(panel);
		frame.repaint();
		frame.revalidate();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public void Clientes_con_tarifa_PREMIUM() {
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
		panel_1.setBounds(0, 0, 1084, 75);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(10, 11, 53, 53);
		ImageIcon icon = new ImageIcon(getClass().getResource("/Imagenes/logo sin letras.png"));
		Image imagen = icon.getImage().getScaledInstance(53, 53, Image.SCALE_SMOOTH);
		lblNewLabel_1.setIcon(new ImageIcon(imagen));
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

		JLabel lblNewLabel = new JLabel("TARIFA: PREMIUM");// titulo de inicio
		lblNewLabel.setFont(new Font("Anton", Font.PLAIN, 26));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(60, 11, 309, 28);
		panel_3.add(lblNewLabel);

		Object[][] data = { { 2, "Laura", "Martínez", "0987654321", "laura@mail.com" } };

		String[] columnas = { "ID cliente", "Nombre(s)", "Primer apellido", "Teléfono", "Correo electrónico" };

		JScrollPane scrollPane_Usuario = new JScrollPane();// tabla del usario
		scrollPane_Usuario.setBounds(10, 61, 898, 427);
		panel_2.add(scrollPane_Usuario);
		JTable table = new JTable(data, columnas);
		table.setFont(new Font("Anton", Font.PLAIN, 12));
		table.setBackground(new Color(204, 204, 204));
		scrollPane_Usuario.setViewportView(table);

		JTableHeader header = table.getTableHeader();
		header.setBackground(Color.BLACK);
		header.setForeground(Color.WHITE);
		header.setFont(new Font("Anton", Font.PLAIN, 14));
		header.setReorderingAllowed(false);
		scrollPane_Usuario.setViewportView(table);

		JButton boton_regresar = new JButton("Regresar");// boton regresar
		boton_regresar.setFont(new Font("Anton", Font.PLAIN, 20));
		boton_regresar.setBackground(new Color(255, 205, 17));
		boton_regresar.setBounds(724, 503, 184, 50);
		boton_regresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Tarifas();
			}
		});
		panel_2.add(boton_regresar);

		JButton boton_INICIO = new JButton("INICIO");
		boton_INICIO.setBackground(new Color(255, 205, 17));
		boton_INICIO.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_INICIO.setBounds(10, 86, 136, 71);
		boton_INICIO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Panel_inicio();
			}
		});
		panel.add(boton_INICIO);

		JButton boton_CLIENTES = new JButton("CLIENTES");// boton de clientes
		boton_CLIENTES.setBackground(new Color(255, 205, 17));
		boton_CLIENTES.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CLIENTES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Clientes();

			}
		});
		boton_CLIENTES.setBounds(10, 168, 136, 71);
		panel.add(boton_CLIENTES);

		JButton boton_TARIFAS = new JButton("TARIFAS");// boton de tarifas
		boton_TARIFAS.setBackground(new Color(255, 255, 255));
		boton_TARIFAS.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_TARIFAS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		boton_TARIFAS.setBounds(10, 250, 136, 71);
		panel.add(boton_TARIFAS);

		JButton boton_INSTRUCTORES = new JButton("INSTRUCTORES");// boton de instructores
		boton_INSTRUCTORES.setBackground(new Color(255, 205, 17));
		boton_INSTRUCTORES.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_INSTRUCTORES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Instructores();
			}
		});
		boton_INSTRUCTORES.setBounds(10, 332, 136, 71);
		panel.add(boton_INSTRUCTORES);

		JButton boton_CLASES = new JButton("CLASES");
		boton_CLASES.setBackground(new Color(255, 205, 17));
		boton_CLASES.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CLASES.setBounds(10, 414, 136, 71);
		boton_CLASES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Clases();
			}
		});
		panel.add(boton_CLASES);

		JButton boton_CHECADOR = new JButton("CHECADOR");// boton de checador
		boton_CHECADOR.setBackground(new Color(255, 205, 17));
		boton_CHECADOR.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CHECADOR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Panel_checador();
			}
		});
		boton_CHECADOR.setBounds(10, 496, 136, 71);
		panel.add(boton_CHECADOR);

		JButton boton_CERRAR_SESION = new JButton("CERRAR SESION");
		boton_CERRAR_SESION.setBackground(new Color(255, 205, 17));
		boton_CERRAR_SESION.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CERRAR_SESION.addActionListener(e -> {
			JDialog dialog = new JDialog(frame, "Cerrar sesión", true);
			dialog.setSize(400, 220);
			dialog.setLocationRelativeTo(frame);
			dialog.setUndecorated(true);
			dialog.setLayout(null);

			// Panel principal - AHORA CON TAMAÑO COMPLETO
			JPanel Cerrar_sesion = new JPanel();
			Cerrar_sesion.setBackground(new Color(255, 255, 255));
			Cerrar_sesion.setBounds(0, 0, 400, 220);
			Cerrar_sesion.setLayout(null);
			dialog.add(Cerrar_sesion);

			// Panel superior azul
			JPanel panel_complemento = new JPanel();
			panel_complemento.setBackground(new Color(81, 151, 255));
			panel_complemento.setBounds(0, 0, 400, 33);
			Cerrar_sesion.add(panel_complemento);

			// Etiqueta con la pregunta
			JLabel pregunta_de_confirmacion = new JLabel(
					"<html><div style='text-align: center;'>Está a punto de cerrar sesión<br>¿Desea continuar?</div></html>");
			pregunta_de_confirmacion.setFont(new Font("Anton", Font.PLAIN, 16));
			pregunta_de_confirmacion.setBounds(90, 44, 310, 60);
			Cerrar_sesion.add(pregunta_de_confirmacion);

			// Botón Cancelar
			JButton boton_cancelar = new JButton("Cancelar");
			boton_cancelar.setForeground(Color.WHITE);
			boton_cancelar.setFont(new Font("Anton", Font.PLAIN, 14));
			boton_cancelar.setBackground(Color.RED);
			boton_cancelar.setBounds(50, 140, 120, 35);
			Cerrar_sesion.add(boton_cancelar);

			// Botón Aceptar
			JButton boton_aceptar = new JButton("Aceptar");
			boton_aceptar.setBackground(new Color(0, 206, 82));
			boton_aceptar.setForeground(Color.WHITE);
			boton_aceptar.setFont(new Font("Anton", Font.PLAIN, 14));
			boton_aceptar.setBounds(230, 140, 120, 35);
			Cerrar_sesion.add(boton_aceptar);

			// Acciones de los botones
			boton_cancelar.addActionListener(ev -> dialog.dispose());
			boton_aceptar.addActionListener(ev -> {
				dialog.dispose();
				closeSession(frame);
			});

			dialog.setVisible(true);
		});
		boton_CERRAR_SESION.setBounds(10, 579, 136, 71);
		panel.add(boton_CERRAR_SESION);

		frame.add(panel);
		frame.repaint();
		frame.revalidate();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public void Ficha_de_instructor(int idinstructor) {
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
		panel_1.setBounds(0, 0, 1084, 75);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(10, 11, 53, 53);
		ImageIcon icon = new ImageIcon(getClass().getResource("/Imagenes/logo sin letras.png"));
		Image imagen = icon.getImage().getScaledInstance(53, 53, Image.SCALE_SMOOTH);
		lblNewLabel_1.setIcon(new ImageIcon(imagen));
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

		JLabel lblNewLabel = new JLabel("FICHA DE INSTRUCTOR");// titulo de inicio
		lblNewLabel.setFont(new Font("Anton", Font.PLAIN, 26));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(60, 11, 309, 28);
		panel_3.add(lblNewLabel);

		JLabel Imagen_de_usuario = new JLabel("");
		Imagen_de_usuario.setBounds(15, 90, 200, 300);
		Imagen_de_usuario.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		Imagen_de_usuario.setHorizontalAlignment(SwingConstants.CENTER);
		Imagen_de_usuario.setVerticalAlignment(SwingConstants.CENTER);
		ImageIcon icon1 = new ImageIcon(getClass().getResource("/Imagenes/VADUROTISH.jpg"));
		Image imagen1 = icon1.getImage().getScaledInstance(200, 300, Image.SCALE_SMOOTH);
		Imagen_de_usuario.setIcon(new ImageIcon(imagen1));
		panel_2.add(Imagen_de_usuario);

		JLabel lblNewLabel_11 = new JLabel("Nombre:");
		lblNewLabel_11.setFont(new Font("Anton", Font.PLAIN, 16));
		lblNewLabel_11.setBounds(272, 118, 60, 22);
		panel_2.add(lblNewLabel_11);

		JLabel lblNewLabel_10 = new JLabel("Especialidad: ");
		lblNewLabel_10.setFont(new Font("Anton", Font.PLAIN, 16));
		lblNewLabel_10.setBounds(272, 165, 91, 22);
		panel_2.add(lblNewLabel_10);

		JLabel lblNewLabel_9 = new JLabel("Clase asignada:");
		lblNewLabel_9.setFont(new Font("Anton", Font.PLAIN, 16));
		lblNewLabel_9.setBounds(272, 267, 104, 22);
		panel_2.add(lblNewLabel_9);

		JLabel lblNewLabel_8 = new JLabel("Teléfono:");
		lblNewLabel_8.setFont(new Font("Anton", Font.PLAIN, 16));
		lblNewLabel_8.setBounds(272, 315, 67, 22);
		panel_2.add(lblNewLabel_8);

		JLabel lblNewLabel_7 = new JLabel("Correo electronico:");
		lblNewLabel_7.setFont(new Font("Anton", Font.PLAIN, 16));
		lblNewLabel_7.setBounds(272, 362, 132, 28);
		panel_2.add(lblNewLabel_7);

		JLabel lblNewLabel_13 = new JLabel("Horarios disponibles: ");
		lblNewLabel_13.setFont(new Font("Anton", Font.PLAIN, 16));
		lblNewLabel_13.setBounds(272, 217, 147, 22);
		panel_2.add(lblNewLabel_13);
		// info de
		// intructor///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		JLabel Info_nombre = new JLabel("Laura Mendez");
		Info_nombre.setFont(new Font("Anton", Font.PLAIN, 16));
		Info_nombre.setBounds(328, 119, 122, 21);
		panel_2.add(Info_nombre);

		JLabel Info_especialidad = new JLabel("Pilates y Yoga");
		Info_especialidad.setFont(new Font("Anton", Font.PLAIN, 16));
		Info_especialidad.setBounds(361, 165, 147, 22);
		panel_2.add(Info_especialidad);

		JLabel Info_clase = new JLabel("YOGA RELAX");
		Info_clase.setFont(new Font("Anton", Font.PLAIN, 16));
		Info_clase.setBounds(381, 267, 152, 22);
		panel_2.add(Info_clase);

		JLabel Info_telefono = new JLabel("612 187 0000");
		Info_telefono.setFont(new Font("Anton", Font.PLAIN, 16));
		Info_telefono.setBounds(334, 315, 152, 22);
		panel_2.add(Info_telefono);

		JLabel Info_correo = new JLabel(" laura.mendez@evolvefit.com");
		Info_correo.setFont(new Font("Anton", Font.PLAIN, 16));
		Info_correo.setBounds(405, 362, 200, 28);
		panel_2.add(Info_correo);

		JLabel Info_horarrio = new JLabel("Lunes, Miércoles y Viernes de 8:00 am a 12:00 pm");
		Info_horarrio.setFont(new Font("Anton", Font.PLAIN, 16));
		Info_horarrio.setBounds(422, 217, 352, 22);
		panel_2.add(Info_horarrio);

		// botones de accion para el cliente
		// ///////////////////////////////////////////////////////////////////////
		JButton boton_descraga_credencial = new JButton("credencial");
		boton_descraga_credencial.setBackground(new Color(255, 205, 17));
		boton_descraga_credencial.setFont(new Font("Anton", Font.PLAIN, 12));
		boton_descraga_credencial.setBounds(736, 501, 160, 32);
		boton_descraga_credencial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				UsersController uc = new UsersController();
				uc.Credencial_instructor();
			}
		});
		panel_2.add(boton_descraga_credencial);

		JButton boton_historial_asistencias = new JButton("Editar información");
		boton_historial_asistencias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				UsersController uc = new UsersController();
				uc.Editar_instructor();
			}
		});
		boton_historial_asistencias.setBackground(new Color(255, 205, 17));
		boton_historial_asistencias.setFont(new Font("Anton", Font.PLAIN, 12));
		boton_historial_asistencias.setBounds(566, 501, 160, 32);
		panel_2.add(boton_historial_asistencias);

		JButton boton_historial_clase = new JButton("Historial de clases");
		boton_historial_clase.setBackground(new Color(255, 205, 17));
		boton_historial_clase.setFont(new Font("Anton", Font.PLAIN, 12));
		boton_historial_clase.setBounds(396, 501, 160, 32);
		boton_historial_clase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				UsersController uc = new UsersController();
				uc.Historial_de_clase();
			}
		});
		panel_2.add(boton_historial_clase);

		JButton boton_regresar = new JButton("Regresar");
		boton_regresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Instructores();
			}
		});
		boton_regresar.setFont(new Font("Anton", Font.PLAIN, 12));
		boton_regresar.setBackground(new Color(255, 205, 17));
		boton_regresar.setBounds(226, 501, 160, 32);
		panel_2.add(boton_regresar);

		// Botones laterales
		// //////////////////////////////////////////////////////////////////////////////////////////////
		JButton boton_INICIO = new JButton("INICIO");// boton de inicio
		boton_INICIO.setBackground(new Color(255, 205, 17));
		boton_INICIO.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_INICIO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Panel_inicio();
			}
		});
		boton_INICIO.setBounds(10, 86, 136, 71);
		panel.add(boton_INICIO);

		JButton boton_CLIENTES = new JButton("CLIENTES");// boton de clientes
		boton_CLIENTES.setBackground(new Color(255, 205, 17));
		boton_CLIENTES.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CLIENTES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Clientes();

			}
		});
		boton_CLIENTES.setBounds(10, 168, 136, 71);
		panel.add(boton_CLIENTES);

		JButton boton_TARIFAS = new JButton("TARIFAS");// boton de tarifas
		boton_TARIFAS.setBackground(new Color(255, 205, 17));
		boton_TARIFAS.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_TARIFAS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Tarifas();
			}
		});
		boton_TARIFAS.setBounds(10, 250, 136, 71);
		panel.add(boton_TARIFAS);

		JButton boton_INSTRUCTORES = new JButton("INSTRUCTORES");// boton de instructores
		boton_INSTRUCTORES.setBackground(new Color(255, 255, 255));
		boton_INSTRUCTORES.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_INSTRUCTORES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		boton_INSTRUCTORES.setBounds(10, 332, 136, 71);
		panel.add(boton_INSTRUCTORES);

		JButton boton_CLASES = new JButton("CLASES");// boton de clases
		boton_CLASES.setBackground(new Color(255, 205, 17));
		boton_CLASES.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CLASES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Clases();
			}
		});
		boton_CLASES.setBounds(10, 414, 136, 71);
		panel.add(boton_CLASES);

		JButton boton_CHECADOR = new JButton("CHECADOR");// boton de checador
		boton_CHECADOR.setBackground(new Color(255, 205, 17));
		boton_CHECADOR.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CHECADOR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Panel_checador();
			}
		});
		boton_CHECADOR.setBounds(10, 496, 136, 71);
		panel.add(boton_CHECADOR);

		JButton boton_CERRAR_SESION = new JButton("CERRAR SESION");
		boton_CERRAR_SESION.setBackground(new Color(255, 205, 17));
		boton_CERRAR_SESION.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CERRAR_SESION.addActionListener(e -> {
			JDialog dialog = new JDialog(frame, "Cerrar sesión", true);
			dialog.setSize(400, 220);
			dialog.setLocationRelativeTo(frame);
			dialog.setUndecorated(true);
			dialog.setLayout(null);

			// Panel principal - AHORA CON TAMAÑO COMPLETO
			JPanel Cerrar_sesion = new JPanel();
			Cerrar_sesion.setBackground(new Color(255, 255, 255));
			Cerrar_sesion.setBounds(0, 0, 400, 220);
			Cerrar_sesion.setLayout(null);
			dialog.add(Cerrar_sesion);

			// Panel superior azul
			JPanel panel_complemento = new JPanel();
			panel_complemento.setBackground(new Color(81, 151, 255));
			panel_complemento.setBounds(0, 0, 400, 33);
			Cerrar_sesion.add(panel_complemento);

			// Etiqueta con la pregunta
			JLabel pregunta_de_confirmacion = new JLabel(
					"<html><div style='text-align: center;'>Está a punto de cerrar sesión<br>¿Desea continuar?</div></html>");
			pregunta_de_confirmacion.setFont(new Font("Anton", Font.PLAIN, 16));
			pregunta_de_confirmacion.setBounds(90, 44, 310, 60);
			Cerrar_sesion.add(pregunta_de_confirmacion);

			// Botón Cancelar
			JButton boton_cancelar = new JButton("Cancelar");
			boton_cancelar.setForeground(Color.WHITE);
			boton_cancelar.setFont(new Font("Anton", Font.PLAIN, 14));
			boton_cancelar.setBackground(Color.RED);
			boton_cancelar.setBounds(50, 140, 120, 35);
			Cerrar_sesion.add(boton_cancelar);

			// Botón Aceptar
			JButton boton_aceptar = new JButton("Aceptar");
			boton_aceptar.setBackground(new Color(0, 206, 82));
			boton_aceptar.setForeground(Color.WHITE);
			boton_aceptar.setFont(new Font("Anton", Font.PLAIN, 14));
			boton_aceptar.setBounds(230, 140, 120, 35);
			Cerrar_sesion.add(boton_aceptar);

			// Acciones de los botones
			boton_cancelar.addActionListener(ev -> dialog.dispose());
			boton_aceptar.addActionListener(ev -> {
				dialog.dispose();
				closeSession(frame);
			});

			dialog.setVisible(true);
		});
		boton_CERRAR_SESION.setBounds(10, 579, 136, 71);
		panel.add(boton_CERRAR_SESION);

		frame.add(panel);
		frame.repaint();
		frame.revalidate();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public void Historial_de_clase() {
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
		panel_1.setBounds(0, 0, 1084, 75);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(10, 11, 53, 53);
		ImageIcon icon = new ImageIcon(getClass().getResource("/Imagenes/logo sin letras.png"));
		Image imagen = icon.getImage().getScaledInstance(53, 53, Image.SCALE_SMOOTH);
		lblNewLabel_1.setIcon(new ImageIcon(imagen));
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

		JLabel lblNewLabel = new JLabel("HISTORIAL DE CLASE");// titulo de inicio
		lblNewLabel.setFont(new Font("Anton", Font.PLAIN, 26));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(60, 11, 309, 28);
		panel_3.add(lblNewLabel);

		Object[][] data = { { "Laura Mendez", "YOGA RELAX", "MATUTINO", "Lunes y Viernes 8:00 am a 9:00 am" } };

		String[] columnas = { "Entrenador", "Clase asignada", "Turno", "Horario" };

		JScrollPane scrollPane_Usuario = new JScrollPane();// tabla del usario
		scrollPane_Usuario.setBounds(10, 61, 898, 50);
		panel_2.add(scrollPane_Usuario);
		JTable table = new JTable(data, columnas);
		table.setFont(new Font("Anton", Font.PLAIN, 12));
		table.setBackground(new Color(204, 204, 204));
		scrollPane_Usuario.setViewportView(table);

		JTableHeader header = table.getTableHeader();
		header.setBackground(Color.BLACK);
		header.setForeground(Color.WHITE);
		header.setFont(new Font("Anton", Font.PLAIN, 14));
		header.setReorderingAllowed(false);
		scrollPane_Usuario.setViewportView(table);

		Object[][] data2 = { { 2, "Laura", "Martínez", "0987654321", "laura@mail.com" } };

		String[] columnas2 = { "ID cliente", "Nombre(s)", "Primer apellido", "Teléfono", "Correo electrónico" };

		JScrollPane scrollPane_Pagos = new JScrollPane();// tabla de pagos
		scrollPane_Pagos.setBounds(10, 122, 898, 168);
		panel_2.add(scrollPane_Pagos);
		JTable table_1 = new JTable(data2, columnas2);
		table_1.setFont(new Font("Anton", Font.PLAIN, 12));
		table_1.setBackground(new Color(204, 204, 204));
		scrollPane_Pagos.setViewportView(table_1);

		JTableHeader header2 = table_1.getTableHeader();
		header2.setBackground(Color.BLACK);
		header2.setForeground(Color.WHITE);
		header2.setFont(new Font("Anton", Font.PLAIN, 14));
		header2.setReorderingAllowed(false);
		scrollPane_Pagos.setViewportView(table_1);

		JButton boton_regresar = new JButton("Regresar");// boton regresar
		boton_regresar.setFont(new Font("Anton", Font.PLAIN, 20));
		boton_regresar.setBackground(new Color(255, 205, 17));
		boton_regresar.setBounds(530, 503, 184, 50);
		boton_regresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				UsersController uc = new UsersController();
				uc.Ficha_de_instructor(0);
			}
		});
		panel_2.add(boton_regresar);

		JButton boton_descargar_clase = new JButton("Descargar");
		boton_descargar_clase.setFont(new Font("Anton", Font.PLAIN, 20));
		boton_descargar_clase.setBackground(new Color(255, 205, 17));
		boton_descargar_clase.setBounds(724, 503, 184, 50);
		panel_2.add(boton_descargar_clase);

		JButton boton_INICIO = new JButton("INICIO");
		boton_INICIO.setBackground(new Color(255, 205, 17));
		boton_INICIO.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_INICIO.setBounds(10, 86, 136, 71);
		boton_INICIO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Panel_inicio();
			}
		});
		panel.add(boton_INICIO);

		JButton boton_CLIENTES = new JButton("CLIENTES");// boton de clientes
		boton_CLIENTES.setBackground(new Color(255, 205, 17));
		boton_CLIENTES.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CLIENTES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Clientes();
			}
		});
		boton_CLIENTES.setBounds(10, 168, 136, 71);
		panel.add(boton_CLIENTES);

		JButton boton_TARIFAS = new JButton("TARIFAS");// boton de tarifas
		boton_TARIFAS.setBackground(new Color(255, 205, 17));
		boton_TARIFAS.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_TARIFAS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Tarifas();
			}
		});
		boton_TARIFAS.setBounds(10, 250, 136, 71);
		panel.add(boton_TARIFAS);

		JButton boton_INSTRUCTORES = new JButton("INSTRUCTORES");// boton de instructores
		boton_INSTRUCTORES.setBackground(new Color(255, 255, 255));
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
		boton_CLASES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Clases();
			}
		});
		panel.add(boton_CLASES);

		JButton boton_CHECADOR = new JButton("CHECADOR");// boton de checador
		boton_CHECADOR.setBackground(new Color(255, 205, 17));
		boton_CHECADOR.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CHECADOR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Panel_checador();
			}
		});
		boton_CHECADOR.setBounds(10, 496, 136, 71);
		panel.add(boton_CHECADOR);

		JButton boton_CERRAR_SESION = new JButton("CERRAR SESION");
		boton_CERRAR_SESION.setBackground(new Color(255, 205, 17));
		boton_CERRAR_SESION.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CERRAR_SESION.addActionListener(e -> {
			JDialog dialog = new JDialog(frame, "Cerrar sesión", true);
			dialog.setSize(400, 220);
			dialog.setLocationRelativeTo(frame);
			dialog.setUndecorated(true);
			dialog.setLayout(null);

			// Panel principal - AHORA CON TAMAÑO COMPLETO
			JPanel Cerrar_sesion = new JPanel();
			Cerrar_sesion.setBackground(new Color(255, 255, 255));
			Cerrar_sesion.setBounds(0, 0, 400, 220);
			Cerrar_sesion.setLayout(null);
			dialog.add(Cerrar_sesion);

			// Panel superior azul
			JPanel panel_complemento = new JPanel();
			panel_complemento.setBackground(new Color(81, 151, 255));
			panel_complemento.setBounds(0, 0, 400, 33);
			Cerrar_sesion.add(panel_complemento);

			// Etiqueta con la pregunta
			JLabel pregunta_de_confirmacion = new JLabel(
					"<html><div style='text-align: center;'>Está a punto de cerrar sesión<br>¿Desea continuar?</div></html>");
			pregunta_de_confirmacion.setFont(new Font("Anton", Font.PLAIN, 16));
			pregunta_de_confirmacion.setBounds(90, 44, 310, 60);
			Cerrar_sesion.add(pregunta_de_confirmacion);

			// Botón Cancelar
			JButton boton_cancelar = new JButton("Cancelar");
			boton_cancelar.setForeground(Color.WHITE);
			boton_cancelar.setFont(new Font("Anton", Font.PLAIN, 14));
			boton_cancelar.setBackground(Color.RED);
			boton_cancelar.setBounds(50, 140, 120, 35);
			Cerrar_sesion.add(boton_cancelar);

			// Botón Aceptar
			JButton boton_aceptar = new JButton("Aceptar");
			boton_aceptar.setBackground(new Color(0, 206, 82));
			boton_aceptar.setForeground(Color.WHITE);
			boton_aceptar.setFont(new Font("Anton", Font.PLAIN, 14));
			boton_aceptar.setBounds(230, 140, 120, 35);
			Cerrar_sesion.add(boton_aceptar);

			// Acciones de los botones
			boton_cancelar.addActionListener(ev -> dialog.dispose());
			boton_aceptar.addActionListener(ev -> {
				dialog.dispose();
				closeSession(frame);
			});

			dialog.setVisible(true);
		});
		boton_CERRAR_SESION.setBounds(10, 579, 136, 71);
		panel.add(boton_CERRAR_SESION);

		frame.add(panel);
		frame.repaint();
		frame.revalidate();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public void Editar_instructor() {
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
		panel_1.setBounds(0, 0, 1084, 75);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(10, 11, 53, 53);
		ImageIcon icon = new ImageIcon(getClass().getResource("/Imagenes/logo sin letras.png"));
		Image imagen = icon.getImage().getScaledInstance(53, 53, Image.SCALE_SMOOTH);
		lblNewLabel_1.setIcon(new ImageIcon(imagen));
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

		JLabel lblNewLabel = new JLabel("FICHA DE INSTRUCTOR");// titulo de inicio
		lblNewLabel.setFont(new Font("Anton", Font.PLAIN, 26));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(60, 11, 309, 28);
		panel_3.add(lblNewLabel);

		JLabel Imagen_de_usuario = new JLabel("");
		Imagen_de_usuario.setBounds(49, 92, 173, 252);
		Imagen_de_usuario.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		Imagen_de_usuario.setHorizontalAlignment(SwingConstants.CENTER);
		Imagen_de_usuario.setVerticalAlignment(SwingConstants.CENTER);
		ImageIcon icon1 = new ImageIcon(getClass().getResource("/Imagenes/VADUROTISH.jpg"));
		Image imagen1 = icon1.getImage().getScaledInstance(173, 252, Image.SCALE_SMOOTH);
		Imagen_de_usuario.setIcon(new ImageIcon(imagen1));
		panel_2.add(Imagen_de_usuario);

		JLabel lblNewLabel_11 = new JLabel("Nombre:");
		lblNewLabel_11.setFont(new Font("Anton", Font.PLAIN, 16));
		lblNewLabel_11.setBounds(272, 118, 60, 22);
		panel_2.add(lblNewLabel_11);

		JLabel lblNewLabel_10 = new JLabel("Especialidad: ");
		lblNewLabel_10.setFont(new Font("Anton", Font.PLAIN, 16));
		lblNewLabel_10.setBounds(272, 151, 91, 22);
		panel_2.add(lblNewLabel_10);

		JLabel lblNewLabel_9 = new JLabel("Clase asignada:");
		lblNewLabel_9.setFont(new Font("Anton", Font.PLAIN, 16));
		lblNewLabel_9.setBounds(272, 293, 104, 22);
		panel_2.add(lblNewLabel_9);

		JLabel lblNewLabel_8 = new JLabel("Teléfono:");
		lblNewLabel_8.setFont(new Font("Anton", Font.PLAIN, 16));
		lblNewLabel_8.setBounds(272, 326, 67, 22);
		panel_2.add(lblNewLabel_8);

		JLabel lblNewLabel_7 = new JLabel("Correo electronico:");
		lblNewLabel_7.setFont(new Font("Anton", Font.PLAIN, 16));
		lblNewLabel_7.setBounds(271, 359, 132, 28);
		panel_2.add(lblNewLabel_7);

		JLabel lblNewLabel_13 = new JLabel("Horarios disponibles: ");
		lblNewLabel_13.setFont(new Font("Anton", Font.PLAIN, 16));
		lblNewLabel_13.setBounds(272, 184, 147, 22);
		panel_2.add(lblNewLabel_13);
		// info de
		// intructor///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		JTextField Info_nombre = new JTextField("Laura Mendez");
		Info_nombre.setBackground(new Color(204, 204, 204));
		Info_nombre.setFont(new Font("Anton", Font.PLAIN, 16));
		Info_nombre.setBounds(420, 119, 320, 21);
		panel_2.add(Info_nombre);

		JTextField Info_especialidad = new JTextField("Pilates y Yoga");
		Info_especialidad.setBackground(new Color(204, 204, 204));
		Info_especialidad.setFont(new Font("Anton", Font.PLAIN, 16));
		Info_especialidad.setBounds(420, 151, 320, 22);
		panel_2.add(Info_especialidad);

		JTextField Info_telefono = new JTextField("612 187 0000");
		Info_telefono.setBackground(new Color(204, 204, 204));
		Info_telefono.setFont(new Font("Anton", Font.PLAIN, 16));
		Info_telefono.setBounds(420, 326, 320, 22);
		panel_2.add(Info_telefono);

		JTextField Info_correo = new JTextField(" laura.mendez@evolvefit.com");
		Info_correo.setBackground(new Color(204, 204, 204));
		Info_correo.setFont(new Font("Anton", Font.PLAIN, 16));
		Info_correo.setBounds(420, 362, 320, 22);
		panel_2.add(Info_correo);

		JTextField Info_horarrio = new JTextField("Lunes, Miércoles y Viernes de 8:00 am a 12:00 pm");
		Info_horarrio.setBackground(new Color(204, 204, 204));
		Info_horarrio.setFont(new Font("Anton", Font.PLAIN, 16));
		Info_horarrio.setBounds(272, 217, 468, 65);
		panel_2.add(Info_horarrio);

		// comobox//////////////////////////////////////////////////////////////////////////////////////////////////////////////

		UIManager.put("ComboBox.buttonBackground", new Color(255, 205, 17));
		JComboBox comboBox_clases = new JComboBox();
		comboBox_clases.setBackground(new Color(204, 204, 204));
		comboBox_clases.setFont(new Font("Anton", Font.PLAIN, 16));
		comboBox_clases.setBounds(420, 293, 320, 22);
		String[] clases = { "NINGUNA", "TECNICA EN MAQUINAS", "TECNICA EN MAQUINAS", "YOGA RELAX", "HIIT FUNCIONAL",
				"SPINNING INTENSO" };
		for (String clase : clases) {
			comboBox_clases.addItem(clase);
		}
		panel_2.add(comboBox_clases);

		// botones de accion para el instructor
		// ///////////////////////////////////////////////////////////////////////
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
				frame.dispose();
				UsersController uc = new UsersController();
				uc.Ficha_de_instructor(0);
			}
		});
		boton_cancelar.setFont(new Font("Anton", Font.PLAIN, 18));
		boton_cancelar.setBackground(new Color(255, 0, 0));
		boton_cancelar.setBounds(554, 510, 160, 43);
		panel_2.add(boton_cancelar);

		JButton boton_cargargar_foto = new JButton("Cargar foto");
		boton_cargargar_foto.setFont(new Font("Anton", Font.PLAIN, 11));
		boton_cargargar_foto.setBackground(new Color(255, 205, 17));
		boton_cargargar_foto.setBounds(80, 355, 104, 22);
		panel_2.add(boton_cargargar_foto);

		// Botones laterales
		// //////////////////////////////////////////////////////////////////////////////////////////////
		JButton boton_INICIO = new JButton("INICIO");// boton de inicio
		boton_INICIO.setBackground(new Color(255, 205, 17));
		boton_INICIO.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_INICIO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Panel_inicio();
			}
		});
		boton_INICIO.setBounds(10, 86, 136, 71);
		panel.add(boton_INICIO);

		JButton boton_CLIENTES = new JButton("CLIENTES");// boton de clientes
		boton_CLIENTES.setBackground(new Color(255, 205, 17));
		boton_CLIENTES.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CLIENTES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Clientes();
			}
		});
		boton_CLIENTES.setBounds(10, 168, 136, 71);
		panel.add(boton_CLIENTES);

		JButton boton_TARIFAS = new JButton("TARIFAS");// boton de tarifas
		boton_TARIFAS.setBackground(new Color(255, 205, 17));
		boton_TARIFAS.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_TARIFAS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Tarifas();
			}
		});
		boton_TARIFAS.setBounds(10, 250, 136, 71);
		panel.add(boton_TARIFAS);

		JButton boton_INSTRUCTORES = new JButton("INSTRUCTORES");// boton de instructores
		boton_INSTRUCTORES.setBackground(new Color(255, 255, 255));
		boton_INSTRUCTORES.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_INSTRUCTORES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		boton_INSTRUCTORES.setBounds(10, 332, 136, 71);
		panel.add(boton_INSTRUCTORES);

		JButton boton_CLASES = new JButton("CLASES");// boton de clases
		boton_CLASES.setBackground(new Color(255, 205, 17));
		boton_CLASES.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CLASES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Clases();
			}
		});
		boton_CLASES.setBounds(10, 414, 136, 71);
		panel.add(boton_CLASES);

		JButton boton_CHECADOR = new JButton("CHECADOR");// boton de checador
		boton_CHECADOR.setBackground(new Color(255, 205, 17));
		boton_CHECADOR.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CHECADOR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Panel_checador();
			}
		});
		boton_CHECADOR.setBounds(10, 496, 136, 71);
		panel.add(boton_CHECADOR);

		JButton boton_CERRAR_SESION = new JButton("CERRAR SESION");
		boton_CERRAR_SESION.setBackground(new Color(255, 205, 17));
		boton_CERRAR_SESION.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CERRAR_SESION.addActionListener(e -> {
			JDialog dialog = new JDialog(frame, "Cerrar sesión", true);
			dialog.setSize(400, 220);
			dialog.setLocationRelativeTo(frame);
			dialog.setUndecorated(true);
			dialog.setLayout(null);

			// Panel principal - AHORA CON TAMAÑO COMPLETO
			JPanel Cerrar_sesion = new JPanel();
			Cerrar_sesion.setBackground(new Color(255, 255, 255));
			Cerrar_sesion.setBounds(0, 0, 400, 220);
			Cerrar_sesion.setLayout(null);
			dialog.add(Cerrar_sesion);

			// Panel superior azul
			JPanel panel_complemento = new JPanel();
			panel_complemento.setBackground(new Color(81, 151, 255));
			panel_complemento.setBounds(0, 0, 400, 33);
			Cerrar_sesion.add(panel_complemento);

			// Etiqueta con la pregunta
			JLabel pregunta_de_confirmacion = new JLabel(
					"<html><div style='text-align: center;'>Está a punto de cerrar sesión<br>¿Desea continuar?</div></html>");
			pregunta_de_confirmacion.setFont(new Font("Anton", Font.PLAIN, 16));
			pregunta_de_confirmacion.setBounds(90, 44, 310, 60);
			Cerrar_sesion.add(pregunta_de_confirmacion);

			// Botón Cancelar
			JButton boton_cancelar_alerta = new JButton("Cancelar");
			boton_cancelar_alerta.setForeground(Color.WHITE);
			boton_cancelar_alerta.setFont(new Font("Anton", Font.PLAIN, 14));
			boton_cancelar_alerta.setBackground(Color.RED);
			boton_cancelar_alerta.setBounds(50, 140, 120, 35);
			Cerrar_sesion.add(boton_cancelar_alerta);

			// Botón Aceptar
			JButton boton_aceptar = new JButton("Aceptar");
			boton_aceptar.setBackground(new Color(0, 206, 82));
			boton_aceptar.setForeground(Color.WHITE);
			boton_aceptar.setFont(new Font("Anton", Font.PLAIN, 14));
			boton_aceptar.setBounds(230, 140, 120, 35);
			Cerrar_sesion.add(boton_aceptar);

			// Acciones de los botones
			boton_cancelar_alerta.addActionListener(ev -> dialog.dispose());
			boton_aceptar.addActionListener(ev -> {
				dialog.dispose();
				closeSession(frame);
			});

			dialog.setVisible(true);
		});
		boton_CERRAR_SESION.setBounds(10, 579, 136, 71);
		panel.add(boton_CERRAR_SESION);

		frame.add(panel);
		frame.repaint();
		frame.revalidate();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public void Credencial_instructor() {
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
		panel_1.setBounds(0, 0, 1084, 75);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(10, 11, 53, 53);
		ImageIcon icon = new ImageIcon(getClass().getResource("/Imagenes/logo sin letras.png"));
		Image imagen = icon.getImage().getScaledInstance(53, 53, Image.SCALE_SMOOTH);
		lblNewLabel_1.setIcon(new ImageIcon(imagen));
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

		JLabel lblNewLabel = new JLabel("Credencial");// titulo de inicio
		lblNewLabel.setFont(new Font("Anton", Font.PLAIN, 26));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(60, 11, 309, 28);
		panel_3.add(lblNewLabel);

		JLabel Imagen_de_usuario = new JLabel("");
		Imagen_de_usuario.setBounds(70, 140, 200, 300);
		Imagen_de_usuario.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		Imagen_de_usuario.setHorizontalAlignment(SwingConstants.CENTER);
		Imagen_de_usuario.setVerticalAlignment(SwingConstants.CENTER);
		ImageIcon icon1 = new ImageIcon(getClass().getResource("/Imagenes/VADUROTISH.jpg"));
		Image imagen1 = icon1.getImage().getScaledInstance(200, 300, Image.SCALE_SMOOTH);
		Imagen_de_usuario.setIcon(new ImageIcon(imagen1));
		panel_2.add(Imagen_de_usuario);

		JLabel lblNewLabel_5 = new JLabel("INSTRUCTOR");
		lblNewLabel_5.setFont(new Font("Anton", Font.PLAIN, 32));
		lblNewLabel_5.setBounds(70, 61, 147, 68);
		panel_2.add(lblNewLabel_5);

		JLabel lblNewLabel_11 = new JLabel("Nombre:");
		lblNewLabel_11.setFont(new Font("Anton", Font.PLAIN, 20));
		lblNewLabel_11.setBounds(325, 160, 84, 32);
		panel_2.add(lblNewLabel_11);

		JLabel lblNewLabel_10 = new JLabel("Especialidad:");
		lblNewLabel_10.setFont(new Font("Anton", Font.PLAIN, 20));
		lblNewLabel_10.setBounds(325, 230, 127, 32);
		panel_2.add(lblNewLabel_10);

		JLabel lblNewLabel_14 = new JLabel("Horarios: ");
		lblNewLabel_14.setFont(new Font("Anton", Font.PLAIN, 20));
		lblNewLabel_14.setBounds(325, 300, 152, 32);
		panel_2.add(lblNewLabel_14);

		JLabel lblNewLabel_7 = new JLabel("");
		lblNewLabel_7.setBounds(779, 370, 100, 100);
		lblNewLabel_7.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setVerticalAlignment(SwingConstants.CENTER);
		ImageIcon icon2 = new ImageIcon(getClass().getResource("/Imagenes/logo ginmasio.png"));
		Image imagen2 = icon2.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		lblNewLabel_7.setIcon(new ImageIcon(imagen2));
		panel_2.add(lblNewLabel_7);

		// info de
		// instructor/////////////////////////////////////////////////////////////////////////////////////////////////
		JLabel Info_nombre = new JLabel("Laura Mendez ");
		Info_nombre.setFont(new Font("Anton", Font.PLAIN, 20));
		Info_nombre.setBounds(399, 162, 152, 29);
		panel_2.add(Info_nombre);

		JLabel Info_fecha_nacimiento = new JLabel("Pilates y Yoga");
		Info_fecha_nacimiento.setFont(new Font("Anton", Font.PLAIN, 20));
		Info_fecha_nacimiento.setBounds(435, 230, 136, 32);
		panel_2.add(Info_fecha_nacimiento);

		JLabel Info_plan = new JLabel("Lunes, Miércoles y Viernes - 8:00 am a 12:00 pm");
		Info_plan.setFont(new Font("Anton", Font.PLAIN, 20));
		Info_plan.setBounds(325, 327, 461, 32);
		panel_2.add(Info_plan);

		// botones de accion para el cliente
		// ///////////////////////////////////////////////////////////////////////
		JButton boton_descraga_credencial = new JButton("Descargar ");
		boton_descraga_credencial.setBackground(new Color(255, 205, 17));
		boton_descraga_credencial.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_descraga_credencial.setBounds(740, 494, 147, 39);
		panel_2.add(boton_descraga_credencial);

		JButton boton_regresar = new JButton("Regresar");
		boton_regresar.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_regresar.setBackground(new Color(255, 205, 17));
		boton_regresar.setBounds(583, 494, 147, 39);
		boton_regresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				UsersController uc = new UsersController();
				uc.Ficha_de_instructor(0);
			}
		});
		panel_2.add(boton_regresar);

		// Botones laterales
		// //////////////////////////////////////////////////////////////////////////////////////////////
		JButton boton_INICIO = new JButton("INICIO");// boton de inicio
		boton_INICIO.setBackground(new Color(255, 205, 17));
		boton_INICIO.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_INICIO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Panel_inicio();
			}
		});
		boton_INICIO.setBounds(10, 86, 136, 71);
		panel.add(boton_INICIO);

		JButton boton_CLIENTES = new JButton("CLIENTES");// boton de clientes
		boton_CLIENTES.setBackground(new Color(255, 205, 17));
		boton_CLIENTES.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CLIENTES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Clientes();
			}
		});
		boton_CLIENTES.setBounds(10, 168, 136, 71);
		panel.add(boton_CLIENTES);

		JButton boton_TARIFAS = new JButton("TARIFAS");// boton de tarifas
		boton_TARIFAS.setBackground(new Color(255, 205, 17));
		boton_TARIFAS.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_TARIFAS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Tarifas();
			}
		});
		boton_TARIFAS.setBounds(10, 250, 136, 71);
		panel.add(boton_TARIFAS);

		JButton boton_INSTRUCTORES = new JButton("INSTRUCTORES");// boton de instructores
		boton_INSTRUCTORES.setBackground(new Color(255, 255, 255));
		boton_INSTRUCTORES.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_INSTRUCTORES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		boton_INSTRUCTORES.setBounds(10, 332, 136, 71);
		panel.add(boton_INSTRUCTORES);

		JButton boton_CLASES = new JButton("CLASES");// boton de clases
		boton_CLASES.setBackground(new Color(255, 205, 17));
		boton_CLASES.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CLASES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Clases();
			}
		});
		boton_CLASES.setBounds(10, 414, 136, 71);
		panel.add(boton_CLASES);

		JButton boton_CHECADOR = new JButton("CHECADOR");// boton de checador
		boton_CHECADOR.setBackground(new Color(255, 205, 17));
		boton_CHECADOR.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CHECADOR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Panel_checador();
			}
		});
		boton_CHECADOR.setBounds(10, 496, 136, 71);
		panel.add(boton_CHECADOR);

		JButton boton_CERRAR_SESION = new JButton("CERRAR SESION");
		boton_CERRAR_SESION.setBackground(new Color(255, 205, 17));
		boton_CERRAR_SESION.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CERRAR_SESION.addActionListener(e -> {
			JDialog dialog = new JDialog(frame, "Cerrar sesión", true);
			dialog.setSize(400, 220);
			dialog.setLocationRelativeTo(frame);
			dialog.setUndecorated(true);
			dialog.setLayout(null);

			// Panel principal - AHORA CON TAMAÑO COMPLETO
			JPanel Cerrar_sesion = new JPanel();
			Cerrar_sesion.setBackground(new Color(255, 255, 255));
			Cerrar_sesion.setBounds(0, 0, 400, 220);
			Cerrar_sesion.setLayout(null);
			dialog.add(Cerrar_sesion);

			// Panel superior azul
			JPanel panel_complemento = new JPanel();
			panel_complemento.setBackground(new Color(81, 151, 255));
			panel_complemento.setBounds(0, 0, 400, 33);
			Cerrar_sesion.add(panel_complemento);

			// Etiqueta con la pregunta
			JLabel pregunta_de_confirmacion = new JLabel(
					"<html><div style='text-align: center;'>Está a punto de cerrar sesión<br>¿Desea continuar?</div></html>");
			pregunta_de_confirmacion.setFont(new Font("Anton", Font.PLAIN, 16));
			pregunta_de_confirmacion.setBounds(90, 44, 310, 60);
			Cerrar_sesion.add(pregunta_de_confirmacion);

			// Botón Cancelar
			JButton boton_cancelar_alerta = new JButton("Cancelar");
			boton_cancelar_alerta.setForeground(Color.WHITE);
			boton_cancelar_alerta.setFont(new Font("Anton", Font.PLAIN, 14));
			boton_cancelar_alerta.setBackground(Color.RED);
			boton_cancelar_alerta.setBounds(50, 140, 120, 35);
			Cerrar_sesion.add(boton_cancelar_alerta);

			// Botón Aceptar
			JButton boton_aceptar = new JButton("Aceptar");
			boton_aceptar.setBackground(new Color(0, 206, 82));
			boton_aceptar.setForeground(Color.WHITE);
			boton_aceptar.setFont(new Font("Anton", Font.PLAIN, 14));
			boton_aceptar.setBounds(230, 140, 120, 35);
			Cerrar_sesion.add(boton_aceptar);

			// Acciones de los botones
			boton_cancelar_alerta.addActionListener(ev -> dialog.dispose());
			boton_aceptar.addActionListener(ev -> {
				dialog.dispose();
				closeSession(frame);
			});

			dialog.setVisible(true);
		});
		boton_CERRAR_SESION.setBounds(10, 579, 136, 71);
		panel.add(boton_CERRAR_SESION);

		frame.add(panel);
		frame.repaint();
		frame.revalidate();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public void Añadir_instructor() {
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
		panel_1.setBounds(0, 0, 1084, 75);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(10, 11, 53, 53);
		ImageIcon icon = new ImageIcon(getClass().getResource("/Imagenes/logo sin letras.png"));
		Image imagen = icon.getImage().getScaledInstance(53, 53, Image.SCALE_SMOOTH);
		lblNewLabel_1.setIcon(new ImageIcon(imagen));
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

		JLabel lblNewLabel = new JLabel("FICHA DE INSTRUCTOR");// titulo de inicio
		lblNewLabel.setFont(new Font("Anton", Font.PLAIN, 26));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(60, 11, 309, 28);
		panel_3.add(lblNewLabel);

		JLabel Imagen_de_usuario = new JLabel("");
		Imagen_de_usuario.setBounds(49, 92, 173, 252);
		Imagen_de_usuario.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		Imagen_de_usuario.setHorizontalAlignment(SwingConstants.CENTER);
		Imagen_de_usuario.setVerticalAlignment(SwingConstants.CENTER);
		ImageIcon icon1 = new ImageIcon(getClass().getResource("/Imagenes/VADUROTISH.jpg"));
		Image imagen1 = icon1.getImage().getScaledInstance(173, 252, Image.SCALE_SMOOTH);
		Imagen_de_usuario.setIcon(new ImageIcon(imagen1));
		panel_2.add(Imagen_de_usuario);

		JLabel lblNewLabel_11 = new JLabel("Nombre:");
		lblNewLabel_11.setFont(new Font("Anton", Font.PLAIN, 16));
		lblNewLabel_11.setBounds(272, 118, 60, 22);
		panel_2.add(lblNewLabel_11);

		JLabel lblNewLabel_10 = new JLabel("Especialidad: ");
		lblNewLabel_10.setFont(new Font("Anton", Font.PLAIN, 16));
		lblNewLabel_10.setBounds(272, 151, 91, 22);
		panel_2.add(lblNewLabel_10);

		JLabel lblNewLabel_9 = new JLabel("Clase asignada:");
		lblNewLabel_9.setFont(new Font("Anton", Font.PLAIN, 16));
		lblNewLabel_9.setBounds(272, 293, 104, 22);
		panel_2.add(lblNewLabel_9);

		JLabel lblNewLabel_8 = new JLabel("Teléfono:");
		lblNewLabel_8.setFont(new Font("Anton", Font.PLAIN, 16));
		lblNewLabel_8.setBounds(272, 326, 67, 22);
		panel_2.add(lblNewLabel_8);

		JLabel lblNewLabel_7 = new JLabel("Correo electronico:");
		lblNewLabel_7.setFont(new Font("Anton", Font.PLAIN, 16));
		lblNewLabel_7.setBounds(271, 359, 132, 28);
		panel_2.add(lblNewLabel_7);

		JLabel lblNewLabel_13 = new JLabel("Horarios disponibles: ");
		lblNewLabel_13.setFont(new Font("Anton", Font.PLAIN, 16));
		lblNewLabel_13.setBounds(272, 184, 147, 22);
		panel_2.add(lblNewLabel_13);
		// info de
		// intructor///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		JTextField Info_nombre = new JTextField("");
		Info_nombre.setBackground(new Color(204, 204, 204));
		Info_nombre.setFont(new Font("Anton", Font.PLAIN, 16));
		Info_nombre.setBounds(420, 119, 320, 21);
		panel_2.add(Info_nombre);

		JTextField Info_especialidad = new JTextField("");
		Info_especialidad.setBackground(new Color(204, 204, 204));
		Info_especialidad.setFont(new Font("Anton", Font.PLAIN, 16));
		Info_especialidad.setBounds(420, 151, 320, 22);
		panel_2.add(Info_especialidad);

		JTextField Info_telefono = new JTextField("");
		Info_telefono.setBackground(new Color(204, 204, 204));
		Info_telefono.setFont(new Font("Anton", Font.PLAIN, 16));
		Info_telefono.setBounds(420, 326, 320, 22);
		panel_2.add(Info_telefono);

		JTextField Info_correo = new JTextField(" ");
		Info_correo.setBackground(new Color(204, 204, 204));
		Info_correo.setFont(new Font("Anton", Font.PLAIN, 16));
		Info_correo.setBounds(420, 362, 320, 22);
		panel_2.add(Info_correo);

		JTextField Info_horarrio = new JTextField("");
		Info_horarrio.setBackground(new Color(204, 204, 204));
		Info_horarrio.setFont(new Font("Anton", Font.PLAIN, 16));
		Info_horarrio.setBounds(272, 217, 468, 65);
		panel_2.add(Info_horarrio);

		// comobox//////////////////////////////////////////////////////////////////////////////////////////////////////////////

		UIManager.put("ComboBox.buttonBackground", new Color(255, 205, 17));
		JComboBox comboBox_clases = new JComboBox();
		comboBox_clases.setBackground(new Color(204, 204, 204));
		comboBox_clases.setFont(new Font("Anton", Font.PLAIN, 16));
		comboBox_clases.setBounds(420, 293, 320, 22);
		String[] clases = { "NINGUNA", "TECNICA EN MAQUINAS", "TECNICA EN MAQUINAS", "YOGA RELAX", "HIIT FUNCIONAL",
				"SPINNING INTENSO" };
		for (String clase : clases) {
			comboBox_clases.addItem(clase);
		}
		panel_2.add(comboBox_clases);

		// botones de accion para el cliente
		// ///////////////////////////////////////////////////////////////////////
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
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Instructores();
			}
		});
		boton_cancelar.setFont(new Font("Anton", Font.PLAIN, 18));
		boton_cancelar.setBackground(new Color(255, 0, 0));
		boton_cancelar.setBounds(554, 510, 160, 43);
		panel_2.add(boton_cancelar);

		JButton boton_cargargar_foto = new JButton("Cargar foto");
		boton_cargargar_foto.setFont(new Font("Anton", Font.PLAIN, 11));
		boton_cargargar_foto.setBackground(new Color(255, 205, 17));
		boton_cargargar_foto.setBounds(80, 355, 104, 22);
		panel_2.add(boton_cargargar_foto);

		// Botones laterales
		// //////////////////////////////////////////////////////////////////////////////////////////////
		JButton boton_INICIO = new JButton("INICIO");// boton de inicio
		boton_INICIO.setBackground(new Color(255, 205, 17));
		boton_INICIO.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_INICIO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Panel_inicio();

			}
		});
		boton_INICIO.setBounds(10, 86, 136, 71);
		panel.add(boton_INICIO);

		JButton boton_CLIENTES = new JButton("CLIENTES");// boton de clientes
		boton_CLIENTES.setBackground(new Color(255, 205, 17));
		boton_CLIENTES.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CLIENTES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Clientes();
			}
		});
		boton_CLIENTES.setBounds(10, 168, 136, 71);
		panel.add(boton_CLIENTES);

		JButton boton_TARIFAS = new JButton("TARIFAS");// boton de tarifas
		boton_TARIFAS.setBackground(new Color(255, 205, 17));
		boton_TARIFAS.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_TARIFAS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Tarifas();
			}
		});
		boton_TARIFAS.setBounds(10, 250, 136, 71);
		panel.add(boton_TARIFAS);

		JButton boton_INSTRUCTORES = new JButton("INSTRUCTORES");// boton de instructores
		boton_INSTRUCTORES.setBackground(new Color(255, 255, 255));
		boton_INSTRUCTORES.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_INSTRUCTORES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		boton_INSTRUCTORES.setBounds(10, 332, 136, 71);
		panel.add(boton_INSTRUCTORES);

		JButton boton_CLASES = new JButton("CLASES");// boton de clases
		boton_CLASES.setBackground(new Color(255, 205, 17));
		boton_CLASES.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CLASES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Clases();

			}
		});
		boton_CLASES.setBounds(10, 414, 136, 71);
		panel.add(boton_CLASES);

		JButton boton_CHECADOR = new JButton("CHECADOR");// boton de checador
		boton_CHECADOR.setBackground(new Color(255, 205, 17));
		boton_CHECADOR.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CHECADOR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Panel_checador();
			}
		});
		boton_CHECADOR.setBounds(10, 496, 136, 71);
		panel.add(boton_CHECADOR);

		JButton boton_CERRAR_SESION = new JButton("CERRAR SESION");
		boton_CERRAR_SESION.setBackground(new Color(255, 205, 17));
		boton_CERRAR_SESION.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CERRAR_SESION.addActionListener(e -> {
			JDialog dialog = new JDialog(frame, "Cerrar sesión", true);
			dialog.setSize(400, 220);
			dialog.setLocationRelativeTo(frame);
			dialog.setUndecorated(true);
			dialog.setLayout(null);

			// Panel principal - AHORA CON TAMAÑO COMPLETO
			JPanel Cerrar_sesion = new JPanel();
			Cerrar_sesion.setBackground(new Color(255, 255, 255));
			Cerrar_sesion.setBounds(0, 0, 400, 220);
			Cerrar_sesion.setLayout(null);
			dialog.add(Cerrar_sesion);

			// Panel superior azul
			JPanel panel_complemento = new JPanel();
			panel_complemento.setBackground(new Color(81, 151, 255));
			panel_complemento.setBounds(0, 0, 400, 33);
			Cerrar_sesion.add(panel_complemento);

			// Etiqueta con la pregunta
			JLabel pregunta_de_confirmacion = new JLabel(
					"<html><div style='text-align: center;'>Está a punto de cerrar sesión<br>¿Desea continuar?</div></html>");
			pregunta_de_confirmacion.setFont(new Font("Anton", Font.PLAIN, 16));
			pregunta_de_confirmacion.setBounds(90, 44, 310, 60);
			Cerrar_sesion.add(pregunta_de_confirmacion);

			// Botón Cancelar
			JButton boton_cancelar_alerta = new JButton("Cancelar");
			boton_cancelar_alerta.setForeground(Color.WHITE);
			boton_cancelar_alerta.setFont(new Font("Anton", Font.PLAIN, 14));
			boton_cancelar_alerta.setBackground(Color.RED);
			boton_cancelar_alerta.setBounds(50, 140, 120, 35);
			Cerrar_sesion.add(boton_cancelar_alerta);

			// Botón Aceptar
			JButton boton_aceptar = new JButton("Aceptar");
			boton_aceptar.setBackground(new Color(0, 206, 82));
			boton_aceptar.setForeground(Color.WHITE);
			boton_aceptar.setFont(new Font("Anton", Font.PLAIN, 14));
			boton_aceptar.setBounds(230, 140, 120, 35);
			Cerrar_sesion.add(boton_aceptar);

			// Acciones de los botones
			boton_cancelar_alerta.addActionListener(ev -> dialog.dispose());
			boton_aceptar.addActionListener(ev -> {
				dialog.dispose();
				closeSession(frame);
			});

			dialog.setVisible(true);
		});
		boton_CERRAR_SESION.setBounds(10, 579, 136, 71);
		panel.add(boton_CERRAR_SESION);

		frame.add(panel);
		frame.repaint();
		frame.revalidate();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public void Registro_de_clase(int nombreclase) {
		try {
			UIManager.setLookAndFeel(new FlatLightLaf());
			UIManager.put("Button.arc", 8);
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
		panel_1.setBounds(0, 0, 1084, 75);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(10, 11, 53, 53);
		ImageIcon icon = new ImageIcon(getClass().getResource("/Imagenes/logo sin letras.png"));
		Image imagen = icon.getImage().getScaledInstance(53, 53, Image.SCALE_SMOOTH);
		lblNewLabel_1.setIcon(new ImageIcon(imagen));
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

		JLabel lblNewLabel = new JLabel("REGISTRO: YOGA RELAX");
		lblNewLabel.setFont(new Font("Anton", Font.PLAIN, 26));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(60, 11, 508, 28);
		panel_3.add(lblNewLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 61, 898, 56);
		panel_2.add(scrollPane);

		// Datos de ejemplo
		Object[][] data = { { "Laura Mendez", "laura.mendez@evolvefit.com", "MATUTINO", "Lunes y Viernes" }

		};

		String[] columnNames = { "Entrenador", "Correo", "Turno", "Horario" };

		JTable table = new JTable(data, columnNames);
		table.setFont(new Font("Anton", Font.PLAIN, 12));
		table.setBackground(new Color(204, 204, 204));
		table.setRowHeight(30);
		JTableHeader header = table.getTableHeader();
		header.setBackground(Color.BLACK);
		header.setForeground(Color.WHITE);
		header.setFont(new Font("Anton", Font.PLAIN, 14));
		header.setReorderingAllowed(false);
		scrollPane.setViewportView(table);

		Object[][] data2 = { { 3, "Renata", "Ochoa", "612 765 0000", "Renata_123@gmail.com" } };

		String[] columnas2 = { "ID cliente", "Nombre(s)", "Primer apellido", "Teléfono", "Correo electrónico",
				"Eliminar" };

		JScrollPane scrollPane_Pagos = new JScrollPane();// tabla de pagos
		scrollPane_Pagos.setBounds(10, 128, 898, 56);
		panel_2.add(scrollPane_Pagos);
		DefaultTableModel model = new DefaultTableModel(data2, columnas2) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return column == 5;
			}
		};

		JTable table_1 = new JTable(model);
		table_1.setFont(new Font("Anton", Font.PLAIN, 12));
		table_1.setBackground(new Color(204, 204, 204));
		table_1.setRowHeight(30);
		scrollPane_Pagos.setViewportView(table_1);

		JTableHeader header2 = table_1.getTableHeader();
		header2.setBackground(Color.BLACK);
		header2.setForeground(Color.WHITE);
		header2.setFont(new Font("Anton", Font.PLAIN, 14));
		header2.setReorderingAllowed(false);
		scrollPane_Pagos.setViewportView(table_1);

		// Renderizar botones en la tabla
		table_1.getColumn("Eliminar").setCellRenderer(new ButtonRenderer3("Eliminar"));
		table_1.getColumn("Eliminar").setCellEditor(new ButtonEditor3(new JCheckBox(), "Eliminar", table_1));

		// Botón Añadir cliente
		JButton boton_descargar = new JButton("Descargar");
		boton_descargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		boton_descargar.setForeground(new Color(0, 0, 0));
		boton_descargar.setBackground(new Color(255, 205, 17));
		boton_descargar.setFont(new Font("Anton", Font.PLAIN, 14));
		boton_descargar.setBounds(763, 510, 145, 43);
		panel_2.add(boton_descargar);

		JButton boton_regresar = new JButton("Regresar");
		boton_regresar.setForeground(Color.BLACK);
		boton_regresar.setFont(new Font("Anton", Font.PLAIN, 14));
		boton_regresar.setBackground(new Color(255, 205, 17));
		boton_regresar.setBounds(608, 510, 145, 43);
		boton_regresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Clases();
			}
		});
		panel_2.add(boton_regresar);

		// Botones laterales de la ventana
		JButton boton_INICIO = new JButton("INICIO");
		boton_INICIO.setBackground(new Color(255, 205, 17));
		boton_INICIO.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_INICIO.setBounds(10, 86, 136, 71);
		boton_INICIO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Panel_inicio();

			}
		});
		panel.add(boton_INICIO);

		JButton boton_CLIENTES = new JButton("CLIENTES");
		boton_CLIENTES.setBackground(new Color(255, 205, 17));
		boton_CLIENTES.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CLIENTES.setBounds(10, 168, 136, 71);
		boton_CLIENTES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Clientes();
			}
		});
		panel.add(boton_CLIENTES);

		JButton boton_TARIFAS = new JButton("TARIFAS");
		boton_TARIFAS.setBackground(new Color(255, 205, 17));
		boton_TARIFAS.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_TARIFAS.setBounds(10, 250, 136, 71);
		boton_TARIFAS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Tarifas();
			}
		});
		panel.add(boton_TARIFAS);

		JButton boton_INSTRUCTORES = new JButton("INSTRUCTORES");
		boton_INSTRUCTORES.setBackground(new Color(255, 205, 17));
		boton_INSTRUCTORES.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_INSTRUCTORES.setBounds(10, 332, 136, 71);
		boton_INSTRUCTORES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Instructores();
			}
		});
		panel.add(boton_INSTRUCTORES);

		JButton boton_CLASES = new JButton("CLASES");
		boton_CLASES.setBackground(new Color(255, 255, 255));
		boton_CLASES.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CLASES.setBounds(10, 414, 136, 71);
		panel.add(boton_CLASES);

		JButton boton_CHECADOR = new JButton("CHECADOR");
		boton_CHECADOR.setBackground(new Color(255, 205, 17));
		boton_CHECADOR.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CHECADOR.setBounds(10, 496, 136, 71);
		boton_CHECADOR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Panel_checador();
			}
		});
		panel.add(boton_CHECADOR);

		JButton boton_CERRAR_SESION = new JButton("CERRAR SESION");
		boton_CERRAR_SESION.setBackground(new Color(255, 205, 17));
		boton_CERRAR_SESION.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CERRAR_SESION.addActionListener(e -> {
			JDialog dialog = new JDialog(frame, "Cerrar sesión", true);
			dialog.setSize(400, 220);
			dialog.setLocationRelativeTo(frame);
			dialog.setUndecorated(true);
			dialog.setLayout(null);

			// Panel principal - AHORA CON TAMAÑO COMPLETO
			JPanel Cerrar_sesion = new JPanel();
			Cerrar_sesion.setBackground(new Color(255, 255, 255));
			Cerrar_sesion.setBounds(0, 0, 400, 220);
			Cerrar_sesion.setLayout(null);
			dialog.add(Cerrar_sesion);

			// Panel superior azul
			JPanel panel_complemento = new JPanel();
			panel_complemento.setBackground(new Color(81, 151, 255));
			panel_complemento.setBounds(0, 0, 400, 33);
			Cerrar_sesion.add(panel_complemento);

			// Etiqueta con la pregunta
			JLabel pregunta_de_confirmacion = new JLabel(
					"<html><div style='text-align: center;'>Está a punto de cerrar sesión<br>¿Desea continuar?</div></html>");
			pregunta_de_confirmacion.setFont(new Font("Anton", Font.PLAIN, 16));
			pregunta_de_confirmacion.setBounds(90, 44, 310, 60);
			Cerrar_sesion.add(pregunta_de_confirmacion);

			// Botón Cancelar
			JButton boton_cancelar_alerta = new JButton("Cancelar");
			boton_cancelar_alerta.setForeground(Color.WHITE);
			boton_cancelar_alerta.setFont(new Font("Anton", Font.PLAIN, 14));
			boton_cancelar_alerta.setBackground(Color.RED);
			boton_cancelar_alerta.setBounds(50, 140, 120, 35);
			Cerrar_sesion.add(boton_cancelar_alerta);

			// Botón Aceptar
			JButton boton_aceptar = new JButton("Aceptar");
			boton_aceptar.setBackground(new Color(0, 206, 82));
			boton_aceptar.setForeground(Color.WHITE);
			boton_aceptar.setFont(new Font("Anton", Font.PLAIN, 14));
			boton_aceptar.setBounds(230, 140, 120, 35);
			Cerrar_sesion.add(boton_aceptar);

			// Acciones de los botones
			boton_cancelar_alerta.addActionListener(ev -> dialog.dispose());
			boton_aceptar.addActionListener(ev -> {
				dialog.dispose();
				closeSession(frame);
			});

			dialog.setVisible(true);
		});
		boton_CERRAR_SESION.setBounds(10, 579, 136, 71);
		panel.add(boton_CERRAR_SESION);

		frame.add(panel);
		frame.repaint();
		frame.revalidate();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	// Renderer para mostrar botones en tabla
	public class ButtonRenderer3 extends JButton implements TableCellRenderer {
		public ButtonRenderer3(String label) {
			setOpaque(true);
			setForeground(Color.BLACK);
			setBackground(new Color(255, 0, 0));
			setFont(new Font("Anton", Font.PLAIN, 14));
			setHorizontalAlignment(SwingConstants.CENTER);

			// Cargar icono según etiqueta
			if (label.equals("Eliminar")) {
				setIcon(loadIcon("/Imagenes/eliminar.png"));
				setBackground(new Color(255, 0, 0));
			}
			setText(null);
		}

		private ImageIcon loadIcon(String path) {
			java.net.URL imgURL = getClass().getResource(path);
			if (imgURL != null) {
				ImageIcon icon = new ImageIcon(imgURL);
				Image img = icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
				return new ImageIcon(img);
			} else {
				System.err.println("No se pudo encontrar la imagen: " + path);
				return null;
			}
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {

			setSize(table.getColumnModel().getColumn(column).getWidth(), table.getRowHeight(row));
			return this;
		}
	}

	// Editor para que los botones con imagen funcionen en tabla
	public class ButtonEditor3 extends DefaultCellEditor {
		protected JButton button;
		private String label;
		private boolean clicked;
		private int row;
		private JTable table_1;

		public ButtonEditor3(JCheckBox checkBox, String label, JTable table) {
			super(checkBox);
			this.label = label;
			this.table_1 = table;
			button = new JButton();
			button.setOpaque(true);
			button.setForeground(Color.BLACK);
			button.setBackground(new Color(255, 0, 0));
			button.setFont(new Font("Anton", Font.PLAIN, 14));
			button.setHorizontalAlignment(SwingConstants.CENTER);

			// Cargar icono según etiqueta
			if (label.equals("Eliminar")) {
				button.setIcon(loadIcon("/Imagenes/eliminar.png"));
			}
			button.setText(null); // Sin texto

			button.addActionListener(e -> fireEditingStopped());
		}

		private ImageIcon loadIcon(String path) {
			java.net.URL imgURL = getClass().getResource(path);
			if (imgURL != null) {
				ImageIcon icon = new ImageIcon(imgURL);
				Image img = icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
				return new ImageIcon(img);
			} else {
				System.err.println("No se pudo encontrar la imagen: " + path);
				return null;
			}
		}

		@Override
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
				int column) {
			this.row = row;
			clicked = true;

			button.setSize(table.getColumnModel().getColumn(column).getWidth(), table.getRowHeight(row));
			return button;
		}

		public Object getCellEditorValue() {
			if (clicked) {
				if (label.equals("Eliminar")) {
					// Crear el JDialog con la alerta personalizada
					JDialog dialog = new JDialog((Frame) null, "Confirmar Eliminación", true);
					dialog.setSize(400, 220);
					dialog.setLocationRelativeTo(null);
					dialog.setUndecorated(true);
					dialog.setLayout(null);

					// Panel principal de la alerta
					JPanel confirmar_eliminacion = new JPanel();
					confirmar_eliminacion.setBackground(new Color(255, 255, 255));
					confirmar_eliminacion.setBounds(0, 0, 400, 220);
					confirmar_eliminacion.setLayout(null);
					dialog.add(confirmar_eliminacion);

					// Panel superior de la alerta (azul)
					JPanel panel_complemento = new JPanel();
					panel_complemento.setBackground(new Color(81, 151, 255));
					panel_complemento.setBounds(0, 0, 400, 33);
					confirmar_eliminacion.add(panel_complemento);

					// Etiqueta de confirmación
					JLabel pregunta_de_confirmacion = new JLabel(
							"<html><div style='text-align: center;'>El miembro se borrará permanentemente<br>¿Desea continuar?</div></html>");
					pregunta_de_confirmacion.setFont(new Font("Anton", Font.PLAIN, 16));
					pregunta_de_confirmacion.setBounds(66, 44, 346, 59);
					confirmar_eliminacion.add(pregunta_de_confirmacion);

					// Botón "Cancelar"
					JButton boton_cancelar_alerta = new JButton("Cancelar");
					boton_cancelar_alerta.setForeground(Color.WHITE);
					boton_cancelar_alerta.setFont(new Font("Anton", Font.PLAIN, 14));
					boton_cancelar_alerta.setBackground(Color.RED);
					boton_cancelar_alerta.setBounds(50, 140, 120, 35);
					confirmar_eliminacion.add(boton_cancelar_alerta);

					// Botón "Aceptar"
					JButton boton_aceptar = new JButton("Aceptar");
					boton_aceptar.setBackground(new Color(0, 206, 82));
					boton_aceptar.setForeground(Color.WHITE);
					boton_aceptar.setFont(new Font("Anton", Font.PLAIN, 14));
					boton_aceptar.setBounds(230, 140, 120, 35);
					confirmar_eliminacion.add(boton_aceptar);

					// Acción al hacer clic en "Cancelar"
					boton_cancelar_alerta.addActionListener(ev -> dialog.dispose());

					// Acción al hacer clic en "Aceptar"
					boton_aceptar.addActionListener(ev -> {
						dialog.dispose();

					});

					dialog.setVisible(true); // Mostrar el diálogo de confirmación
				}
			}
			clicked = false;
			return label;

		}

		@Override
		public boolean stopCellEditing() {
			clicked = false;
			return super.stopCellEditing();
		}

		@Override
		protected void fireEditingStopped() {
			super.fireEditingStopped();
		}
	}

	public void Editar_eliminar_y_añadir_clases() {
		try {
			UIManager.setLookAndFeel(new FlatLightLaf());
			UIManager.put("Button.arc", 8);
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
		panel_1.setBounds(0, 0, 1084, 75);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(10, 11, 53, 53);
		ImageIcon icon = new ImageIcon(getClass().getResource("/Imagenes/logo sin letras.png"));
		Image imagen = icon.getImage().getScaledInstance(53, 53, Image.SCALE_SMOOTH);
		lblNewLabel_1.setIcon(new ImageIcon(imagen));
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

		JLabel lblNewLabel = new JLabel("EDITAR CLASES");
		lblNewLabel.setFont(new Font("Anton", Font.PLAIN, 26));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(60, 11, 507, 28);
		panel_3.add(lblNewLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 61, 898, 450);
		panel_2.add(scrollPane);

		// Datos de ejemplo
		Object[][] data = { { "YOGA RELAX", "Laura Mendez", "VESPERTINO", "Lunes y Viernes ", "", "" } };

		String[] columnNames = { "Nombre de la clase", "Entrenador", "Turno", "Horario", "Editar", "Eliminar" };

		DefaultTableModel model = new DefaultTableModel(data, columnNames) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return column == 4 || column == 5;
			}
		};

		JTable table = new JTable(model);
		table.setFont(new Font("Anton", Font.PLAIN, 12));
		table.setBackground(new Color(204, 204, 204));
		table.setRowHeight(30);
		JTableHeader header = table.getTableHeader();
		header.setBackground(Color.BLACK);
		header.setForeground(Color.WHITE);
		header.setFont(new Font("Anton", Font.PLAIN, 14));
		header.setReorderingAllowed(false);
		scrollPane.setViewportView(table);

		// Renderizar botones en la tabla
		table.getColumn("Editar").setCellRenderer(new ButtonRenderer("Editar"));
		table.getColumn("Editar").setCellEditor(new ButtonEditor(new JCheckBox(), "Editar", table));

		table.getColumn("Eliminar").setCellRenderer(new ButtonRenderer("Eliminar"));
		table.getColumn("Eliminar").setCellEditor(new ButtonEditor(new JCheckBox(), "Eliminar", table));

		// Botón Añadir cliente
		JButton boton_Añadir_clase = new JButton("Añadir clase");
		boton_Añadir_clase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				UsersController uc = new UsersController();
				uc.Añadir_clases();
			}
		});
		boton_Añadir_clase.setForeground(new Color(255, 255, 255));
		boton_Añadir_clase.setBackground(new Color(0, 206, 82));
		boton_Añadir_clase.setFont(new Font("Anton", Font.PLAIN, 14));
		boton_Añadir_clase.setBounds(763, 522, 145, 31);
		panel_2.add(boton_Añadir_clase);

		JButton boton_cancelar = new JButton("Cancelar");
		boton_cancelar.setForeground(new Color(255, 255, 255));
		boton_cancelar.setFont(new Font("Anton", Font.PLAIN, 14));
		boton_cancelar.setBackground(new Color(255, 0, 0));
		boton_cancelar.setBounds(608, 522, 145, 31);
		boton_cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Clases();
			}
		});
		panel_2.add(boton_cancelar);

		// Botones laterales de la ventana
		JButton boton_INICIO = new JButton("INICIO");
		boton_INICIO.setBackground(new Color(255, 205, 17));
		boton_INICIO.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_INICIO.setBounds(10, 86, 136, 71);
		boton_INICIO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Panel_inicio();

			}
		});
		panel.add(boton_INICIO);

		JButton boton_CLIENTES = new JButton("CLIENTES");
		boton_CLIENTES.setBackground(new Color(255, 205, 17));
		boton_CLIENTES.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CLIENTES.setBounds(10, 168, 136, 71);
		boton_CLIENTES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Clientes();
			}
		});
		panel.add(boton_CLIENTES);

		JButton boton_TARIFAS = new JButton("TARIFAS");
		boton_TARIFAS.setBackground(new Color(255, 205, 17));
		boton_TARIFAS.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_TARIFAS.setBounds(10, 250, 136, 71);
		boton_TARIFAS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Tarifas();
			}
		});
		panel.add(boton_TARIFAS);

		JButton boton_INSTRUCTORES = new JButton("INSTRUCTORES");
		boton_INSTRUCTORES.setBackground(new Color(255, 205, 17));
		boton_INSTRUCTORES.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_INSTRUCTORES.setBounds(10, 332, 136, 71);
		boton_INSTRUCTORES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Instructores();
			}
		});
		panel.add(boton_INSTRUCTORES);

		JButton boton_CLASES = new JButton("CLASES");
		boton_CLASES.setBackground(new Color(255, 255, 255));
		boton_CLASES.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CLASES.setBounds(10, 414, 136, 71);
		panel.add(boton_CLASES);

		JButton boton_CHECADOR = new JButton("CHECADOR");
		boton_CHECADOR.setBackground(new Color(255, 205, 17));
		boton_CHECADOR.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CHECADOR.setBounds(10, 496, 136, 71);
		boton_CHECADOR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Panel_checador();
			}
		});
		panel.add(boton_CHECADOR);

		JButton boton_CERRAR_SESION = new JButton("CERRAR SESION");
		boton_CERRAR_SESION.setBackground(new Color(255, 205, 17));
		boton_CERRAR_SESION.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CERRAR_SESION.addActionListener(e -> {
			JDialog dialog = new JDialog(frame, "Cerrar sesión", true);
			dialog.setSize(400, 220);
			dialog.setLocationRelativeTo(frame);
			dialog.setUndecorated(true);
			dialog.setLayout(null);

			// Panel principal - AHORA CON TAMAÑO COMPLETO
			JPanel Cerrar_sesion = new JPanel();
			Cerrar_sesion.setBackground(new Color(255, 255, 255));
			Cerrar_sesion.setBounds(0, 0, 400, 220);
			Cerrar_sesion.setLayout(null);
			dialog.add(Cerrar_sesion);

			// Panel superior azul
			JPanel panel_complemento = new JPanel();
			panel_complemento.setBackground(new Color(81, 151, 255));
			panel_complemento.setBounds(0, 0, 400, 33);
			Cerrar_sesion.add(panel_complemento);

			// Etiqueta con la pregunta
			JLabel pregunta_de_confirmacion = new JLabel(
					"<html><div style='text-align: center;'>Está a punto de cerrar sesión<br>¿Desea continuar?</div></html>");
			pregunta_de_confirmacion.setFont(new Font("Anton", Font.PLAIN, 16));
			pregunta_de_confirmacion.setBounds(90, 44, 310, 60);
			Cerrar_sesion.add(pregunta_de_confirmacion);

			// Botón Cancelar
			JButton boton_cancelar_alerta = new JButton("Cancelar");
			boton_cancelar_alerta.setForeground(Color.WHITE);
			boton_cancelar_alerta.setFont(new Font("Anton", Font.PLAIN, 14));
			boton_cancelar_alerta.setBackground(Color.RED);
			boton_cancelar_alerta.setBounds(50, 140, 120, 35);
			Cerrar_sesion.add(boton_cancelar_alerta);

			// Botón Aceptar
			JButton boton_aceptar = new JButton("Aceptar");
			boton_aceptar.setBackground(new Color(0, 206, 82));
			boton_aceptar.setForeground(Color.WHITE);
			boton_aceptar.setFont(new Font("Anton", Font.PLAIN, 14));
			boton_aceptar.setBounds(230, 140, 120, 35);
			Cerrar_sesion.add(boton_aceptar);

			// Acciones de los botones
			boton_cancelar_alerta.addActionListener(ev -> dialog.dispose());
			boton_aceptar.addActionListener(ev -> {
				dialog.dispose();
				closeSession(frame);
			});

			dialog.setVisible(true);
		});
		boton_CERRAR_SESION.setBounds(10, 579, 136, 71);
		panel.add(boton_CERRAR_SESION);

		frame.add(panel);
		frame.repaint();
		frame.revalidate();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	// Renderer para mostrar botones en tabla
	public class ButtonRenderer extends JButton implements TableCellRenderer {
		public ButtonRenderer(String label) {
			setOpaque(true);
			setForeground(Color.BLACK);
			setBackground(new Color(255, 205, 17));
			setFont(new Font("Anton", Font.PLAIN, 14));
			setHorizontalAlignment(SwingConstants.CENTER);

			// Cargar icono según etiqueta
			if (label.equals("Editar")) {
				setIcon(loadIcon("/Imagenes/editar.png"));
				setBackground(new Color(255, 205, 17));
			} else if (label.equals("Eliminar")) {
				setIcon(loadIcon("/Imagenes/eliminar.png"));
				setBackground(new Color(255, 0, 0));
			}
			setText(null);
		}

		private ImageIcon loadIcon(String path) {
			java.net.URL imgURL = getClass().getResource(path);
			if (imgURL != null) {
				ImageIcon icon = new ImageIcon(imgURL);
				Image img = icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
				return new ImageIcon(img);
			} else {
				System.err.println("No se pudo encontrar la imagen: " + path);
				return null;
			}
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			return this;
		}
	}

	// Editor para que los botones con imagen funcionen en tabla
	public class ButtonEditor extends DefaultCellEditor {
		protected JButton button;
		private String label;
		private boolean clicked;
		private int row;
		private JTable table;

		public ButtonEditor(JCheckBox checkBox, String label, JTable table) {
			super(checkBox);
			this.label = label;
			this.table = table;
			button = new JButton();
			button.setOpaque(true);
			button.setForeground(Color.BLACK);
			button.setBackground(new Color(255, 205, 17));
			button.setFont(new Font("Anton", Font.PLAIN, 14));
			button.setHorizontalAlignment(SwingConstants.CENTER);

			// Cargar icono según etiqueta
			if (label.equals("Editar")) {
				button.setIcon(loadIcon("/Imagenes/editar.png"));
			} else if (label.equals("Eliminar")) {
				button.setIcon(loadIcon("/Imagenes/eliminar.png"));
			}
			button.setText(null); // Sin texto

			button.addActionListener(e -> fireEditingStopped());
		}

		private ImageIcon loadIcon(String path) {
			java.net.URL imgURL = getClass().getResource(path);
			if (imgURL != null) {
				ImageIcon icon = new ImageIcon(imgURL);
				Image img = icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
				return new ImageIcon(img);
			} else {
				System.err.println("No se pudo encontrar la imagen: " + path);
				return null;
			}
		}

		@Override
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
				int column) {
			this.row = row;
			clicked = true;
			return button;
		}

		@Override
		public Object getCellEditorValue() {
			if (clicked) {
				if (label.equals("Editar")) {
					String nombreClase = (String) table.getValueAt(row, 0); // Obtén el nombre
					Window window = SwingUtilities.getWindowAncestor(table);
					if (window != null) {
						window.dispose();
					}
					UsersController uc = new UsersController();
					uc.Editar_clases(nombreClase);
					// Aquí conecta con la base de datos para borrar el registro según el ID de la
					// fila seleccionada
					// y luego actualiza la tabla recargando datos.
				} else if (label.equals("Eliminar")) {

					JDialog dialog = new JDialog((Frame) null, "Confirmar Eliminación", true);
					dialog.setSize(400, 220);
					dialog.setLocationRelativeTo(null);
					dialog.setUndecorated(true);
					dialog.setLayout(null);

					// Panel principal de la alerta
					JPanel confirmar_eliminacion = new JPanel();
					confirmar_eliminacion.setBackground(new Color(255, 255, 255));
					confirmar_eliminacion.setBounds(0, 0, 400, 220);
					confirmar_eliminacion.setLayout(null);
					dialog.add(confirmar_eliminacion);

					// Panel superior de la alerta (azul)
					JPanel panel_complemento = new JPanel();
					panel_complemento.setBackground(new Color(81, 151, 255));
					panel_complemento.setBounds(0, 0, 400, 33);
					confirmar_eliminacion.add(panel_complemento);

					// Etiqueta de confirmación
					JLabel pregunta_de_confirmacion = new JLabel(
							"<html><div style='text-align: center;'>La clase se borrará permanentemente<br>¿Desea continuar?</div></html>");
					pregunta_de_confirmacion.setFont(new Font("Anton", Font.PLAIN, 16));
					pregunta_de_confirmacion.setBounds(66, 44, 346, 59);
					confirmar_eliminacion.add(pregunta_de_confirmacion);

					// Botón "Cancelar"
					JButton boton_cancelar_alerta = new JButton("Cancelar");
					boton_cancelar_alerta.setForeground(Color.WHITE);
					boton_cancelar_alerta.setFont(new Font("Anton", Font.PLAIN, 14));
					boton_cancelar_alerta.setBackground(Color.RED);
					boton_cancelar_alerta.setBounds(50, 140, 120, 35);
					confirmar_eliminacion.add(boton_cancelar_alerta);

					// Botón "Aceptar"
					JButton boton_aceptar = new JButton("Aceptar");
					boton_aceptar.setBackground(new Color(0, 206, 82));
					boton_aceptar.setForeground(Color.WHITE);
					boton_aceptar.setFont(new Font("Anton", Font.PLAIN, 14));
					boton_aceptar.setBounds(230, 140, 120, 35);
					confirmar_eliminacion.add(boton_aceptar);

					// Acción al hacer clic en "Cancelar"
					boton_cancelar_alerta.addActionListener(ev -> dialog.dispose());

					// Acción al hacer clic en "Aceptar"
					boton_aceptar.addActionListener(ev -> {
						dialog.dispose();

					});

					dialog.setVisible(true);
				}
			}
			clicked = false;
			return label;
		}

		@Override
		public boolean stopCellEditing() {
			clicked = false;
			return super.stopCellEditing();
		}

		@Override
		protected void fireEditingStopped() {
			super.fireEditingStopped();
		}
	}

	public void Editar_clases(String nombreClase) {
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
		panel_1.setBounds(0, 0, 1084, 75);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(10, 11, 53, 53);
		ImageIcon icon = new ImageIcon(getClass().getResource("/Imagenes/logo sin letras.png"));
		Image imagen = icon.getImage().getScaledInstance(53, 53, Image.SCALE_SMOOTH);
		lblNewLabel_1.setIcon(new ImageIcon(imagen));
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

		JLabel lblNewLabel = new JLabel("EDITAR CLASES");// titulo de inicio
		lblNewLabel.setFont(new Font("Anton", Font.PLAIN, 26));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(60, 11, 309, 28);
		panel_3.add(lblNewLabel);
		ImageIcon p1 = new ImageIcon("/Imagenes/imagen credencial.png");
		Image p2 = p1.getImage();
		Image p3 = p2.getScaledInstance(100, 150, Image.SCALE_SMOOTH);

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
		// info de
		// intructor///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		JTextField Info_nombre = new JTextField("YOGA RELAX");
		Info_nombre.setBackground(new Color(204, 204, 204));
		Info_nombre.setFont(new Font("Anton", Font.PLAIN, 20));
		Info_nombre.setBounds(247, 83, 328, 37);
		panel_2.add(Info_nombre);

		JTextField Info_horarrio = new JTextField("Lunes, Miércoles y Viernes de 8:00 am a 12:00 pm");
		Info_horarrio.setBackground(new Color(204, 204, 204));
		Info_horarrio.setFont(new Font("Anton", Font.PLAIN, 20));
		Info_horarrio.setBounds(58, 356, 517, 37);
		panel_2.add(Info_horarrio);

		// comobox//////////////////////////////////////////////////////////////////////////////////////////////////////////////

		UIManager.put("ComboBox.buttonBackground", new Color(255, 205, 17));
		JComboBox comboBox_turno = new JComboBox();
		comboBox_turno.setBackground(new Color(204, 204, 204));
		comboBox_turno.setFont(new Font("Anton", Font.PLAIN, 20));
		comboBox_turno.setBounds(123, 238, 200, 31);
		String[] turnos = { "NINGUNA", "MATUTINO", "VESPERTINO", "MIXTO" };
		for (String turno : turnos) {
			comboBox_turno.addItem(turno);
		}
		panel_2.add(comboBox_turno);

		JComboBox comboBox_entrenador = new JComboBox();
		comboBox_entrenador.setFont(new Font("Anton", Font.PLAIN, 20));
		comboBox_entrenador.setBackground(new Color(204, 204, 204));
		comboBox_entrenador.setBounds(247, 161, 328, 31);
		String[] entrenador = { "NINGUNA", "Sebastian Torres", "Laura Mendez", "Camila Rodriguez", "Marco Rojas" };
		for (String entrenadores : entrenador) {
			comboBox_entrenador.addItem(entrenadores);
		}
		panel_2.add(comboBox_entrenador);

		// botones de accion para el instructor
		// ///////////////////////////////////////////////////////////////////////
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
				frame.dispose();
				UsersController uc = new UsersController();
				uc.Editar_eliminar_y_añadir_clases();

			}
		});
		boton_cancelar.setFont(new Font("Anton", Font.PLAIN, 18));
		boton_cancelar.setBackground(new Color(255, 0, 0));
		boton_cancelar.setBounds(554, 510, 160, 43);
		panel_2.add(boton_cancelar);

		// Botones laterales
		// //////////////////////////////////////////////////////////////////////////////////////////////
		JButton boton_INICIO = new JButton("INICIO");// boton de inicio
		boton_INICIO.setBackground(new Color(255, 205, 17));
		boton_INICIO.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_INICIO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Panel_inicio();

			}
		});
		boton_INICIO.setBounds(10, 86, 136, 71);
		panel.add(boton_INICIO);

		JButton boton_CLIENTES = new JButton("CLIENTES");// boton de clientes
		boton_CLIENTES.setBackground(new Color(255, 205, 17));
		boton_CLIENTES.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CLIENTES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Clientes();
			}
		});
		boton_CLIENTES.setBounds(10, 168, 136, 71);
		panel.add(boton_CLIENTES);

		JButton boton_TARIFAS = new JButton("TARIFAS");// boton de tarifas
		boton_TARIFAS.setBackground(new Color(255, 205, 17));
		boton_TARIFAS.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_TARIFAS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Tarifas();
			}
		});
		boton_TARIFAS.setBounds(10, 250, 136, 71);
		panel.add(boton_TARIFAS);

		JButton boton_INSTRUCTORES = new JButton("INSTRUCTORES");// boton de instructores
		boton_INSTRUCTORES.setBackground(new Color(255, 205, 17));
		boton_INSTRUCTORES.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_INSTRUCTORES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Instructores();
			}
		});
		boton_INSTRUCTORES.setBounds(10, 332, 136, 71);
		panel.add(boton_INSTRUCTORES);

		JButton boton_CLASES = new JButton("CLASES");// boton de clases
		boton_CLASES.setBackground(new Color(255, 255, 255));
		boton_CLASES.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CLASES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		boton_CLASES.setBounds(10, 414, 136, 71);
		panel.add(boton_CLASES);

		JButton boton_CHECADOR = new JButton("CHECADOR");// boton de checador
		boton_CHECADOR.setBackground(new Color(255, 205, 17));
		boton_CHECADOR.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CHECADOR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Panel_checador();
			}
		});
		boton_CHECADOR.setBounds(10, 496, 136, 71);
		panel.add(boton_CHECADOR);

		JButton boton_CERRAR_SESION = new JButton("CERRAR SESION");
		boton_CERRAR_SESION.setBackground(new Color(255, 205, 17));
		boton_CERRAR_SESION.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CERRAR_SESION.addActionListener(e -> {
			JDialog dialog = new JDialog(frame, "Cerrar sesión", true);
			dialog.setSize(400, 220);
			dialog.setLocationRelativeTo(frame);
			dialog.setUndecorated(true);
			dialog.setLayout(null);

			// Panel principal - AHORA CON TAMAÑO COMPLETO
			JPanel Cerrar_sesion = new JPanel();
			Cerrar_sesion.setBackground(new Color(255, 255, 255));
			Cerrar_sesion.setBounds(0, 0, 400, 220);
			Cerrar_sesion.setLayout(null);
			dialog.add(Cerrar_sesion);

			// Panel superior azul
			JPanel panel_complemento = new JPanel();
			panel_complemento.setBackground(new Color(81, 151, 255));
			panel_complemento.setBounds(0, 0, 400, 33);
			Cerrar_sesion.add(panel_complemento);

			// Etiqueta con la pregunta
			JLabel pregunta_de_confirmacion = new JLabel(
					"<html><div style='text-align: center;'>Está a punto de cerrar sesión<br>¿Desea continuar?</div></html>");
			pregunta_de_confirmacion.setFont(new Font("Anton", Font.PLAIN, 16));
			pregunta_de_confirmacion.setBounds(90, 44, 310, 60);
			Cerrar_sesion.add(pregunta_de_confirmacion);

			// Botón Cancelar
			JButton boton_cancelar_alerta = new JButton("Cancelar");
			boton_cancelar_alerta.setForeground(Color.WHITE);
			boton_cancelar_alerta.setFont(new Font("Anton", Font.PLAIN, 14));
			boton_cancelar_alerta.setBackground(Color.RED);
			boton_cancelar_alerta.setBounds(50, 140, 120, 35);
			Cerrar_sesion.add(boton_cancelar_alerta);

			// Botón Aceptar
			JButton boton_aceptar = new JButton("Aceptar");
			boton_aceptar.setBackground(new Color(0, 206, 82));
			boton_aceptar.setForeground(Color.WHITE);
			boton_aceptar.setFont(new Font("Anton", Font.PLAIN, 14));
			boton_aceptar.setBounds(230, 140, 120, 35);
			Cerrar_sesion.add(boton_aceptar);

			// Acciones de los botones
			boton_cancelar_alerta.addActionListener(ev -> dialog.dispose());
			boton_aceptar.addActionListener(ev -> {
				dialog.dispose();
				closeSession(frame);
			});

			dialog.setVisible(true);
		});
		boton_CERRAR_SESION.setBounds(10, 579, 136, 71);
		panel.add(boton_CERRAR_SESION);

		frame.add(panel);
		frame.repaint();
		frame.revalidate();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public void Añadir_clases() {
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
		panel_1.setBounds(0, 0, 1084, 75);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(10, 11, 53, 53);
		ImageIcon icon = new ImageIcon(getClass().getResource("/Imagenes/logo sin letras.png"));
		Image imagen = icon.getImage().getScaledInstance(53, 53, Image.SCALE_SMOOTH);
		lblNewLabel_1.setIcon(new ImageIcon(imagen));
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

		JLabel lblNewLabel = new JLabel("AÑADIR CLASE");// titulo de inicio
		lblNewLabel.setFont(new Font("Anton", Font.PLAIN, 26));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(60, 11, 309, 28);
		panel_3.add(lblNewLabel);
		ImageIcon p1 = new ImageIcon("/Imagenes/imagen credencial.png");
		Image p2 = p1.getImage();
		Image p3 = p2.getScaledInstance(100, 150, Image.SCALE_SMOOTH);

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
		// info de
		// intructor///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		JTextField Info_nombre = new JTextField("");
		Info_nombre.setBackground(new Color(204, 204, 204));
		Info_nombre.setFont(new Font("Anton", Font.PLAIN, 20));
		Info_nombre.setBounds(247, 83, 328, 37);
		panel_2.add(Info_nombre);

		JTextField Info_horarrio = new JTextField("");
		Info_horarrio.setBackground(new Color(204, 204, 204));
		Info_horarrio.setFont(new Font("Anton", Font.PLAIN, 20));
		Info_horarrio.setBounds(58, 356, 517, 37);
		panel_2.add(Info_horarrio);

		// comobox//////////////////////////////////////////////////////////////////////////////////////////////////////////////

		UIManager.put("ComboBox.buttonBackground", new Color(255, 205, 17));
		JComboBox comboBox_turno = new JComboBox();
		comboBox_turno.setBackground(new Color(204, 204, 204));
		comboBox_turno.setFont(new Font("Anton", Font.PLAIN, 20));
		comboBox_turno.setBounds(123, 238, 200, 31);
		String[] turnos = { "NINGUNA", "MATUTINO", "VESPERTINO", "MIXTO" };
		for (String turno : turnos) {
			comboBox_turno.addItem(turno);
		}
		panel_2.add(comboBox_turno);

		JComboBox comboBox_entrenador = new JComboBox();
		comboBox_entrenador.setFont(new Font("Anton", Font.PLAIN, 20));
		comboBox_entrenador.setBackground(new Color(204, 204, 204));
		comboBox_entrenador.setBounds(247, 161, 328, 31);
		String[] entrenador = { "NINGUNA", "Sebastian Torres", "Laura Mendez", "Camila Rodriguez", "Marco Rojas" };
		for (String entrenadores : entrenador) {
			comboBox_entrenador.addItem(entrenadores);
		}
		panel_2.add(comboBox_entrenador);

		// botones de accion para el instructor
		// ///////////////////////////////////////////////////////////////////////
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
				frame.dispose();
				UsersController uc = new UsersController();
				uc.Editar_eliminar_y_añadir_clases();
			}
		});
		boton_cancelar.setFont(new Font("Anton", Font.PLAIN, 18));
		boton_cancelar.setBackground(new Color(255, 0, 0));
		boton_cancelar.setBounds(554, 510, 160, 43);
		panel_2.add(boton_cancelar);

		// Botones laterales
		// //////////////////////////////////////////////////////////////////////////////////////////////
		JButton boton_INICIO = new JButton("INICIO");// boton de inicio
		boton_INICIO.setBackground(new Color(255, 205, 17));
		boton_INICIO.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_INICIO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Panel_inicio();

			}
		});
		boton_INICIO.setBounds(10, 86, 136, 71);
		panel.add(boton_INICIO);

		JButton boton_CLIENTES = new JButton("CLIENTES");// boton de clientes
		boton_CLIENTES.setBackground(new Color(255, 205, 17));
		boton_CLIENTES.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CLIENTES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Clientes();
			}
		});
		boton_CLIENTES.setBounds(10, 168, 136, 71);
		panel.add(boton_CLIENTES);

		JButton boton_TARIFAS = new JButton("TARIFAS");// boton de tarifas
		boton_TARIFAS.setBackground(new Color(255, 205, 17));
		boton_TARIFAS.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_TARIFAS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Tarifas();
			}
		});
		boton_TARIFAS.setBounds(10, 250, 136, 71);
		panel.add(boton_TARIFAS);

		JButton boton_INSTRUCTORES = new JButton("INSTRUCTORES");// boton de instructores
		boton_INSTRUCTORES.setBackground(new Color(255, 205, 17));
		boton_INSTRUCTORES.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_INSTRUCTORES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Instructores();
			}
		});
		boton_INSTRUCTORES.setBounds(10, 332, 136, 71);
		panel.add(boton_INSTRUCTORES);

		JButton boton_CLASES = new JButton("CLASES");// boton de clases
		boton_CLASES.setBackground(new Color(255, 255, 255));
		boton_CLASES.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CLASES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		boton_CLASES.setBounds(10, 414, 136, 71);
		panel.add(boton_CLASES);

		JButton boton_CHECADOR = new JButton("CHECADOR");// boton de checador
		boton_CHECADOR.setBackground(new Color(255, 205, 17));
		boton_CHECADOR.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CHECADOR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Panel_checador();
			}
		});
		boton_CHECADOR.setBounds(10, 496, 136, 71);
		panel.add(boton_CHECADOR);

		JButton boton_CERRAR_SESION = new JButton("CERRAR SESION");
		boton_CERRAR_SESION.setBackground(new Color(255, 205, 17));
		boton_CERRAR_SESION.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CERRAR_SESION.addActionListener(e -> {
			JDialog dialog = new JDialog(frame, "Cerrar sesión", true);
			dialog.setSize(400, 220);
			dialog.setLocationRelativeTo(frame);
			dialog.setUndecorated(true);
			dialog.setLayout(null);

			// Panel principal - AHORA CON TAMAÑO COMPLETO
			JPanel Cerrar_sesion = new JPanel();
			Cerrar_sesion.setBackground(new Color(255, 255, 255));
			Cerrar_sesion.setBounds(0, 0, 400, 220);
			Cerrar_sesion.setLayout(null);
			dialog.add(Cerrar_sesion);

			// Panel superior azul
			JPanel panel_complemento = new JPanel();
			panel_complemento.setBackground(new Color(81, 151, 255));
			panel_complemento.setBounds(0, 0, 400, 33);
			Cerrar_sesion.add(panel_complemento);

			// Etiqueta con la pregunta
			JLabel pregunta_de_confirmacion = new JLabel(
					"<html><div style='text-align: center;'>Está a punto de cerrar sesión<br>¿Desea continuar?</div></html>");
			pregunta_de_confirmacion.setFont(new Font("Anton", Font.PLAIN, 16));
			pregunta_de_confirmacion.setBounds(90, 44, 310, 60);
			Cerrar_sesion.add(pregunta_de_confirmacion);

			// Botón Cancelar
			JButton boton_cancelar_alerta = new JButton("Cancelar");
			boton_cancelar_alerta.setForeground(Color.WHITE);
			boton_cancelar_alerta.setFont(new Font("Anton", Font.PLAIN, 14));
			boton_cancelar_alerta.setBackground(Color.RED);
			boton_cancelar_alerta.setBounds(50, 140, 120, 35);
			Cerrar_sesion.add(boton_cancelar_alerta);

			// Botón Aceptar
			JButton boton_aceptar = new JButton("Aceptar");
			boton_aceptar.setBackground(new Color(0, 206, 82));
			boton_aceptar.setForeground(Color.WHITE);
			boton_aceptar.setFont(new Font("Anton", Font.PLAIN, 14));
			boton_aceptar.setBounds(230, 140, 120, 35);
			Cerrar_sesion.add(boton_aceptar);

			// Acciones de los botones
			boton_cancelar_alerta.addActionListener(ev -> dialog.dispose());
			boton_aceptar.addActionListener(ev -> {
				dialog.dispose();
				closeSession(frame);
			});

			dialog.setVisible(true);
		});
		boton_CERRAR_SESION.setBounds(10, 579, 136, 71);
		panel.add(boton_CERRAR_SESION);

		frame.add(panel);
		frame.repaint();
		frame.revalidate();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}