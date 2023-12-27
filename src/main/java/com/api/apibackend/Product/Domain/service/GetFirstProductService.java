package com.api.apibackend.Product.Domain.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.api.apibackend.Midia.infra.entity.MidiaEntity;
import com.api.apibackend.Price.infra.entity.PriceEntity;
import com.api.apibackend.Product.Domain.model.Product;
import com.api.apibackend.Product.Domain.repository.IGetFirstService;
import com.api.apibackend.Product.Infra.entity.ProductEntity;
import com.api.apibackend.Product.Infra.repository.ProductRepository;

@Service
public class GetFirstProductService implements IGetFirstService {
	private ProductRepository productRepository;
	
	@Autowired
	public GetFirstProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Cacheable(value = "produtos", key = "'getFirst12Products'")
	public List<Product> execute() {
		List<ProductEntity> products = productRepository.findAll();

		List<Product> productDTOs = new ArrayList<>();

		int limit = Math.min(12, products.size());

		for (int i = 0; i < limit; i++) {
			ProductEntity product = products.get(i);
			MidiaEntity midia = product.getMidia();
			PriceEntity price = product.getPriceEntity();

			Product productDTO = new Product(
					product.getIdProduct(),
					product.getName(),
					midia != null ? midia.getUrl() : null,
					product.getDescription(),
					product.getCategory().getName(),
					price.getPrice(),
					price.getDiscountPrice(),
					product.getQuantityInStock());

			productDTOs.add(productDTO);
		}

		return productDTOs;
	}
}
