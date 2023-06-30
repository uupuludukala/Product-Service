package com.coolbook.erp.rest.assembler;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class WorkShiftGetResourceAssembler  extends AbstractWorkShiftResourceAssembler {

    @Override
    protected String getWorkShiftSelfLink(String id) {
        if (!StringUtils.isEmpty(id)) {
            UriComponentsBuilder builder = ServletUriComponentsBuilder.fromHttpUrl(requestURI).query(null);
            if (!builder.build().toString().endsWith("/WorkShiftess")) {
                String WorkShiftCurrentURI = builder.build().toString();
                String WorkShiftURI = WorkShiftCurrentURI.substring(0, WorkShiftCurrentURI.indexOf("/WorkShiftess") + 9);
                builder = ServletUriComponentsBuilder.fromHttpUrl(WorkShiftURI);
            }
            return builder.path("/" + id).build().toUriString();
        }
        return ServletUriComponentsBuilder.fromHttpUrl(requestURI).build().toUriString();
    }

}
