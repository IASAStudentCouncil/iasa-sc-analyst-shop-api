package iasa.sc.site.Backend.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import iasa.sc.site.Backend.entities.Image;
import lombok.Data;

import java.util.List;

@Data
public class StationeryItemDTO {
    private final Integer id;

    private final String type;

    private final String name;

    private final Integer price;

    private final List<Image> images;

    public StationeryItemDTO(@JsonProperty("id") Integer id,
                             @JsonProperty("type") String type,
                             @JsonProperty("name") String name,
                             @JsonProperty("price") Integer price,
                             @JsonProperty("images") List<Image> images) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.price = price;
        this.images = images;
    }
}




