package ua.logos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.logos.Repository.ProductRepository;
import ua.logos.entity.ProductEntity;
import ua.logos.service.ProductService;

import java.util.List;

//@Component
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public void saveProduct(ProductEntity product) {

    }

    @Override
    public List<ProductEntity> findAllProducts() {
        return null;
    }

    @Override
    public ProductEntity findProductById(Long id) {
        return null;
    }
}
