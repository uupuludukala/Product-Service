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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class ReportController {
    
    @Autowired
    private ReportService reportService;

    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @RequestMapping(value = "stockReport/{productCategory}", method = RequestMethod.GET)
    public ResponseEntity stockReport(@Valid @PathVariable  Long productCategory) {
        ByteArrayInputStream outStream = reportService.generateStockReport(productCategory);

        StringBuilder headerContentDispositionValue = new StringBuilder();
        headerContentDispositionValue.append("attachment; filename=stockReport.pdf");

        return ResponseEntity.ok().header(
                "Content-Disposition", headerContentDispositionValue.toString()).contentType(
                MediaType.APPLICATION_OCTET_STREAM).body(new InputStreamResource(outStream));
    }

    @RequestMapping(value = "incomeReport", method = RequestMethod.GET)
    public ResponseEntity incomeReport( @RequestParam String fromDate,String toDate) throws ParseException {
        ByteArrayInputStream outStream = reportService.generateIncomeReport(dateFormat.parse(fromDate),dateFormat.parse(toDate));

        StringBuilder headerContentDispositionValue = new StringBuilder();
        headerContentDispositionValue.append("attachment; filename=stockReport.pdf");

        return ResponseEntity.ok().header(
                "Content-Disposition", headerContentDispositionValue.toString()).contentType(
                MediaType.APPLICATION_OCTET_STREAM).body(new InputStreamResource(outStream));
    }
}
