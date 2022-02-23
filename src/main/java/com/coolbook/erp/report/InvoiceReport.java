package com.coolbook.erp.report;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.coolbook.erp.entity.CompanyEntity;
import com.coolbook.erp.rest.service.CompanyService;
import com.coolbook.erp.security.SecurityFacade;
import com.itextpdf.text.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itextpdf.text.pdf.PdfWriter;

@Component
public class InvoiceReport {
    
    @Autowired
    CompanyService companyService;

    @Autowired
    private SecurityFacade securityFacade;
    
	public ByteArrayInputStream generateInvoice() {
		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			PdfWriter.getInstance(document, out);
		} catch (DocumentException e1) {
			e1.printStackTrace();
		}
		document.open();
		Font font = FontFactory.getFont(FontFactory.COURIER, 8, BaseColor.BLACK);
		
        try {
            CompanyEntity company=companyService.getCompanyById(securityFacade.getCurrentUser().getCompanyId());
            Path path = Paths.get(ClassLoader.getSystemResource("Logo.png").toURI());
            Image img = Image.getInstance(path.toAbsolutePath().toString());
            document.add(img);
            document.add( Chunk.NEWLINE );
            document.add(new Paragraph(company.getCompanyName()+", "+company.getAddressLine1()+", " +
                    company.getAddressLine2()+", " +company.getAddressLine3()+", "+company.getContactNumber(), font));
           
		} catch (DocumentException | MalformedURLException | URISyntaxException | FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
            e.printStackTrace();
        }
        document.close();
		return new ByteArrayInputStream(out.toByteArray());
	}
	
	private void populateCompanyDetaila(){
	    
    }
}
