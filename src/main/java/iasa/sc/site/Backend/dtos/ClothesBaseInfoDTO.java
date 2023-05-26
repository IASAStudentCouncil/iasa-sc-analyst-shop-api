package iasa.sc.site.Backend.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @Min(value = 0, message = "count on storage must be greater than or equal to zero")
    private final Integer countOnStorage;

    @NotNull(message = "color is required")
    private final ClothesBaseColor color;

    @NotNull(message = "size is required")
    private final ClothesBaseSize clothesBaseSize;

    private final Set<Image> images;

    @JsonCreator
    public ClothesBaseInfoDTO(@JsonProperty("id") Integer id,
                              @JsonProperty("count_on_storage") Integer countOnStorage,
                              @JsonProperty("color") ClothesBaseColor color,
                              @JsonProperty("size") ClothesBaseSize clothesBaseSize,
                              @JsonProperty("images") Set<Image> images) {
        this.id = id;
        this.countOnStorage = countOnStorage;
        this.color = color;
        this.clothesBaseSize = clothesBaseSize;
        this.images = images;
    }
}
