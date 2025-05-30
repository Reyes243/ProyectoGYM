package Vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import com.formdev.flatlaf.FlatLightLaf;

import Vistas.Clases.ButtonEditor3;
import Vistas.Clases.ButtonRenderer3;

public class Editar_eliminar_y_añadir_clases {

	private JFrame frame;
	private JTable table;
	private DefaultTableModel model;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Editar_eliminar_y_añadir_clases window = new Editar_eliminar_y_añadir_clases();
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
	public Editar_eliminar_y_añadir_clases() {
		try {
			UIManager.setLookAndFeel(new FlatLightLaf());
			UIManager.put("Button.arc", 8);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		initialize();
	}

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
		ImageIcon portada1 = new ImageIcon("Imagenes/logo sin letras.png");
		Image portada2 = portada1.getImage().getScaledInstance(53, 53, Image.SCALE_SMOOTH);
		lblNewLabel_1.setIcon(new ImageIcon(portada2));
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
		Object[][] data = {
			{"YOGA RELAX", "Laura Mendez", "VESPERTINO", "Lunes y Viernes ", "", ""}
		};

		String[] columnNames = {"Nombre de la clase", "Entrenador", "Turno", "Horario", "Editar", "Eliminar"};

		model = new DefaultTableModel(data, columnNames) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return column == 4 || column == 5;
			}
		};

		table = new JTable(model);
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
		table.getColumn("Editar").setCellRenderer(new ButtonRenderer3("Editar"));
		table.getColumn("Editar").setCellEditor(new ButtonEditor3(new JCheckBox(), "Editar", table));

		table.getColumn("Eliminar").setCellRenderer(new ButtonRenderer3("Eliminar"));
		table.getColumn("Eliminar").setCellEditor(new ButtonEditor3(new JCheckBox(), "Eliminar", table));

		// Botón Añadir cliente 
		JButton boton_Añadir_clase= new JButton("Añadir clase");
		boton_Añadir_clase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		panel_2.add(boton_cancelar);
		

		// Botones laterales de la ventana
		JButton boton_INICIO= new JButton("INICIO");
		boton_INICIO.setBackground(new Color(255, 205, 17));
		boton_INICIO.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_INICIO.setBounds(10, 86, 136, 71);
		panel.add(boton_INICIO);

		JButton boton_CLIENTES = new JButton("CLIENTES");
		boton_CLIENTES.setBackground(new Color(255, 205, 17));
		boton_CLIENTES.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CLIENTES.setBounds(10, 168, 136, 71);
		panel.add(boton_CLIENTES);

		JButton boton_TARIFAS = new JButton("TARIFAS");
		boton_TARIFAS.setBackground(new Color(255, 205, 17));
		boton_TARIFAS.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_TARIFAS.setBounds(10, 250, 136, 71);
		panel.add(boton_TARIFAS);

		JButton boton_INSTRUCTORES = new JButton("INSTRUCTORES");
		boton_INSTRUCTORES.setBackground(new Color(255, 205, 17));
		boton_INSTRUCTORES.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_INSTRUCTORES.setBounds(10, 332, 136, 71);
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
		panel.add(boton_CHECADOR);

		JButton boton_CERRAR_SESION = new JButton("CERRAR SESION");
		boton_CERRAR_SESION.setBackground(new Color(255, 205, 17));
		boton_CERRAR_SESION.setFont(new Font("Anton", Font.PLAIN, 16));
		boton_CERRAR_SESION.setBounds(10, 579, 136, 71);
		panel.add(boton_CERRAR_SESION);
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
	        if (label.equals("Editar")) {
	            setIcon(loadIcon("Imagenes/editar.png"));
	            setBackground(new Color(255, 205, 17)); 
	        } else if (label.equals("Eliminar")) {
	            setIcon(loadIcon("Imagenes/eliminar.png"));
	            setBackground(new Color(255, 0, 0)); 
	        }
	        setText(null);  
	    }

	    private ImageIcon loadIcon(String path) {
	        ImageIcon icon = new ImageIcon(path);
	        Image img = icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
	        return new ImageIcon(img);
	    }

	    @Override
	    public Component getTableCellRendererComponent(JTable table, Object value,
	            boolean isSelected, boolean hasFocus, int row, int column) {
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
	        if (label.equals("Editar")) {
	            button.setIcon(loadIcon("Imagenes/editar.png"));
	        } else if (label.equals("Eliminar")) {
	            button.setIcon(loadIcon("Imagenes/eliminar.png"));
	        }
	        button.setText(null);  // Sin texto

	        button.addActionListener(e -> fireEditingStopped());
	    }

	    private ImageIcon loadIcon(String path) {
	        ImageIcon icon = new ImageIcon(path);
	        Image img = icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
	        return new ImageIcon(img);
	    }

	    @Override
	    public Component getTableCellEditorComponent(JTable table, Object value,
	            boolean isSelected, int row, int column) {
	        this.row = row;
	        clicked = true;
	        return button;
	    }

	    @Override
	    public Object getCellEditorValue() {
		    if (clicked) {
		        if (label.equals("Eliminar")) {
		            // Aquí conecta con la base de datos para borrar el registro según el ID de la fila seleccionada
		            // y luego actualiza la tabla recargando datos.
		        } else if (label.equals("Editar")) {
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
}

