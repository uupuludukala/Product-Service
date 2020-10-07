package com.coolbook.erp.validation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class ApiError implements Serializable {

    private static final long serialVersionUID = -5130054537377087224L;
    private List<FieldError> fieldsErrors = new ArrayList<>();
    private List<GlobalError> globalErrors = new ArrayList<>();

    public ApiError addFieldError(String code, String fieldName, String userMessage, String rejectedValue) {
        this.fieldsErrors.add(new FieldError(code, fieldName, userMessage, rejectedValue));
        return this;
    }

    public ApiError addGlobalError(String code, List<String> fields, String userMessage) {
        this.globalErrors.add(new GlobalError(code, fields, userMessage));
        return this;
    }

}
