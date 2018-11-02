package ua.logos.service;

import ua.logos.domain.CategoryDTO;

import java.util.List;

public interface CategoryService {
    void createCategory(CategoryDTO categoryDTO);
    List<CategoryDTO> findAllCategories();
    CategoryDTO findCategoryById(Long id);
}
