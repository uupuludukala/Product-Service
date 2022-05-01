package com.coolbook.erp.rest.service;

import com.coolbook.erp.report.IncomeReport;
import com.coolbook.erp.report.StockReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.Date;

@Service
public class ReportService {

    @Autowired
    private StockReport stockReport;
    
    @Autowired
    private IncomeReport incomeReport;
    
    
    public ByteArrayInputStream generateStockReport(long productCategory) {
        return stockReport.generateStockReport(productCategory);
    }

    public ByteArrayInputStream generateIncomeReport(Date dateFrom, Date dateUntil) {
        return incomeReport.generateIncomeReport( dateFrom,  dateUntil);
        
    }
}
