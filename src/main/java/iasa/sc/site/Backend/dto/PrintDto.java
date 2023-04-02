package iasa.sc.site.Backend.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import iasa.sc.site.Backend.entity.Image;
import lombok.Data;

import java.util.List;

@Data
public class PrintDto {
    private Integer id;

    private String printType;

    private List<Image> photosUrl;

    private String text;

    @JsonCreator
    public PrintDto(@JsonProperty(value = "id") Integer id
            , @JsonProperty(value = "print_type") String printType
            , @JsonProperty(value = "text") String text
            , @JsonProperty(value = "photos_urls") List<Image> photosUrl) {
        this.id = id;
        this.text = text;
        this.printType = printType;
        this.photosUrl = photosUrl;
    }
}
