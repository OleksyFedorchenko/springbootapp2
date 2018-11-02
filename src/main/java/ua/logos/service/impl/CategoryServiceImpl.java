package ua.logos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.logos.Repository.CategoryRepository;
import ua.logos.domain.CategoryDTO;
import ua.logos.entity.CategoryEntity;
import ua.logos.service.CategoryService;
import ua.logos.utils.ObjectMapperUtils;

import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ObjectMapperUtils modelMapper;

    @Override
    public void createCategory(CategoryDTO categoryDTO) {
        CategoryEntity category =modelMapper.map(categoryDTO,CategoryEntity.class);
        categoryRepository.save(category);
    }

    @Override
    public List<CategoryDTO> findAllCategories() {
        List<CategoryEntity> categoryEntities=categoryRepository.findAll();
        List<CategoryDTO> categoryDTOList = modelMapper.mapAll(categoryEntities,CategoryDTO.class);
        return categoryDTOList;
    }

    @Override
    public CategoryDTO findCategoryById(Long id) {
        CategoryEntity category = categoryRepository.findById(id).get();
        CategoryDTO categoryDTO=modelMapper.map(category,CategoryDTO.class);
        return categoryDTO;
    }
}
