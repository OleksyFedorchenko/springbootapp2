package ua.logos.Validation;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class CheckCategoryNameBiggerThanOneSymbolValidator implements ConstraintValidator<CheckCategoryNameBiggerThanOneSymbol, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value.length() > 1) {
            return true;
        }
        return false;

    }
}
