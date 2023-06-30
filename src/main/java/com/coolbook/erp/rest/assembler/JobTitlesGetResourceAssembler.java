package com.coolbook.erp.rest.assembler;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;


@Component
public class JobTitlesGetResourceAssembler extends AbstractJobTitlesResourceAssembler {

    @Override
    protected String getJobTitlesSelfLink(String id) {
        if (!StringUtils.isEmpty(id)) {
            UriComponentsBuilder builder = ServletUriComponentsBuilder.fromHttpUrl(requestURI).query(null);
            if (!builder.build().toString().endsWith("/JobTitlesess")) {
                String JobTitlesCurrentURI = builder.build().toString();
                String JobTitlesURI = JobTitlesCurrentURI.substring(0, JobTitlesCurrentURI.indexOf("/JobTitlesess") + 9);
                builder = ServletUriComponentsBuilder.fromHttpUrl(JobTitlesURI);
            }
            return builder.path("/" + id).build().toUriString();
        }
        return ServletUriComponentsBuilder.fromHttpUrl(requestURI).build().toUriString();
    }

}
