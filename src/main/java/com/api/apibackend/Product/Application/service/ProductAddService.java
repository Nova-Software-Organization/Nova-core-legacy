package com.api.apibackend.Product.Application.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.apibackend.Midia.infra.entity.MidiaEntity;
import com.api.apibackend.Midia.infra.repository.MidiaRepository;
import com.api.apibackend.Price.infra.entity.PriceEntity;
import com.api.apibackend.Price.infra.repository.PriceRepository;
import com.api.apibackend.Product.Domain.model.Product;
import com.api.apibackend.Product.Infra.entity.ProductEntity;
import com.api.apibackend.Product.Infra.repository.ProductRepository;
import com.api.apibackend.ProductCategory.infra.entity.ProductCategoryEntity;
import com.api.apibackend.ProductCategory.infra.repository.ProductCategoryRepository;
import com.api.apibackend.Supplier.Infra.entity.SupplierEntity;
import com.api.apibackend.Supplier.Infra.repository.SupplierRepository;
import com.api.apibackend.SupplierAddress.infra.entity.SupplierAddressEntity;
import com.api.apibackend.SupplierAddress.infra.repository.SupplierAddressRepository;

@Service
public class ProductAddService {
    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final MidiaRepository midiaRepository;
    private final PriceRepository priceRepository;
    private final SupplierRepository supplierRepository;
    private final SupplierAddressRepository supplierAddressRepository;

    @Autowired
    public ProductAddService(
            ProductRepository productRepository,
            ProductCategoryRepository productCategoryRepository,
            MidiaRepository midiaRepository,
            PriceRepository priceRepository,
            SupplierRepository supplierRepository,
            SupplierAddressRepository supplierAddressRepository) {
        this.productRepository = productRepository;
        this.productCategoryRepository = productCategoryRepository;
        this.midiaRepository = midiaRepository;
        this.priceRepository = priceRepository;
        this.supplierRepository = supplierRepository;
        this.supplierAddressRepository = supplierAddressRepository;
    }

    public ResponseEntity<String> addProducts(List<Product> productDTOList) {
        List<String> errorMessages = new ArrayList<>();

        for (Product productDTO : productDTOList) {
            try {
                MidiaEntity midia = new MidiaEntity();
                midia.setUrl(productDTO.getUrl());
                midia.setCategory(productDTO.getCategory());
                midia.setDateCreate(new Date());
                midia = midiaRepository.save(midia);

                ProductCategoryEntity category = new ProductCategoryEntity();
                category.setName(productDTO.getCategory());
                category = productCategoryRepository.save(category);

                SupplierAddressEntity supplierAddress = new SupplierAddressEntity();
                supplierAddress.setCep(productDTO.getSupplier().getSupplierAddress().getCep());
                supplierAddress.setRoad(productDTO.getSupplier().getSupplierAddress().getRoad());
                supplierAddress.setNeighborhood(productDTO.getSupplier().getSupplierAddress().getNeighborhood());
                supplierAddress.setNumberHouseOrCompany(productDTO.getSupplier().getSupplierAddress().getNumberHouseOrCompany());
                supplierAddressRepository.save(supplierAddress);

                SupplierEntity supplier = new SupplierEntity();
                supplier.setNameCompany(productDTO.getSupplier().getNameCompany());
                supplier.setCnpj(productDTO.getSupplier().getCnpj());
                supplier.setRegion(productDTO.getSupplier().getRegion());
                supplier.setOfficeSupplier(productDTO.getSupplier().getOfficeSupplier());
                supplier.setDateCreated(new Date());
                supplier.setContact(productDTO.getSupplier().getContact());
                supplier.setSupplierAddressEntity(supplierAddress);
                supplier = supplierRepository.save(supplier);

                ProductEntity newProduct = new ProductEntity();
                newProduct.setName(productDTO.getName());
                newProduct.setDescription(productDTO.getDescription());
                newProduct.setQuantityInStock(productDTO.getQuantityInStock());
                newProduct.setStatus(productDTO.getStatus());
                newProduct.setCategory(category);
                newProduct.setMidia(midia);
                newProduct.setSupplierEntity(supplier);
                newProduct = productRepository.save(newProduct);

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
				price.setProductEntity(newProduct);
                priceRepository.save(price);

            } catch (RuntimeException e) {
                errorMessages.add("Erro ao tentar adicionar os produtos: " + e.getMessage());
            }
        }

        if (!errorMessages.isEmpty()) {
            return ResponseEntity.badRequest().body(String.join("\n", errorMessages));
        }

        return ResponseEntity.status(HttpStatus.CREATED).body("Produtos adicionandos com sucesso!");
    }
}
