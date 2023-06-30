package com.coolbook.erp.rest.assembler;

import com.coolbook.erp.entity.PettyCashEntity;
import com.coolbook.erp.model.PettyCashGet;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

public abstract class AbstractPettyCashResourceAssembler extends ResourceAssemblerSupport<PettyCashEntity, PettyCashGet> {
    protected String requestURI;

    public AbstractPettyCashResourceAssembler() {
        super(PettyCashEntity.class, PettyCashGet.class);
    }

    @Override
    public PettyCashGet toResource(PettyCashEntity pettyCashEntity) {
        return createPettyCashJson(pettyCashEntity);
    }

    private PettyCashGet createPettyCashJson(PettyCashEntity pettyCashEntity) {
        PettyCashGet pettyCashGet=new PettyCashGet();
        pettyCashGet.setPettyCash_id(pettyCashEntity.getId());
        pettyCashGet.setDate(pettyCashEntity.getDate());
        pettyCashGet.setDescription(pettyCashEntity.getDescription());
        pettyCashGet.setAmount(pettyCashEntity.getAmount());
        return pettyCashGet;
    }

    protected abstract String getPettyCashSelfLink(String id);
}