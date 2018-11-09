package ua.logos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.logos.domain.ErrorDTO;
import ua.logos.domain.ProductDTO;
import ua.logos.service.FileStorageService;
import ua.logos.service.ProductService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private FileStorageService fileStorageService;

    //localhost:8080/products


    @GetMapping("search")
    public ResponseEntity<?> getProductByName(@RequestParam("name") String name) {                 //для того коли в назві є пробіл або /
        return ResponseEntity.ok(productService.findProductByName(name));
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@Valid @RequestBody ProductDTO product, BindingResult br) {
        if (br.hasErrors()) {
            System.out.println("Validation error");
            String errMsg = br.getFieldErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .findFirst().get().toString();
            ErrorDTO errorDTO = new ErrorDTO(errMsg);
            return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
        }
        productService.saveProduct(product);
        return new ResponseEntity<Void>(HttpStatus.CREATED); //201
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getProducts() {
        return ResponseEntity.ok(productService.findAllProducts());
    }

    @GetMapping("searchlike")
    public ResponseEntity<List<ProductDTO>> getProductsContains(@RequestParam("name") String name, @RequestParam("price") BigDecimal price) {
        return ResponseEntity.ok(productService.findProductByNameLikeAndPrice(name, price));
    }

    @GetMapping("{productId}")
    public ResponseEntity<ProductDTO> getProductById(
            @PathVariable("productId") Long id) {
        return ResponseEntity.ok(productService.findProductById(id));
    }

    @DeleteMapping("{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable("productId") Long id) {
        productService.deleteProductById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping("{productId}/image")
    public ResponseEntity<?> uploadImage(@PathVariable("productId") Long id, @RequestParam("file") MultipartFile file) {
        System.out.println(file.getOriginalFilename());
        fileStorageService.storeFile(file);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("image/{fileName}")
    public ResponseEntity<?> getImage(@PathVariable ("fileName") String fileName, HttpServletRequest request){
        System.out.println(fileName);
        Resource resource = fileStorageService.loadFile(fileName);
        String contentType = null;
        try {
            contentType=request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());

        }
        catch (Exception e){
            e.printStackTrace();
        }
        if (contentType==null){
            contentType="application/octet-stream";
        }
        return  ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline: fileName=\"" +resource.getFilename()+"\"")
                .body(resource);
    }
}
