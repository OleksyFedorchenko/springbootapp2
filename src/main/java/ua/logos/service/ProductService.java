package ua.logos.service;

import ua.logos.domain.ProductDTO;


import java.util.List;

public interface ProductService {
    void saveProduct(ProductDTO product);

    List<ProductDTO> findAllProducts();

    ProductDTO findProductById(Long id);

    void deleteProductById(Long id);
}
