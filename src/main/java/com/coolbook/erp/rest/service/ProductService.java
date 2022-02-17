package com.coolbook.erp.rest.service;

import com.coolbook.erp.rest.searchCriteria.ProductCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.coolbook.erp.entity.ProductEntity;
import com.coolbook.erp.repository.ProductRepository;
import com.coolbook.erp.repository.specs.ProductSearchSpecification;
import com.coolbook.erp.repository.specs.ProductSpecification;
import com.coolbook.erp.repository.specs.ProductSpecificationPOS;
import com.coolbook.erp.security.SecurityFacade;

@Service
public class ProductService {

	ProductRepository productRepository;

	private SecurityFacade securityFacade;

	@Autowired
	ProductService(ProductRepository productRepository, SecurityFacade securityFacade) {
		this.productRepository = productRepository;
		this.securityFacade = securityFacade;
	}

	public long saveProduct(ProductEntity product) {
		return this.productRepository.save(product).getId();
	}

	public void updateProduct(ProductEntity product, long id) {
		product.setId(id);
		this.productRepository.save(product);
	}

	public void deleteProduct(long id) {

		this.productRepository.delete(id);
	}

	public ProductEntity getProductById(long id) {
		return this.productRepository.getOne(id);
	}

	public Page<ProductEntity> getAllProduct(Pageable page, ProductCriteria criteria) {
		ProductSpecification specification = new ProductSpecification(criteria,
				securityFacade.getCurrentUser().getCompanyId());
		return this.productRepository.findAll(specification, page);
	}

	public Page<ProductEntity> getAllProductPOS(Pageable page, ProductCriteria criteria) {
		ProductSpecificationPOS specification = new ProductSpecificationPOS(criteria);
		return this.productRepository.findAll(specification, page);
	}

	public Page<ProductEntity> searchProduct(Pageable page, String searchValue) {
		ProductSearchSpecification specification = new ProductSearchSpecification(searchValue);
		return this.productRepository.findAll(specification, page);
	}

}
