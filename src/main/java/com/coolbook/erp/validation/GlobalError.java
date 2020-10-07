package com.coolbook.erp.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Getter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class GlobalError implements Serializable {

    private static final long serialVersionUID = 8155630775174930513L;
    private String code;
    private List<String> fields;
    private String userMessage;

}
