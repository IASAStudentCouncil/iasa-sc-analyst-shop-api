package iasa.sc.site.Backend.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import iasa.sc.site.Backend.entities.Image;
import lombok.Data;

import java.util.List;

@Data
public class PhotocardDTO {
    private final Integer id;

    private final String type;

    private final List<Image> images;

    public PhotocardDTO(@JsonProperty("id") Integer id,
                        @JsonProperty("type") String type,
                        @JsonProperty("image") List<Image> images) {
        this.id = id;
        this.type = type;
        this.images = images;
    }
}
