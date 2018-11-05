package ua.logos.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import ua.logos.Repository.ProductRepository;
import ua.logos.domain.ProductDTO;
import ua.logos.entity.ProductEntity;
import ua.logos.exception.AlreadyExistsException;
import ua.logos.exception.ResourceNotFoundException;
import ua.logos.service.ProductService;
import ua.logos.utils.ObjectMapperUtils;

import java.math.BigDecimal;
import java.util.List;

//@Component
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ObjectMapperUtils modelMapper;


    @Override
    public void saveProduct(ProductDTO productDTO) {

//        ProductEntity product =new ProductEntity();
//        product.setName(productDTO.getName());
//        product.setDescription(productDTO.getDescription());
//        product.setPrice(productDTO.getPrice());
//        product.setImage(productDTO.getImage());
//        product.setQwt(productDTO.getQwt());
        ProductEntity productEntity=productRepository.findByName(productDTO.getName());
        if (productEntity!=null){
            throw new AlreadyExistsException("Product with name ["+productDTO.getName()+"]already exists");
        }
        ProductEntity product =modelMapper.map(productDTO,ProductEntity.class);

        productRepository.save(product);
    }

    @Override
    public List<ProductDTO> findAllProducts() {
        List<ProductEntity> productEntities=productRepository.findAll();
        List<ProductDTO> productDTOList = modelMapper.mapAll(productEntities,ProductDTO.class);
        return productDTOList; //select * from products
    }

    @Override
    public ProductDTO findProductById(Long id) {
        ProductEntity product = productRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Product with id["+id+"]not found"));

        ProductDTO productDTO=modelMapper.map(product,ProductDTO.class);
        return productDTO;
    }

    @Override
    public void deleteProductById(Long id) {
        ProductEntity product = productRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Could not delete product with id["+id+"]not found"));;
            productRepository.deleteById(id);
    }

    @Override
    public ProductDTO findProductByName(String name) {
        ProductEntity product = productRepository.findByName(name);
        if(product==null){
            throw new ResourceNotFoundException("Product with id["+name+"]not found");}
        ProductDTO productDTO=modelMapper.map(product,ProductDTO.class);
        return productDTO;
    }


    @Override
    public List<ProductDTO> findProductByNameLikeAndPrice(String name, BigDecimal price) {
        List<ProductEntity> productEntities=productRepository.findByNameContainingAndPriceIsBefore(name,price);
        List<ProductDTO> productDTOList = modelMapper.mapAll(productEntities,ProductDTO.class);
        return productDTOList; //select * from products
    }
}
