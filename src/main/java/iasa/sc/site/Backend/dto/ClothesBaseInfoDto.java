package iasa.sc.site.Backend.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import iasa.sc.site.Backend.entity.Image;
import iasa.sc.site.Backend.entity.enums.ClothesBaseColor;
import iasa.sc.site.Backend.entity.enums.ClothesBaseSize;
import lombok.Data;

import java.util.List;

@Data
public class ClothesBaseInfoDto {
    private Integer id;

    private Integer countOnStorage;

    private ClothesBaseColor color;

    private ClothesBaseSize clothesBaseSize;

    private List<Image> imageList;

    @JsonCreator
    public ClothesBaseInfoDto(@JsonProperty("id") Integer id
            , @JsonProperty("count_on_storage") Integer countOnStorage
            , @JsonProperty("color") ClothesBaseColor color
            , @JsonProperty("size") ClothesBaseSize clothesBaseSize
            , @JsonProperty("images") List<Image> imageList) {
        this.id = id;
        this.countOnStorage = countOnStorage;
        this.color = color;
        this.clothesBaseSize = clothesBaseSize;
        this.imageList = imageList;
    }
}
