package ua.logos.controller;

import lombok.Lombok;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.logos.entity.ProductEntity;
import ua.logos.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {
    @Autowired
    ProductService productService;

    //localhost:8080/products

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody ProductEntity product) {
        productService.saveProduct(product);
    return new ResponseEntity<Void>(HttpStatus.CREATED); //201
    }

    @GetMapping
    public ResponseEntity<List<ProductEntity>> getProducts(){
        List<ProductEntity> products=productService.findAllProducts();
        return new ResponseEntity<List<ProductEntity>>(products,HttpStatus.OK);
    }

    @GetMapping("{productId}")
    public ResponseEntity<ProductEntity> getProductById(
            @PathVariable("productId") Long id){
        ProductEntity product = productService.findProductById(id);
        return new ResponseEntity<ProductEntity>(product,HttpStatus.OK);
    }

    @DeleteMapping("{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable("productId") Long id){
    productService.deleteProductById(id);
    return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
