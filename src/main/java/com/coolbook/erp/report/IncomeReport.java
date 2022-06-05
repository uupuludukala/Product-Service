package com.coolbook.erp.report;

import com.coolbook.erp.entity.InvoiceEntity;
import com.coolbook.erp.entity.InvoiceIncomeEntity;
import com.coolbook.erp.repository.InvoiceIncomeRepository;
import com.coolbook.erp.repository.InvoiceRepository;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class IncomeReport {

    DecimalFormat df = new DecimalFormat("###.###");

    DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
    
    @Autowired
    private InvoiceIncomeRepository invoiceIncomeRepository;
    

    Font normalFont = FontFactory.getFont(FontFactory.COURIER, 10, BaseColor.BLACK);

    Font totalFont = FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD);

    @Autowired
    private InvoiceRepository invoiceRepository;
    
    public ByteArrayInputStream generateIncomeReport(Date dateFrom, Date dateUntil){
        List<InvoiceEntity> invoiceList=invoiceRepository.getInvoiceByPeriod(dateFrom,dateUntil);
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            PdfWriter.getInstance(document, out);
        } catch (DocumentException e1) {
            e1.printStackTrace();
        }
        document.open();
        try {
            populateInvoices( document,invoiceList);
        } catch (DocumentException  e) {
            e.printStackTrace();
        }
        document.close();
        return new ByteArrayInputStream(out.toByteArray());
    }
    
    private void populateInvoices(Document document, List<InvoiceEntity> invoiceList) throws DocumentException {
        float [] pointColumnWidths = {100F, 300F,150F, 150F, 150F,150F};
        PdfPTable table = new PdfPTable(pointColumnWidths);
        PdfPCell invoiceInHeader=new PdfPCell(new Phrase(new Chunk("Invoice Id",normalFont)));
        invoiceInHeader.setBorder(Rectangle.NO_BORDER);
        table.addCell(invoiceInHeader);
        PdfPCell invoiceNumerHeader=new PdfPCell(new Phrase(new Chunk("Invoice Number",normalFont)));
        invoiceNumerHeader.setBorder(Rectangle.NO_BORDER);
        table.addCell(invoiceNumerHeader);
        PdfPCell dateHeader=new PdfPCell(new Phrase(new Chunk("Date",normalFont)));
        dateHeader.setBorder(Rectangle.NO_BORDER);
        table.addCell(dateHeader);
        PdfPCell totalHeader=new PdfPCell(new Phrase(new Chunk("Total",normalFont)));
        totalHeader.setBorder(Rectangle.NO_BORDER);
        table.addCell(totalHeader);
        PdfPCell costHeader=new PdfPCell(new Phrase(new Chunk("Cost",normalFont)));
        costHeader.setBorder(Rectangle.NO_BORDER);
        table.addCell(costHeader);
        PdfPCell profitHeader=new PdfPCell(new Phrase(new Chunk("Profit",normalFont)));
        profitHeader.setBorder(Rectangle.NO_BORDER);
        table.addCell(profitHeader);
        double totalCost=0;
        double totalSalePrice=0;
        double totalProfit=0;
        for(InvoiceEntity invoice:invoiceList){
            PdfPCell invoiceIdCell=new PdfPCell(new Phrase(new Chunk(String.valueOf(invoice.getId()),normalFont)));
            invoiceIdCell.setBorder(Rectangle.NO_BORDER);
            table.addCell(invoiceIdCell);
            PdfPCell invoiceNumberCell=new PdfPCell(new Phrase(new Chunk(invoice.getInvoiceNumber(),normalFont)));
            invoiceNumberCell.setBorder(Rectangle.NO_BORDER);
            table.addCell(invoiceNumberCell);
            PdfPCell dateCell=new PdfPCell(new Phrase(new Chunk(dateFormat2.format(invoice.getDate()),normalFont)));
            dateCell.setBorder(Rectangle.NO_BORDER);
            table.addCell(dateCell);
            PdfPCell totalCell=new PdfPCell(new Phrase(new Chunk(String.valueOf(invoice.getTotal()),normalFont)));
            totalCell.setBorder(Rectangle.NO_BORDER);
            table.addCell(totalCell);
            List<InvoiceIncomeEntity> invoiceIncomeList=invoiceIncomeRepository.getInvoiceIncomesByInvoice(invoice);
            double cost=0;
            for(InvoiceIncomeEntity invoiceIncomeEntity:invoiceIncomeList){
                cost+=invoiceIncomeEntity.getCost();
            }
            totalCost+=cost;
            totalSalePrice+=invoice.getTotal();
            totalProfit+=invoice.getTotal()-cost;
            PdfPCell costCell=new PdfPCell(new Phrase(new Chunk(String.valueOf(cost),normalFont)));
            costCell.setBorder(Rectangle.NO_BORDER);
            table.addCell(costCell);
            PdfPCell profitCell=new PdfPCell(new Phrase(new Chunk(String.valueOf(df.format(invoice.getTotal()-cost)),normalFont)));
            profitCell.setBorder(Rectangle.NO_BORDER);
            table.addCell(profitCell);
        }
        Phrase companyPhrase=new Phrase();
        companyPhrase.add(new Chunk("Total Cost : ",totalFont));
        companyPhrase.add(new Chunk(String.valueOf(df.format(totalCost)),totalFont));
        companyPhrase.add(Chunk.NEWLINE);
        companyPhrase.add(new Chunk("Total Sale Price : ",totalFont));
        companyPhrase.add(new Chunk(String.valueOf(df.format(totalSalePrice)),totalFont));
        companyPhrase.add(Chunk.NEWLINE);
        companyPhrase.add(new Chunk("Total Profit : ",totalFont));
        companyPhrase.add(new Chunk(String.valueOf(df.format(totalProfit)),totalFont));
        companyPhrase.add(Chunk.NEWLINE);
        document.add(table);
        document.add(companyPhrase);
    }
}
