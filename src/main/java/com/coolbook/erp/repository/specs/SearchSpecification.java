package com.coolbook.erp.repository.specs;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class SearchSpecification  {

    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    DateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy"); 

    public boolean isDateValid(String dateStr) {
        dateFormat2.setLenient(false);
        try {
            dateFormat2.parse(dateStr);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
  
}
