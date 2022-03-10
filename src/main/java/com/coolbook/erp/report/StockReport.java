package com.coolbook.erp.report;

import com.coolbook.erp.entity.CompanyEntity;
import com.coolbook.erp.entity.ProductEntity;
import com.coolbook.erp.repository.ProductRepository;
import com.coolbook.erp.rest.service.CompanyService;
import com.coolbook.erp.security.SecurityFacade;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;


@Component
public class StockReport {

    @Autowired
    CompanyService companyService;

    @Autowired
    ProductRepository productRepository;
    
    @Autowired
    private SecurityFacade securityFacade;

    public ByteArrayInputStream generateStockReport(long productCategory) {
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
            document.add( Chunk.NEWLINE );
            document.add( Chunk.NEWLINE );
            List<ProductEntity> products=productRepository.getProductsByCategory(productCategory);
            float [] pointColumnWidths = {150F, 150F,150F, 150F, 150F};
            PdfPTable table = new PdfPTable(pointColumnWidths);
            table.addCell("Product Category Code");
            table.addCell("Product Category Name");
            table.addCell("Product Code");            
            table.addCell("Product Name");
            table.addCell("Quantity");
            if(products.isEmpty()){
                table.addCell("No Records Found");
            }
            for(ProductEntity product:products){
                table.addCell(product.getProductCategory().getProductCategoryCode());
                table.addCell(product.getProductCategory().getProductCategoryName());
                table.addCell(product.getProductCode());
                table.addCell(product.getProductName());
                table.addCell(String.valueOf(product.getQuantity()));
            }
            document.add( table);
            

        } catch (DocumentException | MalformedURLException | URISyntaxException | FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        document.close();
        return new ByteArrayInputStream(out.toByteArray());
    }
//
//    public ByteArrayInputStream generateStockReport(long productCategory) throws IOException, DocumentException {
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        Document document = new Document();
//        PdfWriter.getInstance(document, out);
//        document.open();
//        List<ProductEntity> products=productRepository.getProductsByCategory(productCategory);
//        float [] pointColumnWidths = {150F, 150F, 150F};
//        PdfPTable table = new PdfPTable(pointColumnWidths);
//        for(ProductEntity product:products){
//            table.addCell(product.getProductCode());
//            table.addCell(product.getProductName());
//            table.addCell(String.valueOf(product.getQuantity()));
//        }
//        document.add(table);
//        document.close();
//        return new ByteArrayInputStream(out.toByteArray());
//    }
    
}
