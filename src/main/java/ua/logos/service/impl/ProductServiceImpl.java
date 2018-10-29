package ua.logos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
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
        productRepository.save(product);
    }

    @Override
    public List<ProductEntity> findAllProducts() {
        return productRepository.findAll(); //select * from products
    }

    @Override
    public ProductEntity findProductById(Long id) {
        return productRepository.findById(id).get();
    }
}
