package ua.logos.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.logos.Validation.CheckProductExist;

import javax.validation.constraints.*;
import java.math.BigDecimal;


@Getter
@Setter
@NoArgsConstructor

public class ProductDTO {
    private Long id;

    @CheckProductExist
    @NotNull(message = "Field 'NAME' can't be null")
    @Size(min = 2, max = 150, message = "Name length should be between 2 and 150")
    private String name;

    @Size(min = 1, message = "Please type the description")
    private String description;

    @DecimalMin(value = "0.0", message = "Min value 0")
    @DecimalMax(value = "9999.9", message = "Max value 9999.9")
    private BigDecimal price;

    @Min(value = 0, message = "QWT min value 0")
    @Max(value = 10000)
    private int qwt;

    private String image;

    @NotNull(message = "Field CATEGORY can't be null")
    private CategoryDTO category;
}
