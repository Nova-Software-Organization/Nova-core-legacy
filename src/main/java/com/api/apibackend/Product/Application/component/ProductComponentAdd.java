package com.api.apibackend.Product.Application.component;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.api.apibackend.Price.infra.entity.PriceEntity;
import com.api.apibackend.Price.infra.repository.PriceRepository;
import com.api.apibackend.Product.Domain.model.Product;
import com.api.apibackend.Product.Infra.entity.ProductEntity;
import com.api.apibackend.Supplier.Infra.entity.SupplierEntity;
import com.api.apibackend.Supplier.Infra.repository.SupplierRepository;
import com.api.apibackend.SupplierAddress.infra.persistence.entity.SupplierAddressEntity;
import com.api.apibackend.SupplierAddress.infra.repository.SupplierAddressRepository;

@Component
public class ProductComponentAdd {
    private PriceRepository priceRepository;
    private SupplierAddressRepository supplierAddressRepository;
    private SupplierRepository supplierRepository;

    @Autowired
    public ProductComponentAdd(PriceRepository priceRepository, SupplierAddressRepository supplierAddressRepository, SupplierRepository supplierRepository) {
        this.priceRepository = priceRepository;
        this.supplierAddressRepository = supplierAddressRepository;
        this.supplierRepository = supplierRepository;
    }

    public void priceSaveProduct(Product productDTO, ProductEntity newProduct) {
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
    }

    public SupplierEntity supplierSave(Product productDTO) {
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

        return supplier;
    }
}
