package ua.logos.Validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
@Constraint(validatedBy = CheckCategoryNameBiggerThanOneSymbolValidator.class)
public @interface CheckCategoryNameBiggerThanOneSymbol {
    String message() default "Name less than one symbol";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
