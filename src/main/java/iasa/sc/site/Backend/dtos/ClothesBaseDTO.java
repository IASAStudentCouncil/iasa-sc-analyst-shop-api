package iasa.sc.site.Backend.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import iasa.sc.site.Backend.entities.enums.ClothesBaseType;
import lombok.Data;

import java.util.List;


@Data
public class ClothesBaseDTO {
    private Integer id;

    private ClothesBaseType type;

    private String text;

    private Integer price;

    private List<ClothesBaseInfoDto> info;

    @JsonCreator
    public ClothesBaseDTO(@JsonProperty("id") Integer id,
                          @JsonProperty("type") ClothesBaseType type,
                          @JsonProperty("price") Integer price,
                          @JsonProperty("info") List<ClothesBaseInfoDto> info,
                          @JsonProperty("text") String text) {
        this.id = id;
        this.type = type;
        this.price = price;
        this.info = info;
        this.text = text;
    }
}
