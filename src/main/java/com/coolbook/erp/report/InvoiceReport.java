package com.coolbook.erp.report;

import java.io.*;
import java.text.DecimalFormat;
import java.util.Set;

import com.coolbook.erp.entity.*;
import com.coolbook.erp.repository.InvoiceRepository;
import com.coolbook.erp.rest.service.CompanyService;
import com.coolbook.erp.rest.service.UserService;
import com.coolbook.erp.security.SecurityFacade;
import com.coolbook.erp.util.NumberToWordConverterUtil;
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
    private UserService userService;
    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private SecurityFacade securityFacade;

    Font normalFont = FontFactory.getFont(FontFactory.HELVETICA, 8, BaseColor.BLACK);

    Font nameFont = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);

    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.00");
    
    
    int totalItems;
    
	public ByteArrayInputStream generateInvoice(long id) {
        InvoiceEntity invoiceEntity = invoiceRepository.findOne(id);
		Document document = new Document(PageSize.A5.rotate(),15,15,15,15);
        
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
            PdfWriter writer=PdfWriter.getInstance(document, out);
            document.open();
            populateHeader(document,invoiceEntity.getCustomer(),invoiceEntity);           
            populateInvoiceItems(document,invoiceEntity.getProducts());
            populateInvoiceFooter(document,invoiceEntity,writer);
            document.close();
		} catch (DocumentException  e) {
			e.printStackTrace();
		} 
       
		return new ByteArrayInputStream(out.toByteArray());
	}
	
	private void populateHeader(Document document,CustomerEntity customer,InvoiceEntity invoice) throws DocumentException {
        float [] pointColumnWidths = {1500F, 1500F};
        PdfPTable table = new PdfPTable(pointColumnWidths);
        table.setWidthPercentage(100);
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        PdfPCell customerCell=new PdfPCell(populateCustomerDetails(customer));
        customerCell.setBorder(Rectangle.NO_BORDER);
        PdfPCell companyCell=new PdfPCell(populateCompanyDetails());
        companyCell.setBorder(Rectangle.NO_BORDER);
        PdfPCell invoiceCell=new PdfPCell(populateInvoiceDetails(invoice));
        invoiceCell.setBorder(Rectangle.NO_BORDER);
        invoiceCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.addCell(companyCell);
        table.addCell(invoiceCell);

        PdfPTable customerTable = new PdfPTable(1);
        customerTable.setWidthPercentage(100);
        customerTable.setHorizontalAlignment(Element.ALIGN_LEFT);
        customerTable.addCell(customerCell);
        document.add(table);
        document.add(customerTable);
    }
    
    private Paragraph populateInvoiceDetails(InvoiceEntity invoice){
        Phrase invoicePhrase=new Phrase();
        invoicePhrase.add(new Chunk("Invoice No : "+invoice.getInvoiceNumber(),normalFont));
        invoicePhrase.add(Chunk.NEWLINE);
        invoicePhrase.add(new Chunk("Date : "+invoice.getDate(),normalFont));
        invoicePhrase.add(Chunk.NEWLINE);
        invoicePhrase.add(new Chunk("Operator : "+userService.getUserById(invoice.getUserId()).getUserName(),normalFont));
        invoicePhrase.add(Chunk.NEWLINE);
        Paragraph invoiceParagraph=new Paragraph(invoicePhrase);
        invoiceParagraph.setAlignment(Element.ALIGN_RIGHT);
        return new Paragraph(invoicePhrase);
    }
	private Paragraph populateCompanyDetails() throws DocumentException {
        Phrase companyPhrase=new Phrase();
        CompanyEntity company = companyService.getCompanyById(securityFacade.getCurrentUser().getCompanyId());
        companyPhrase.add(new Chunk(company.getCompanyName().toUpperCase(),nameFont));
        companyPhrase.add(Chunk.NEWLINE);
        companyPhrase.add(new Chunk(company.getAddressLine1()+","+company.getAddressLine2()+","+company.getAddressLine3(),normalFont));
        companyPhrase.add(Chunk.NEWLINE);
        companyPhrase.add(new Chunk("Tel: "+company.getContactNumber(),normalFont));
        Paragraph companyParagraph=new Paragraph(companyPhrase);
        companyParagraph.setAlignment(Element.ALIGN_LEFT);
        return companyParagraph;
    }

    private Paragraph populateCustomerDetails( CustomerEntity customer) throws DocumentException {
        Phrase customerPhrase=new Phrase();
        customerPhrase.add(new Chunk("Customer : ",normalFont));
        customerPhrase.add(new Chunk(customer.getCustomerName(),normalFont));
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
        customerPhrase.add(Chunk.NEWLINE);
        customerPhrase.add(Chunk.NEWLINE);
        Paragraph customerParagraph=new Paragraph(customerPhrase);
        customerParagraph.setAlignment(Element.ALIGN_LEFT);
        return customerParagraph;        
    }
    
    private void populateInvoiceItems(Document document, Set<InvoiceProductEntity> products) throws DocumentException {
        float [] pointColumnWidths = {100F, 500F,100F, 100F, 100F,100F};
        PdfPTable table = new PdfPTable(pointColumnWidths);
        table.setWidthPercentage(100);
        PdfPCell itemNoHeaderCell=new PdfPCell(new Phrase(new Chunk("Item",normalFont)));
        itemNoHeaderCell.setBorder(Rectangle.BOX);
        table.addCell(itemNoHeaderCell);
        PdfPCell descriptionHeaderCell=new PdfPCell(new Phrase(new Chunk("Description",normalFont)));
        descriptionHeaderCell.setBorder(Rectangle.BOX);
        table.addCell(descriptionHeaderCell);
        PdfPCell pquantityHeaderCell=new PdfPCell(new Phrase(new Chunk("Qty",normalFont)));
        pquantityHeaderCell.setBorder(Rectangle.BOX);
        table.addCell(pquantityHeaderCell);
        PdfPCell rateHeaderCell=new PdfPCell(new Phrase(new Chunk("Rate",normalFont)));
        rateHeaderCell.setBorder(Rectangle.BOX);
        table.addCell(rateHeaderCell);
        PdfPCell discountHeaderCell=new PdfPCell(new Phrase(new Chunk("Dis%",normalFont)));
        discountHeaderCell.setBorder(Rectangle.BOX);
        table.addCell(discountHeaderCell);
        PdfPCell amountHeaderCell=new PdfPCell(new Phrase(new Chunk("Amount(Rs)",normalFont)));
        amountHeaderCell.setBorder(Rectangle.BOX);
        table.addCell(amountHeaderCell);
        totalItems=0;
        for(InvoiceProductEntity product:products){
            PdfPCell productCodeCell=new PdfPCell(new Phrase(new Chunk(product.getProduct().getProductCode(),normalFont)));
            productCodeCell.setBorder(Rectangle.NO_BORDER);
            table.addCell(productCodeCell);
            PdfPCell productNameCell=new PdfPCell(new Phrase(new Chunk(product.getDescription(),normalFont)));
            productNameCell.setBorder(Rectangle.NO_BORDER);
            table.addCell(productNameCell);
            PdfPCell quantityCell=new PdfPCell(new Phrase(new Chunk(String.valueOf(product.getQuantity()),normalFont)));
            quantityCell.setBorder(Rectangle.NO_BORDER);
            table.addCell(quantityCell);
            PdfPCell unitPriceCell=new PdfPCell(new Phrase(new Chunk(String.valueOf(DECIMAL_FORMAT.format(product.getRate())),normalFont)));
            unitPriceCell.setBorder(Rectangle.NO_BORDER);            
            table.addCell(unitPriceCell);
            PdfPCell discountCell=new PdfPCell(new Phrase(new Chunk(String.valueOf(DECIMAL_FORMAT.format(product.getDiscount())),normalFont)));
            discountCell.setBorder(Rectangle.NO_BORDER);
            table.addCell(discountCell);
            PdfPCell amountCell=new PdfPCell(new Phrase(new Chunk(String.valueOf(DECIMAL_FORMAT.format(product.getAmount())),normalFont)));
            amountCell.setBorder(Rectangle.NO_BORDER);
            table.addCell(amountCell);
            totalItems+=product.getQuantity();
        }        
        document.add(table);
    }

    private void populateInvoiceFooter(Document document,InvoiceEntity invoice, PdfWriter writer) throws DocumentException {
        PdfPTable table = new PdfPTable(1);
        table.setHorizontalAlignment(Element.ALIGN_RIGHT);
        Phrase totalPhrase=new Phrase();
        Phrase footerDescriptionPhrase=new Phrase();
        double total=invoice.getTotal();
        totalPhrase.add(new Chunk("Total : " + DECIMAL_FORMAT.format(total),nameFont));
        footerDescriptionPhrase.add(new Chunk(" \n\n" +
                "Amount in Words   : " + NumberToWordConverterUtil.numberToWord((int)total) + " and " 
                +Integer.parseInt( String.valueOf(DECIMAL_FORMAT.format(total)).substring(String.valueOf(DECIMAL_FORMAT.format(total)).indexOf(".")+1)) + "/100 Only \n" ,normalFont));
        footerDescriptionPhrase.add(new Chunk("Number of items   : " + totalItems +"\n\n\n\n",normalFont));
        footerDescriptionPhrase.add(new Chunk(" ---------------------------\n",normalFont));
        footerDescriptionPhrase.add(new Chunk(" Authorized Signature \n",normalFont));
        
        Paragraph totalParagraph=new Paragraph(totalPhrase);
        Paragraph footerDescriptionParagraph=new Paragraph(footerDescriptionPhrase);
        table.setWidthPercentage(100);
        PdfPCell totalCell=new PdfPCell(totalParagraph);
        totalCell.setBorder(Rectangle.NO_BORDER);
        totalCell.setHorizontalAlignment(Element.ALIGN_RIGHT);

        PdfPCell footerDescriptionCell=new PdfPCell(footerDescriptionParagraph);
        footerDescriptionCell.setBorder(Rectangle.NO_BORDER);
        footerDescriptionCell.setBorderColorBottom(BaseColor.BLACK);
        footerDescriptionCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(totalCell);
        table.addCell(footerDescriptionCell);
        if(table.getTotalWidth()==0)
            table.setTotalWidth((document.right()-document.left())*table.getWidthPercentage()/100f);
        table.writeSelectedRows(0, -1,
                document.left(document.leftMargin()),
                table.getTotalHeight() + document.bottom(document.bottomMargin()),
                writer.getDirectContent() );    
    }
}

