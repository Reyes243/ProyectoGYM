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
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import com.formdev.flatlaf.FlatLightLaf;

import Vistas.Clases.ButtonEditor3;
import Vistas.Clases.ButtonRenderer3;
import Vistas.Instructores.ButtonEditor2;
import Vistas.Instructores.ButtonRenderer2;
import controllers.HomeController;
import controllers.UsersController;
import models.ConectionModel;
import models.User;
import models.UsersModel;

public class HomeView {

	public HomeView() {

	}

	public void Panel_inicio() {
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
		ImageIcon icon1 = new ImageIcon(getClass().getResource("/Imagenes/logo sin letras.png"));
		Image imagen1 = icon1.getImage().getScaledInstance(53, 53, Image.SCALE_SMOOTH);
		lblNewLabel_1.setIcon(new ImageIcon(imagen1));
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

		JLabel lblNewLabel = new JLabel("INICIO");// titulo de inicio
		lblNewLabel.setFont(new Font("Anton", Font.PLAIN, 26));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(60, 11, 105, 28);
		panel_3.add(lblNewLabel);

		Object[][] data = { { null, null, null, null, "1", "2", "3" }, // Semana 1
				{ "4", "5", "6", "7", "8", "9", "10" }, // Semana 2
				{ "11", "12", "13", "14", "15", "16", "17" }, // Semana 3
				{ "18", "19", "20", "21", "22", "23", "24" }, // Semana 4
				{ "25", "26", "27", "28", "29", "30", "31" } // Semana 5
		};
		JTable table = new JTable(data, new String[] { "", "", "", "", "", "", "" });
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

		JLabel lblNewLabel_7 = new JLabel("<html>" + "<div style='padding:5px; text-align:left; '>"
				+ "-Procura tomar suficiente agua durante tu entrenamiento y durante el día.<br><br>"
				+ "-El descanso correcto es fundamental para progresar, así como la técnica al "
				+ "ejecutar ejercicios, sobre todo si se hacen con pesos pesados." + "</div></html>");
		lblNewLabel_7.setFont(new Font("Anton", Font.PLAIN, 16));
		lblNewLabel_7.setBounds(690, 206, 218, 334);
		lblNewLabel_7.setVerticalAlignment(JLabel.TOP); // Alinea el texto en la parte superior
		panel_2.add(lblNewLabel_7);

		JLabel lblNewLabel_8 = new JLabel("Consejos:");
		lblNewLabel_8.setFont(new Font("Anton", Font.PLAIN, 20));
		lblNewLabel_8.setBounds(690, 161, 95, 34);
		panel_2.add(lblNewLabel_8);
		// botones laterales
		// ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		JButton boton_INICIO = new JButton("INICIO");// boton de inicio
		boton_INICIO.setBackground(new Color(255, 255, 255));
		boton_INICIO.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_INICIO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//					frame.dispose();
//					HomeController hc = new HomeController();
//					hc.Panel_inicio();
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

	public void Clientes(List<User> clientes) {
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

		JLabel lblNewLabel = new JLabel("CLIENTES");
		lblNewLabel.setFont(new Font("Anton", Font.PLAIN, 26));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(60, 11, 105, 28);
		panel_3.add(lblNewLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 61, 898, 450);
		panel_2.add(scrollPane);

		String[] columnNames = { "ID", "Nombre", "Primer Apellido", "Teléfono", "Correo", "Consulta", "Eliminar" };

		DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
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

		for (User usuario : clientes) {
			Object[] fila = { usuario.getId(), usuario.getNombre(), usuario.getPrimerApellido(), usuario.getTelefono(),
					usuario.getCorreo(), "", "" };
			model.addRow(fila);
		}

		// Renderizar botones en la tabla
		table.getColumn("Consulta").setCellRenderer(new ButtonRenderer("Consulta"));
		table.getColumn("Consulta").setCellEditor(new ButtonEditor(new JCheckBox(), "Consulta", table));

		table.getColumn("Eliminar").setCellRenderer(new ButtonRenderer("Eliminar"));
		table.getColumn("Eliminar").setCellEditor(new ButtonEditor(new JCheckBox(), "Eliminar", table));

		// Botón Añadir cliente
		JButton boton_Añadir_cliente = new JButton("Añadir cliente");
		boton_Añadir_cliente.setForeground(new Color(255, 255, 255));
		boton_Añadir_cliente.setBackground(new Color(0, 143, 57));
		boton_Añadir_cliente.setFont(new Font("Anton", Font.PLAIN, 14));
		boton_Añadir_cliente.setBounds(763, 522, 145, 31);
		boton_Añadir_cliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				UsersController controller = new UsersController();
				controller.Añadir_cliente();
			}
		});
		panel_2.add(boton_Añadir_cliente);

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
		boton_CLIENTES.setBackground(new Color(255, 255, 255));
		boton_CLIENTES.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CLIENTES.setBounds(10, 168, 136, 71);
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

	// Renderer para mostrar botones en tabla clientes
	public class ButtonRenderer extends JButton implements TableCellRenderer {
		public ButtonRenderer(String label) {
			setOpaque(true);
			setForeground(Color.BLACK);
			setBackground(new Color(255, 205, 17));
			setFont(new Font("Anton", Font.PLAIN, 14));
			setHorizontalAlignment(SwingConstants.CENTER);

			// Cargar icono según etiqueta
			if (label.equals("Consulta")) {
				setIcon(loadIcon("/Imagenes/editar.png"));
				setBackground(new Color(255, 205, 17));
			} else if (label.equals("Eliminar")) {
				setIcon(loadIcon("/Imagenes/eliminar.png"));
				setBackground(new Color(205, 0, 0));
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

	// Editor para que los botones con imagen funcionen en tabla clientes
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
			if (label.equals("Consulta")) {
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
				if (label.equals("Eliminar")) {
					int idCliente = (int) table.getValueAt(row, 0);

					// Crear el JDialog personalizado (ALERTA DE CONFIRMACIÓN)
					final boolean[] confirmado = {false};

					// Usamos Window como padre para evitar errores de constructor
					Frame parentFrame = JOptionPane.getFrameForComponent(table);
					JDialog dialog = new JDialog(parentFrame, "Confirmación", true);
					dialog.setSize(400, 180);
					dialog.setLayout(null);
					dialog.setUndecorated(true);
					dialog.setLocationRelativeTo(table);

					// Panel superior decorativo
					JPanel header = new JPanel();
					header.setBackground(new Color(81, 151, 255));
					header.setBounds(0, 0, 400, 33);
					dialog.add(header);

					// Mensaje de confirmación
					JLabel mensaje = new JLabel("<html><div style='text-align: center;'>Se borrará al cliente permanentemente<br>¿Desea continuar?</div></html>");
					mensaje.setFont(new Font("Anton", Font.PLAIN, 16));
					mensaje.setBounds(69, 44, 297, 59);
					dialog.add(mensaje);

					// Botón ACEPTAR
					JButton aceptar = new JButton("Aceptar");
					aceptar.setBackground(new Color(0, 206, 82));
					aceptar.setForeground(Color.WHITE);
					aceptar.setFont(new Font("Anton", Font.PLAIN, 14));
					aceptar.setBounds(264, 123, 102, 33);
					dialog.add(aceptar);

					// Botón CANCELAR
					JButton cancelar = new JButton("Cancelar");
					cancelar.setBackground(Color.RED);
					cancelar.setForeground(Color.WHITE);
					cancelar.setFont(new Font("Anton", Font.PLAIN, 14));
					cancelar.setBounds(38, 123, 102, 33);
					dialog.add(cancelar);

					// Listeners
					aceptar.addActionListener(e -> {
						confirmado[0] = true;
						dialog.dispose();
					});

					cancelar.addActionListener(e -> {
						dialog.dispose();
					});

					// Mostrar alerta (modal)
					dialog.setVisible(true);

					// Si el usuario confirmó
					if (confirmado[0]) {
						UsersModel model = new UsersModel();
						boolean eliminado = model.eliminarCliente(idCliente);

						if (eliminado) {
							DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
							tableModel.removeRow(row);

							JDialog exitoDialog = new JDialog(parentFrame, "Éxito", true);
		                    exitoDialog.setSize(400, 180);
		                    exitoDialog.setLayout(null);
		                    exitoDialog.setUndecorated(true);
		                    exitoDialog.setLocationRelativeTo(table);

		                    JPanel eliminarExito = new JPanel();
		                    eliminarExito.setBackground(new Color(255, 255, 255));
		                    eliminarExito.setBounds(0, 0, 400, 180);
		                    eliminarExito.setLayout(null);
		                    exitoDialog.add(eliminarExito);

		                    JPanel panelComplementoExito = new JPanel();
		                    panelComplementoExito.setBackground(new Color(81, 151, 255));
		                    panelComplementoExito.setBounds(0, 0, 400, 33);
		                    eliminarExito.add(panelComplementoExito);

		                    JLabel mensajeExito = new JLabel("<html><div style='text-align: center;'>Cliente eliminado correctamente<br></div></html>");
		                    mensajeExito.setFont(new Font("Anton", Font.PLAIN, 16));
		                    mensajeExito.setBounds(85, 44, 305, 59);
		                    eliminarExito.add(mensajeExito);

		                    JButton botonAceptarExito = new JButton("Aceptar");
		                    botonAceptarExito.setBackground(new Color(0, 206, 82));
		                    botonAceptarExito.setForeground(Color.WHITE);
		                    botonAceptarExito.setFont(new Font("Anton", Font.PLAIN, 14));
		                    botonAceptarExito.setBounds(151, 121, 102, 33);
		                    eliminarExito.add(botonAceptarExito);

		                    botonAceptarExito.addActionListener(e -> exitoDialog.dispose());

		                    exitoDialog.setVisible(true);
		                    
						} else {
							JDialog errorDialog = new JDialog(parentFrame, "Error", true);
		                    errorDialog.setSize(400, 180);
		                    errorDialog.setLayout(null);
		                    errorDialog.setUndecorated(true);
		                    errorDialog.setLocationRelativeTo(table);

		                    JPanel eliminarError = new JPanel();
		                    eliminarError.setBackground(new Color(255, 255, 255));
		                    eliminarError.setBounds(0, 0, 400, 180);
		                    eliminarError.setLayout(null);
		                    errorDialog.add(eliminarError);

		                    JPanel panelComplementoError = new JPanel();
		                    panelComplementoError.setBackground(new Color(81, 151, 255));
		                    panelComplementoError.setBounds(0, 0, 400, 33);
		                    eliminarError.add(panelComplementoError);

		                    JLabel mensajeError = new JLabel("<html><div style='text-align: center;'>Error al eliminar el cliente<br></div></html>");
		                    mensajeError.setFont(new Font("Anton", Font.PLAIN, 16));
		                    mensajeError.setBounds(113, 44, 277, 59);
		                    eliminarError.add(mensajeError);

		                    JButton botonAceptarError = new JButton("Aceptar");
		                    botonAceptarError.setBackground(new Color(0, 206, 82));
		                    botonAceptarError.setForeground(Color.WHITE);
		                    botonAceptarError.setFont(new Font("Anton", Font.PLAIN, 14));
		                    botonAceptarError.setBounds(151, 121, 102, 33);
		                    eliminarError.add(botonAceptarError);

		                    botonAceptarError.addActionListener(e -> errorDialog.dispose());

		                    errorDialog.setVisible(true);
						}
					}

				} else if (label.equals("Consulta")) {
					int idCliente = (int) table.getValueAt(row, 0);

					Window window = SwingUtilities.getWindowAncestor(table);
					if (window != null) {
						window.dispose();
					}
					UsersController controller = new UsersController();
					controller.Informacion_de_cliente(idCliente);
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

	public void Tarifas() {
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

		JLabel lblNewLabel = new JLabel("TARIFAS");// titulo de inicio
		lblNewLabel.setFont(new Font("Anton", Font.PLAIN, 26));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(60, 11, 105, 28);
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
		boton_inf_plan_estandar.setBounds(225, 139, 40, 40);
		ImageIcon icon1 = new ImageIcon(getClass().getResource("/Imagenes/descripcion.png"));
		Image imagen1 = icon1.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		boton_inf_plan_estandar.setIcon(new ImageIcon(imagen1));
		plan_estandar.add(boton_inf_plan_estandar);

		JButton boton_plan_instcibsion_estandar = new JButton("");
		boton_plan_instcibsion_estandar.setBackground(new Color(255, 205, 17));
		boton_plan_instcibsion_estandar.setBounds(225, 88, 40, 40);
		ImageIcon icon4 = new ImageIcon(getClass().getResource("/Imagenes/usuarios.png"));
		Image imagen4 = icon4.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		boton_plan_instcibsion_estandar.setIcon(new ImageIcon(imagen4));
		boton_plan_instcibsion_estandar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				UsersController uc = new UsersController();
				uc.Clientes_con_tarifa_ESTANDAR();

			}
		});
		plan_estandar.add(boton_plan_instcibsion_estandar);

		JButton boton_inf_plan_premium = new JButton("");
		boton_inf_plan_premium.setBackground(new Color(255, 205, 17));
		boton_inf_plan_premium.setBounds(225, 139, 40, 40);
		ImageIcon icon2 = new ImageIcon(getClass().getResource("/Imagenes/descripcion.png"));
		Image imagen2 = icon2.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		boton_inf_plan_premium.setIcon(new ImageIcon(imagen2));
		plan_premium.add(boton_inf_plan_premium);

		JButton boton_plan_instcibsion_premium = new JButton("");
		boton_plan_instcibsion_premium.setBackground(new Color(255, 205, 17));
		boton_plan_instcibsion_premium.setBounds(225, 88, 40, 40);
		ImageIcon icon5 = new ImageIcon(getClass().getResource("/Imagenes/usuarios.png"));
		Image imagen5 = icon5.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		boton_plan_instcibsion_premium.setIcon(new ImageIcon(imagen5));
		boton_plan_instcibsion_premium.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				UsersController uc = new UsersController();
				uc.Clientes_con_tarifa_PREMIUM();

			}
		});
		plan_premium.add(boton_plan_instcibsion_premium);

		JButton boton_inf_plan_familiar = new JButton("");
		boton_inf_plan_familiar.setBackground(new Color(255, 205, 17));
		boton_inf_plan_familiar.setBounds(225, 139, 40, 40);
		ImageIcon icon3 = new ImageIcon(getClass().getResource("/Imagenes/descripcion.png"));
		Image imagen3 = icon3.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		boton_inf_plan_familiar.setIcon(new ImageIcon(imagen3));
		plan_familiar.add(boton_inf_plan_familiar);

		JButton boton_plan_instcibsion_familiar = new JButton("");
		boton_plan_instcibsion_familiar.setBackground(new Color(255, 205, 17));
		boton_plan_instcibsion_familiar.setBounds(225, 88, 40, 40);
		ImageIcon icon6 = new ImageIcon(getClass().getResource("/Imagenes/usuarios.png"));
		Image imagen6 = icon6.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		boton_plan_instcibsion_familiar.setIcon(new ImageIcon(imagen6));
		boton_plan_instcibsion_familiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				UsersController uc = new UsersController();
				uc.Clientes_con_tarifa_FAMILIAR();

			}
		});
		plan_familiar.add(boton_plan_instcibsion_familiar);

		JButton boton_editar_tarifas = new JButton("Editar tarifas");
		boton_editar_tarifas.setBackground(new Color(255, 205, 17));
		boton_editar_tarifas.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_editar_tarifas.setBounds(754, 513, 136, 40);
		boton_editar_tarifas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				UsersController uc = new UsersController();
				uc.Editar_tarifas();

			}
		});
		panel_2.add(boton_editar_tarifas);

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

	public void Instructores() {
		try {
			UIManager.setLookAndFeel(new FlatLightLaf());
			UIManager.put("Button.arc", 8);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		JFrame frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(0, 0, 1100, 700);
		// frame.setLocationRelativeTo(null);
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

		JLabel lblNewLabel = new JLabel("INSTRUCTORES");
		lblNewLabel.setFont(new Font("Anton", Font.PLAIN, 26));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(60, 11, 205, 28);
		panel_3.add(lblNewLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 61, 898, 450);
		panel_2.add(scrollPane);
		// tabla/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		Object[][] data = { { 1, "Carlos", "Crossfit", "carlos@mail.com", "", "" }, // Datos de ejemplo
				{ 2, "Laura", "Spinning y Cardio Dance", "laura@mail.com", "", "" } };

		String[] columnNames = { "ID", "Nombre", "Especialidad", "Correo", "Detalles", "Eliminar" };

		DefaultTableModel model = new DefaultTableModel(data, columnNames) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return column == 4 || column == 6;
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
		table.getColumn("Detalles").setCellRenderer(new ButtonRenderer2("Detalles"));
		table.getColumn("Detalles").setCellEditor(new ButtonEditor2(new JCheckBox(), "Detalles", table));

		table.getColumn("Eliminar").setCellRenderer(new ButtonRenderer2("Eliminar"));
		table.getColumn("Eliminar").setCellEditor(new ButtonEditor2(new JCheckBox(), "Eliminar", table));

		// Botón Añadir instructor

		JButton boton_Añadir_instructor = new JButton("Añadir instructor");
		boton_Añadir_instructor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				UsersController uc = new UsersController();
				uc.Añadir_instructor();
			}
		});
		boton_Añadir_instructor.setForeground(new Color(255, 255, 255));
		boton_Añadir_instructor.setBackground(new Color(0, 143, 57));
		boton_Añadir_instructor.setFont(new Font("Anton", Font.PLAIN, 14));
		boton_Añadir_instructor.setBounds(763, 522, 145, 31);
		panel_2.add(boton_Añadir_instructor);

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
		boton_INSTRUCTORES.setBackground(new Color(255, 255, 255));
		boton_INSTRUCTORES.setFont(new Font("Anton", Font.PLAIN, 16));
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

	// Renderer para mostrar botones en tabla
	public class ButtonRenderer2 extends JButton implements TableCellRenderer {
		public ButtonRenderer2(String label) {
			setOpaque(true);
			setForeground(Color.BLACK);
			setBackground(new Color(255, 205, 17));
			setFont(new Font("Anton", Font.PLAIN, 14));
			setHorizontalAlignment(SwingConstants.CENTER);

			// Cargar icono según etiqueta
			if (label.equals("Detalles")) {
				setIcon(loadIcon("/Imagenes/consultar.png"));
				setBackground(new Color(255, 205, 17));
			} else if (label.equals("Eliminar")) {
				setIcon(loadIcon("/Imagenes/eliminar.png"));
				setBackground(new Color(205, 0, 0));
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
	public class ButtonEditor2 extends DefaultCellEditor {
		protected JButton button;
		private String label;
		private boolean clicked;
		private int row;
		private JTable table;

		public ButtonEditor2(JCheckBox checkBox, String label, JTable table) {
			super(checkBox);
			this.label = label;
			this.table = table;
			button = new JButton();
			button.setOpaque(true);
			button.setForeground(Color.BLACK);
			button.setBackground(new Color(255, 205, 17));
			button.setFont(new Font("Anton", Font.PLAIN, 14));
			button.setHorizontalAlignment(SwingConstants.CENTER);

			// Cargar icono
			if (label.equals("Detalles")) {
				button.setIcon(loadIcon("/Imagenes/consultar.png"));
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
				if (label.equals("Eliminar")) {
					// Aquí conecta con la base de datos para borrar el registro según el ID de la
					// fila seleccionada
					// y luego actualiza la tabla recargando datos.
				} else if (label.equals("Detalles")) {
					String nombreInstructor = (String) table.getValueAt(row, 0); // Obtén el nombre
					Window window = SwingUtilities.getWindowAncestor(table);
					if (window != null) {
						window.dispose();
					}

					UsersController uc = new UsersController();
					uc.Ficha_de_instructor(nombreInstructor);
					// Aquí abre un formulario para editar la fila,
					// luego guarda cambios en la base de datos con un UPDATE,
					// y recarga la tabla con los datos actualizados.
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

	public void Clases() {
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

		JLabel Label_de_titulo_de_ventana = new JLabel("CLASES");
		Label_de_titulo_de_ventana.setFont(new Font("Anton", Font.PLAIN, 26));
		Label_de_titulo_de_ventana.setForeground(new Color(255, 255, 255));
		Label_de_titulo_de_ventana.setBounds(60, 11, 105, 28);
		panel_3.add(Label_de_titulo_de_ventana);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 61, 898, 450);
		panel_2.add(scrollPane);
		// TAbla de
		// clases////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		Object[][] data = { { 1, "YOGA RELAX", "Laura Mendez", "VESPERTINO", "Lunes y Viernes ", "", "" } };// Datos de
																											// ejemplo

		String[] columnNames = { "ID", "Nombre de la clase", "Entrenador", "Turno", "Horario", "Inscribir",
				"Registros" };

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
		table.getColumn("Inscribir").setCellRenderer(new ButtonRenderer3("Inscribir"));
		table.getColumn("Inscribir").setCellEditor(new ButtonEditor3(new JCheckBox(), "Inscribir", table));

		table.getColumn("Registros").setCellRenderer(new ButtonRenderer3("Registros"));
		table.getColumn("Registros").setCellEditor(new ButtonEditor3(new JCheckBox(), "Registros", table));

		// Botón Añadir cliente
		JButton boton_Editar_clases = new JButton("Editar clases");
		boton_Editar_clases.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				UsersController uc = new UsersController();
				uc.Editar_eliminar_y_añadir_clases();
			}
		});
		boton_Editar_clases.setForeground(new Color(0, 0, 0));
		boton_Editar_clases.setBackground(new Color(255, 205, 17));
		boton_Editar_clases.setFont(new Font("Anton", Font.PLAIN, 14));
		boton_Editar_clases.setBounds(763, 522, 145, 31);
		panel_2.add(boton_Editar_clases);

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

	// Renderer para mostrar botones en tabla
	public class ButtonRenderer3 extends JButton implements TableCellRenderer {
		public ButtonRenderer3(String label) {
			setOpaque(true);
			setForeground(Color.BLACK);
			setBackground(new Color(255, 205, 17));
			setFont(new Font("Anton", Font.PLAIN, 14));
			setHorizontalAlignment(SwingConstants.CENTER);

			// Cargar icono según etiqueta
			if (label.equals("Inscribir")) {
				setIcon(loadIcon("/Imagenes/agregar usuario.png"));
				setBackground(new Color(0, 206, 82));
			} else if (label.equals("Registros")) {
				setIcon(loadIcon("/Imagenes/lista.png"));
				setBackground(new Color(255, 205, 17));
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
	public class ButtonEditor3 extends DefaultCellEditor {
		protected JButton button;
		private String label;
		private boolean clicked;
		private int row;
		private JTable table;

		public ButtonEditor3(JCheckBox checkBox, String label, JTable table) {
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
			if (label.equals("Inscribir")) {
				button.setIcon(loadIcon("/Imagenes/agregar usuario.png"));
			} else if (label.equals("Registros")) {
				button.setIcon(loadIcon("/Imagenes/lista.png"));
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
				if (label.equals("Registros")) {
					String nombreClase = (String) table.getValueAt(row, 0); // Obtén el nombre
					Window window = SwingUtilities.getWindowAncestor(table);
					if (window != null) {
						window.dispose();
					}
					UsersController uc = new UsersController();
					uc.Registro_de_clase(nombreClase);
					// Aquí conecta con la base de datos para borrar el registro según el ID de la
					// fila seleccionada
					// y luego actualiza la tabla recargando datos.
				} else if (label.equals("Inscribir")) {
					Frame frame = JOptionPane.getFrameForComponent(table);
					JDialog idDialog = new JDialog(frame, "Inscribir usuario", true);
					idDialog.setSize(400, 180);
					idDialog.setUndecorated(true);
					idDialog.setLocationRelativeTo(frame);
					idDialog.setLayout(null);

					// Panel principal
					JPanel id_de_usuario_clase = new JPanel();
					id_de_usuario_clase.setBackground(Color.WHITE);
					id_de_usuario_clase.setBounds(0, 0, 400, 180);
					id_de_usuario_clase.setLayout(null);
					idDialog.add(id_de_usuario_clase);

					// Panel superior
					JPanel panel_complemento = new JPanel();
					panel_complemento.setBackground(new Color(81, 151, 255));
					panel_complemento.setBounds(0, 0, 400, 33);
					id_de_usuario_clase.add(panel_complemento);

					// Texto explicativo
					JLabel pregunta_de_confirmacion = new JLabel(
					    "<html><div style='text-align: center;'>Ingrese el ID del usuario a inscribir a la clase:</div></html>",
					    SwingConstants.CENTER
					);
					pregunta_de_confirmacion.setFont(new Font("Anton", Font.PLAIN, 16));
					pregunta_de_confirmacion.setBounds(25, 40, 350, 40);
					id_de_usuario_clase.add(pregunta_de_confirmacion);

					// Campo de texto
					JTextField textField = new JTextField();
					textField.setBounds(150, 85, 100, 30);
					id_de_usuario_clase.add(textField);
					textField.setColumns(10);

					// Etiqueta ID
					JLabel lblNewLabel = new JLabel("ID:");
					lblNewLabel.setFont(new Font("Anton", Font.PLAIN, 16));
					lblNewLabel.setBounds(120, 85, 30, 30);
					id_de_usuario_clase.add(lblNewLabel);

					// Botón Aceptar
					JButton boton_aceptar = new JButton("Aceptar");
					boton_aceptar.setBackground(new Color(0, 206, 82));
					boton_aceptar.setForeground(Color.WHITE);
					boton_aceptar.setFont(new Font("Anton", Font.PLAIN, 14));
					boton_aceptar.setBounds(230, 130, 100, 30);
					id_de_usuario_clase.add(boton_aceptar);

					// Acción del botón Aceptar
					boton_aceptar.addActionListener(e -> {
					    String idUsuario = textField.getText().trim();
					    if (!idUsuario.isEmpty()) {
					        // Aquí haces lo que necesitas con el ID, como inscribir al usuario
					        System.out.println("ID ingresado: " + idUsuario);
					        idDialog.dispose();
					    } else {
					        JOptionPane.showMessageDialog(idDialog, "Por favor, ingrese un ID válido.", "Campo vacío", JOptionPane.WARNING_MESSAGE);
					    }
					});

					// Botón Cancelar
					JButton boton_cancelar = new JButton("Cancelar");
					boton_cancelar.setBackground(Color.RED);
					boton_cancelar.setForeground(Color.WHITE);
					boton_cancelar.setFont(new Font("Anton", Font.PLAIN, 14));
					boton_cancelar.setBounds(70, 130, 100, 30);
					id_de_usuario_clase.add(boton_cancelar);

					// Acción del botón Cancelar
					boton_cancelar.addActionListener(e -> idDialog.dispose());

					// Mostrar el diálogo
					idDialog.setVisible(true);


					// Aquí abre un formulario para editar la fila,
					// luego guarda cambios en la base de datos con un UPDATE,
					// y recarga la tabla con los datos actualizados.
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

	public void Panel_checador() {
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

		JLabel lblNewLabel = new JLabel("CHECADOR");// titulo de inicio
		lblNewLabel.setFont(new Font("Anton", Font.PLAIN, 26));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(60, 11, 309, 28);
		panel_3.add(lblNewLabel);

		Object[][] data = { { 1, "Brandon Arce Ulloa", "12:02 pm" } };

		String[] columnas = { "ID cliente", "Nombre(s)", "Entrada" };

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
		// inicio del boton Añadir
		// entrada/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		JButton boton_Añadir_entrada = new JButton("Añadir entrada");
		boton_Añadir_entrada.setForeground(new Color(255, 255, 255));
		boton_Añadir_entrada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JDialog dialogoAlerta = new JDialog(frame, "Registrar Entrada", true);
				dialogoAlerta.setSize(400, 220);
				dialogoAlerta.setLocationRelativeTo(frame);
				dialogoAlerta.setResizable(false);
				dialogoAlerta.setUndecorated(true);
				dialogoAlerta.setLayout(null);

				JPanel cliente_entrada = new JPanel();
				cliente_entrada.setBackground(Color.WHITE);
				cliente_entrada.setBounds(0, 0, 400, 220);
				cliente_entrada.setLayout(null);
				dialogoAlerta.add(cliente_entrada);

				JPanel panel_complemento = new JPanel();
				panel_complemento.setBackground(new Color(81, 151, 255));
				panel_complemento.setBounds(0, 0, 400, 33);
				cliente_entrada.add(panel_complemento);

				JLabel Label_alerta = new JLabel("Introduce el ID para registrar la hora de entrada");
				Label_alerta.setFont(new Font("Anton", Font.PLAIN, 16));
				Label_alerta.setBounds(38, 44, 319, 22);
				cliente_entrada.add(Label_alerta);

				JLabel label_ID = new JLabel("ID :");
				label_ID.setFont(new Font("Anton", Font.PLAIN, 16));
				label_ID.setBounds(130, 86, 46, 22);
				cliente_entrada.add(label_ID);

				// Este es donde se ingresa el
				// ID////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				JTextField textField_del_id = new JTextField();
				textField_del_id.setBackground(new Color(204, 204, 204));
				textField_del_id.setFont(new Font("Anton", Font.PLAIN, 14));
				textField_del_id.setBounds(160, 86, 86, 22);
				cliente_entrada.add(textField_del_id);
				textField_del_id.setColumns(10);
				// boton cancelar
				// alerta///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				JButton boton_cancelar_de_alerta = new JButton("Cancelar");
				boton_cancelar_de_alerta.setForeground(Color.WHITE);
				boton_cancelar_de_alerta.setBackground(Color.RED);
				boton_cancelar_de_alerta.setFont(new Font("Anton", Font.PLAIN, 14));
				boton_cancelar_de_alerta.setBounds(38, 136, 102, 33);
				cliente_entrada.add(boton_cancelar_de_alerta);
				boton_cancelar_de_alerta.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dialogoAlerta.dispose();
					}
				});
				// boton aceptar
				// alerta/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				JButton boton_aceptar_de_alerta = new JButton("Aceptar");
				boton_aceptar_de_alerta.setBackground(new Color(0, 206, 82));
				boton_aceptar_de_alerta.setForeground(Color.WHITE);
				boton_aceptar_de_alerta.setFont(new Font("Anton", Font.PLAIN, 14));
				boton_aceptar_de_alerta.setBounds(255, 136, 102, 33);
				cliente_entrada.add(boton_aceptar_de_alerta);
				boton_aceptar_de_alerta.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dialogoAlerta.dispose();// quitar cuando pongan funcionalidad
					}
				});

				dialogoAlerta.setVisible(true);
			}
		});
		boton_Añadir_entrada.setFont(new Font("Anton", Font.PLAIN, 20));
		boton_Añadir_entrada.setBackground(new Color(0, 206, 82));
		boton_Añadir_entrada.setBounds(724, 503, 184, 50);
		panel_2.add(boton_Añadir_entrada);

		JButton boton_descargar_clase = new JButton("Descargar");
		boton_descargar_clase.setFont(new Font("Anton", Font.PLAIN, 20));
		boton_descargar_clase.setBackground(new Color(255, 205, 17));
		boton_descargar_clase.setBounds(530, 503, 184, 50);
		panel_2.add(boton_descargar_clase);

		// botones
		// laterales/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
		boton_CHECADOR.setBackground(new Color(255, 255, 255));
		boton_CHECADOR.setFont(new Font("Anton", Font.PLAIN, 16));
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

//		JPanel ID_cliente_entrada = new JPanel();
//		ID_cliente_entrada.setBackground(new Color(255, 255, 255));
//		ID_cliente_entrada.setBounds(335, 234, 400, 180);
//		frame.getContentPane().add(ID_cliente_entrada);
//		ID_cliente_entrada.setLayout(null);
		//
//		JPanel panel_complemento= new JPanel();
//		panel_complemento.setBackground(new Color(81, 151, 255));
//		panel_complemento.setBounds(0, 0, 400, 33);
//		ID_cliente_entrada.add(panel_complemento);
		//
//		JLabel lblEntradaRegistradaCon = new JLabel("Entrada registrada con éxito");
//		lblEntradaRegistradaCon.setFont(new Font("Anton", Font.PLAIN, 20));
//		lblEntradaRegistradaCon.setBounds(93, 70, 264, 22);
//		ID_cliente_entrada.add(lblEntradaRegistradaCon);
		//
//		JButton boton_aceptar_de_alerta = new JButton("Aceptar");
//		boton_aceptar_de_alerta.setBackground(new Color(0, 206, 82));
//		boton_aceptar_de_alerta.setForeground(new Color(255, 255, 255));
//		boton_aceptar_de_alerta.setFont(new Font("Anton", Font.PLAIN, 14));
//		boton_aceptar_de_alerta.setBounds(145, 114, 102, 33);
//		ID_cliente_entrada.add(boton_aceptar_de_alerta);
		//
//		JPanel ID_cliente_entrada = new JPanel();
//		ID_cliente_entrada.setBackground(new Color(255, 255, 255));
//		ID_cliente_entrada.setBounds(335, 234, 400, 180);
//		frame.getContentPane().add(ID_cliente_entrada);
//		ID_cliente_entrada.setLayout(null);
//		
//		JPanel panel_complemento= new JPanel();
//		panel_complemento.setBackground(new Color(81, 151, 255));
//		panel_complemento.setBounds(0, 0, 400, 33);
//		ID_cliente_entrada.add(panel_complemento);
//		
//		JLabel lblEntradaRegistradaCon = new JLabel("Cliente no encontrado. Verifica que el ID sea correcto.");
//		lblEntradaRegistradaCon.setFont(new Font("Anton", Font.PLAIN, 16));
//		lblEntradaRegistradaCon.setBounds(24, 68, 386, 22);
//		ID_cliente_entrada.add(lblEntradaRegistradaCon);
//		
//		JButton boton_aceptar_de_alerta = new JButton("Aceptar");
//		boton_aceptar_de_alerta.setBackground(new Color(0, 206, 82));
//		boton_aceptar_de_alerta.setForeground(new Color(255, 255, 255));
//		boton_aceptar_de_alerta.setFont(new Font("Anton", Font.PLAIN, 14));
//		boton_aceptar_de_alerta.setBounds(145, 114, 102, 33);
//		ID_cliente_entrada.add(boton_aceptar_de_alerta);

	}

}
