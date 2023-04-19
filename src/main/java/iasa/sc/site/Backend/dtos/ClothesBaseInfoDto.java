package iasa.sc.site.Backend.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import iasa.sc.site.Backend.entities.Image;
import iasa.sc.site.Backend.entities.enums.ClothesBaseColor;
import iasa.sc.site.Backend.entities.enums.ClothesBaseSize;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class ClothesBaseInfoDto {
    private Integer id;

    private Integer countOnStorage;

    private ClothesBaseColor color;

    private ClothesBaseSize clothesBaseSize;

    private List<Image> imageList;

    private UUID uuid;

    @JsonCreator
    public ClothesBaseInfoDto(@JsonProperty("id") Integer id,
                              @JsonProperty("count_on_storage") Integer countOnStorage,
                              @JsonProperty("color") ClothesBaseColor color,
                              @JsonProperty("size") ClothesBaseSize clothesBaseSize,
                              @JsonProperty("images") List<Image> imageList,
                              @JsonProperty("uuid") UUID uuid) {
        this.id = id;
        this.countOnStorage = countOnStorage;
        this.color = color;
        this.clothesBaseSize = clothesBaseSize;
        this.imageList = imageList;
        this.uuid = uuid;
    }
}
