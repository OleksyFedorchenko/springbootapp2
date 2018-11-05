package ua.logos.service;

import ua.logos.domain.ProductDTO;


import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    void saveProduct(ProductDTO product);

    List<ProductDTO> findAllProducts();

    ProductDTO findProductById(Long id);

    void deleteProductById(Long id);

    ProductDTO findProductByName(String name);
    List<ProductDTO> findProductByNameLikeAndPrice(String name, BigDecimal price);
}
