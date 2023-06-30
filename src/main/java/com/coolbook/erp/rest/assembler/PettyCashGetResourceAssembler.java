package com.coolbook.erp.rest.assembler;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class PettyCashGetResourceAssembler  extends AbstractPettyCashResourceAssembler{

    @Override
    protected String getPettyCashSelfLink(String id) {
        if (!StringUtils.isEmpty(id)) {
            UriComponentsBuilder builder = ServletUriComponentsBuilder.fromHttpUrl(requestURI).query(null);
            if (!builder.build().toString().endsWith("/pettyCashes")) {
                String pettyCashCurrentURI = builder.build().toString();
                String pettyCashURI = pettyCashCurrentURI.substring(0, pettyCashCurrentURI.indexOf("/pettyCashes") + 9);
                builder = ServletUriComponentsBuilder.fromHttpUrl(pettyCashURI);
            }
            return builder.path("/" + id).build().toUriString();
        }
        return ServletUriComponentsBuilder.fromHttpUrl(requestURI).build().toUriString();
    }

}

