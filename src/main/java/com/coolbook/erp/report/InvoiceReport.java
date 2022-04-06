package com.coolbook.erp.report;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

import com.coolbook.erp.entity.*;
import com.coolbook.erp.repository.InvoiceRepository;
import com.coolbook.erp.rest.service.CompanyService;
import com.coolbook.erp.security.SecurityFacade;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itextpdf.text.pdf.PdfWriter;

@Component
public class InvoiceReport {
    
    @Autowired
    CompanyService companyService;
    
    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private SecurityFacade securityFacade;

    Font normalFont = FontFactory.getFont(FontFactory.COURIER, 10, BaseColor.BLACK);

    Font nameFont = FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD);
    
	public ByteArrayInputStream generateInvoice(long id) {
        InvoiceEntity invoiceEntity = invoiceRepository.findOne(id);
		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			PdfWriter.getInstance(document, out);
		} catch (DocumentException e1) {
			e1.printStackTrace();
		}
		document.open();
        try {
//            Path path = Paths.get(ClassLoader.getSystemResource("Logo.png").toURI());
//            Image img = Image.getInstance(path.toAbsolutePath().toString());
//            img.setAlignment(Element.ALIGN_RIGHT);
//            img.setIndentationRight(10.00F);
//            document.add(img);
            document.add( Chunk.NEWLINE );
            populateCompanyDetails(document);
            populateHeader(document,invoiceEntity.getCustomer(),invoiceEntity);
            document.add( Chunk.NEWLINE );
            populateInvoiceItems(document,invoiceEntity.getProducts());
//            populateHeader(document,invoiceEntity.getProducts());
		} catch (DocumentException  e) {
			e.printStackTrace();
		} 
        document.close();
		return new ByteArrayInputStream(out.toByteArray());
	}
	
	private void populateHeader(Document document,CustomerEntity customer,InvoiceEntity invoice) throws DocumentException {
        float [] pointColumnWidths = {500F, 500F};
        PdfPTable table = new PdfPTable(pointColumnWidths);
        PdfPCell customerCell=new PdfPCell(populateCustomerDetails(customer));
        customerCell.setBorder(Rectangle.NO_BORDER);
        PdfPCell invoiceCell=new PdfPCell(populateInvoiceDetails(invoice));
        invoiceCell.setBorder(Rectangle.NO_BORDER);
        table.addCell(customerCell);
        table.addCell(invoiceCell);
        document.add(table);
    }
    
    private Paragraph populateInvoiceDetails(InvoiceEntity invoice){
        Phrase invoicePhrase=new Phrase();
        CompanyEntity company = companyService.getCompanyById(securityFacade.getCurrentUser().getCompanyId());
        invoicePhrase.add(new Chunk("Invoice No : "+invoice.getInvoiceNumber(),nameFont));
        invoicePhrase.add(Chunk.NEWLINE);
        invoicePhrase.add(new Chunk("Date : "+invoice.getDate(),normalFont));
        invoicePhrase.add(Chunk.NEWLINE);
        invoicePhrase.add(new Chunk("Operator : "+invoice.getUser().getUserName(),normalFont));
        invoicePhrase.add(Chunk.NEWLINE);
        Paragraph invoiceParagraph=new Paragraph(invoicePhrase);
        invoiceParagraph.setAlignment(Element.ALIGN_RIGHT);
        return new Paragraph(invoicePhrase);
    }
	private void populateCompanyDetails(Document document) throws DocumentException {
        float [] pointColumnWidths = {250F};
        PdfPTable table = new PdfPTable(pointColumnWidths);
        Phrase companyPhrase=new Phrase();
        CompanyEntity company = companyService.getCompanyById(securityFacade.getCurrentUser().getCompanyId());
        companyPhrase.add(new Chunk(company.getCompanyName(),nameFont));
        companyPhrase.add(Chunk.NEWLINE);
        companyPhrase.add(new Chunk(company.getAddressLine1(),normalFont));
        companyPhrase.add(Chunk.NEWLINE);
        companyPhrase.add(new Chunk(company.getAddressLine2(),normalFont));
        companyPhrase.add(Chunk.NEWLINE);
        companyPhrase.add(new Chunk(company.getAddressLine3(),normalFont));
        companyPhrase.add(Chunk.NEWLINE);
        companyPhrase.add(new Chunk(company.getContactNumber(),normalFont));
        Paragraph companyParagraph=new Paragraph(companyPhrase);
        companyParagraph.setAlignment(Element.ALIGN_RIGHT);
        
        PdfPCell companyCell=new PdfPCell(companyParagraph);
        companyCell.setBorder(Rectangle.NO_BORDER);
        table.addCell(companyCell);
        document.add(table);
    }

    private Paragraph populateCustomerDetails( CustomerEntity customer) throws DocumentException {
        Phrase customerPhrase=new Phrase();
        customerPhrase.add(new Chunk("Customer : ",nameFont));
        customerPhrase.add(Chunk.NEWLINE);
        customerPhrase.add(new Chunk(customer.getCustomerName(),nameFont));
        customerPhrase.add(Chunk.NEWLINE);
        customerPhrase.add(new Chunk(customer.getAddressLine1(),normalFont));
        customerPhrase.add(Chunk.NEWLINE);
        customerPhrase.add(new Chunk(customer.getAddressLine2(),normalFont));
        customerPhrase.add(Chunk.NEWLINE);
        customerPhrase.add(new Chunk(customer.getAddressLine3(),normalFont));
        customerPhrase.add(Chunk.NEWLINE);
        customerPhrase.add(new Chunk(customer.getHomePhone(),normalFont));
        customerPhrase.add(Chunk.NEWLINE);
        customerPhrase.add(new Chunk(customer.getMobileNumber(),normalFont));
        Paragraph customerParagraph=new Paragraph(customerPhrase);
        customerParagraph.setAlignment(Element.ALIGN_LEFT);
        return customerParagraph;        
    }
    
    private void populateInvoiceItems(Document document, Set<InvoiceProductEntity> products) throws DocumentException {
        float [] pointColumnWidths = {100F, 300F,150F, 150F, 150F,150F};
        PdfPTable table = new PdfPTable(pointColumnWidths);
        PdfPCell itemNoHeaderCell=new PdfPCell(new Phrase(new Chunk("Item No",normalFont)));
        itemNoHeaderCell.setBorder(Rectangle.NO_BORDER);
        table.addCell(itemNoHeaderCell);
        PdfPCell descriptionHeaderCell=new PdfPCell(new Phrase(new Chunk("Description",normalFont)));
        descriptionHeaderCell.setBorder(Rectangle.NO_BORDER);
        table.addCell(descriptionHeaderCell);
        PdfPCell pquantityHeaderCell=new PdfPCell(new Phrase(new Chunk("Qty",normalFont)));
        pquantityHeaderCell.setBorder(Rectangle.NO_BORDER);
        table.addCell(pquantityHeaderCell);
        PdfPCell rateHeaderCell=new PdfPCell(new Phrase(new Chunk("Rate",normalFont)));
        rateHeaderCell.setBorder(Rectangle.NO_BORDER);
        table.addCell(rateHeaderCell);
        PdfPCell discountHeaderCell=new PdfPCell(new Phrase(new Chunk("Dis%",normalFont)));
        discountHeaderCell.setBorder(Rectangle.NO_BORDER);
        table.addCell(discountHeaderCell);
        PdfPCell amountHeaderCell=new PdfPCell(new Phrase(new Chunk("Amount(Rs)",normalFont)));
        amountHeaderCell.setBorder(Rectangle.NO_BORDER);
        table.addCell(amountHeaderCell);
        for(InvoiceProductEntity product:products){
            PdfPCell productCodeCell=new PdfPCell(new Phrase(new Chunk(product.getProduct().getProductCode(),normalFont)));
            productCodeCell.setBorder(Rectangle.NO_BORDER);
            table.addCell(productCodeCell);
            PdfPCell productNameCell=new PdfPCell(new Phrase(new Chunk(product.getProduct().getProductName(),normalFont)));
            productNameCell.setBorder(Rectangle.NO_BORDER);
            table.addCell(productNameCell);
            PdfPCell quantityCell=new PdfPCell(new Phrase(new Chunk(String.valueOf(product.getQuantity()),normalFont)));
            quantityCell.setBorder(Rectangle.NO_BORDER);
            table.addCell(quantityCell);
            PdfPCell unitPriceCell=new PdfPCell(new Phrase(new Chunk(String.valueOf(product.getUnitPrice()),normalFont)));
            unitPriceCell.setBorder(Rectangle.NO_BORDER);            
            table.addCell(unitPriceCell);
            PdfPCell discountCell=new PdfPCell(new Phrase(new Chunk(String.valueOf(product.getDiscount()),normalFont)));
            discountCell.setBorder(Rectangle.NO_BORDER);
            table.addCell(discountCell);
            PdfPCell amountCell=new PdfPCell(new Phrase(new Chunk(String.valueOf(product.getAmount()),normalFont)));
            amountCell.setBorder(Rectangle.NO_BORDER);
            table.addCell(amountCell);
        }
        
        document.add(table);
    }
}

