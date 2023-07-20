package iasa.sc.site.Backend.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonCreator
    public PrintDTO(@JsonProperty(value = "id") Integer id,
                    @JsonProperty(value = "print_type") PrintType printType,
                    @JsonProperty(value = "images") List<Image> images) {
        this.id = id;
        this.printType = printType;
        this.images = images;
    }
}
