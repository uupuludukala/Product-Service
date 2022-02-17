package com.coolbook.erp.report;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.springframework.stereotype.Component;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.PdfWriter;

@Component
public class InvoiceReport {

	public ByteArrayInputStream generateInvoice() {
		Document document = new Document();

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			PdfWriter.getInstance(document, out);
		} catch (DocumentException e1) {
			e1.printStackTrace();
		}

		document.open();
		Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
		Chunk chunk = new Chunk("Hello World", font);

		try {
			document.add(chunk);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		document.close();
		return new ByteArrayInputStream(out.toByteArray());
	}
}
