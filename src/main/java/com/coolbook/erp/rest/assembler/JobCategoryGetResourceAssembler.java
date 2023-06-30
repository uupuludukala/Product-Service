package com.coolbook.erp.rest.assembler;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class JobCategoryGetResourceAssembler  extends AbstractJobCategoryResourceAssembler{

    @Override
    protected String getJobCategorySelfLink(String id) {
        if (!StringUtils.isEmpty(id)) {
            UriComponentsBuilder builder = ServletUriComponentsBuilder.fromHttpUrl(requestURI).query(null);
            if (!builder.build().toString().endsWith("/JobCategoryess")) {
                String JobCategoryCurrentURI = builder.build().toString();
                String JobCategoryURI = JobCategoryCurrentURI.substring(0, JobCategoryCurrentURI.indexOf("/JobCategoryess") + 9);
                builder = ServletUriComponentsBuilder.fromHttpUrl(JobCategoryURI);
            }
            return builder.path("/" + id).build().toUriString();
        }
        return ServletUriComponentsBuilder.fromHttpUrl(requestURI).build().toUriString();
    }

}
