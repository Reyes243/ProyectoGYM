package controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfWriter;

import models.User;
import models.UsersModel;
import views.UsersView;

public class UsersController {

	private UsersView vista;
	private List<User> clientes = new ArrayList<>();

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
						new Phrase(datos.getOrDefault("primer_apellido", " "), fontDatosNormal), 																									
						marginLeft + 100, marginTop, 0);

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

	public void Editar_tarifas_PREMIUM() {
		vista.Editar_tarifas_PREMIUM();
	}

	public void Editar_tarifas_FAMILIAR() {
		vista.Editar_tarifas_FAMILIAR();
	}

	public void Editar_tarifas2() {
		vista.Editar_tarifas_2();
	}

	public void Añadir_tarifa() {
		vista.Añadir_tarifa();
	}

	public void Clientes_con_tarifa_FAMILIAR() {
		vista.Clientes_con_tarifa_FAMILIAR();
	}

	public void Clientes_con_tarifa_ESTANDAR() {
		vista.Clientes_con_tarifa_ESTANDAR();
	}

	public void Clientes_con_tarifa_PREMIUM() {
		vista.Clientes_con_tarifa_PREMIUM();
	}

	public void Credencial_usuario(int idcliente) {
		vista.Credencial_usuario(idcliente);
	}

	public void Ficha_de_instructor(int idinstructor) {
		vista.Ficha_de_instructor(idinstructor);
	}

	public void Historial_de_clase() {
		vista.Historial_de_clase();
	}

	public void Editar_instructor() {
		vista.Editar_instructor();
	}

	public void Credencial_instructor() {
		vista.Credencial_instructor();
	}

	public void Añadir_instructor() {
		vista.Añadir_instructor();

	}

	public void Registro_de_clase(int nombreclase) {
		vista.Registro_de_clase(nombreclase);
	}

	public void Editar_eliminar_y_añadir_clases() {
		vista.Editar_eliminar_y_añadir_clases();
	}

	public void Editar_clases(String nombreClase) {
		vista.Editar_clases(nombreClase);
	}

	public void Añadir_clases() {
		vista.Añadir_clases();

	}

}
