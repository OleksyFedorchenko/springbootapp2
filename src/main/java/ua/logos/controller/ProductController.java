package ua.logos.controller;

import lombok.Lombok;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.logos.domain.ProductDTO;
import ua.logos.entity.ProductEntity;
import ua.logos.service.ProductService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {
    @Autowired
    ProductService productService;

    //localhost:8080/products


    @GetMapping("search")
    public ResponseEntity<?> getProductByName(@RequestParam("name") String name){                 //для того коли в назві є пробіл або /
        ProductDTO product=productService.findProductByName(name);
        return new ResponseEntity<ProductDTO>(product,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody ProductDTO product) {
        productService.saveProduct(product);
    return new ResponseEntity<Void>(HttpStatus.CREATED); //201
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getProducts(){
        List<ProductDTO> products=productService.findAllProducts();
        return new ResponseEntity<List<ProductDTO>>(products,HttpStatus.OK);
    }

    @GetMapping("searchlike")
    public ResponseEntity<List<ProductDTO>> getProductsContains(@RequestParam("name")String name, @RequestParam("price")BigDecimal price){
        List<ProductDTO> products=productService.findProductByNameLikeAndPrice(name,price);
        return new ResponseEntity<List<ProductDTO>>(products,HttpStatus.OK);
    }

    @GetMapping("{productId}")
    public ResponseEntity<ProductDTO> getProductById(
            @PathVariable("productId") Long id){
        ProductDTO product = productService.findProductById(id);
        return new ResponseEntity<ProductDTO>(product,HttpStatus.OK);
    }

    @DeleteMapping("{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable("productId") Long id){
    productService.deleteProductById(id);
    return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
