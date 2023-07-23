package iasa.sc.site.Backend.dtos;

import iasa.sc.site.Backend.entities.Image;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PhotocardDTO {
    private final Integer id;

    @NotNull(message = "type is required")
    private final String type;

    private final Image image;
}
