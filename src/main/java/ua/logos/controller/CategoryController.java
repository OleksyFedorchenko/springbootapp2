package ua.logos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.logos.domain.CategoryDTO;
import ua.logos.domain.ErrorDTO;
import ua.logos.service.CategoryService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("categories")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @PostMapping
    public ResponseEntity<?> createProduct(@Valid @RequestBody CategoryDTO category, BindingResult br) {
        if (br.hasErrors()) {
            System.out.println("Validation error");
            String errMsg = br.getFieldErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .findFirst().get().toString();
            ErrorDTO errorDTO = new ErrorDTO(errMsg);
            return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
        }
        categoryService.createCategory(category);
        return new ResponseEntity<Void>(HttpStatus.CREATED); //201
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getProducts() {
        return ResponseEntity.ok(categoryService.findAllCategories());
    }

    @GetMapping("{categoryId}")
    public ResponseEntity<CategoryDTO> getProductById(
            @PathVariable("categoryId") Long id) {
        return ResponseEntity.ok(categoryService.findCategoryById(id));
    }


}
