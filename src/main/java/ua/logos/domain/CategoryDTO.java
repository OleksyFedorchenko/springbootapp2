package ua.logos.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.logos.Validation.CheckCategoryNameBiggerThanOneSymbol;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDTO {

    private Long id;
    @CheckCategoryNameBiggerThanOneSymbol
    @NotNull(message = "Field 'NAME' can't be null")
    private String name;
}
