package iasa.sc.site.Backend.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import iasa.sc.site.Backend.entities.enums.ClothesBaseType;
import lombok.Data;

import java.util.List;


@Data
public class ClothesBaseDTO {
    private final Integer id;

    private final ClothesBaseType type;

    private final String text;

    private final Integer price;

    private final List<ClothesBaseInfoDTO> info;

    @JsonCreator
    public ClothesBaseDTO(@JsonProperty("id") Integer id,
                          @JsonProperty("type") ClothesBaseType type,
                          @JsonProperty("price") Integer price,
                          @JsonProperty("info") List<ClothesBaseInfoDTO> info,
                          @JsonProperty("text") String text) {
        this.id = id;
        this.type = type;
        this.price = price;
        this.info = info;
        this.text = text;
    }
}
