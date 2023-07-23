package iasa.sc.site.Backend.dtos;

import iasa.sc.site.Backend.entities.Image;
import iasa.sc.site.Backend.entities.enums.PrintType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class PrintDTO {
    private final Integer id;

    @NotNull(message = "type is required")
    private final PrintType printType;

    private final List<Image> images;
}
