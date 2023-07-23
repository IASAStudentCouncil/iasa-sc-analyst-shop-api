package iasa.sc.site.Backend.dtos;

import iasa.sc.site.Backend.entities.enums.ClothesBaseType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;


@Data
public class ClothesBaseDTO {
    private final Integer id;

    @NotNull(message = "type is required")
    private final ClothesBaseType type;

    @Min(value = 0, message = "price must be greater than or equal to zero")
    private final Integer price;

    private final List<ClothesBaseInfoDTO> info;
}
