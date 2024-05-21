package com.api.apibackend.modules.Product.Domain.specs;

import org.springframework.data.jpa.domain.Specification;

import com.api.apibackend.modules.ProductVariant.infra.persistence.entity.ProductVariantEntity;

public final class ProductVariantSpecs {
    public static Specification<ProductVariantEntity> withCategory(String category) {
        return (root, query, cb) -> {
            if (category == null) {
                return cb.isTrue(cb.literal(true));
            }
            return cb.equal(root.join("product").join("productCategory").get("name"), category);
        };
    }

    public static Specification<ProductVariantEntity> withColor(String color) {
        return (root, query, cb) -> {
            if (color == null) {
                return cb.isTrue(cb.literal(true));
            }
            return cb.equal(root.get("color").get("name"), color);
        };
    }

    public static Specification<ProductVariantEntity> maxPrice(Float price) {
        return (root, query, cb) -> {
            if (price == null) {
                return cb.isTrue(cb.literal(true));
            }
            return cb.lessThan(root.get("price"), price);
        };
    }

    public static Specification<ProductVariantEntity> minPrice(Float price) {
        return (root, query, cb) -> {
            if (price == null) {
                return cb.isTrue(cb.literal(true));
            }
            return cb.greaterThanOrEqualTo(root.get("price"), price);
        };
    }

}
