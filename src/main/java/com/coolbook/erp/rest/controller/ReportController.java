package com.coolbook.erp.rest.controller;

import com.coolbook.erp.model.ProductPost;
import com.coolbook.erp.rest.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.ByteArrayInputStream;

@RestController
public class ReportController {
    
    @Autowired
    private ReportService reportService;
    

    @RequestMapping(value = "stockReport/{productCategory}", method = RequestMethod.GET)
    public ResponseEntity stockReport(@Valid @PathVariable  Long productCategory) {
        ByteArrayInputStream outStream = reportService.generateStockReport(productCategory);

        StringBuilder headerContentDispositionValue = new StringBuilder();
        headerContentDispositionValue.append("attachment; filename=stockReport.pdf");

        return ResponseEntity.ok().header(
                "Content-Disposition", headerContentDispositionValue.toString()).contentType(
                MediaType.APPLICATION_OCTET_STREAM).body(new InputStreamResource(outStream));
    }
}
