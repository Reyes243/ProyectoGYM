package controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfWriter;

import models.Clase;
import models.ClaseModel;
import models.ConectionModel;
import models.InstrucoresModel;
import models.Instructor;
import models.User;
import models.UsersModel;
import views.UsersView;

public class UsersController {

	private UsersView vista;
	private List<User> clientes = new ArrayList<>();
	private List<Instructor> instructores = new ArrayList<>();

	public UsersController() {

		vista = new UsersView();
	}

	public void Informacion_de_cliente(int idCliente) {
		vista.Informacion_de_cliente(idCliente);
	}

	public void generarPDFCliente(int idCliente) {
		UsersModel model = new UsersModel();
		Map<String, String> datos = model.obtenerDatosParaPDF(idCliente);

		// Pedir al usuario dónde guardar el PDF
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Guardar PDF");
		fileChooser.setSelectedFile(new File("cliente_" + idCliente + ".pdf"));

		if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			String rutaPDF = fileChooser.getSelectedFile().getAbsolutePath();

			Document documento = new Document();
			try {
				PdfWriter.getInstance(documento, new FileOutputStream(rutaPDF));
				documento.open();

				Paragraph titulo = new Paragraph("Información del Cliente",
						FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18));
				titulo.setAlignment(Element.ALIGN_CENTER);
				documento.add(titulo);

				try {
					Image logo = Image.getInstance(getClass().getResource("/Imagenes/logo ginmasio.png"));
					logo.scaleToFit(100, 100);
					logo.setAlignment(Element.ALIGN_CENTER);
					documento.add(logo);
				} catch (Exception e) {
					System.out.println("No se pudo cargar el logo: " + e.getMessage());
				}
				documento.add(new Paragraph("\n"));

				documento.add(new Paragraph("ID: " + datos.get("id")));
				documento.add(new Paragraph("Nombre completo: " + datos.get("nombre_completo")));
				documento.add(new Paragraph("Correo: " + datos.get("correo")));
				documento.add(new Paragraph("Teléfono: " + datos.get("telefono")));

				// Información de la tarifa
				documento.add(
						new Paragraph("\nInformación de la tarifa:", FontFactory.getFont(FontFactory.HELVETICA_BOLD)));

				documento.add(new Paragraph("Plan: " + datos.get("tarifa")));
				documento.add(new Paragraph("Precio: " + datos.get("precio_tarifa")));

				JOptionPane.showMessageDialog(null, "PDF generado exitosamente en:\n" + rutaPDF, "Éxito",
						JOptionPane.INFORMATION_MESSAGE);

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error al generar el PDF: " + e.getMessage(), "Error",
						JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			} finally {
				if (documento != null && documento.isOpen()) {
					documento.close();
				}
			}
		}
	}

	public void generarCredencialPDF(int idCliente) {
		UsersModel model = new UsersModel();
		Map<String, String> datos = model.obtenerDatosParaPDF(idCliente);

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Guardar Credencial");
		fileChooser.setSelectedFile(new File("credencial_" + idCliente + ".pdf"));

		if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			String rutaPDF = fileChooser.getSelectedFile().getAbsolutePath();

			Document documento = new Document(new Rectangle(400f, 250f));
			PdfWriter writer = null;

			try {
				writer = PdfWriter.getInstance(documento, new FileOutputStream(rutaPDF));
				documento.open();

				// Fuentes
				com.itextpdf.text.Font fontTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
				com.itextpdf.text.Font fontDatosBold = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
				com.itextpdf.text.Font fontDatosNormal = FontFactory.getFont(FontFactory.HELVETICA, 12);

				// Subimos el título más arriba (ajustado a 210f)
				ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER,
						new Phrase("CREDENCIAL", fontTitulo), documento.getPageSize().getWidth() / 2,
						documento.getPageSize().getHeight() - 30, 0);

				// Imagen del miembro
				try {
					Image foto = Image.getInstance(getClass().getResource("/Imagenes/GYMBRO.jpg"));
					foto.scaleToFit(120, 140);
					foto.setAbsolutePosition(40, documento.getPageSize().getHeight() - 180);
					documento.add(foto);
				} catch (Exception e) {
					System.out.println("No se pudo cargar la foto: " + e.getMessage());
				}

				// Más cerca de la imagen (ajustado a 150)
				float marginLeft = 150;
				float marginTop = documento.getPageSize().getHeight() - 60;

				ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT,
						new Phrase("Nombre:", fontDatosBold), marginLeft, marginTop, 0);
				ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT,
						new Phrase(datos.get("nombre"), fontDatosNormal), marginLeft + 60, marginTop, 0);

				marginTop -= 18;

				ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT,
						new Phrase("Primer apellido:", fontDatosBold), marginLeft, marginTop, 0);
				ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT,
						new Phrase(datos.getOrDefault("primer_apellido", " "), fontDatosNormal), marginLeft + 100,
						marginTop, 0);

				marginTop -= 18;

				ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT,
						new Phrase("Plan actual:", fontDatosBold), marginLeft, marginTop, 0);
				ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT,
						new Phrase(datos.getOrDefault("tarifa", "Ninguno"), fontDatosNormal), marginLeft + 90,
						marginTop, 0);

				marginTop -= 18;

				ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT,
						new Phrase("Vigencia hasta:", fontDatosBold), marginLeft, marginTop, 0);
				ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT,
						new Phrase("09/06/2025", fontDatosNormal), marginLeft + 100, marginTop, 0);

				marginTop -= 18;

				ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT,
						new Phrase("No. Identificador:", fontDatosBold), marginLeft, marginTop, 0);
				ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT,
						new Phrase(datos.get("id"), fontDatosNormal), marginLeft + 110, marginTop, 0);

				// Logo en la esquina inferior derecha
				try {
					Image logo = Image.getInstance(getClass().getResource("/Imagenes/logo ginmasio.png"));
					logo.scaleToFit(50, 50);
					logo.setAbsolutePosition(documento.getPageSize().getWidth() - 60, 20);
					documento.add(logo);
				} catch (Exception e) {
					System.out.println("No se pudo cargar el logo: " + e.getMessage());
				}

				JOptionPane.showMessageDialog(null, "Credencial generada exitosamente en:\n" + rutaPDF, "Éxito",
						JOptionPane.INFORMATION_MESSAGE);

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error al generar la credencial: " + e.getMessage(), "Error",
						JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			} finally {
				if (documento != null && documento.isOpen()) {
					documento.close();
				}
			}
		}
	}

	public void Edicion_de_informacion_de_cliente(int idcliente) {
		vista.Edicion_de_informacion_de_cliente(idcliente);
	}

	public void Historial_de_pagos(int idcliente) {
		vista.Historial_de_pagos(idcliente);
	}

	public void HIstorial_de_asistencias(int idcliente) {
		vista.HIstorial_de_asistencias(idcliente);
	}

	public void Añadir_cliente() {
		vista.Añadir_cliente();
	}

	public void Editar_tarifas() {
		vista.Editar_tarifas();
	}

	public void Editar_tarifas2() {
		vista.Editar_tarifas_2();
	}

	public void Añadir_tarifa() {
		vista.Añadir_tarifa();
	}

	public void Clientes_con_tarifa_ESTANDAR(String nombreTarifa) {
		vista.Clientes_con_tarifa_ESTANDAR(nombreTarifa);
	}

	public void Credencial_usuario(int idcliente) {
		vista.Credencial_usuario(idcliente);
	}

	public void Ficha_de_instructor(int idinstructor) {
		vista.Ficha_de_instructor(idinstructor);
	}

	public void Historial_de_clase(int idinstructor) {
		vista.Historial_de_clase(idinstructor);
	}

	public void Editar_instructor(int idinstructor) {
		vista.Editar_instructor(idinstructor);
	}

	public void Credencial_instructor(int idinstructor) {
		vista.Credencial_instructor(idinstructor);
	}

	public void Añadir_instructor() {
		vista.Añadir_instructor();

	}

	public void Registro_de_clase(int nombreclase) {
		vista.Registro_de_clase(nombreclase);
	}

	public void Editar_eliminar_y_añadir_clases(int idclase) {
		vista.Editar_eliminar_y_añadir_clases(idclase);
	}

	public void Editar_clases(int idclase) {
		vista.Editar_clases(idclase);
	}

	public void Añadir_clases() {
		vista.Añadir_clases();

	}

	public void generarCredencialPDFInstructor(int idInstructor) {
		InstrucoresModel model = new InstrucoresModel();
		Map<String, String> datos = model.obtenerDatosParaPDFInstructor(idInstructor);

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Guardar Credencial de Instructor");
		fileChooser.setSelectedFile(new File("credencial_instructor_" + idInstructor + ".pdf"));

		if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			String rutaPDF = fileChooser.getSelectedFile().getAbsolutePath();

			Document documento = new Document(new Rectangle(400f, 250f));
			PdfWriter writer = null;

			try {
				writer = PdfWriter.getInstance(documento, new FileOutputStream(rutaPDF));
				documento.open();

				// Fuentes
				com.itextpdf.text.Font fontTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
				com.itextpdf.text.Font fontDatosBold = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
				com.itextpdf.text.Font fontDatosNormal = FontFactory.getFont(FontFactory.HELVETICA, 12);

				// Título
				ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER,
						new Phrase("CREDENCIAL DE INSTRUCTOR", fontTitulo), documento.getPageSize().getWidth() / 2,
						documento.getPageSize().getHeight() - 30, 0);

				// Imagen del instructor
				try {
					Image foto = Image.getInstance(getClass().getResource("/Imagenes/VADUROTISH.jpg"));
					foto.scaleToFit(120, 140);
					foto.setAbsolutePosition(40, documento.getPageSize().getHeight() - 180);
					documento.add(foto);
				} catch (Exception e) {
					System.out.println("No se pudo cargar la foto: " + e.getMessage());
				}

				// Posición inicial para los datos
				float marginLeft = 170;
				float marginTop = documento.getPageSize().getHeight() - 90;

				// Nombre
				ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT,
						new Phrase("Nombre:", fontDatosBold), marginLeft, marginTop, 0);
				ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT,
						new Phrase(datos.get("nombre"), fontDatosNormal), marginLeft + 60, marginTop, 0);

				marginTop -= 18;

				// Especialidad
				ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT,
						new Phrase("Especialidad:", fontDatosBold), marginLeft, marginTop, 0);
				ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT,
						new Phrase(datos.getOrDefault("especialidad", "No especificada"), fontDatosNormal),
						marginLeft + 90, marginTop, 0);

				marginTop -= 18;

				// Clase asignada
				ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT,
						new Phrase("Clase asignada:", fontDatosBold), marginLeft, marginTop, 0);
				ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT,
						new Phrase(datos.getOrDefault("clase_asignada", "Ninguna"), fontDatosNormal), marginLeft + 100,
						marginTop, 0);

				marginTop -= 18;

				// Horarios
				ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT,
						new Phrase("Horarios:", fontDatosBold), marginLeft, marginTop, 0);

				// Manejo de horarios con múltiples líneas
				String horarios = datos.getOrDefault("horarios", "Sin horarios asignados");
				String[] lineasHorarios = horarios.split("\n");

				for (String linea : lineasHorarios) {
					ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT,
							new Phrase(linea, fontDatosNormal), marginLeft + 70, marginTop, 0);
					marginTop -= 15;
				}

				marginTop -= 10;

				// Número de identificación
				ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT,
						new Phrase("ID Instructor:", fontDatosBold), marginLeft, marginTop, 0);
				ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT,
						new Phrase(datos.get("id"), fontDatosNormal), marginLeft + 90, marginTop, 0);

				// Logo del gimnasio
				try {
					Image logo = Image.getInstance(getClass().getResource("/Imagenes/logo ginmasio.png"));
					logo.scaleToFit(50, 50);
					logo.setAbsolutePosition(documento.getPageSize().getWidth() - 60, 20);
					documento.add(logo);
				} catch (Exception e) {
					System.out.println("No se pudo cargar el logo: " + e.getMessage());
				}

				JOptionPane.showMessageDialog(null, "Credencial de instructor generada exitosamente en:\n" + rutaPDF,
						"Éxito", JOptionPane.INFORMATION_MESSAGE);

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error al generar la credencial: " + e.getMessage(), "Error",
						JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			} finally {
				if (documento != null && documento.isOpen()) {
					documento.close();
				}
			}
		}
	}

	public void generarPDFRegistroClase(int idClase, List<Object[]> instructorData, DefaultTableModel alumnosModel) {
		// Pedir al usuario dónde guardar el PDF
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Guardar Registro de Clase");
		fileChooser.setSelectedFile(new File("registro_clase_" + idClase + ".pdf"));

		if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			String rutaPDF = fileChooser.getSelectedFile().getAbsolutePath();

			Document documento = new Document();
			PdfWriter writer = null;
			try {
				writer = PdfWriter.getInstance(documento, new FileOutputStream(rutaPDF));
				documento.open();

				// Fuentes
				com.itextpdf.text.Font fontTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
				com.itextpdf.text.Font fontSubtitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14);
				com.itextpdf.text.Font fontNormal = FontFactory.getFont(FontFactory.HELVETICA, 12);
				com.itextpdf.text.Font fontCabecera = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);

				// Obtener nombre de la clase
				ClaseModel claseModel = new ClaseModel(new ConectionModel().getConnection());
				Clase clase = claseModel.obtenerClasePorId(idClase);
				String nombreClase = (clase != null) ? clase.getNombreClase() : "Clase no encontrada";

				// Título
				Paragraph titulo = new Paragraph("REGISTRO: " + nombreClase.toUpperCase(), fontTitulo);
				titulo.setAlignment(Element.ALIGN_CENTER);
				documento.add(titulo);
				documento.add(new Paragraph("\n"));

				// Información del instructor
				if (!instructorData.isEmpty()) {
					Object[] instructor = instructorData.get(0);
					Paragraph subtituloInstructor = new Paragraph("INFORMACIÓN DEL INSTRUCTOR", fontSubtitulo);
					documento.add(subtituloInstructor);

					// Crear tabla para instructor
					com.itextpdf.text.pdf.PdfPTable tablaInstructor = new com.itextpdf.text.pdf.PdfPTable(4);
					tablaInstructor.setWidthPercentage(100);

					// Cabeceras
					tablaInstructor.addCell(new Phrase("Entrenador", fontCabecera));
					tablaInstructor.addCell(new Phrase("Correo", fontCabecera));
					tablaInstructor.addCell(new Phrase("Turno", fontCabecera));
					tablaInstructor.addCell(new Phrase("Horario", fontCabecera));

					// Datos
					tablaInstructor.addCell(new Phrase(instructor[0].toString(), fontNormal));
					tablaInstructor.addCell(new Phrase(instructor[1].toString(), fontNormal));
					tablaInstructor.addCell(new Phrase(instructor[2].toString(), fontNormal));
					tablaInstructor.addCell(new Phrase(instructor[3].toString(), fontNormal));

					documento.add(tablaInstructor);
					documento.add(new Paragraph("\n"));
				}

				// Lista de alumnos
				Paragraph subtituloAlumnos = new Paragraph("ALUMNOS INSCRITOS", fontSubtitulo);
				documento.add(subtituloAlumnos);

				// Crear tabla para alumnos (6 columnas sin la columna Eliminar)
				com.itextpdf.text.pdf.PdfPTable tablaAlumnos = new com.itextpdf.text.pdf.PdfPTable(6);
				tablaAlumnos.setWidthPercentage(100);

				// Cabeceras
				tablaAlumnos.addCell(new Phrase("ID cliente", fontCabecera));
				tablaAlumnos.addCell(new Phrase("Nombre", fontCabecera));
				tablaAlumnos.addCell(new Phrase("Primer apellido", fontCabecera));
				tablaAlumnos.addCell(new Phrase("Segundo apellido", fontCabecera));
				tablaAlumnos.addCell(new Phrase("Teléfono", fontCabecera));
				tablaAlumnos.addCell(new Phrase("Correo electrónico", fontCabecera));

				// Datos de alumnos (omitimos la última columna que es "Eliminar")
				for (int i = 0; i < alumnosModel.getRowCount(); i++) {
					for (int j = 0; j < alumnosModel.getColumnCount() - 1; j++) {
						Object value = alumnosModel.getValueAt(i, j);
						tablaAlumnos.addCell(new Phrase(value != null ? value.toString() : "", fontNormal));
					}
				}

				documento.add(tablaAlumnos);

				// Agregar el logo en la esquina inferior derecha
				try {
					Image logo = Image.getInstance(getClass().getResource("/Imagenes/logo ginmasio.png"));
					logo.scaleToFit(80, 80); // Tamaño del logo (ajustar según necesidad)

					// Posicionamiento absoluto en la esquina inferior derecha
					float x = documento.getPageSize().getWidth() - logo.getScaledWidth() - 36; // 36 = ~0.5 pulgadas de
																								// margen
					float y = logo.getScaledHeight() + 5;
					logo.setAbsolutePosition(x, y);

					documento.add(logo);
				} catch (Exception e) {
					System.out.println("No se pudo cargar el logo: " + e.getMessage());
				}

				JOptionPane.showMessageDialog(null, "PDF generado exitosamente en:\n" + rutaPDF, "Éxito",
						JOptionPane.INFORMATION_MESSAGE);

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error al generar el PDF: " + e.getMessage(), "Error",
						JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			} finally {
				if (documento != null && documento.isOpen()) {
					documento.close();
				}
			}
		}
	}

}
