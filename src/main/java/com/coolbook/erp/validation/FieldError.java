package com.coolbook.erp.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Getter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class FieldError implements Serializable {

    private static final long serialVersionUID = 6315370139671943386L;
    private String code;
    private String fieldName;
    private String userMessage;
    private String rejectedValue;

}
