package iasa.sc.site.Backend.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import iasa.sc.site.Backend.entities.Image;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class PrintDto {
    private Integer id;

    private String printType;

    private List<Image> photosUrl;

    private String text;

    private UUID uuid;

    @JsonCreator
    public PrintDto(@JsonProperty(value = "id") Integer id,
                    @JsonProperty(value = "print_type") String printType,
                    @JsonProperty(value = "text") String text,
                    @JsonProperty(value = "photos_urls") List<Image> photosUrl,
                    @JsonProperty(value = "uuid") UUID uuid) {
        this.id = id;
        this.text = text;
        this.printType = printType;
        this.photosUrl = photosUrl;
        this.uuid = uuid;
    }
}
