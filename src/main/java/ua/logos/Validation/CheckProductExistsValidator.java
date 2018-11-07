package ua.logos.Validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.logos.Repository.ProductRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class CheckProductExistsValidator implements ConstraintValidator<CheckProductExist,String> {
    private ProductRepository productRepository;
    @Autowired
    public CheckProductExistsValidator(ProductRepository productRepository){
        this.productRepository=productRepository;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(productRepository.findByName(value)==null){
            return true;
        }
        return false;
    }
}
