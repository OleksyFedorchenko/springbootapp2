package ua.logos.service;

import ua.logos.entity.ProductEntity;

import java.util.List;

public interface ProductService {
    void saveProduct(ProductEntity product);

    List<ProductEntity> findAllProducts();

    ProductEntity findProductById(Long id);
}
