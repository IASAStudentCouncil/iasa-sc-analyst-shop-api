package iasa.sc.site.Backend.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import iasa.sc.site.Backend.entities.Image;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PhotocardDTO {
    private final Integer id;

    @NotNull(message = "type is required")
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
