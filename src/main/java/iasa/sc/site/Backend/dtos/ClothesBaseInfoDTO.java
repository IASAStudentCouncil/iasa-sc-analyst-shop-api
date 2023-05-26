package iasa.sc.site.Backend.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import iasa.sc.site.Backend.entities.Image;
import iasa.sc.site.Backend.entities.enums.ClothesBaseColor;
import iasa.sc.site.Backend.entities.enums.ClothesBaseSize;
import lombok.Data;

import java.util.Set;

@Data
public class ClothesBaseInfoDTO {
    private final Integer id;

    private final Integer countOnStorage;

    private final ClothesBaseColor color;

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
