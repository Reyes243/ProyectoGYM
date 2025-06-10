package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

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
import models.Clase;
import models.ClaseModel;
import models.ConectionModel;
import models.InstrucoresModel;
import models.UsersModel;
import models.Tarifa;
import models.TarifaModel;
import models.User;

public class UsersView {
	private boolean cambiosRealizados = false;
	private Font antonFont;

	private int obtenerIdEntrenadorPorNombre(String nombreEntrenador, Connection conn) throws SQLException {
		String sql = "SELECT id_usuario FROM usuario WHERE nombre = ? AND id_rol = 3";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, nombreEntrenador);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getInt("id_usuario");
			} else {
				throw new SQLException("No se encontró el entrenador con ese nombre.");
			}
		}
	}

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

		JLabel lblNewLabel_10 = new JLabel("Primer apellido:");
		lblNewLabel_10.setFont(new Font("Anton", Font.PLAIN, 16));
		lblNewLabel_10.setBounds(235, 210, 147, 22);
		panel_2.add(lblNewLabel_10);

		JLabel lblNewLabel_13 = new JLabel("Segundo Apellido:");
		lblNewLabel_13.setFont(new Font("Anton", Font.PLAIN, 16));
		lblNewLabel_13.setBounds(575, 210, 147, 22);
		panel_2.add(lblNewLabel_13);

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

		JLabel Info_segundo_apellido = new JLabel("");
		Info_segundo_apellido.setText(datosCliente.get("segundo_apellido"));
		Info_segundo_apellido.setFont(new Font("Anton", Font.PLAIN, 16));
		Info_segundo_apellido.setBounds(695, 210, 200, 22);
		panel_2.add(Info_segundo_apellido);

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

		JLabel Info_clase = new JLabel("");
		Info_clase.setText(datosCliente.getOrDefault("clase", "Ninguna"));
		Info_clase.setFont(new Font("Anton", Font.PLAIN, 16));
		Info_clase.setBounds(362, 362, 167, 28);
		panel_2.add(Info_clase);

		JLabel Info_plan = new JLabel("");
		Info_plan.setText(datosCliente.getOrDefault("tarifa", "Ninguna"));
		Info_plan.setFont(new Font("Anton", Font.PLAIN, 16));
		Info_plan.setBounds(617, 160, 200, 22);
		panel_2.add(Info_plan);

		JLabel Info_pago_sig = new JLabel("");
		String precio = datosCliente.getOrDefault("precio_tarifa", "0.00");
		Info_pago_sig.setText("$" + precio);
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
				try {
					hc.Clases();
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error al cargar las clases.");
				}
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
		String tarifaActual = datosCliente.getOrDefault("tarifa", "NINGUNA");

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

		JComboBox<String> comboBox_Tarifas = new JComboBox<>();
		comboBox_Tarifas.setBackground(new Color(204, 204, 204));
		comboBox_Tarifas.setFont(new Font("Anton", Font.PLAIN, 16));
		comboBox_Tarifas.setBounds(649, 283, 200, 22);
		cargarTarifasEnComboBox(comboBox_Tarifas);
		comboBox_Tarifas.setSelectedItem(tarifaActual);
		panel_2.add(comboBox_Tarifas);

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
					String tarifaSeleccionada = (String) comboBox_Tarifas.getSelectedItem();
					boolean exito = actualizarCliente(idcliente, Info_nombre.getText(), Info_primer_apellido.getText(),
							Info_segundo_apellido.getText(), Info_telefono.getText(), Info_correo.getText(),
							nuevaContra.isEmpty() ? null : nuevaContra);

					// Actualizar la tarifa del cliente
					if (exito) {
						exito = actualizarTarifaCliente(idcliente, tarifaSeleccionada);
					}

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
				try {
					hc.Clases();
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error al cargar las clases.");
				}
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

	private boolean actualizarTarifaCliente(int idCliente, String nombreTarifa) {
		ConectionModel conexion = new ConectionModel();
		Connection conn = null;

		try {
			conn = conexion.getConnection();
			conn.setAutoCommit(false); // Iniciar transacción

			// Primero eliminar cualquier tarifa existente
			String sqlEliminar = "DELETE FROM usuario_tarifa WHERE id_usuario = ?";
			try (PreparedStatement pstmt = conn.prepareStatement(sqlEliminar)) {
				pstmt.setInt(1, idCliente);
				pstmt.executeUpdate();
			}

			// Si se seleccionó una tarifa (no es "NINGUNA"), insertar la nueva
			if (!"NINGUNA".equals(nombreTarifa)) {
				int idTarifa = obtenerIdTarifa(nombreTarifa);
				if (idTarifa != -1) {
					String sqlInsertar = "INSERT INTO usuario_tarifa (id_usuario, id_tarifa) VALUES (?, ?)";
					try (PreparedStatement pstmt = conn.prepareStatement(sqlInsertar)) {
						pstmt.setInt(1, idCliente);
						pstmt.setInt(2, idTarifa);
						pstmt.executeUpdate();
					}
				}
			}

			conn.commit(); // Confirmar la transacción
			return true;
		} catch (SQLException e) {
			try {
				if (conn != null) {
					conn.rollback(); // Revertir en caso de error
				}
			} catch (SQLException ex) {
				System.err.println("Error al hacer rollback: " + ex.getMessage());
			}
			System.err.println("Error al actualizar tarifa del cliente: " + e.getMessage());
			return false;
		} finally {
			try {
				if (conn != null) {
					conn.setAutoCommit(true);
				}
			} catch (SQLException e) {
				System.err.println("Error al restaurar auto-commit: " + e.getMessage());
			}
			conexion.close();
		}
	}

	// Método auxiliar para obtener el ID de una tarifa por su nombre
	private int obtenerIdTarifa(String nombreTarifa) {
		ConectionModel conexion = new ConectionModel();
		String sql = "SELECT id_tarifa FROM tarifa WHERE nombre_tarifa = ?";

		try {
			Connection conn = conexion.getConnection();
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setString(1, nombreTarifa.toLowerCase());
				try (ResultSet rs = pstmt.executeQuery()) {
					if (rs.next()) {
						return rs.getInt("id_tarifa");
					}
				}
			}
		} catch (SQLException e) {
			System.err.println("Error al obtener ID de tarifa: " + e.getMessage());
		} finally {
			conexion.close();
		}
		return -1;
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

		Object[][] dataCliente = { { datosCliente.get("id"), datosCliente.get("nombre"), // nombre(s)
				datosCliente.get("primer_apellido"), // primer apellido
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

		String plan = datosCliente.get("tarifa") != null ? datosCliente.get("tarifa") : "NINGUNO";
		String precio = datosCliente.get("precio_tarifa") != null ? datosCliente.get("precio_tarifa") : "0.00";

		Object[][] data2 = { { datosCliente.get("id"), plan.toUpperCase(), "$" + precio, "$" + precio // mismo monto
																										// como
																										// "siguiente
																										// pago"
				} };

		String[] columnas2 = { "ID Cliente", "Plan", "Tarifa", "Siguiente pago" };

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
				try {
					hc.Clases();
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error al cargar las clases.");
				}
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

		// Tabla con los datos básicos del cliente
		Object[][] dataCliente = { { datosCliente.get("id"), datosCliente.get("nombre"), // nombre(s)
				datosCliente.get("primer_apellido"), // primer apellido
				datosCliente.get("telefono"), datosCliente.get("correo") } };

		String[] columnas = { "ID cliente", "Nombre(s)", "Primer apellido", "Teléfono", "Correo electrónico" };

		JScrollPane scrollPane_Usuario = new JScrollPane(); // tabla del usuario
		scrollPane_Usuario.setBounds(10, 61, 898, 50);
		panel_2.add(scrollPane_Usuario);

		JTable table = new JTable(dataCliente, columnas);
		table.setFont(new Font("Anton", Font.PLAIN, 12));
		table.setBackground(new Color(204, 204, 204));
		scrollPane_Usuario.setViewportView(table);

		// Estilo del encabezado
		JTableHeader header = table.getTableHeader();
		header.setBackground(Color.BLACK);
		header.setForeground(Color.WHITE);
		header.setFont(new Font("Anton", Font.PLAIN, 14));
		header.setReorderingAllowed(false);

		String[] columnas2 = { "Día", "Mes", "Año", "Hora de entrada" };
		DefaultTableModel modeloHistorial = new DefaultTableModel(columnas2, 0);
		JTable tablaHistorial = new JTable(modeloHistorial);

		tablaHistorial.setFont(new Font("Anton", Font.PLAIN, 12));
		tablaHistorial.setBackground(new Color(204, 204, 204));

		JScrollPane scrollPaneHistorial = new JScrollPane(tablaHistorial);
		scrollPaneHistorial.setBounds(10, 122, 898, 168);
		panel_2.add(scrollPaneHistorial);

		// Personalizar encabezado
		JTableHeader header2 = tablaHistorial.getTableHeader();
		header2.setBackground(Color.BLACK);
		header2.setForeground(Color.WHITE);
		header2.setFont(new Font("Anton", Font.PLAIN, 14));
		header2.setReorderingAllowed(false);

		// Cargar historial desde BD
		List<String[]> asistencias = um.obtenerHistorialAsistencia(idcliente);
		for (String[] fila : asistencias) {
			modeloHistorial.addRow(fila);
		}

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
				try {
					hc.Clases();
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error al cargar las clases.");
				}
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
				try {
					hc.Clases();
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error al cargar las clases.");
				}
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
		boton_descraga_credencial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UsersController controller = new UsersController();
				controller.generarCredencialPDF(idCliente);
			}
		});
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
				try {
					hc.Clases();
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error al cargar las clases.");
				}
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

		JComboBox<String> comboBox_Tarifas = new JComboBox<>();
		comboBox_Tarifas.setBackground(new Color(204, 204, 204));
		comboBox_Tarifas.setFont(new Font("Anton", Font.PLAIN, 16));
		comboBox_Tarifas.setBounds(649, 283, 200, 22);
		cargarTarifasEnComboBox(comboBox_Tarifas);
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
				try {
					hc.Clases();
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error al cargar las clases.");
				}
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

	private void cargarTarifasEnComboBox(JComboBox<String> comboBox) {
		comboBox.removeAllItems(); // Limpia opciones previas
		comboBox.addItem("NINGUNA"); // Opción predeterminada

		String sql = "SELECT nombre_tarifa FROM tarifa";

		try {
			ConectionModel conexion = new ConectionModel();
			Connection conn = conexion.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				String nombreTarifa = rs.getString("nombre_tarifa");
				comboBox.addItem(nombreTarifa);
			}

			conexion.close();
		} catch (SQLException e) {
			System.err.println("Error al cargar tarifas: " + e.getMessage());
		}
	}

	private final int FILAS = 2;
	private final int COLUMNAS = 3;
	private final Point[][] posicionesMatriz = new Point[FILAS][COLUMNAS];

	private void inicializarMatrizPosiciones() {
		int xInicial = 30;
		int yInicial = 81;
		int ancho = 275;
		int alto = 185;
		int espacioX = 18;
		int espacioY = 18;

		for (int fila = 0; fila < FILAS; fila++) {
			for (int col = 0; col < COLUMNAS; col++) {
				int x = xInicial + col * (ancho + espacioX);
				int y = yInicial + fila * (alto + espacioY);
				posicionesMatriz[fila][col] = new Point(x, y);
			}
		}
	}

	private void reubicarPaneles(JButton botonAgregar) {
		int total = paneles_tarifa.size();

		// Máximo 6 paneles (2 filas x 3 columnas)
		if (total >= 6) {
			botonAgregar.setVisible(false);
		} else {
			botonAgregar.setVisible(true);
		}

		// Posicionar paneles
		for (int i = 0; i < total; i++) {
			int fila = i / COLUMNAS;
			int col = i % COLUMNAS;
			Point pos = posicionesMatriz[fila][col];
			paneles_tarifa.get(i).setBounds((int) pos.getX(), (int) pos.getY(), 275, 185); // ← corregido
		}

		// Posicionar botón en la siguiente celda libre
		if (total < 6) {
			int fila = total / COLUMNAS;
			int col = total % COLUMNAS;
			Point pos = posicionesMatriz[fila][col];
			botonAgregar.setBounds((int) pos.getX(), (int) pos.getY(), 275, 185); // ← corregido
		}
	}

	public class TarifaData {
		public static List<Tarifa> tarifas = new ArrayList<>();
	}

	private JPanel panel_2;
	private List<JPanel> paneles_tarifa = new ArrayList<>();

	private JPanel crearPanelTarifa(String nombre, int precio, String descripcion, JButton botonAgregar, Tarifa t) {
		JPanel panelTarifa = new JPanel();
		panelTarifa.setLayout(null);
		panelTarifa.setBackground(Color.WHITE);
		panelTarifa.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		panelTarifa.setBounds(30 + (paneles_tarifa.size() * 293), 81, 275, 185); // disposición horizontal

		JLabel lblPlan = new JLabel("Plan");
		lblPlan.setFont(new Font("Anton", Font.PLAIN, 22));
		lblPlan.setBounds(20, 26, 100, 35);
		panelTarifa.add(lblPlan);

		JLabel lblNombre = new JLabel(nombre.toUpperCase());
		lblNombre.setFont(new Font("Anton", Font.PLAIN, 28));
		lblNombre.setBounds(20, 72, 172, 35);
		panelTarifa.add(lblNombre);

		JLabel lblPrecio = new JLabel("$" + precio + " / mes");
		lblPrecio.setFont(new Font("Anton", Font.PLAIN, 28));
		lblPrecio.setBounds(20, 118, 200, 35);
		panelTarifa.add(lblPrecio);

		// Botón Eliminar

		JButton btnEliminar = new JButton("");
		btnEliminar.setBounds(240, 0, 35, 35);
		btnEliminar.setBackground(Color.RED);
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		ImageIcon icon5 = new ImageIcon(getClass().getResource("/Imagenes/eliminar.png"));
		Image imagen5 = icon5.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		btnEliminar.setIcon(new ImageIcon(imagen5));
		btnEliminar.addActionListener(e -> {
			int confirm = JOptionPane.showConfirmDialog(null, "¿Eliminar esta tarifa?", "Confirmar",
					JOptionPane.YES_NO_OPTION);
			if (confirm == JOptionPane.YES_OPTION) {
				TarifaModel model = new TarifaModel();
				boolean eliminado = model.eliminarTarifa(t.getNombreTarifa(), t.getPrecio(), t.getDescripcion());

				if (eliminado) {
					TarifaData.tarifas.remove(t); // si usas cache local
					panel_2.remove(panelTarifa);
					paneles_tarifa.remove(panelTarifa);
					reubicarPaneles(botonAgregar);
					panel_2.repaint();
					panel_2.revalidate();
					JOptionPane.showMessageDialog(null, "Tarifa eliminada correctamente.");
				} else {
					JOptionPane.showMessageDialog(null, "No se pudo eliminar la tarifa.");
				}
			}
		});
		panelTarifa.add(btnEliminar);

		// Botón Editar
		JButton btnEditar = new JButton("");
		btnEditar.setBounds(205, 0, 35, 35);
		btnEditar.setBackground(new Color(255, 205, 17));
		btnEditar.setForeground(Color.WHITE);
		btnEditar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		ImageIcon icon2 = new ImageIcon(getClass().getResource("/Imagenes/editar.png"));
		Image imagen2 = icon2.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		btnEditar.setIcon(new ImageIcon(imagen2));
		btnEditar.addActionListener(e -> {
			// 1. Guardar los datos actuales de la tarifa a editar
			TarifaTempStorage.nombre = nombre;
			TarifaTempStorage.precio = precio;
			TarifaTempStorage.descripcion = descripcion;

			// 2. Cerrar la ventana actual
			JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(panelTarifa);
			if (parentFrame != null)
				parentFrame.dispose();

			// 3. Abrir la ventana de edición
			UsersController uc = new UsersController();
			uc.Editar_tarifas2(); // << aquí tu conexión
		});
		panelTarifa.add(btnEditar);

		return panelTarifa;
	}

	public class TarifaTempStorage {
		public static String nombre = "";
		public static int precio = 0;
		public static String descripcion = "";
	}

	private JButton botonAgregar;

	public void Editar_tarifas() {

		try {
			UIManager.setLookAndFeel(new FlatLightLaf());
			UIManager.put("Button.arc", 8); // Esquinas redondeadas
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		JFrame frame = new JFrame();
		frame.setResizable(false);
		frame.setSize(1100, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setLayout(null);
		panel.setBounds(0, 0, 1100, 700);
		frame.add(panel);

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

		panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBounds(156, 86, 918, 564);
		panel_2.setBackground(Color.WHITE);
		panel.add(panel_2);

		// Panel negro de título
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(0, 0, 0));
		panel_3.setBounds(0, 0, 918, 50);
		panel_3.setLayout(null);
		panel_2.add(panel_3);

		JLabel lblNewLabel = new JLabel("EDITAR TARIFAS Y DESCRIPCIÓN");
		lblNewLabel.setFont(new Font("Anton", Font.PLAIN, 26));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(60, 11, 436, 28);
		panel_3.add(lblNewLabel);
		inicializarMatrizPosiciones();

		// Botón "+"
		botonAgregar = new JButton("+");
		botonAgregar.setFont(new Font("Arial", Font.BOLD, 50));
		botonAgregar.setBounds(30, 81, 275, 185);
		panel_2.add(botonAgregar);

		TarifaModel model = new TarifaModel();
		TarifaData.tarifas = model.obtenerTodas();

		for (Tarifa t : TarifaData.tarifas) {
			JPanel panel_tarifa = crearPanelTarifa(t.getNombreTarifa(), t.getPrecio(), t.getDescripcion(), botonAgregar,
					t);
			paneles_tarifa.add(panel_tarifa);
			panel_2.add(panel_tarifa);
		}

		// Listener del botón "+"
		botonAgregar.addActionListener(e -> {
			if (paneles_tarifa.size() >= 6) {
				JOptionPane.showMessageDialog(frame, "Máximo 6 tarifas permitidas.");
				return;
			}

			frame.dispose();
			UsersController uc = new UsersController();
			uc.Añadir_tarifa(); // ← Aquí vas a la ventana para ingresar datos
		});

		// Agregar tarifas iniciales

		// Reubicar y mostrar
		reubicarPaneles(botonAgregar);

		JButton boton_cancelar_regresar = new JButton("Cancelar");
		boton_cancelar_regresar.setForeground(new Color(255, 255, 255));
		boton_cancelar_regresar.setBackground(new Color(255, 0, 0));
		boton_cancelar_regresar.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_cancelar_regresar.setBounds(762, 503, 128, 50);
		boton_cancelar_regresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Tarifas();
			}
		});
		panel_2.add(boton_cancelar_regresar);

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
				try {
					hc.Clases();
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error al cargar las clases.");
				}
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

		JLabel lblNewLabel = new JLabel("EDITAR TARIFA");// titulo de inicio
		lblNewLabel.setFont(new Font("Anton", Font.PLAIN, 26));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(60, 11, 300, 28);
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

		JTextField nombre_de_la_tarifa = new JTextField("");
		nombre_de_la_tarifa.setBackground(new Color(204, 204, 204));
		nombre_de_la_tarifa.setFont(new Font("Anton", Font.PLAIN, 28));
		nombre_de_la_tarifa.setBounds(20, 72, 172, 35);
		plan_estandar.add(nombre_de_la_tarifa);

		// Campo: Nombre del plan
		JTextField txtEstandar = new JTextField();
		txtEstandar.setBackground(new Color(204, 204, 204));
		txtEstandar.setFont(new Font("Anton", Font.PLAIN, 22));
		txtEstandar.setBounds(74, 26, 214, 32);
		descripcion_plan.add(txtEstandar);
		txtEstandar.setColumns(10);

		// Campo: Precio
		JTextField textField = new JTextField();
		textField.setBackground(new Color(204, 204, 204));
		textField.setFont(new Font("Anton", Font.PLAIN, 28));
		textField.setBounds(34, 118, 62, 35);
		plan_estandar.add(textField);
		textField.setColumns(10);

		// Campo: Descripción
		JTextArea txtrSeIncluyeAcceso = new JTextArea();
		txtrSeIncluyeAcceso.setLineWrap(true);
		txtrSeIncluyeAcceso.setWrapStyleWord(true);
		txtrSeIncluyeAcceso.setFont(new Font("Anton", Font.PLAIN, 17));
		txtrSeIncluyeAcceso.setBackground(new Color(204, 204, 204));
		txtrSeIncluyeAcceso.setBounds(20, 72, 527, 169);
		descripcion_plan.add(txtrSeIncluyeAcceso);

		nombre_de_la_tarifa.setText(TarifaTempStorage.nombre);
		txtEstandar.setText(TarifaTempStorage.nombre);
		textField.setText(String.valueOf(TarifaTempStorage.precio));
		txtrSeIncluyeAcceso.setText(TarifaTempStorage.descripcion);

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

		boton_guardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nuevoNombre = txtEstandar.getText().trim();
				String nuevaDescripcion = txtrSeIncluyeAcceso.getText().trim();
				int nuevoPrecio;

				try {
					nuevoPrecio = Integer.parseInt(textField.getText().trim());
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(frame, "El precio debe ser un número válido.");
					return;
				}

				if (nuevoNombre.isEmpty() || nuevaDescripcion.isEmpty()) {
					JOptionPane.showMessageDialog(frame, "Todos los campos deben estar llenos.");
					return;
				}

				Tarifa nuevaTarifa = new Tarifa(nuevoNombre, nuevaDescripcion, nuevoPrecio);
				TarifaModel model = new TarifaModel();

				boolean actualizado = model.actualizarTarifa(TarifaTempStorage.nombre, nuevaTarifa);

				if (actualizado) {
					JOptionPane.showMessageDialog(frame, "Tarifa actualizada correctamente.");
					frame.dispose();
					UsersController uc = new UsersController();
					uc.Editar_tarifas();
				} else {
					JOptionPane.showMessageDialog(frame, "No se pudo actualizar la tarifa.");
				}
			}
		});

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
				try {
					hc.Clases();
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error al cargar las clases.");
				}
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

	private JTextField txtNombre;
	private JTextField txtPrecio;
	private JTextArea txtDescripcion;

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

		JLabel lblNewLabel = new JLabel("AÑADIR TARIFA");// titulo de inicio
		lblNewLabel.setFont(new Font("Anton", Font.PLAIN, 26));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(60, 11, 300, 28);
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

		boton_guardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String nombre = txtEstandar.getText().trim();
					int precio = Integer.parseInt(textField.getText().trim());
					String descripcion = txtrSeIncluyeAcceso.getText().trim();

					// Validar datos
					if (nombre.isEmpty() || descripcion.isEmpty()) {
						JOptionPane.showMessageDialog(frame, "Todos los campos deben estar completos.");
						return;
					}
					Tarifa nueva = new Tarifa(nombre, descripcion, precio);
					TarifaModel model = new TarifaModel();
					boolean insertado = model.insertarTarifa(nueva);
					if (insertado) {
						JOptionPane.showMessageDialog(frame, "Tarifa guardada exitosamente.");
						// Cierra ventana o redirige
					} else {
						JOptionPane.showMessageDialog(frame, "Error al guardar tarifa.");
					}

					// Guardar en clase temporal
					Tarifa nuevaTarifa = new Tarifa(nombre, descripcion, precio);
					TarifaData.tarifas.add(nuevaTarifa);

					// Cerrar esta ventana y regresar
					frame.dispose();
					UsersController uc = new UsersController();
					uc.Editar_tarifas();

				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(frame, "El precio debe ser un número válido.");
				}
			}
		});

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
				try {
					hc.Clases();
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error al cargar las clases.");
				}
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

	public void Clientes_con_tarifa_ESTANDAR(String nombreTarifa) {
		try {
			UIManager.setLookAndFeel(new FlatLightLaf());
			UIManager.put("Button.arc", 8); // Esquinas redondeadas
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		UsersModel um = new UsersModel();
		List<User> clientesFiltrados = um.getClientesPorTarifa(nombreTarifa);
		System.out.println("Clientes encontrados con tarifa " + nombreTarifa + ": " + clientesFiltrados.size());

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

		JLabel lblTituloTarifa = new JLabel("TARIFA: " + nombreTarifa.toUpperCase());
		lblTituloTarifa.setFont(new Font("Anton", Font.PLAIN, 26));
		lblTituloTarifa.setForeground(Color.WHITE);
		lblTituloTarifa.setBounds(60, 11, 523, 28);
		panel_3.add(lblTituloTarifa);

		String[] columnas = { "ID", "Nombre", "Apellido", "Teléfono", "Correo" };
		Object[][] data = new Object[clientesFiltrados.size()][5];

		for (int i = 0; i < clientesFiltrados.size(); i++) {
			User u = clientesFiltrados.get(i);
			data[i][0] = u.getId();
			data[i][1] = u.getNombre();
			data[i][2] = u.getCorreo();
			data[i][3] = u.getTelefono();
			data[i][4] = u.getPrimer_apellido();
		}

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
				try {
					hc.Clases();
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error al cargar las clases.");
				}
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
		InstrucoresModel um = new InstrucoresModel();
		Map<String, String> datosInstructor = um.obtenerDatosCompletosInstructor(idinstructor);

		if (datosInstructor.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Cliente no encontrado. ID inválido: " + idinstructor, "Error",
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

		JLabel lblNewLabel_5 = new JLabel("ID Instructor: ");
		lblNewLabel_5.setFont(new Font("Anton", Font.PLAIN, 20));
		lblNewLabel_5.setBounds(235, 90, 200, 32);
		panel_2.add(lblNewLabel_5);

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
		JLabel Info_ID = new JLabel("");
		Info_ID.setText(datosInstructor.get("id"));
		Info_ID.setFont(new Font("Anton", Font.PLAIN, 20));
		Info_ID.setBounds(350, 92, 46, 28);
		panel_2.add(Info_ID);

		JLabel Info_nombre = new JLabel("");
		Info_nombre.setText(datosInstructor.get("nombre"));
		Info_nombre.setFont(new Font("Anton", Font.PLAIN, 16));
		Info_nombre.setBounds(328, 119, 122, 21);
		panel_2.add(Info_nombre);

		JLabel Info_especialidad = new JLabel("");
		Info_especialidad.setText(datosInstructor.get("especialidad"));
		Info_especialidad.setFont(new Font("Anton", Font.PLAIN, 16));
		Info_especialidad.setBounds(361, 165, 147, 22);
		panel_2.add(Info_especialidad);

		JLabel Info_clase = new JLabel("");
		Info_clase.setText(datosInstructor.get("clases"));
		Info_clase.setFont(new Font("Anton", Font.PLAIN, 16));
		Info_clase.setBounds(381, 267, 152, 22);
		panel_2.add(Info_clase);

		JLabel Info_telefono = new JLabel("");
		Info_telefono.setText(datosInstructor.get("telefono"));
		Info_telefono.setFont(new Font("Anton", Font.PLAIN, 16));
		Info_telefono.setBounds(334, 315, 152, 22);
		panel_2.add(Info_telefono);

		JLabel Info_correo = new JLabel("");
		Info_correo.setText(datosInstructor.get("correo"));
		Info_correo.setFont(new Font("Anton", Font.PLAIN, 16));
		Info_correo.setBounds(405, 362, 200, 28);
		panel_2.add(Info_correo);

		JLabel Info_horarrio = new JLabel("");
		Info_horarrio.setText(datosInstructor.get("horarios"));
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
				uc.Credencial_instructor(idinstructor);
			}
		});
		panel_2.add(boton_descraga_credencial);

		JButton boton_historial_asistencias = new JButton("Editar información");
		boton_historial_asistencias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				UsersController uc = new UsersController();
				uc.Editar_instructor(idinstructor);
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
				uc.Historial_de_clase(idinstructor);
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
				try {
					hc.Clases();
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error al cargar las clases.");
				}
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

	public void Historial_de_clase(int idinstructor) {
		try {
			UIManager.setLookAndFeel(new FlatLightLaf());
			UIManager.put("Button.arc", 8); // Esquinas redondeadas
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		InstrucoresModel model = new InstrucoresModel();
		List<Object[]> clases = model.getClasesPorInstructor(idinstructor);
		List<Object[]> clientes = model.getInstructor(idinstructor);

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

		JLabel lblNewLabel = new JLabel("HISTORIAL DE CLASES");// titulo de inicio
		lblNewLabel.setFont(new Font("Anton", Font.PLAIN, 26));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(60, 11, 309, 28);
		panel_3.add(lblNewLabel);

		Object[][] data = clases.toArray(new Object[0][]);

		String[] columnas = { "Entrenador", "Clase asignada", "Turno", "Día" };

		JScrollPane scrollPane_Usuario = new JScrollPane();// tabla del usario
		scrollPane_Usuario.setBounds(10, 61, 898, 431);
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
				UsersController uc = new UsersController();
				uc.Ficha_de_instructor(idinstructor);
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
				try {
					hc.Clases();
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error al cargar las clases.");
				}
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

	public void Editar_instructor(int idinstructor) {
		try {
			UIManager.setLookAndFeel(new FlatLightLaf());
			UIManager.put("Button.arc", 8); // Esquinas redondeadas
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		InstrucoresModel model = new InstrucoresModel();
		Map<String, String> datosInstructor = model.obtenerDatosBasicosInstructor(idinstructor);
		String claseActual = datosInstructor.getOrDefault("clase_asignada", "NINGUNA");

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
		lblNewLabel_11.setBounds(272, 119, 91, 54);
		panel_2.add(lblNewLabel_11);

		JLabel lblNewLabel_10 = new JLabel("Especialidad: ");
		lblNewLabel_10.setFont(new Font("Anton", Font.PLAIN, 16));
		lblNewLabel_10.setBounds(272, 184, 91, 52);
		panel_2.add(lblNewLabel_10);

		JLabel lblNewLabel_9 = new JLabel("Clase asignada:");
		lblNewLabel_9.setFont(new Font("Anton", Font.PLAIN, 16));
		lblNewLabel_9.setBounds(272, 247, 132, 49);
		panel_2.add(lblNewLabel_9);

		JLabel lblNewLabel_8 = new JLabel("Teléfono:");
		lblNewLabel_8.setFont(new Font("Anton", Font.PLAIN, 16));
		lblNewLabel_8.setBounds(272, 307, 104, 50);
		panel_2.add(lblNewLabel_8);

		JLabel lblNewLabel_7 = new JLabel("Correo electronico:");
		lblNewLabel_7.setFont(new Font("Anton", Font.PLAIN, 16));
		lblNewLabel_7.setBounds(272, 368, 132, 50);
		panel_2.add(lblNewLabel_7);

		// info de
		// intructor///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		JTextField Info_nombre = new JTextField(datosInstructor.getOrDefault("nombre", ""));
		valoresOriginales.put("nombre", Info_nombre.getText());
		Info_nombre.setFont(new Font("Anton", Font.PLAIN, 16));
		Info_nombre.setBounds(420, 119, 320, 50);
		panel_2.add(Info_nombre);

		JTextField Info_especialidad = new JTextField(datosInstructor.getOrDefault("especialidad", ""));
		valoresOriginales.put("especialidad", Info_especialidad.getText());
		Info_especialidad.setBackground(new Color(204, 204, 204));
		Info_especialidad.setFont(new Font("Anton", Font.PLAIN, 16));
		Info_especialidad.setBounds(420, 184, 320, 50);
		panel_2.add(Info_especialidad);

		JTextField Info_telefono = new JTextField(datosInstructor.getOrDefault("telefono", ""));
		valoresOriginales.put("telefono", Info_telefono.getText());
		Info_telefono.setBackground(new Color(204, 204, 204));
		Info_telefono.setFont(new Font("Anton", Font.PLAIN, 16));
		Info_telefono.setBounds(420, 308, 320, 50);
		panel_2.add(Info_telefono);

		JTextField Info_correo = new JTextField(datosInstructor.getOrDefault("correo", ""));
		valoresOriginales.put("correo", Info_correo.getText());
		Info_correo.setBackground(new Color(204, 204, 204));
		Info_correo.setFont(new Font("Anton", Font.PLAIN, 16));
		Info_correo.setBounds(420, 369, 320, 50);
		panel_2.add(Info_correo);

		// comobox//////////////////////////////////////////////////////////////////////////////////////////////////////////////

		UIManager.put("ComboBox.buttonBackground", new Color(255, 205, 17));
		JComboBox<String> comboBox_clases = new JComboBox();
		comboBox_clases.setBackground(new Color(204, 204, 204));
		comboBox_clases.setFont(new Font("Anton", Font.PLAIN, 16));
		comboBox_clases.setBounds(420, 247, 320, 50);
		cargarClasesEnComboBox(comboBox_clases);
		comboBox_clases.setSelectedItem(claseActual);
		panel_2.add(comboBox_clases);

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
		Info_especialidad.getDocument().addDocumentListener(documentListener);
		Info_telefono.getDocument().addDocumentListener(documentListener);
		Info_correo.getDocument().addDocumentListener(documentListener);

		// botones de accion para el instructor
		// ///////////////////////////////////////////////////////////////////////
		JButton boton_guardar = new JButton("Guardar");
		boton_guardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!cambiosRealizados) {
					mostrarDialogo(frame, "Información", "No se han realizado cambios para guardar.");
					return;
				}

				// Validación de campos obligatorios
				if (Info_nombre.getText().trim().isEmpty() || Info_especialidad.getText().trim().isEmpty()
						|| Info_correo.getText().trim().isEmpty()) {
					mostrarDialogo(frame, "Campos obligatorios",
							"Nombre, especialidad y correo son campos obligatorios.");
					return;
				}

				// Validación del teléfono (si se proporciona)
				String telefono = Info_telefono.getText().trim();
				if (!telefono.isEmpty() && !telefono.matches("\\d+")) {
					mostrarDialogo(frame, "Error de teléfono", "El teléfono debe contener solo números.");
					return;
				}

				// Validación del correo
				String correo = Info_correo.getText().trim();
				if (!correo.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
					mostrarDialogo(frame, "Error de correo", "Por favor ingrese un correo electrónico válido.");
					return;
				}

				// Confirmación antes de guardar
				mostrarDialogoConfirmacion(frame, "¿Está seguro que desea guardar los cambios?", "Confirmar cambios",
						new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								String claseSeleccionada = (String) comboBox_clases.getSelectedItem();
								boolean exito = model.actualizarInstructor(idinstructor, Info_nombre.getText(),
										Info_telefono.getText().isEmpty() ? null : Info_telefono.getText(),
										Info_correo.getText(), Info_especialidad.getText(), claseSeleccionada);

								if (exito) {
									valoresOriginales.put("nombre", Info_nombre.getText());
									valoresOriginales.put("especialidad", Info_especialidad.getText());
									valoresOriginales.put("telefono", Info_telefono.getText());
									valoresOriginales.put("correo", Info_correo.getText());
									cambiosRealizados = false;

								}
								mostrarDialogo(frame, exito ? "Éxito" : "Error",
										exito ? "Cambios guardados correctamente." : "Error al guardar los cambios.");
								frame.dispose();
								UsersController uc = new UsersController();
								uc.Ficha_de_instructor(idinstructor);
							}
						});
			}
		});
		panel_2.add(boton_guardar);
		boton_guardar.setForeground(new Color(255, 255, 255));
		boton_guardar.setBackground(new Color(0, 206, 82));
		boton_guardar.setFont(new Font("Anton", Font.PLAIN, 18));
		boton_guardar.setBounds(748, 510, 160, 43);
		panel_2.add(boton_guardar);

		JButton boton_cancelar = new JButton("Cancelar");
		boton_cancelar.setForeground(new Color(255, 255, 255));
		boton_cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cambiosRealizados) {
					mostrarDialogoConfirmacion(frame, "¿Está seguro que desea salir sin guardar los cambios?",
							"Confirmar salida", new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									frame.dispose();
									new UsersController().Ficha_de_instructor(idinstructor);
								}
							});
				} else {
					frame.dispose();
					new UsersController().Ficha_de_instructor(idinstructor);
				}
			}
		});
		panel_2.add(boton_cancelar);
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
				try {
					hc.Clases();
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error al cargar las clases.");
				}
			}
		});
		;
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

	private void mostrarDialogo(JFrame parent, String titulo, String mensaje) {
		JDialog dialogo = new JDialog(parent, titulo, true);
		dialogo.setUndecorated(true);
		dialogo.setSize(400, 180);
		dialogo.setLocationRelativeTo(parent);
		dialogo.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 400, 180);
		panel.setLayout(null);
		dialogo.add(panel);

		JPanel encabezado = new JPanel();
		encabezado.setBackground(new Color(81, 151, 255));
		encabezado.setBounds(0, 0, 400, 33);
		panel.add(encabezado);

		JLabel lblMensaje = new JLabel("<html><div style='text-align: center;'>" + mensaje + "</div></html>",
				SwingConstants.CENTER);
		lblMensaje.setFont(new Font("Anton", Font.PLAIN, 16));
		lblMensaje.setBounds(30, 50, 340, 50);
		panel.add(lblMensaje);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBackground(new Color(0, 206, 82));
		btnAceptar.setForeground(Color.WHITE);
		btnAceptar.setFont(new Font("Anton", Font.PLAIN, 14));
		btnAceptar.setBounds(148, 110, 102, 33);
		btnAceptar.addActionListener(a -> dialogo.dispose());
		panel.add(btnAceptar);

		dialogo.setVisible(true);
	}

	private void mostrarDialogoConfirmacion(JFrame parent, String mensaje, String titulo, ActionListener onConfirm) {
		JDialog dialogo = new JDialog(parent, titulo, true);
		dialogo.setUndecorated(true);
		dialogo.setSize(400, 180);
		dialogo.setLocationRelativeTo(parent);
		dialogo.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 400, 180);
		panel.setLayout(null);
		dialogo.add(panel);

		JPanel encabezado = new JPanel();
		encabezado.setBackground(new Color(81, 151, 255));
		encabezado.setBounds(0, 0, 400, 33);
		panel.add(encabezado);

		JLabel lblMensaje = new JLabel("<html><div style='text-align: center;'>" + mensaje + "</div></html>",
				SwingConstants.CENTER);
		lblMensaje.setFont(new Font("Anton", Font.PLAIN, 16));
		lblMensaje.setBounds(30, 50, 340, 50);
		panel.add(lblMensaje);

		JButton btnSi = new JButton("Sí");
		btnSi.setBackground(new Color(0, 206, 82));
		btnSi.setForeground(Color.WHITE);
		btnSi.setFont(new Font("Anton", Font.PLAIN, 14));
		btnSi.setBounds(90, 110, 90, 33);
		btnSi.addActionListener(a -> {
			dialogo.dispose();
			onConfirm.actionPerformed(a);
		});

		JButton btnNo = new JButton("No");
		btnNo.setBackground(new Color(255, 70, 70));
		btnNo.setForeground(Color.WHITE);
		btnNo.setFont(new Font("Anton", Font.PLAIN, 14));
		btnNo.setBounds(220, 110, 90, 33);
		btnNo.addActionListener(a -> dialogo.dispose());

		panel.add(btnSi);
		panel.add(btnNo);

		dialogo.setVisible(true);
	}

	public void Credencial_instructor(int idinstructor) {
		try {
			UIManager.setLookAndFeel(new FlatLightLaf());
			UIManager.put("Button.arc", 8); // Esquinas redondeadas
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		InstrucoresModel im = new InstrucoresModel();
		Map<String, String> datosInstructor = im.obtenerDatosBasicosInstructor(idinstructor);

		if (datosInstructor.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Instructor no encontrado. ID inválido: " + idinstructor, "Error",
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
		ImageIcon icon1 = new ImageIcon(getClass().getResource("/Imagenes/VADUROTISH.jpg"));
		Image imagen1 = icon1.getImage().getScaledInstance(200, 300, Image.SCALE_SMOOTH);
		Imagen_de_usuario.setIcon(new ImageIcon(imagen1));
		panel_2.add(Imagen_de_usuario);

		JLabel lblNewLabel_5 = new JLabel("INSTRUCTOR");
		lblNewLabel_5.setFont(new Font("Anton", Font.PLAIN, 32));
		lblNewLabel_5.setBounds(70, 61, 147, 68);
		panel_2.add(lblNewLabel_5);

		JLabel lblNewLabel_11 = new JLabel("Nombre:");
		lblNewLabel_11.setFont(new Font("Anton", Font.PLAIN, 18));
		lblNewLabel_11.setBounds(325, 160, 84, 32);
		panel_2.add(lblNewLabel_11);

		JLabel lblNewLabel_10 = new JLabel("Especialidad:");
		lblNewLabel_10.setFont(new Font("Anton", Font.PLAIN, 18));
		lblNewLabel_10.setBounds(325, 230, 127, 32);
		panel_2.add(lblNewLabel_10);

		JLabel lblNewLabel_14 = new JLabel("Horarios: ");
		lblNewLabel_14.setFont(new Font("Anton", Font.PLAIN, 18));
		lblNewLabel_14.setBounds(325, 300, 152, 32);
		panel_2.add(lblNewLabel_14);

		JLabel lblNewLabel_13 = new JLabel("No. Identificador:");
		lblNewLabel_13.setFont(new Font("Anton", Font.PLAIN, 18));
		lblNewLabel_13.setBounds(688, 160, 150, 32);
		panel_2.add(lblNewLabel_13);

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
		JLabel Info_nombre = new JLabel("");
		Info_nombre.setText(datosInstructor.get("nombre"));
		Info_nombre.setFont(new Font("Anton", Font.PLAIN, 22));
		Info_nombre.setBounds(325, 185, 152, 29);
		panel_2.add(Info_nombre);

		JLabel Info_especialidad = new JLabel("");
		Info_especialidad.setText(datosInstructor.get("especialidad"));
		Info_especialidad.setFont(new Font("Anton", Font.PLAIN, 22));
		Info_especialidad.setBounds(325, 255, 300, 30);
		panel_2.add(Info_especialidad);

		JLabel Info_horarios = new JLabel("");
		Info_horarios.setText(datosInstructor.get("horarios"));
		Info_horarios.setFont(new Font("Anton", Font.PLAIN, 22));
		Info_horarios.setBounds(325, 325, 400, 30);
		panel_2.add(Info_horarios);

		JLabel Info_identificador = new JLabel("");
		Info_identificador.setText(datosInstructor.get("id"));
		Info_identificador.setFont(new Font("Anton", Font.PLAIN, 22));
		Info_identificador.setBounds(688, 185, 200, 30);

		panel_2.add(Info_identificador);

		// botones de accion para el cliente
		// ///////////////////////////////////////////////////////////////////////
		JButton boton_descraga_credencial = new JButton("Descargar ");
		boton_descraga_credencial.setBackground(new Color(255, 205, 17));
		boton_descraga_credencial.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_descraga_credencial.setBounds(740, 494, 147, 39);
		boton_descraga_credencial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UsersController controller = new UsersController();
				controller.generarCredencialPDFInstructor(idinstructor);
			}
		});
		panel_2.add(boton_descraga_credencial);

		JButton boton_regresar = new JButton("Regresar");
		boton_regresar.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_regresar.setBackground(new Color(255, 205, 17));
		boton_regresar.setBounds(583, 494, 147, 39);
		boton_regresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				UsersController uc = new UsersController();
				uc.Ficha_de_instructor(idinstructor);
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
				try {
					hc.Clases();
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error al cargar las clases.");
				}
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
		lblNewLabel_11.setBounds(272, 119, 91, 54);
		panel_2.add(lblNewLabel_11);

		JLabel lblNewLabel_10 = new JLabel("Especialidad: ");
		lblNewLabel_10.setFont(new Font("Anton", Font.PLAIN, 16));
		lblNewLabel_10.setBounds(272, 184, 91, 52);
		panel_2.add(lblNewLabel_10);

		JLabel lblNewLabel_9 = new JLabel("Clase asignada:");
		lblNewLabel_9.setFont(new Font("Anton", Font.PLAIN, 16));
		lblNewLabel_9.setBounds(272, 247, 132, 49);
		panel_2.add(lblNewLabel_9);

		JLabel lblNewLabel_8 = new JLabel("Teléfono:");
		lblNewLabel_8.setFont(new Font("Anton", Font.PLAIN, 16));
		lblNewLabel_8.setBounds(272, 307, 104, 50);
		panel_2.add(lblNewLabel_8);

		JLabel lblNewLabel_7 = new JLabel("Correo electronico:");
		lblNewLabel_7.setFont(new Font("Anton", Font.PLAIN, 16));
		lblNewLabel_7.setBounds(272, 368, 132, 50);
		panel_2.add(lblNewLabel_7);

		// info de
		// intructor///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		JTextField Info_nombre = new JTextField("");
		Info_nombre.setBackground(new Color(204, 204, 204));
		Info_nombre.setFont(new Font("Anton", Font.PLAIN, 16));
		Info_nombre.setBounds(420, 119, 320, 50);
		panel_2.add(Info_nombre);

		JTextField Info_especialidad = new JTextField("");
		Info_especialidad.setBackground(new Color(204, 204, 204));
		Info_especialidad.setFont(new Font("Anton", Font.PLAIN, 16));
		Info_especialidad.setBounds(420, 184, 320, 50);
		panel_2.add(Info_especialidad);

		JTextField Info_telefono = new JTextField("");
		Info_telefono.setBackground(new Color(204, 204, 204));
		Info_telefono.setFont(new Font("Anton", Font.PLAIN, 16));
		Info_telefono.setBounds(420, 308, 320, 50);
		panel_2.add(Info_telefono);

		JTextField Info_correo = new JTextField(" ");
		Info_correo.setBackground(new Color(204, 204, 204));
		Info_correo.setFont(new Font("Anton", Font.PLAIN, 16));
		Info_correo.setBounds(420, 369, 320, 50);
		panel_2.add(Info_correo);

		// comobox//////////////////////////////////////////////////////////////////////////////////////////////////////////////

		UIManager.put("ComboBox.buttonBackground", new Color(255, 205, 17));
		JComboBox<String> comboBox_clases = new JComboBox();
		comboBox_clases.setBackground(new Color(204, 204, 204));
		comboBox_clases.setFont(new Font("Anton", Font.PLAIN, 16));
		comboBox_clases.setBounds(420, 247, 320, 50);
		cargarClasesEnComboBox(comboBox_clases);
		panel_2.add(comboBox_clases);

		// botones de accion para el cliente
		// ///////////////////////////////////////////////////////////////////////
		JButton boton_guardar = new JButton("Guardar");
		boton_guardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = Info_nombre.getText().trim();
				String especialidad = Info_especialidad.getText().trim();
				String telefono = Info_telefono.getText().trim();
				String correo = Info_correo.getText().trim();
				String claseSeleccionada = (String) comboBox_clases.getSelectedItem();

				Frame parentFrame = JOptionPane.getFrameForComponent(frame); // Para el JDialog

				// Validación de campos obligatorios
				if (nombre.isEmpty() || especialidad.isEmpty() || correo.isEmpty()) {
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
							"<html><div style='text-align: center;'>Nombre, especialidad y correo son campos obligatorios<br></div></html>");
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

				// Validación de caracteres en el nombre (solo letras)
				if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚÑñ ]+")) {
					// Alerta personalizada si el nombre tiene caracteres no válidos
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
							"<html><div style='text-align: center;'>El nombre solo debe contener letras.</div></html>");
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

				// Validación del teléfono (solo números, si se proporciona)
				if (!telefono.isEmpty() && !telefono.matches("[0-9]+")) {
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

				// Validación del correo (formato básico)
				if (!correo.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
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
							"<html><div style='text-align: center;'>Por favor ingrese un correo electrónico válido.</div></html>");
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

				// Registrar el instructor
				InstrucoresModel im = new InstrucoresModel();
				int idUsuario = im.registrarInstructor(nombre, telefono.isEmpty() ? null : telefono, correo,
						especialidad, claseSeleccionada);

				if (idUsuario != -1) {
					// Éxito - Instructor registrado correctamente
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
							"<html><div style='text-align: center;'>Instructor registrado correctamente<br></div></html>");
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
						new HomeController().Instructores();
					});

					exitoDialog.setVisible(true);
				} else {
					// Error al registrar el instructor
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
							"<html><div style='text-align: center;'>Error al registrar el instructor<br></div></html>");
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
				try {
					hc.Clases();
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error al cargar las clases.");
				}
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

	private void cargarClasesEnComboBox(JComboBox<String> comboBox) {
		comboBox.removeAllItems(); // Limpia opciones previas
		comboBox.addItem("NINGUNA"); // Opción predeterminada

		String sql = "SELECT nombre_clase FROM clase";

		try {
			ConectionModel conexion = new ConectionModel();
			Connection conn = conexion.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				String nombreTarifa = rs.getString("nombre_clase");
				comboBox.addItem(nombreTarifa);
			}

			conexion.close();
		} catch (SQLException e) {
			System.err.println("Error al cargar clase: " + e.getMessage());
		}
	}

	public void Registro_de_clase(int nombreclase) {
		try {
			UIManager.setLookAndFeel(new FlatLightLaf());
			UIManager.put("Button.arc", 8);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		ClaseModel claseModel = new ClaseModel(new ConectionModel().getConnection());
		Clase clase = claseModel.obtenerClasePorId(nombreclase);
		String nombreClase = (clase != null) ? clase.getNombreClase() : "Clase no encontrada";

		InstrucoresModel model = new InstrucoresModel();
		List<Object[]> instructor = model.getInstructor(nombreclase);

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

		JLabel lblNewLabel = new JLabel("REGISTRO: " + nombreClase.toUpperCase());
		lblNewLabel.setFont(new Font("Anton", Font.PLAIN, 26));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(60, 11, 508, 28);
		panel_3.add(lblNewLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 61, 898, 56);
		panel_2.add(scrollPane);

		// Datos de ejemplo
		Object[][] data = instructor.toArray(new Object[0][]);

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

		JScrollPane scrollPane_Pagos = new JScrollPane();
		scrollPane_Pagos.setBounds(10, 128, 898, 371);
		panel_2.add(scrollPane_Pagos);

		DefaultTableModel model1;

		try {
			Connection conn = new ConectionModel().getConnection();
			PreparedStatement stmt = conn.prepareStatement(
					"SELECT u.id_usuario, u.nombre, u.primer_apellido, u.segundo_apellido, u.telefono, u.correo "
							+ "FROM inscripcion i " + "JOIN usuario u ON i.id_usuario = u.id_usuario "
							+ "WHERE i.id_clase = ? AND u.id_rol = 2");
			stmt.setInt(1, nombreclase); // El ID de la clase que viene como parámetro

			ResultSet rs = stmt.executeQuery();

			Vector<String> columnNames1 = new Vector<>(Arrays.asList("ID cliente", "Nombre(s)", "Primer apellido",
					"Segundo apellido", "Teléfono", "Correo electrónico", "Eliminar"));
			Vector<Vector<Object>> data1 = new Vector<>();

			while (rs.next()) {
				Vector<Object> row = new Vector<>();
				row.add(rs.getInt("id_usuario"));
				row.add(rs.getString("nombre"));
				row.add(rs.getString("primer_apellido"));
				row.add(rs.getString("segundo_apellido"));
				row.add(rs.getString("telefono"));
				row.add(rs.getString("correo"));
				row.add("Eliminar"); // Texto que activa el botón
				data1.add(row);
			}

			rs.close();
			stmt.close();
			conn.close();

			model1 = new DefaultTableModel(data1, columnNames1) {
				@Override
				public boolean isCellEditable(int row, int column) {
					return column == 6; // Solo el botón "Eliminar" es editable
				}
			};

		} catch (Exception e) {
			e.printStackTrace();
			model1 = new DefaultTableModel(new Object[][] {}, new String[] { "ID cliente", "Nombre(s)",
					"Primer apellido", "Segundo apellido", "Teléfono", "Correo electrónico", "Eliminar" });
		}

		JTable table_1 = new JTable(model1);
		table_1.setFont(new Font("Anton", Font.PLAIN, 12));
		table_1.setBackground(new Color(204, 204, 204));
		table_1.setRowHeight(30);
		scrollPane_Pagos.setViewportView(table_1);

		JTableHeader header2 = table_1.getTableHeader();
		header2.setBackground(Color.BLACK);
		header2.setForeground(Color.WHITE);
		header2.setFont(new Font("Anton", Font.PLAIN, 14));
		header2.setReorderingAllowed(false);

		// Renderizar botones en la tabla
		table_1.getColumn("Eliminar").setCellRenderer(new ButtonRenderer3("Eliminar"));
		table_1.getColumn("Eliminar")
				.setCellEditor(new ButtonEditor3(new JCheckBox(), "Eliminar", table_1, nombreclase));

		JButton boton_descargar = new JButton("Descargar");
		boton_descargar.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
			        DefaultTableModel modeloTablaAlumnos = (DefaultTableModel) table_1.getModel();
			        UsersController controller = new UsersController(); // O usa la instancia existente
			        controller.generarPDFRegistroClase(nombreclase, instructor, modeloTablaAlumnos);
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
				try {
					hc.Clases();
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error al cargar las clases.");
				}
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
		boton_CLASES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Clases();
			}
		});
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
		private int nombreclase;
		private boolean debeEliminar = false;

		public ButtonEditor3(JCheckBox checkBox, String label, JTable table, int nombreclase) {
			super(checkBox);
			this.label = label;
			this.table_1 = table;
			this.nombreclase = nombreclase;
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

						if (row >= 0 && row < table_1.getRowCount()) {
							try {
								int idUsuario = (int) table_1.getValueAt(row, 0);
								Connection conn = new ConectionModel().getConnection();
								PreparedStatement ps = conn.prepareStatement(
										"DELETE FROM inscripcion WHERE id_usuario = ? AND id_clase = ?");
								ps.setInt(1, idUsuario);
								ps.setInt(2, nombreclase);
								ps.executeUpdate();
								ps.close();
								conn.close();

								// ✅ NO eliminar aquí directamente
								debeEliminar = true;
								JOptionPane.showMessageDialog(null, "Miembro eliminado de la clase.");

							} catch (SQLException ex) {
								ex.printStackTrace();
								JOptionPane.showMessageDialog(null, "Error al eliminar inscripción.");
							}
						}
					});

					dialog.setVisible(true); // Mostrar el diálogo de confirmación
				}
			}
			clicked = false;
			if (debeEliminar && row >= 0 && row < table_1.getRowCount()) {
				SwingUtilities.invokeLater(() -> {
					if (row >= 0 && row < table_1.getRowCount()) {
						((DefaultTableModel) table_1.getModel()).removeRow(row);
					}
				});
				debeEliminar = false;
			}
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

	public void Editar_eliminar_y_añadir_clases(int idclase) {
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
		ClaseModel modelClase = new ClaseModel(new ConectionModel().getConnection());
		List<Clase> clases = modelClase.obtenerTodasLasClases(); // Debes tener este método ya implementado

		Object[][] data = new Object[clases.size()][7];

		for (int i = 0; i < clases.size(); i++) {
			Clase c = clases.get(i);
			String dias = modelClase.getDiasPorClase(c.getIdClase()); // ← Aquí traes los días reales

			data[i][0] = c.getIdClase();
			data[i][1] = c.getNombreClase();
			data[i][2] = c.getNombreEntrenador();
			data[i][3] = c.getTurno();
			data[i][4] = dias; // 👈 ahora sí, días reales
			data[i][5] = "Editar";
			data[i][6] = "Eliminar";
		}

		String[] columnNames = { "ID de clase", "Nombre de la clase", "Entrenador", "Turno", "Horario", "Editar",
				"Eliminar" };

		DefaultTableModel model = new DefaultTableModel(data, columnNames) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return column == 5 || column == 6;
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
				try {
					hc.Clases();
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error al cargar las clases.");
				}
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
		boton_CLASES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeController hc = new HomeController();
				hc.Clases();
			}
		});
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
		private boolean debeEliminar = false;

		public ButtonEditor(JCheckBox checkBox, String label, JTable table) {
			super(checkBox);
			this.label = label;
			this.table = table;
			button = new JButton();
			button.setOpaque(true);
			button.setForeground(Color.BLACK);
			button.setBackground(label.equals("Eliminar") ? new Color(255, 0, 0) : new Color(255, 205, 17));
			button.setFont(new Font("Anton", Font.PLAIN, 14));
			button.setHorizontalAlignment(SwingConstants.CENTER);

			// Cargar icono
			if (label.equals("Editar")) {
				button.setIcon(loadIcon("/Imagenes/editar.png"));
			} else if (label.equals("Eliminar")) {
				button.setIcon(loadIcon("/Imagenes/eliminar.png"));
			}
			button.setText(null);

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
					if (row >= 0 && row < table.getRowCount()) {
						try {
							int idclase = (int) table.getValueAt(row, 0);
							Window window = SwingUtilities.getWindowAncestor(table);
							if (window != null)
								window.dispose();

							UsersController uc = new UsersController();
							uc.Editar_clases(idclase);
						} catch (Exception ex) {
							ex.printStackTrace();
							JOptionPane.showMessageDialog(null, "Error al editar la clase.");
						}
					}
				} else if (label.equals("Eliminar")) {
					JDialog dialog = new JDialog((Frame) null, "Confirmar Eliminación", true);
					dialog.setSize(400, 220);
					dialog.setLocationRelativeTo(null);
					dialog.setUndecorated(true);
					dialog.setLayout(null);

					JPanel confirmar_eliminacion = new JPanel();
					confirmar_eliminacion.setBackground(Color.WHITE);
					confirmar_eliminacion.setBounds(0, 0, 400, 220);
					confirmar_eliminacion.setLayout(null);
					dialog.add(confirmar_eliminacion);

					JPanel panel_complemento = new JPanel();
					panel_complemento.setBackground(new Color(81, 151, 255));
					panel_complemento.setBounds(0, 0, 400, 33);
					confirmar_eliminacion.add(panel_complemento);

					JLabel pregunta = new JLabel(
							"<html><div style='text-align: center;'>La clase se borrará permanentemente<br>¿Desea continuar?</div></html>");
					pregunta.setFont(new Font("Anton", Font.PLAIN, 16));
					pregunta.setBounds(66, 44, 346, 59);
					confirmar_eliminacion.add(pregunta);

					JButton boton_cancelar = new JButton("Cancelar");
					boton_cancelar.setForeground(Color.WHITE);
					boton_cancelar.setFont(new Font("Anton", Font.PLAIN, 14));
					boton_cancelar.setBackground(Color.RED);
					boton_cancelar.setBounds(50, 140, 120, 35);
					confirmar_eliminacion.add(boton_cancelar);

					JButton boton_aceptar = new JButton("Aceptar");
					boton_aceptar.setBackground(new Color(0, 206, 82));
					boton_aceptar.setForeground(Color.WHITE);
					boton_aceptar.setFont(new Font("Anton", Font.PLAIN, 14));
					boton_aceptar.setBounds(230, 140, 120, 35);
					confirmar_eliminacion.add(boton_aceptar);

					boton_cancelar.addActionListener(ev -> dialog.dispose());

					boton_aceptar.addActionListener(ev -> {
						dialog.dispose();
						if (row >= 0 && row < table.getRowCount()) {
							try {
								int idClase = (int) table.getValueAt(row, 0);
								ClaseModel modelClase = new ClaseModel(new ConectionModel().getConnection());
								modelClase.eliminarClase(idClase);
								debeEliminar = true;
								JOptionPane.showMessageDialog(null, "Clase eliminada correctamente.");
							} catch (Exception ex) {
								ex.printStackTrace();
								JOptionPane.showMessageDialog(null, "Error al eliminar la clase.");
							}
						}
					});

					dialog.setVisible(true);
				}
			}

			clicked = false;

			if (debeEliminar && row >= 0 && row < table.getRowCount()) {
				// Esperar a que Swing termine el ciclo de edición
				SwingUtilities.invokeLater(() -> {
					if (row >= 0 && row < table.getRowCount()) {
						((DefaultTableModel) table.getModel()).removeRow(row);
					}
				});
				debeEliminar = false;
			}

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

	public void Editar_clases(int idclase) {
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

		JLabel lblNewLabel_13 = new JLabel("Dias disponibles: ");
		lblNewLabel_13.setFont(new Font("Anton", Font.PLAIN, 20));
		lblNewLabel_13.setBounds(58, 323, 179, 22);
		panel_2.add(lblNewLabel_13);

		// comobox//////////////////////////////////////////////////////////////////////////////////////////////////////////////

		UIManager.put("ComboBox.buttonBackground", new Color(255, 205, 17));

		// ComboBox de turno
		JComboBox<String> comboBox_turno = new JComboBox();
		comboBox_turno.setBackground(new Color(204, 204, 204));
		comboBox_turno.setFont(new Font("Anton", Font.PLAIN, 20));
		comboBox_turno.setBounds(247, 238, 328, 31);
		cargarTurnoEnComboBox(comboBox_turno);
		panel_2.add(comboBox_turno);

		// ComboBox de entrenador
		JComboBox<String> comboBox_entrenador = new JComboBox();
		comboBox_entrenador.setFont(new Font("Anton", Font.PLAIN, 20));
		comboBox_entrenador.setBackground(new Color(204, 204, 204));
		comboBox_entrenador.setBounds(247, 161, 328, 31);
		cargarInstructoresEnComboBox(comboBox_entrenador);
		panel_2.add(comboBox_entrenador);

		// ComboBox de días
		JComboBox<String> comboBox_dias = new JComboBox();
		comboBox_dias.setFont(new Font("Anton", Font.PLAIN, 20));
		comboBox_dias.setBackground(new Color(204, 204, 204));
		comboBox_dias.setBounds(247, 319, 328, 31);
		cargarDiasSemanaEnComboBox(comboBox_dias);
		panel_2.add(comboBox_dias);

		// Conexión y modelo
		Connection conn = new ConectionModel().getConnection();
		ClaseModel model = new ClaseModel(conn);
		Clase clase = model.obtenerClasePorId(idclase);

		// Campo de texto del nombre de la clase
		JTextField Info_nombre1 = new JTextField(clase.getNombreClase());
		Info_nombre1.setBackground(new Color(204, 204, 204));
		Info_nombre1.setFont(new Font("Anton", Font.PLAIN, 20));
		Info_nombre1.setBounds(247, 83, 328, 37);
		panel_2.add(Info_nombre1);

		// Seleccionar entrenador
		comboBox_entrenador.setSelectedItem(clase.getNombreEntrenador());

		// Seleccionar turno (normalizado para coincidir con ComboBox)
		if (clase.getTurno() != null) {
			comboBox_turno.setSelectedItem(normalizarTexto(clase.getTurno()));
		}

		// Seleccionar día (también normalizado)
		if (clase.getHorarios() != null && !clase.getHorarios().isEmpty()) {
			String dia = clase.getHorarios().get(0).getDiaSemana().name();
			comboBox_dias.setSelectedItem(normalizarTexto(dia));
		}

		// botones de accion para el instructor
		// ///////////////////////////////////////////////////////////////////////
		JButton boton_guardar = new JButton("Guardar");
		boton_guardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nuevoNombre = Info_nombre1.getText().trim();
				String turno = comboBox_turno.getSelectedItem().toString();
				String dia = comboBox_dias.getSelectedItem().toString();
				String entrenador = comboBox_entrenador.getSelectedItem().toString();

				if (nuevoNombre.isEmpty()) {
					JOptionPane.showMessageDialog(null, "El nombre de la clase no puede estar vacío.");
					return;
				}

				try {
					Connection conn = new ConectionModel().getConnection();
					ClaseModel model = new ClaseModel(conn);

					// Obtener el ID del entrenador por nombre
					int idEntrenador = obtenerIdEntrenadorPorNombre(entrenador, conn);

					// Actualizar clase principal
					boolean exito = model.actualizarClase(idclase, nuevoNombre, idEntrenador, turno, dia);

					if (exito) {
						JOptionPane.showMessageDialog(null, "Clase actualizada correctamente.");
						frame.dispose();
						HomeController hc = new HomeController();
						hc.Clases();
					} else {
						JOptionPane.showMessageDialog(null, "No se pudo actualizar la clase.");
					}

				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error de base de datos.");
				}
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
				uc.Editar_eliminar_y_añadir_clases(idclase);

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

		JLabel lblNewLabel_13 = new JLabel("Dias disponibles: ");
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

		// comobox//////////////////////////////////////////////////////////////////////////////////////////////////////////////

		UIManager.put("ComboBox.buttonBackground", new Color(255, 205, 17));
		JComboBox<String> comboBox_turno = new JComboBox();
		comboBox_turno.setBackground(new Color(204, 204, 204));
		comboBox_turno.setFont(new Font("Anton", Font.PLAIN, 20));
		comboBox_turno.setBounds(247, 238, 328, 31);
		cargarTurnoEnComboBox(comboBox_turno);
		panel_2.add(comboBox_turno);

		JComboBox<String> comboBox_entrenador = new JComboBox();
		comboBox_entrenador.setFont(new Font("Anton", Font.PLAIN, 20));
		comboBox_entrenador.setBackground(new Color(204, 204, 204));
		comboBox_entrenador.setBounds(247, 161, 328, 31);
		cargarInstructoresEnComboBox(comboBox_entrenador);
		panel_2.add(comboBox_entrenador);

		JComboBox<String> comboBox_dias = new JComboBox();
		comboBox_dias.setFont(new Font("Anton", Font.PLAIN, 20));
		comboBox_dias.setBackground(new Color(204, 204, 204));
		comboBox_dias.setBounds(247, 319, 328, 31);
		cargarDiasSemanaEnComboBox(comboBox_dias);
		panel_2.add(comboBox_dias);

		// botones de accion para el instructor
		// ///////////////////////////////////////////////////////////////////////
		JButton boton_guardar = new JButton("Guardar");
		boton_guardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombreClase = Info_nombre.getText().trim();
				String turno = comboBox_turno.getSelectedItem().toString();
				String dia = comboBox_dias.getSelectedItem().toString();
				String entrenadorNombre = comboBox_entrenador.getSelectedItem().toString();

				if (nombreClase.isEmpty() || turno.equals("NINGUNO") || dia.equals("NINGUNO")) {
					JOptionPane.showMessageDialog(frame, "Por favor, complete todos los campos obligatorios.");
					return;
				}

				try {
					Connection conn = new ConectionModel().getConnection();
					ClaseModel model = new ClaseModel(conn);

					// Obtener ID del entrenador
					int idEntrenador = obtenerIdEntrenadorPorNombre(entrenadorNombre, conn); // Debes tener este método
					if (idEntrenador == -1) {
						JOptionPane.showMessageDialog(frame, "Entrenador no válido.");
						return;
					}

					// Insertar clase
					int idClase = model.insertarClase(nombreClase, idEntrenador);

					// Insertar horario
					model.insertarHorario(idClase, dia, turno);

					JOptionPane.showMessageDialog(frame, "Clase añadida correctamente.");
					frame.dispose();
					UsersController uc = new UsersController();
					uc.Editar_eliminar_y_añadir_clases(0); // Refrescar vista

				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(frame, "Error al guardar la clase.");
				}
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
				uc.Editar_eliminar_y_añadir_clases(0);
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

	private void cargarInstructoresEnComboBox(JComboBox<String> comboBox) {
		comboBox.removeAllItems(); // Limpia opciones previas
		comboBox.addItem("NINGUNO"); // Opción predeterminada

		// Consulta para obtener los nombres de los instructores (rol = 3)
		String sql = "SELECT id_usuario, nombre FROM usuario WHERE id_rol = 3 ORDER BY nombre";

		try {
			ConectionModel conexion = new ConectionModel();
			Connection conn = conexion.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				// Obtener el ID y nombre del instructor
				int idInstructor = rs.getInt("id_usuario");
				String nombreInstructor = rs.getString("nombre");

				// Agregar al ComboBox (puedes mostrar solo el nombre o nombre + ID)
				comboBox.addItem(nombreInstructor);

				// Opcional: Guardar el ID como dato asociado al item
				comboBox.setSelectedItem(nombreInstructor);
				comboBox.putClientProperty(nombreInstructor, idInstructor);
			}

			conexion.close();
		} catch (SQLException e) {
			System.err.println("Error al cargar instructores: " + e.getMessage());
			e.printStackTrace();
		}
	}

	private void cargarDiasSemanaEnComboBox(JComboBox<String> comboBox) {
		comboBox.removeAllItems(); // Limpiar opciones previas
		comboBox.addItem("NINGUNO");

		String sql = "SHOW COLUMNS FROM clase_horario LIKE 'dia_semana'";

		try {
			ConectionModel conexion = new ConectionModel();
			Connection conn = conexion.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				String enumDefinition = rs.getString("Type");
				String[] enumValues = enumDefinition.replaceAll("enum\\('|'\\)", "").split("','");

				for (String dia : enumValues) {
					comboBox.addItem(normalizarTexto(dia)); // 👈 se normaliza
				}
			}

			conexion.close();
		} catch (SQLException e) {
			System.err.println("Error al cargar días de la semana: " + e.getMessage());
			e.printStackTrace();
		}
	}

	private void cargarTurnoEnComboBox(JComboBox<String> comboBox) {
		comboBox.removeAllItems(); // Limpiar opciones previas
		comboBox.addItem("NINGUNO");

		String sql = "SHOW COLUMNS FROM clase_horario LIKE 'turno'";

		try {
			ConectionModel conexion = new ConectionModel();
			Connection conn = conexion.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				String enumDefinition = rs.getString("Type");
				String[] enumValues = enumDefinition.replaceAll("enum\\('|'\\)", "").split("','");

				for (String turno : enumValues) {
					comboBox.addItem(normalizarTexto(turno)); // 👈 se normaliza
				}
			}

			conexion.close();
		} catch (SQLException e) {
			System.err.println("Error al cargar turno: " + e.getMessage());
			e.printStackTrace();
		}
	}

	private String normalizarTexto(String texto) {
		return java.text.Normalizer.normalize(texto, java.text.Normalizer.Form.NFD)
				.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "").toUpperCase();
	}

}