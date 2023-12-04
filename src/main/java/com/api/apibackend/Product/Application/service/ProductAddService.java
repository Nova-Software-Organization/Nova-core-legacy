package com.api.apibackend.Product.Application.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.api.apibackend.Midia.infra.entity.MidiaEntity;
import com.api.apibackend.Midia.infra.repository.MidiaRepository;
import com.api.apibackend.Product.Domain.model.Product;
import com.api.apibackend.Product.infra.entity.ProductEntity;
import com.api.apibackend.Product.infra.repository.ProductRepository;
import com.api.apibackend.ProductCategory.infra.entity.ProductCategoryEntity;
import com.api.apibackend.ProductCategory.infra.repository.ProductCategoryRepository;

@Service
public class ProductAddService {

	private ProductRepository productRepository;
	private ProductCategoryRepository productCategoryRepository;
	private MidiaRepository midiaRepository;

	@Autowired
	public ProductAddService(
		ProductRepository productRepository,
	 	ProductCategoryRepository productCategoryRepository,
		MidiaRepository midiaRepository
	) {
		this.productRepository = productRepository;
		this.productCategoryRepository = productCategoryRepository;
		this.midiaRepository = midiaRepository;
	}

	public ResponseEntity<?> addProducts(@RequestBody List<Product> productDTOList) {
		List<Product> addedProducts = new ArrayList<>();
		List<String> errorMessages = new ArrayList<>();
	
		productDTOList.forEach(productDTO -> {
			if (productDTO.getUrl() == null) {
				errorMessages.add("Produto inválido: " + productDTO.getName());
				return;
			}
	
			try {
				MidiaEntity midia = new MidiaEntity();
				midia.setUrl(productDTO.getUrl());
				midia.setCategory(productDTO.getCategory());
				midia.setDate_create(productDTO.getDate_create());
				midiaRepository.save(midia);
	
				ProductCategoryEntity category = new ProductCategoryEntity();
				category.setName(productDTO.getCategory());
				productCategoryRepository.save(category);
	
				ProductEntity newProduct = new ProductEntity();
				newProduct.setName(productDTO.getName());
				newProduct.setDescription(productDTO.getDescription());
				newProduct.setPrice(productDTO.getPrice());
				newProduct.setQuantityInStock(productDTO.getQuantityInStock());
				newProduct.setDePrice(productDTO.getDePrice());
	
				newProduct.setCategory(category);
				newProduct.setMidia(midia);
				newProduct = productRepository.save(newProduct);
	
				Product addedProductDTO = new Product(
					newProduct.getId(),
					newProduct.getName(),
					midia.getUrl(),
					newProduct.getDescription(),
					category.getName(),
					newProduct.getPrice(),
					newProduct.getDePrice(),
					newProduct.getQuantityInStock()
				);
	
				addedProducts.add(addedProductDTO);
			} catch (Exception e) {
				errorMessages.add("Erro ao tentar adicionar o produto " + productDTO.getName() + ": " + e.getMessage());
			}
		});
	
		if (addedProducts.isEmpty()) {
			return new ResponseEntity<>(errorMessages, HttpStatus.BAD_REQUEST);
		}
	
		return new ResponseEntity<>(addedProducts, HttpStatus.CREATED);
	}
}
