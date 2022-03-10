package com.coolbook.erp.rest.service;

import com.coolbook.erp.report.StockReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;

@Service
public class ReportService {

    @Autowired
    private StockReport stockReport;
    
    public ByteArrayInputStream generateStockReport(long productCategory) {
        return stockReport.generateStockReport(productCategory);
    }
}
