package iasa.sc.site.Backend.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PrintDto {
    private Integer id;
    private String printType;
    private String photoUrl;

    @JsonCreator
    public PrintDto(@JsonProperty(value = "id") Integer id
            , @JsonProperty(value = "print_type") String printType
            , @JsonProperty(value = "photo_url") String photoUrl) {
        this.id = id;
        this.printType = printType;
        this.photoUrl = photoUrl;
    }
}
