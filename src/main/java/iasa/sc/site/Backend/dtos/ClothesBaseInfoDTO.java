package iasa.sc.site.Backend.dtos;

import iasa.sc.site.Backend.entities.Image;
import iasa.sc.site.Backend.entities.enums.ClothesBaseColor;
import iasa.sc.site.Backend.entities.enums.ClothesBaseSize;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Set;

@Data
public class ClothesBaseInfoDTO {
    private final Integer id;

    @Min(value = 0, message = "amount must be greater than or equal to zero")
    private final Integer amount;

    @NotNull(message = "color is required")
    private final ClothesBaseColor color;

    @NotNull(message = "size is required")
    private final ClothesBaseSize size;

    private final Set<Image> images;
}
