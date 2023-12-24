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
import com.api.apibackend.Price.infra.entity.PriceEntity;
import com.api.apibackend.Price.infra.repository.PriceRepository;
import com.api.apibackend.Product.Domain.model.Price;
import com.api.apibackend.Product.Domain.model.Product;
import com.api.apibackend.Product.Domain.model.Supplier;
import com.api.apibackend.Product.Infra.entity.ProductEntity;
import com.api.apibackend.Product.Infra.repository.ProductRepository;
import com.api.apibackend.ProductCategory.infra.entity.ProductCategoryEntity;
import com.api.apibackend.ProductCategory.infra.repository.ProductCategoryRepository;
import com.api.apibackend.Supplier.Infra.entity.SupplierEntity;
import com.api.apibackend.Supplier.Infra.repository.SupplierRepository;

@Service
public class ProductAddService {
	private final ProductRepository productRepository;
	private final ProductCategoryRepository productCategoryRepository;
	private final MidiaRepository midiaRepository;
	private final PriceRepository priceRepository;
	private final SupplierRepository supplierRepository;

	@Autowired
	public ProductAddService(
			ProductRepository productRepository,
			ProductCategoryRepository productCategoryRepository,
			MidiaRepository midiaRepository,
			PriceRepository priceRepository,
			SupplierRepository supplierRepository) {
		this.productRepository = productRepository;
		this.productCategoryRepository = productCategoryRepository;
		this.midiaRepository = midiaRepository;
		this.priceRepository = priceRepository;
		this.supplierRepository = supplierRepository;
	}

	public ResponseEntity<List<Product>> addProducts(@RequestBody List<Product> productDTOList) {
		List<Product> addedProducts = new ArrayList<>();
		List<Supplier> addedSupplierProducts = new ArrayList<>();
		List<Price> addedPriceProducts = new ArrayList<>();
		List<String> errorMessages = new ArrayList<>();

		productDTOList.forEach(productDTO -> {
			try {
				MidiaEntity midia = new MidiaEntity();
				midia.setUrl(productDTO.getUrl());
				midia.setCategory(productDTO.getCategory());
				midia.setDate_create(productDTO.getDate_create());
				midiaRepository.save(midia);

				ProductCategoryEntity category = new ProductCategoryEntity();
				category.setName(productDTO.getCategory());
				productCategoryRepository.save(category);

				SupplierEntity supplier = new SupplierEntity();
				supplier.setNameCompany(productDTO.getSupplier().getNameCompany());
				supplierRepository.save(supplier);

				PriceEntity price = new PriceEntity();
				price.setPrice(productDTO.getPrice().getPrice());
				price.setDiscountPrice(productDTO.getPrice().getDiscountPrice());
				price.setStartDate(productDTO.getPrice().getStartDate());
				price.setEndDate(productDTO.getPrice().getEndDate());
				price.setCurrency(productDTO.getPrice().getCurrency());
				price.setUnitOfMeasure(productDTO.getPrice().getUnitOfMeasure());
				price.setStatus(productDTO.getPrice().getStatus());
				price.setDiscountType(productDTO.getPrice().getDiscountType());
				price.setPriceOrigin(productDTO.getPrice().getPriceOrigin());
				price.setNotes(productDTO.getPrice().getNotes());
				price.setUpdatedBy(productDTO.getPrice().getUpdatedBy());
				priceRepository.save(price);

				ProductEntity newProduct = new ProductEntity();
				newProduct.setName(productDTO.getName());
				newProduct.setDescription(productDTO.getDescription());
				newProduct.setQuantityInStock(productDTO.getQuantityInStock());
				newProduct.setStatus(productDTO.getStatus());
				newProduct.setCategory(category);
				newProduct.setMidia(midia);
				newProduct.setSupplierEntity(supplier);
				newProduct = productRepository.save(newProduct);

				Product addedProductDTO = new Product(
						newProduct.getIdProduct(),
						newProduct.getName(),
						midia.getUrl(),
						newProduct.getDescription(),
						category.getName(),
						newProduct.getQuantityInStock(),
						newProduct.getStatus()
						);

				Supplier supplierProdutoDTO =  new Supplier(
								supplier.getIdSupplier(),
								supplier.getNameCompany(),
								supplier.getRegion(),
								supplier.getOfficeSupplier(),
								supplier.getDate_creaated(),
								supplier.getContact(),
								supplier.getCnpj(),
								supplier.getSupplierAddressEntity());

				Price priceProductDTO = new Price(
						price.getIdPrice(),
						price.getProductEntity(),
						price.getPrice(),
						price.getDiscountPrice(),
						price.getStartDate(),
						price.getEndDate(),
						price.getCurrency(),
						price.getUnitOfMeasure(),
						price.getStatus(),
						price.getDiscountType(),
						price.getPriceOrigin(),
						price.getNotes(),
						price.getUpdatedBy());
						
				addedProducts.add(addedProductDTO);
				addedPriceProducts.add(priceProductDTO);
				addedSupplierProducts.add(supplierProdutoDTO);
			} catch (RuntimeException e) {
				errorMessages.add("Erro ao tentar adicionar o produto " + productDTO.getName() + ": " + e.getMessage());
			}
		});

		if (addedProducts.isEmpty()) {
			return new ResponseEntity<>(addedProducts, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(addedProducts, HttpStatus.CREATED);
	}
}
