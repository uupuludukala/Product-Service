package com.coolbook.erp.rest.assembler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.coolbook.erp.entity.CompanyEntity;
import com.coolbook.erp.entity.ProductCategoryEntity;
import com.coolbook.erp.entity.ProductEntity;
import com.coolbook.erp.model.CompanyGet;
import com.coolbook.erp.model.ProductGet;
import com.coolbook.erp.model.ProductPost;
import com.coolbook.erp.rest.service.CompanyService;
import com.coolbook.erp.security.SecurityFacade;



@Component
public class ProductAssembler {

	private SecurityFacade securityFacade;
	private CompanyService companyService;

	public ProductAssembler(SecurityFacade securityFacade, CompanyService companyService) {
		this.securityFacade = securityFacade;
		this.companyService = companyService;
	}

	public ProductGet essembleProductGet(ProductEntity productEntity) {
		ProductGet productGet = new ProductGet();
		productGet.setProduct_Id(productEntity.getId());
		productGet.setCanBeSold(productEntity.isCanBeSold());
		productGet.setCanBePurchased(productEntity.isCanBePurchased());
		productGet.setActive(productEntity.isActive());
		productGet.setBarcode(productEntity.getBarcode());
		productGet.setInternalNotes(productEntity.getInternalNotes());
		productGet.setCost(productEntity.getCost());
		productGet.setProductCategory(productEntity.getProductCategory().getId());
		productGet.setInternalReference(productEntity.getInternalReference());
		productGet.setProductName(productEntity.getProductName());
		productGet.setProductType(productEntity.getProductType());
		productGet.setQuantity(productEntity.getQuantity());
		productGet.setSalePrice(productEntity.getSalePrice());
		productGet.setImageUrl(productEntity.getImageUrl());
		productGet.setAvailableInPos(productEntity.isAvailableInPos());
		productGet.setMakeToOrder(productEntity.isMakeToOrder());
		productGet.setCustomerLeadTime(productEntity.getCustomerLeadTime());
		productGet.setDescDelOrder(productEntity.getDescDelOrder());
		productGet.setDescReceipt(productEntity.getDescReceipt());
		productGet.setWeight(productEntity.getWeight());
		productGet.setVolume(productEntity.getVolume());
		productGet.setResponsible(productEntity.getResponsible());
		List<CompanyGet> companies =new ArrayList<CompanyGet>();
		for(CompanyEntity company:productEntity.getCompanies()) {
			CompanyGet companyGet=new CompanyGet();
			companyGet.setCompany_id(company.getId());
			companyGet.setCompanyName(company.getCompanyName());
			companies.add(companyGet);
		}
		productGet.setCompanies(companies);
		return productGet;
	}

	public ProductEntity essembleProductentity(ProductPost productPost) {
		ProductEntity productEntity = new ProductEntity();
		productEntity.setCanBeSold(productPost.isCanBeSold());
		productEntity.setCanBePurchased(productPost.isCanBePurchased());
		productEntity.setActive(productPost.isActive());
		productEntity.setBarcode(productPost.getBarcode());
		productEntity.setInternalNotes(productPost.getInternalNotes());
		productEntity.setCost(productPost.getCost());
		ProductCategoryEntity productCategory = new ProductCategoryEntity();
		productCategory.setId(productPost.getProductCategory());
		productEntity.setProductCategory(productCategory);
		productEntity.setInternalReference(productPost.getInternalReference());
		productEntity.setProductName(productPost.getProductName());
		productEntity.setProductType(productPost.getProductType());
		productEntity.setQuantity(productPost.getQuantity());
		productEntity.setSalePrice(productPost.getSalePrice());
		productEntity.setImageUrl(productPost.getImageUrl());
		productEntity.setAvailableInPos(productPost.isAvailableInPos());
		productEntity.setMakeToOrder(productPost.isMakeToOrder());
		productEntity.setCustomerLeadTime(productPost.getCustomerLeadTime());
		productEntity.setDescDelOrder(productPost.getDescDelOrder());
		productEntity.setDescReceipt(productPost.getDescReceipt());
		productEntity.setWeight(productPost.getWeight());
		productEntity.setVolume(productPost.getVolume());
		productEntity.setResponsible(productPost.getResponsible());
		Set<CompanyEntity> companies=new HashSet<CompanyEntity>();
		companies.add(companyService.getCompanyById(securityFacade.getCurrentUser().getCompanyId()));		
		for(Long companyId:productPost.getCompanies()) {
			companies.add(companyService.getCompanyById(companyId));
		}
		productEntity.setCompanies(companies);
		return productEntity;
	}
}
