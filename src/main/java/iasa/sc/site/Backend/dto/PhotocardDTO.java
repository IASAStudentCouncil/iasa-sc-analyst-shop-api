package iasa.sc.site.Backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import iasa.sc.site.Backend.entity.Image;
import lombok.Data;

@Data
public class PhotocardDTO {
    private final Integer id;
    private final String type;
    private final Image image;

    public PhotocardDTO(@JsonProperty("id") Integer id,
                        @JsonProperty("type") String type,
                        @JsonProperty("image") Image image) {
        this.id = id;
        this.type = type;
        this.image = image;
    }
}
