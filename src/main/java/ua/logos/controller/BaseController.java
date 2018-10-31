package ua.logos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.logos.entity.ProductEntity;
import ua.logos.service.ProductService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@RestController
public class BaseController {

    @Autowired
    private ProductService productService;


    //Запис даних в базу
    @GetMapping("test-save")
    public ResponseEntity<?> testSaveProduct() {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName("Iphone X");
        productEntity.setDescription("bla bla");
        productEntity.setPrice(new BigDecimal(999.99).setScale(2, RoundingMode.FLOOR));
        productEntity.setQwt(20);
        productEntity.setImage("image.png");
        productService.saveProduct(productEntity);
        System.out.println(productEntity.getPrice());
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    //отримати дані з бази
    @GetMapping("test-get")
    public ResponseEntity<List<ProductEntity>> testGetAll() {
        List<ProductEntity> products = productService.findAllProducts();
        return new ResponseEntity<List<ProductEntity>>(products, HttpStatus.OK);
    }

    //@RequestMapping(value = "/",method = RequestMethod.GET)
    @GetMapping("/")
    public String sayHello() {
        return "Hello World";
    }

    @GetMapping("/news")
    public String showNews() {
        return "NEWS";
    }

    @GetMapping("/profile")
    public ResponseEntity<String> showProfile() {
        String myProfile = "It is my profile";
        return new ResponseEntity<String>(myProfile, HttpStatus.OK);
    }

    @GetMapping("/books")
    public ResponseEntity<List<String>> getBooks() {
        List<String> books = new ArrayList<>();
        books.add("Book 1");
        books.add("Book 2");
        books.add("Book 3");
        books.add("Book 4");
        books.add("Book 5");
        books.add("Book 6");
        return new ResponseEntity<List<String>>(books, HttpStatus.OK);
    }


    @GetMapping("/books/{bookIndex}")
    public ResponseEntity<String> getBook(@PathVariable("bookIndex") Integer index) {
        List<String> books = new ArrayList<>();
        books.add("Book 1");
        books.add("Book 2");
        books.add("Book 3");
        books.add("Book 4");
        books.add("Book 5");
        books.add("Book 6");
        String book = "Does not exist";
        if (index < books.size()) {
            book = books.get(index);
        }
        return new ResponseEntity<String>(book, HttpStatus.OK);
    }
}
