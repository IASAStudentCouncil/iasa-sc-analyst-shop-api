package iasa.sc.site.Backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import iasa.sc.site.Backend.entity.Image;
import iasa.sc.site.Backend.entity.enums.PhotocardType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PhotocardDTO {
    @JsonProperty("id")
    private final Integer id;

    @JsonProperty("type")
    private final PhotocardType type;

    @JsonProperty("image_urls")
    private final List<Image> imageURLs;
}
