package com.api.apibackend.Product.Domain.repository;

import java.util.List;

import com.api.apibackend.Product.Domain.model.Product;

public interface IGetFirstService {
	public List<Product> execute();
}
