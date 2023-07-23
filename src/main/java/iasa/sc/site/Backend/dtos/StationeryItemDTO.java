package iasa.sc.site.Backend.dtos;

import iasa.sc.site.Backend.entities.Image;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class StationeryItemDTO {
    private final Integer id;

    @NotNull(message = "type is required")
    private final String type;

    @NotNull(message = "name is required")
    private final String name;

    @Min(value = 0, message = "price must be greater than or equal to zero")
    private final Integer price;

    private final List<Image> images;
}




