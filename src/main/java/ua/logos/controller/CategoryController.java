package ua.logos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.logos.domain.CategoryDTO;
import ua.logos.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("categories")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody CategoryDTO category) {
        categoryService.createCategory(category);
        return new ResponseEntity<Void>(HttpStatus.CREATED); //201
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getProducts(){
        List<CategoryDTO> categories=categoryService.findAllCategories();
        return new ResponseEntity<List<CategoryDTO>>(categories,HttpStatus.OK);
    }

    @GetMapping("{categoryId}")
    public ResponseEntity<CategoryDTO> getProductById(
            @PathVariable("categoryId") Long id){
        CategoryDTO category = categoryService.findCategoryById(id);
        return new ResponseEntity<CategoryDTO>(category,HttpStatus.OK);
    }


}
