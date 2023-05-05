package iasa.sc.site.Backend.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import iasa.sc.site.Backend.entities.Image;
import lombok.Data;

import java.util.List;

@Data
public class PrintDTO {
    private final Integer id;

    private final String printType;

    private final List<Image> photosUrl;

    private final String text;

    @JsonCreator
    public PrintDTO(@JsonProperty(value = "id") Integer id,
                    @JsonProperty(value = "print_type") String printType,
                    @JsonProperty(value = "text") String text,
                    @JsonProperty(value = "photos_urls") List<Image> photosUrl) {
        this.id = id;
        this.text = text;
        this.printType = printType;
        this.photosUrl = photosUrl;
    }
}
